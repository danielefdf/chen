package view.nodepane;

import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import model.elements.Colors;
import model.elements.Squares;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class BoardEPFigure extends StackPane {

    private byte playerColor;
    private byte file, rank;
    private double mouseX, mouseY;
    private int boardTileEdge;

    private BoardEPFigureListener boardEPFigureListener;

    public BoardEPFigure(byte newPlayerColor, byte newFile, byte newRank, int newBoardTileEdge)
            throws Exception {

        playerColor = newPlayerColor;

        boardTileEdge = newBoardTileEdge;

        move(newFile, newRank);

        ImageView pieceImageView;

        switch (playerColor) {
        case Colors.WHITE: pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\wep.png"); break;
        case Colors.BLACK: pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\bep.png"); break;
        default:
            throw new Exception("playerColor=" + playerColor);
        }

        pieceImageView.setFitWidth(boardTileEdge);
        pieceImageView.setFitHeight(boardTileEdge);

        setEffect(ViewUtils.newDropShadow((byte) -playerColor, boardTileEdge));

        getChildren().add(pieceImageView);

        setOnMouseEntered(e -> setCursor(Cursor.HAND));

        setOnMouseExited(e -> setCursor(Cursor.DEFAULT));

        setOnMousePressed(e -> {

            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

            if (e.getButton() == MouseButton.PRIMARY) {
                setCursor(Cursor.CLOSED_HAND);
                if (boardEPFigureListener != null) {
                    try {
                        boardEPFigureListener.onMousePressedLeft(file, rank);
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

                    if (boardEPFigureListener != null) {
                        boardEPFigureListener.onMouseReleasedLeft(toFile, toRank);
                    }

                } else {

                    if (boardEPFigureListener != null) {
                        boardEPFigureListener.onMouseReleasedRight(toFile, toRank);
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

    public void setBoardEPFigureListener(BoardEPFigureListener newBoardEPFigureListener) {

        boardEPFigureListener = newBoardEPFigureListener;

    }

}
