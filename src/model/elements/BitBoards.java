package model.elements;

public abstract class BitBoards {

    // base numerica utilizzata -- per parseUnsignedLong/toUnsignedString
    public static final byte BINARY_RADIX = 2;

    //                                                  h1....a1 h2....a2 h3....a3 h4....a4 h5....a5 h6....a6 h7....a7 h8....a8

    // empty bitBoard
    public static final long
            EMPTY              = 0x0000000000000000L; // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.00000000

    // calcolo bitBoard a partire da una casa
    public static final long
            ONE                = 0x0000000000000001L; // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.00000001

    // scacchiera
    public static final long

            FILE_A             = 0x0101010101010101L, // 00000001.00000001.00000001.00000001.00000001.00000001.00000001.00000001
            FILE_B             = 0x0202020202020202L, // 00000010.00000010.00000010.00000010.00000010.00000010.00000010.00000010
            FILE_C             = 0x0404040404040404L, // 00000100.00000100.00000100.00000100.00000100.00000100.00000100.00000100
            FILE_D             = 0x0808080808080808L, // 00001000.00001000.00001000.00001000.00001000.00001000.00001000.00001000
            FILE_E             = 0x1010101010101010L, // 00010000.00010000.00010000.00010000.00010000.00010000.00010000.00010000
            FILE_F             = 0x2020202020202020L, // 00100000.00100000.00100000.00100000.00100000.00100000.00100000.00100000
            FILE_G             = 0x4040404040404040L, // 01000000.01000000.01000000.01000000.01000000.01000000.01000000.01000000
            FILE_H             = 0x8080808080808080L, // 10000000.10000000.10000000.10000000.10000000.10000000.10000000.10000000
            RANK_1             = 0xFF00000000000000L, // 11111111.00000000.00000000.00000000.00000000.00000000.00000000.00000000
            RANK_2             = 0x00FF000000000000L, // 00000000.11111111.00000000.00000000.00000000.00000000.00000000.00000000
            RANK_3             = 0x0000FF0000000000L, // 00000000.00000000.11111111.00000000.00000000.00000000.00000000.00000000
            RANK_4             = 0x000000FF00000000L, // 00000000.00000000.00000000.11111111.00000000.00000000.00000000.00000000
            RANK_5             = 0x00000000FF000000L, // 00000000.00000000.00000000.00000000.11111111.00000000.00000000.00000000
            RANK_6             = 0x0000000000FF0000L, // 00000000.00000000.00000000.00000000.00000000.11111111.00000000.00000000
            RANK_7             = 0x000000000000FF00L, // 00000000.00000000.00000000.00000000.00000000.00000000.11111111.00000000
            RANK_8             = 0x00000000000000FFL, // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.11111111

            DIAG_1             = 0x0000000000000001L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.00000001
            DIAG_2             = 0x0000000000000102L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000001.00000010
            DIAG_3             = 0x0000000000010204L, // 00000000.00000000.00000000.00000000.00000000.00000001.00000010.00000100
            DIAG_4             = 0x0000000001020408L, // 00000000.00000000.00000000.00000000.00000001.00000010.00000100.00001000
            DIAG_5             = 0x0000000102040810L, // 00000000.00000000.00000000.00000001.00000010.00000100.00001000.00010000
            DIAG_6             = 0x0000010204081020L, // 00000000.00000000.00000001.00000010.00000100.00001000.00010000.00100000
            DIAG_7             = 0x0001020408102040L, // 00000000.00000001.00000010.00000100.00001000.00010000.00100000.01000000
            DIAG_8             = 0x0102040810204080L, // 00000001.00000010.00000100.00001000.00010000.00100000.01000000.10000000
            DIAG_9             = 0x0204081020408000L, // 00000010.00000100.00001000.00010000.00100000.01000000.10000000.00000000
            DIAG_10            = 0x0408102040800000L, // 00000100.00001000.00010000.00100000.01000000.10000000.00000000.00000000
            DIAG_11            = 0x0810204080000000L, // 00001000.00010000.00100000.01000000.10000000.00000000.00000000.00000000
            DIAG_12            = 0x1020408000000000L, // 00010000.00100000.01000000.10000000.00000000.00000000.00000000.00000000
            DIAG_13            = 0x2040800000000000L, // 00100000.01000000.10000000.00000000.00000000.00000000.00000000.00000000
            DIAG_14            = 0x4080000000000000L, // 01000000.10000000.00000000.00000000.00000000.00000000.00000000.00000000
            DIAG_15            = 0x8000000000000000L, // 10000000.00000000.00000000.00000000.00000000.00000000.00000000.00000000

            ANTI_1             = 0x0100000000000000L, // 00000001.00000000.00000000.00000000.00000000.00000000.00000000.00000000
            ANTI_2             = 0x0201000000000000L, // 00000010.00000001.00000000.00000000.00000000.00000000.00000000.00000000
            ANTI_3             = 0x0402010000000000L, // 00000100.00000010.00000001.00000000.00000000.00000000.00000000.00000000
            ANTI_4             = 0x0804020100000000L, // 00001000.00000100.00000010.00000001.00000000.00000000.00000000.00000000
            ANTI_5             = 0x1008040201000000L, // 00010000.00001000.00000100.00000010.00000001.00000000.00000000.00000000
            ANTI_6             = 0x2010080402010000L, // 00100000.00010000.00001000.00000100.00000010.00000001.00000000.00000000
            ANTI_7             = 0x4020100804020100L, // 01000000.00100000.00010000.00001000.00000100.00000010.00000001.00000000
            ANTI_8             = 0x8040201008040201L, // 10000000.01000000.00100000.00010000.00001000.00000100.00000010.00000001
            ANTI_9             = 0x0080402010080402L, // 00000000.10000000.01000000.00100000.00010000.00001000.00000100.00000010
            ANTI_10            = 0x0000804020100804L, // 00000000.00000000.10000000.01000000.00100000.00010000.00001000.00000100
            ANTI_11            = 0x0000008040201008L, // 00000000.00000000.00000000.10000000.01000000.00100000.00010000.00001000
            ANTI_12            = 0x0000000080402010L, // 00000000.00000000.00000000.00000000.10000000.01000000.00100000.00010000
            ANTI_13            = 0x0000000000804020L, // 00000000.00000000.00000000.00000000.00000000.10000000.01000000.00100000
            ANTI_14            = 0x0000000000008040L, // 00000000.00000000.00000000.00000000.00000000.00000000.10000000.01000000
            ANTI_15            = 0x0000000000000080L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.10000000

            FILE_AB            = 0x0303030303030303L, // 00000011.00000011.00000011.00000011.00000011.00000011.00000011.00000011
            FILE_GH            = 0xC0C0C0C0C0C0C0C0L, // 11000000.11000000.11000000.11000000.11000000.11000000.11000000.11000000

            CENTRE             = 0x0000001818000000L, // 00000000.00000000.00000000.00011000.00011000.00000000.00000000.00000000
            CENTRE_RING_1      = 0x00003C24243C0000L, // 00000000.00000000.00111100.00100100.00100100.00111100.00000000.00000000
            CENTRE_RING_2      = 0x007E424242427E00L, // 00000000.01111110.01000010.01000010.01000010.01000010.01111110.00000000

            KING_SIDE          = 0xF0F0F0F0F0F0F0F0L, // 11110000.11110000.11110000.11110000.11110000.11110000.11110000.11110000
            QUEEN_SIDE         = 0x0F0F0F0F0F0F0F0FL, // 00001111.00001111.00001111.00001111.00001111.00001111.00001111.00001111

            WHITE_FIELDS       = 0xAA55AA55AA55AA55L; // 10101010.01010101.10101010.01010101.10101010.01010101.10101010.01010101

    // mosse cavallo
    public static final long
            KNIGHT_D5_SPAN     = 0x0000142200221400L; // 00000000.00000000.00010100.00100010.0000|000.00100010.00010100.00000000

    // mosse re
    public static final long
            KING_D5_SPAN       = 0x0000001C141C0000L; // 00000000.00000000.00000000.00011100.0001|100.00011100.00000000.00000000

    // arrocchi: case richieste libere da pezzi
    public static final long
            W_S_CG_PATH        = 0x6000000000000000L, // 01100000.00000000.00000000.00000000.00000000.00000000.00000000.00000000
            W_L_CG_PATH        = 0x0E00000000000000L, // 00001110.00000000.00000000.00000000.00000000.00000000.00000000.00000000
            B_S_CG_PATH        = 0x0000000000000060L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.01100000
            B_L_CG_PATH        = 0x000000000000000EL; // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.00001110

    // arrocchi: case percorse dal re, richieste libere da minacce
    public static final long
            W_S_CG_KING_PATH   = 0x7000000000000000L, // 01110000.00000000.00000000.00000000.00000000.00000000.00000000.00000000
            W_L_CG_KING_PATH   = 0x1C00000000000000L, // 00011100.00000000.00000000.00000000.00000000.00000000.00000000.00000000
            B_S_CG_KING_PATH   = 0x0000000000000070L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.01110000
            B_L_CG_KING_PATH   = 0x000000000000001CL; // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.00011100

    // arrocchi Bianco: SQUARE_A1 => SQUARE_H1
    public static final long
            SQUARE_A1          = 0x0100000000000000L, // 00000001.00000000.00000000.00000000.00000000.00000000.00000000.00000000
            SQUARE_B1          = 0x0200000000000000L, // 00000010.00000000.00000000.00000000.00000000.00000000.00000000.00000000
            SQUARE_C1          = 0x0400000000000000L, // 00000100.00000000.00000000.00000000.00000000.00000000.00000000.00000000
            SQUARE_D1          = 0x0800000000000000L, // 00001000.00000000.00000000.00000000.00000000.00000000.00000000.00000000
            SQUARE_E1          = 0x1000000000000000L, // 00010000.00000000.00000000.00000000.00000000.00000000.00000000.00000000
            SQUARE_F1          = 0x2000000000000000L, // 00100000.00000000.00000000.00000000.00000000.00000000.00000000.00000000
            SQUARE_G1          = 0x4000000000000000L, // 01000000.00000000.00000000.00000000.00000000.00000000.00000000.00000000
            SQUARE_H1          = 0x8000000000000000L; // 10000000.00000000.00000000.00000000.00000000.00000000.00000000.00000000

    // arrocchi Nero: SQUARE_A8 => SQUARE_H8
    public static final long
            SQUARE_A8          = 0x0000000000000001L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.00000001
            SQUARE_B8          = 0x0000000000000002L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.00000010
            SQUARE_C8          = 0x0000000000000004L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.00000100
            SQUARE_D8          = 0x0000000000000008L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.00001000
            SQUARE_E8          = 0x0000000000000010L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.00010000
            SQUARE_F8          = 0x0000000000000020L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.00100000
            SQUARE_G8          = 0x0000000000000040L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.01000000
            SQUARE_H8          = 0x0000000000000080L; // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.10000000

    // FILE_A => FILE_H
    public static final long[]
            FILE_MASKS = {
                                 0x0101010101010101L, // 00000001.00000001.00000001.00000001.00000001.00000001.00000001.00000001
                                 0x0202020202020202L, // 00000010.00000010.00000010.00000010.00000010.00000010.00000010.00000010
                                 0x0404040404040404L, // 00000100.00000100.00000100.00000100.00000100.00000100.00000100.00000100
                                 0x0808080808080808L, // 00001000.00001000.00001000.00001000.00001000.00001000.00001000.00001000
                                 0x1010101010101010L, // 00010000.00010000.00010000.00010000.00010000.00010000.00010000.00010000
                                 0x2020202020202020L, // 00100000.00100000.00100000.00100000.00100000.00100000.00100000.00100000
                                 0x4040404040404040L, // 01000000.01000000.01000000.01000000.01000000.01000000.01000000.01000000
                                 0x8080808080808080L  // 10000000.10000000.10000000.10000000.10000000.10000000.10000000.10000000
    };

    // mosse in verticale - lookup table
    public static final long[]
            FILE_MASKS_LT = computeFileMasksLT();

    // RANK_1 => RANK_8
    public static final long[]
            RANK_MASKS = {
                                 0x00000000000000FFL, // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.11111111
                                 0x000000000000FF00L, // 00000000.00000000.00000000.00000000.00000000.00000000.11111111.00000000
                                 0x0000000000FF0000L, // 00000000.00000000.00000000.00000000.00000000.11111111.00000000.00000000
                                 0x00000000FF000000L, // 00000000.00000000.00000000.00000000.11111111.00000000.00000000.00000000
                                 0x000000FF00000000L, // 00000000.00000000.00000000.11111111.00000000.00000000.00000000.00000000
                                 0x0000FF0000000000L, // 00000000.00000000.11111111.00000000.00000000.00000000.00000000.00000000
                                 0x00FF000000000000L, // 00000000.11111111.00000000.00000000.00000000.00000000.00000000.00000000
                                 0xFF00000000000000L  // 11111111.00000000.00000000.00000000.00000000.00000000.00000000.00000000
    };

    // mosse in orizzontale - lookup table
    public static final long[]
            RANK_MASKS_LT = computeRankMasksLT();

    // SQUARE_A1 => SQUARE_H8
    public static final long[]
            DIAG_MASKS = {
                                 0x0000000000000001L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.00000001
                                 0x0000000000000102L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000001.00000010
                                 0x0000000000010204L, // 00000000.00000000.00000000.00000000.00000000.00000001.00000010.00000100
                                 0x0000000001020408L, // 00000000.00000000.00000000.00000000.00000001.00000010.00000100.00001000
                                 0x0000000102040810L, // 00000000.00000000.00000000.00000001.00000010.00000100.00001000.00010000
                                 0x0000010204081020L, // 00000000.00000000.00000001.00000010.00000100.00001000.00010000.00100000
                                 0x0001020408102040L, // 00000000.00000001.00000010.00000100.00001000.00010000.00100000.01000000
                                 0x0102040810204080L, // 00000001.00000010.00000100.00001000.00010000.00100000.01000000.10000000
                                 0x0204081020408000L, // 00000010.00000100.00001000.00010000.00100000.01000000.10000000.00000000
                                 0x0408102040800000L, // 00000100.00001000.00010000.00100000.01000000.10000000.00000000.00000000
                                 0x0810204080000000L, // 00001000.00010000.00100000.01000000.10000000.00000000.00000000.00000000
                                 0x1020408000000000L, // 00010000.00100000.01000000.10000000.00000000.00000000.00000000.00000000
                                 0x2040800000000000L, // 00100000.01000000.10000000.00000000.00000000.00000000.00000000.00000000
                                 0x4080000000000000L, // 01000000.10000000.00000000.00000000.00000000.00000000.00000000.00000000
                                 0x8000000000000000L  // 10000000.00000000.00000000.00000000.00000000.00000000.00000000.00000000
    };

    // mosse in diagonale - lookup table
    public static final long[]
            DIAG_MASKS_LT = computeDiagMasksLT();

    // SQUARE_H1 => SQUARE_A8
    public static final long[]
            ANTI_MASKS = {
                                 0x0000000000000080L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.10000000
                                 0x0000000000008040L, // 00000000.00000000.00000000.00000000.00000000.00000000.10000000.01000000
                                 0x0000000000804020L, // 00000000.00000000.00000000.00000000.00000000.10000000.01000000.00100000
                                 0x0000000080402010L, // 00000000.00000000.00000000.00000000.10000000.01000000.00100000.00010000
                                 0x0000008040201008L, // 00000000.00000000.00000000.10000000.01000000.00100000.00010000.00001000
                                 0x0000804020100804L, // 00000000.00000000.10000000.01000000.00100000.00010000.00001000.00000100
                                 0x0080402010080402L, // 00000000.10000000.01000000.00100000.00010000.00001000.00000100.00000010
                                 0x8040201008040201L, // 10000000.01000000.00100000.00010000.00001000.00000100.00000010.00000001
                                 0x4020100804020100L, // 01000000.00100000.00010000.00001000.00000100.00000010.00000001.00000000
                                 0x2010080402010000L, // 00100000.00010000.00001000.00000100.00000010.00000001.00000000.00000000
                                 0x1008040201000000L, // 00010000.00001000.00000100.00000010.00000001.00000000.00000000.00000000
                                 0x0804020100000000L, // 00001000.00000100.00000010.00000001.00000000.00000000.00000000.00000000
                                 0x0402010000000000L, // 00000100.00000010.00000001.00000000.00000000.00000000.00000000.00000000
                                 0x0201000000000000L, // 00000010.00000001.00000000.00000000.00000000.00000000.00000000.00000000
                                 0x0100000000000000L  // 00000001.00000000.00000000.00000000.00000000.00000000.00000000.00000000
    };

    // mosse in diagonale - lookup table
    public static final long[]
            ANTI_MASKS_LT = computeAntiMasksLT();

    // mosse cavallo - lookup table
    public static final long[]
            KNIGHT_MOVES_LT = computeKnightMovesLT();

    // mosse re - lookup table
    public static final long[]
            KING_MOVES_LT = computeKingMovesLT();

    // white pawn blocker columns from b2
    public static final long
            WP_BLOCK_COLS_B2   = 0x0000070707070707L; // 00000000.00000000.00000111.00000111.00000111.00000111.00000111.00000111

    // white pawn blocker columns - lookup table
    public static final long[]
            WP_BLOCK_C_LT = computeWPBlockerColsLT();

    // black pawn blocker columns from b2
    public static final long
            BP_BLOCK_COLS_B2   = 0x0707070707070000L; // 00000111.00000111.00000111.00000111.00000111.00000111.00000000.00000000

    // black pawn blocker columns - lookup table
    public static final long[]
            BP_BLOCK_C_LT = computeBPBlockerColsLT();

    // mosse pedoni e slider -- è definita numericamente solo la direzione: il verso è dato dal tipo di bit shift (>>> o <<)
    public static final byte
            VERTICAL_STEP = 8, VERTICAL_DOUBLE_STEP = 16, DIAGONAL_STEP = 7, ANTIGONAL_STEP = 9;

    // white pawn shields
    public static final long[]
            WK_PSHIELD_BB_LT = {
                                 0x0003000000000000L, // 00000000.00000011.00000000.00000000.00000000.00000000.00000000.00000000
                                 0x0007000000000000L, // 00000000.00000111.00000000.00000000.00000000.00000000.00000000.00000000
                                 0x000E000000000000L, // 00000000.00001110.00000000.00000000.00000000.00000000.00000000.00000000
                                 0x001C000000000000L, // 00000000.00011100.00000000.00000000.00000000.00000000.00000000.00000000
                                 0x0038000000000000L, // 00000000.00111000.00000000.00000000.00000000.00000000.00000000.00000000
                                 0x0070000000000000L, // 00000000.01110000.00000000.00000000.00000000.00000000.00000000.00000000
                                 0x00E0000000000000L, // 00000000.11100000.00000000.00000000.00000000.00000000.00000000.00000000
                                 0x00C0000000000000L, // 00000000.11000000.00000000.00000000.00000000.00000000.00000000.00000000
    };

    // black pawn shields
    public static final long[]
            BK_PSHIELD_BB_LT = {
                                 0x0000000000000300L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000011.00000000
                                 0x0000000000000700L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000111.00000000
                                 0x0000000000000E00L, // 00000000.00000000.00000000.00000000.00000000.00000000.00001110.00000000
                                 0x0000000000001C00L, // 00000000.00000000.00000000.00000000.00000000.00000000.00011100.00000000
                                 0x0000000000003800L, // 00000000.00000000.00000000.00000000.00000000.00000000.00111000.00000000
                                 0x0000000000007000L, // 00000000.00000000.00000000.00000000.00000000.00000000.01110000.00000000
                                 0x000000000000E000L, // 00000000.00000000.00000000.00000000.00000000.00000000.11100000.00000000
                                 0x000000000000C000L, // 00000000.00000000.00000000.00000000.00000000.00000000.11000000.00000000
    };

    public static final long
            KRING1_D5          = 0x0000001C141C0000L; // 00000000.00000000.00000000.00011100.00010100.00011100.00000000.00000000

    public static final long
            KRING2_D5          = 0x00003E2222223E00L; // 00000000.00000000.00111110.00100010.00100010.00100010.00111110.00000000

    public static final long[]
            KRING1_LT          = computeKRing1LT();

    public static final long[]
            KRING2_LT          = computeKRing2LT();

    private static long[] computeFileMasksLT() {

        final long[] fileMasksLt = new long[Squares.SQUARES_NUMBER];

        byte file;

        for (byte square = 0; square <= Squares.SQUARES_NUMBER - 1; ++square) {
            file = (byte) (/*Squares.file()*/ square % Squares.EDGE);
            fileMasksLt[square] = FILE_MASKS[file];
        }

        return fileMasksLt;
    }

    private static long[] computeRankMasksLT() {

        final long[] rankMasksLt = new long[Squares.SQUARES_NUMBER];

        byte rank;

        for (byte square = 0; square <= Squares.SQUARES_NUMBER - 1; ++square) {
            rank = (byte) (/*Squares.rank()*/ square / Squares.EDGE);
            rankMasksLt[square] = RANK_MASKS[rank];
        }

        return rankMasksLt;
    }

    private static long[] computeDiagMasksLT() {

        final long[] diagMasksLt = new long[Squares.SQUARES_NUMBER];

        byte file, rank;

        for (byte square = 0; square <= Squares.SQUARES_NUMBER - 1; ++square) {
            file = (byte) (/*Squares.file()*/ square % Squares.EDGE);
            rank = (byte) (/*Squares.rank()*/ square / Squares.EDGE);
            diagMasksLt[square] = DIAG_MASKS[file + rank];
        }

        return diagMasksLt;
    }

    private static long[] computeAntiMasksLT() {

        final long[] antiMasksLt = new long[Squares.SQUARES_NUMBER];

        byte file, rank;

        for (byte square = 0; square <= Squares.SQUARES_NUMBER - 1; ++square) {
            file = (byte) (/*Squares.file()*/ square % Squares.EDGE);
            rank = (byte) (/*Squares.rank()*/ square / Squares.EDGE);
            antiMasksLt[square] = ANTI_MASKS[rank - file + 7];
        }

        return antiMasksLt;
    }

    private static long[] computeKnightMovesLT() {

        final long[] knigthsMovesLT = new long[Squares.SQUARES_NUMBER];

        long toSquaresBb;

        for (byte square = 0; square <= Squares.SQUARES_NUMBER - 1; ++square) {
            if (square <= Squares.SQUARE_D5) {
                toSquaresBb = BitBoards.KNIGHT_D5_SPAN >>> (Squares.SQUARE_D5 - square);
            } else {
                toSquaresBb = BitBoards.KNIGHT_D5_SPAN << (square - Squares.SQUARE_D5);
            }
            if (/*Squares.file()*/square % Squares.EDGE <= Squares.FILE_D) {
                toSquaresBb &= ~BitBoards.FILE_GH;
            } else {
                toSquaresBb &= ~BitBoards.FILE_AB;
            }
            knigthsMovesLT[square] = toSquaresBb;
        }

        return knigthsMovesLT;
    }

    private static long[] computeKingMovesLT() {

        final long[] kingMovesLT = new long[Squares.SQUARES_NUMBER];

        long toSquaresBb;

        for (byte square = 0; square <= Squares.SQUARES_NUMBER - 1; ++square) {
            if (square <= Squares.SQUARE_D5) {
                toSquaresBb = BitBoards.KING_D5_SPAN >>> (Squares.SQUARE_D5 - square);
            } else {
                toSquaresBb = BitBoards.KING_D5_SPAN << (square - Squares.SQUARE_D5);
            }
            if (/*Squares.file()*/square % Squares.EDGE <= Squares.FILE_D) {
                toSquaresBb &= ~BitBoards.FILE_H;
            } else {
                toSquaresBb &= ~BitBoards.FILE_A;
            }
            kingMovesLT[square] = toSquaresBb;
        }

        return kingMovesLT;
    }

    private static long[] computeWPBlockerColsLT() {

        final long[] wpBlockerColsLT = new long[Squares.SQUARES_NUMBER];

        long toSquaresBb;

        for (byte square = 0; square <= Squares.SQUARES_NUMBER - 1; ++square) {
        	if (/*Squares.rank()*/square / Squares.EDGE == Squares.RANK_1) {
        		wpBlockerColsLT[square] = BitBoards.EMPTY;
        	} else {
                if (square <= Squares.SQUARE_B2) {
                    toSquaresBb = BitBoards.WP_BLOCK_COLS_B2 >>> (Squares.SQUARE_B2 - square) & ~BitBoards.RANK_8;
                } else {
                    toSquaresBb = BitBoards.WP_BLOCK_COLS_B2 << (square - Squares.SQUARE_B2) & ~BitBoards.RANK_8;
                }
                if (/*Squares.file()*/square % Squares.EDGE <= Squares.FILE_D) {
                    toSquaresBb &= ~BitBoards.FILE_H;
                } else {
                    toSquaresBb &= ~BitBoards.FILE_A;
                }
                wpBlockerColsLT[square] = toSquaresBb;
        	}
        }

        return wpBlockerColsLT;
    }

    private static long[] computeBPBlockerColsLT() {

        final long[] bpBlockerColsLT = new long[Squares.SQUARES_NUMBER];

        long toSquaresBb;

        for (byte square = 0; square <= Squares.SQUARES_NUMBER - 1; ++square) {
        	if (/*Squares.rank()*/square / Squares.EDGE == Squares.RANK_8) {
        		bpBlockerColsLT[square] = BitBoards.EMPTY;
        	} else {
                if (square <= Squares.SQUARE_B7) {
                    toSquaresBb = BitBoards.BP_BLOCK_COLS_B2 >>> (Squares.SQUARE_B7 - square) & ~BitBoards.RANK_1;
                } else {
                    toSquaresBb = BitBoards.BP_BLOCK_COLS_B2 << (square - Squares.SQUARE_B7) & ~BitBoards.RANK_1;
                }
                if (/*Squares.file()*/square % Squares.EDGE <= Squares.FILE_D) {
                    toSquaresBb &= ~BitBoards.FILE_H;
                } else {
                    toSquaresBb &= ~BitBoards.FILE_A;
                }
                bpBlockerColsLT[square] = toSquaresBb;
        	}
        }

        return bpBlockerColsLT;
    }

    private static long[] computeKRing1LT() {

        final long[] kRing1LT = new long[Squares.SQUARES_NUMBER];

        long toSquaresBb;

        for (byte square = 0; square <= Squares.SQUARES_NUMBER - 1; ++square) {
            if (square <= Squares.SQUARE_D5) {
                toSquaresBb = BitBoards.KRING1_D5 >>> (Squares.SQUARE_D5 - square);
            } else {
                toSquaresBb = BitBoards.KRING1_D5 << (square - Squares.SQUARE_D5);
            }
            if (/*Squares.file()*/square % Squares.EDGE <= Squares.FILE_D) {
                toSquaresBb &= ~BitBoards.FILE_GH;
            } else {
                toSquaresBb &= ~BitBoards.FILE_AB;
            }
            kRing1LT[square] = toSquaresBb;
        }

        return kRing1LT;
    }

    private static long[] computeKRing2LT() {

        final long[] kRing2LT = new long[Squares.SQUARES_NUMBER];

        long toSquaresBb;

        for (byte square = 0; square <= Squares.SQUARES_NUMBER - 1; ++square) {
            if (square <= Squares.SQUARE_D5) {
                toSquaresBb = BitBoards.KRING2_D5 >>> (Squares.SQUARE_D5 - square);
            } else {
                toSquaresBb = BitBoards.KRING2_D5 << (square - Squares.SQUARE_D5);
            }
            if (/*Squares.file()*/square % Squares.EDGE <= Squares.FILE_D) {
                toSquaresBb &= ~BitBoards.FILE_GH;
            } else {
                toSquaresBb &= ~BitBoards.FILE_AB;
            }
            kRing2LT[square] = toSquaresBb;
        }

        return kRing2LT;
    }

    public static long horizontalVerticalMoves(byte square, long squareBb, long sideBlockersBb) {

        final long horizontalMoves, verticalMoves;

        horizontalMoves = (sideBlockersBb - 2 * squareBb)
                ^ Long.reverse(Long.reverse(sideBlockersBb) - 2 * Long.reverse(squareBb));

        verticalMoves = ((sideBlockersBb & BitBoards.FILE_MASKS_LT[square]) - (2 * squareBb))
                ^ Long.reverse(Long.reverse(sideBlockersBb & BitBoards.FILE_MASKS_LT[square]) - (2 * Long.reverse(squareBb)));

        return (horizontalMoves & BitBoards.RANK_MASKS_LT[square])
                | (verticalMoves & BitBoards.FILE_MASKS_LT[square]);
    }

    public static long diagonalAntigonalMoves(byte square, long squareBb, long sideBlockersBb) {

        final long diagonalMoves, antigonalMoves;

        diagonalMoves = ((sideBlockersBb & DIAG_MASKS_LT[square]) - (2 * squareBb))
                ^ Long.reverse(Long.reverse(sideBlockersBb & DIAG_MASKS_LT[square]) - (2 * Long.reverse(squareBb)));

        antigonalMoves = ((sideBlockersBb & ANTI_MASKS_LT[square]) - (2 * squareBb))
                ^ Long.reverse(Long.reverse(sideBlockersBb & ANTI_MASKS_LT[square]) - (2 * Long.reverse(squareBb)));

        return (diagonalMoves & DIAG_MASKS_LT[square])
                | (antigonalMoves & ANTI_MASKS_LT[square]);
    }

    public static String toString(long bitBoard) {

        final StringBuilder bitBoardSb, aCapoBitBoardSb;

        bitBoardSb = new StringBuilder();
        bitBoardSb.append(new String(new char[Long.numberOfLeadingZeros(bitBoard)]).replace("\0", "0"));
        bitBoardSb.append(Long.toUnsignedString(bitBoard, BINARY_RADIX));

//        aCapoBitBoardSb = new StringBuilder("\n");
        aCapoBitBoardSb = new StringBuilder();
        for (byte r = 7; r >= 0; --r) {
            aCapoBitBoardSb.append(" ");
            for (byte f = 7; f >= 0; --f) {
                // es.: ultimo rank (7) = da 8*7+0=56 a 8*7+7+1=64 -- +1 fisso per metodo
                aCapoBitBoardSb.append(bitBoardSb.substring(8 * r + f, 8 * r + f + 1));
                aCapoBitBoardSb.append(" ");
            }
            if (r > 0) {
                aCapoBitBoardSb.append("\n");
            }
        }

        return aCapoBitBoardSb.toString()
        					      .replaceAll("0", "-")
        					          .replaceAll("1", "x");
    }

}
