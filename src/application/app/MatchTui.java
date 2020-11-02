package application.app;

import engine.model.Engine;
import engine.model.Move;
import engine.model.Node;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.util.Duration;
import model.elements.Colors;
import model.elements.Kinds;
import model.elements.States;
import model.game.Game;
import model.game.Player;

import java.util.Scanner;

public class MatchTui {

    private static final Engine MOVES_ENGINE = Engine.newMiniMaxEngine();

    private Game game;

    private Node focusNode;
    private Move focusMove;

    private Player player;

    private boolean ongoingGame, drawOffered;

    // non gestito dal motore, ma per una partita tra umani è OK
    private Player drawOfferPlayer;

    private long searchStartMillis;

    private Thread nextMoveThread;

    private Timeline clockTimeline;

    private MatchTuiListener matchTuiListener;

    Scanner inputScanner;

    public MatchTui()
            throws Exception {

        inputScanner = new Scanner(System.in);

        startNullGame();
        setClockTimeLine();

    }

    private void startNullGame()
            throws Exception {

        game = Game.newNullGame();

        switch (game.startNode.playerColor) {
            case Colors.WHITE: player = game.whitePlayer; break;
            case Colors.BLACK: player = game.blackPlayer; break;
            default:
                throw new Exception("game.startNode.playerColor=" + game.startNode.playerColor);
        }

        focusNode = new Node(game.startNode);
        focusMove = null;

        ongoingGame = false;

        drawOffered = false;
        drawOfferPlayer = null;

    }

    public void startMatch(Game newGame)
            throws Exception {

        game = newGame;

        switch (game.startNode.playerColor) {
            case Colors.WHITE: player = game.whitePlayer; break;
            case Colors.BLACK: player = game.blackPlayer; break;
            default:
                throw new Exception("game.startNode.playerColor=" + game.startNode.playerColor);
        }

        focusNode = new Node(game.startNode);
        focusMove = null;

        ongoingGame = true;

        drawOffered = false;
        drawOfferPlayer = null;

        playClockTimeline();

        game.gameStartMillis = System.currentTimeMillis();

        checkNextNode();

    }

    /*
     * end of game
     */

    private void offerAcceptDraw()
            throws Exception {

        if (drawOffered) {
            acceptDraw();
        }

    }

    private void acceptDraw()
            throws Exception {

        focusNode.gameState = States.DRAW_AGREEMENT;

        checkEndNode();

    }

    private void checkEndNode()
            throws Exception {

        stopCurrentMoveThread();
        checkGameState();

    }

    private void resignGame()
            throws Exception {

        focusNode.gameState = States.PLAYER_RESIGNS;

        checkEndNode();

    }

    /*
     * next node
     */

    private void checkGameState()
            throws Exception {

        if (game.timeControlSet) {
            checkTimeOut();
        }

        checkAvailableMoves();
        checkThreeFoldReps();

        if (focusNode.gameState != States.ONGOING
                && focusNode.gameState != States.CHECK) {
            endGame();
        }

    }

    private void checkTimeOut() {

        if (focusNode.gameState == States.ONGOING
                || focusNode.gameState == States.CHECK) {
            if (game.whiteLeftTimeMillis < MainUtils.MIN_TIME_MILLIS
                    || game.blackLeftTimeMillis < MainUtils.MIN_TIME_MILLIS) {
                focusNode.gameState = States.TIME_OUT;
            }
        }

    }

    private void checkAvailableMoves()
            throws Exception {

        Node movesNode = new Node(focusNode);

        MOVES_ENGINE.computePlayerMovesBb(movesNode);

        if (MOVES_ENGINE.movesListMaxIndex == Engine.START_MOVES_LIST_INDEX) {
            switch (movesNode.gameState) {
                case States.ONGOING: focusNode.gameState = States.STALE_MATE; break;
                case States.CHECK:   focusNode.gameState = States.CHECKMATE;  break;
            }
        }

        player.engine.movesList = MOVES_ENGINE.movesList;
        player.engine.movesListMaxIndex = MOVES_ENGINE.movesListMaxIndex;

    }

    private void checkThreeFoldReps() {

        if (focusNode.gameState == States.THREEFOLD_REP) {
            if (focusNode.isThreefoldRepetition(Engine.GAME_REPS_TO_THREEFOLD)) {
                if (DebugUtils.searchStepByStepDebug
                        || DebugUtils.searchNextMoveDebug) {
                    System.out.println("isThreefoldRepetition!");
                }
            } else {
                if (DebugUtils.searchStepByStepDebug
                        || DebugUtils.searchNextMoveDebug) {
                    System.out.println("is NOT ThreefoldRepetition!");
                }
                focusNode.gameState = States.ONGOING;
            }
        }

    }

    private void endGame()
            throws Exception {

        if (DebugUtils.searchStepByStepDebug
                || DebugUtils.searchNextMoveDebug) {
            System.out.println("focusNode.gameState=" + States.toString(focusNode.gameState));
        }

        ongoingGame = false;

        stopClockTimeline();
        stopCurrentMoveThread();

        game.gameEndMillis = System.currentTimeMillis();
        game.gameLengthMillis = game.gameEndMillis - game.gameStartMillis;

        if (DebugUtils.endOfGameDebug) {
            System.out.println(game);
        }

        if (matchTuiListener != null) {
            matchTuiListener.onGameEnded();
        }

    }

    private void setNextPlayer()
            throws Exception {

        switch (focusNode.playerColor) {
            case Colors.WHITE: player = game.whitePlayer; break;
            case Colors.BLACK: player = game.blackPlayer; break;
            default:
                throw new Exception("focusNode.playerColor=" + focusNode.playerColor);
        }

    }

    private void checkDrawOffered() {

        if (drawOffered
                && player == drawOfferPlayer) {
            drawOffered = false;
        }

    }

    private void checkNextNode()
            throws Exception {

        checkGameState();

        Move nextMove;

        if (focusNode.gameState == States.ONGOING
                || focusNode.gameState == States.CHECK) {

            switch (player.kind) {
                case Kinds.ENGINE: startNextMoveThread(focusNode); break;
                case Kinds.HUMAN:  requestNextMove();              break;
            }

        }

    }

    private void startNextMoveThread(Node fromNode) {

        searchStartMillis = System.currentTimeMillis();

        Task<Void> nextMoveTask = new Task<>() {
            @Override
            protected Void call()
                    throws Exception {

                System.out.println("player=" + player);

                player.engine.nextMove(game, focusNode);

                return null;
            }
        };

        nextMoveTask.setOnSucceeded((wse) -> {
            try {
                if (ongoingGame) {  // altrimenti scatena threads in successione che si sovrappongono

                    stopCurrentMoveThread();

                    Integer intMove = player.engine.pvMap.get(focusNode.nodeHashCode);
                    focusMove = new Move(intMove);

                    playMove();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        nextMoveThread = new Thread(nextMoveTask);
        nextMoveThread.setDaemon(true);

        nextMoveThread.start();

    }

    private void requestNextMove()
            throws Exception {

        System.out.println("player=" + player);
        System.out.println(focusNode.toStringDebug());

        requestNextMove1();

    }

    private void requestNextMove1()
            throws Exception {

        boolean moveOk = false;
        while (!moveOk) {
            if (drawOffered) {
                System.out.println("draw offered - enter your move or resign:");
            } else {
                if (game.whitePlayer.kind == Kinds.HUMAN
                        && game.blackPlayer.kind == Kinds.HUMAN) {
                    System.out.println("enter your move, offer draw or resign:");
                } else {
                    System.out.println("enter your move or resign:");
                }
            }
            String humanInput = inputScanner.nextLine();
            switch (humanInput) {
                case "offerdraw":
                    if (game.whitePlayer.kind == Kinds.HUMAN
                            && game.blackPlayer.kind == Kinds.HUMAN) {
                        if (drawOffered) {
                            focusMove = null;
                        } else {
                            drawOffered = true;
                            requestNextMove1();
                            focusMove = new Move(humanInput, focusNode);
                        }
                        moveOk = true;
                        offerAcceptDraw();
                    }
                    break;
                case "resign":
                    resignGame();
                    focusMove = null;
                    moveOk = true;
                    break;
                default:
                    try {
                        focusMove = new Move(humanInput, focusNode);
                        moveOk = true;
                    } catch (Exception e) {
                        System.out.println("input KO");
                    }
            }
        }

        if (ongoingGame) {
            playMove();
        }

    }

    private void playMove()
            throws Exception {

        stopCurrentMoveThread();

        if (focusMove == null) {
            System.out.println("focusMove == null");
        } else {
            focusNode = new Node(focusNode, focusMove);

            game.nodesList.add(focusNode);
            game.movesList.add(focusMove);
            game.valuesList.add(player.engine.rootNodeValue);
            game.srchLensList.add(System.currentTimeMillis() - searchStartMillis);

            setNextPlayer();
            checkDrawOffered();
            checkNextNode();
        }

    }

    /*
     * clock
     */

    private void setClockTimeLine() {

        clockTimeline = new Timeline(new KeyFrame(Duration.millis(MainUtils.TIMER_PERIOD_MILLIS),
                ae -> {
                    try {
                        if (game.timeControlSet) {
                            checkLeftTime();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
        );

        clockTimeline.setCycleCount(Timeline.INDEFINITE);

    }

    private void checkLeftTime()
            throws Exception {

        if (game.whiteLeftTimeMillis < MainUtils.MIN_TIME_MILLIS
                || game.blackLeftTimeMillis < MainUtils.MIN_TIME_MILLIS) {
            checkGameState();
        } else if (focusNode.gameState == States.ONGOING
                || focusNode.gameState == States.CHECK) {
            switch (focusNode.playerColor) {
                case Colors.WHITE: game.whiteLeftTimeMillis -= MainUtils.TIMER_PERIOD_MILLIS; break;
                case Colors.BLACK: game.blackLeftTimeMillis -= MainUtils.TIMER_PERIOD_MILLIS; break;
                default:
                    throw new Exception("focusNode.playerColor=" + focusNode.playerColor);
            }
        }

    }

    private void playClockTimeline() {

        if (game.timeControlSet) {
            clockTimeline.play();
        }

    }

    private void stopClockTimeline() {

        if (clockTimeline.getStatus() == Animation.Status.RUNNING) {
            clockTimeline.stop();
        }

    }

    /*
     * threads
     */

    private void stopCurrentMoveThread()
            throws Exception {

        player.engine.outsideStop = true;

        if (nextMoveThread != null) {
            nextMoveThread.interrupt();
        }

        Thread.sleep(MainUtils.THREAD_MOVE_PAUSE_MILLIS);

    }

    public void setGameBackgroundListener(MatchTuiListener newMatchTuiListener) {
        matchTuiListener = newMatchTuiListener;
    }

}
