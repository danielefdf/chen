package engine.view.evaluation;

import application.app.MainUtils;
import engine.model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.elements.BitBoards;
import view.nodepane.NodePane;

import java.util.LinkedList;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class EMoveOrderingTabPane extends TabPane {

    private static final int BOARD_TILE_EDGE = 30;
    private static final int MOVE_TABLEVIEW_PREF_WIDTH = 700;

    private Stage ownerStage;
    private Engine engine;
    private Node node;
    private Move move;
    private boolean allowedToGame;

    private NodePane nodePane;
    private TextArea movesTextArea;

    private LinkedList<MoveRecord> moveRecordsList;

    public EMoveOrderingTabPane(Stage newOwnerStage, Node newNode, Move newMove, boolean newAllowedToGame)
                throws Exception {

        ownerStage = newOwnerStage;
        node = new Node(newNode);
        move = newMove;
        allowedToGame = newAllowedToGame;

        moveRecordsList = new LinkedList<>();

        newEMoveOrderingTabPane();

    }

    private void newEMoveOrderingTabPane()
            throws Exception {

        newNodePane();
        newMovesTextArea();

        setEMoveOrderingTabPane();

    }

    private void setEMoveOrderingTabPane() {

        getTabs().clear();

        Tab boardTab = new Tab("board", nodePane);
        boardTab.setClosable(false);

        Tab scoresTab = new Tab("scores", movesTextArea);
        scoresTab.setClosable(false);

        getTabs().add(0, boardTab);
        getTabs().add(1, scoresTab);

    }

    public void reset(Node newNode, Move newMove, boolean newAllowedToGame) throws Exception {

        node = new Node(newNode);
        move = newMove;

        allowedToGame = newAllowedToGame;

        resetNodePane();
        resetMovesTextArea();

    }

    private void newNodePane() throws Exception {

        nodePane = new NodePane(ownerStage, node, move,
                true, true, true, BOARD_TILE_EDGE);

        nodePane.disableControls();

    }

    private void resetNodePane() throws Exception {

        nodePane.resetNodePane(node, move);

    }

    private void newMovesTextArea() throws Exception {

        movesTextArea = new TextArea();

        if (move != null) {
            long fromSquareBb = BitBoards.ONE << move.fromSquare;
            long toSquareBb = BitBoards.ONE << move.toSquare;

            movesTextArea.setText(EngineShow.computeMoveScore(node.playerColor, move, fromSquareBb, toSquareBb));
        }

    }

    private void resetMovesTextArea() throws Exception {

        if (move != null) {
            long fromSquareBb = BitBoards.ONE << move.fromSquare;
            long toSquareBb = BitBoards.ONE << move.toSquare;

            movesTextArea.setText(EngineShow.computeMoveScore(node.playerColor, move, fromSquareBb, toSquareBb));
        }

    }

}
