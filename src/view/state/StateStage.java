package view.state;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class StateStage extends Stage {

    private static final int DURATION_MILLIS = 700;

    private Stage ownerStage;

    private String gameStateString;

    private Label stateLabel;

    public StateStage(Stage newOwnerStage, String newGameStateString)
            throws Exception {

        ownerStage = newOwnerStage;

        gameStateString = newGameStateString;

        newStateLabel();

//        setTitle("");
        initModality(Modality.NONE);
        initOwner(ownerStage);
        setResizable(false);
        initStyle(StageStyle.UNDECORATED);
//        setOnCloseRequest();

        setStateStage();

    }

    private void setStateStage() {

        Scene s = new Scene(stateLabel);

        s.getStylesheets().add(getClass().getResource("state.css").toExternalForm());

        setScene(s);

        Timeline t = new Timeline(new KeyFrame(Duration.millis(DURATION_MILLIS),
                new EventHandler<>() {
                    @Override
                    public void handle(ActionEvent ae) {
                        StateStage.this.hide();
                    }
                }));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();

    }

    private void newStateLabel()
            throws Exception {

        stateLabel = new Label();

        stateLabel.getStyleClass().add("stateLabel");

        stateLabel.setText(gameStateString);

    }

}
