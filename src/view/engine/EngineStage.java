package view.engine;

import engine.model.Engine;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.utilities.ToolBarButton;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class EngineStage extends Stage {

    private Stage ownerStage;
    private Engine engine;

    private boolean okButtonPressed = false;

    private BorderPane rootBorderPane;
        private ToolBar okToolBar = new ToolBar();
            private ToolBarButton okToolBarButton = new ToolBarButton("ok");
        private EngineHBox engineHBox;

    public Engine getEngine() {
        return engine;
    }

    public boolean isOkButtonPressed() {
        return okButtonPressed;
    }

    public EngineStage(Stage newOwnerStage, Engine newEngine)
            throws Exception {

        ownerStage = newOwnerStage;
        engine = newEngine;

        // 0----+----1----+---|2----+--
        // model.engine.Engine@406e116d
        engine.name = engine.toString().substring(19);

        newOkToolBar();
        newEngineHBox();

        setTitle("engine parms");
//        initModality(Modality.APPLICATION_MODAL);
        initOwner(ownerStage);
//        setResizable(false);
//        initStyle(StageStyle.UNDECORATED);
//        setOnCloseRequest(we ->  we.consume() );

        setHeight(700);

        getIcons().add(ViewUtils.selectRandomIcon());

        setNextGameStage();

    }

    private void setNextGameStage() {

        rootBorderPane = new BorderPane();

        rootBorderPane.setTop(okToolBar);
        rootBorderPane.setCenter(engineHBox);

        okToolBarButton.requestFocus();

        Scene s = new Scene(rootBorderPane);

//        s.getStylesheets().add(getClass().getResource("engine.css").toExternalForm());

        setScene(s);

    }

    private void newOkToolBar() {

        newOkButton();

        setOkToolBar();

    }

    private void setOkToolBar() {

        okToolBar.getItems().clear();

        okToolBar.getItems().add(okToolBarButton);

    }

    private void newOkButton() {

        okToolBarButton.setOnAction(ae -> {
            try {

                engine = engineHBox.getEngine();
                okButtonPressed = true;
                EngineStage.this.hide();

            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText("");
                alert.setContentText("invalid parms - logic error");
                alert.showAndWait();
                e.printStackTrace();
            }
        });

    }

    private void newEngineHBox()
            throws Exception {

        engineHBox = new EngineHBox(ownerStage, engine);

    }

}
