package view.game;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import application.app.MainUtils;
import engine.model.Engine;
import engine.model.Move;
import engine.model.Node;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.elements.Colors;
import model.elements.Functions;
import model.elements.Kinds;
import model.elements.Squares;
import model.elements.States;
import model.game.Game;
import model.game.GameType;
import model.game.Player;
import view.analysis.evaluation.EvaluationBorderPaneListener;
import view.analysis.moves.MovesTableViewListener;
import view.analysis.search.PvTableViewListener;
import view.analysis.search.SearchAim;
import view.analysis.search.SearchStage;
import view.analysis.stage.AnalysisStage;
import view.engine.EngineHBoxListener;
import view.nextgame.NextGameStage;
import view.nodepane.NodePane;
import view.nodepane.NodePaneListener;
import view.options.UserOptions;
import view.preferences.PreferencesStage;
import view.promote.PromoteStage;
import view.state.StateStage;
import view.utilities.ToolBarButton;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef"})
public class GameStage extends Stage {

    private static final int BOARD_TILE_EDGE = 50;
    private static final Engine MOVES_ENGINE = Engine.newMiniMaxEngine();

    private Game game;

    private Node focusNode, panelNode;
    private Move focusMove, panelMove;

    private Player player;

    private boolean ongoingGame, drawOffered;

    private Player drawOfferPlayer;

    private long searchStartMillis;

    private Thread nextMoveThread;
    private Task<Void> nextMoveTask;

    private Timeline clockTimeline, pvTimeline, movesTimeline;

    private BorderPane rootBorderPane;
        private MenuBar menuBar;
            private Menu fileMenu;
                private MenuItem exitMenuItem;
            private Menu editMenu;
                private MenuItem prefsMenuItem;
            private Menu helpMenu;
                private MenuItem aboutMenuItem;
        private BorderPane controlsBorderPane;
            private ToolBar mainToolBar;
                private ToolBarButton newButton;
                private ToolBarButton openButton;
                private ToolBarButton saveButton;
            private ToolBar gameToolBar;
                private ToolBarButton stopGameButton;
                private ToolBarButton offerAcceptDrawButton;
                private ToolBarButton resignGameButton;
                private ToolBarButton helpToMoveButton;
            private HBox appHBox;
                private NodePane nodePane;
                private GameVBox gameVBox;

    private StateStage       stateStage;
    private PromoteStage     promoteStage;
    private NextGameStage    nextGameStage;
    private PreferencesStage preferencesStage;
    private AnalysisStage    analysisStage;
    private SearchStage      searchStage;

    private GameStageListener gameStageListener;

    public GameStage()
            throws Exception {

        startNullGame();
        newMenuBar();
        newControlsBorderPane();

        setTitle("game");
//        initModality(Modality.APPLICATION_MODAL);
//        initOwner();
//        initStyle(StageStyle.UNDECORATED);
//        setResizable(false);
        setOnCloseRequest(we -> System.exit(0));

        getIcons().add(ViewUtils.selectRandomIcon());

        setGameStage();

        setClockTimeLine();
        setMovesTimeLine();
        setPvTimeLine();

    }

    private void setGameStage()
            throws Exception {

        rootBorderPane = new BorderPane();

        rootBorderPane.getChildren().clear();

        rootBorderPane.setTop(menuBar);
        rootBorderPane.setCenter(controlsBorderPane);

        Scene s = new Scene(rootBorderPane);

        s.getStylesheets().add(getClass().getResource("game.css").toExternalForm());

        setScene(s);

        Platform.runLater(() -> GameStage.this.requestFocus());

    }

    private void resetGameStage()
            throws Exception {

        // no reset for menu
        resetControlsBorderPane();

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

        panelNode = focusNode;
        panelMove = null;

        ongoingGame = false;

        drawOffered = false;
        drawOfferPlayer = null;

    }

    private void newMenuBar() {

            exitMenuItem = new MenuItem("exit");
            exitMenuItem.setOnAction(ae -> System.exit(0));
        fileMenu = new Menu("_file");
        fileMenu.setMnemonicParsing(true);

        fileMenu.getItems().add(exitMenuItem);
            try {
                prefsMenuItem = new MenuItem("preferences...");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            prefsMenuItem.setOnAction(ae -> {
                try {

                    preferencesStage = new PreferencesStage(this, prefsMenuItem);
                    preferencesStage.showAndWait();

                    panelNode = focusNode;
                    panelMove = focusMove;
                    resetGameStage();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        editMenu = new Menu("_edit");
        editMenu.setMnemonicParsing(true);

        editMenu.getItems().add(prefsMenuItem);

            aboutMenuItem = new MenuItem("about");
        helpMenu = new Menu("_help");
        helpMenu.setMnemonicParsing(true);

        helpMenu.getItems().add(aboutMenuItem);

        menuBar = new MenuBar();

        setMenuBar();

    }

    private void setMenuBar() {

        menuBar.getMenus().clear();

        menuBar.getMenus().add(fileMenu);
        menuBar.getMenus().add(editMenu);
        menuBar.getMenus().add(helpMenu);

    }

    private void newControlsBorderPane()
            throws Exception {

        newMainToolBar();
        newGameToolBar();
        newAppHBox();

        controlsBorderPane = new BorderPane();

        setControlsBorderPane();

    }

    private void resetControlsBorderPane()
            throws Exception {

        resetGameToolBar();
        resetAppHBox();

        setControlsBorderPane();

    }

    private void setControlsBorderPane() {

        controlsBorderPane.getChildren().clear();

        if (ongoingGame) {
            resetGameToolBar();
            controlsBorderPane.setTop(gameToolBar);
        } else {
            setMainToolBar();
            controlsBorderPane.setTop(mainToolBar);
        }
        controlsBorderPane.setCenter(appHBox);

    }

    private void newMainToolBar() {

        newButton = new ToolBarButton("new game");
        newButton.setOnAction(ae -> {
            try {

                nextGameStage = new NextGameStage(this);
                nextGameStage.showAndWait();

                if (nextGameStage.isOkButtonPressed()) {

                    stopCurrentMoveThread();
                    startNextGame();

                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        openButton = new ToolBarButton("open game");
        openButton.setOnAction(ae -> {
            try {

                FileChooser fc = new FileChooser();
                fc.setInitialDirectory(new File(ViewUtils.GAMES_DEFAULT_PATH));
//                fc.getExtensionFilters().addAll(
//                        new FileChooser.ExtensionFilter("chess log", "*.log"));

                File fileToOpen = fc.showOpenDialog(getOwner());

                if (fileToOpen != null) {
                    game = GameLoader.readGame(fileToOpen);
                    if (game.nodesList.size() > 0) {
                        panelNode = game.startNode;
                        panelMove = game.movesList.get(0);
                        resetGameStage();
                    }
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        saveButton = new ToolBarButton("save game");
        saveButton.setOnAction(ae -> {
            try {

                FileChooser fc = new FileChooser();
                fc.setInitialDirectory(new File(ViewUtils.GAMES_DEFAULT_PATH));
                fc.setInitialFileName(game.getTitle());
//                fc.getExtensionFilters().addAll(
//                        new FileChooser.ExtensionFilter("chess log", "*.log"));

                File fileToSave = fc.showSaveDialog(getOwner());
                if (fileToSave != null) {
                    GameLoader.writeGame(game, fileToSave);
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        mainToolBar = new ToolBar();

        setMainToolBar();

    }

    private void setMainToolBar() {

        mainToolBar.getItems().clear();

        mainToolBar.getItems().add(newButton);
        mainToolBar.getItems().add(openButton);
        mainToolBar.getItems().add(saveButton);

    }

    private void startNextGame()
            throws Exception {

        GameType gameType;

        if (nextGameStage.getAnalysisGameRadioButton().isSelected()) {
            gameType = GameType.ANALYSIS;
        } else {
            gameType = GameType.MATCH;
        }

        startNewGame(nextGameStage.getGame(), gameType);

    }

    public void startNewGame(Game newGame, GameType gameType)
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

        panelNode = focusNode;
        panelMove = null;

        ongoingGame = true;

        drawOffered = false;
        drawOfferPlayer = null;

        if (gameType == GameType.ANALYSIS) {
            startAnalysisStage();
        } else {
            playClockTimeline();
        }

        stateStage = new StateStage(this, States.dialogDescription(focusNode.gameState));
        stateStage.show();

        checkNextNode();

    }

    private void newGameToolBar() {

        newStopGameButton();
        newOfferAcceptDrawButton();
        newResignGameButton();

        if (game.gameType == GameType.MATCH) {
            newHelpToMoveButton();
        }

        gameToolBar = new ToolBar();

        resetGameToolBar();

    }

    private void newStopGameButton() {

        stopGameButton = new ToolBarButton("stop game");

        stopGameButton.setOnAction(e -> {
            try {

                interruptGame();

                if (analysisStage != null
                        && analysisStage.isShowing()) {
                    analysisStage.hide();
                } else if (searchStage != null
                            && searchStage.isShowing()) {
                    searchStage.hide();
                }

                if (gameStageListener != null) {
                    gameStageListener.onGameEnded();
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

    }

    private void newOfferAcceptDrawButton() {

        offerAcceptDrawButton = new ToolBarButton("offer draw");

        offerAcceptDrawButton.setOnAction(ae -> {
            try {

                offerAcceptDraw();

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

    }

    private void newResignGameButton() {

        resignGameButton = new ToolBarButton("resign game");

        resignGameButton.setOnAction(ae -> {
            try {

                resignGame();

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

    }

    private void newHelpToMoveButton() {

        helpToMoveButton = new ToolBarButton("help to move");

        helpToMoveButton.setOnAction(ae -> {
            try {

                if (focusNode.gameState == States.ONGOING
                        || focusNode.gameState == States.CHECK) {

                    stopTimelines();

                    nodePane.startBoardProgressIndicator();

                    Platform.runLater(() -> {
                        try {

                            nodePane.stopBoardProgressIndicator();

                            startSearchStageHelp();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

    }

    private void resetGameToolBar() {

        if (drawOffered) {
            offerAcceptDrawButton.setText("accept draw");
        } else {
            offerAcceptDrawButton.setText("offer draw");
        }

        offerAcceptDrawButton.setDisable(player.kind == Kinds.ENGINE);

        resignGameButton.setDisable(player.kind == Kinds.ENGINE);

        if (game.gameType == GameType.MATCH) {
            helpToMoveButton.setDisable(player.kind == Kinds.ENGINE);
        }

        setGameToolBar();

    }

    private void setGameToolBar() {

        gameToolBar.getItems().clear();

        gameToolBar.getItems().add(stopGameButton);
        gameToolBar.getItems().add(offerAcceptDrawButton);
        gameToolBar.getItems().add(resignGameButton);

        if (game.gameType == GameType.MATCH) {
            gameToolBar.getItems().add(helpToMoveButton);
        }

    }

    private void newAppHBox()
            throws Exception {

        newNodePane();
        newGameVBox();

        appHBox = new HBox();

        appHBox.setPadding(new Insets(5));

        setAppHBox();

    }

    private void resetAppHBox()
            throws Exception {

        resetNodePane();
        resetGameVBox();

    }

    private void setAppHBox() {

        appHBox.getChildren().clear();

        appHBox.getChildren().add(nodePane);
        appHBox.getChildren().add(gameVBox);

        HBox.setHgrow(gameVBox, Priority.ALWAYS);

    }

    private void newNodePane()
            throws Exception {

        nodePane = new NodePane(this, panelNode, panelMove,
                UserOptions.showAvailablesOption, UserOptions.showNodeDetailsOption, false,
                BOARD_TILE_EDGE);

        nodePane.setNodePaneListener(new NodePaneListener() {

            @Override
            public void onPieceMoved(byte fromSquare, byte toSquare)
            		throws Exception {

                if (player.kind == Kinds.HUMAN) {
                    checkHumanNextNode(fromSquare, toSquare);
                }

            }

            @Override
            public void onPieceAdded(byte square) {} // not handled here

            @Override
            public void onPieceRemoved(byte square) {} // not handled here

            @Override
            public void onBoardReset(Node newNode) {} // not handled here

        });

        addEventFilter(KeyEvent.KEY_PRESSED, nodePane.getKeyEventHandler());

    }

    private void resetNodePane()
            throws Exception {

        removeEventFilter(KeyEvent.KEY_PRESSED, nodePane.getKeyEventHandler());

        nodePane.resetNodePane(panelNode, panelMove);

        addEventFilter(KeyEvent.KEY_PRESSED, nodePane.getKeyEventHandler());

    }

    private void startSearchStageHelp()
            throws Exception {

        searchStage = new SearchStage(this, focusNode, null, SearchAim.HELP);

        searchStage.getSearchHBox().getPvTableView().addPvTableViewListener(new PvTableViewListener() {

            @Override
            public void onVariationSelected(LinkedList<Move> movesList) {} // not handled here

            @Override
            public void onVariationToPlay(LinkedList<Move> movesList)
                    throws Exception {

                playVariation(movesList);

                searchStage.hide();

            }

            @Override
            public void onVariationToEvaluate(LinkedList<Move> movesList) {} // not handled here

            @Override
            public void onControlPageDownPressed() {} // not handled here

            @Override
            public void onControlPageUpPressed() {} // not handled here

        });

        searchStage.show();

        playClockTimeline();
        playPvTimeline();

    }

    private void playMove(Move move)
            throws Exception {

        stopCurrentMoveThread();

        if (move == null) {

            panelNode = focusNode;
            panelMove = focusMove;
            resetGameStage();

        } else {

            focusNode = new Node(focusNode, move);
            focusMove = move;

            game.nodesList.add(focusNode);
            game.movesList.add(focusMove);
            game.valuesList.add(player.engine.rootNodeValue);
            game.srchLensList.add(System.currentTimeMillis() - searchStartMillis);

            updateMovesStages();
            setNextPlayer();
            checkDrawOffered();
            checkNextNode();

        }

    }

    private void updateMovesStages()
            throws Exception {

        if (analysisStage != null
                && analysisStage.isShowing()) {
            Engine e = analysisStage.getEngineHBox().getEngine();
            analysisStage.getEvaluationBorderPane().resetEvaluationBorderPane(e, focusNode);
            analysisStage.getMovesHBox().resetMovesHBox(focusNode, focusMove, true);
            analysisStage.getSearchHBox().resetSearchHBox(focusNode, focusMove, true);
        } else if (searchStage != null
                    && searchStage.isShowing()) {
            searchStage.getSearchHBox().resetSearchHBox(focusNode, focusMove, true);
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

    private void playVariation(LinkedList<Move> movesList)
            throws Exception {

        int lastMoveIndex;

        stopCurrentMoveThread();

        for (int i = 0; i <= movesList.size() - 2; ++i) {

            focusMove = movesList.get(i);
            focusNode = new Node(focusNode, focusMove);

            game.nodesList.add(focusNode);
            game.movesList.add(focusMove);
            game.valuesList.add(player.engine.rootNodeValue);
            game.srchLensList.add(System.currentTimeMillis() - searchStartMillis);

//            setNextPlayer(); // non va fatto qui: si arriva qui in analysis e il player è uno solo
//            checkDrawOffered(); // idem

        }

        lastMoveIndex = movesList.size() - 1;
        playMove(movesList.get(lastMoveIndex));

    }

    private Node evaluateVariation(LinkedList<Move> movesList)
            throws Exception {

        stopCurrentMoveThread();

        Node evalNode = new Node(analysisStage.getEvaluationBorderPane().getNode());
        Move evalMove;

        for (int i = 0; i <= movesList.size() - 1; ++i) {

            evalMove = movesList.get(i);
            evalNode = new Node(evalNode, evalMove);

//            setNextPlayer(); // non va fatto qui: si arriva qui in analysis e il player è uno solo
//            checkDrawOffered(); // idem

        }

        startAnalysisMoveThread(evalNode);

        return evalNode;
    }

    private Node evaluateMove(Move move)
            throws Exception {

        stopCurrentMoveThread();

        Node evalNode = new Node(analysisStage.getEvaluationBorderPane().getNode());

        evalNode = new Node(evalNode, move);

//        setNextPlayer(); // non va fatto qui: si arriva qui in analysis e il player è uno solo
//        checkDrawOffered(); // idem

        startAnalysisMoveThread(evalNode);

        return evalNode;
    }

    private void startAnalysisStage()
    		throws Exception {

        analysisStage = new AnalysisStage(this, game.analysisPlayer.engine, focusNode, null);

        analysisStage.getEngineHBox().setEngineHBoxListener(new EngineHBoxListener() {

            @Override
            public void onEngineChanged(Engine newEngine)
                    throws Exception {

                stopCurrentMoveThread();

                newEngine.initMaps();
                newEngine.initHeuristicsLists();

                player.engine = newEngine;

                game.resetCurrentPlayer(newEngine);

                startAnalysisMoveThread(focusNode);

                panelNode = focusNode;
                panelMove = focusMove;
                resetGameStage();

                analysisStage.getEvaluationBorderPane().resetEvaluationBorderPane(newEngine, focusNode);
                analysisStage.getMovesHBox().resetMovesHBox(focusNode, focusMove, true);
                analysisStage.getSearchHBox().resetSearchHBox(focusNode, focusMove, true);
                // il ritorno al gioco è permesso anche se player è stato modificato - scelta arbitraria

            }

        });

        analysisStage.getEvaluationBorderPane().setEvaluationBorderPaneListener(new EvaluationBorderPaneListener() {

            @Override
            public void onEvaluationNodeChanged(Node resetNode)
                    throws Exception {

                stopCurrentMoveThread();
                startAnalysisMoveThread(resetNode);

                analysisStage.getMovesHBox().resetMovesHBox(resetNode, null, false);
                analysisStage.getSearchHBox().resetSearchHBox(resetNode, null, false);

            }

        });

        analysisStage.getMovesHBox().getMovesTableView().addMovesTableViewListener(new MovesTableViewListener() {

            @Override
            public void onMoveSelected(Move move) {} // not handled here

            @Override
            public void onMoveToPlay(Move move)
                    throws Exception {

                Node evalNode = new Node(analysisStage.getEvaluationBorderPane().getNode());

                if (evalNode.equals(focusNode)) {

                    playMove(move);

                    Engine e = analysisStage.getEngineHBox().getEngine();

                    analysisStage.getEvaluationBorderPane().resetEvaluationBorderPane(e, focusNode);
                    analysisStage.getMovesHBox().resetMovesHBox(focusNode, focusMove, true);
                    analysisStage.getSearchHBox().resetSearchHBox(focusNode, focusMove, true);

                }

            }

            @Override
            public void onMoveToEvaluate(Move move)
                    throws Exception {

                Node evalNode = evaluateMove(move);
                Move evalMove = move;

                Engine e = analysisStage.getEngineHBox().getEngine();

                analysisStage.getEvaluationBorderPane().resetEvaluationBorderPane(e, evalNode);
                analysisStage.getMovesHBox().resetMovesHBox(evalNode, evalMove, false);
                analysisStage.getSearchHBox().resetSearchHBox(evalNode, evalMove, false);

            }

            @Override
            public void onControlPageDownPressed() {} // not handled here

            @Override
            public void onControlPageUpPressed() {} // not handled here

        });

        analysisStage.getSearchHBox().getPvTableView().addPvTableViewListener(new PvTableViewListener() {

            @Override
            public void onVariationSelected(LinkedList<Move> movesList) {} // not handled here

            @Override
            public void onVariationToPlay(LinkedList<Move> movesList)
                    throws Exception {

                Node evalNode = new Node(analysisStage.getEvaluationBorderPane().getNode());

                if (evalNode.equals(focusNode)) {

                    playVariation(movesList);

                    Engine e = analysisStage.getEngineHBox().getEngine();

                    analysisStage.getEvaluationBorderPane().resetEvaluationBorderPane(e, focusNode);
                    analysisStage.getMovesHBox().resetMovesHBox(focusNode, focusMove, true);
                    analysisStage.getSearchHBox().resetSearchHBox(focusNode, focusMove, true);

                }

            }

            @Override
            public void onVariationToEvaluate(LinkedList<Move> movesList)
                    throws Exception {

                Node evalNode = evaluateVariation(movesList);
                Move evalMove = movesList.getLast();

                Engine e = analysisStage.getEngineHBox().getEngine();

                analysisStage.getEvaluationBorderPane().resetEvaluationBorderPane(e, evalNode);
                analysisStage.getMovesHBox().resetMovesHBox(evalNode, evalMove, false);
                analysisStage.getSearchHBox().resetSearchHBox(evalNode, evalMove, false);

            }

            @Override
            public void onControlPageDownPressed() {} // not handled here

            @Override
            public void onControlPageUpPressed() {} // not handled here

        });

        analysisStage.show();

        playMovesTimeline();
        playPvTimeline();

    }

    private void newGameVBox()
            throws Exception {

        gameVBox = new GameVBox(game);

        gameVBox.setGamePaneListener(new GamePaneListener() {

            @Override
            public void onMousePressed(MouseEvent e) {

                int row = gameVBox.getGameTableView().getSelectionModel().getSelectedCells().get(0).getRow();
                int col = gameVBox.getGameTableView().getSelectionModel().getSelectedCells().get(0).getColumn();

                if (e.isPrimaryButtonDown()) {
                    try {
                        resetGameTableEvent(row, col);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

            }

            @Override
            public void onKeyPressed(KeyEvent e) {

                if (e.getCode() == KeyCode.LEFT
                        || e.getCode() == KeyCode.RIGHT
                        || e.getCode() == KeyCode.UP
                        || e.getCode() == KeyCode.DOWN) {
                    int row = gameVBox.getGameTableView().getSelectionModel().getSelectedCells().get(0).getRow();
                    int col = gameVBox.getGameTableView().getSelectionModel().getSelectedCells().get(0).getColumn();
                    try {
                        resetGameTableEvent(row, col);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

            }

        });

    }

    private void resetGameVBox()
            throws Exception {

        gameVBox.resetGamePane(game);

    }

    private void resetGameTableEvent(int row, int col)
            throws Exception {

        // numero della mossa corrispondente a riga e colonna selezionati
        int moveIndex = 2 * (row - 1) + col + 1;

        // correzione nel caso della prima mossa al nero
        if (game.startNode.playerColor == Colors.BLACK) {
            if (moveIndex == -1 || moveIndex == 0) {
                moveIndex = -1;
            } else {
                --moveIndex;
            }
        }

        if (moveIndex == -1) {
            panelNode = game.startNode;
            panelMove = null;
            resetNodePane();
        } else {
            if (moveIndex <= game.movesList.size() - 1) {
                panelNode = game.nodesList.get(moveIndex);
                panelMove = game.movesList.get(moveIndex);
                resetNodePane();
            }
        }

    }

    /*
     * end of game
     */

    private void interruptGame()
            throws Exception {

        focusNode.gameState = States.GAME_INTERRUPTED;

        checkEndNode();

    }

    private void checkEndNode()
            throws Exception {

        stopCurrentMoveThread();
        checkGameState();

        panelNode = focusNode;
        panelMove = focusMove;
        resetGameStage();

    }

    private void offerAcceptDraw()
            throws Exception {

        if (drawOffered) {
            acceptDraw();
        } else {
            if (player.kind == Kinds.HUMAN) {
                offerDraw();
            }
        }

    }

    private void offerDraw()
            throws Exception {

        drawOffered = true;
        drawOfferPlayer = player;

        stateStage = new StateStage(this, "draw offered");
        stateStage.show();

        offerAcceptDrawButton.setDisable(true);

        setGameToolBar();

    }

    private void acceptDraw()
            throws Exception {

        focusNode.gameState = States.DRAW_AGREEMENT;

        checkEndNode();

    }

    private void resignGame()
            throws Exception {

        focusNode.gameState = States.PLAYER_RESIGNS;

        checkEndNode();

    }

    /*
     * left time
     */

    private void checkLeftTime()
            throws Exception {

        if (game.whiteLeftTimeMillis < MainUtils.MIN_TIME_MILLIS
                || game.blackLeftTimeMillis < MainUtils.MIN_TIME_MILLIS) {

            checkGameState();

            panelNode = focusNode;
            panelMove = focusMove;
            resetGameStage();

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

    /*
     * next node
     */

    private void checkHumanNextNode(Byte squareFrom, Byte squareTo)
            throws Exception {

        stopCurrentMoveThread();

        Move nextMove = checkHumanNextMove(squareFrom, squareTo);

        playMove(nextMove);

    }

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

        if (focusNode.gameState != States.ONGOING
                && (game.whitePlayer.kind == Kinds.HUMAN
                    || game.blackPlayer.kind == Kinds.HUMAN)) {

            stateStage = new StateStage(this, States.dialogDescription(focusNode.gameState));
            stateStage.show();

        }

    }

    private void checkTimeOut()
            throws Exception {

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

    private void checkThreeFoldReps()
            throws Exception {

        if (focusNode.gameState == States.THREEFOLD_REP) {
            if (!focusNode.isThreefoldRepetition(Engine.GAME_REPS_TO_THREEFOLD)) {
                focusNode.gameState = States.ONGOING;
            }
        }

    }

    private void endGame()
            throws Exception {

        ongoingGame = false;

        stopTimelines();
        stopCurrentMoveThread();

        if (gameStageListener != null) {
            gameStageListener.onGameEnded();
        }

    }

    private Move checkHumanNextMove(Byte fromSquare, Byte toSquare)
            throws Exception {

        MOVES_ENGINE.computePlayerMovesBb(focusNode);

        ArrayList<Move> nextMovesList = new ArrayList<>();

        for (Move move : MOVES_ENGINE.movesList) {
            if (move != null) {
                // scelgo l'arrocco corrispondente alle case from/to selezionate,
                // oppure (default) accumulo in movesList tutte le mosse disponibili
                // a parità di casa from/to (possono essere 4 in caso di promozione)
                switch (focusNode.playerColor) {
                case Colors.WHITE:
                    switch (move.function) {
                    case Functions.SHORT_CG:
                        if (fromSquare == Squares.SQUARE_E1 && toSquare == Squares.SQUARE_G1) { return move; }
                        break;
                    case Functions.LONG_CG:
                        if (fromSquare == Squares.SQUARE_E1 && toSquare == Squares.SQUARE_C1) { return move; }
                        break;
                    default:
                        if (move.fromSquare.equals(fromSquare)
                                && move.toSquare.equals(toSquare)) {
                            nextMovesList.add(move);
                        }
                        break;
                    }
                    break;
                case Colors.BLACK:
                    switch (move.function) {
                    case Functions.SHORT_CG:
                        if (fromSquare == Squares.SQUARE_E8 && toSquare == Squares.SQUARE_G8) { return move; }
                        break;
                    case Functions.LONG_CG:
                        if (fromSquare == Squares.SQUARE_E8 && toSquare == Squares.SQUARE_C8) { return move; }
                        break;
                    default:
                        if (move.fromSquare.equals(fromSquare)
                                && move.toSquare.equals(toSquare)) {
                            nextMovesList.add(move);
                        }
                        break;
                    }
                    break;
                default:
                    throw new Exception("focusNode.playerColor=" + focusNode.playerColor);
                }
            }
        }

        if (nextMovesList.size() == 0) {
            // nessuna mossa disponibile
            return null;
        } else if (nextMovesList.size() == 1) {
            // una sola mossa disponibile, diversa dall'arrocco:
            // scelgo per uguaglianza delle case from/to
            for (Move move : MOVES_ENGINE.movesList) {
                switch (move.function) {
                case Functions.SHORT_CG:
                case Functions.LONG_CG:
                    // già gestiti nel ciclo precedente
                    break;
                default:
                    if (move.fromSquare.equals(fromSquare)
                            && move.toSquare.equals(toSquare)) {
                        return move;
                    }
                    break;
                }
            }
        } else {
            // movesList.size() > 1
            // più mosse disponibili per case from/to: si tratta di promozione
            // scelgo attraverso il dialog dedicato
            promoteStage = new PromoteStage(this, focusNode);
            promoteStage.showAndWait();
            for (Move move : MOVES_ENGINE.movesList) {
                if (move != null
                        && move.fromSquare.equals(fromSquare)
                        && move.toSquare.equals(toSquare)
                        && move.promotionPiece != null
                        && move.promotionPiece.equals(promoteStage.getPiece())) {
                    return move;
                }
            }
        }

        return null;
    }

    private void checkEngineNextNode()
            throws Exception {

        stopCurrentMoveThread();

        Move nextMove = player.engine.pvMap.get(focusNode.nodeHashCode);

        playMove(nextMove);

    }

    public void checkNextNode()
            throws Exception {

        checkGameState();

        panelNode = focusNode;
        panelMove = focusMove;
        resetGameStage();

        if (focusNode.gameState == States.ONGOING
                || focusNode.gameState == States.CHECK) {

            switch (player.kind) {
            case Kinds.HUMAN:
                startAnalysisMoveThread(focusNode);
                break;
            case Kinds.ENGINE:
                startGameMoveThread();
                break;
            default:
                throw new Exception("currentPlayer.kind=" + player.kind);
            }

        }

    }

    /*
     * timelines
     */

    private void setClockTimeLine() {

        clockTimeline = new Timeline(new KeyFrame(Duration.millis(MainUtils.TIMER_PERIOD_MILLIS),
            ae -> {
                try {

                    if (game.timeControlSet) {
                        checkLeftTime();
                        switch (focusNode.playerColor) {
                        case Colors.WHITE: gameVBox.resetWhiteClockValue(); break;
                        case Colors.BLACK: gameVBox.resetBlackClockValue(); break;
                        default:
                            throw new Exception("focusNode.playerColor=" + focusNode.playerColor);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }));

        clockTimeline.setCycleCount(Timeline.INDEFINITE);

    }

    private void setMovesTimeLine() {

        movesTimeline = new Timeline(new KeyFrame(Duration.millis(MainUtils.MOVE_TIMELINE_PERIOD_MILLIS),
            ae -> {
                try {

                    if (player.engine != null) {
                        if (analysisStage != null
                                && analysisStage.isShowing()) {
                            analysisStage.getMovesHBox().setCurrentMoveRecord(player.engine.getMoveRecord());
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }));

        movesTimeline.setCycleCount(Timeline.INDEFINITE);

    }

    private void setPvTimeLine() {

        pvTimeline = new Timeline(new KeyFrame(Duration.millis(MainUtils.PV_TIMELINE_PERIOD_MILLIS),
            ae -> {
                try {

                    if (player.engine != null) {
                        if (analysisStage != null
                                && analysisStage.isShowing()) {
                            analysisStage.getSearchHBox().addPvRecord(player.engine.getPvRecord());
                        } else if (searchStage != null
                                    && searchStage.isShowing()) {
                            searchStage.getSearchHBox().addPvRecord(player.engine.getPvRecord());
                            if (player.engine.searchTimeOut()) {
                                pvTimeline.stop();
                                stateStage = new StateStage(searchStage, "play!");
                                stateStage.show();
                            }
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }));

        pvTimeline.setCycleCount(Timeline.INDEFINITE);

    }

    private void playClockTimeline() {

        if (game.timeControlSet) {
            clockTimeline.play();
        }

    }

    private void playMovesTimeline() {

        movesTimeline.play();

    }

    private void playPvTimeline() {

        pvTimeline.play();

    }

    private void stopTimelines() {

        if (clockTimeline.getStatus() == Animation.Status.RUNNING) {
            clockTimeline.stop();
        }

        if (movesTimeline.getStatus() == Animation.Status.RUNNING) {
            movesTimeline.stop();
        }

        if (pvTimeline.getStatus() == Animation.Status.RUNNING) {
            pvTimeline.stop();
        }

    }

    /*
     * threads
     */

    private void stopCurrentMoveThread() {

        player.engine.outsideStop = true;

        if (nextMoveThread != null) {
            nextMoveThread.interrupt();
        }

        try {
            Thread.sleep(MainUtils.THREAD_MOVE_PAUSE_MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void startAnalysisMoveThread(Node fromNode) {

        searchStartMillis = System.currentTimeMillis();

        nextMoveTask = new Task<>() {
            @Override
            protected Void call()
                    throws Exception {

                System.out.println("player=" + player);

                player.engine.searchBestMove(game, fromNode);

                return null;
            }
        };

        nextMoveThread = new Thread(nextMoveTask);

        nextMoveThread.setDaemon(true);
        nextMoveThread.start();

    }

    private void startGameMoveThread() {

        searchStartMillis = System.currentTimeMillis();

        nextMoveTask = new Task<>() {
            @Override
            protected Void call()
                    throws Exception {

                nodePane.startBoardProgressIndicator();

                System.out.println("player=" + player);

                player.engine.searchBestMove(game, focusNode);

                return null;
            }
        };

        nextMoveTask.setOnSucceeded((wse) -> {

            nodePane.stopBoardProgressIndicator();

            if (ongoingGame) { // altrimenti scatena threads in successione che si sovrappongono

                Platform.runLater(() -> {
                    try {

                        checkEngineNextNode();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

            }

        });

        nextMoveThread = new Thread(nextMoveTask);

        nextMoveThread.setDaemon(true);
        nextMoveThread.start();

    }

    public void setGameStageListener(GameStageListener newGameStageListener) {
        gameStageListener = newGameStageListener;
    }

}
