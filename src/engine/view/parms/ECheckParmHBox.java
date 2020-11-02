package engine.view.parms;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class ECheckParmHBox extends HBox {

    private Label label;
    private CheckBox checkBox;

    private EParmListener eParmListener;

    public CheckBox getCheckBox() { return checkBox; }

    public ECheckParmHBox(String newParmString, Boolean newBoolean, EParmListener newEParmListener) {

        label = new Label(newParmString);
        label.setMinWidth(EParmsVBox.LABEL_MIN_WIDTH);

        eParmListener = newEParmListener;

        checkBox = new CheckBox();

        if (newBoolean != null) {
            checkBox.setSelected(newBoolean);
        }

        checkBox.selectedProperty().addListener(
            (ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) ->
                    eParmListener.onValueChanged());

        setSpacing(5);

        setAlignment(Pos.CENTER_LEFT);

        setParmPaneSpinner();

    }

    public void resetCheckParmHBox(Boolean newBoolean) {

        if (newBoolean != null) {
            checkBox.setSelected(newBoolean);
        }

    }

    private void setParmPaneSpinner() {

        getChildren().clear();

        getChildren().add(label);
        getChildren().add(checkBox);

    }

}
