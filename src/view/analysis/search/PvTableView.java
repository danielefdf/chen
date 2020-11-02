package view.analysis.search;

import java.util.ArrayList;
import java.util.LinkedList;
import engine.model.Move;
import engine.model.PvRecord;
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
public class PvTableView extends TableView<PvRecord> {

    public static final int MOVE_COLUMNS_NUMBER  = 20;
    public static final int ROOT_NODE_COLUMN_IX  = 4; // 0 based
    public static final int FIRST_MOVE_COLUMN_IX = 5; // 0 based

    public static final int VISIBLE_ROWS_NUMBER    = 4;
    public static final int VISIBLE_COLUMNS_NUMBER = 10;

    private LinkedList<PvRecord> pvRecordsList;
    private boolean allowedToGame;
    private SearchAim searchAim;

    private int row;
    private int col;

    private TableColumn<PvRecord, Integer> depthColumn;
    private TableColumn<PvRecord, Integer> valueColumn;
    private TableColumn<PvRecord, Long> timeLengthColumn;
    private TableColumn<PvRecord, Integer> visitedNodesColumn;
    private LinkedList<TableColumn<PvRecord, Move>> moveColumnsList;
    private ObservableList<PvRecord> pvRecordsObservableList;

    private ArrayList<PvTableViewListener> pvTableViewListenersList;

    public void addPvTableViewListener(PvTableViewListener newPvTableViewListener) {
        pvTableViewListenersList.add(newPvTableViewListener);
    }

    public PvTableView(LinkedList<PvRecord> newPvRecordsList, boolean newAllowedToGame, SearchAim newSearchAim)
    		throws Exception {

        pvRecordsList = newPvRecordsList;
        allowedToGame = newAllowedToGame;
        searchAim = newSearchAim;

        moveColumnsList = new LinkedList<>();

        pvTableViewListenersList = new ArrayList<>();

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

        setItems(pvRecordsObservableList);

        setGameTableView();

        addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            try {

                if (ke.isControlDown()
                            && ke.getCode() == KeyCode.PAGE_DOWN) {
                    for (PvTableViewListener l : pvTableViewListenersList) {
                        if (l != null) {
                            l.onControlPageDownPressed();
                        }
                    }
                    ke.consume();
                } else if (ke.isControlDown()
                            && ke.getCode() == KeyCode.PAGE_UP) {
                    for (PvTableViewListener l : pvTableViewListenersList) {
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
                    resetToVariation(/*scrollReset*/false);
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
                    resetToVariation(/*scrollReset*/true);
                } else if (ke.getCode() == KeyCode.ENTER) {
                    if (ke.isShiftDown()) {
                        if (searchAim == SearchAim.ANALYSIS) {
                            for (PvTableViewListener l : pvTableViewListenersList) {
                                if (l != null) {
                                    l.onVariationToEvaluate(getMovesList());
                                }
                            }
                        }
                    } else {
                        switch (searchAim) {
                        case HELP:
                            if (getSelectionModel().getSelectedCells().get(0).getColumn() == FIRST_MOVE_COLUMN_IX) {
                                for (PvTableViewListener l : pvTableViewListenersList) {
                                    if (l != null) {
                                        l.onVariationToPlay(getMovesList());
                                    }
                                }
                            }
                            break;
                        case ANALYSIS:
                            for (PvTableViewListener l : pvTableViewListenersList) {
                                if (l != null) {
                                    l.onVariationToPlay(getMovesList());
                                }
                            }
                            break;
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void resetToVariation(boolean scrollReset)
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

        LinkedList<Move> ll = new LinkedList<>();

        if (col > ROOT_NODE_COLUMN_IX) {

            int i = FIRST_MOVE_COLUMN_IX;

            Move m;

            do {
                m = (Move) getColumns().get(i).getCellData(row);
                if (m != null) {
                    ll.add(m);
                }
                ++i;
            } while (i <= col && m != null);

        }

        for (PvTableViewListener l : pvTableViewListenersList) {
            if (l != null) {
                l.onVariationSelected(ll);
            }
        }

    }

    private LinkedList<Move> getMovesList() {

        row = getSelectionModel().getSelectedCells().get(0).getRow();
        col = getSelectionModel().getSelectedCells().get(0).getColumn();

        LinkedList<Move> ll = new LinkedList<>();

        int i = FIRST_MOVE_COLUMN_IX;

        Move m;

        do {
            m = (Move) getColumns().get(i).getCellData(row);
            if (m != null) {
                ll.add(m);
            }
            ++i;
        } while (i <= col && m != null);

        return ll;
    }

    public void resetPvTableView(LinkedList<PvRecord> newPvRecordsList, boolean newAllowedToGame)
    		throws Exception {

        pvRecordsList = newPvRecordsList;
        allowedToGame = newAllowedToGame;

        resetGameTableRecordsObservableList();

        setGameTableView();

    }

    private void newGameTableColumns() {

        TableColumn<PvRecord, Move> moveColumn;

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
                    new PropertyValueFactory<>("pvMove" + i));
            moveColumn.setResizable(false);
            moveColumn.setReorderable(false);
            moveColumn.setSortable(false);

            moveColumnsList.add(moveColumn);

        }

    }

    private void setGameTableView()
    		throws Exception {

        setItems(pvRecordsObservableList);

        switch (searchAim) {
        case ANALYSIS:
            if (allowedToGame) {
                setGameTableViewAllowedToGame();
            } else {
                setGameTableViewNotAllowedToGame(/*fromIndex*/1);
            }
            break;
        case HELP:
            setGameTableViewNotAllowedToGame(/*fromIndex*/2);
            break;
        default:
            break;
        }

    }

    private void setGameTableViewNotAllowedToGame(int fromIndex) {

        for (int i = fromIndex; i <= MOVE_COLUMNS_NUMBER - 1; ++i) {
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

        pvRecordsObservableList = FXCollections.observableArrayList(pvRecordsList);

    }

}
