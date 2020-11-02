package model.figures;

/**
 * Contiene gli elementi per disegnare una scacchiera in formato testuale,
 *   utilizzando il carattere Chess Motif.
 */
public abstract class Motif {

    /*
     * 122222222223
     * 4!""""""""#5
     * 4$ + + + +%5
     * 4$+ + + + %5
     * 4$ + + + +%5
     * 4$+ + + + %5
     * 4$ + + + +%5
     * 4$+ + + + %5
     * 4$ + + + +%5
     * 4$+ + + + %5
     * 788888888889
     * 4/(((((((()5
     */

    // i pezzi su campo nero si ottengono con UPPERCASE

    public static final String TOP_EXT_EDGE = "122222222223", TOP_BOARD_EDGE = "4!\"\"\"\"\"\"\"\"#5";
    public static final String BOTTOM_EXT_EDGE = "788888888889", BOTTOM_BOARD_EDGE = "4/(((((((()5";
    public static final String LEFT_EXT_BOARD_EDGE = "4$", RIGHT_EXT_BOARD_EDGE = "%5";
    public static final String PLAYER_FLAG = ".";
    public static final String WHITE_PAWN = "p", WHITE_KNIGHT = "n", WHITE_BISHOP = "b", WHITE_ROOK = "r",
            WHITE_QUEEN = "q", WHITE_KING = "k";
    public static final String BLACK_PAWN = "o", BLACK_KNIGHT = "m", BLACK_BISHOP = "v", BLACK_ROOK = "t",
            BLACK_QUEEN = "w", BLACK_KING = "l";
    public static final String WHITE_FIELD_EMPTY_SQUARE = " ", BLACK_FIELD_EMPTY_SQUARE = "+";

}
