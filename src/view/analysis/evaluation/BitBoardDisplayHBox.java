package view.analysis.evaluation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import model.elements.BitBoards;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class BitBoardDisplayHBox extends HBox {

    private Label label;
    private Label bitBoardLabel;

    public Label getBitBoardLabel() { return bitBoardLabel; }

    public BitBoardDisplayHBox(String newBitBoardString, Long newBitBoard) {

        label = new Label(newBitBoardString);
        label.setMinWidth(200);

        Font f = Font.loadFont("file:\\" + ViewUtils.CONTROLS_FONTS_PATH + "\\SourceCodePro-Regular.ttf", 12);

        if (newBitBoard != null) {
            bitBoardLabel = new Label(BitBoards.toString(newBitBoard));
            bitBoardLabel.setFont(f);
        } else {
            bitBoardLabel = new Label();
            bitBoardLabel.setDisable(true);
        }
        bitBoardLabel.getStyleClass().add("bitBoardDisplayHBox-bitBoardLabel");

        setSpacing(5);

        setAlignment(Pos.CENTER_LEFT);

        setParmPaneSpinner();

    }

    private void setParmPaneSpinner() {

        getChildren().clear();

        getChildren().add(label);

        getChildren().add(bitBoardLabel);

    }

    public void reset(Long newBitBoard) {

        if (newBitBoard != null) {
            bitBoardLabel.setText(BitBoards.toString(newBitBoard));
        } else {
            bitBoardLabel.setText("");
            bitBoardLabel.setDisable(true);
        }

    }

}
