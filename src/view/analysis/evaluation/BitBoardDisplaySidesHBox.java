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
public class BitBoardDisplaySidesHBox extends HBox {

    private Label label;
    private Label whiteBitBoardLabel;
    private Label blackBitBoardLabel;

    public Label getWhiteBitBoardLabel() { return whiteBitBoardLabel; }
    public Label getBlackBitBoardLabel() { return blackBitBoardLabel; }

    public BitBoardDisplaySidesHBox(String newBitBoardDescrString, Long newWhiteBitBoard, Long newBlackBitBoard) {

        label = new Label(newBitBoardDescrString);
        label.setMinWidth(200);

        Font f = Font.loadFont("file:\\" + ViewUtils.CONTROLS_FONTS_PATH + "\\SourceCodePro-Regular.ttf", 12);

        if (newWhiteBitBoard != null) {
            whiteBitBoardLabel = new Label(BitBoards.toString(newWhiteBitBoard));
            whiteBitBoardLabel.setFont(f);
        } else {
            whiteBitBoardLabel = new Label();
            whiteBitBoardLabel.setDisable(true);
        }
        whiteBitBoardLabel.getStyleClass().add("bitBoardDisplaySidesHBox-sideBitBoardLabel");

        if (newBlackBitBoard != null) {
            blackBitBoardLabel = new Label(BitBoards.toString(newBlackBitBoard));
            blackBitBoardLabel.setFont(f);
        } else {
            blackBitBoardLabel = new Label();
            blackBitBoardLabel.setDisable(true);
        }
        blackBitBoardLabel.getStyleClass().add("bitBoardDisplaySidesHBox-sideBitBoardLabel");

        setSpacing(5);

        setAlignment(Pos.CENTER_LEFT);

        setParmPaneSpinner();

    }

    public void reset(Long newWhiteBitBoard, Long newBlackBitBoard) {

        if (newWhiteBitBoard != null) {
            whiteBitBoardLabel.setText(BitBoards.toString(newWhiteBitBoard));
        } else {
            whiteBitBoardLabel.setText("");
            whiteBitBoardLabel.setDisable(true);
        }

        if (newBlackBitBoard != null) {
            blackBitBoardLabel.setText(BitBoards.toString(newBlackBitBoard));
        } else {
            blackBitBoardLabel.setText("");
            blackBitBoardLabel.setDisable(true);
        }

        setParmPaneSpinner();

    }

    private void setParmPaneSpinner() {

        getChildren().clear();

        getChildren().add(label);
        getChildren().add(whiteBitBoardLabel);
        getChildren().add(blackBitBoardLabel);

    }

}
