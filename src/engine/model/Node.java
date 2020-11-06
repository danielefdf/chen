package engine.model;

import java.util.Arrays;
import model.elements.BitBoards;
import model.elements.Colors;
import model.elements.Functions;
import model.elements.Pieces;
import model.elements.Squares;
import model.elements.States;
import model.elements.Zobrist;
import model.figures.Alpha2;
import model.notations.Fen;
import model.figures.Utf8;

/**
 * Contiene informazioni su una posizione e sulla sua collocazione nel gioco.
 * informazioni "FEN" e altre sul gioco: lo stato del gioco e il campo treelevel (vedi campo).
 *
 * Contiene le funzioni per do e undo della mossa, per l'importazione/esportazione in formato FEN,
 * e per la verifica dello stato del nodo.
 *
 * La posizione viene registrata sia in formato bitmap sia in formato array.
 * Oltre alla posizione sono registrate grandezze affini come case controllate e
 * colonne aperte o semi-aperte.
 */
public final class Node {

    public byte playerColor;
    public boolean whiteShortCg;
    public boolean whiteLongCg;
    public boolean blackShortCg;
    public boolean blackLongCg;
    public Byte enPassantSquare;  // nullable
    public short halfmovesClock;
    public short fullmovesNumber;

    public byte gameState;

    /**
     * Per limitare la ricerca della posizione eventualmente ripetuta alla ricerca della
     * triplice ripetizione, registro nel nodo la profondità di ricerca cui è stato generato.
     */
    public short treeLevel;  // [0;MAX_MOVES_IN_GAME]

    public long nodeHashCode;

    public Byte[] squarePieceMap;

    public long allPiecesBb;

    public long whitePiecesBb,  blackPiecesBb;

    public long whitePawnsBb,   blackPawnsBb,
                whiteKnightsBb, blackKnightsBb,
                whiteBishopsBb, blackBishopsBb,
                whiteRooksBb,   blackRooksBb,
                whiteQueensBb,  blackQueensBb,
                whiteKingBb,    blackKingBb;

    public int whitePawnsCounter,     blackPawnsCounter,
               whiteKnightsCounter,   blackKnightsCounter,
               whiteBishopsCounter,   blackBishopsCounter,
               whiteWFBishopsCounter, blackWFBishopsCounter,
               whiteBFBishopsCounter, blackBFBishopsCounter,
               whiteRooksCounter,     blackRooksCounter,
               whiteQueensCounter,    blackQueensCounter;

    public long whiteKRing1Bb, blackKRing1Bb;
    public long whiteKRing2Bb, blackKRing2Bb;

    public long whiteControlledBb, blackControlledBb;

    public long whitePawnsFilesBb, blackPawnsFilesBb;

    public long openFilesBb;

    public long whiteSemiOFilesBb, blackSemiOFilesBb;

    /**
     * Data una stringa FEN, crea un nodo.
     *
     * @param newFenString La stringa FEN.
     * @throws Exception Nel caso di errore nella lettura o nell'interpretazione della stringa.
     */
    public Node(final String newFenString)
            throws Exception {

        fromFen(newFenString);

        checkLegalState();

        if (gameState == States.NOT_LEGAL) {
            return;
        }

        treeLevel = 0;

        Engine.treeHashcodesList[0] = nodeHashCode;
        Engine.lastIrrvLevelsList[0] = 0;

        // necessita gestione treeNodesList
        computeGameState(null);

    }

    /**
     * Crea un nodo a partire da un altro nodo e una mossa.
     *
     * @param parentNode Il nodo da cui partire.
     * @param newGenMove La mossa da applicare.
     * @throws Exception In caso di errore.
     */
    public Node(final Node parentNode, final Move newGenMove)
            throws Exception {

        copyFrom(parentNode);
        doMove(newGenMove);

    }

    /**
     * Costruttore copia.
     *
     * @param otherNode Il nodo da copiare.
     */
	@SuppressWarnings("CopyConstructorMissesField")
    public Node(Node otherNode) {

        copyFrom(otherNode);

    }

    /**
     * Copia l'oggetto nodo.
     *
     * @param node Il nodo da copiare.
     */
    public void copyFrom(final Node node) {

        playerColor     = node.playerColor;
        whiteShortCg    = node.whiteShortCg;
        whiteLongCg     = node.whiteLongCg;
        blackShortCg    = node.blackShortCg;
        blackLongCg     = node.blackLongCg;
        enPassantSquare = node.enPassantSquare;
        halfmovesClock  = node.halfmovesClock;
        fullmovesNumber = node.fullmovesNumber;
        nodeHashCode    = node.nodeHashCode;
        treeLevel       = node.treeLevel;
        gameState       = node.gameState;

        squarePieceMap = new Byte[Squares.SQUARES_NUMBER];

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(node.squarePieceMap, 0,
                squarePieceMap, 0, Squares.SQUARES_NUMBER);

        whitePiecesBb = node.whitePiecesBb;
        blackPiecesBb = node.blackPiecesBb;

        whitePawnsBb   = node.whitePawnsBb;
        blackPawnsBb   = node.blackPawnsBb;
        whiteKnightsBb = node.whiteKnightsBb;
        blackKnightsBb = node.blackKnightsBb;
        whiteBishopsBb = node.whiteBishopsBb;
        blackBishopsBb = node.blackBishopsBb;
        whiteRooksBb   = node.whiteRooksBb;
        blackRooksBb   = node.blackRooksBb;
        whiteQueensBb  = node.whiteQueensBb;
        blackQueensBb  = node.blackQueensBb;
        whiteKingBb    = node.whiteKingBb;
        blackKingBb    = node.blackKingBb;

        whitePawnsCounter     = node.whitePawnsCounter;
        blackPawnsCounter     = node.blackPawnsCounter;
        whiteKnightsCounter   = node.whiteKnightsCounter;
        blackKnightsCounter   = node.blackKnightsCounter;
        whiteBishopsCounter   = node.whiteBishopsCounter;
        blackBishopsCounter   = node.blackBishopsCounter;
        whiteWFBishopsCounter = node.whiteWFBishopsCounter;
        blackWFBishopsCounter = node.blackWFBishopsCounter;
        whiteBFBishopsCounter = node.whiteBFBishopsCounter;
        blackBFBishopsCounter = node.blackBFBishopsCounter;
        whiteRooksCounter     = node.whiteRooksCounter;
        blackRooksCounter     = node.blackRooksCounter;
        whiteQueensCounter    = node.whiteQueensCounter;
        blackQueensCounter    = node.blackQueensCounter;

        allPiecesBb = node.allPiecesBb;

    }

    @Override
    public String toString() {

        return String.valueOf(nodeHashCode);
    }

    /**
     * Restituisce una stringa con tutte le informazioni del nodo.
     * Le quantità relative alla posizione vengono scritte solo in forma di scacchiera,
     * utilizzando le figure disponibili in UTF-8.
     *
     * Per la visualizzazione delle bitboard, vedi {@link #displayNodeBitBoards}.
     *
     * @return La stringa che contiene la descrizione del nodo.
     * @throws Exception Nel caso di errore nella scrittura delle informazioni.
     */
    public String toStringDebug()
            throws Exception {

        String gameStateString = null;
        String[] utf8BoardRowsArray;
        String fenPositionString;
        String nodeHashCodeString;
        StringBuilder treeNodesListString = null;
        StringBuilder lastIrrvLevelsListString = null;
        String lastIrrvLevelsListLengthString = null;
        String treeHashcodesListLengthString = null;
        StringBuilder countersString;

        StringBuilder levelIndentStringBuilder = new StringBuilder();
//        for (int i = 1; i <= treeLevel; ++i) {
//            levelIndentStringBuilder.append("        ");
//        }
        if (gameState == States.NOT_LEGAL) {
            levelIndentStringBuilder.append("///");
        }
        String levelIndentString = levelIndentStringBuilder.toString();

        try {
            gameStateString = States.toString(gameState);
        } catch (Exception e) {
            e.printStackTrace();
        }

        utf8BoardRowsArray = toUtf8().split("\\n");

        fenPositionString = toFen();

        nodeHashCodeString = String.valueOf(nodeHashCode);

        if (gameState != States.NOT_LEGAL) {

            treeNodesListString = new StringBuilder("{");
            for (int i = 0; i <= treeLevel; ++i) {
                if (i == 0) {
                    treeNodesListString.append(" ");
                } else {
                    treeNodesListString.append(" , ");
                }
                treeNodesListString.append(Engine.treeHashcodesList[i]);
            }
            treeNodesListString.append(" }");

            treeHashcodesListLengthString = String.valueOf(Engine.treeHashcodesList.length);

            lastIrrvLevelsListString = new StringBuilder("{");
            for (int i = 0; i <= treeLevel; ++i) {
                if (i == 0) {
                    lastIrrvLevelsListString.append(" ");
                } else {
                    lastIrrvLevelsListString.append(" , ");
                }
                lastIrrvLevelsListString.append(Engine.lastIrrvLevelsList[i]);
            }
            lastIrrvLevelsListString.append(" }");

            lastIrrvLevelsListLengthString = String.valueOf(Engine.lastIrrvLevelsList.length);

        }

        countersString = new StringBuilder();
        countersString.append("{");
        if (whitePawnsCounter > 0) {
            countersString.append(" wP=")
                    .append(whitePawnsCounter);
        }
        if (whiteKnightsCounter > 0) {
            countersString.append(" wN=")
                    .append(whiteKnightsCounter);
        }
        if (whiteBishopsCounter > 0) {
            countersString.append(" wB=")
                    .append(whiteBishopsCounter);
        }
        if (whiteWFBishopsCounter > 0) {
            countersString.append(" (wF=")
                    .append(whiteWFBishopsCounter)
                    .append(")");
        }
        if (whiteBFBishopsCounter > 0) {
            countersString.append(" (bF=")
                    .append(whiteBFBishopsCounter)
                    .append(")");
        }
        if (whiteRooksCounter > 0) {
            countersString.append(" wR=")
                    .append(whiteRooksCounter);
        }
        if (whiteQueensCounter > 0) {
            countersString.append(" wQ=")
                    .append(whiteQueensCounter);
        }
        countersString.append(" }");
        countersString.append("   ");
        countersString.append("{");
        if (blackPawnsCounter > 0) {
            countersString.append(" bP=")
                    .append(blackPawnsCounter);
        }
        if (blackKnightsCounter > 0) {
            countersString.append(" bN=")
                    .append(blackKnightsCounter);
        }
        if (blackBishopsCounter > 0) {
            countersString.append(" bB=")
                    .append(blackBishopsCounter);
        }
        if (blackWFBishopsCounter > 0) {
            countersString.append(" (wF=")
                    .append(blackWFBishopsCounter)
                    .append(")");
        }
        if (blackBFBishopsCounter > 0) {
            countersString.append(" (bF=")
                    .append(blackBFBishopsCounter)
                    .append(")");
        }
        if (blackRooksCounter > 0) {
            countersString.append(" bR=")
                    .append(blackRooksCounter);
        }
        if (blackQueensCounter > 0) {
            countersString.append(" bQ=")
                    .append(blackQueensCounter);
        }
        countersString.append(" }");

        /* example
        ------------------------------ hashCode: 9199725209105262553 treeLevel: 0 gameState: ONGOING
        |♜|♞|♝|♛|♚|♝|♞|♜|
        |♟|♟|♟|♟|♟|♟|♟|♟|       fenPosition: rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
        | | | | | | | | |       counters: { wP=8 wN=2 wB=2 (wF=1) (bF=1) wR=2 wQ=1 }   { bP=8 bN=2 bB=2 (wF=1) (bF=1) bR=2 bQ=1 }
        | | | | | | | | |
        | | | | | | | | |       treeHashcodesList(length=2000,>>treeLevel): { 9199725209105262553 }
        | | | | | | | | |   #   lastIrrvLevelsList(length=2000,>>treeLevel): { 0 }
        |♙|♙|♙|♙|♙|♙|♙|♙|
        |♖|♘|♗|♕|♔|♗|♘|♖|
         */

        String rec0 = levelIndentString.replace(" ", "-") + "-----------------------"
                + " hashCode: " + nodeHashCodeString + " treeLevel: " + treeLevel + " gameState: " + gameStateString;
        String rec1 = levelIndentString + utf8BoardRowsArray[0];
        String rec2 = levelIndentString + utf8BoardRowsArray[1]
                + "fenPosition: " + fenPositionString;
        String rec3 = levelIndentString + utf8BoardRowsArray[2]
                + "counters: " + countersString;
        String rec4 = levelIndentString + utf8BoardRowsArray[3];
        String rec5 = levelIndentString + utf8BoardRowsArray[4]
                + "treeHashcodesList(length=" + treeHashcodesListLengthString + ",>>treeLevel): " + treeNodesListString;
        String rec6 = levelIndentString + utf8BoardRowsArray[5]
                + "lastIrrvLevelsList(length=" + lastIrrvLevelsListLengthString + ",>>treeLevel): "
                + lastIrrvLevelsListString;
        String rec7 = levelIndentString + utf8BoardRowsArray[6];
        String rec8 = levelIndentString + utf8BoardRowsArray[7];

        return rec0 + "\n" + rec1 + "\n" + rec2 + "\n" + rec3 + "\n" + rec4 + "\n" + rec5
                + "\n" + rec6 + "\n" + rec7 + "\n" + rec8;
    }

    public void displayNodeBitBoards() {

        if (whitePawnsBb   != BitBoards.EMPTY) { System.out.println("whitePawnsBb="   + BitBoards.toString(whitePawnsBb));   }
        if (whiteKnightsBb != BitBoards.EMPTY) { System.out.println("whiteKnightsBb=" + BitBoards.toString(whiteKnightsBb)); }
        if (whiteBishopsBb != BitBoards.EMPTY) { System.out.println("whiteBishopsBb=" + BitBoards.toString(whiteBishopsBb)); }
        if (whiteRooksBb   != BitBoards.EMPTY) { System.out.println("whiteRooksBb="   + BitBoards.toString(whiteRooksBb));   }
        if (whiteQueensBb  != BitBoards.EMPTY) { System.out.println("whiteQueensBb="  + BitBoards.toString(whiteQueensBb));  }
        if (whiteKingBb    != BitBoards.EMPTY) { System.out.println("whiteKingBb="    + BitBoards.toString(whiteKingBb));    }

        if (blackPawnsBb   != BitBoards.EMPTY) { System.out.println("blackPawnsBb="   + BitBoards.toString(blackPawnsBb));   }
        if (blackKnightsBb != BitBoards.EMPTY) { System.out.println("blackKnightsBb=" + BitBoards.toString(blackKnightsBb)); }
        if (blackBishopsBb != BitBoards.EMPTY) { System.out.println("blackBishopsBb=" + BitBoards.toString(blackBishopsBb)); }
        if (blackRooksBb   != BitBoards.EMPTY) { System.out.println("blackRooksBb="   + BitBoards.toString(blackRooksBb));   }
        if (blackQueensBb  != BitBoards.EMPTY) { System.out.println("blackQueensBb="  + BitBoards.toString(blackQueensBb));  }
        if (blackKingBb    != BitBoards.EMPTY) { System.out.println("blackKingBb="    + BitBoards.toString(blackKingBb));    }

    }

    @Override
    public int hashCode() {

        final int prime = 31;

        int result = 1;

        result = prime * result + (int) (whitePawnsBb   ^ (whitePawnsBb   >>> 32));
        result = prime * result + (int) (blackPawnsBb   ^ (blackPawnsBb   >>> 32));
        result = prime * result + (int) (whiteKnightsBb ^ (whiteKnightsBb >>> 32));
        result = prime * result + (int) (blackKnightsBb ^ (blackKnightsBb >>> 32));
        result = prime * result + (int) (whiteBishopsBb ^ (whiteBishopsBb >>> 32));
        result = prime * result + (int) (blackBishopsBb ^ (blackBishopsBb >>> 32));
        result = prime * result + (int) (whiteRooksBb   ^ (whiteRooksBb   >>> 32));
        result = prime * result + (int) (blackRooksBb   ^ (blackRooksBb   >>> 32));
        result = prime * result + (int) (whiteQueensBb  ^ (whiteQueensBb  >>> 32));
        result = prime * result + (int) (blackQueensBb  ^ (blackQueensBb  >>> 32));
        result = prime * result + (int) (whiteKingBb    ^ (whiteKingBb    >>> 32));
        result = prime * result + (int) (blackKingBb    ^ (blackKingBb    >>> 32));

        result = prime * result + playerColor;

        result = prime * result + (whiteShortCg? 1: 0);
        result = prime * result + (whiteLongCg?  1: 0);
        result = prime * result + (blackShortCg? 1: 0);
        result = prime * result + (blackLongCg?  1: 0);

        result = prime * result + ((enPassantSquare == null) ? 0 : enPassantSquare.hashCode());

        return result;
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

        Node other = (Node) obj;

        if (whitePawnsBb   != other.whitePawnsBb)   { return false; }
        if (blackPawnsBb   != other.blackPawnsBb)   { return false; }
        if (whiteBishopsBb != other.whiteBishopsBb) { return false; }
        if (blackBishopsBb != other.blackBishopsBb) { return false; }
        if (whiteKnightsBb != other.whiteKnightsBb) { return false; }
        if (blackKnightsBb != other.blackKnightsBb) { return false; }
        if (whiteRooksBb   != other.whiteRooksBb)   { return false; }
        if (whiteQueensBb  != other.whiteQueensBb)  { return false; }
        if (blackQueensBb  != other.blackQueensBb)  { return false; }
        if (whiteKingBb    != other.whiteKingBb)    { return false; }
        if (blackKingBb    != other.blackKingBb)    { return false; }
        if (blackRooksBb   != other.blackRooksBb)   { return false; }

        if (whiteShortCg != other.whiteShortCg) { return false; }
        if (whiteLongCg  != other.whiteLongCg)  { return false; }
        if (blackShortCg != other.blackShortCg) { return false; }
        if (blackLongCg  != other.blackLongCg)  { return false; }

        if (playerColor != other.playerColor) { return false; }

        if (enPassantSquare == null) {
            return other.enPassantSquare == null;
        } else {
            return enPassantSquare.equals(other.enPassantSquare);
        }
    }

    /**
     * Verifica l'uguaglianza tra due nodi e stampa un messaggio al primo errore.
     *
     * @param obj L'oggetto (il nodo) da confrontare con this.
     */
    public void equalsDebug(Object obj) {

        if (this == obj) {
            System.out.println("equalsDebug: this == obj");
            return;
        }

        if (obj == null) {
            System.out.println("equalsDebug: obj == null");
            return;
        }

        Node other = (Node) obj;

        if (playerColor != other.playerColor) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: playerColor != other.playerColor");
            return;
        }

        if (whiteShortCg != other.whiteShortCg) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: whiteShortCg != other.whiteShortCg");
            return;
        }

        if (whiteLongCg != other.whiteLongCg) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: whiteLongCg != other.whiteLongCg");
            return;
        }

        if (blackShortCg != other.blackShortCg) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: blackShortCg != other.blackShortCg");
            return;
        }

        if (blackLongCg != other.blackLongCg) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: blackLongCg != other.blackLongCg");
            return;
        }

        if (enPassantSquare == null) {
            if (other.enPassantSquare != null) {
                System.out.println("equalsDebug: this=" + this);
                System.out.println("equalsDebug: other=" + other);
                System.out.println("equalsDebug: enPassantSquare == null & other.enPassantSquare != null");
                return;
            }
        } else if (!enPassantSquare.equals(other.enPassantSquare)) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: !enPassantSquare.equals(other.enPassantSquare)");
            return;
        }

        if (halfmovesClock != other.halfmovesClock) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: halfmovesClock != other.halfmovesClock");
            return;
        }

        if (fullmovesNumber != other.fullmovesNumber) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: fullmovesNumber != other.fullmovesNumber");
            return;
        }

//        movesList
//        movesListMaxIndex

        if (gameState != other.gameState) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: fullmovesNumber != gameState != other.gameState");
            return;
        }

        if (treeLevel != other.treeLevel) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: treeLevel != other.treeLevel");
            return;
        }

        if (nodeHashCode != other.nodeHashCode) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: nodeHashCode != other.nodeHashCode");
            return;
        }

        for(int s = 0; s <= Squares.SQUARES_NUMBER - 1; ++ s) {
            if (squarePieceMap[s] != null || other.squarePieceMap[s] != null) {
                if (squarePieceMap[s] == null && other.squarePieceMap[s] != null) {
                    System.out.println("equalsDebug: this=" + this);
                    System.out.println("equalsDebug: other=" + other);
                    System.out.println("equalsDebug: squarePieceMap[" + s + "] != other.squarePieceMap[" + s + "]");
                    return;
                } else if (squarePieceMap[s] != null && other.squarePieceMap[s] == null) {
                    System.out.println("equalsDebug: this=" + this);
                    System.out.println("equalsDebug: other=" + other);
                    System.out.println("equalsDebug: squarePieceMap[" + s + "] != other.squarePieceMap[" + s + "]");
                    return;
                } else if (!squarePieceMap[s].equals(other.squarePieceMap[s])) {
                    System.out.println("equalsDebug: this=" + this);
                    System.out.println("equalsDebug: other=" + other);
                    System.out.println("equalsDebug: squarePieceMap[" + s + "] != other.squarePieceMap[" + s + "]");
                    return;
                }
            }
        }

        if (whitePawnsBb   != other.whitePawnsBb)   {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Bb != other.*Bb -- W P");
            return;
        }

        if (blackPawnsBb   != other.blackPawnsBb)   {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Bb != other.*Bb -- B P");
            return;
        }

        if (whiteKnightsBb != other.whiteKnightsBb) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Bb != other.*Bb -- W N");
            return;
        }

        if (blackKnightsBb != other.blackKnightsBb) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Bb != other.*Bb -- B N");
            return;
        }

        if (whiteBishopsBb != other.whiteBishopsBb) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Bb != other.*Bb -- W B");
            return;
        }

        if (blackBishopsBb != other.blackBishopsBb) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Bb != other.*Bb -- B B");
            return;
        }

        if (whiteRooksBb   != other.whiteRooksBb)   {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Bb != other.*Bb -- W R");
            return;
        }

        if (blackRooksBb   != other.blackRooksBb)   {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Bb != other.*Bb -- B R");
            return;
        }

        if (whiteQueensBb  != other.whiteQueensBb)  {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Bb != other.*Bb -- W Q");
            return;
        }

        if (blackQueensBb  != other.blackQueensBb)  {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Bb != other.*Bb -- B Q");
            return;
        }

        if (whiteKingBb    != other.whiteKingBb)    {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Bb != other.*Bb -- W K");
            return;
        }

        if (blackKingBb    != other.blackKingBb)    {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Bb != other.*Bb -- B K");
            return;
        }

        if (whitePiecesBb  != other.whitePiecesBb)  {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Bb != other.*Bb -- W pieces");
            return;
        }

        if (blackPiecesBb  != other.blackPiecesBb)  {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Bb != other.*Bb -- B pieces");
            return;
        }

        if (allPiecesBb    != other.allPiecesBb)    {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Bb != other.*Bb -- all pieces");
            return;
        }

        if (whitePawnsCounter     != other.whitePawnsCounter     ) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Counter != other.*Counter -- W P");
            return;
        }

        if (blackPawnsCounter     != other.blackPawnsCounter     ) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Counter != other.*Counter -- B P");
            return;
        }

        if (whiteKnightsCounter   != other.whiteKnightsCounter   ) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Counter != other.*Counter -- W N");
            return;
        }

        if (blackKnightsCounter   != other.blackKnightsCounter   ) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Counter != other.*Counter -- B N");
            return;
        }

        if (whiteBishopsCounter   != other.whiteBishopsCounter   ) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Counter != other.*Counter -- W B");
            return;
        }

        if (blackBishopsCounter   != other.blackBishopsCounter   ) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Counter != other.*Counter -- B B");
            return;
        }

        if (whiteWFBishopsCounter != other.whiteWFBishopsCounter ) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Counter != other.*Counter -- W WF B");
            return;
        }

        if (blackWFBishopsCounter != other.blackWFBishopsCounter ) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Counter != other.*Counter -- B WF B");
            return;
        }

        if (whiteBFBishopsCounter != other.whiteBFBishopsCounter ) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Counter != other.*Counter -- W BF B");
            return;
        }

        if (blackBFBishopsCounter != other.blackBFBishopsCounter ) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Counter != other.*Counter -- B BF B");
            return;
        }

        if (whiteRooksCounter     != other.whiteRooksCounter     ) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Counter != other.*Counter -- W R");
            return;
        }

        if (blackRooksCounter     != other.blackRooksCounter     ) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Counter != other.*Counter -- B R");
            return;
        }

        if (whiteQueensCounter    != other.whiteQueensCounter    ) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Counter != other.*Counter -- W Q");
            return;
        }

        if (blackQueensCounter    != other.blackQueensCounter    ) {
            System.out.println("equalsDebug: this=" + this);
            System.out.println("equalsDebug: other=" + other);
            System.out.println("equalsDebug: *Counter != other.*Counter -- B Q");
        }

    }

    /********************************************************************************************************************
     *** translations
     ********************************************************************************************************************/

    public String toFen()
            throws Exception {

        @SuppressWarnings("UnnecessaryLocalVariable")
        String fenPosition = toFenChessboardBb() +
                Fen.ELEMENTS_SEPARATOR +
                toFenPlayerColor() +
                Fen.ELEMENTS_SEPARATOR +
                toFenCastlingFlags() +
                Fen.ELEMENTS_SEPARATOR +
                Squares.toFen(enPassantSquare) +
                Fen.ELEMENTS_SEPARATOR +
                toFenHalfmovesClock() +
                Fen.ELEMENTS_SEPARATOR +
                toFenFullmovesNumber();

        return fenPosition;
    }

    private String toFenChessboardBb()
            throws Exception {

        StringBuilder fenBoard = new StringBuilder();

        byte square;
        Byte piece;

        int emptySqNum = 0;

        for (int r = Squares.RANK_8; r <= Squares.RANK_1; ++r) {
            if (emptySqNum > 0) {
                fenBoard.append(emptySqNum);
                emptySqNum = 0;
            }
            if (r > Squares.RANK_8) {
                fenBoard.append("/");
            }
            for (int f = Squares.FILE_A; f <= Squares.FILE_H; ++f) {
                square = (byte) (/*Squares.newSquare()*/f + Squares.EDGE * r);
                piece = checkSquareBb(square);
                if (piece != null) {
                    if (emptySqNum > 0) {
                        fenBoard.append(emptySqNum);
                        emptySqNum = 0;
                    }
                    fenBoard.append(Pieces.toFen(piece));
                } else {
                    ++emptySqNum;
                }
            }
        }

        if (emptySqNum > 0) {
            fenBoard.append(emptySqNum);
        }

        return fenBoard.toString();
    }

    public Byte checkSquareBb(final byte square) {

        long toSquareBb;

        toSquareBb = (BitBoards.ONE << square);

        if (whitePawnsBb   != BitBoards.EMPTY && (toSquareBb & whitePawnsBb)   == toSquareBb) { return Pieces.WHITE_PAWN;   }
        if (blackPawnsBb   != BitBoards.EMPTY && (toSquareBb & blackPawnsBb)   == toSquareBb) { return Pieces.BLACK_PAWN;   }
        if (whiteKnightsBb != BitBoards.EMPTY && (toSquareBb & whiteKnightsBb) == toSquareBb) { return Pieces.WHITE_KNIGHT; }
        if (blackKnightsBb != BitBoards.EMPTY && (toSquareBb & blackKnightsBb) == toSquareBb) { return Pieces.BLACK_KNIGHT; }
        if (whiteBishopsBb != BitBoards.EMPTY && (toSquareBb & whiteBishopsBb) == toSquareBb) { return Pieces.WHITE_BISHOP; }
        if (blackBishopsBb != BitBoards.EMPTY && (toSquareBb & blackBishopsBb) == toSquareBb) { return Pieces.BLACK_BISHOP; }
        if (whiteRooksBb   != BitBoards.EMPTY && (toSquareBb & whiteRooksBb)   == toSquareBb) { return Pieces.WHITE_ROOK;   }
        if (blackRooksBb   != BitBoards.EMPTY && (toSquareBb & blackRooksBb)   == toSquareBb) { return Pieces.BLACK_ROOK;   }
        if (whiteQueensBb  != BitBoards.EMPTY && (toSquareBb & whiteQueensBb)  == toSquareBb) { return Pieces.WHITE_QUEEN;  }
        if (blackQueensBb  != BitBoards.EMPTY && (toSquareBb & blackQueensBb)  == toSquareBb) { return Pieces.BLACK_QUEEN;  }
        if (whiteKingBb    != BitBoards.EMPTY && (toSquareBb & whiteKingBb)    == toSquareBb) { return Pieces.WHITE_KING;   }
        if (blackKingBb    != BitBoards.EMPTY && (toSquareBb & blackKingBb)    == toSquareBb) { return Pieces.BLACK_KING;   }

        return null;

    }

    private String toFenPlayerColor() {

        if (playerColor == Colors.WHITE) {
            return Fen.WHITE_PLAYER;
        } else {
            return Fen.BLACK_PLAYER;
        }

    }

    private String toFenCastlingFlags() {

        StringBuilder sb = new StringBuilder();

        if (whiteShortCg) { sb.append(Fen.WHITE_SHORT_CASTLING); }
        if (whiteLongCg)  { sb.append(Fen.WHITE_LONG_CASTLING);  }
        if (blackShortCg) { sb.append(Fen.BLACK_SHORT_CASTLING); }
        if (blackLongCg)  { sb.append(Fen.BLACK_LONG_CASTLING);  }

        if (sb.toString().equals("")) {
            sb.append(Fen.NO_CASTLINGS);
        }

        return sb.toString();
    }

    private String toFenHalfmovesClock() {

        return String.valueOf(halfmovesClock);
    }

    private String toFenFullmovesNumber() {

        return String.valueOf(fullmovesNumber);
    }

    private void fromFen(final String fenString)
            throws Exception {

        nodeHashCode = 0;

        String[] fenFieldElementsArray = fenString.split(Fen.ELEMENTS_SEPARATOR);

        if (fenFieldElementsArray.length != Fen.ELEMENTS_NUMBER) {
            throw new Exception("fenFieldElementsArray=" + Arrays.toString(fenFieldElementsArray));
        }

        String fenSquarePieceMap  = fenFieldElementsArray[0];
        String fenPlayerColor     = fenFieldElementsArray[1];
        String fenCastlingFlags   = fenFieldElementsArray[2];
        String fenEnPassantSquare = fenFieldElementsArray[3];
        String fenHalfmovesClock  = fenFieldElementsArray[4];
        String fenFullmovesNumber = fenFieldElementsArray[5];

        // prima perché serve ai successivi
        fromFenColor(fenPlayerColor);

        fromFenSquarePieceMap(  fenSquarePieceMap);
        fromFenCastlingFlags(   fenCastlingFlags);
        fromFenEnPassantSquare( fenEnPassantSquare);
        fromFenHalfmovesClock(  fenHalfmovesClock);
        fromFenFullmovesNumber( fenFullmovesNumber);

    }

    private void fromFenSquarePieceMap(final String fenBoard)
            throws Exception {

        squarePieceMap = new Byte[Squares.SQUARES_NUMBER];

        whitePawnsCounter = 0;
        blackPawnsCounter = 0;
        whiteKnightsCounter = 0;
        blackKnightsCounter = 0;
        whiteBishopsCounter = 0;
        blackBishopsCounter = 0;
        whiteWFBishopsCounter = 0;
        blackWFBishopsCounter = 0;
        whiteBFBishopsCounter = 0;
        blackBFBishopsCounter = 0;
        whiteRooksCounter = 0;
        blackRooksCounter = 0;
        whiteQueensCounter = 0;
        blackQueensCounter = 0;

        int file;
        int rank = Squares.RANK_8;

        byte square;
        byte piece;

        String[] fenRanksList = fenBoard.split(Fen.RANK_SEPARATOR);

        if (fenRanksList.length != Squares.RANKS_NUMBER) {
            throw new Exception("fenRanksList.length " + fenRanksList.length);
        }

        String figureCharsArray = "12345678";

        for (final String fenRankElement : fenRanksList) {
            file = Squares.FILE_A;
            for (final char fenRankElementChar : fenRankElement.toCharArray()) {
                String fenRankElementString = String.valueOf(fenRankElementChar);
                if (figureCharsArray.contains(fenRankElementString)) {
                    file += Integer.parseInt(fenRankElementString);
                } else {
                    piece = Pieces.fromFen(fenRankElementString);
                    square = (byte) (/*Squares.newSquare()*/file + Squares.EDGE * rank);
                    squarePieceMap[square] = piece;
                    updatePiecesBitBoards(square, piece);
                    updateBoardZobristHashCode(square, piece);
                    ++file;
                }
            }
            ++rank;
        }

        setAllPiecesBb();

    }

    private void updatePiecesBitBoards(final byte square, final byte piece)
            throws Exception {

        long pieceBb = (BitBoards.ONE << square);

        switch (piece) {
        case Pieces.WHITE_PAWN:   whitePawnsBb   |= pieceBb; ++whitePawnsCounter;   break;
        case Pieces.BLACK_PAWN:   blackPawnsBb   |= pieceBb; ++blackPawnsCounter;   break;
        case Pieces.WHITE_KNIGHT: whiteKnightsBb |= pieceBb; ++whiteKnightsCounter; break;
        case Pieces.BLACK_KNIGHT: blackKnightsBb |= pieceBb; ++blackKnightsCounter; break;
        case Pieces.WHITE_BISHOP: whiteBishopsBb |= pieceBb; ++whiteBishopsCounter;
            if ((pieceBb & BitBoards.WHITE_FIELDS) == pieceBb) {
                ++whiteWFBishopsCounter;
            } else {
                ++whiteBFBishopsCounter;
            }
            break;
        case Pieces.BLACK_BISHOP: blackBishopsBb |= pieceBb; ++blackBishopsCounter;
        if ((pieceBb & BitBoards.WHITE_FIELDS) == pieceBb) {
            ++blackWFBishopsCounter;
        } else {
            ++blackBFBishopsCounter;
        }
            break;
        case Pieces.WHITE_ROOK:   whiteRooksBb   |= pieceBb; ++whiteRooksCounter;   break;
        case Pieces.BLACK_ROOK:   blackRooksBb   |= pieceBb; ++blackRooksCounter;   break;
        case Pieces.WHITE_QUEEN:  whiteQueensBb  |= pieceBb; ++whiteQueensCounter;  break;
        case Pieces.BLACK_QUEEN:  blackQueensBb  |= pieceBb; ++blackQueensCounter;  break;
        case Pieces.WHITE_KING:   whiteKingBb    |= pieceBb;                        break;
        case Pieces.BLACK_KING:   blackKingBb    |= pieceBb;                        break;
        default:
            throw new Exception("piece=" + piece);
        }

        if (piece > 0) {
            whitePiecesBb |= pieceBb;
        } else {
            blackPiecesBb |= pieceBb;
        }

    }

    private void updateBoardZobristHashCode(final byte square, final byte piece)
            throws Exception {

        switch (piece) {
        case Pieces.WHITE_PAWN:   nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_PAWN_IX]   [square]; break;
        case Pieces.BLACK_PAWN:   nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_PAWN_IX]   [square]; break;
        case Pieces.WHITE_KNIGHT: nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_KNIGHT_IX] [square]; break;
        case Pieces.BLACK_KNIGHT: nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_KNIGHT_IX] [square]; break;
        case Pieces.WHITE_BISHOP: nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_BISHOP_IX] [square]; break;
        case Pieces.BLACK_BISHOP: nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_BISHOP_IX] [square]; break;
        case Pieces.WHITE_ROOK:   nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_ROOK_IX]   [square]; break;
        case Pieces.BLACK_ROOK:   nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_ROOK_IX]   [square]; break;
        case Pieces.WHITE_QUEEN:  nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_QUEEN_IX]  [square]; break;
        case Pieces.BLACK_QUEEN:  nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_QUEEN_IX]  [square]; break;
        case Pieces.WHITE_KING:   nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_KING_IX]   [square]; break;
        case Pieces.BLACK_KING:   nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_KING_IX]   [square]; break;
        default:
            throw new Exception("piece=" + piece);
        }

    }

    private void setAllPiecesBb() {

        allPiecesBb = whitePiecesBb | blackPiecesBb;

    }

    private void fromFenColor(final String fenColor)
            throws Exception {

        switch (fenColor) {
        case Fen.WHITE_PLAYER:
            playerColor = Colors.WHITE;
            break;
        case Fen.BLACK_PLAYER:
            playerColor = Colors.BLACK;
            nodeHashCode ^= Zobrist.BLACK_PLAYER_RANDOM;
            break;
        default:
            throw new Exception("fenColor=" + fenColor);
        }

    }

    private void fromFenCastlingFlags(final String fenCastlingFlags)
            throws Exception {

        for (final char fenCastlingFlag : fenCastlingFlags.toCharArray()) {

            switch (String.valueOf(fenCastlingFlag)) {
            case Fen.NO_CASTLINGS:
                break;
            case Fen.WHITE_SHORT_CASTLING:
                whiteShortCg = true;
                nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.WHITE_SHORT_CG_IX];
                break;
            case Fen.BLACK_SHORT_CASTLING:
                blackShortCg = true;
                nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.BLACK_SHORT_CG_IX];
                break;
            case Fen.WHITE_LONG_CASTLING:
                whiteLongCg = true;
                nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.WHITE_LONG_CG_IX];
                break;
            case Fen.BLACK_LONG_CASTLING:
                blackLongCg = true;
                nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.BLACK_LONG_CG_IX];
                break;
            default:
                throw new Exception("fenCastlingFlag=" + fenCastlingFlag);
            }

        }

    }

    private void fromFenEnPassantSquare(final String fenEnPassantSquare)
            throws Exception {

        enPassantSquare = null;

        if (!fenEnPassantSquare.equals(Fen.NO_EN_PASSANT)) {
            enPassantSquare = Squares.fromSan(fenEnPassantSquare);
            nodeHashCode ^= Zobrist.EN_PASSANT_RANDOMS_LIST[/*Squares.file()*/enPassantSquare % Squares.EDGE];
        }

    }

    private void fromFenHalfmovesClock(final String fenHalfmovesClock) {

        halfmovesClock = Short.parseShort(fenHalfmovesClock);

    }

    private void fromFenFullmovesNumber(final String fenFullmovesNumber) {

        fullmovesNumber = Short.parseShort(fenFullmovesNumber);
    }

    public String toAlpha2WithField()
            throws Exception {

        StringBuilder sb = new StringBuilder();

        sb.append(Alpha2.TOP_EXT_EDGE);
        sb.append("\n");
        sb.append(Alpha2.TOP_BOARD_EDGE);

        if (playerColor == Colors.BLACK) {
            sb.append(Alpha2.PLAYER_FLAG);
        }

        sb.append("\n");

        for (int r = Squares.RANK_8; r <= Squares.RANK_1; ++r) {
            sb.append(Alpha2.LEFT_EXT_BOARD_EDGE);
            for (int f = Squares.FILE_A; f <= Squares.FILE_H; ++f) {
                sb.append(toAlpha2WithFieldSquare((byte) (/*Squares.newSquare()*/f + Squares.EDGE * r)));
            }
            sb.append(Alpha2.RIGHT_EXT_BOARD_EDGE);
            sb.append("\n");
        }

        sb.append(Alpha2.BOTTOM_BOARD_EDGE);

        if (playerColor == Colors.WHITE) {
            sb.append(Alpha2.PLAYER_FLAG);
        }

        sb.append("\n");
        sb.append(Alpha2.BOTTOM_EXT_EDGE);

        return sb.toString();
    }

    String toAlpha2WithFieldSquare(final byte square)
            throws Exception {

        String squareString;
        Byte piece;

        piece = checkSquareBb(square);

        if (piece != null) {
            squareString = Pieces.toAlpha2WithField(piece, Squares.color(square));
        } else {
            squareString = Squares.toAlpha2WithField(square);
        }

        return squareString;
    }

    private String toUtf8()
            throws Exception {

        StringBuilder sb = new StringBuilder();

        for (int r = Squares.RANK_8; r <= Squares.RANK_1; ++r) {
            sb.append(Utf8.SQUARE_SEPARATOR);
            for (int f = Squares.FILE_A; f <= Squares.FILE_H; ++f) {
                sb.append(toUTF8Square((byte) (/*newSquare()*/f + Squares.EDGE * r)));
            }
            if (r == Squares.RANK_1 && playerColor == Colors.WHITE) {
                sb.append("   " + Utf8.PLAYER_FLAG + "   ");
            } else if (r == Squares.RANK_8 && playerColor == Colors.BLACK) {
                sb.append("   " + Utf8.PLAYER_FLAG + "   ");
            } else {
                sb.append("       ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private String toUTF8Square(final byte square)
            throws Exception {

        String squareString;
        Byte piece;

        piece = checkSquareBb(square);

        if (piece != null) {
            squareString = Pieces.toUtf8(piece);
        } else {
            squareString = Squares.toUTF8(square);
        }

        return squareString + Utf8.SQUARE_SEPARATOR;
    }

    /********************************************************************************************************************
     *** do move
     ********************************************************************************************************************/

    public void doMove(final Move move)
            throws Exception {

        Byte newEnPassantSquare = null;
        byte removeSquare;
        short parentNodeTreeLevel;

        switch (move.function) {
        // ordinati per frequenza
        case Functions.MOVEMENT:
            if (whiteShortCg || whiteLongCg
                    || blackShortCg || blackLongCg) {
                doUpdateCastlingFlagsStep(move);
            }
            doStepMap(move);
            doStepBb(move);
            switch (move.piece) {
            case Pieces.WHITE_PAWN:
                if (/*Squares.rank()*/move.fromSquare / Squares.EDGE == Squares.RANK_2
                        && /*Squares.rank()*/move.toSquare / Squares.EDGE == Squares.RANK_4) {
                    newEnPassantSquare = (byte) (move.fromSquare + Squares.NORTH_STEP);
                }
                halfmovesClock = 0;
                break;
            case Pieces.BLACK_PAWN:
                if (/*Squares.rank()*/move.fromSquare / Squares.EDGE == Squares.RANK_7
                        && /*Squares.rank()*/move.toSquare / Squares.EDGE == Squares.RANK_5) {
                    newEnPassantSquare = (byte) (move.fromSquare + Squares.SOUTH_STEP);
                }
                halfmovesClock = 0;
                break;
            default:
                ++halfmovesClock;
                break;
            }
            break;
        case Functions.CAPTURE:
            if (whiteShortCg || whiteLongCg
                    || blackShortCg || blackLongCg) {
                doUpdateCastlingFlagsStep(move);
                doUpdateCastlingFlagsCaptured(move);
            }
            doStepMap(move);
            doStepBb(move);
            doCaptureBb(move);
            halfmovesClock = 0;
            break;
        case Functions.SHORT_CG:
            switch (playerColor) {
            case Colors.WHITE:
                doUpdateCastlingFlagsWhiteCastling();
                doWhiteShortCgMap();
                doWhiteShortCgBb();
                break;
            case Colors.BLACK:
                doUpdateCastlingFlagsBlackCastling();
                doBlackShortCgMap();
                doBlackShortCgBb();
                break;
            default:
                throw new Exception("playerColor=" + playerColor);
            }
            ++halfmovesClock;
            break;
        case Functions.LONG_CG:
            switch (playerColor) {
            case Colors.WHITE:
                doUpdateCastlingFlagsWhiteCastling();
                doWhiteLongCgMap();
                doWhiteLongCgBb();
                break;
            case Colors.BLACK:
                doUpdateCastlingFlagsBlackCastling();
                doBlackLongCgMap();
                doBlackLongCgBb();
                break;
            default:
                throw new Exception("playerColor=" + playerColor);
            }
            ++halfmovesClock;
            break;
        case Functions.EN_PASSANT:
            switch (playerColor) {
            case Colors.WHITE: removeSquare = (byte) (move.toSquare + Squares.SOUTH_STEP); break;
            case Colors.BLACK: removeSquare = (byte) (move.toSquare + Squares.NORTH_STEP); break;
            default:
                throw new Exception("playerColor=" + playerColor);
            }
            doEnPassantMap(move, removeSquare);
            doEnPassantBb(move, removeSquare);
            halfmovesClock = 0;
            break;
        default:
            throw new Exception("move.function=" + move.function);
        }

        if (enPassantSquare != null) {
            nodeHashCode ^= Zobrist.EN_PASSANT_RANDOMS_LIST[/*Squares.file()*/enPassantSquare % Squares.EDGE];
            enPassantSquare = null;
        }

        if (newEnPassantSquare != null) {
            enPassantSquare = newEnPassantSquare;
            nodeHashCode ^= Zobrist.EN_PASSANT_RANDOMS_LIST[/*Squares.file()*/enPassantSquare % Squares.EDGE];
        }

        setAllPiecesBb();

        playerColor /*Colors.switchColor()*/ *= Colors.BLACK;
        nodeHashCode ^= Zobrist.BLACK_PLAYER_RANDOM;

        if (playerColor == Colors.WHITE) {
            ++fullmovesNumber;
        }

        parentNodeTreeLevel = Engine.lastIrrvLevelsList[treeLevel];

        ++treeLevel;

        checkLegalState();

        if (gameState == States.NOT_LEGAL) {
            return;
        }

        Engine.treeHashcodesList[treeLevel] = nodeHashCode;

        switch (move.function) {
        case Functions.MOVEMENT:
            if (/*getRole()*/Math.abs(move.piece) == Pieces.ROLE_PAWN) {
                Engine.lastIrrvLevelsList[treeLevel] = treeLevel;
            } else {
                Engine.lastIrrvLevelsList[treeLevel] = parentNodeTreeLevel;
            }
            break;
        case Functions.CAPTURE:
        case Functions.EN_PASSANT:
            Engine.lastIrrvLevelsList[treeLevel] = treeLevel;
            break;
        default:
            Engine.lastIrrvLevelsList[treeLevel] = parentNodeTreeLevel;
        }

        if (gameState == States.ONGOING
                || gameState == States.CHECK) {
            // necessita gestione TREE_HASHCODES_LIST
            computeGameState(move);
        }

    }

    private void doStepMap(final Move move) {

        squarePieceMap[move.fromSquare] = null;

        if (move.promotionPiece == null) {
            squarePieceMap[move.toSquare] = move.piece;
        } else {
            squarePieceMap[move.toSquare] = move.promotionPiece;
        }

    }

    private void doStepBb(final Move move)
            throws Exception {

        final long fromSquareBb, toSquareBb;

        fromSquareBb = (BitBoards.ONE << move.fromSquare);
        toSquareBb = (BitBoards.ONE << move.toSquare);

        if (move.promotionPiece == null) {
            switch (move.piece) {
            case Pieces.WHITE_PAWN:
                whitePawnsBb &= ~fromSquareBb;
                whitePawnsBb |= toSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_PAWN_IX][move.fromSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_PAWN_IX][move.toSquare];
                break;
            case Pieces.BLACK_PAWN:
                blackPawnsBb &= ~fromSquareBb;
                blackPawnsBb |= toSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_PAWN_IX][move.fromSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_PAWN_IX][move.toSquare];
                break;
            case Pieces.WHITE_KNIGHT:
                whiteKnightsBb &= ~fromSquareBb;
                whiteKnightsBb |= toSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_KNIGHT_IX][move.fromSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_KNIGHT_IX][move.toSquare];
                break;
            case Pieces.BLACK_KNIGHT:
                blackKnightsBb &= ~fromSquareBb;
                blackKnightsBb |= toSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_KNIGHT_IX][move.fromSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_KNIGHT_IX][move.toSquare];
                break;
            case Pieces.WHITE_BISHOP:
                whiteBishopsBb &= ~fromSquareBb;
                whiteBishopsBb |= toSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_BISHOP_IX][move.fromSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_BISHOP_IX][move.toSquare];
                break;
            case Pieces.BLACK_BISHOP:
                blackBishopsBb &= ~fromSquareBb;
                blackBishopsBb |= toSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_BISHOP_IX][move.fromSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_BISHOP_IX][move.toSquare];
                break;
            case Pieces.WHITE_ROOK:
                whiteRooksBb &= ~fromSquareBb;
                whiteRooksBb |= toSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_ROOK_IX][move.fromSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_ROOK_IX][move.toSquare];
                break;
            case Pieces.BLACK_ROOK:
                blackRooksBb &= ~fromSquareBb;
                blackRooksBb |= toSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_ROOK_IX][move.fromSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_ROOK_IX][move.toSquare];
                break;
            case Pieces.WHITE_QUEEN:
                whiteQueensBb &= ~fromSquareBb;
                whiteQueensBb |= toSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_QUEEN_IX][move.fromSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_QUEEN_IX][move.toSquare];
                break;
            case Pieces.BLACK_QUEEN:
                blackQueensBb &= ~fromSquareBb;
                blackQueensBb |= toSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_QUEEN_IX][move.fromSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_QUEEN_IX][move.toSquare];
                break;
            case Pieces.WHITE_KING:
                whiteKingBb &= ~fromSquareBb;
                whiteKingBb |= toSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_KING_IX][move.fromSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_KING_IX][move.toSquare];
                break;
            case Pieces.BLACK_KING:
                blackKingBb &= ~fromSquareBb;
                blackKingBb |= toSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_KING_IX][move.fromSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_KING_IX][move.toSquare];
                break;
            default:
                throw new Exception("move.piece=" + move.piece);
            }
        } else {
            switch (move.piece) {
            case Pieces.WHITE_PAWN:
                whitePawnsBb &= ~fromSquareBb;
                --whitePawnsCounter;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_PAWN_IX][move.fromSquare];
                break;
            case Pieces.BLACK_PAWN:
                blackPawnsBb &= ~fromSquareBb;
                --blackPawnsCounter;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_PAWN_IX][move.fromSquare];
                break;
            default:
                throw new Exception("move.piece=" + move.piece);
            }
            switch (move.promotionPiece) {
            case Pieces.WHITE_KNIGHT:
                whiteKnightsBb |= toSquareBb;
                ++whiteKnightsCounter;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_KNIGHT_IX][move.toSquare];
                break;
            case Pieces.BLACK_KNIGHT:
                blackKnightsBb |= toSquareBb;
                ++blackKnightsCounter;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_KNIGHT_IX][move.toSquare];
                break;
            case Pieces.WHITE_BISHOP:
                whiteBishopsBb |= toSquareBb;
                ++whiteBishopsCounter;
                if ((toSquareBb & BitBoards.WHITE_FIELDS) == toSquareBb) {
                    ++whiteWFBishopsCounter;
                } else {
                    ++whiteBFBishopsCounter;
                }
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_BISHOP_IX][move.toSquare];
                break;
            case Pieces.BLACK_BISHOP:
                blackBishopsBb |= toSquareBb;
                ++blackBishopsCounter;
                if ((toSquareBb & BitBoards.WHITE_FIELDS) == toSquareBb) {
                    ++blackWFBishopsCounter;
                } else {
                    ++blackBFBishopsCounter;
                }
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_BISHOP_IX][move.toSquare];
                break;
            case Pieces.WHITE_ROOK:
                whiteRooksBb |= toSquareBb;
                ++whiteRooksCounter;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_ROOK_IX][move.toSquare];
                break;
            case Pieces.BLACK_ROOK:
                blackRooksBb |= toSquareBb;
                ++blackRooksCounter;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_ROOK_IX][move.toSquare];
                break;
            case Pieces.WHITE_QUEEN:
                whiteQueensBb |= toSquareBb;
                ++whiteQueensCounter;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_QUEEN_IX][move.toSquare];
                break;
            case Pieces.BLACK_QUEEN:
                blackQueensBb |= toSquareBb;
                ++blackQueensCounter;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_QUEEN_IX][move.toSquare];
                break;
            default:
                throw new Exception("move.promotionPiece=" + move.promotionPiece);
            }
        }

        switch (playerColor) {
        case Colors.WHITE: whitePiecesBb &= ~fromSquareBb; whitePiecesBb |= toSquareBb; break;
        case Colors.BLACK: blackPiecesBb &= ~fromSquareBb; blackPiecesBb |= toSquareBb; break;
        default:
            throw new Exception("playerColor=" + playerColor);
        }

    }

    private void doUpdateCastlingFlagsStep(final Move move) {

        switch (move.piece) {
        case Pieces.WHITE_ROOK:
            switch (move.fromSquare) {
            case Squares.SQUARE_H1:
                if (whiteShortCg) {
                    whiteShortCg = false;
                    nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.WHITE_SHORT_CG_IX];
                }
                break;
            case Squares.SQUARE_A1:
                if (whiteLongCg) {
                    whiteLongCg = false;
                    nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.WHITE_LONG_CG_IX];
                }
                break;
            }
            break;
        case Pieces.BLACK_ROOK:
            switch (move.fromSquare) {
            case Squares.SQUARE_H8:
                if (blackShortCg) {
                    blackShortCg = false;
                    nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.BLACK_SHORT_CG_IX];
                }
                break;
            case Squares.SQUARE_A8:
                if (blackLongCg) {
                    blackLongCg = false;
                    nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.BLACK_LONG_CG_IX];
                }
                break;
            }
            break;
        case Pieces.WHITE_KING:
            if (whiteShortCg) {
                whiteShortCg = false;
                nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.WHITE_SHORT_CG_IX];
            }
            if (whiteLongCg) {
                whiteLongCg = false;
                nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.WHITE_LONG_CG_IX];
            }
            break;
        case Pieces.BLACK_KING:
            if (blackShortCg) {
                blackShortCg = false;
                nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.BLACK_SHORT_CG_IX];
            }
            if (blackLongCg) {
                blackLongCg = false;
                nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.BLACK_LONG_CG_IX];
            }
            break;
        }

    }

    private void doCaptureBb(final Move move)
            throws Exception {

        long toSquareBb;

        toSquareBb = (BitBoards.ONE << move.toSquare);

        switch (move.targetPiece) {
        case Pieces.WHITE_PAWN:
            whitePawnsBb &= ~toSquareBb;
            --whitePawnsCounter;
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_PAWN_IX][move.toSquare];
            break;
        case Pieces.BLACK_PAWN:
            blackPawnsBb &= ~toSquareBb;
            --blackPawnsCounter;
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_PAWN_IX][move.toSquare];
            break;
        case Pieces.WHITE_KNIGHT:
            whiteKnightsBb &= ~toSquareBb;
            --whiteKnightsCounter;
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_KNIGHT_IX][move.toSquare];
            break;
        case Pieces.BLACK_KNIGHT:
            blackKnightsBb &= ~toSquareBb;
            --blackKnightsCounter;
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_KNIGHT_IX][move.toSquare];
            break;
        case Pieces.WHITE_BISHOP:
            whiteBishopsBb &= ~toSquareBb;
            --whiteBishopsCounter;
            if ((toSquareBb & BitBoards.WHITE_FIELDS) == toSquareBb) {
                --whiteWFBishopsCounter;
            } else {
                --whiteBFBishopsCounter;
            }
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_BISHOP_IX][move.toSquare];
            break;
        case Pieces.BLACK_BISHOP:
            blackBishopsBb &= ~toSquareBb;
            --blackBishopsCounter;
            if ((toSquareBb & BitBoards.WHITE_FIELDS) == toSquareBb) {
                --blackWFBishopsCounter;
            } else {
                --blackBFBishopsCounter;
            }
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_BISHOP_IX][move.toSquare];
            break;
        case Pieces.WHITE_ROOK:
            whiteRooksBb &= ~toSquareBb;
            --whiteRooksCounter;
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_ROOK_IX][move.toSquare];
            break;
        case Pieces.BLACK_ROOK:
            blackRooksBb &= ~toSquareBb;
            --blackRooksCounter;
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_ROOK_IX][move.toSquare];
            break;
        case Pieces.WHITE_QUEEN:
            whiteQueensBb &= ~toSquareBb;
            --whiteQueensCounter;
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_QUEEN_IX][move.toSquare];
            break;
        case Pieces.BLACK_QUEEN:
            blackQueensBb &= ~toSquareBb;
            --blackQueensCounter;
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_QUEEN_IX][move.toSquare];
            break;
        default:
            throw new Exception("move.targetPiece=" + move.targetPiece);
        }

        switch (playerColor) {
        case Colors.WHITE: blackPiecesBb &= ~toSquareBb; break;
        case Colors.BLACK: whitePiecesBb &= ~toSquareBb; break;
        default:
            throw new Exception("playerColor=" + playerColor);
        }

    }

    private void doUpdateCastlingFlagsCaptured(final Move move) {

        switch (move.targetPiece) {
        case Pieces.WHITE_ROOK:
            switch (move.toSquare) {
            case Squares.SQUARE_H1:
                if (whiteShortCg) {
                    whiteShortCg = false;
                    nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.WHITE_SHORT_CG_IX];
                }
                break;
            case Squares.SQUARE_A1:
                if (whiteLongCg) {
                    whiteLongCg = false;
                    nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.WHITE_LONG_CG_IX];
                }
                break;
            }
            break;
        case Pieces.BLACK_ROOK:
            switch (move.toSquare) {
            case Squares.SQUARE_H8:
                if (blackShortCg) {
                    blackShortCg = false;
                    nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.BLACK_SHORT_CG_IX];
                }
                break;
            case Squares.SQUARE_A8:
                if (blackLongCg) {
                    blackLongCg = false;
                    nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.BLACK_LONG_CG_IX];
                }
                break;
            }
            break;
        }

    }

    private void doWhiteShortCgMap() {

        squarePieceMap[Squares.SQUARE_E1] = null;
        squarePieceMap[Squares.SQUARE_G1] = Pieces.WHITE_KING;
        squarePieceMap[Squares.SQUARE_H1] = null;
        squarePieceMap[Squares.SQUARE_F1] = Pieces.WHITE_ROOK;

    }

    private void doWhiteShortCgBb() {

        whiteKingBb  &= ~BitBoards.SQUARE_E1;
        whiteKingBb  |=  BitBoards.SQUARE_G1;
        whiteRooksBb &= ~BitBoards.SQUARE_H1;
        whiteRooksBb |=  BitBoards.SQUARE_F1;

        whitePiecesBb &= ~BitBoards.SQUARE_E1;
        whitePiecesBb |=  BitBoards.SQUARE_G1;
        whitePiecesBb &= ~BitBoards.SQUARE_H1;
        whitePiecesBb |=  BitBoards.SQUARE_F1;

        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_KING_IX] [Squares.SQUARE_E1];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_KING_IX] [Squares.SQUARE_G1];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_ROOK_IX] [Squares.SQUARE_H1];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_ROOK_IX] [Squares.SQUARE_F1];

    }

    private void doBlackShortCgMap() {

        squarePieceMap[Squares.SQUARE_E8] = null;
        squarePieceMap[Squares.SQUARE_G8] = Pieces.BLACK_KING;
        squarePieceMap[Squares.SQUARE_H8] = null;
        squarePieceMap[Squares.SQUARE_F8] = Pieces.BLACK_ROOK;

    }

    private void doBlackShortCgBb() {

        blackKingBb  &= ~BitBoards.SQUARE_E8;
        blackKingBb  |=  BitBoards.SQUARE_G8;
        blackRooksBb &= ~BitBoards.SQUARE_H8;
        blackRooksBb |=  BitBoards.SQUARE_F8;

        blackPiecesBb &= ~BitBoards.SQUARE_E8;
        blackPiecesBb |=  BitBoards.SQUARE_G8;
        blackPiecesBb &= ~BitBoards.SQUARE_H8;
        blackPiecesBb |=  BitBoards.SQUARE_F8;

        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_KING_IX] [Squares.SQUARE_E8];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_KING_IX] [Squares.SQUARE_G8];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_ROOK_IX] [Squares.SQUARE_H8];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_ROOK_IX] [Squares.SQUARE_F8];

    }

    private void doWhiteLongCgMap() {

        squarePieceMap[Squares.SQUARE_E1] = null;
        squarePieceMap[Squares.SQUARE_C1] = Pieces.WHITE_KING;
        squarePieceMap[Squares.SQUARE_A1] = null;
        squarePieceMap[Squares.SQUARE_D1] = Pieces.WHITE_ROOK;

    }

    private void doWhiteLongCgBb() {

        whiteKingBb  &= ~BitBoards.SQUARE_E1;
        whiteKingBb  |=  BitBoards.SQUARE_C1;
        whiteRooksBb &= ~BitBoards.SQUARE_A1;
        whiteRooksBb |=  BitBoards.SQUARE_D1;

        whitePiecesBb &= ~BitBoards.SQUARE_E1;
        whitePiecesBb |=  BitBoards.SQUARE_C1;
        whitePiecesBb &= ~BitBoards.SQUARE_A1;
        whitePiecesBb |=  BitBoards.SQUARE_D1;

        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_KING_IX] [Squares.SQUARE_E1];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_KING_IX] [Squares.SQUARE_C1];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_ROOK_IX] [Squares.SQUARE_A1];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_ROOK_IX] [Squares.SQUARE_D1];

    }

    private void doBlackLongCgMap() {

        squarePieceMap[Squares.SQUARE_E8] = null;
        squarePieceMap[Squares.SQUARE_C8] = Pieces.BLACK_KING;
        squarePieceMap[Squares.SQUARE_A8] = null;
        squarePieceMap[Squares.SQUARE_D8] = Pieces.BLACK_ROOK;

    }

    private void doBlackLongCgBb() {

        blackKingBb  &= ~BitBoards.SQUARE_E8;
        blackKingBb  |=  BitBoards.SQUARE_C8;
        blackRooksBb &= ~BitBoards.SQUARE_A8;
        blackRooksBb |=  BitBoards.SQUARE_D8;

        blackPiecesBb &= ~BitBoards.SQUARE_E8;
        blackPiecesBb |=  BitBoards.SQUARE_C8;
        blackPiecesBb &= ~BitBoards.SQUARE_A8;
        blackPiecesBb |=  BitBoards.SQUARE_D8;

        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_KING_IX] [Squares.SQUARE_E8];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_KING_IX] [Squares.SQUARE_C8];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_ROOK_IX] [Squares.SQUARE_A8];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_ROOK_IX] [Squares.SQUARE_D8];

    }

    private void doUpdateCastlingFlagsWhiteCastling() {

        if (whiteShortCg) {
            whiteShortCg = false;
            nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.WHITE_SHORT_CG_IX];
        }

        if (whiteLongCg) {
            whiteLongCg = false;
            nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.WHITE_LONG_CG_IX];
        }

    }

    private void doUpdateCastlingFlagsBlackCastling() {

        if (blackShortCg) {
            blackShortCg = false;
            nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.BLACK_SHORT_CG_IX];
        }

        if (blackLongCg) {
            blackLongCg = false;
            nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.BLACK_LONG_CG_IX];
        }

    }

    private void doEnPassantMap(final Move move, final int removeSquare) {

        squarePieceMap[move.fromSquare] = null;
        squarePieceMap[move.toSquare] = move.piece;

        squarePieceMap[removeSquare] = null;

    }

    private void doEnPassantBb(final Move move, final int removeSquare)
            throws Exception {

        long fromSquareBb, toSquareBb, removeSquareBb;

        fromSquareBb   = (BitBoards.ONE << move.fromSquare);
        toSquareBb     = (BitBoards.ONE << move.toSquare);
        removeSquareBb = (BitBoards.ONE << removeSquare);

        switch (move.piece) {
        case Pieces.WHITE_PAWN:
            whitePawnsBb &= ~fromSquareBb; whitePawnsBb |= toSquareBb;
            blackPawnsBb &= ~removeSquareBb; --blackPawnsCounter;
            //
            whitePiecesBb &= ~fromSquareBb; whitePiecesBb |= toSquareBb;
            blackPiecesBb &= ~removeSquareBb;
            //
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_PAWN_IX] [move.fromSquare];
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_PAWN_IX] [move.toSquare];
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_PAWN_IX] [removeSquare];
            break;
        case Pieces.BLACK_PAWN:
            blackPawnsBb &= ~fromSquareBb; blackPawnsBb |= toSquareBb;
            whitePawnsBb &= ~removeSquareBb; --whitePawnsCounter;
            //
            blackPiecesBb &= ~fromSquareBb; blackPiecesBb |= toSquareBb;
            whitePiecesBb &= ~removeSquareBb;
            //
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_PAWN_IX] [move.fromSquare];
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_PAWN_IX] [move.toSquare];
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_PAWN_IX] [removeSquare];
            break;
        default:
            throw new Exception("move.piece=" + move.piece);
        }

    }

    /********************************************************************************************************************
     *** undo move
     ********************************************************************************************************************/

    public void undoMove(final Move move, final boolean prevWhiteShortCg, final boolean prevWhiteLongCg,
            final boolean prevBlackShortCg, final boolean prevBlackLongCg, final Byte prevEnPassantSquare,
            final short prevHalfmovesClock, final byte prevGameState)
                    throws Exception {

        int removeSquare;

        switch (move.function) {
        case Functions.MOVEMENT:
            undoStepMap(move);
            undoStepBb(move);
            break;
        case Functions.CAPTURE:
            undoCaptureMap(move);
            undoStepBb(move);
            undoCaptureBb(move);
            break;
        case Functions.SHORT_CG:
            switch (playerColor) {
            case Colors.WHITE:
                undoBlackShortCgMap();
                undoBlackShortCgBb();
                break;
            case Colors.BLACK:
                undoWhiteShortCgMap();
                undoWhiteShortCgBb();
                break;
            default:
                throw new Exception("playerColor=" + playerColor);
            }
            break;
        case Functions.LONG_CG:
            switch (playerColor) {
            case Colors.WHITE:
                undoBlackLongCgMap();
                undoBlackLongCgBb();
                break;
            case Colors.BLACK:
                undoWhiteLongCgMap();
                undoWhiteLongCgBb();
                break;
            default:
                throw new Exception("playerColor=" + playerColor);
            }
            break;
        case Functions.EN_PASSANT:
            switch (playerColor) {
            case Colors.WHITE: removeSquare = move.toSquare + Squares.NORTH_STEP; break;
            case Colors.BLACK: removeSquare = move.toSquare + Squares.SOUTH_STEP; break;
            default:
                throw new Exception("playerColor=" + playerColor);
            }
            undoEnPassantMap(move, removeSquare);
            undoEnPassantBb(move, removeSquare);
            break;
        }

        setAllPiecesBb();

        playerColor /*Colors.switchColor()*/ *= Colors.BLACK;
        nodeHashCode ^= Zobrist.BLACK_PLAYER_RANDOM;

        if (playerColor == Colors.BLACK) {
            --fullmovesNumber;
        }

        --treeLevel;

        if (whiteShortCg != prevWhiteShortCg) {
            nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.WHITE_SHORT_CG_IX];
        }

        if (whiteLongCg != prevWhiteLongCg) {
            nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.WHITE_LONG_CG_IX];
        }

        if (blackShortCg != prevBlackShortCg) {
            nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.BLACK_SHORT_CG_IX];
        }

        if (blackLongCg != prevBlackLongCg) {
            nodeHashCode ^= Zobrist.CASTLINGS_RANDOMS_LIST[Zobrist.BLACK_LONG_CG_IX];
        }

        whiteShortCg = prevWhiteShortCg;
        whiteLongCg  = prevWhiteLongCg;
        blackShortCg = prevBlackShortCg;
        blackLongCg  = prevBlackLongCg;

        if (enPassantSquare != null) {
            nodeHashCode ^= Zobrist.EN_PASSANT_RANDOMS_LIST[/*Squares.file()*/enPassantSquare % Squares.EDGE];
        }

        enPassantSquare = prevEnPassantSquare;

        if (enPassantSquare != null) {
            nodeHashCode ^= Zobrist.EN_PASSANT_RANDOMS_LIST[/*Squares.file()*/enPassantSquare % Squares.EDGE];
        }

        halfmovesClock = prevHalfmovesClock;

        gameState = prevGameState;

    }

    private void undoStepMap(final Move move) {

        squarePieceMap[move.fromSquare] = move.piece;
        squarePieceMap[move.toSquare] = null;

    }

    private void undoStepBb(final Move move)
            throws Exception {

        final long fromSquareBb, toSquareBb;

        fromSquareBb = (BitBoards.ONE << move.fromSquare);
        toSquareBb = (BitBoards.ONE << move.toSquare);

        if (move.promotionPiece == null) {
            switch (move.piece) {
            case Pieces.WHITE_PAWN:
                whitePawnsBb &= ~toSquareBb;
                whitePawnsBb |= fromSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_PAWN_IX][move.toSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_PAWN_IX][move.fromSquare];
                break;
            case Pieces.BLACK_PAWN:
                blackPawnsBb &= ~toSquareBb;
                blackPawnsBb |= fromSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_PAWN_IX][move.toSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_PAWN_IX][move.fromSquare];
                break;
            case Pieces.WHITE_KNIGHT:
                whiteKnightsBb &= ~toSquareBb;
                whiteKnightsBb |= fromSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_KNIGHT_IX][move.toSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_KNIGHT_IX][move.fromSquare];
                break;
            case Pieces.BLACK_KNIGHT:
                blackKnightsBb &= ~toSquareBb;
                blackKnightsBb |= fromSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_KNIGHT_IX][move.toSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_KNIGHT_IX][move.fromSquare];
                break;
            case Pieces.WHITE_BISHOP:
                whiteBishopsBb &= ~toSquareBb;
                whiteBishopsBb |= fromSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_BISHOP_IX][move.toSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_BISHOP_IX][move.fromSquare];
                break;
            case Pieces.BLACK_BISHOP:
                blackBishopsBb &= ~toSquareBb;
                blackBishopsBb |= fromSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_BISHOP_IX][move.toSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_BISHOP_IX][move.fromSquare];
                break;
            case Pieces.WHITE_ROOK:
                whiteRooksBb &= ~toSquareBb;
                whiteRooksBb |= fromSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_ROOK_IX][move.toSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_ROOK_IX][move.fromSquare];
                break;
            case Pieces.BLACK_ROOK:
                blackRooksBb &= ~toSquareBb;
                blackRooksBb |= fromSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_ROOK_IX][move.toSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_ROOK_IX][move.fromSquare];
                break;
            case Pieces.WHITE_QUEEN:
                whiteQueensBb &= ~toSquareBb;
                whiteQueensBb |= fromSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_QUEEN_IX][move.toSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_QUEEN_IX][move.fromSquare];
                break;
            case Pieces.BLACK_QUEEN:
                blackQueensBb &= ~toSquareBb;
                blackQueensBb |= fromSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_QUEEN_IX][move.toSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_QUEEN_IX][move.fromSquare];
                break;
            case Pieces.WHITE_KING:
                whiteKingBb &= ~toSquareBb;
                whiteKingBb |= fromSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_KING_IX][move.toSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_KING_IX][move.fromSquare];
                break;
            case Pieces.BLACK_KING:
                blackKingBb &= ~toSquareBb;
                blackKingBb |= fromSquareBb;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_KING_IX][move.toSquare];
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_KING_IX][move.fromSquare];
                break;
            default:
                throw new Exception("move.piece=" + move.piece);
            }
        } else {
            switch (move.piece) {
            case Pieces.WHITE_PAWN:
                whitePawnsBb |= fromSquareBb;
                ++whitePawnsCounter;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_PAWN_IX][move.fromSquare];
                break;
            case Pieces.BLACK_PAWN:
                blackPawnsBb |= fromSquareBb;
                ++blackPawnsCounter;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_PAWN_IX][move.fromSquare];
                break;
            default:
                throw new Exception("move.piece=" + move.piece);
            }
            switch (move.promotionPiece) {
            case Pieces.WHITE_KNIGHT:
                whiteKnightsBb &= ~toSquareBb;
                --whiteKnightsCounter;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_KNIGHT_IX][move.toSquare];
                break;
            case Pieces.BLACK_KNIGHT:
                blackKnightsBb &= ~toSquareBb;
                --blackKnightsCounter;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_KNIGHT_IX][move.toSquare];
                break;
            case Pieces.WHITE_BISHOP:
                whiteBishopsBb &= ~toSquareBb;
                --whiteBishopsCounter;
                if ((toSquareBb & BitBoards.WHITE_FIELDS) == toSquareBb) {
                    --whiteWFBishopsCounter;
                } else {
                    --whiteBFBishopsCounter;
                }
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_BISHOP_IX][move.toSquare];
                break;
            case Pieces.BLACK_BISHOP:
                blackBishopsBb &= ~toSquareBb;
                --blackBishopsCounter;
                if ((toSquareBb & BitBoards.WHITE_FIELDS) == toSquareBb) {
                    --blackWFBishopsCounter;
                } else {
                    --blackBFBishopsCounter;
                }
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_BISHOP_IX][move.toSquare];
                break;
            case Pieces.WHITE_ROOK:
                whiteRooksBb &= ~toSquareBb;
                --whiteRooksCounter;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_ROOK_IX][move.toSquare];
                break;
            case Pieces.BLACK_ROOK:
                blackRooksBb &= ~toSquareBb;
                --blackRooksCounter;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_ROOK_IX][move.toSquare];
                break;
            case Pieces.WHITE_QUEEN:
                whiteQueensBb &= ~toSquareBb;
                --whiteQueensCounter;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_QUEEN_IX][move.toSquare];
                break;
            case Pieces.BLACK_QUEEN:
                blackQueensBb &= ~toSquareBb;
                --blackQueensCounter;
                nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_QUEEN_IX][move.toSquare];
                break;
            default:
                throw new Exception("move.promotionPiece=" + move.promotionPiece);
            }
        }

        switch (playerColor) {
        case Colors.WHITE: blackPiecesBb &= ~toSquareBb; blackPiecesBb |= fromSquareBb; break;
        case Colors.BLACK: whitePiecesBb &= ~toSquareBb; whitePiecesBb |= fromSquareBb; break;
        default:
            throw new Exception("playerColor=" + playerColor);
        }

    }

    private void undoCaptureMap(final Move move) {

        squarePieceMap[move.fromSquare] = move.piece;
        squarePieceMap[move.toSquare] = move.targetPiece;

    }

    private void undoCaptureBb(final Move move)
            throws Exception {

        long toSquareBb;

        toSquareBb = (BitBoards.ONE << move.toSquare);

        switch (move.targetPiece) {
        case Pieces.WHITE_PAWN:
            whitePawnsBb |= toSquareBb;
            ++whitePawnsCounter;
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_PAWN_IX][move.toSquare];
            break;
        case Pieces.BLACK_PAWN:
            blackPawnsBb |= toSquareBb;
            ++blackPawnsCounter;
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_PAWN_IX][move.toSquare];
            break;
        case Pieces.WHITE_KNIGHT:
            whiteKnightsBb |= toSquareBb;
            ++whiteKnightsCounter;
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_KNIGHT_IX][move.toSquare];
            break;
        case Pieces.BLACK_KNIGHT:
            blackKnightsBb |= toSquareBb;
            ++blackKnightsCounter;
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_KNIGHT_IX][move.toSquare];
            break;
        case Pieces.WHITE_BISHOP:
            whiteBishopsBb |= toSquareBb;
            ++whiteBishopsCounter;
            if ((toSquareBb & BitBoards.WHITE_FIELDS) == toSquareBb) {
                ++whiteWFBishopsCounter;
            } else {
                ++whiteBFBishopsCounter;
            }
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_BISHOP_IX][move.toSquare];
            break;
        case Pieces.BLACK_BISHOP:
            blackBishopsBb |= toSquareBb;
            ++blackBishopsCounter;
            if ((toSquareBb & BitBoards.WHITE_FIELDS) == toSquareBb) {
                ++blackWFBishopsCounter;
            } else {
                ++blackBFBishopsCounter;
            }
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_BISHOP_IX][move.toSquare];
            break;
        case Pieces.WHITE_ROOK:
            whiteRooksBb |= toSquareBb;
            ++whiteRooksCounter;
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_ROOK_IX][move.toSquare];
            break;
        case Pieces.BLACK_ROOK:
            blackRooksBb |= toSquareBb;
            ++blackRooksCounter;
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_ROOK_IX][move.toSquare];
            break;
        case Pieces.WHITE_QUEEN:
            whiteQueensBb |= toSquareBb;
            ++whiteQueensCounter;
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_QUEEN_IX][move.toSquare];
            break;
        case Pieces.BLACK_QUEEN:
            blackQueensBb |= toSquareBb;
            ++blackQueensCounter;
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_QUEEN_IX][move.toSquare];
            break;
        default:
            throw new Exception("move.targetPiece=" + move.targetPiece);
        }

        switch (playerColor) {
        case Colors.WHITE: whitePiecesBb |= toSquareBb; break;
        case Colors.BLACK: blackPiecesBb |= toSquareBb; break;
        default:
            throw new Exception("playerColor=" + playerColor);
        }

    }

    private void undoWhiteShortCgMap() {

        squarePieceMap[Squares.SQUARE_G1] = null;
        squarePieceMap[Squares.SQUARE_E1] = Pieces.WHITE_KING;
        squarePieceMap[Squares.SQUARE_F1] = null;
        squarePieceMap[Squares.SQUARE_H1] = Pieces.WHITE_ROOK;

    }

    private void undoWhiteShortCgBb() {

        whiteKingBb  &= ~BitBoards.SQUARE_G1;
        whiteKingBb  |=  BitBoards.SQUARE_E1;
        whiteRooksBb &= ~BitBoards.SQUARE_F1;
        whiteRooksBb |=  BitBoards.SQUARE_H1;

        whitePiecesBb &= ~BitBoards.SQUARE_G1;
        whitePiecesBb |=  BitBoards.SQUARE_E1;
        whitePiecesBb &= ~BitBoards.SQUARE_F1;
        whitePiecesBb |=  BitBoards.SQUARE_H1;

        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_KING_IX] [Squares.SQUARE_G1];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_KING_IX] [Squares.SQUARE_E1];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_ROOK_IX] [Squares.SQUARE_F1];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_ROOK_IX] [Squares.SQUARE_H1];

    }

    private void undoBlackShortCgMap() {

        squarePieceMap[Squares.SQUARE_G8] = null;
        squarePieceMap[Squares.SQUARE_E8] = Pieces.BLACK_KING;
        squarePieceMap[Squares.SQUARE_F8] = null;
        squarePieceMap[Squares.SQUARE_H8] = Pieces.BLACK_ROOK;

    }

    private void undoBlackShortCgBb() {

        blackKingBb  &= ~BitBoards.SQUARE_G8;
        blackKingBb  |=  BitBoards.SQUARE_E8;
        blackRooksBb &= ~BitBoards.SQUARE_F8;
        blackRooksBb |=  BitBoards.SQUARE_H8;

        blackPiecesBb &= ~BitBoards.SQUARE_G8;
        blackPiecesBb |=  BitBoards.SQUARE_E8;
        blackPiecesBb &= ~BitBoards.SQUARE_F8;
        blackPiecesBb |=  BitBoards.SQUARE_H8;

        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_KING_IX] [Squares.SQUARE_G8];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_KING_IX] [Squares.SQUARE_E8];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_ROOK_IX] [Squares.SQUARE_F8];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_ROOK_IX] [Squares.SQUARE_H8];

    }

    private void undoWhiteLongCgMap() {

        squarePieceMap[Squares.SQUARE_C1] = null;
        squarePieceMap[Squares.SQUARE_E1] = Pieces.WHITE_KING;
        squarePieceMap[Squares.SQUARE_D1] = null;
        squarePieceMap[Squares.SQUARE_A1] = Pieces.WHITE_ROOK;

    }

    private void undoWhiteLongCgBb() {

        whiteKingBb  &= ~BitBoards.SQUARE_C1;
        whiteKingBb  |=  BitBoards.SQUARE_E1;
        whiteRooksBb &= ~BitBoards.SQUARE_D1;
        whiteRooksBb |=  BitBoards.SQUARE_A1;

        whitePiecesBb &= ~BitBoards.SQUARE_C1;
        whitePiecesBb |=  BitBoards.SQUARE_E1;
        whitePiecesBb &= ~BitBoards.SQUARE_D1;
        whitePiecesBb |=  BitBoards.SQUARE_A1;

        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_KING_IX] [Squares.SQUARE_C1];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_KING_IX] [Squares.SQUARE_E1];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_ROOK_IX] [Squares.SQUARE_D1];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.WHITE_ROOK_IX] [Squares.SQUARE_A1];

    }

    private void undoBlackLongCgMap() {

        squarePieceMap[Squares.SQUARE_C8] = null;
        squarePieceMap[Squares.SQUARE_E8] = Pieces.BLACK_KING;
        squarePieceMap[Squares.SQUARE_D8] = null;
        squarePieceMap[Squares.SQUARE_A8] = Pieces.BLACK_ROOK;

    }

    private void undoBlackLongCgBb() {

        blackKingBb  &= ~BitBoards.SQUARE_C8;
        blackKingBb  |=  BitBoards.SQUARE_E8;
        blackRooksBb &= ~BitBoards.SQUARE_D8;
        blackRooksBb |=  BitBoards.SQUARE_A8;

        blackPiecesBb &= ~BitBoards.SQUARE_C8;
        blackPiecesBb |=  BitBoards.SQUARE_E8;
        blackPiecesBb &= ~BitBoards.SQUARE_D8;
        blackPiecesBb |=  BitBoards.SQUARE_A8;

        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_KING_IX] [Squares.SQUARE_C8];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_KING_IX] [Squares.SQUARE_E8];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_ROOK_IX] [Squares.SQUARE_D8];
        nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST [Zobrist.BLACK_ROOK_IX] [Squares.SQUARE_A8];

    }

    private void undoEnPassantMap(final Move move, final int removeSquare) {

        squarePieceMap[move.fromSquare] = move.piece;
        squarePieceMap[move.toSquare] = null;

        squarePieceMap[removeSquare] = move.targetPiece;

    }

    private void undoEnPassantBb(final Move move, final int removeSquare)
            throws Exception {

        long fromSquareBb, toSquareBb, removeSquareBb;

        fromSquareBb = (BitBoards.ONE << move.fromSquare);
        toSquareBb = (BitBoards.ONE << move.toSquare);
        removeSquareBb = (BitBoards.ONE << removeSquare);

        switch (move.piece) {
        case Pieces.WHITE_PAWN:
            whitePawnsBb &= ~toSquareBb;
            whitePawnsBb |= fromSquareBb;
            blackPawnsBb |= removeSquareBb;
            ++blackPawnsCounter;
            //
            whitePiecesBb &= ~toSquareBb;
            whitePiecesBb |= fromSquareBb;
            blackPiecesBb |= removeSquareBb;
            //
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_PAWN_IX][move.toSquare];
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_PAWN_IX][move.fromSquare];
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_PAWN_IX][removeSquare];
            break;
        case Pieces.BLACK_PAWN:
            blackPawnsBb &= ~toSquareBb;
            blackPawnsBb |= fromSquareBb;
            whitePawnsBb |= removeSquareBb;
            ++whitePawnsCounter;
            //
            blackPiecesBb &= ~toSquareBb;
            blackPiecesBb |= fromSquareBb;
            whitePiecesBb |= removeSquareBb;
            //
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_PAWN_IX][move.toSquare];
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.BLACK_PAWN_IX][move.fromSquare];
            nodeHashCode ^= Zobrist.BOARD_RANDOMS_LIST[Zobrist.WHITE_PAWN_IX][removeSquare];
            break;
        default:
            throw new Exception("move.piece=" + move.piece);
        }

    }

    /********************************************************************************************************************
     *** state checking
     ********************************************************************************************************************/

    public void checkLegalState()
            throws Exception {

        switch (playerColor) {
        case Colors.WHITE:
            if (areSquaresCheckedBb2(blackKingBb, Colors.BLACK)) {
                gameState = States.NOT_LEGAL;
            }
            break;
        case Colors.BLACK:
            if (areSquaresCheckedBb2(whiteKingBb, Colors.WHITE)) {
                gameState = States.NOT_LEGAL;
            }
            break;
        default:
            throw new Exception("playerColor=" + playerColor);
        }

    }

    public boolean areSquaresCheckedBb2(final long initSquaresBb, final int sideColor)
            throws Exception {

        final long antagonistKnightsBb, antagonistBishopsBb, antagonistRooksBb, antagonistQueensBb, antagonistKingBb;

        long squaresBb;
        long nextSquareBb, toSquaresBb;
        byte nextSquare;

        switch (sideColor) {
        case Colors.WHITE:

            /* NW + NW EP */
            squaresBb = initSquaresBb;
            toSquaresBb = (squaresBb >>> BitBoards.ANTIGONAL_STEP) & blackPawnsBb & ~BitBoards.FILE_H;
            if (toSquaresBb != BitBoards.EMPTY) {
                return true;
            }

            /* NE + NE EP */
            squaresBb = initSquaresBb;
            toSquaresBb = (squaresBb >>> BitBoards.DIAGONAL_STEP) & blackPawnsBb & ~BitBoards.FILE_A;
            if (toSquaresBb != BitBoards.EMPTY) {
                return true;
            }

            antagonistKnightsBb = blackKnightsBb;
            antagonistBishopsBb = blackBishopsBb;
            antagonistRooksBb   = blackRooksBb;
            antagonistQueensBb  = blackQueensBb;
            antagonistKingBb    = blackKingBb;

            break;
        case Colors.BLACK:

            /* SE + SE EP */
            squaresBb = initSquaresBb;
            toSquaresBb = (squaresBb << BitBoards.ANTIGONAL_STEP) & whitePawnsBb & ~BitBoards.FILE_A;
            if (toSquaresBb != BitBoards.EMPTY) {
                return true;
            }

            /* SW + SW EP */
            squaresBb = initSquaresBb;
            toSquaresBb = (squaresBb << BitBoards.DIAGONAL_STEP) & whitePawnsBb & ~BitBoards.FILE_H;
            if (toSquaresBb != BitBoards.EMPTY) {
                return true;
            }

            antagonistKnightsBb = whiteKnightsBb;
            antagonistBishopsBb = whiteBishopsBb;
            antagonistRooksBb   = whiteRooksBb;
            antagonistQueensBb  = whiteQueensBb;
            antagonistKingBb    = whiteKingBb;

            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

        squaresBb = initSquaresBb;
        nextSquareBb = squaresBb & -squaresBb;
        while (nextSquareBb != BitBoards.EMPTY) {
            nextSquare = (byte) Long.numberOfTrailingZeros(nextSquareBb);
            toSquaresBb = BitBoards.KNIGHT_MOVES_LT[nextSquare] & antagonistKnightsBb;
            if (toSquaresBb != BitBoards.EMPTY) {
                return true;
            }
            squaresBb &= ~nextSquareBb;
            nextSquareBb = squaresBb & -squaresBb;
        }

        squaresBb = initSquaresBb;
        nextSquareBb = squaresBb & -squaresBb;
        while (nextSquareBb != BitBoards.EMPTY) {
            nextSquare = (byte) Long.numberOfTrailingZeros(nextSquareBb);
            toSquaresBb = BitBoards.horizontalVerticalMoves(nextSquare, nextSquareBb, allPiecesBb)
                    & (antagonistRooksBb | antagonistQueensBb);
            if (toSquaresBb != BitBoards.EMPTY) {
                return true;
            }
            squaresBb &= ~nextSquareBb;
            nextSquareBb = squaresBb & -squaresBb;
        }

        squaresBb = initSquaresBb;
        nextSquareBb = squaresBb & -squaresBb;
        while (nextSquareBb != BitBoards.EMPTY) {
            nextSquare = (byte) Long.numberOfTrailingZeros(nextSquareBb);
            toSquaresBb = BitBoards.diagonalAntigonalMoves(nextSquare, nextSquareBb, allPiecesBb)
                    & (antagonistBishopsBb | antagonistQueensBb);
            if (toSquaresBb != BitBoards.EMPTY) {
                return true;
            }
            squaresBb &= ~nextSquareBb;
            nextSquareBb = squaresBb & -squaresBb;
        }

        squaresBb = initSquaresBb;
        nextSquareBb = squaresBb & -squaresBb;
        while (nextSquareBb != BitBoards.EMPTY) {
            nextSquare = (byte) Long.numberOfTrailingZeros(nextSquareBb);
            toSquaresBb = BitBoards.KING_MOVES_LT[nextSquare] & antagonistKingBb;
            if (toSquaresBb != BitBoards.EMPTY) {
                return true;
            }
            squaresBb &= ~nextSquareBb;
            nextSquareBb = squaresBb & -squaresBb;
        }

        return false;
    }

    private void computeGameState(final Move move)
            throws Exception {

        if (halfmovesClock > 50) {
            gameState = States.FIFTY_MOVES;
        }
        // se nuova mossa irreversibile, non cerco eventuali ripetizioni
        else if (isMoveIrreversible(move)
                && isThreefoldRepetition(Engine.SEARCH_REPS_TO_THREEFOLD)) {
            gameState = States.THREEFOLD_REP;
        }
        //
        else if (isCheckmateImpossible()) {
            gameState = States.IMPOSSIBLE_MATE;
        }
        //
        else {
            switch (playerColor) {
                case Colors.WHITE:
                    if (areSquaresCheckedBb2(whiteKingBb, Colors.WHITE)) {
                        gameState = States.CHECK;
                    } else {
                        gameState = States.ONGOING;
                    }
                    break;
                case Colors.BLACK:
                    if (areSquaresCheckedBb2(blackKingBb, Colors.BLACK)) {
                        gameState = States.CHECK;
                    } else {
                        gameState = States.ONGOING;
                    }
                    break;
                default:
                    throw new Exception("playerColor=" + playerColor);
            }
        }

    }

    private boolean isMoveIrreversible(final Move move) {

        return move != null
                // mosse di pedone
                && ( (move.function == Functions.MOVEMENT
                    && /*getRole()*/Math.abs(move.piece) != Pieces.ROLE_PAWN)
                    // catture
                    || move.targetPiece != null );
    }

    public boolean isThreefoldRepetition(final int repsToThreefold) {

        // ultimo elemento: this
        int i = treeLevel;

        int repeatsCounter = 1;

        // incremento: i - 2 per trovare una posizione con lo stesso colore al tratto
        for (i = i - 2; i >= Engine.lastIrrvLevelsList[treeLevel]; i = i - 2) {
            if (nodeHashCode == Engine.treeHashcodesList[i]) {
                ++repeatsCounter;
                if (repeatsCounter >= repsToThreefold) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isCheckmateImpossible() {

        /*
         * patte teoriche
         *  - K vs K
         *  - K vs KN
         *  - K vs KB
         *  - KB[BBB...] vs KB[BBB...] alfieri dello stesso campo
         */

        if (whitePawnsCounter > 0 || whiteRooksCounter > 0 || whiteQueensCounter > 0
                || blackPawnsCounter > 0 || blackRooksCounter > 0 || blackQueensCounter > 0) {
            // => ci sono pedoni o pezzi pesanti
            return false;
        }

        // non ci sono pedoni né pezzi pesanti
        if (whiteKnightsCounter > 0 || blackKnightsCounter > 0) {
            // => ci sono cavalli
            if (whiteBishopsCounter > 0 || blackBishopsCounter > 0) {
                // => ci sono alfieri
                return false;
            }
            // non ci sono alfieri
            //noinspection RedundantIfStatement
            if ((whiteKnightsCounter == 1 && blackKnightsCounter == 0)
                    || (whiteKnightsCounter == 0 && blackKnightsCounter == 1)) {
                // => KN vs K
                return true;
            }
            // più cavalli
            return false;
        }

        // non ci sono cavalli
        if (whiteBishopsCounter > 0 || blackBishopsCounter > 0) {
            // => ci sono alfieri
            if ((whiteBishopsCounter == 1 && blackBishopsCounter == 0)
                    || (whiteBishopsCounter == 0 && blackBishopsCounter == 1)) {
                // => KB vs K
                return true;
            }
            // più alfieri
            //noinspection RedundantIfStatement
            if ((whiteWFBishopsCounter > 0 && blackBFBishopsCounter > 0)
                    || (blackWFBishopsCounter > 0 && whiteBFBishopsCounter > 0)) {
                // => alfieri di campo opposto
                return false;
            }
            // alfieri dello stesso campo
            return true;
        }

        // K vs K
        return true;
    }

}
