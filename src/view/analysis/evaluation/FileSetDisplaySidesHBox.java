package view.analysis.evaluation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import model.elements.FileSets;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class FileSetDisplaySidesHBox extends HBox {

    private Label label;
    private Label whiteFileSetLabel;
    private Label blackFileSetLabel;

    public FileSetDisplaySidesHBox(String newFileSetDescrString, Integer newWhiteFileSet, Integer newBlackFileSet) {

        label = new Label(newFileSetDescrString);
        label.setMinWidth(200);

        Font f = Font.loadFont("file:\\" + ViewUtils.CONTROLS_FONTS_PATH + "\\SourceCodePro-Regular.ttf", 12);

        if (newWhiteFileSet != null) {
            whiteFileSetLabel = new Label(FileSets.toString(newWhiteFileSet));
            whiteFileSetLabel.setFont(f);
        } else {
            whiteFileSetLabel = new Label();
            whiteFileSetLabel.setDisable(true);
        }
        whiteFileSetLabel.getStyleClass().add("fileSetDisplaySidesHBox-fileSetBoardSidesLabel");

        if (newBlackFileSet != null) {
            blackFileSetLabel = new Label(FileSets.toString(newBlackFileSet));
            blackFileSetLabel.setFont(f);
        } else {
            blackFileSetLabel = new Label();
            blackFileSetLabel.setDisable(true);
        }
        blackFileSetLabel.getStyleClass().add("fileSetDisplaySidesHBox-fileSetBoardSidesLabel");

        setSpacing(5);

        setAlignment(Pos.CENTER_LEFT);

        setParmPaneSpinner();

    }

    private void setParmPaneSpinner() {

        getChildren().clear();

        getChildren().add(label);
        getChildren().add(whiteFileSetLabel);
        getChildren().add(blackFileSetLabel);

    }

    public void reset(Integer newWhiteFileSet, Integer newBlackFileSet) {

        if (newWhiteFileSet != null) {
            whiteFileSetLabel.setText(FileSets.toString(newWhiteFileSet));
        } else {
            whiteFileSetLabel.setText("");
            whiteFileSetLabel.setDisable(true);
        }

        if (newBlackFileSet != null) {
            blackFileSetLabel.setText(FileSets.toString(newBlackFileSet));
        } else {
            blackFileSetLabel.setText("");
            blackFileSetLabel.setDisable(true);
        }

    }

}
