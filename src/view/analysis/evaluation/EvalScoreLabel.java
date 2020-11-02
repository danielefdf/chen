package view.analysis.evaluation;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class EvalScoreLabel extends Label {

    private static final int WIDTH = 50;

    public EvalScoreLabel(Integer newScore) {

        setPrefWidth(WIDTH);

        if (newScore != null) {
            setText(String.valueOf(newScore));

            Font f = Font.loadFont("file:\\" + ViewUtils.CONTROLS_FONTS_PATH + "\\SourceCodePro-Regular.ttf", 12);

            setFont(f);
        }

    }

    public void reset(Integer newScore) {

        if (newScore != null) {
            setText(String.valueOf(newScore));
        }

    }

}





