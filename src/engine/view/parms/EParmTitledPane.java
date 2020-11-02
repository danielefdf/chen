package engine.view.parms;

import javafx.scene.control.TitledPane;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class EParmTitledPane extends TitledPane {

    private EParmGridPane eParmGridPane = new EParmGridPane();

    public EParmTitledPane(String newTitle) {

        setText(newTitle);
        setExpanded(false);

        setContent(eParmGridPane);

    }

    public void clear() {

        eParmGridPane.getChildren().clear();

    }

    public void add(javafx.scene.Node node, int row, int col) {

        eParmGridPane.add(node, row, col);

    }

}
