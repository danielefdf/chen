package view.analysis.evaluation;

import engine.model.Node;
import javafx.scene.control.Button;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class NodeToolBarButton extends Button {

    private Node node;

    public Node getNode() {
        return node;
    }

    public NodeToolBarButton(Node newNode) {

        node = newNode;

        setPrefWidth(30);
        setPrefHeight(30);

        setMinWidth(30);

    }

    public NodeToolBarButton(NodeToolBarButton newNodeToolBarButton) {

        node = newNodeToolBarButton.getNode();

        setPrefWidth(30);
        setPrefHeight(30);

        setMinWidth(30);

    }

}
