package view.analysis.search;

import java.util.LinkedList;
import engine.model.Move;
import engine.model.Node;
import engine.model.PvRecord;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import view.nodepane.NodePane;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class SearchHBox extends HBox {

    private static final int BOARD_TILE_EDGE = 30;
    private static final int PV_TABLEVIEW_PREF_WIDTH = 700;

    private PvRecord lastPvRecord;
    private LinkedList<PvRecord> pvRecordsList;

    private Stage ownerStage;
    private Node node;
    private Move move;
    private boolean allowedToGame;

    private SearchAim searchAim;

    private PvTableView pvTableView;
    private NodePane nodePane;

	public PvTableView getPvTableView() {
		return pvTableView;
	}

    public SearchHBox(Stage newOwnerStage, Node newNode, Move newMove, boolean newAllowedToGame, SearchAim newSearchAim)
            throws Exception {

        ownerStage = newOwnerStage;

        node = new Node(newNode);
        move = newMove;

        allowedToGame = newAllowedToGame;

        searchAim = newSearchAim;

        pvRecordsList = new LinkedList<>();

        newPvTableView();
        newNodePane();

        setSearchHBox();

    }

    private void setSearchHBox() {

        getChildren().clear();

        getChildren().add(pvTableView);
        getChildren().add(nodePane);

        HBox.setHgrow(pvTableView, Priority.ALWAYS);

    }

    public void resetSearchHBox(Node newNode, Move newMove, boolean newAllowedToGame)
            throws Exception {

        node = new Node(newNode);
        move = newMove;

        allowedToGame = newAllowedToGame;

        pvRecordsList.clear();

        pvTableView.resetPvTableView(pvRecordsList, allowedToGame);

        nodePane.resetNodePane(node, move);

    }

    private void newPvTableView() throws Exception {

        pvTableView = new PvTableView(pvRecordsList, allowedToGame, searchAim);

        pvTableView.setPrefWidth(PV_TABLEVIEW_PREF_WIDTH);

        pvTableView.addPvTableViewListener(new PvTableViewListener() {

            @Override
            public void onVariationSelected(LinkedList<Move> movesList)
                    throws Exception {

                Node newNode = new Node(node);

                Move newMove = null;

                for (int i = 0; i <= movesList.size() - 1; ++i) {
                    newMove = movesList.get(i);
                    newNode = new Node(newNode, newMove);
                }

                nodePane.resetNodePane(newNode, newMove);

            }

            @Override
            public void onVariationToPlay(LinkedList<Move> movesList) {} // not handled here

            @Override
            public void onVariationToEvaluate(LinkedList<Move> movesList) {} // not handled here

            @Override
            public void onControlPageDownPressed() {} // not handled here

            @Override
            public void onControlPageUpPressed() {} // not handled here

        });

    }

    private void newNodePane()
            throws Exception {

        nodePane = new NodePane(ownerStage, node, move,
                /*showAvailables*/true, /*showNodeDetails*/true, /*showNodeSetPane*/true,
                BOARD_TILE_EDGE);

        nodePane.disableControls();

    }

    @SuppressWarnings("unchecked")
    public void addPvRecord(PvRecord currentPvRecord)
            throws Exception {

        //noinspection PointlessNullCheck
        if (lastPvRecord == null
                || !currentPvRecord.equals(lastPvRecord)) {

            boolean resetSelection = false;

            int row = 0;

            TableColumn<PvRecord, ?> tableColumn = null;

            if (pvTableView.getSelectionModel().getSelectedCells().size() > 0) {
                row = pvTableView.getSelectionModel().getSelectedCells().get(0).getRow();
                tableColumn = pvTableView.getSelectionModel().getSelectedCells().get(0).getTableColumn();
                resetSelection = true;
            }

            pvRecordsList.add(0, currentPvRecord);

            pvTableView.resetPvTableView(pvRecordsList, allowedToGame);

            lastPvRecord = currentPvRecord;

            if (resetSelection) {
                ++row;
                pvTableView.getSelectionModel().clearAndSelect(row, tableColumn);
            }

        }

    }

}
