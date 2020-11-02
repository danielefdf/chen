package model.elements;

import model.notations.San;

/**
 * Consente di memorizzare tutti i dati di una mossa in un campo long.
 *
 * Deprecated. Dai test con perft la gestione con LongMoves è risultata più lenta della gestione con Move.
 */
@Deprecated
public abstract class LongMoves {

    public static final long NULL_MOVE = 0x0L;

    public static final Integer START_VALUE        = null;
    public static final int     MOVES_TO_MATE      = 100;
    public static final int     MOVE_STRING_LENGTH = 10;

    /*
     *     function;          1-5          0->7
     *     fromSquare;        0-63         0->63            nullable   -> bit
     *     toSquare;          0-63         0->63            nullable   -> bit
     *     piece;             1-6          0->7             nullable   -> 0
     *     targetPiece;       1-6          0->7             nullable   -> 0
     *     promotionPiece;    1-6          0->7             nullable   -> 0
     *     orderValue;        0-5920000    0->33000000++    nullable   -> bit
     *                          ^ vedi Engine -> parametri per move ordering
     */

    private static final int
                                    // .---+----1----+----2----+----3----+----4----+----5----+----6----
                                    // 6666555555555544444444443333333333222222222211111111110000000000
                                    // 3210987654321098765432109876543210987654321098765432109876543210
                                    //          .                        ..  ..  ..  ..     ..     .  .
//        FUNCTION_IX         =  0, // 0000000000000000000000000000000000000000000000000000000000000111
        FROM_SQUARE_IX        =  3, // 0000000000000000000000000000000000000000000000000000000111111000
        FROM_SQUARE_EXISTS_IX =  9, // 0000000000000000000000000000000000000000000000000000001000000000
        TO_SQUARE_IX          = 10, // 0000000000000000000000000000000000000000000000001111110000000000
        TO_SQUARE_EXISTS_IX   = 16, // 0000000000000000000000000000000000000000000000010000000000000000
        PIECE_ROLE_IX         = 17, // 0000000000000000000000000000000000000000000011100000000000000000
        PIECE_COLOR_IX        = 20, // 0000000000000000000000000000000000000000000100000000000000000000
        TARGET_PIECE_ROLE_IX  = 21, // 0000000000000000000000000000000000000000111000000000000000000000
        TARGET_PIECE_COLOR_IX = 24, // 0000000000000000000000000000000000000001000000000000000000000000
        PROM_PIECE_ROLE_IX    = 25, // 0000000000000000000000000000000000001110000000000000000000000000
        PROM_PIECE_COLOR_IX   = 28, // 0000000000000000000000000000000000010000000000000000000000000000
        ORDER_VALUE_IX        = 29, // 0000000000111111111111111111111111100000000000000000000000000000
        ORDER_VALUE_EXISTS_IX = 54; // 0000000001000000000000000000000000000000000000000000000000000000

    public static final long
        FUNCTION_MASK           = 0x0000000000000007L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000000.00000111
        FROM_SQUARE_MASK        = 0x00000000000001F8L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000001.11111000
        FROM_SQUARE_EXISTS_MASK = 0x0000000000000200L, // 00000000.00000000.00000000.00000000.00000000.00000000.00000010.00000000
        TO_SQUARE_MASK          = 0x000000000000FC00L, // 00000000.00000000.00000000.00000000.00000000.00000000.11111100.00000000
        TO_SQUARE_EXISTS_MASK   = 0x0000000000010000L, // 00000000.00000000.00000000.00000000.00000000.00000001.00000000.00000000
        PIECE_ROLE_MASK         = 0x00000000000E0000L, // 00000000.00000000.00000000.00000000.00000000.00001110.00000000.00000000
        PIECE_COLOR_MASK        = 0x0000000000100000L, // 00000000.00000000.00000000.00000000.00000000.00010000.00000000.00000000
        TARGET_PIECE_ROLE_MASK  = 0x0000000000E00000L, // 00000000.00000000.00000000.00000000.00000000.11100000.00000000.00000000
        TARGET_PIECE_COLOR_MASK = 0x0000000001000000L, // 00000000.00000000.00000000.00000000.00000001.00000000.00000000.00000000
        PROM_PIECE_ROLE_MASK    = 0x000000000E000000L, // 00000000.00000000.00000000.00000000.00001110.00000000.00000000.00000000
        PROM_PIECE_COLOR_MASK   = 0x0000000010000000L, // 00000000.00000000.00000000.00000000.00010000.00000000.00000000.00000000
        ORDER_VALUE_MASK        = 0x003FFFFFE0000000L, // 00000000.00111111.11111111.11111111.11100000.00000000.00000000.00000000
        ORDER_VALUE_EXISTS_MASK = 0x0040000000000000L; // 00000000.01000000.00000000.00000000.00000000.00000000.00000000.00000000

    public static final long
        COMPARE_MASK            = 0x000000001FFFFFFFL; // 00000000.00000000.00000000.00000000.00011111.11111111.11111111.11111111

    public static long newLongMove(final int newFunction, final Integer newFromSquare, final Integer newToSquare,
            final Integer newPiece, final Integer newTargetPiece, final Integer newPromotionPiece) {

        long longMove = NULL_MOVE;

        longMove |= newFunction; // << FUNCTION_IX;

        if (newFromSquare != null) {
            longMove |= newFromSquare << FROM_SQUARE_IX;
            longMove |= 0x1L          << FROM_SQUARE_EXISTS_IX;
        }

        if (newFromSquare != null) {
            longMove |= newToSquare << TO_SQUARE_IX;
            longMove |= 0x1L        << TO_SQUARE_EXISTS_IX;
        }

        if (newPiece != null) {
            longMove |= Math.abs(newPiece)     << PIECE_ROLE_IX;
            longMove |= (newPiece > 0 ? 1 : 0) << PIECE_COLOR_IX;
        }

        if (newTargetPiece != null) {
            longMove |= Math.abs(newTargetPiece)     << TARGET_PIECE_ROLE_IX;
            longMove |= (newTargetPiece > 0 ? 1 : 0) << TARGET_PIECE_COLOR_IX;
        }

        if (newPromotionPiece != null) {
            longMove |= Math.abs(newPromotionPiece)     << PROM_PIECE_ROLE_IX;
            longMove |= (newPromotionPiece > 0 ? 1 : 0) << PROM_PIECE_COLOR_IX;
        }

        return longMove;
    }

    public static int getFunction(final Long longMove) {
        return (int) ((longMove /*>>> FUNCTION_IX*/) & FUNCTION_MASK);
    }

    public static Byte getFromSquare(final Long longMove) {
        if ((longMove & FROM_SQUARE_EXISTS_MASK) == 0x0L) {
            return null;
        } else {
            return (byte) ((longMove & FROM_SQUARE_MASK) >>> FROM_SQUARE_IX);
        }
    }

    public static Byte getToSquare(final Long longMove) {
        if ((longMove & TO_SQUARE_EXISTS_MASK) == 0x0L) {
            return null;
        } else {
            return (byte) ((longMove & TO_SQUARE_MASK) >>> TO_SQUARE_IX);
        }
    }

//    public static Integer getPieceRole(final Long longMove) {
//        if ((longMove & PIECE_ROLE_MASK) == 0x0L) {
//            return null;
//        } else {
//            return (int) ((longMove & PIECE_ROLE_MASK) >>> PIECE_ROLE_IX);
//        }
//    }

    public static Byte getPiece(final Long longMove) {
        if ((longMove & PIECE_ROLE_MASK) == 0x0L) {
            return null;
        } else {
            if ((int) ((longMove & PIECE_COLOR_MASK) >>> PIECE_COLOR_IX) == 1) {
                return (byte) ((longMove & PIECE_ROLE_MASK) >>> PIECE_ROLE_IX);
            } else {
                return (byte) -((longMove & PIECE_ROLE_MASK) >>> PIECE_ROLE_IX);
            }
        }
    }

//    public static Integer getTargetPieceRole(final Long longMove) {
//        if ((longMove & TARGET_PIECE_ROLE_MASK) == 0x0L) {
//            return null;
//        } else {
//            return (int) ((longMove & TARGET_PIECE_ROLE_MASK) >>> TARGET_PIECE_ROLE_IX);
//        }
//    }

    public static Byte getTargetPiece(final Long longMove) {
        if ((longMove & TARGET_PIECE_ROLE_MASK) == 0x0L) {
            return null;
        } else {
            if ((int) ((longMove & TARGET_PIECE_COLOR_MASK) >>> TARGET_PIECE_COLOR_IX) == 1) {
                return (byte) ((longMove & TARGET_PIECE_ROLE_MASK) >>> TARGET_PIECE_ROLE_IX);
            } else {
                return (byte) -((longMove & TARGET_PIECE_ROLE_MASK) >>> TARGET_PIECE_ROLE_IX);
            }
        }
    }

//    public static Integer getPromotionPieceRole(final Long longMove) {
//        if ((longMove & PROM_PIECE_ROLE_MASK) == 0x0L) {
//            return null;
//        } else {
//            return (int) ((longMove & PROM_PIECE_ROLE_MASK) >>> PROM_PIECE_ROLE_IX);
//        }
//    }

    public static Byte getPromotionPiece(final Long longMove) {
        if ((longMove & PROM_PIECE_ROLE_MASK) == 0x0L) {
            return null;
        } else {
            if ((byte) ((longMove & PROM_PIECE_COLOR_MASK) >>> PROM_PIECE_COLOR_IX) == 1) {
                return (byte) ((longMove & PROM_PIECE_ROLE_MASK) >>> PROM_PIECE_ROLE_IX);
            } else {
                return (byte) -((longMove & PROM_PIECE_ROLE_MASK) >>> PROM_PIECE_ROLE_IX);
            }
        }
    }

    public static Integer getOrderValue(final Long longMove) {
        if ((longMove & ORDER_VALUE_EXISTS_MASK) == 0x0L) {
            return null;
        } else {
            return (int) ((longMove & ORDER_VALUE_MASK) >>> ORDER_VALUE_IX);
        }
    }

    public static Long setOrderValue(Long longMove, final Integer newOrderValue) {
        if ((longMove & ORDER_VALUE_EXISTS_MASK) != 0x0L) {
            longMove &= ~(longMove & ORDER_VALUE_MASK);
            longMove &= ~(0x1L << ORDER_VALUE_EXISTS_IX);
        }
        if (newOrderValue != null) {
            if (newOrderValue != 0) {
                longMove |= (((long) newOrderValue) << ORDER_VALUE_IX);
            }
            longMove |= (0x1L << ORDER_VALUE_EXISTS_IX);
        }

        return longMove;
    }

    public static boolean equals(final long longMove1, final long longMove2) {

        return (longMove1 & COMPARE_MASK) == (longMove2 & COMPARE_MASK);
    }

    public static String toString(final long longMove) {

        String moveString = "???";

        try {
            moveString = toSan(longMove);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return moveString;
    }

    public static String toSan(final long longMove)
            throws Exception {

        StringBuilder sb = new StringBuilder();

        Byte p;
        Byte fs;
        Byte ts;
        Byte tp;
        Byte pp;

        switch (getFunction(longMove)) {
        case Functions.MOVEMENT:
            p = getPiece(longMove);
            fs = getFromSquare(longMove);
            ts = getToSquare(longMove);
            pp = getPromotionPiece(longMove);
            assert p != null && fs != null && ts != null && pp != null;
            sb.append(Pieces.toSan(p));
            sb.append(Squares.toSan(fs))
                    .append(San.MOVEMENT)
                    .append(Squares.toSan(ts));
            if (getPromotionPiece(longMove) != null) {
                sb.append(San.PROMOTION_TO)
                        .append(Pieces.toSan(pp));
            }
            break;
        case Functions.CAPTURE:
            p = getPiece(longMove);
            fs = getFromSquare(longMove);
            ts = getToSquare(longMove);
            tp = getTargetPiece(longMove);
            pp = getPromotionPiece(longMove);
            assert p != null && fs != null && ts != null && tp != null && pp != null;
            sb.append(Pieces.toSan(p));
            sb.append(Squares.toSan(fs))
                    .append(San.CAPTURE)
                    .append(Squares.toSan(ts));
            sb.append("(")
                    .append(Pieces.toSan(tp))
                    .append(")");
            if (getPromotionPiece(longMove) != null) {
                sb.append(San.PROMOTION_TO)
                        .append(Pieces.toSan(pp));
            }
            break;
        case Functions.EN_PASSANT:
            p = getPiece(longMove);
            fs = getFromSquare(longMove);
            ts = getToSquare(longMove);
            tp = getTargetPiece(longMove);
            pp = getPromotionPiece(longMove);
            assert p != null && fs != null && ts != null && tp != null && pp != null;
            sb.append(Pieces.toSan(p));
            sb.append(Squares.toSan(fs))
                    .append(San.CAPTURE)
                    .append(Squares.toSan(ts));
            sb.append("(")
                    .append(Pieces.toSan(tp))
                    .append(")");
            sb.append(San.EN_PASSANT);
            break;
        case Functions.SHORT_CG:
            sb.append(San.SHORT_CASTLING);
            break;
        case Functions.LONG_CG:
            sb.append(San.LONG_CASTLING);
            break;
        default:
            throw new Exception("getFunction(longMove)=" + getFunction(longMove));
        }

        while (sb.length() <= MOVE_STRING_LENGTH) { sb.append(" "); }

//        if (orderValue == null) {
//            sb.append("[ null ]");
//        } else {
//            sb.append("[ " + orderValue + " ]");
//        }

        return sb.toString();
    }

    public static int compareTo(final long longMove1, final long longMove2) {

        return -Long.compare(longMove1, longMove2);
    }

    public static String toBinaryString(final long move) {

        String s = Long.toBinaryString(move);

        return new String(new char[64 - s.length()]) + s;
    }

}
