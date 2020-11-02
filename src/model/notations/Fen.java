package model.notations;

import model.elements.Colors;
import model.elements.Squares;

/**
 * FEN: Forsyth-Edwards Notation
 *
 * E' il formato utilizzato nel protocollo UCI, descritto nelle specifiche
 * come Long Algebraic Notation, in modo leggermente scorretto.
 *
 * Ad es.: "rnbqkbnr/ppp1pppp/8/3p4/2PP4/8/PP2PPPP/RNBQKBNR b KQkq c3 0 2" -- Queen's Gambit
 */
public abstract class Fen {

    // nk6/8/8/8/8/8/8/6KN w - - 0 1
    public static final int ELEMENTS_NUMBER = 6;

    public static final String ELEMENTS_SEPARATOR = " ";

    public static final String RANK_SEPARATOR = "/";

    public static final String WHITE_PAWN   = "P", BLACK_PAWN   = "p",
                               WHITE_KNIGHT = "N", BLACK_KNIGHT = "n",
                               WHITE_BISHOP = "B", BLACK_BISHOP = "b",
                               WHITE_ROOK   = "R", BLACK_ROOK   = "r",
                               WHITE_QUEEN  = "Q", BLACK_QUEEN  = "q",
                               WHITE_KING   = "K", BLACK_KING   = "k";

    public static final String WHITE_PLAYER = "w", BLACK_PLAYER = "b";

    public static final String NO_CASTLINGS = "-",
                               WHITE_SHORT_CASTLING = "K", WHITE_LONG_CASTLING = "Q",
                               BLACK_SHORT_CASTLING = "k", BLACK_LONG_CASTLING = "q";

    public static final String NO_EN_PASSANT = "-";

    public static String flipColor(final String fenString)
            throws Exception {

        String[] fenStringElementsList = fenString.split(Fen.ELEMENTS_SEPARATOR);

        if (fenStringElementsList.length != Fen.ELEMENTS_NUMBER) {
            throw new Exception("fenStringElementsList.length=" + fenStringElementsList.length);
        }

        String fenSquarePieceMap  = fenStringElementsList[0];
        String fenPlayerColor     = fenStringElementsList[1];
        String fenCastlingFlags   = fenStringElementsList[2];
        String fenEnPassantSquare = fenStringElementsList[3];
        String fenHalfmovesClock  = fenStringElementsList[4];
        String fenFullmovesNumber = fenStringElementsList[5];

        StringBuilder flipSquarePieceMap = new StringBuilder();
        String[] fenRanksList = fenSquarePieceMap.split(Fen.RANK_SEPARATOR);
        for (int i = fenRanksList.length - 1; i >= 0; --i) {
        	String flipRank = fenRanksList[i]
        			.replaceAll("P", "u")
        			.replaceAll("N", "v")
        			.replaceAll("B", "w")
        			.replaceAll("R", "x")
        			.replaceAll("Q", "y")
        			.replaceAll("K", "z");
        	flipRank = flipRank
        			.replaceAll("p", "P")
        			.replaceAll("n", "N")
        			.replaceAll("b", "B")
        			.replaceAll("r", "R")
        			.replaceAll("q", "Q")
        			.replaceAll("k", "K");
        	flipRank = flipRank
        			.replaceAll("u", "p")
        			.replaceAll("v", "n")
        			.replaceAll("w", "b")
        			.replaceAll("x", "r")
        			.replaceAll("y", "q")
        			.replaceAll("z", "k");
        	flipSquarePieceMap.append(flipRank);
        	if (i > 0) {
        		flipSquarePieceMap.append(Fen.RANK_SEPARATOR);
        	}
        }

        String flipPlayerColor;
        switch (fenPlayerColor) {
        case Fen.WHITE_PLAYER: flipPlayerColor = Fen.BLACK_PLAYER; break;
        case Fen.BLACK_PLAYER: flipPlayerColor = Fen.WHITE_PLAYER; break;
        default:
        	throw new Exception("fenPlayerColor=" + fenPlayerColor);
        }

        StringBuilder flipCastlingFlags = new StringBuilder();
        if (fenCastlingFlags.equals(Fen.NO_CASTLINGS)) {
        	flipCastlingFlags.append(Fen.NO_CASTLINGS);
        } else {
            for (int i = 0; i <= fenCastlingFlags.length() - 1; ++i) {
            	if (fenCastlingFlags.startsWith(Fen.BLACK_LONG_CASTLING, i)) {
            		flipCastlingFlags.append(Fen.WHITE_LONG_CASTLING);
            	}
            }
            for (int i = 0; i <= fenCastlingFlags.length() - 1; ++i) {
            	if (fenCastlingFlags.startsWith(Fen.BLACK_SHORT_CASTLING, i)) {
            		flipCastlingFlags.append(Fen.WHITE_SHORT_CASTLING);
            	}
            }
            for (int i = 0; i <= fenCastlingFlags.length() - 1; ++i) {
            	if (fenCastlingFlags.startsWith(Fen.WHITE_LONG_CASTLING, i)) {
            		flipCastlingFlags.append(Fen.BLACK_LONG_CASTLING);
            	}
            }
            for (int i = 0; i <= fenCastlingFlags.length() - 1; ++i) {
            	if (fenCastlingFlags.startsWith(Fen.WHITE_SHORT_CASTLING, i)) {
            		flipCastlingFlags.append(Fen.BLACK_SHORT_CASTLING);
            	}
            }
        }

        String flipEnPassantSquare;
        if (fenEnPassantSquare.equals(Fen.NO_EN_PASSANT)) {
            flipEnPassantSquare = Fen.NO_EN_PASSANT;
        } else {
            byte enPassantSquare = Squares.fromSan(fenEnPassantSquare);
            byte enPassantFile = (byte) (/*Squares.file()*/enPassantSquare % Squares.EDGE);
            byte enPassantRank = (byte) (/*Squares.rank()*/enPassantSquare / Squares.EDGE);
            byte byteFlipEnPassantRank = (byte) (Squares.EDGE - enPassantRank - 1);
            byte byteFlipEnPassantSquare = (byte) (/*Squares.newSquare()*/Squares.EDGE * byteFlipEnPassantRank + enPassantFile);
            flipEnPassantSquare = Squares.toFen(byteFlipEnPassantSquare);
        }

        @SuppressWarnings("UnnecessaryLocalVariable")
        String flip = flipSquarePieceMap.toString()
        		+ Fen.ELEMENTS_SEPARATOR + flipPlayerColor
        		+ Fen.ELEMENTS_SEPARATOR + flipCastlingFlags.toString()
        		+ Fen.ELEMENTS_SEPARATOR + flipEnPassantSquare
        		+ Fen.ELEMENTS_SEPARATOR + fenHalfmovesClock
        		+ Fen.ELEMENTS_SEPARATOR + fenFullmovesNumber;

		return flip;
    }

    public static String verticalMirror(final String fenString)
            throws Exception {

        String[] fenStringElementsList = fenString.split(Fen.ELEMENTS_SEPARATOR);

        if (fenStringElementsList.length != Fen.ELEMENTS_NUMBER) {
            throw new Exception("fenStringElementsList.length=" + fenStringElementsList.length);
        }

        String fenSquarePieceMap  = fenStringElementsList[0];
        String fenPlayerColor     = fenStringElementsList[1];
        String fenCastlingFlags   = fenStringElementsList[2];
        String fenEnPassantSquare = fenStringElementsList[3];
        String fenHalfmovesClock  = fenStringElementsList[4];
        String fenFullmovesNumber = fenStringElementsList[5];

        StringBuilder mirrorSquarePieceMap = new StringBuilder();
        String[] fenRanksList = fenSquarePieceMap.split(Fen.RANK_SEPARATOR);
        for (int i = 0; i <= fenRanksList.length - 1; ++i) {
        	char[] fenRankCharsList = fenRanksList[i].toCharArray();
        	StringBuilder mirrorRank = new StringBuilder();
        	for (int j = fenRanksList[i].length() - 1; j >= 0; --j) {
        		mirrorRank.append(fenRankCharsList[j]);
        	}
        	mirrorSquarePieceMap.append(mirrorRank);
        	if (i < fenRanksList.length - 1) {
        		mirrorSquarePieceMap.append(Fen.RANK_SEPARATOR);
        	}
        }

        StringBuilder mirrorCastlingFlags = new StringBuilder();
        if (fenCastlingFlags.equals(Fen.NO_CASTLINGS)) {
        	mirrorCastlingFlags.append(Fen.NO_CASTLINGS);
        } else {
            for (int i = 0; i <= fenCastlingFlags.length() - 1; ++i) {
            	if (fenCastlingFlags.startsWith(Fen.WHITE_LONG_CASTLING, i)) {
            		mirrorCastlingFlags.append(Fen.WHITE_SHORT_CASTLING);
            	}
            }
            for (int i = 0; i <= fenCastlingFlags.length() - 1; ++i) {
            	if (fenCastlingFlags.startsWith(Fen.WHITE_SHORT_CASTLING, i)) {
            		mirrorCastlingFlags.append(Fen.WHITE_LONG_CASTLING);
            	}
            }
            for (int i = 0; i <= fenCastlingFlags.length() - 1; ++i) {
            	if (fenCastlingFlags.startsWith(Fen.BLACK_LONG_CASTLING, i)) {
            		mirrorCastlingFlags.append(Fen.BLACK_SHORT_CASTLING);
            	}
            }
            for (int i = 0; i <= fenCastlingFlags.length() - 1; ++i) {
            	if (fenCastlingFlags.startsWith(Fen.BLACK_SHORT_CASTLING, i)) {
            		mirrorCastlingFlags.append(Fen.BLACK_LONG_CASTLING);
            	}
            }
        }

        String mirrorEnPassantSquare;
        if (fenEnPassantSquare.equals(Fen.NO_EN_PASSANT)) {
            mirrorEnPassantSquare = Fen.NO_EN_PASSANT;
        } else {
            byte enPassantSquare = Squares.fromSan(fenEnPassantSquare);
            byte enPassantFile = (byte) (/*Squares.file()*/enPassantSquare % Squares.EDGE);
            byte enPassantRank = (byte) (/*Squares.rank()*/enPassantSquare / Squares.EDGE);
            byte intMirrorEnPassantFile = (byte) (Squares.EDGE - enPassantFile - 1);
            byte intMirrorEnPassantSquare = (byte) (/*Squares.newSquare()*/Squares.EDGE * enPassantRank + intMirrorEnPassantFile);
            mirrorEnPassantSquare = Squares.toFen(intMirrorEnPassantSquare);
        }

        @SuppressWarnings("UnnecessaryLocalVariable")
        String mirror = mirrorSquarePieceMap.toString()
        		+ Fen.ELEMENTS_SEPARATOR + fenPlayerColor
        		+ Fen.ELEMENTS_SEPARATOR + mirrorCastlingFlags.toString()
        		+ Fen.ELEMENTS_SEPARATOR + mirrorEnPassantSquare
        		+ Fen.ELEMENTS_SEPARATOR + fenHalfmovesClock
        		+ Fen.ELEMENTS_SEPARATOR + fenFullmovesNumber;

		return mirror;
    }

    public static int getPlayerColor(final String fenString)
            throws Exception {

        final String[] fenStringElementsList = fenString.split(Fen.ELEMENTS_SEPARATOR);

        if (fenStringElementsList.length != Fen.ELEMENTS_NUMBER) {
            throw new Exception("fenStringElementsList.length=" + fenStringElementsList.length);
        }

        final String fenPlayerColor = fenStringElementsList[1];

        switch (fenPlayerColor) {
        case "w": return Colors.WHITE;
        case "b": return Colors.BLACK;
        default:
            throw new Exception("fenPlayerColor=" + fenPlayerColor);
        }

    }

}
