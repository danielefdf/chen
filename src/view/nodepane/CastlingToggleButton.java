package view.nodepane;

import javafx.scene.control.ToggleButton;
import javafx.scene.text.Font;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class CastlingToggleButton extends ToggleButton {

    public CastlingToggleButton(int newBoardTileEdge) {

        setFont(new Font(0));

        setPrefHeight(newBoardTileEdge / 3.0);

    }

    public void reset() {

        if (isSelected()) {
            setStyle("-fx-base: darkgray;");
        } else {
            setStyle("-fx-base: white;");
        }

    }

}
