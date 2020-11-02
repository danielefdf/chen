package view.promote;

import java.awt.MouseInfo;
import java.awt.Point;
import engine.model.Node;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.elements.Pieces;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class PromoteStage extends Stage {

    private Node node;
    private byte piece;

    public byte getPiece() {
        return piece;
    }

    private HBox rootPane;
        private PromoteButton queenButton;
        private PromoteButton rookButton;
        private PromoteButton bishopButton;
        private PromoteButton knightButton;

    public PromoteStage(Stage ownerStage, Node newNode)
            throws Exception {

        node = newNode;

        newButtons(node);

        setTitle("promote to");
        initModality(Modality.APPLICATION_MODAL);
        initOwner(ownerStage);
        setResizable(false);
//        initStyle(StageStyle.UNDECORATED);
//        setOnCloseRequest();

        String iconsPath = getClass().getResource("../../resources/icons").toExternalForm();

        getIcons().add(new Image(iconsPath + "/wn.png"));

        Point p = MouseInfo.getPointerInfo().getLocation();

        setX(p.getX());
        setY(p.getY());

        setPromoteStage();

    }

    private void setPromoteStage() {

        rootPane = new HBox();

        rootPane.setAlignment(Pos.CENTER);
        rootPane.setPadding(new Insets(10, 10, 10, 10));

        rootPane.getChildren().add(queenButton);
        rootPane.getChildren().add(rookButton);
        rootPane.getChildren().add(bishopButton);
        rootPane.getChildren().add(knightButton);

        Scene s = new Scene(rootPane);

        setScene(s);

        rootPane.requestFocus();

    }

    private void newButtons(Node focusNode)
            throws Exception {

        byte queenPiece  = (byte) (focusNode.playerColor * Pieces.ROLE_QUEEN);
        byte rookPiece   = (byte) (focusNode.playerColor * Pieces.ROLE_ROOK);
        byte bishopPiece = (byte) (focusNode.playerColor * Pieces.ROLE_BISHOP);
        byte knightPiece = (byte) (focusNode.playerColor * Pieces.ROLE_KNIGHT);

        queenButton = new PromoteButton(queenPiece);
        queenButton.setOnAction(e -> {
            piece = queenPiece;
            hide();
        });

        rookButton = new PromoteButton(rookPiece);
        rookButton.setOnAction(e -> {
            piece = rookPiece;
            hide();
        });

        bishopButton = new PromoteButton(bishopPiece);
        bishopButton.setOnAction(e -> {
            piece = bishopPiece;
            hide();
        });

        knightButton = new PromoteButton(knightPiece);
        knightButton.setOnAction(e -> {
            piece = knightPiece;
            hide();
        });

    }

}
