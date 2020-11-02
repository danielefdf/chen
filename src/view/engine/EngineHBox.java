package view.engine;

import java.io.File;
import engine.model.Engine;
import engine.view.parms.EParmsVBox;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.utilities.ToolBarButton;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class EngineHBox extends HBox {

    private Stage ownerStage;

    private Engine engine;

    private ListView<String> engineNamesListView = new ListView<>();

    private VBox controlsVBox = new VBox();
        private Label nameLabel;
        private ToolBar toolBar;
            private ToolBarButton saveButton;
            private ToolBarButton collapseAllButton;
            private ToolBarButton expandAllButton;
        private ScrollPane engineParmsScrollPane;
            private EParmsVBox eParmsVBox;

    private EngineHBoxListener engineHBoxListener;

    public Engine getEngine() {
        return engine;
    }

    public void setEngineHBoxListener(EngineHBoxListener newEngineHBoxListener) {
        engineHBoxListener = newEngineHBoxListener;

    }

    public EngineHBox(Stage newOwnerStage, Engine newEngine)
            throws Exception {

        ownerStage = newOwnerStage;

        engine = newEngine;

        newEngineNamesListView();
        newControlsBorderPane();

        setEngineHBox();

    }

    public void resetEngineHBox(Engine newEngine)
            throws Exception {

        engine = newEngine;

        resetEngineNamesListView();
        resetControlsVBox();

    }

    private void setEngineHBox() {

        getChildren().clear();

        getChildren().add(engineNamesListView);
        getChildren().add(controlsVBox);

    }

    private void newEngineNamesListView() {

        engineNamesListView.setPrefWidth(100);

        engineNamesListView.setStyle("-fx-control-inner-background: lavender ;"
                + "-fx-control-inner-background-alt: derive(-fx-control-inner-background, 70%) ; ");

        loadEngineNamesListView();

        engineNamesListView.setOnKeyPressed(ke -> {
            try {

                if (ke.getCode() == KeyCode.ENTER) {

                    File f = new File(ViewUtils.ENGINES_DEFAULT_PATH
                            + "\\" + engineNamesListView.getSelectionModel().getSelectedItem());

                    engine = Engine.readEngine(f);

                    resetEngineHBox(engine);

                    if (engineHBoxListener != null) {
                        engineHBoxListener.onEngineChanged(engine);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void loadEngineNamesListView() {

        File folder = new File("engines");
        File[] filesList = folder.listFiles();

        engineNamesListView.getItems().clear();

        assert filesList != null;
        for(File file : filesList) {
            if (file.isFile()) {
                engineNamesListView.getItems().add(file.getName());
            }
        }

    }

    private void resetEngineNamesListView() {

        loadEngineNamesListView();

    }

    private void newControlsBorderPane() {

        newNameLabel();
        newToolBar();
        newEngineParmsScollPane();

        setControlsVBox();

    }

    private void resetControlsVBox() {

        resetNameLabel();
        resetEngineParmsScollPane();

    }

    private void setControlsVBox() {

        controlsVBox.getChildren().clear();

        controlsVBox.getChildren().add(nameLabel);
        controlsVBox.getChildren().add(toolBar);
        controlsVBox.getChildren().add(engineParmsScrollPane);

    }

    private void newNameLabel() {

        nameLabel = new Label();

        nameLabel.setText("    new engine :    " + engine.name);
        nameLabel.setMinHeight(23);

    }

    private void resetNameLabel() {

        nameLabel.setText("    new engine :    " + engine.name);

    }

    private void newToolBar() {

        saveButton = new ToolBarButton("save");
        saveButton.setOnAction(e -> {
            try {

                FileChooser fc = new FileChooser();
                fc.setInitialDirectory(new File(ViewUtils.ENGINES_DEFAULT_PATH));
                fc.setInitialFileName(engine.name);
//                fc.getExtensionFilters().addAll(
//                        new FileChooser.ExtensionFilter("chess engine", "*.ngn"));

                File fileToSave = fc.showSaveDialog(ownerStage);
                if (fileToSave != null) {
                    engine.name = fileToSave.getName();
                    int rc = Engine.writeEngine(engine, fileToSave);
                    if (rc == 0) {
                        resetEngineHBox(engine);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("error");
                        alert.setHeaderText("");
                        alert.setContentText("error occurred in saving engine");
                        alert.showAndWait();
                    }
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        collapseAllButton = new ToolBarButton("collapse all");
        collapseAllButton.setOnAction(e -> eParmsVBox.collapseAll());

        expandAllButton = new ToolBarButton("expand all");
        expandAllButton.setOnAction(e -> eParmsVBox.expandAll());

        toolBar = new ToolBar();

        setToolBar();

    }

    private void setToolBar() {

        toolBar.getItems().clear();

        toolBar.getItems().add(saveButton);
        toolBar.getItems().add(collapseAllButton);
        toolBar.getItems().add(expandAllButton);

    }

    private void newEngineParmsScollPane() {

        eParmsVBox = new EParmsVBox(engine);

        eParmsVBox.setEngineParmsVBoxListener((Engine engine) -> {
            if (engineHBoxListener != null) {
                engineHBoxListener.onEngineChanged(engine);
            }
        });

        engineParmsScrollPane = new ScrollPane();

        engineParmsScrollPane.setFitToWidth(true);
        engineParmsScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

        engineParmsScrollPane.setContent(eParmsVBox);

    }

    private void resetEngineParmsScollPane() {

        eParmsVBox.resetEngineParmsVBox(engine);

    }

}
