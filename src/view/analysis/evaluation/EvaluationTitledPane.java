package view.analysis.evaluation;

import javafx.scene.control.TitledPane;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class EvaluationTitledPane extends TitledPane {

    private EvaluationGridPane evaluationGridPane = new EvaluationGridPane();

    public EvaluationTitledPane(String newTitle) {

        setText(newTitle);
        setExpanded(false);

        setContent(evaluationGridPane);

    }

    public void clear() {

        evaluationGridPane.getChildren().clear();

    }

    public void add(javafx.scene.Node node, int row, int col) {

        evaluationGridPane.add(node, row, col);

    }

}
