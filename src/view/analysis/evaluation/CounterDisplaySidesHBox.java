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
public class CounterDisplaySidesHBox extends HBox {

    private Label label;
    private Label whiteCounterLabel;
    private Label blackCounterLabel;

    public Label getWhiteCounterLabel() { return whiteCounterLabel; }
    public Label getBlackCounterLabel() { return blackCounterLabel; }

    public CounterDisplaySidesHBox(String newParmString, Integer newWhiteCounter, Integer newBlackCounter) {

        label = new Label(newParmString);
        label.setMinWidth(200);

        Font f = Font.loadFont("file:\\" + ViewUtils.CONTROLS_FONTS_PATH + "\\SourceCodePro-Regular.ttf", 12);

        if (newWhiteCounter != null) {
            whiteCounterLabel = new Label(String.valueOf(newWhiteCounter));
            whiteCounterLabel.setFont(f);
        } else {
            whiteCounterLabel = new Label();
            whiteCounterLabel.setDisable(true);
        }

        if (newBlackCounter != null) {
            blackCounterLabel = new Label(String.valueOf(newBlackCounter));
            blackCounterLabel.setFont(f);
        } else {
            blackCounterLabel = new Label();
            blackCounterLabel.setDisable(true);
        }

        setSpacing(5);

        setAlignment(Pos.CENTER_LEFT);

        setParmPaneSpinner();

    }

    private void setParmPaneSpinner() {

        getChildren().clear();

        getChildren().add(label);

        getChildren().add(whiteCounterLabel);
        getChildren().add(blackCounterLabel);

    }

    public void reset(Integer newWhiteCounter, Integer newBlackCounter) {

        if (newWhiteCounter != null) {
            whiteCounterLabel.setText(String.valueOf(newWhiteCounter));
        } else {
            whiteCounterLabel.setText("");
            whiteCounterLabel.setDisable(true);
        }

        if (newBlackCounter != null) {
            blackCounterLabel.setText(String.valueOf(newBlackCounter));
        } else {
            blackCounterLabel.setText("");
            blackCounterLabel.setDisable(true);
        }

    }

}
