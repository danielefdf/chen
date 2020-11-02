package model.elements;

import model.notations.San;
import model.figures.Alpha2;
import model.notations.Pan;
import model.notations.Fen;
import model.figures.Utf8;

public abstract class Pieces {

    public static final byte WHITE_PAWN   = 1, BLACK_PAWN   = -1,
                            WHITE_KNIGHT = 2, BLACK_KNIGHT = -2,
                            WHITE_BISHOP = 3, BLACK_BISHOP = -3,
                            WHITE_ROOK   = 4, BLACK_ROOK   = -4,
                            WHITE_QUEEN  = 5, BLACK_QUEEN  = -5,
                            WHITE_KING   = 6, BLACK_KING   = -6;

    public static final byte ROLE_PAWN   = 1,
                            ROLE_KNIGHT = 2,
                            ROLE_BISHOP = 3,
                            ROLE_ROOK   = 4,
                            ROLE_QUEEN  = 5,
                            ROLE_KING   = 6;

    public static final byte ROLES_NUMBER = 6;

    // view/export only
    public static byte color(final byte piece) {

        return (byte) Math.signum(piece);
    }

    public static String toString(final byte piece)
            throws Exception {

        switch (piece) {
        case WHITE_PAWN:   return "WHITE_PAWN";
        case WHITE_KNIGHT: return "WHITE_KNIGHT";
        case WHITE_BISHOP: return "WHITE_BISHOP";
        case WHITE_ROOK:   return "WHITE_ROOK";
        case WHITE_QUEEN:  return "WHITE_QUEEN";
        case WHITE_KING:   return "WHITE_KING";
        case BLACK_PAWN:   return "BLACK_PAWN";
        case BLACK_KNIGHT: return "BLACK_KNIGHT";
        case BLACK_BISHOP: return "BLACK_BISHOP";
        case BLACK_ROOK:   return "BLACK_ROOK";
        case BLACK_QUEEN:  return "BLACK_QUEEN";
        case BLACK_KING:   return "BLACK_KING";
        default:
            throw new Exception("piece=" + piece);
        }

    }

    public static String toFen(final byte piece)
            throws Exception {

        switch (piece) {
        case WHITE_PAWN:   return Fen.WHITE_PAWN;
        case WHITE_KNIGHT: return Fen.WHITE_KNIGHT;
        case WHITE_BISHOP: return Fen.WHITE_BISHOP;
        case WHITE_ROOK:   return Fen.WHITE_ROOK;
        case WHITE_QUEEN:  return Fen.WHITE_QUEEN;
        case WHITE_KING:   return Fen.WHITE_KING;
        case BLACK_PAWN:   return Fen.BLACK_PAWN;
        case BLACK_KNIGHT: return Fen.BLACK_KNIGHT;
        case BLACK_BISHOP: return Fen.BLACK_BISHOP;
        case BLACK_ROOK:   return Fen.BLACK_ROOK;
        case BLACK_QUEEN:  return Fen.BLACK_QUEEN;
        case BLACK_KING:   return Fen.BLACK_KING;
        default:
            throw new Exception("piece=" + piece);
        }

    }

    public static byte fromFen(final String fenPiece)
            throws Exception {

        switch (fenPiece) {
        case Fen.WHITE_PAWN:   return Pieces.WHITE_PAWN;
        case Fen.WHITE_KNIGHT: return Pieces.WHITE_KNIGHT;
        case Fen.WHITE_BISHOP: return Pieces.WHITE_BISHOP;
        case Fen.WHITE_ROOK:   return Pieces.WHITE_ROOK;
        case Fen.WHITE_QUEEN:  return Pieces.WHITE_QUEEN;
        case Fen.WHITE_KING:   return Pieces.WHITE_KING;
        case Fen.BLACK_PAWN:   return Pieces.BLACK_PAWN;
        case Fen.BLACK_KNIGHT: return Pieces.BLACK_KNIGHT;
        case Fen.BLACK_BISHOP: return Pieces.BLACK_BISHOP;
        case Fen.BLACK_ROOK:   return Pieces.BLACK_ROOK;
        case Fen.BLACK_QUEEN:  return Pieces.BLACK_QUEEN;
        case Fen.BLACK_KING:   return Pieces.BLACK_KING;
        default:
            throw new Exception("fenPiece=" + fenPiece);
        }

    }

    public static String toSan(final byte piece)
            throws Exception {

        switch (Math.abs(piece)) {
        case ROLE_PAWN:   return San.ROLE_PAWN;
        case ROLE_BISHOP: return San.ROLE_BISHOP;
        case ROLE_KNIGHT: return San.ROLE_KNIGHT;
        case ROLE_ROOK:   return San.ROLE_ROOK;
        case ROLE_QUEEN:  return San.ROLE_QUEEN;
        case ROLE_KING:   return San.ROLE_KING;
        default:
            throw new Exception("Math.abs(piece)=" + Math.abs(piece));
        }

    }

    public static byte fromPan(final String panPieceRole)
            throws Exception {

        switch (panPieceRole) {
        case Pan.ROLE_BISHOP: return Pieces.ROLE_BISHOP;
        case Pan.ROLE_KNIGHT: return Pieces.ROLE_KNIGHT;
        case Pan.ROLE_ROOK:   return Pieces.ROLE_ROOK;
        case Pan.ROLE_QUEEN:  return Pieces.ROLE_QUEEN;
        default:
            throw new Exception("panPieceRole=" + panPieceRole);
        }

    }

    public static String toAlpha2WithoutField(final byte piece)
            throws Exception {

        switch ((byte) Math.signum(piece)) {
        case Colors.WHITE: return toAlpha2WithoutFieldWhite(piece);
        case Colors.BLACK: return toAlpha2WithoutFieldWhite(piece).toUpperCase();
        default:
            throw new Exception("Math.signum(piece)=" + Math.signum(piece));
        }

    }

    private static String toAlpha2WithoutFieldWhite(final byte piece)
            throws Exception {

        switch (Math.abs(piece)) {
        case ROLE_PAWN:   return Alpha2.WHITE_PAWN;
        case ROLE_KNIGHT: return Alpha2.WHITE_KNIGHT;
        case ROLE_BISHOP: return Alpha2.WHITE_BISHOP;
        case ROLE_ROOK:   return Alpha2.WHITE_ROOK;
        case ROLE_QUEEN:  return Alpha2.WHITE_QUEEN;
        case ROLE_KING:   return Alpha2.WHITE_KING;
        default:
            throw new Exception("Math.abs(piece)=" + Math.abs(piece));
        }

    }

    public static String toAlpha2WithField(final byte piece, final byte fieldColor)
            throws Exception {

        switch ((byte) Math.signum(piece)) {
        case Colors.WHITE: return toAlpha2WithFieldWhite(piece, fieldColor);
        case Colors.BLACK: return toAlpha2WithFieldWhite(piece, fieldColor).toLowerCase();
        default:
            throw new Exception("Math.signum(piece)=" + Math.signum(piece));
        }

    }

    public static String toAlpha2WithFieldWhite(final byte piece, final byte fieldColor)
            throws Exception {

        switch (fieldColor) {
        case Colors.WHITE:
            switch (Math.abs(piece)) {
            case Pieces.ROLE_PAWN:   return Alpha2.WHITE_FIELD_WHITE_PAWN;
            case Pieces.ROLE_KNIGHT: return Alpha2.WHITE_FIELD_WHITE_KNIGHT;
            case Pieces.ROLE_BISHOP: return Alpha2.WHITE_FIELD_WHITE_BISHOP;
            case Pieces.ROLE_ROOK:   return Alpha2.WHITE_FIELD_WHITE_ROOK;
            case Pieces.ROLE_QUEEN:  return Alpha2.WHITE_FIELD_WHITE_QUEEN;
            case Pieces.ROLE_KING:   return Alpha2.WHITE_FIELD_WHITE_KING;
            default:
                throw new Exception("Math.abs(piece)=" + Math.abs(piece));
            }
        case Colors.BLACK:
            switch (Math.abs(piece)) {
            case Pieces.ROLE_PAWN:   return Alpha2.BLACK_FIELD_WHITE_PAWN;
            case Pieces.ROLE_KNIGHT: return Alpha2.BLACK_FIELD_WHITE_KNIGHT;
            case Pieces.ROLE_BISHOP: return Alpha2.BLACK_FIELD_WHITE_BISHOP;
            case Pieces.ROLE_ROOK:   return Alpha2.BLACK_FIELD_WHITE_ROOK;
            case Pieces.ROLE_QUEEN:  return Alpha2.BLACK_FIELD_WHITE_QUEEN;
            case Pieces.ROLE_KING:   return Alpha2.BLACK_FIELD_WHITE_KING;
            default:
                throw new Exception("Math.abs(piece)=" + Math.abs(piece));
            }
        default:
            throw new Exception("Math.signum(piece)=" + Math.signum(piece));
        }

    }

    public static String toUtf8(final Byte piece)
            throws Exception {

        switch ((byte) Math.signum(piece)) {
        case Colors.WHITE:
            switch (Math.abs(piece)) {
            case ROLE_PAWN:   return Utf8.WHITE_PAWN;
            case ROLE_KNIGHT: return Utf8.WHITE_KNIGHT;
            case ROLE_BISHOP: return Utf8.WHITE_BISHOP;
            case ROLE_ROOK:   return Utf8.WHITE_ROOK;
            case ROLE_QUEEN:  return Utf8.WHITE_QUEEN;
            case ROLE_KING:   return Utf8.WHITE_KING;
            default:
                throw new Exception("Math.abs(piece)=" + Math.abs(piece));
            }
        case Colors.BLACK:
            switch (Math.abs(piece)) {
            case ROLE_PAWN:   return Utf8.BLACK_PAWN;
            case ROLE_KNIGHT: return Utf8.BLACK_KNIGHT;
            case ROLE_BISHOP: return Utf8.BLACK_BISHOP;
            case ROLE_ROOK:   return Utf8.BLACK_ROOK;
            case ROLE_QUEEN:  return Utf8.BLACK_QUEEN;
            case ROLE_KING:   return Utf8.BLACK_KING;
            default:
                throw new Exception("Math.abs(piece)=" + Math.abs(piece));
            }
        default:
            throw new Exception("Math.signum(piece)=" + Math.signum(piece));
        }

    }

}
