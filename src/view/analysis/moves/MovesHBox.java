package view.analysis.moves;

import java.util.LinkedList;
import engine.model.Move;
import engine.model.MoveRecord;
import engine.model.Node;
import engine.view.evaluation.EMoveOrderingTabPane;
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
public class MovesHBox extends HBox {

    private static final int BOARD_TILE_EDGE = 30;
    private static final int MOVE_TABLEVIEW_PREF_WIDTH = 700;

    private LinkedList<MoveRecord> moveRecordsList;

    private Stage ownerStage;
    private Node node;
    private Move move;
    private boolean allowedToGame;

    private MovesTableView moveTableView;
    private EMoveOrderingTabPane eMoveOrderingTabPane;

	public MovesTableView getMovesTableView() {
		return moveTableView;
	}

    public MovesHBox(Stage newOwnerStage, Node newNode, Move newMove, boolean newAllowedToGame)
            throws Exception {

        ownerStage = newOwnerStage;

        node = new Node(newNode);
        move = newMove;

        allowedToGame = newAllowedToGame;

        moveRecordsList = new LinkedList<>();

        newMoveTableView();
        newEMoveOrderingTabPane();

        setMovesHBox();

    }

    private void setMovesHBox() {

        getChildren().clear();

        getChildren().add(moveTableView);
        getChildren().add(eMoveOrderingTabPane);

        HBox.setHgrow(moveTableView, Priority.ALWAYS);

    }

    public void resetMovesHBox(Node newNode, Move newMove, boolean newAllowedToGame)
            throws Exception {

        node = new Node(newNode);
        move = newMove;

        allowedToGame = newAllowedToGame;

        moveRecordsList.clear();

        moveTableView.resetMovesTableView(moveRecordsList, allowedToGame);
        eMoveOrderingTabPane.reset(node, move, allowedToGame);

    }

    private void newMoveTableView() throws Exception {

        moveTableView = new MovesTableView(moveRecordsList, allowedToGame);

        moveTableView.setPrefWidth(MOVE_TABLEVIEW_PREF_WIDTH);

        moveTableView.addMovesTableViewListener(new MovesTableViewListener() {

            @Override
            public void onMoveSelected(Move move)
                    throws Exception {

                Node newNode = new Node(node);

                if (move != null) {
                    newNode = new Node(newNode, move);
                }

                eMoveOrderingTabPane.reset(newNode, move, allowedToGame);

            }

            @Override
            public void onMoveToPlay(Move move) {} // not handled here

            @Override
            public void onMoveToEvaluate(Move move) {} // not handled here

            @Override
            public void onControlPageDownPressed() {} // not handled here

            @Override
            public void onControlPageUpPressed() {} // not handled here

        });

    }

    private void newEMoveOrderingTabPane()
            throws Exception {

        eMoveOrderingTabPane = new EMoveOrderingTabPane(ownerStage, node, move, true);

    }

    @SuppressWarnings("unchecked")
    public void setCurrentMoveRecord(MoveRecord currentMoveRecord)
            throws Exception {

        boolean resetSelection = false;

        int selectedRow = 0;

        TableColumn<MoveRecord, ?> selectedTableColumn = null;

        if (moveTableView.getSelectionModel().getSelectedCells().size() > 0) {
            selectedRow = moveTableView.getSelectionModel().getSelectedCells().get(0).getRow();
            selectedTableColumn = moveTableView.getSelectionModel().getSelectedCells().get(0).getTableColumn();
            resetSelection = true;
        }

        LinkedList<MoveRecord> newMoveRecordsList = new LinkedList<>();

        newMoveRecordsList.add(0, currentMoveRecord);

        boolean currentMoveRecordFound = false;

        Integer currentMoveIndex = null;

        for (int i = 0; i <= moveRecordsList.size() - 1; ++i) {
            if (moveRecordsList.get(i).equals(currentMoveRecord)) {
                currentMoveRecordFound = true;
                currentMoveIndex = i;
            } else {
                newMoveRecordsList.add(moveRecordsList.get(i));
            }
        }

        moveRecordsList = newMoveRecordsList;

        moveTableView.resetMovesTableView(moveRecordsList, allowedToGame);

        if (resetSelection) {
            if (currentMoveRecordFound) {
                if (currentMoveIndex.equals(selectedRow)) {
                    selectedRow = 0;
                }
            } else {
                ++selectedRow;
            }
        }

        moveTableView.getSelectionModel().clearAndSelect(selectedRow, selectedTableColumn);

    }

}
