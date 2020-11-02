package model.notations;

/**
 * SAN: Short Algebraic Notation
 *
 * Una mossa è individuata da:
 *  - pezzo (dipendente dalla nazione, ma qui inteso in inglese)
 *  - tipo movimento
 *  - casa di partenza
 *  - [pezzo nel quale si promuove], se esiste
 *
 * Ad es.: 1.d4 d5 2.c4 -- Queen's Gambit
 */
public abstract class San {

    public static final String FILE_A = "a", FILE_B = "b", FILE_C = "c", FILE_D = "d",
                               FILE_E = "e", FILE_F = "f", FILE_G = "g", FILE_H = "h";

    public static final String RANK_1 = "1", RANK_2 = "2", RANK_3 = "3", RANK_4 = "4",
                               RANK_5 = "5", RANK_6 = "6", RANK_7 = "7", RANK_8 = "8";

    public static final String ROLE_KING   = "K",
                               ROLE_QUEEN  = "Q",
                               ROLE_ROOK   = "R",
                               ROLE_BISHOP = "B",
                               ROLE_KNIGHT = "N",
                               ROLE_PAWN   = "";

    public static final String MOVEMENT       = "-",
                               CAPTURE        = "x",
                               EN_PASSANT     = " e.p.",
                               PROMOTION_TO   = "=",
                               SHORT_CASTLING = "O-O",
                               LONG_CASTLING  = "O-O-O";

}
