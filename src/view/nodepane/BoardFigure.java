package view.nodepane;

import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import model.elements.Pieces;
import model.elements.Squares;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class BoardFigure extends StackPane {

    private Byte piece;
    private byte file, rank;
    private double mouseX, mouseY;
    private int boardTileEdge;

    private BoardFigureListener boardFigureListener;

    public BoardFigure(byte newPiece, byte newFile, byte newRank, int newBoardTileEdge)
            throws Exception {

        piece = newPiece;

        boardTileEdge = newBoardTileEdge;

        move(newFile, newRank);

        ImageView pieceImageView;

        switch (piece) {
        case Pieces.WHITE_PAWN:   pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\wp.png"); break;
        case Pieces.BLACK_PAWN:   pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\bp.png"); break;
        case Pieces.WHITE_KNIGHT: pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\wn.png"); break;
        case Pieces.BLACK_KNIGHT: pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\bn.png"); break;
        case Pieces.WHITE_BISHOP: pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\wb.png"); break;
        case Pieces.BLACK_BISHOP: pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\bb.png"); break;
        case Pieces.WHITE_ROOK:   pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\wr.png"); break;
        case Pieces.BLACK_ROOK:   pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\br.png"); break;
        case Pieces.WHITE_QUEEN:  pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\wq.png"); break;
        case Pieces.BLACK_QUEEN:  pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\bq.png"); break;
        case Pieces.WHITE_KING:   pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\wk.png"); break;
        case Pieces.BLACK_KING:   pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\bk.png"); break;
        default:
            throw new Exception("piece=" + piece);
        }

        pieceImageView.setFitWidth(boardTileEdge);
        pieceImageView.setFitHeight(boardTileEdge);

        setEffect(ViewUtils.newDropShadow(Pieces.color(piece), boardTileEdge));

        getChildren().add(pieceImageView);

        setOnMouseEntered(e -> setCursor(Cursor.HAND));

        setOnMouseExited(e -> setCursor(Cursor.DEFAULT));

        setOnMousePressed(e -> {

            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

            if (e.getButton() == MouseButton.PRIMARY) {
                setCursor(Cursor.CLOSED_HAND);
                if (boardFigureListener != null) {
                    try {
                        boardFigureListener.onMousePressedLeft(piece, file, rank);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                toFront();
            }

        });

        setOnMouseDragged(e -> {

            setCursor(Cursor.CLOSED_HAND);
            if (e.getButton() == MouseButton.PRIMARY) {
                relocate(e.getSceneX() - mouseX + boardTileEdge * file,
                         e.getSceneY() - mouseY + boardTileEdge * rank);
            }

        });

        setOnMouseReleased(e -> {
            try {

                setCursor(Cursor.HAND);

                byte toFile = toBoard(getLayoutX());
                byte toRank = toBoard(getLayoutY());

                if (toFile >= 0 && toFile <= Squares.EDGE - 1
                        && toRank >= 0 && toRank <= Squares.EDGE - 1) {
                    move(toFile, toRank);
                } else {
                    move(file, rank);
                }

                if (e.getButton() == MouseButton.PRIMARY) {

                    if (boardFigureListener != null) {
                        boardFigureListener.onMouseReleasedLeft(toFile, toRank);
                    }

                } else {

                    if (boardFigureListener != null) {
                        boardFigureListener.onMouseReleasedRight(toFile, toRank);
                    }

                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

    }

    public void move(byte toFile, byte toRank) {

        file = toFile;
        rank = toRank;

        relocate(boardTileEdge * file, boardTileEdge * rank);

    }

    private byte toBoard(double pixel) {

        return (byte) ((boardTileEdge / 2 + pixel) / boardTileEdge);
    }

    public void setBoardFigureListener(BoardFigureListener newBoardFigureListener) {

        boardFigureListener = newBoardFigureListener;

    }

}
