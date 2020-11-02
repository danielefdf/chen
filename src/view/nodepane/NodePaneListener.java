package view.nodepane;

import engine.model.Node;

public interface NodePaneListener {

    void onPieceMoved(byte fromSquare, byte toSquare)
    		throws Exception;

    void onPieceAdded(byte square)
    		throws Exception;

    void onPieceRemoved(byte square)
    		throws Exception;

    void onBoardReset(Node newNode)
    		throws Exception;

}
