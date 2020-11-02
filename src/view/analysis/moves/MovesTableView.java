package view.analysis.moves;

import java.util.ArrayList;
import java.util.LinkedList;
import engine.model.Move;
import engine.model.MoveRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class MovesTableView extends TableView<MoveRecord> {

    public static final int MOVE_COLUMNS_NUMBER  = 20;
    public static final int ROOT_NODE_COLUMN_IX  = 4; // 0 based
    public static final int FIRST_MOVE_COLUMN_IX = 5; // 0 based

    public static final int VISIBLE_ROWS_NUMBER    = 4;
    public static final int VISIBLE_COLUMNS_NUMBER = 10;

    private LinkedList<MoveRecord> moveRecordsList;
    private boolean allowedToGame;

    private int row;
    private int col;

    private TableColumn<MoveRecord, Integer> depthColumn;
    private TableColumn<MoveRecord, Integer> valueColumn;
    private TableColumn<MoveRecord, Long> timeLengthColumn;
    private TableColumn<MoveRecord, Integer> visitedNodesColumn;
    private LinkedList<TableColumn<MoveRecord, Move>> moveColumnsList;
    private ObservableList<MoveRecord> moveRecordsObservableList;

    private ArrayList<MovesTableViewListener> movesTableViewListenersList;

    public void addMovesTableViewListener(MovesTableViewListener newMovesTableViewListener) {
        movesTableViewListenersList.add(newMovesTableViewListener);
    }

    public MovesTableView(LinkedList<MoveRecord> newMoveRecordsList, boolean newAllowedToGame)
    		throws Exception {

        moveRecordsList = newMoveRecordsList;
        allowedToGame = newAllowedToGame;

        moveColumnsList = new LinkedList<>();

        movesTableViewListenersList = new ArrayList<>();

        newGameTableRecordsObservableList();
        newGameTableColumns();

        getSelectionModel().setCellSelectionEnabled(true);

        setPlaceholder(new Label(""));

        getColumns().add(depthColumn);
        getColumns().add(valueColumn);
        getColumns().add(timeLengthColumn);
        getColumns().add(visitedNodesColumn);
        for (int i = 0; i <= MOVE_COLUMNS_NUMBER - 1; ++i) {
            getColumns().add(moveColumnsList.get(i));
        }

        setItems(moveRecordsObservableList);

        setGameTableView();

        addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            try {

                if (ke.isControlDown()
                            && ke.getCode() == KeyCode.PAGE_DOWN) {
                    for (MovesTableViewListener l : movesTableViewListenersList) {
                        if (l != null) {
                            l.onControlPageDownPressed();
                        }
                    }
                    ke.consume();
                } else if (ke.isControlDown()
                            && ke.getCode() == KeyCode.PAGE_UP) {
                    for (MovesTableViewListener l : movesTableViewListenersList) {
                        if (l != null) {
                            l.onControlPageUpPressed();
                        }
                    }
                    ke.consume();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        setOnMousePressed(me -> {
            try {

                if (me.isPrimaryButtonDown()) {
                    resetToMove(/*scrollReset*/false);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        setOnKeyPressed(ke -> {
            try {

                if (ke.getCode() == KeyCode.LEFT
                        || ke.getCode() == KeyCode.RIGHT
                        || ke.getCode() == KeyCode.DOWN
                        || ke.getCode() == KeyCode.UP) {
                    resetToMove(/*scrollReset*/true);
                } else if (ke.getCode() == KeyCode.ENTER) {
                    if (ke.isShiftDown()) {
                        for (MovesTableViewListener l : movesTableViewListenersList) {
                            if (l != null) {
                                l.onMoveToEvaluate(getMove());
                            }
                        }
                    } else {
                        for (MovesTableViewListener l : movesTableViewListenersList) {
                            if (l != null) {
                                l.onMoveToPlay(getMove());
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void resetToMove(boolean scrollReset)
            throws Exception {

    	if (getSelectionModel().getSelectedCells().size() != 0) {
            row = getSelectionModel().getSelectedCells().get(0).getRow();
            col = getSelectionModel().getSelectedCells().get(0).getColumn();
    	}

        if (scrollReset) {
            if (row <= VISIBLE_ROWS_NUMBER - 1) {
                scrollTo(0);
            } else {
                scrollTo(row - VISIBLE_ROWS_NUMBER + 1);
            }
            if (col <= VISIBLE_COLUMNS_NUMBER - 1) {
                scrollToColumnIndex(0);
            } else {
                scrollToColumnIndex(col - VISIBLE_COLUMNS_NUMBER + 1);
            }
        }

        Move m = null;

        if (col > ROOT_NODE_COLUMN_IX) {
            m = (Move) getColumns().get(col).getCellData(row);
        }

        for (MovesTableViewListener l : movesTableViewListenersList) {
            if (l != null) {
                l.onMoveSelected(m);
            }
        }

    }

    private Move getMove() {

        row = getSelectionModel().getSelectedCells().get(0).getRow();
        col = getSelectionModel().getSelectedCells().get(0).getColumn();

        return (Move) getColumns().get(col).getCellData(row);
    }

    public void resetMovesTableView(LinkedList<MoveRecord> newMoveRecordsList, boolean newAllowedToGame)
    		throws Exception {

        moveRecordsList = newMoveRecordsList;
        allowedToGame = newAllowedToGame;

        resetGameTableRecordsObservableList();

        setGameTableView();

    }

    private void newGameTableColumns() {

        TableColumn<MoveRecord, Move> moveColumn;

        depthColumn = new TableColumn<>("d");
        depthColumn.setPrefWidth(20);
        depthColumn.setCellValueFactory(
                new PropertyValueFactory<>("depth"));
        depthColumn.setResizable(false);
        depthColumn.setReorderable(false);
        depthColumn.setSortable(false);

        valueColumn = new TableColumn<>("v");
        valueColumn.setPrefWidth(50);
        valueColumn.setCellValueFactory(
                new PropertyValueFactory<>("value"));
        valueColumn.setResizable(false);
        valueColumn.setReorderable(false);
        valueColumn.setSortable(false);

        timeLengthColumn = new TableColumn<>("l");
        timeLengthColumn.setPrefWidth(60);
        timeLengthColumn.setCellValueFactory(
                new PropertyValueFactory<>("timeLength"));
        timeLengthColumn.setResizable(false);
        timeLengthColumn.setReorderable(false);
        timeLengthColumn.setSortable(false);

        visitedNodesColumn = new TableColumn<>("#");
        visitedNodesColumn.setPrefWidth(20);
        visitedNodesColumn.setCellValueFactory(
                new PropertyValueFactory<>("visitedNodes"));
        visitedNodesColumn.setResizable(false);
        visitedNodesColumn.setReorderable(false);
        visitedNodesColumn.setSortable(false);

        for (int i = 0; i <= MOVE_COLUMNS_NUMBER - 1; ++i) {

            moveColumn = new TableColumn<>();
            moveColumn.setPrefWidth(70);
            moveColumn.setCellValueFactory(
                    new PropertyValueFactory<>("move" + i));
            moveColumn.setResizable(false);
            moveColumn.setReorderable(false);
            moveColumn.setSortable(false);

            moveColumnsList.add(moveColumn);

        }

    }

    private void setGameTableView()
    		throws Exception {

        setItems(moveRecordsObservableList);

        if (allowedToGame) {
            setGameTableViewAllowedToGame();
        } else {
            setGameTableViewNotAllowedToGame();
        }

    }

    private void setGameTableViewNotAllowedToGame() {

        for (int i = 1; i <= MOVE_COLUMNS_NUMBER - 1; ++i) {
            moveColumnsList.get(i).setCellFactory(tc -> new TableCell<>() {
                @Override
                protected void updateItem(Move item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null
                            || isEmpty()) {
                        setText(null);
                    } else {
                        setText(item.toString());
                    }
                    setStyle("-fx-text-fill: black;"
                            + "-fx-selection-bar: gray;"
                            + "-fx-selection-bar-non-focused: lightgray;");

                }
            });
        }

    }

    private void setGameTableViewAllowedToGame() {

        for (int i = 1; i <= MOVE_COLUMNS_NUMBER - 1; ++i) {
            moveColumnsList.get(i).setCellFactory(tc -> new TableCell<>() {
                @Override
                protected void updateItem(Move item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null
                            || isEmpty()) {
                        setText(null);
                    } else {
                        setText(item.toString());
                    }
                    setStyle(null);

                }
            });
        }

    }

    private void newGameTableRecordsObservableList()
            throws Exception {

        setGameTableRecordsObservableList();

    }

    private void resetGameTableRecordsObservableList()
            throws Exception {

        setGameTableRecordsObservableList();

    }

    private void setGameTableRecordsObservableList()
            throws Exception {

        moveRecordsObservableList = FXCollections.observableArrayList(moveRecordsList);

    }

}
