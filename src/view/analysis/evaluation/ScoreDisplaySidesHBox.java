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
public class ScoreDisplaySidesHBox extends HBox {

    private Label label;
    private Label whiteOpegScoreLabel;
    private Label whiteMidgScoreLabel;
    private Label whiteEndgScoreLabel;
    private Label blackOpegScoreLabel;
    private Label blackMidgScoreLabel;
    private Label blackEndgScoreLabel;

    public ScoreDisplaySidesHBox(String newParmString,
            Integer newWhiteOpegScore, Integer newWhiteMidgScore, Integer newWhiteEndgScore,
            Integer newBlackOpegScore, Integer newBlackMidgScore, Integer newBlackEndgScore) {

        label = new Label(newParmString);
        label.setMinWidth(200);

        Font f = Font.loadFont("file:\\" + ViewUtils.CONTROLS_FONTS_PATH + "\\SourceCodePro-Regular.ttf", 12);

        int width = 50;

        whiteOpegScoreLabel = new Label();
        whiteOpegScoreLabel.setPrefWidth(width);
        if (newWhiteOpegScore != null) {
            whiteOpegScoreLabel.setText(String.valueOf(newWhiteOpegScore));
            whiteOpegScoreLabel.setFont(f);
        } else {
            whiteOpegScoreLabel.setDisable(true);
        }

        whiteMidgScoreLabel = new Label();
        whiteMidgScoreLabel.setPrefWidth(width);
        if (newWhiteMidgScore != null) {
            whiteMidgScoreLabel.setText(String.valueOf(newWhiteMidgScore));
            whiteMidgScoreLabel.setFont(f);
        } else {
            whiteMidgScoreLabel.setDisable(true);
        }

        whiteEndgScoreLabel = new Label();
        whiteEndgScoreLabel.setPrefWidth(width);
        if (newWhiteEndgScore != null) {
            whiteEndgScoreLabel.setText(String.valueOf(newWhiteEndgScore));
            whiteEndgScoreLabel.setFont(f);
        } else {
            whiteEndgScoreLabel.setDisable(true);
        }

        blackOpegScoreLabel = new Label();
        blackOpegScoreLabel.setPrefWidth(width);
        if (newBlackOpegScore != null) {
            blackOpegScoreLabel.setText(String.valueOf(newBlackOpegScore));
            blackOpegScoreLabel.setFont(f);
        } else {
            blackOpegScoreLabel.setDisable(true);
        }

        blackMidgScoreLabel = new Label();
        blackMidgScoreLabel.setPrefWidth(width);
        if (newBlackMidgScore != null) {
            blackMidgScoreLabel.setText(String.valueOf(newBlackMidgScore));
            blackMidgScoreLabel.setFont(f);
        } else {
            blackMidgScoreLabel.setDisable(true);
        }

        blackEndgScoreLabel = new Label();
        blackEndgScoreLabel.setPrefWidth(width);
        if (newBlackEndgScore != null) {
            blackEndgScoreLabel.setText(String.valueOf(newBlackEndgScore));
            blackEndgScoreLabel.setFont(f);
        } else {
            blackEndgScoreLabel.setDisable(true);
        }

        setSpacing(5);

        setAlignment(Pos.CENTER_LEFT);

        setParmPaneSpinner();

    }

    private void setParmPaneSpinner() {

        getChildren().clear();

        getChildren().add(label);

        getChildren().add(whiteOpegScoreLabel);
        getChildren().add(whiteMidgScoreLabel);
        getChildren().add(whiteEndgScoreLabel);
        getChildren().add(blackOpegScoreLabel);
        getChildren().add(blackMidgScoreLabel);
        getChildren().add(blackEndgScoreLabel);

    }

    public void reset(Integer newWhiteOpegScore, Integer newWhiteMidgScore, Integer newWhiteEndgScore,
            Integer newBlackOpegScore, Integer newBlackMidgScore, Integer newBlackEndgScore) {

        if (newWhiteOpegScore != null) {
            whiteOpegScoreLabel.setText(String.valueOf(newWhiteOpegScore));
        } else {
            whiteOpegScoreLabel.setDisable(true);
        }

        if (newWhiteMidgScore != null) {
            whiteMidgScoreLabel.setText(String.valueOf(newWhiteMidgScore));
        } else {
            whiteMidgScoreLabel.setDisable(true);
        }

        if (newWhiteEndgScore != null) {
            whiteEndgScoreLabel.setText(String.valueOf(newWhiteEndgScore));
        } else {
            whiteEndgScoreLabel.setDisable(true);
        }

        if (newBlackOpegScore != null) {
            blackOpegScoreLabel.setText(String.valueOf(newBlackOpegScore));
        } else {
            blackOpegScoreLabel.setDisable(true);
        }

        if (newBlackMidgScore != null) {
            blackMidgScoreLabel.setText(String.valueOf(newBlackMidgScore));
        } else {
            blackMidgScoreLabel.setDisable(true);
        }

        if (newBlackEndgScore != null) {
            blackEndgScoreLabel.setText(String.valueOf(newBlackEndgScore));
        } else {
            blackEndgScoreLabel.setDisable(true);
        }

    }

}
