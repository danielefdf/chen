package view.utilities;

import javafx.scene.control.Button;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class ToolBarButton extends Button {

    public ToolBarButton(String string) {

        super(string);

        setPrefWidth(110);
        setPrefHeight(30);

    }

}
