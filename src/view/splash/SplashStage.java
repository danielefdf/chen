package view.splash;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class SplashStage extends Stage {

    private static final long THREAD_PAUSE_MILLIS = 300;

    private static final ObservableList<String> AVAILABLE_ITEMS_LIST = FXCollections.observableArrayList(
            "board", "pieces", "fen strings", "players", "engine",
            "i/o tools", "figures", "fonts", "icons",
            "preferences", "help", "analysis tools", "utilities",
            ""
    );

    private VBox rootPane;
        private ImageView iconImageView;
        private ProgressBar stageProgressBar;
        private Label stageLoadingLabel;

    public SplashStage() {

        newImageView();
        newProgressBar();
        newLabel();

        initStyle(StageStyle.UNDECORATED);
        setAlwaysOnTop(true);

        setSplashStage();

    }

    public void showSplash(SplashStageListener splashStageListener) {

        final Task<ObservableList<String>> task = new Task<>() {
            @Override
            protected ObservableList<String> call()
                    throws InterruptedException {

                ObservableList<String> loadedItemsList = FXCollections.observableArrayList();

                updateMessage("loading. . .");

                for (int i = 0; i < AVAILABLE_ITEMS_LIST.size(); ++i) {
                    Thread.sleep(THREAD_PAUSE_MILLIS);
                    updateProgress(i + 1, AVAILABLE_ITEMS_LIST.size());
                    String nextLoadItem = AVAILABLE_ITEMS_LIST.get(i);
                    loadedItemsList.add(nextLoadItem);
                    updateMessage("loading " + nextLoadItem + ". . . ");
                }
                Thread.sleep(THREAD_PAUSE_MILLIS);
                updateMessage("ready.");

                return loadedItemsList;
            }
        };

        stageLoadingLabel.textProperty().bind(task.messageProperty());
        stageProgressBar.progressProperty().bind(task.progressProperty());

        task.stateProperty().addListener((observableValue, oldState, newState) -> {

            if (newState == Worker.State.SUCCEEDED) {

                stageProgressBar.progressProperty().unbind();
                stageProgressBar.setProgress(1);

                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), getScene().getRoot());
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> hide());
                fadeSplash.play();

                splashStageListener.onCompleteTask();

            }

        });

        new Thread(task).start();

        show();

    }

    private void setSplashStage() {

        rootPane = new VBox();

        rootPane.getStyleClass().add("rootPane");

        rootPane.setEffect(new DropShadow());

        rootPane.getChildren().add(iconImageView);
        rootPane.getChildren().add(stageProgressBar);
        rootPane.getChildren().add(stageLoadingLabel);

        Scene s = new Scene(rootPane);

        s.getStylesheets().add(getClass().getResource("splash.css").toExternalForm());

        setScene(s);

    }

    private void newImageView() {

        iconImageView = new ImageView(ViewUtils.selectRandomIcon());

    }

    private void newProgressBar() {

        stageProgressBar = new ProgressBar();

        stageProgressBar.getStyleClass().add("progressBar");

    }

    private void newLabel() {

        stageLoadingLabel = new Label("loading...");

        stageLoadingLabel.getStyleClass().add("label");

    }

}
