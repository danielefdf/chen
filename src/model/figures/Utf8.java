package model.figures;

/**
 * Contiene gli elementi per disegnare una scacchiera in formato testuale,
 * utilizzando le figure presenti nella codifica Utf-8.
 */
public abstract class Utf8 {

    public static final String WHITE_PAWN   = "♙", BLACK_PAWN   = "♟",
                               WHITE_KNIGHT = "♘", BLACK_KNIGHT = "♞",
                               WHITE_BISHOP = "♗", BLACK_BISHOP = "♝",
                               WHITE_ROOK   = "♖", BLACK_ROOK   = "♜",
                               WHITE_QUEEN  = "♕", BLACK_QUEEN  = "♛",
                               WHITE_KING   = "♔", BLACK_KING   = "♚";

    public static final String VOID_SQUARE = " ";

    public static final String PLAYER_FLAG = "#";

    public static final String SQUARE_SEPARATOR = "|";

}
