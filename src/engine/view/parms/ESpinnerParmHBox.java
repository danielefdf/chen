package engine.view.parms;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class ESpinnerParmHBox extends HBox {

    private static final int DEFAULT_MIN_VALUE = -999;
    private static final int DEFAULT_MAX_VALUE = 999;

    private Label label;
    private Integer midgScore;
    private Integer endgScore;

    private Spinner<Integer> midgScoreSpinner;
    private Spinner<Integer> endgScoreSpinner;

    private EParmListener eParmListener;

    public Spinner<Integer> getMidgScoreSpinner() { return midgScoreSpinner; }
    public Spinner<Integer> getEndgScoreSpinner() { return endgScoreSpinner; }

    public ESpinnerParmHBox(String newParmString, Integer newMidgScore, Integer newEndgScore,
            EParmListener newEParmListener) {

        midgScore = newMidgScore;
        endgScore = newEndgScore;

        label = new Label(newParmString);
        label.setMinWidth(EParmsVBox.LABEL_MIN_WIDTH);

        eParmListener = newEParmListener;

        if (midgScore != null) {
            midgScoreSpinner = new Spinner<>(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, midgScore);
            midgScoreSpinner.valueProperty().addListener(
                    (ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) ->
                            eParmListener.onValueChanged());
        } else {
            midgScoreSpinner = new Spinner<>();
            midgScoreSpinner.setDisable(true);
        }

        midgScoreSpinner.setEditable(true);

        if (endgScore != null) {
            endgScoreSpinner = new Spinner<>(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, endgScore);
            endgScoreSpinner.valueProperty().addListener(
                    (ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) ->
                            eParmListener.onValueChanged());
        } else {
            endgScoreSpinner = new Spinner<>();
            endgScoreSpinner.setDisable(true);
        }

        endgScoreSpinner.setEditable(true);

        setSpacing(5);

        setAlignment(Pos.CENTER_LEFT);

        setParmPaneSpinner();

    }

    public void resetSpinnerParmHBox(Integer newMidgScore, Integer newEndgScore) {

        midgScore = newMidgScore;
        endgScore = newEndgScore;

        if (midgScore != null) {
            midgScoreSpinner.getValueFactory().setValue(midgScore);
        }

        if (endgScore != null) {
            endgScoreSpinner.getValueFactory().setValue(endgScore);
        }

    }

    private void setParmPaneSpinner() {

        getChildren().clear();

        getChildren().add(label);
        getChildren().add(midgScoreSpinner);
        getChildren().add(endgScoreSpinner);

    }

}
