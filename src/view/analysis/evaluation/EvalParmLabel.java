package view.analysis.evaluation;

import javafx.scene.control.Label;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class EvalParmLabel extends Label {

    private static final int WIDTH = 150;

    public EvalParmLabel(String newString) {

        setPrefWidth(WIDTH);
        setText(newString);

    }

}
