package model.notations;

/**
 * PAN: Pure Algebraic (Coordinate) Notation
 *
 * E' la notazione utilizzata nel protocollo UCI, descritta nelle specifiche
 * come Long Algebraic Notation, in modo leggermente scorretto.
 *
 * Una mossa è individuata, in notazione algebrica standard (SAN), da:
 *  - casa di partenza;
 *  - casa di destinazione;
 *  - [pezzo nel quale si promuove], se esiste.
 *
 * Ad es.: d2d4 d7d5 c2c4 -- Queen's Gambit
 */
public abstract class Pan {

    public static final String ROLE_QUEEN  = "q",
                               ROLE_ROOK   = "r",
                               ROLE_BISHOP = "b",
                               ROLE_KNIGHT = "n";

    public static final String WHITE_SHORT_CASTLING = "e1g1",
            				   WHITE_LONG_CASTLING  = "e1c1";

    public static final String BLACK_SHORT_CASTLING = "e8g8",
            				   BLACK_LONG_CASTLING  = "e8c8";

    public static final String NULL_MOVE = "0000";

}
