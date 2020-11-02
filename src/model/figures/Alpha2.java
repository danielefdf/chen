package model.figures;

/**
 * Contiene gli elementi per disegnare una scacchiera in formato testuale,
 *   utilizzando il carattere ChessAlpha2.
 */
public abstract class Alpha2 {

    // con bordo esterno
    //    public static final String TOP_EXT_EDGE = "ùúúúúúúúúúúû", TOP_BOARD_EDGE = "Í[<<<<<<<<]Ì";
    //    public static final String BOTTOM_EXT_EDGE = "ÙÚÚÚÚÚÚÚÚÚÚÛ", BOTTOM_BOARD_EDGE = "Í{yyyyyyyy}Ì";
    //    public static final String LEFT_EXT_BOARD_EDGE = "Í|", RIGHT_EXT_BOARD_EDGE = "\\Ì";

    // senza bordo esterno
    public static final String TOP_EXT_EDGE = "", TOP_BOARD_EDGE = " [<<<<<<<<] ";

    public static final String BOTTOM_EXT_EDGE = "", BOTTOM_BOARD_EDGE = " {yyyyyyyy} ";

    public static final String LEFT_EXT_BOARD_EDGE = " |", RIGHT_EXT_BOARD_EDGE = "\\ ";

    // il colore al tratto sarà segnalato diversamente
    //    public static final String ACTIVE_PLAYER_FLAG = "x";
    public static final String PLAYER_FLAG = "";

    /*
     * ùúúúúúúúúúúû
     * Í[<<<<<<<<]Ì
     * Í|LKJMNJKL\Ì
     * Í|IIIIIIII\Ì
     * Í|        \Ì
     * Í|        \Ì
     * Í|        \Ì
     * Í|        \Ì
     * Í|iiiiiiii\Ì
     * Í|lkjmnjkl\Ì
     * Í{yyyyyyyy}Ì
     * ÙÚÚÚÚÚÚÚÚÚÚÛ
     */

    // elementi differenziati per campo -- per scacchiera testuale
    // i pezzi neri si ottengono con LOWERCASE
    public static final String WHITE_FIELD_WHITE_PAWN   = "Ê", BLACK_FIELD_WHITE_PAWN   = "Ë",
                               WHITE_FIELD_WHITE_KNIGHT = "Â", BLACK_FIELD_WHITE_KNIGHT = "Ã",
                               WHITE_FIELD_WHITE_BISHOP = "À", BLACK_FIELD_WHITE_BISHOP = "Á",
                               WHITE_FIELD_WHITE_ROOK   = "Ä", BLACK_FIELD_WHITE_ROOK   = "Å",
                               WHITE_FIELD_WHITE_QUEEN  = "Æ", BLACK_FIELD_WHITE_QUEEN  = "Ç",
                               WHITE_FIELD_WHITE_KING   = "È", BLACK_FIELD_WHITE_KING   = "É";

    public static final String WHITE_FIELD_EMPTY_SQUARE = "'", BLACK_FIELD_EMPTY_SQUARE = "#";

    // elementi non differenziati per campo -- per i bottoni
    // i pezzi neri si ottengono con UPPERCASE
    public static final String WHITE_PAWN   = "i",
                               WHITE_KNIGHT = "k",
                               WHITE_BISHOP = "j",
                               WHITE_ROOK   = "l",
                               WHITE_QUEEN  = "m",
                               WHITE_KING   = "n";

    public static final String EMPTY_SQUARE = " ";

}
