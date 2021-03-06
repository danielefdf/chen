package view.analysis.evaluation;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import engine.model.Engine;
import engine.model.EngineShow;
import engine.model.Node;
import engine.view.evaluation.EEvaluationTabPane;
import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import view.nodepane.NodePane;
import view.nodepane.NodePaneListener;
import view.preview.PreviewStage;
import view.utilities.ToolBarButton;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class EvaluationBorderPane extends BorderPane {

    private Stage ownerStage;
    private Engine engine;
    private Node node;

    private ToolBar nodesToolBar = new ToolBar();
        private ToolBarButton searchToolBarButton;
        private Separator separator;
        private ToolBarButton clearToolBarButton;
    private HBox controlsHBox = new HBox();
        private NodePane nodePane;
        private EEvaluationTabPane eEvaluationTabPane;

    private PreviewStage previewStage;

    private EvaluationBorderPaneListener evaluationBorderPaneListener;

    public Node getNode() {
        return node;
    }

    public void setEvaluationBorderPaneListener(EvaluationBorderPaneListener newEvaluationBorderPaneListener) {
        evaluationBorderPaneListener = newEvaluationBorderPaneListener;
    }

    public EvaluationBorderPane(Stage newOwnerStage, Engine newEngine, Node newNode)
            throws Exception {

        ownerStage = newOwnerStage;
        engine = newEngine;
        node = new Node(newNode);

        newNodesToolBar();
        newControlsHBox();

        setTop(nodesToolBar);
        setCenter(controlsHBox);

        addEventFilter(KeyEvent.KEY_PRESSED, ke -> {

            if (ke.isControlDown()
                    && ke.getCode() == KeyCode.C) {
                try {
                    Toolkit.getDefaultToolkit().getSystemClipboard()
                            .setContents(new StringSelection(nodePane.getNode().toFen()), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ke.consume();
            } else if (ke.isControlDown()
                    && ke.getCode() == KeyCode.V) {
                Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard()
                        .getContents(this);
                if (t != null) {
                    try {
                        String s = (String) t.getTransferData(DataFlavor.stringFlavor);
                        node = new Node(s);
                        resetNodePane();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ke.consume();
            }

        });

        previewStage = new PreviewStage(ownerStage, newNode);

    }

    public void resetEvaluationBorderPane(Engine newEngine, Node newNode)
            throws Exception {

        engine = newEngine;
        node = new Node(newNode);

        addNodeToolBarButton();
        resetControlsHBox();

    }

    private void newNodesToolBar()
            throws Exception {

        String searchTitle = "";

        searchToolBarButton = new ToolBarButton(searchTitle);
        searchToolBarButton.setText("start search");
        searchToolBarButton.setOnAction(ae -> {
            try {

                evaluationBorderPaneListener.onSearchButtonPressed(node);

                if (evaluationBorderPaneListener.getNextMoveThread() != null
                        && evaluationBorderPaneListener.getNextMoveThread().isAlive()) {
                    searchToolBarButton.setText("stop search");
                } else {
                    searchToolBarButton.setText("start search");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        separator = new Separator();
        separator.setPadding(new Insets(0, 5, 0, 8));

        clearToolBarButton = new ToolBarButton("clear");
        clearToolBarButton.setOnAction(ae -> {
            try {

                nodesToolBar.getItems().clear();
                nodesToolBar.getItems().add(searchToolBarButton);
                nodesToolBar.getItems().add(separator);
                nodesToolBar.getItems().add(clearToolBarButton);

                addNodeToolBarButton(); // root node

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        nodesToolBar.getItems().clear();
        nodesToolBar.getItems().add(searchToolBarButton);
        nodesToolBar.getItems().add(separator);
        nodesToolBar.getItems().add(clearToolBarButton);

        addNodeToolBarButton(); // root node

    }

    private void newControlsHBox()
            throws Exception {

        newNodePane();
        newEEvaluationTabPane();

        setControlsHBox();

    }

    private void newEEvaluationTabPane()
            throws Exception {

        eEvaluationTabPane = new EEvaluationTabPane(engine, node);

        HBox.setHgrow(eEvaluationTabPane, Priority.ALWAYS);

    }

    private void resetEEvaluationTabPane()
            throws Exception {

        eEvaluationTabPane.resetEEvaluationTabPane(engine, node);

    }

    private void resetControlsHBox()
            throws Exception {

        resetNodePane();
        resetEEvaluationTabPane();

    }

    private void setControlsHBox() {

        controlsHBox.getChildren().clear();

        controlsHBox.getChildren().add(nodePane);
        controlsHBox.getChildren().add(eEvaluationTabPane);

    }

    private void newNodePane() throws Exception {

        nodePane = new NodePane(ownerStage, node, null,
                false, true, true,
                40);

        nodePane.setNodePaneListener(new NodePaneListener() {

            @Override
            public void onPieceRemoved(byte square)
                    throws Exception {
                resetEvaluationBorderPane(engine, nodePane.getNode());
                if (evaluationBorderPaneListener != null) {
                    evaluationBorderPaneListener.onEvaluationNodeChanged(nodePane.getNode());
                }
            }

            @Override
            public void onPieceMoved(byte fromSquare, byte toSquare)
                    throws Exception {
                resetEvaluationBorderPane(engine, nodePane.getNode());
                if (evaluationBorderPaneListener != null) {
                    evaluationBorderPaneListener.onEvaluationNodeChanged(nodePane.getNode());
                }
            }

            @Override
            public void onPieceAdded(byte square)
                    throws Exception {
                resetEvaluationBorderPane(engine, nodePane.getNode());
                if (evaluationBorderPaneListener != null) {
                    evaluationBorderPaneListener.onEvaluationNodeChanged(nodePane.getNode());
                }
            }

            @Override
            public void onBoardReset(Node newNode)
                    throws Exception {
                resetEvaluationBorderPane(engine, newNode);
                if (evaluationBorderPaneListener != null) {
                    evaluationBorderPaneListener.onEvaluationNodeChanged(nodePane.getNode());
                }
            }

        });

        addEventFilter(KeyEvent.KEY_PRESSED, nodePane.getKeyEventHandler());

    }

    private void resetNodePane() throws Exception {

        removeEventFilter(KeyEvent.KEY_PRESSED, nodePane.getKeyEventHandler());

        nodePane.resetNodePane(node, null);

        addEventFilter(KeyEvent.KEY_PRESSED, nodePane.getKeyEventHandler());

    }

    private void addNodeToolBarButton() throws Exception {

        NodeToolBarButton nodeTBB = new NodeToolBarButton(node);

        setNodeToolBarListeners(nodeTBB);

        if (nodesToolBar.getItems().size() == 3) {

            /* add item */

            // [x] [|] [x]
            // [x] [|] [x] [*0*]
            nodesToolBar.getItems().add(nodeTBB);

        } else {
            int prevNTBIndex;
            NodeToolBarButton prevNTB;

            /* add item */

            // [x] [|] [x] [1] [2] [3]
            // [x] [|] [x] [1] [2] [3] [*0*]
            prevNTBIndex = nodesToolBar.getItems().size() - 1;
            prevNTB = copyNodeToolBarButton(
                    (NodeToolBarButton) nodesToolBar.getItems().get(prevNTBIndex));
            nodesToolBar.getItems().add(prevNTB);

            /* move items */

            // [x] [|] [x] [1] [2]   [3]   [*0*]
            // [x] [|] [x] [1] [2]   [*0*] [*3*]
            // [x] [|] [x] [1] [*0*] [*2*] [*3*]
            prevNTBIndex = nodesToolBar.getItems().size() - 2;
            for (int i = prevNTBIndex; i >= 4; --i) {
                prevNTB = copyNodeToolBarButton(
                        (NodeToolBarButton) nodesToolBar.getItems().get(i - 1));
                nodesToolBar.getItems().set(i, prevNTB);
            }

            /* set 1st item */

            // [x] [|] [x] [*0*] [*1*] [*2*] [*3*]
            nodesToolBar.getItems().set(3, nodeTBB);
        }

    }

    private NodeToolBarButton copyNodeToolBarButton(NodeToolBarButton inputNTBB)
            throws Exception {

        NodeToolBarButton outputNTBB = new NodeToolBarButton(inputNTBB);

        setNodeToolBarListeners(outputNTBB);

        return outputNTBB;
    }

    private void setNodeToolBarListeners(NodeToolBarButton nodeTBB)
            throws Exception {

        nodeTBB.setOnAction(ae -> {
            try {

                resetEvaluationBorderPane(engine, nodeTBB.getNode());
                if (evaluationBorderPaneListener != null) {
                    evaluationBorderPaneListener.onEvaluationNodeChanged(nodeTBB.getNode());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        nodeTBB.setOnMousePressed(me -> {
            if (me.getButton() == MouseButton.SECONDARY) {
                nodesToolBar.getItems().remove(nodeTBB);
            }
        });

        nodeTBB.setOnMouseEntered(me -> {
            try {

                if (!previewStage.isShowing()) {
                    previewStage.resetPreviewStage(nodeTBB.getNode());
                    previewStage.show();
                    nodeTBB.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        nodeTBB.setOnMouseExited(me -> {
            if (previewStage.isShowing()) {
                previewStage.hide();
            }
        });

    }

}
