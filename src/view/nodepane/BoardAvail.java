package view.nodepane;

import javafx.scene.shape.Rectangle;
import model.elements.Colors;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class BoardAvail extends Rectangle {

    private byte playerColor;
    private byte file;
    private byte rank;
    private int boardTileEdge;

    public BoardAvail(byte newPlayerColor, byte newFile, byte newRank, int newBoardTileEdge)
            throws Exception {

        super(newBoardTileEdge, newBoardTileEdge);

        playerColor = newPlayerColor;

        file = newFile;
        rank = newRank;

        boardTileEdge = newBoardTileEdge;

        int x = file * boardTileEdge;
        int y = rank * boardTileEdge;

        relocate(x, y);

        switch (playerColor) {
        case Colors.WHITE:
            setStyle("-fx-fill: black;" + "-fx-stroke: white;" + "-fx-stroke-width: 1.0;");
            break;
        case Colors.BLACK:
            setStyle("-fx-fill: white;" + "-fx-stroke: black;" + "-fx-stroke-width: 1.0;");
            break;
        default:
            throw new Exception("playerColor=" + playerColor);
        }

    }

}
