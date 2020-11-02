package view.nextgame;

import engine.model.Node;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.elements.Kinds;
import model.game.Game;
import model.game.GameType;
import view.engine.EngineStage;
import view.nodepane.NodePane;
import view.utilities.ToolBarButton;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class NextGameStage extends Stage {

    private static final String[] KINDS_STRING_LIST = { "engine", "human" };
    private static final int BOARD_TILE_EDGE = 37;

    private Stage ownerStage;
    private Game game;

    private boolean okButtonPressed = false;

    private BorderPane rootBorderPane;
        private ToolBar okToolBar = new ToolBar();
            private ToolBarButton okToolBarButton = new ToolBarButton("ok");
        private HBox controlsHBox = new HBox();
//            private ListView<String> gamesListView = new ListView<String>();
            private TabPane tabPane;
                private VBox gameDataVBox = new VBox();
                    private GridPane gameTypeGridPane = new GridPane();
                        private Label gameTypeLabel                   = new Label("game type");
                        private RadioButton matchGameRadioButton      = new RadioButton("match");
                        private RadioButton analysisGameRadioButton   = new RadioButton("analysis");
                    private GridPane playersGridPane = new GridPane();
                        private Label playersWhiteLabel               = new Label("white");
                        private Label playersBlackLabel               = new Label("black");
                        private ComboBox<String> whiteKindsComboBox   = new ComboBox<>();
                        private Button whiteEngineDefButton           = new Button();
                        private ComboBox<String> blackKindsComboBox   = new ComboBox<>();
                        private Button blackEngineDefButton           = new Button();
                        private CheckBox timeControlCheckBox          = new CheckBox("time control");
                        private Label whiteLeftTimeLabel              = new Label("time (min)");
                        private Spinner<Integer> whiteLeftTimeMinutesSpinner;
                        private Label whiteTimeIncrLabel              = new Label("incr (sec)");
                        private Spinner<Integer> whiteTimeIncrSecondsSpinner;
                        private Label blackLeftTimeLabel              = new Label("time (min)");
                        private Spinner<Integer> blackLeftTimeMinutesSpinner;
                        private Label blackTimeIncrLabel              = new Label("incr (sec)");
                        private Spinner<Integer> blackTimeIncrSecondsSpinner;
                    private Button analysisPlayerButton               = new Button();
                private NodePane nodePane;

    private EngineStage engineStage;

    public Game getGame() {
        return game;
    }

    public RadioButton getAnalysisGameRadioButton() {
        return analysisGameRadioButton;
    }

    public boolean isOkButtonPressed() {
        return okButtonPressed;
    }

    public NextGameStage(Stage newOwnerStage)
            throws Exception {

        ownerStage = newOwnerStage;

        game = Game.newDefaultGame();

        whiteLeftTimeMinutesSpinner =
                new Spinner<>(1, 1000, game.whiteLeftTimeMillis / 60000, 1);
        blackLeftTimeMinutesSpinner =
                new Spinner<>(1, 1000, game.blackLeftTimeMillis / 60000, 1);

        whiteTimeIncrSecondsSpinner =
                new Spinner<>(0, 1000, game.whiteTimeIncrementMillis / 1000, 1);
        blackTimeIncrSecondsSpinner =
                new Spinner<>(0, 1000, game.blackTimeIncrementMillis / 1000, 1);

        newOkToolBar();
        newControlsHBox();

        setNextGameStage();

        setTitle("next game");
        initModality(Modality.APPLICATION_MODAL);
        initOwner(ownerStage);
//        initStyle(StageStyle.UNDECORATED);
        setResizable(false);
//        setOnCloseRequest(we -> System.exit(0) );

        getIcons().add(ViewUtils.selectRandomIcon());

    }

    public void resetNextGameStage()
            throws Exception {

        resetControlsHBox();

    }

    private void setNextGameStage() {

        rootBorderPane = new BorderPane();

        rootBorderPane.setTop(okToolBar);
        rootBorderPane.setCenter(controlsHBox);

        okToolBarButton.requestFocus();

        Scene s = new Scene(rootBorderPane);

        s.getStylesheets().add(getClass().getResource("nextGame.css").toExternalForm());

        setScene(s);

    }

    private void newOkToolBar() {

        newOkButton();

        setOkToolBar();

    }

    private void setOkToolBar() {

        okToolBar.getItems().clear();

        okToolBar.getItems().add(okToolBarButton);

    }

    private void newOkButton() {

        okToolBarButton.setOnAction(ae -> {
            try {

                setParms();
                okButtonPressed = true;
                NextGameStage.this.hide();

            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText("");
                alert.setContentText("invalid parms - logic error");
                alert.showAndWait();
                e.printStackTrace();
            }
        });

    }

    private void setParms()
            throws Exception {

        if (analysisGameRadioButton.isSelected()) {
            game.gameType = GameType.ANALYSIS;
        } else if (matchGameRadioButton.isSelected()) {
            game.gameType = GameType.MATCH;
            switch (whiteKindsComboBox.getSelectionModel().getSelectedItem()) {
            case "engine" : game.whitePlayer.kind = Kinds.ENGINE; break;
            case "human"  : game.whitePlayer.kind = Kinds.HUMAN;  break;
            default:
                throw new Exception("whiteKindsComboBox.getSelectionModel().getSelectedItem()="
                        + whiteKindsComboBox.getSelectionModel().getSelectedItem());
            }
            switch (blackKindsComboBox.getSelectionModel().getSelectedItem()) {
            case "engine" : game.blackPlayer.kind = Kinds.ENGINE; break;
            case "human"  : game.blackPlayer.kind = Kinds.HUMAN;  break;
            default:
                throw new Exception("blackKindsComboBox.getSelectionModel().getSelectedItem()="
                        + blackKindsComboBox.getSelectionModel().getSelectedItem());
            }
        } else {
            throw new Exception("match*/analysis* + *GameRadioButton");
        }

        String fenString = nodePane.getFenTextField().getText();

        game.startNode = new Node(fenString);

        game.timeControlSet = timeControlCheckBox.isSelected();

        if (game.timeControlSet) {
            game.whiteLeftTimeMillis      = whiteLeftTimeMinutesSpinner.getValue() * 60000;
            game.blackLeftTimeMillis      = blackLeftTimeMinutesSpinner.getValue() * 60000;
            game.whiteTimeIncrementMillis = whiteTimeIncrSecondsSpinner.getValue() * 1000;
            game.blackTimeIncrementMillis = blackTimeIncrSecondsSpinner.getValue() * 1000;
        } else {
            game.whiteLeftTimeMillis      = 0;
            game.blackLeftTimeMillis      = 0;
            game.whiteTimeIncrementMillis = 0;
            game.blackTimeIncrementMillis = 0;
        }

    }

    private void newControlsHBox()
            throws Exception {

//        newGamesListView();
        newTabPane();

        setControlsHBox();

    }

    private void resetControlsHBox()
            throws Exception {

//        resetGamesListView();
        resetTabPane();

    }

//    private void newGamesListView() {
//
//        gamesListView.setPrefWidth(350);
//
//        gamesListView.setStyle("-fx-control-inner-background: lavender ;"
//                + "-fx-control-inner-background-alt: derive(-fx-control-inner-background, 50%) ; ");
//
//        loadPlayersListView();
//
//        gamesListView.setOnKeyPressed(ke -> {
//            try {
//
//                if (ke.getCode() == KeyCode.ENTER) {
//
//                    File gameFile = new File(Utilities.GAMES_DEFAULT_PATH
//                            + "\\" + gamesListView.getSelectionModel().getSelectedItem());
//
//                    game = GameLoader.readGame(gameFile);
//
//                    resetNextGameStage();
//
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//
//    }
//
//    private void resetGamesListView() {
//
//        loadPlayersListView();
//
//    }
//
//    private void loadPlayersListView() {
//
//        File folder = new File("games");
//        File[] filesList = folder.listFiles();
//
//        gamesListView.getItems().clear();
//
//        for(File file : filesList) {
//            if (file.isFile()) {
//                gamesListView.getItems().add(file.getName());
//            }
//        }
//
//    }

    private void setControlsHBox() {

        controlsHBox.getChildren().clear();

//        controlsHBox.getChildren().add(gamesListView);
        controlsHBox.getChildren().add(tabPane);

    }

    private void newTabPane()
            throws Exception {

        newGameDataVBox();
        newNodePane();

        tabPane = new TabPane();

        setTabPane();

    }

    private void resetTabPane()
            throws Exception {

        resetGameDataVBox();
        resetNodePane();

    }

    private void setTabPane() {

        Tab gameDataPaneTab = new Tab("game parms", gameDataVBox);
        gameDataPaneTab.setClosable(false);

        Tab nodePaneTab = new Tab("start position", nodePane);
        nodePaneTab.setClosable(false);

        tabPane.getTabs().clear();

        tabPane.getTabs().add(0, gameDataPaneTab);
        tabPane.getTabs().add(1, nodePaneTab);

    }

    private void newGameDataVBox()
            throws Exception {

        gameDataVBox.setAlignment(Pos.TOP_CENTER);
        gameDataVBox.setSpacing(5);

        newGameTypeGridPane();

        newPlayersGridPane();
        newAnalysisPlayerButton();

        VBox.setMargin(gameTypeGridPane,     new Insets(10, 10, 0, 10));
        VBox.setMargin(playersGridPane,      new Insets(10, 10, 0, 10));
        VBox.setMargin(analysisPlayerButton, new Insets(25, 25, 0, 25));

        setGameDataVBox();

    }

    private void resetGameDataVBox()
            throws Exception {

        resetGameTypeGridPane();

        switch (game.gameType) {
        case ANALYSIS:
            analysisPlayerButton.setVisible(true);
            playersGridPane.setVisible(false);
            resetAnalysisPlayerButton();
            break;
        case MATCH:
            analysisPlayerButton.setVisible(false);
            playersGridPane.setVisible(true);
            resetPlayersGridPane();
            break;
        default:
            throw new Exception("game.gameType=" + game.gameType);
        }

        setGameDataVBox();

    }

    private void setGameDataVBox()
            throws Exception {

        gameDataVBox.getChildren().clear();

        gameDataVBox.getChildren().add(gameTypeGridPane);

        if (analysisGameRadioButton.isSelected()) {
            gameDataVBox.getChildren().add(analysisPlayerButton);
        } else if (matchGameRadioButton.isSelected()) {
            gameDataVBox.getChildren().add(playersGridPane);
        } else {
            throw new Exception("match*/analysis* + *GameRadioButton");
        }

    }

    private void newGameTypeGridPane()
            throws Exception {

        ToggleGroup tg = new ToggleGroup();

        matchGameRadioButton.setToggleGroup(tg);
        matchGameRadioButton.setOnAction(ae -> {
            try {

                setPlayersPaneOn();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        analysisGameRadioButton.setToggleGroup(tg);
        analysisGameRadioButton.setOnAction(ae -> {
            try {

                setPlayersPaneOff();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        switch (game.gameType) {
        case ANALYSIS: analysisGameRadioButton.setSelected(true); break;
        case MATCH:    matchGameRadioButton.setSelected(true);   break;
        default:
            throw new Exception("game.gameType=" + game.gameType);
        }

        gameTypeGridPane.setAlignment(Pos.TOP_CENTER);

        GridPane.setHalignment(gameTypeLabel, HPos.CENTER);
        GridPane.setMargin(matchGameRadioButton, new Insets(5));
        GridPane.setMargin(analysisGameRadioButton, new Insets(5));

        setGameTypeGridPane();

//        gameTypeGridPane.setGridLinesVisible(true);

    }

    private void resetGameTypeGridPane()
            throws Exception {

        switch (game.gameType) {
        case ANALYSIS: analysisGameRadioButton.setSelected(true); break;
        case MATCH:    matchGameRadioButton.setSelected(true);    break;
        default:
            throw new Exception("game.gameType=" + game.gameType);
        }

    }

    private void setGameTypeGridPane() {

        gameTypeGridPane.getChildren().clear();

        gameTypeGridPane.add(gameTypeLabel,           0, 0, 3, 1);
        gameTypeGridPane.add(matchGameRadioButton,    1, 1);
        gameTypeGridPane.add(analysisGameRadioButton, 2, 1);

    }

    private void newPlayersGridPane() {

        playersGridPane.setHgap(5);

        newWhiteKindsComboBox();
        newWhiteEngineDefButton();
        newBlackKindsComboBox();
        newBlackEngineDefButton();
        newTimeControls();

        GridPane.setMargin(timeControlCheckBox, new Insets(30));

        playersGridPane.setAlignment(Pos.TOP_CENTER);

        GridPane.setHalignment(timeControlCheckBox, HPos.CENTER);
        GridPane.setHalignment(whiteLeftTimeLabel,  HPos.RIGHT);
        GridPane.setHalignment(blackLeftTimeLabel,  HPos.RIGHT);
        GridPane.setHalignment(whiteTimeIncrLabel,  HPos.RIGHT);
        GridPane.setHalignment(blackTimeIncrLabel,  HPos.RIGHT);

        setGamePlayersGridPane();

//        playersGridPane.setGridLinesVisible(true);

    }

    private void resetPlayersGridPane()
            throws Exception {

        resetWhiteKindsComboBox();
        resetWhiteEngineDefButton();
        resetBlackKindsComboBox();
        resetBlackEngineDefButton();
        resetTimeControls();

    }

    private void setGamePlayersGridPane() {

        playersGridPane.getChildren().clear();

        playersGridPane.add(playersWhiteLabel,           0, 0);
        playersGridPane.add(playersBlackLabel,           2, 0);

        playersGridPane.add(whiteKindsComboBox,          0, 1);
        playersGridPane.add(whiteEngineDefButton,        1, 1);
        playersGridPane.add(blackKindsComboBox,          2, 1);
        playersGridPane.add(blackEngineDefButton,        3, 1);

        playersGridPane.add(timeControlCheckBox,         0, 2, 4, 1);

        playersGridPane.add(whiteLeftTimeLabel,          0, 3);
        playersGridPane.add(whiteLeftTimeMinutesSpinner, 1, 3);
        playersGridPane.add(blackLeftTimeLabel,          2, 3);
        playersGridPane.add(blackLeftTimeMinutesSpinner, 3, 3);

        playersGridPane.add(whiteTimeIncrLabel,          0, 4);
        playersGridPane.add(whiteTimeIncrSecondsSpinner, 1, 4);
        playersGridPane.add(blackTimeIncrLabel,          2, 4);
        playersGridPane.add(blackTimeIncrSecondsSpinner, 3, 4);

    }

    private void setPlayersPaneOn()
            throws Exception {

        playersGridPane.setVisible(true);
        analysisPlayerButton.setVisible(false);

        setGameDataVBox();

        setTimeControlsOn();

    }

    private void setPlayersPaneOff()
            throws Exception {

        playersGridPane.setVisible(false);
        analysisPlayerButton.setVisible(true);

        setGameDataVBox();

        whiteKindsComboBox.getSelectionModel().select(1); // HUMAN
        blackKindsComboBox.getSelectionModel().select(1); // HUMAN
        timeControlCheckBox.setSelected(false);

        setTimeControlsOff();

    }

    private void newWhiteKindsComboBox() {

    	whiteKindsComboBox.getItems().clear();

    	whiteKindsComboBox.getItems().addAll(KINDS_STRING_LIST);

    	whiteKindsComboBox.getSelectionModel().select(1); // HUMAN

        whiteKindsComboBox.setOnAction(ae -> {
            try {

                resetWhiteEngineDefButton();

                if (whiteKindsComboBox.getSelectionModel().getSelectedIndex() == 0) { // ENGINE
                    setTimeControlsOn();
                }

                setParms();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void resetWhiteKindsComboBox() {

        if (game.whitePlayer.kind == Kinds.HUMAN) {
            whiteKindsComboBox.getSelectionModel().select(1); // HUMAN
        } else {
            whiteKindsComboBox.getSelectionModel().select(0); // ENGINE
        }

    }

    private void newWhiteEngineDefButton() {

        whiteEngineDefButton.setText("engine: " + game.whitePlayer.engine.name);
        whiteEngineDefButton.setPrefWidth(150);

        whiteEngineDefButton.setOnAction(ae -> {
            try {

                engineStage = new EngineStage(this, game.whitePlayer.engine);
                engineStage.showAndWait();

                game.whitePlayer.engine = engineStage.getEngine();

                resetWhiteEngineDefButton();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void resetWhiteEngineDefButton()
            throws Exception {

        whiteEngineDefButton.setText("engine: " + game.whitePlayer.engine.name);

    }

    private void newBlackKindsComboBox() {

    	blackKindsComboBox.getItems().clear();

        blackKindsComboBox.getItems().addAll(KINDS_STRING_LIST);

        blackKindsComboBox.getSelectionModel().select(1); // HUMAN

        blackKindsComboBox.setOnAction(ae -> {
            try {

                resetBlackEngineDefButton();

                if (blackKindsComboBox.getSelectionModel().getSelectedIndex() == 0) { // ENGINE
                    setTimeControlsOn();
                }

                setParms();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void resetBlackKindsComboBox() {

        if (game.blackPlayer.kind == Kinds.HUMAN) {
            blackKindsComboBox.getSelectionModel().select(1); // HUMAN
        } else {
            blackKindsComboBox.getSelectionModel().select(0); // ENGINE
        }

    }

    private void newBlackEngineDefButton() {

        blackEngineDefButton.setText("engine: " + game.blackPlayer.engine.name);
        blackEngineDefButton.setPrefWidth(150);

        blackEngineDefButton.setOnAction(ae -> {
            try {

                engineStage = new EngineStage(this, game.blackPlayer.engine);
                engineStage.showAndWait();

                game.blackPlayer.engine = engineStage.getEngine();

                resetBlackEngineDefButton();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void resetBlackEngineDefButton()
            throws Exception {

        blackEngineDefButton.setText("engine: " + game.blackPlayer.engine.name);

    }

    private void newTimeControls() {

        timeControlCheckBox.setSelected(true);

        timeControlCheckBox.setOnAction(ae -> {

            game.timeControlSet = timeControlCheckBox.isSelected();

            resetTimeControls();

        });

    }

    private void resetTimeControls() {

        if (game.timeControlSet) {
            setTimeControlsOn();
        } else {
            setTimeControlsOff();
        }

    }

    private void setTimeControlsOn() {

        timeControlCheckBox.setSelected(game.timeControlSet);

        whiteLeftTimeLabel.setDisable(!game.timeControlSet);

        whiteLeftTimeMinutesSpinner.getValueFactory().setValue((int) (game.whiteLeftTimeMillis / 60000.));
        whiteLeftTimeMinutesSpinner.setDisable(!game.timeControlSet);
        whiteLeftTimeMinutesSpinner.setEditable(true);

        blackLeftTimeLabel.setDisable(!game.timeControlSet);

        blackLeftTimeMinutesSpinner.getValueFactory().setValue((int) (game.blackLeftTimeMillis / 60000.));
        blackLeftTimeMinutesSpinner.setDisable(!game.timeControlSet);
        blackLeftTimeMinutesSpinner.setEditable(true);

        whiteTimeIncrLabel.setDisable(!game.timeControlSet);

        whiteTimeIncrSecondsSpinner.getValueFactory().setValue((int) (game.whiteTimeIncrementMillis / 1000.));
        whiteTimeIncrSecondsSpinner.setDisable(!game.timeControlSet);
        whiteTimeIncrSecondsSpinner.setEditable(true);

        blackTimeIncrLabel.setDisable(!game.timeControlSet);

        blackTimeIncrSecondsSpinner.getValueFactory().setValue((int) (game.blackTimeIncrementMillis / 1000.));
        blackTimeIncrSecondsSpinner.setDisable(!game.timeControlSet);
        blackTimeIncrSecondsSpinner.setEditable(true);

    }

    private void newAnalysisPlayerButton() {

        analysisPlayerButton.setText("engine: " + game.analysisPlayer.engine.name);
        analysisPlayerButton.setPrefWidth(150);

        analysisPlayerButton.setOnAction(ae -> {
            try {

                engineStage = new EngineStage(this, game.analysisPlayer.engine);
                engineStage.show();

                game.whitePlayer.engine = engineStage.getEngine();
                game.blackPlayer.engine = engineStage.getEngine();

                game.analysisPlayer.engine = engineStage.getEngine();

                resetAnalysisPlayerButton();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void resetAnalysisPlayerButton() {

        analysisPlayerButton.setText(game.analysisPlayer.engine.name);

    }

    private void setTimeControlsOff() {

        whiteLeftTimeLabel.setDisable(true);
        whiteLeftTimeMinutesSpinner.setDisable(true);

        blackLeftTimeLabel.setDisable(true);
        blackLeftTimeMinutesSpinner.setDisable(true);

        whiteTimeIncrLabel.setDisable(true);
        whiteTimeIncrSecondsSpinner.setDisable(true);

        blackTimeIncrLabel.setDisable(true);
        blackTimeIncrSecondsSpinner.setDisable(true);

    }

    private void newNodePane()
            throws Exception {

        nodePane = new NodePane(this, game.startNode, /*move*/null,
                /*showAvailables*/true, /*showNodeDetails*/true, /*showNodeSetPane*/true,
                BOARD_TILE_EDGE);

        addEventFilter(KeyEvent.KEY_PRESSED, nodePane.getKeyEventHandler());

    }

    private void resetNodePane()
            throws Exception {

        removeEventFilter(KeyEvent.KEY_PRESSED, nodePane.getKeyEventHandler());

        nodePane.resetNodePane(game.startNode, /*move*/null);

        addEventFilter(KeyEvent.KEY_PRESSED, nodePane.getKeyEventHandler());

    }

}
