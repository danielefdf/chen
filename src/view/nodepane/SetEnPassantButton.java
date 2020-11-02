package view.nodepane;

import javafx.scene.Cursor;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class SetEnPassantButton extends ToggleButton {

    private double boardTileEdge;

    private ImageView pieceImageView;

    public SetEnPassantButton(double newBoardTileEdge)
            throws Exception {

        boardTileEdge = newBoardTileEdge;

        pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\ep.png");

        pieceImageView.setFitWidth(boardTileEdge);
        pieceImageView.setFitHeight(boardTileEdge);

        pieceImageView.setEffect(ViewUtils.newDropShadow(/*sideColor*/null, boardTileEdge));

        setGraphic(pieceImageView);

        setOnMouseEntered(e -> setCursor(Cursor.HAND));

        setOnMouseExited(e -> setCursor(Cursor.DEFAULT));

    }

}
