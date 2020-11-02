package view.analysis.evaluation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class ScoreDisplayHBox extends HBox {

    private Label label;
    private Label opegScoreLabel;
    private Label midgScoreLabel;
    private Label endgScoreLabel;

    public Label getOpegScoreLabel() { return opegScoreLabel; }
    public Label getMidgScoreLabel() { return midgScoreLabel; }
    public Label getEndgScoreLabel() { return endgScoreLabel; }

    public ScoreDisplayHBox(String newParmString, Integer newOpegScore, Integer newMidgScore, Integer newEndgScore) {

        label = new Label(newParmString);
        label.setMinWidth(200);

        Font f = Font.loadFont("file:\\" + ViewUtils.CONTROLS_FONTS_PATH + "\\SourceCodePro-Regular.ttf", 12);

        if (newOpegScore != null) {
            opegScoreLabel = new Label(String.valueOf(newOpegScore));
            opegScoreLabel.setFont(f);
        } else {
            opegScoreLabel = new Label();
            opegScoreLabel.setDisable(true);
        }

        if (newMidgScore != null) {
            midgScoreLabel = new Label(String.valueOf(newMidgScore));
            midgScoreLabel.setFont(f);
        } else {
            midgScoreLabel = new Label();
            midgScoreLabel.setDisable(true);
        }

        if (newEndgScore != null) {
            endgScoreLabel = new Label(String.valueOf(newEndgScore));
            endgScoreLabel.setFont(f);
        } else {
            endgScoreLabel = new Label();
            endgScoreLabel.setDisable(true);
        }

        setSpacing(5);

        setAlignment(Pos.CENTER_LEFT);

        setParmPaneSpinner();

    }

    private void setParmPaneSpinner() {

        getChildren().clear();

        getChildren().add(label);

        getChildren().add(opegScoreLabel);
        getChildren().add(midgScoreLabel);
        getChildren().add(endgScoreLabel);

    }

    public void reset(Integer newOpegScore, Integer newMidgScore, Integer newEndgScore) {

        if (newOpegScore != null) {
            opegScoreLabel.setText(String.valueOf(newOpegScore));
        } else {
            opegScoreLabel.setText("");
            opegScoreLabel.setDisable(true);
        }

        if (newMidgScore != null) {
            midgScoreLabel.setText(String.valueOf(newMidgScore));
        } else {
            midgScoreLabel.setText("");
            midgScoreLabel.setDisable(true);
        }

        if (newEndgScore != null) {
            endgScoreLabel.setText(String.valueOf(newEndgScore));
        } else {
            endgScoreLabel.setText("");
            endgScoreLabel.setDisable(true);
        }

    }

}
