package model.game;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import engine.model.Engine;
import engine.model.Move;
import engine.model.Node;
import model.elements.Colors;
import model.elements.Kinds;
import model.elements.States;
import model.utilities.FenStrings;

/**
 * Contiene i dati di una partita:
 *  * giocatori
 *  * posizione iniziale
 *  * gestione del tempo (si/no, tempo iniziale, incremento, tempo residuo)
 *  * risultato
 *  * mosse e dati collegati (tempo di calcolo e valore attribuito).
 */
public final class Game {

    private static final Engine MOVES_ENGINE = Engine.newMiniMaxEngine();

    private static final SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss.SSS");
    private static final SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm:ss.SSS");

    public GameType gameType;

    public Node startNode;

    public boolean timeControlSet;

    public Player whitePlayer;
    public Integer whiteStartLeftTimeMillis;
    public Integer whiteTimeIncrementMillis;
    public Integer whiteLeftTimeMillis;

    public Player blackPlayer;
    public Integer blackStartLeftTimeMillis;
    public Integer blackTimeIncrementMillis;
    public Integer blackLeftTimeMillis;

    public Player analysisPlayer;

    public String result;

    public LinkedList<Node> nodesList;
    public LinkedList<Move> movesList;
    public LinkedList<Long> srchLensList;
    public LinkedList<Integer> valuesList;

    public long gameStartMillis;
    public long gameEndMillis;
    public long gameLengthMillis;

    public Game() {

        gameType = null;

        startNode = null;

        timeControlSet = false;

        whitePlayer = null;
        whiteStartLeftTimeMillis = null;
        whiteTimeIncrementMillis = null;
        whiteLeftTimeMillis = null;

        blackPlayer = null;
        blackStartLeftTimeMillis = null;
        blackTimeIncrementMillis = null;
        blackLeftTimeMillis = null;

        analysisPlayer = null;

        result = null;

        gameStartMillis = 0L;
        gameEndMillis = 0L;
        gameLengthMillis = 0L;

        nodesList    = new LinkedList<>();
        movesList    = new LinkedList<>();
        srchLensList = new LinkedList<>();
        valuesList   = new LinkedList<>();

    }

    public static Game newDefaultGame()
            throws Exception {

        Game defaultGame = new Game();

        defaultGame.gameType = GameType.MATCH;

        defaultGame.startNode = new Node(FenStrings.STD);

        defaultGame.timeControlSet = true;

        defaultGame.whitePlayer = new Player(Kinds.HUMAN, new Engine());
        defaultGame.whiteStartLeftTimeMillis = 2 * 60000;
        defaultGame.whiteTimeIncrementMillis = 1000;
        defaultGame.whiteLeftTimeMillis = defaultGame.whiteStartLeftTimeMillis;

        defaultGame.blackPlayer = new Player(Kinds.HUMAN, new Engine());
        defaultGame.blackStartLeftTimeMillis = 2 * 60000;
        defaultGame.blackTimeIncrementMillis = 1000;
        defaultGame.blackLeftTimeMillis = defaultGame.blackStartLeftTimeMillis;

        defaultGame.result = null;

        defaultGame.analysisPlayer = new Player(Kinds.HUMAN, new Engine());

        return defaultGame;
    }

    public static Game newDefaultAnalysisGame(final Node node, final Player analysisPlayer) {

        Game defaultGame = new Game();

        defaultGame.gameType = GameType.ANALYSIS;

        defaultGame.timeControlSet = false;

        defaultGame.whitePlayer = analysisPlayer;
//        defaultGame.whiteStartLeftTimeMillis = 2 * 60000;
//        defaultGame.whiteTimeIncrementMillis = 1000;
//        defaultGame.whiteLeftTimeMillis = defaultGame.whiteStartLeftTimeMillis;

        defaultGame.blackPlayer = analysisPlayer;
//        defaultGame.blackStartLeftTimeMillis = 2 * 60000;
//        defaultGame.blackTimeIncrementMillis = 1000;
//        defaultGame.blackLeftTimeMillis = defaultGame.blackStartLeftTimeMillis;

        defaultGame.startNode = node;

        defaultGame.result = null;

        defaultGame.analysisPlayer = analysisPlayer;

        defaultGame.nodesList    = new LinkedList<>();
        defaultGame.movesList    = new LinkedList<>();
        defaultGame.srchLensList = new LinkedList<>();
        defaultGame.valuesList   = new LinkedList<>();

        return defaultGame;
    }

    public static Game newNullGame()
            throws Exception {

        Game nullGame = new Game();

        nullGame.gameType = GameType.MATCH;

        nullGame.startNode = new Node(FenStrings.STD);

        nullGame.timeControlSet = true;

        nullGame.whitePlayer = new Player(Kinds.HUMAN, MOVES_ENGINE);
        nullGame.whiteStartLeftTimeMillis = 0;
        nullGame.whiteTimeIncrementMillis = 0;
        nullGame.whiteLeftTimeMillis = nullGame.whiteStartLeftTimeMillis;

        nullGame.blackPlayer = new Player(Kinds.HUMAN, MOVES_ENGINE);
        nullGame.blackStartLeftTimeMillis = 0;
        nullGame.blackTimeIncrementMillis = 0;
        nullGame.blackLeftTimeMillis = nullGame.blackStartLeftTimeMillis;

        nullGame.result = null;

        nullGame.analysisPlayer = new Player(Kinds.HUMAN, MOVES_ENGINE);

        nullGame.nodesList    = new LinkedList<>();
        nullGame.movesList    = new LinkedList<>();
        nullGame.srchLensList = new LinkedList<>();
        nullGame.valuesList   = new LinkedList<>();

        return nullGame;
    }

    /**
     * @return La descrizione della partita. Ad es.:
     *
     * * per una partita tra 2 giocatori:
     * match w=H@default b=E@default
     * position=rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
     * time=00:02:00+1s|00:02:00+1s    -- oppure time=no
     * start=2020/10/15-15:39:11 end=2020/10/15-15:43:11
     * length=00:03:50 left=00:00:06|00:00:04
     * result=0-0 STALE_MATE
     * moves=1.e4 e5 2.N
     *
     * * per una partita di analisi:
     * analysis w=H@default
     * position=rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
     * time=no
     * start=2020/10/15-15:39:11 end=2020/10/15-15:43:11
     * length=00:03:50
     * result=0-0 STALE_MATE
     * moves=1.e4 e5 2.N
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        switch (gameType) {
        case ANALYSIS:
            sb.append(" analysis p=")
                    .append(analysisPlayer);
            break;
        case MATCH:
            sb.append(" match w=")
                    .append(whitePlayer)
                    .append(" vs b=")
                    .append(blackPlayer);
            break;
        default:
            break;
        }

        if (nodesList.size() > 0) {
            try {
                sb.append(" position=")
                        .append(nodesList.getFirst().toFen());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        sb.append(" time=");
        if (timeControlSet) {
            sb.append(whiteStartLeftTimeMillis).append("+")
                    .append(whiteTimeIncrementMillis)
                    .append("|")
                    .append(blackStartLeftTimeMillis).append("+")
                    .append(blackTimeIncrementMillis);
        } else {
            sb.append("no");
        }

        sb.append(" start=")
                .append(sdfdate.format(new Date(gameStartMillis)));

        sb.append(" end=")
                .append(sdfdate.format(new Date(gameEndMillis)));

        sb.append(" length=")
                .append(sdfdate.format(new Date(gameLengthMillis)));

        if (timeControlSet) {
            sb.append(" left=")
                    .append(sdftime.format(new Date(whiteLeftTimeMillis)))
                    .append("|")
                    .append(sdftime.format(new Date(blackLeftTimeMillis)));
        }

        if (nodesList.size() > 0) {

            try {
                switch (nodesList.getLast().gameState) {
                case States.CHECKMATE:
                case States.PLAYER_RESIGNS:
                    switch (nodesList.getLast().playerColor) {
                    case Colors.WHITE: result = "0-1 " + States.toString(nodesList.getLast().gameState); break;
                    case Colors.BLACK: result = "1-0 " + States.toString(nodesList.getLast().gameState); break;
                    default:
                        throw new Exception("nodesList.getLast().playerColor="
                                + nodesList.getLast().playerColor);
                    }
                    break;
                case States.TIME_OUT:
                    if (whiteLeftTimeMillis < blackLeftTimeMillis) {
                        result = "0-1 " + States.toString(nodesList.getLast().gameState);
                    } else {
                        result = "1-0 " + States.toString(nodesList.getLast().gameState);
                    }
                    break;
                case States.STALE_MATE:
                case States.THREEFOLD_REP:
                case States.FIFTY_MOVES:
                case States.IMPOSSIBLE_MATE:
                case States.DRAW_AGREEMENT:
                    result = "0-0 " + States.toString(nodesList.getLast().gameState);
                    break;
                case States.GAME_INTERRUPTED:
                case States.ONGOING:
                case States.CHECK:
                case States.NOT_LEGAL:
                    result = "? " + States.toString(nodesList.getLast().gameState);
                    break;
                default:
                    throw new Exception("nodesList.getLast().gameState="
                            + nodesList.getLast().gameState);
                }
                sb.append(" result=")
                        .append(result);
            } catch (Exception e) {
                e.printStackTrace();
            }

            sb.append(" moves=");
            int fullMoves = 1;
            String fullMoveString = "";
            try {
                switch (nodesList.getFirst().playerColor) {
                case Colors.WHITE: fullMoveString = fullMoves + ".";   break;
                case Colors.BLACK: fullMoveString = fullMoves + "..."; break;
                default:
                    throw new Exception("nodesList.getFirst().playerColor=" + nodesList.getFirst().playerColor);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            for (int i = 0; i <= nodesList.size() - 1; ++ i) {
                try {
                    String twoSpaces = new String(new char[2]);
                    sb.append(" ")
                            .append(fullMoveString)
                            .append(movesList.get(i).toSan()
                                    .replaceAll(twoSpaces, " ")
                                    .replaceAll(twoSpaces, " ")
                                    .replaceAll(twoSpaces, " ")
                                    .replaceAll(twoSpaces, " ")
                                    .replaceAll(twoSpaces, " ")
                                    .replaceAll(twoSpaces, " "))
                            .append("[l=")
                            .append(srchLensList.get(i))
                            .append("ms")
                            .append("/v=")
                            .append(valuesList.get(i));
                    if (nodesList.get(i).gameState != States.ONGOING) {
                        sb.append("/s=")
                                .append(States.toString(nodesList.get(i).gameState));
                    }
                    sb.append("]");
                    switch (nodesList.get(i).playerColor) {
                    case Colors.WHITE:              fullMoveString = "";              break;
                    case Colors.BLACK: ++fullMoves; fullMoveString = fullMoves + "."; break;
                    default:
                        throw new Exception("nodesList.get(i).playerColor=" + nodesList.get(i).playerColor);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return sb.toString();
    }

    public String getTitle()
            throws Exception {

        switch (gameType) {
        case MATCH:
            return "game " + whitePlayer.toString() + " vs " + blackPlayer.toString()
                + " " + new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date());

        case ANALYSIS:
            return "analysis " + analysisPlayer.toString()
                + " " + new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date());

        default:
            throw new Exception("gameType=" + gameType);
        }

    }

    public void resetCurrentPlayer(final Engine newEngine)
            throws Exception {

        switch (gameType) {
        case MATCH:
            throw new Exception("Game.resetCurrentPlayer: modifica player non prevista in MATCH");

        case ANALYSIS:
            analysisPlayer.engine = newEngine;

            whitePlayer = analysisPlayer;
            blackPlayer = analysisPlayer;

            break;
        default:
            throw new Exception("gameType=" + gameType);
        }

    }

}
