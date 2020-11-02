package view.nodepane;

import javafx.scene.Cursor;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.elements.Pieces;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class SetPieceButton extends ToggleButton {

    private byte piece;
    private double boardTileEdge;

    private ImageView pieceImageView;

    public SetPieceButton(byte newPiece, double newBoardTileEdge)
            throws Exception {

        piece = newPiece;

        boardTileEdge = newBoardTileEdge;

        String imagePath;

        switch (piece) {
        case Pieces.WHITE_PAWN:   imagePath = "file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\wp.png"; break;
        case Pieces.BLACK_PAWN:   imagePath = "file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\bp.png"; break;
        case Pieces.WHITE_KNIGHT: imagePath = "file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\wn.png"; break;
        case Pieces.BLACK_KNIGHT: imagePath = "file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\bn.png"; break;
        case Pieces.WHITE_BISHOP: imagePath = "file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\wb.png"; break;
        case Pieces.BLACK_BISHOP: imagePath = "file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\bb.png"; break;
        case Pieces.WHITE_ROOK:   imagePath = "file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\wr.png"; break;
        case Pieces.BLACK_ROOK:   imagePath = "file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\br.png"; break;
        case Pieces.WHITE_QUEEN:  imagePath = "file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\wq.png"; break;
        case Pieces.BLACK_QUEEN:  imagePath = "file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\bq.png"; break;
        case Pieces.WHITE_KING:   imagePath = "file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\wk.png"; break;
        case Pieces.BLACK_KING:   imagePath = "file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\bk.png"; break;
        default:
            throw new Exception("piece=" + piece);
        }

        pieceImageView = new ImageView(new Image(imagePath));

        pieceImageView.setFitWidth(boardTileEdge);
        pieceImageView.setFitHeight(boardTileEdge);

        pieceImageView.setEffect(ViewUtils.newDropShadow(/*sideColor*/null, boardTileEdge));

        setGraphic(pieceImageView);

        setOnMouseEntered(e -> setCursor(Cursor.HAND));

        setOnMouseExited(e -> setCursor(Cursor.DEFAULT));

    }

}
