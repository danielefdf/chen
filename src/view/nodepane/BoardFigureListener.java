package view.nodepane;

public interface BoardFigureListener {

    void onMousePressedLeft(byte piece, byte fromFile, byte fromRank)
            throws Exception;

    void onMouseReleasedLeft(byte toFile, byte toRank)
            throws Exception;

    void onMouseReleasedRight(byte toFile, byte toRank)
            throws Exception;

}
