package view.nodepane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class BoardTile extends Rectangle {

    private int edge;
    private byte file;
    private byte rank;

    public int getEdge() {
        return edge;
    }

    public void setEdge(int edge) {
        this.edge = edge;
    }

    private BoardTileListener boardTileListener;

    public BoardTile(int newEdge, byte newFile, byte newRank) {

        edge = newEdge;
        file = newFile;
        rank = newRank;

        setWidth(edge);
        setHeight(edge);

        relocate(file * edge, rank * edge);

        // __0: black -----------------------------------> 255: white
        // __0 ---> _63|_64 ---> 127|128 ---> 191|192 ---> 255
        // __0 -------> _84|_85 --------> 170|171 -------> 255

        setFill(((file + rank) % 2 == 0) ?
                Color.grayRgb(127 + 20) :
                    Color.grayRgb(128 - 20));

        setOnMousePressed(e -> {

            if (boardTileListener != null) {
                try {
                    boardTileListener.onMousePressedLeft(file, rank);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        });

    }

    public void setBoardTileListener(BoardTileListener newBoardTileListener) {

        boardTileListener = newBoardTileListener;

    }

}
