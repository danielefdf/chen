package view.promote;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.elements.Pieces;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class PromoteButton extends Button {

    private byte piece;

    public PromoteButton(byte newPiece)
            throws Exception {

        piece = newPiece;

        setMaxSize(40, 40);

        String figuresPath = getClass().getResource("../../resources/figures").toExternalForm();

        String imagePath = null;

        switch (piece) {
        case Pieces.WHITE_QUEEN:  imagePath = figuresPath + "/wq.png"; break;
        case Pieces.WHITE_ROOK:   imagePath = figuresPath + "/wr.png"; break;
        case Pieces.WHITE_BISHOP: imagePath = figuresPath + "/wb.png"; break;
        case Pieces.WHITE_KNIGHT: imagePath = figuresPath + "/wn.png"; break;
        case Pieces.BLACK_QUEEN:  imagePath = figuresPath + "/bq.png"; break;
        case Pieces.BLACK_ROOK:   imagePath = figuresPath + "/br.png"; break;
        case Pieces.BLACK_BISHOP: imagePath = figuresPath + "/bb.png"; break;
        case Pieces.BLACK_KNIGHT: imagePath = figuresPath + "/bn.png"; break;
        }

        assert imagePath != null;
        setGraphic(new ImageView(new Image(imagePath)));

        setOnMouseEntered(  e -> setCursor(Cursor.HAND));
        setOnMouseExited(   e -> setCursor(Cursor.DEFAULT));
        setOnMousePressed(  e -> setCursor(Cursor.CLOSED_HAND));
        setOnMouseReleased( e -> setCursor(Cursor.HAND));

    }

}
