package view.game;

import java.util.LinkedList;
import engine.model.Move;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.elements.Colors;
import model.game.Game;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class GameVBox extends VBox {

    public static final int FULLMOVE_HEADER_WIDTH = 80;
    public static final int MOVE_HEADER_WIDTH     = 270;
    public static final int CLOCK_HEADER_WIDTH    = 100; // la somma deve dare MOVE_HEADER_WIDTH
    public static final int ENGINE_HEADER_WIDTH   = 170; // la somma deve dare MOVE_HEADER_WIDTH

    public static final int HEADER_HEIGHT = 40;

    public static final int VISIBLE_ROWS_NUMBER = 9;

    private Game game;

    private int row;
    private int col;

    private LinkedList<GameRecord> fullmovesList = new LinkedList<>();

    private HBox playersHBox;
        private Label fillerLabel;
        private Label whiteClockLabel;
        private Label whitePlayerLabel;
        private Label blackClockLabel;
        private Label blackPlayerLabel;
    private TableView<GameRecord> gameTableView;
        private TableColumn<GameRecord, Integer> fullmoveColumn;
        private TableColumn<GameRecord, GameStep> whiteMoveColumn;
        private TableColumn<GameRecord, GameStep> blackMoveColumn;
        private ObservableList<GameRecord> gameRecordsObservableList;

    public TableView<GameRecord> getGameTableView() {
        return gameTableView;
    }

    private GamePaneListener gamePaneListener;

    public GameVBox(Game newGame)
            throws Exception {

        game = newGame;

        newPlayersHBox();
        newGameTableView();

        setGamePane();

    }

    public void resetGamePane(Game newGame)
            throws Exception {

        game = newGame;

        resetPlayersHBox();
        resetGameTableView();

    }

    private void setGamePane()
            throws Exception {

        getChildren().clear();

        getChildren().add(playersHBox);
        getChildren().add(gameTableView);

        VBox.setVgrow(gameTableView, Priority.ALWAYS);

    }

    private void newPlayersHBox()
            throws Exception {

        newFillerLabel();

        newWhiteClockLabel();
        newBlackClockLabel();

        newWhitePlayerLabel();
        newBlackPlayerLabel();

        playersHBox = new HBox();

        playersHBox.getStyleClass().add("GameVBox-playersHBox");

        setPlayersHBox();

    }

    private void resetPlayersHBox()
            throws Exception {

        resetWhiteClockLabel();
        resetWhitePlayerLabel();
        resetBlackClockLabel();
        resetBlackPlayerLabel();

    }

    private void setPlayersHBox()
            throws Exception {

        playersHBox.getChildren().clear();

        playersHBox.getChildren().add(fillerLabel);
        playersHBox.getChildren().add(whiteClockLabel);
        playersHBox.getChildren().add(whitePlayerLabel);
        playersHBox.getChildren().add(blackClockLabel);
        playersHBox.getChildren().add(blackPlayerLabel);

    }

    private void newFillerLabel()
            throws Exception {

        fillerLabel = new Label();

        fillerLabel.setPrefSize(FULLMOVE_HEADER_WIDTH, HEADER_HEIGHT);

    }

    private void newWhiteClockLabel()
            throws Exception {

        whiteClockLabel = new Label();

        whiteClockLabel.setAlignment(Pos.CENTER);
        whiteClockLabel.setPrefSize(CLOCK_HEADER_WIDTH, HEADER_HEIGHT);

        setWhiteClockLabel();

    }

    public void resetWhiteClockLabel()
            throws Exception {

        setWhiteClockLabel();

    }

    private void setWhiteClockLabel()
            throws Exception {

        if (game.timeControlSet) {
            whiteClockLabel.setText(ViewUtils.hhmmssFormat(game.whiteLeftTimeMillis));
            whiteClockLabel.getStyleClass().clear();
            whiteClockLabel.getStyleClass().add("GameVBox-whiteClockLabel-on");
        } else {
            whiteClockLabel.setText(ViewUtils.hhmmssFormat(0));
            whiteClockLabel.getStyleClass().clear();
            whiteClockLabel.getStyleClass().add("GameVBox-whiteClockLabel-off");
        }

    }

    private void newWhitePlayerLabel()
            throws Exception {

        whitePlayerLabel = new Label();

        whitePlayerLabel.setAlignment(Pos.CENTER);
        whitePlayerLabel.setPrefSize(ENGINE_HEADER_WIDTH, HEADER_HEIGHT);
        whitePlayerLabel.getStyleClass().add("GameVBox-whiteEngineLabel");

        setWhitePlayerLabel();

    }

    private void resetWhitePlayerLabel()
            throws Exception {

        setWhitePlayerLabel();

    }

    private void setWhitePlayerLabel()
            throws Exception {

        whitePlayerLabel.setText(game.whitePlayer.toString());

    }

    public void resetBlackClockLabel()
            throws Exception {

        setBlackClockLabel();

    }

    private void newBlackClockLabel()
            throws Exception {

        blackClockLabel = new Label();

        blackClockLabel.setAlignment(Pos.CENTER);
        blackClockLabel.setPrefSize(CLOCK_HEADER_WIDTH, HEADER_HEIGHT);

        setBlackClockLabel();

    }

    private void setBlackClockLabel()
            throws Exception {

        if (game.timeControlSet) {
            blackClockLabel.setText(ViewUtils.hhmmssFormat(game.blackLeftTimeMillis));
            blackClockLabel.getStyleClass().clear();
            blackClockLabel.getStyleClass().add("GameVBox-blackClockLabel-on");
        } else {
            blackClockLabel.setText(ViewUtils.hhmmssFormat(0));
            blackClockLabel.getStyleClass().clear();
            blackClockLabel.getStyleClass().add("GameVBox-blackClockLabel-off");
        }

    }

    public void newBlackPlayerLabel()
            throws Exception {

        blackPlayerLabel = new Label();

        blackPlayerLabel.setAlignment(Pos.CENTER);
        blackPlayerLabel.setPrefSize(ENGINE_HEADER_WIDTH, HEADER_HEIGHT);
        blackPlayerLabel.getStyleClass().add("GameVBox-blackEngineLabel");

        setBlackPlayerLabel();

    }

    private void resetBlackPlayerLabel()
            throws Exception {

        setBlackPlayerLabel();

    }

    private void setBlackPlayerLabel()
            throws Exception {

        blackPlayerLabel.setText(game.blackPlayer.toString());

    }

    private void newGameTableView()
            throws Exception {

        newGameTableRecordsObservableList();
        newGameTableColumns();

        gameTableView = new TableView<>();

        gameTableView.setPlaceholder(new Label());

        gameTableView.getStyleClass().add("GameVBox-gameTableView");

        gameTableView.getColumns().add(fullmoveColumn);
        gameTableView.getColumns().add(whiteMoveColumn);
        gameTableView.getColumns().add(blackMoveColumn);
        gameTableView.setItems(gameRecordsObservableList);
        gameTableView.getSelectionModel().setCellSelectionEnabled(true);

        setGameTableView();

        gameTableView.setOnMousePressed(new EventHandler<>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.isPrimaryButtonDown()) {
                    if (gameTableView.getSelectionModel().getSelectedCells().size() != 0) {
                        row = gameTableView.getSelectionModel().getSelectedCells().get(0).getRow();
                        col = gameTableView.getSelectionModel().getSelectedCells().get(0).getColumn();
                    }
                    if (gamePaneListener != null) {
                        try {
                            gamePaneListener.onMousePressed(e);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });

        gameTableView.setOnKeyPressed(new EventHandler<>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.LEFT
                        || e.getCode() == KeyCode.RIGHT
                        || e.getCode() == KeyCode.DOWN
                        || e.getCode() == KeyCode.UP) {
                    switch (e.getCode()) {
                    case LEFT:
                        if (row > 0) {
                            switch (col) {
                            case 0:
                            case 1: --row; col = 2; break;
                            case 2:        col = 1; break;
                            }
                        } else {
                            switch (col) {
                            case 0:                 break;
                            case 1:        col = 0; break;
                            case 2:        col = 1; break;
                            }
                        }
                        break;
                    case RIGHT:
                        if (row < gameTableView.getItems().size()) {
                            switch (col) {
                            case 0:        col = 1; break;
                            case 1:        col = 2; break;
                            case 2: ++row; col = 1; break;
                            }
                        }
                        break;
                    case UP:
                        if (row > 0) {
                            switch (col) {
                            case 0:                 break;
                            case 1:
                            case 2: --row;          break;
                            }
                        }
                        break;
                    case DOWN:
                        if (row < gameTableView.getItems().size()) {
                            switch (col) {
                            case 0:                 break;
                            case 1:
                            case 2: ++row;          break;
                            }
                        }
                        break;
                    default:
                        break;
                    }
                    int r = row;
                    TableColumn<GameRecord, ?> tc = gameTableView.getColumns().get(col);
                    gameTableView.getSelectionModel().select(r, tc);
                    if (r <= VISIBLE_ROWS_NUMBER - 1) {
                        gameTableView.scrollTo(0);
                    } else {
                        gameTableView.scrollTo(r - VISIBLE_ROWS_NUMBER + 1);
                    }
                    if (gamePaneListener != null) {
                        try {
                            gamePaneListener.onKeyPressed(e);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });

    }

    private void newGameTableColumns() {

        fullmoveColumn = new TableColumn<>("#");
        fullmoveColumn.setMinWidth(FULLMOVE_HEADER_WIDTH);
        fullmoveColumn.setCellValueFactory(
                new PropertyValueFactory<>("fullmoveNumber"));
        fullmoveColumn.setResizable(false);
        fullmoveColumn.setReorderable(false);
        fullmoveColumn.setSortable(false);

        whiteMoveColumn = new TableColumn<>("white");
        whiteMoveColumn.setMinWidth(MOVE_HEADER_WIDTH);
        whiteMoveColumn.setCellValueFactory(
                new PropertyValueFactory<>("whiteGameStep"));
        whiteMoveColumn.setResizable(false);
        whiteMoveColumn.setReorderable(false);
        whiteMoveColumn.setSortable(false);

        blackMoveColumn = new TableColumn<>("black");
        blackMoveColumn.setMinWidth(MOVE_HEADER_WIDTH);
        blackMoveColumn.setCellValueFactory(
                new PropertyValueFactory<>("blackGameStep"));
        blackMoveColumn.setResizable(false);
        blackMoveColumn.setReorderable(false);
        blackMoveColumn.setSortable(false);

    }

    private void resetGameTableView()
            throws Exception {

        resetGameTableRecordsObservableList();

        setGameTableView();

    }

    private void setGameTableView()
            throws Exception {

        gameTableView.setItems(gameRecordsObservableList);

        int r;

        TableColumn<GameRecord, ?> tc;

        if (gameTableView.getSelectionModel().getSelectedCells().size() != 0) {
            row = gameTableView.getSelectionModel().getSelectedCells().get(0).getRow();
            col = gameTableView.getSelectionModel().getSelectedCells().get(0).getColumn();
            r = row;
            tc = gameTableView.getColumns().get(col);
        } else {
            r = gameRecordsObservableList.size() - 1;
            tc = gameTableView.getColumns().get(0);
        }

        gameTableView.getSelectionModel().select(r, tc);

        if (r <= VISIBLE_ROWS_NUMBER - 1) {
            gameTableView.scrollTo(0);
        } else {
            gameTableView.scrollTo(r - VISIBLE_ROWS_NUMBER + 1);
        }

    }

    private void newGameTableRecordsObservableList()
            throws Exception {

        newGameTableRecordsList();

        setGameTableRecordsObservableList();

    }

    private void resetGameTableRecordsObservableList()
            throws Exception {

        resetGameTableRecordsList();

        setGameTableRecordsObservableList();

    }

    private void setGameTableRecordsObservableList()
            throws Exception {

        gameRecordsObservableList = FXCollections.observableArrayList(fullmovesList);

    }

    private void newGameTableRecordsList()
            throws Exception {

        setGameTableRecordsList();

    }

    private void resetGameTableRecordsList()
            throws Exception {

        setGameTableRecordsList();

    }

    private void setGameTableRecordsList()
            throws Exception {

        fullmovesList.clear();

        int playerColor;

        int fullmoveNumber = 0;

        Move move;
        Long srchLen;
        Integer value;

        GameStep whiteGameStep, blackGameStep;

        if (game.movesList.size() > 0) {
            playerColor = game.startNode.playerColor;
            whiteGameStep = null;
            for (int i = 0; i <= game.movesList.size() - 1; ++i) {
                move    = game.movesList.get(i);
                srchLen = game.srchLensList.get(i);
                value   = game.valuesList.get(i);
                switch (playerColor) {
                case Colors.WHITE:
                    whiteGameStep = new GameStep(move, srchLen, value);
                    break;
                case Colors.BLACK:
                    ++fullmoveNumber;
                    blackGameStep = new GameStep(move, srchLen, value);
                    fullmovesList.add(new GameRecord(fullmoveNumber, whiteGameStep, blackGameStep));
                    whiteGameStep = null;
                    break;
                default:
                    throw new Exception("playerColor=" + playerColor);
                }
                playerColor = -playerColor;
            }
            if (whiteGameStep != null) {
                ++fullmoveNumber;
                fullmovesList.add(new GameRecord(fullmoveNumber, whiteGameStep, null));
            }
        }

        int visibleRows;

        if (fullmovesList.size() == 0) {
            visibleRows = 1;
        } else {
            visibleRows = fullmovesList.size();
        }

        for (int i = fullmoveNumber + 1; i <= visibleRows; ++i) {
            fullmoveNumber = 0;
            fullmovesList.add(new GameRecord(fullmoveNumber, null, null));
        }

    }

    public void resetWhiteClockValue() {

        whiteClockLabel.setText(ViewUtils.hhmmssFormat(game.whiteLeftTimeMillis));

    }

    public void resetBlackClockValue() {

        blackClockLabel.setText(ViewUtils.hhmmssFormat(game.blackLeftTimeMillis));

    }

    public void setGamePaneListener(GamePaneListener newGamePaneListener) {

        gamePaneListener = newGamePaneListener;

    }

}
