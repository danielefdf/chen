package engine.model;

import java.io.Serializable;

import application.app.DebugUtils;
import model.elements.Colors;
import model.elements.Functions;
import model.elements.IntMoves;
import model.elements.Pieces;
import model.elements.Squares;
import model.notations.San;
import model.notations.Pan;

public final class Move implements Comparable<Move>, Serializable {

    private static final long serialVersionUID = 1L;

    public static final Integer START_VALUE        = null;
    public static final int     MOVES_TO_MATE      = 100;
    public static final int     MOVE_STRING_LENGTH = 10;

    public byte function;
    public Byte piece;          // nullable
    public Byte targetPiece;    // nullable
    public Byte fromSquare;     // nullable
    public Byte toSquare;       // nullable
    public Byte promotionPiece; // nullable
    public Integer orderValue;  // nullable

    public Move(final byte newFunction, final Byte newFromSquare, final Byte newToSquare, final Byte newPiece,
            final Byte newTargetPiece, final Byte newPromotionPiece) {

        function       = newFunction;
        piece          = newPiece;
        targetPiece    = newTargetPiece;
        fromSquare     = newFromSquare;
        toSquare       = newToSquare;
        promotionPiece = newPromotionPiece;

        orderValue = START_VALUE;

    }

    public Move(final String panMove, final Node node)
            throws Exception {
    	fromPan(panMove, node);
    }

    public Move(final Integer intMove)
            throws Exception {

        if (intMove == null) {
            throw new Exception("intMove=" + intMove); // 0 è già gestito in PvMap.putMove()
        }

        function = (byte) ((intMove & IntMoves.FUNCTION_MASK)); // >>> LongMoves.FUNCTION_IX);

        if ((intMove & IntMoves.FROM_SQUARE_EXISTS_MASK) != 0x0L) {
            fromSquare = (byte) ((intMove & IntMoves.FROM_SQUARE_MASK) >>> IntMoves.FROM_SQUARE_IX);
        }

        if ((intMove & IntMoves.TO_SQUARE_EXISTS_MASK) != 0x0L) {
            toSquare = (byte) ((intMove & IntMoves.TO_SQUARE_MASK) >>> IntMoves.TO_SQUARE_IX);
        }

        if ((intMove & IntMoves.PIECE_EXISTS_MASK) != 0x0L) {
            piece = (byte) ((intMove & IntMoves.PIECE_ROLE_MASK) >>> IntMoves.PIECE_ROLE_IX);
            if (((intMove & IntMoves.PIECE_NEGA_MASK) >>> IntMoves.PIECE_NEGA_IX) != 0x0L) {
                piece = (byte) ((-1) * piece);
            }
        }

        if ((intMove & IntMoves.TARGET_PIECE_EXISTS_MASK) != 0x0L) {
            targetPiece = (byte) ((intMove & IntMoves.TARGET_PIECE_ROLE_MASK) >>> IntMoves.TARGET_PIECE_ROLE_IX);
            if (((intMove & IntMoves.TARGET_PIECE_NEGA_MASK) >>> IntMoves.TARGET_PIECE_NEGA_IX) != 0x0L) {
                targetPiece = (byte) ((-1) * targetPiece);
            }
        }

        if ((intMove & IntMoves.PROM_PIECE_EXISTS_MASK) != 0x0L) {
            promotionPiece = (byte) ((intMove & IntMoves.PROM_PIECE_ROLE_MASK) >>> IntMoves.PROM_PIECE_ROLE_IX);
            if (((intMove & IntMoves.PROM_PIECE_NEGA_MASK) >>> IntMoves.PROM_PIECE_NEGA_IX) != 0x0L) {
                promotionPiece = (byte) ((-1) * promotionPiece);
            }
        }

//        orderValue = null;

    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        Move other = (Move) obj;

        if (function != other.function) {
            return false;
        }

        if (fromSquare == null) {
            if (other.fromSquare != null) {
                return false;
            }
        } else if (!fromSquare.equals(other.fromSquare)) {
            return false;
        }

        if (toSquare == null) {
            if (other.toSquare != null) {
                return false;
            }
        } else if (!toSquare.equals(other.toSquare)) {
            return false;
        }

        if (promotionPiece == null) {
            //noinspection RedundantIfStatement
            if (other.promotionPiece != null) {
                return false;
            }
        } else //noinspection RedundantIfStatement
            if (!promotionPiece.equals(other.promotionPiece)) {
            return false;
        }

        // orderValue -> NO

        return true;
    }

    @Override
    public String toString() {

        String moveString = "???";

        try {
            moveString = toSan();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return moveString;
    }

    public String toSan()
            throws Exception {

        StringBuilder sb = new StringBuilder();

        byte pieceTo;

        switch (function) {
        case Functions.MOVEMENT:
            sb.append(Pieces.toSan(piece));
            sb.append(Squares.toSan(fromSquare))
                    .append(San.MOVEMENT)
                    .append(Squares.toSan(toSquare));
            if (promotionPiece != null) {
                pieceTo = (byte) (Colors.WHITE * /*getRole()*/Math.abs(promotionPiece));
                // usa sempre Color.WHITE_COLOR perché per la notazione algebrica
                // non occorre il dettaglio del colore
                sb.append(San.PROMOTION_TO)
                        .append(Pieces.toSan(pieceTo));
            }
            break;
        case Functions.CAPTURE:
            sb.append(Pieces.toSan(piece));
            sb.append(Squares.toSan(fromSquare))
                    .append(San.CAPTURE)
                    .append(Squares.toSan(toSquare));
            sb.append("(")
                    .append(Pieces.toSan(targetPiece))
                    .append(")");
            if (promotionPiece != null) {
                // usa sempre Color.WHITE_COLOR perché per la notazione algebrica
                // non occorre il dettaglio del colore
                pieceTo = (byte) (Colors.WHITE * /*getRole()*/Math.abs(promotionPiece));
                sb.append(San.PROMOTION_TO)
                        .append(Pieces.toSan(pieceTo));
            }
            break;
        case Functions.EN_PASSANT:
            sb.append(Pieces.toSan(piece));
            sb.append(Squares.toSan(fromSquare))
                    .append(San.CAPTURE)
                    .append(Squares.toSan(toSquare));
            sb.append("(")
                    .append(Pieces.toSan(targetPiece))
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
            throw new Exception("move.function=" + function);
        }

        while (sb.length() <= MOVE_STRING_LENGTH) { sb.append(" "); }

        if (DebugUtils.movesListScoreDebug) {
            if (orderValue == null) {
                sb.append("[ null ]");
            } else {
                sb.append("[ ")
                        .append(orderValue)
                        .append(" ]");
            }
        }

        return sb.toString();
    }

    public void fromPan(final String panMove, final Node node)
            throws Exception {

    	String fromSquareString, toSquareString;
    	String promRoleString;

        switch (panMove) {
            case Pan.WHITE_SHORT_CASTLING:
            case Pan.BLACK_SHORT_CASTLING:
                function       = Functions.SHORT_CG;
                fromSquare     = null;
                toSquare       = null;
                piece          = null;
                targetPiece    = null;
                promotionPiece = null;
                orderValue     = null;
                break;
            case Pan.WHITE_LONG_CASTLING:
            case Pan.BLACK_LONG_CASTLING:
                function       = Functions.LONG_CG;
                fromSquare     = null;
                toSquare       = null;
                piece          = null;
                targetPiece    = null;
                promotionPiece = null;
                orderValue     = null;
                break;
            default:
                //function       =

                fromSquareString = panMove.substring(0, 2);
                fromSquare = Squares.fromSan(fromSquareString);

                toSquareString = panMove.substring(2, 4);
                toSquare = Squares.fromSan(toSquareString);

                piece = node.squarePieceMap[fromSquare];

                targetPiece = node.squarePieceMap[toSquare];

                if (piece == Pieces.WHITE_PAWN
                        || piece == Pieces.BLACK_PAWN) {
                    if (Squares.file(fromSquare) == Squares.file(toSquare)) {
                        function = Functions.MOVEMENT;
                    } else {
                        if (targetPiece == null) {
                            function = Functions.EN_PASSANT;
                        } else {
                            function = Functions.CAPTURE;
                        }
                    }
                } else {
                    if (targetPiece == null) {
                        function = Functions.MOVEMENT;
                    } else {
                        function = Functions.CAPTURE;
                    }
                }

                if (panMove.length() > 4) {
                    promRoleString = panMove.substring(4, 5);
                    promotionPiece = Pieces.fromPan(promRoleString);
                }

                orderValue = null;
        }

    }

    public String toPan(final int sideColor)
            throws Exception {

        StringBuilder sb = new StringBuilder();

        byte pieceTo;

        switch (function) {
        case Functions.MOVEMENT:
        case Functions.CAPTURE:
        case Functions.EN_PASSANT:
            sb.append(Squares.toSan(fromSquare))
                    .append(Squares.toSan(toSquare));
            if (promotionPiece != null) {
                pieceTo = (byte) (Colors.WHITE * /*Pieces.getRole()*/Math.abs(promotionPiece));
                // usa sempre Color.WHITE_COLOR perché per la notazione algebrica
                // non occorre il dettaglio del colore
                sb.append(Pieces.toSan(pieceTo).toLowerCase());
            }
            break;
        case Functions.SHORT_CG:
        	switch (sideColor) {
        	case Colors.WHITE: sb.append(Pan.WHITE_SHORT_CASTLING); break;
        	case Colors.BLACK: sb.append(Pan.BLACK_SHORT_CASTLING); break;
            default:
                throw new Exception("sideColor=" + sideColor);
        	}
            break;
        case Functions.LONG_CG:
        	switch (sideColor) {
        	case Colors.WHITE: sb.append(Pan.WHITE_LONG_CASTLING); break;
        	case Colors.BLACK: sb.append(Pan.BLACK_LONG_CASTLING); break;
            default:
                throw new Exception("sideColor=" + sideColor);
        	}
            break;
        default:
            throw new Exception("function=" + function);
        }

        return sb.toString();
    }

    public int toIntMove() {

        int intMove = 0x0;

        intMove |= function;                              // << LongMoves.FUNCTION_IX;
        if (fromSquare != null) {
            intMove |= fromSquare                            << IntMoves.FROM_SQUARE_IX;
            intMove |= 0x1                                   << IntMoves.FROM_SQUARE_EXISTS_IX;
        }
        if (toSquare != null) {
            intMove |= toSquare                              << IntMoves.TO_SQUARE_IX;
            intMove |= 0x1                                   << IntMoves.TO_SQUARE_EXISTS_IX;
        }
        if (piece != null) {
            intMove |= Math.abs(piece)                       << IntMoves.PIECE_ROLE_IX;
            if (piece < 0) {
                intMove |= 0x1                               << IntMoves.PIECE_NEGA_IX;
            }
            intMove |= 0x1                                   << IntMoves.PIECE_EXISTS_IX;
        }
        if (targetPiece != null) {
            intMove |= Math.abs(targetPiece)                 << IntMoves.TARGET_PIECE_ROLE_IX;
            if (targetPiece < 0) {
                intMove |= 0x1                               << IntMoves.TARGET_PIECE_NEGA_IX;
            }
            intMove |= 0x1                                   << IntMoves.TARGET_PIECE_EXISTS_IX;
        }
        if (promotionPiece != null) {
            intMove |= Math.abs(promotionPiece)              << IntMoves.PROM_PIECE_ROLE_IX;
            if (promotionPiece < 0) {
                intMove |= 0x1                               << IntMoves.PROM_PIECE_NEGA_IX;
            }
            intMove |= 0x1                                   << IntMoves.PROM_PIECE_EXISTS_IX;
        }

        return intMove;
    }

    @Override
    public int compareTo(final Move m2) {

        if (this.orderValue == null) {
            if (m2 == null || m2.orderValue == null) {
                return 0; //     null = null
            } else {
                return +1; //    null <    y
            }
        } else {
            if (m2 == null || m2.orderValue == null) {
                return -1; //    x    > null
            } else {
                return -(this.orderValue.compareTo(m2.orderValue));
            }
        }

    }

}
