package model.elements;

public abstract class IntMoves {

    /*
     *     function;                     1-5                        =    .--                        0->7
     *     fromSquare;     // nullable   0-63          + null bit   =   ..----+                     0->63
     *     toSquare;       // nullable   0-63          + null bit   =   ..----+                     0->63
     *     piece;          // nullable   1-6       + s + null bit   =  ...--                        0->7
     *     targetPiece;    // nullable   1-6       + s + null bit   =  ...--                        0->7
     *     promotionPiece; // nullable   1-6       + s + null bit   =  ...--                        0->7
     */

    public static final int
        FUNCTION_MASK            = 0x00000007, // 00000000.00000000.00000000.00000111
        FROM_SQUARE_MASK         = 0x000001F8, // 00000000.00000000.00000001.11111000
        FROM_SQUARE_EXISTS_MASK  = 0x00000200, // 00000000.00000000.00000010.00000000
        TO_SQUARE_MASK           = 0x0000FC00, // 00000000.00000000.11111100.00000000
        TO_SQUARE_EXISTS_MASK    = 0x00010000, // 00000000.00000001.00000000.00000000
        PIECE_ROLE_MASK          = 0x000E0000, // 00000000.00001110.00000000.00000000
        PIECE_NEGA_MASK          = 0x00100000, // 00000000.00010000.00000000.00000000
        PIECE_EXISTS_MASK        = 0x00200000, // 00000000.00100000.00000000.00000000
        TARGET_PIECE_ROLE_MASK   = 0x01C00000, // 00000001.11000000.00000000.00000000
        TARGET_PIECE_NEGA_MASK   = 0x02000000, // 00000010.00000000.00000000.00000000
        TARGET_PIECE_EXISTS_MASK = 0x04000000, // 00000100.00000000.00000000.00000000
        PROM_PIECE_ROLE_MASK     = 0x38000000, // 00111000.00000000.00000000.00000000
        PROM_PIECE_NEGA_MASK     = 0x40000000, // 01000000.00000000.00000000.00000000
        PROM_PIECE_EXISTS_MASK   = 0x80000000; // 10000000.00000000.00000000.00000000

    public static final byte
                                        // .---+----1----+----2----+----3--
                                        // 33222222222211111111110000000000
                                        // 10987654321098765432109876543210
                                        // ..  ...  ...  ..     ..     .  .
        FUNCTION_IX            =  0,    // 00000000000000000000000000000111
        FROM_SQUARE_IX         =  3,    // 00000000000000000000000111111000
        FROM_SQUARE_EXISTS_IX  =  9,    // 00000000000000000000001000000000
        TO_SQUARE_IX           = 10,    // 00000000000000001111110000000000
        TO_SQUARE_EXISTS_IX    = 16,    // 00000000000000010000000000000000
        PIECE_ROLE_IX          = 17,    // 00000000000011100000000000000000
        PIECE_NEGA_IX          = 20,    // 00000000000100000000000000000000
        PIECE_EXISTS_IX        = 21,    // 00000000001000000000000000000000
        TARGET_PIECE_ROLE_IX   = 22,    // 00000001110000000000000000000000
        TARGET_PIECE_NEGA_IX   = 25,    // 00000010000000000000000000000000
        TARGET_PIECE_EXISTS_IX = 26,    // 00000100000000000000000000000000
        PROM_PIECE_ROLE_IX     = 27,    // 00111000000000000000000000000000
        PROM_PIECE_NEGA_IX     = 30,    // 01000000000000000000000000000000
        PROM_PIECE_EXISTS_IX   = 31;    // 10000000000000000000000000000000

}
