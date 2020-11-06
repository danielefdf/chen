package model.elements;

import java.security.SecureRandom;

public abstract class Zobrist {

    /*
     * costanti per:
     *  - pezzo/casa
     *  - file della casa en passant
     *  - possibilità di arrocco
     *  - colore al tratto -- inizializzato per nero al tratto
     */

    public static long[][] BOARD_RANDOMS_LIST = new long[12][64];
    public static long[] EN_PASSANT_RANDOMS_LIST = new long[8];
    public static long[] CASTLINGS_RANDOMS_LIST = new long[4];
    public static long BLACK_PLAYER_RANDOM;

    public static final byte WHITE_PAWN_IX   = 0;
    public static final byte WHITE_KNIGHT_IX = 1;
    public static final byte WHITE_BISHOP_IX = 2;
    public static final byte WHITE_ROOK_IX   = 3;
    public static final byte WHITE_QUEEN_IX  = 4;
    public static final byte WHITE_KING_IX   = 5;

    public static final byte BLACK_PAWN_IX   = 6;
    public static final byte BLACK_KNIGHT_IX = 7;
    public static final byte BLACK_BISHOP_IX = 8;
    public static final byte BLACK_ROOK_IX   = 9;
    public static final byte BLACK_QUEEN_IX  = 10;
    public static final byte BLACK_KING_IX   = 11;

    public static final byte WHITE_SHORT_CG_IX = 0;
    public static final byte WHITE_LONG_CG_IX  = 1;
    public static final byte BLACK_SHORT_CG_IX = 2;
    public static final byte BLACK_LONG_CG_IX  = 3;

    public static long randomLong() {

//        return Math.random(); // 48 bit only: KO
        return new SecureRandom().nextLong();
    }

    public static void setZobristConstants() {

        setRandomConstants();

    }

    private static void setRandomConstants() {

        for (byte piece = 0; piece < 12; ++piece) {
            for (byte square = 0; square < 64; ++square) {
                BOARD_RANDOMS_LIST[piece][square] = randomLong();
            }
        }

        BLACK_PLAYER_RANDOM = randomLong();

        for (byte castlingFlag = 0; castlingFlag < 4; ++castlingFlag) {
            CASTLINGS_RANDOMS_LIST[castlingFlag] = randomLong();
        }

        for (byte column = 0; column < 8; ++column) {
            EN_PASSANT_RANDOMS_LIST[column] = randomLong();
        }

    }

}
