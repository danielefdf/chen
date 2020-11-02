package view.preview;

import engine.model.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.nodepane.NodePane;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class PreviewStage extends Stage {

    private static final int BOARD_TILE_EDGE = 20;
    private static final int OWNER_OFFSET = 100;

    private Stage ownerStage;

    private BorderPane rootBorderPane = new BorderPane();
        private NodePane nodePane;

    public PreviewStage(Stage newOwnerStage, Node newNode)
            throws Exception {

        ownerStage = newOwnerStage;

        nodePane = new NodePane(ownerStage, newNode, /*move*/null,
                /*showAvailables*/false, /*showNodeDetails*/false, /*showNodeSetVBox*/false,
                BOARD_TILE_EDGE);

        nodePane.setDisable(true);

        nodePane.setStyle(" -fx-border-color: white ; -fx-border-width: 5 ; ");

        rootBorderPane.setCenter(nodePane);

        // non può essere modale, perché altrimenti non va il meccanismo di show/hide
//        initModality(Modality.APPLICATION_MODAL);

        // no, perché altrimenti non va la chiamata a previewStage
//        initOwner(ownerStage);
        setResizable(false);
        initStyle(StageStyle.UNDECORATED);

        // è UNDECORATED
//        setTitle("preview");
//        String iconsPath = getClass().getResource("../../resources/icons").toExternalForm();
//        getIcons().add(new Image(iconsPath + "/wn.png"));

//        setOnCloseRequest();

        Scene s = new Scene(rootBorderPane);

        setScene(s);

    }

    public void resetPreviewStage(Node node)
            throws Exception {

        setX(0
                + ownerStage.getX()
//                + ntbb.getLayoutBounds().getMinX()
//                + (ntbb.getWidth() - getWidth()) / 2
                + OWNER_OFFSET);
        setY(0
                + ownerStage.getY()
//                + ntbb.getLayoutBounds().getMinY()
//                + (ntbb.getHeight() - getHeight()) / 2
                + OWNER_OFFSET);

        nodePane.resetNodePane(node, /*move*/null);

    }

}
