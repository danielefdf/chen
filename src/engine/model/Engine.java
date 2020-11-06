package engine.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;

import application.app.MainUtils;
import model.elements.Accuracies;
import model.elements.BitBoards;
import model.elements.Colors;
import model.elements.Functions;
import model.elements.Pieces;
import model.elements.Squares;
import model.elements.States;
import model.game.Game;

/**
 * Contiene le informazioni sul gioco, e le quantità che il motore usa per valutare una posizione.
 *
 * Contiene le funzioni di calcolo delle mosse disponibili, la ricerca della mossa successiva,
 * la valutazione della posizione.
 *
 * La ricerca della mossa può avvenire con vari metodi, tutti subordinati a dei flag,
 * vedi "search options".
 */
public class Engine implements Serializable {

    private static final long serialVersionUID = 1L;

    public transient static final String DEFAULT_ENGINE_NAME = "@default";

    public transient static final int MAX_DEPTH = 64;

    public transient static final short MAX_MOVES_IN_POSITION = 200;
    public transient static final int START_MOVES_LIST_INDEX = -1;

    public transient static final short MAX_MOVES_IN_GAME = 2000;

    public transient static final int SEARCH_REPS_TO_THREEFOLD = 2;
    public transient static final int GAME_REPS_TO_THREEFOLD   = 3;

    public transient static final int MAX_SHIELD_PAWNS_NUMBER = 3;

    public transient static final Integer TIME_OUT_SEARCH_VALUE = null;

    public transient static final Engine MOVES_ENGINE = Engine.newMiniMaxEngine();

    public transient static final Double ON_REALS = 0.0;

    /*
     * class fields
     */

    public String name;

    /*
     * search
     */

    public transient Game game;
    public transient Node rootNode;
    public transient Node searchNode;
    public transient Integer rootNodeValue;
    public transient PvRecord stats;
    public transient boolean outsideStop;
    public transient int currentSearchDepth;
    public transient int visitedNodesCounter;
    public transient Long searchStartMillis;
    public transient Integer searchMaxLengthMillis;
    public transient Integer inputSearchMaxLengthMillis;
    public transient Move[] movesList;
    public transient int movesListMaxIndex;
    public transient boolean moveFound;

    /*
     * game tables
     */

    public static transient short[] lastIrrvLevelsList;
    public static transient long[] treeHashcodesList;

    public transient PvMap pvMap;
    public transient TMap tMap;

    public transient int[][][] killersRepsList;
    public transient int[][][] historyRepsList;

    /*
     * search options
     */

    public int movesToGo;
    public Integer searchDepth;
    public boolean alphaBetaPruning;
    public boolean delayedLegalityCheck;
    public boolean delayedMateCheck;  // utile per perft test
    public int sortedMovesNumber;
    public boolean searchCheckIncrement;
    public boolean quiescentPosSearch;
        public int qSearchAddedDepth;
        public boolean qChecksSearch;
    public boolean iterDeepeningSearch;
    public boolean principalVarSearch;
    public boolean lateMoveReduction;
        public int lateMoveMinMoves;
        public int lateMoveSubtrDepth;
    public boolean killerHeuristic;
    public boolean historyHeuristic;
    public boolean transpositionsMap;

    // material

    public int matPawnMidgScore;
    public int matPawnEndgScore;
    public int matKnightMidgScore;
    public int matKnightEndgScore;
    public int matBishopMidgScore;
    public int matBishopEndgScore;
    public int matRookMidgScore;
    public int matRookEndgScore;
    public int matQueenMidgScore;
    public int matQueenEndgScore;
    public int matKingMidgScore; // king mobility

    public int matShortCgMidgScore;
    public int matLongCgMidgScore;

    public transient int matAllPiecesMidgScore;

    public int matLonelyKnightMidgScore;
    public int matLonelyKnightEndgScore;
    public int matLonelyBishopMidgScore;
    public int matLonelyBishopEndgScore;
    public int matLonelyRookMidgScore;
    public int matLonelyRookEndgScore;

    public int matSameFBishopsMidgScore;
    public int matSameFBishopsEndgScore;

    public int matNoPawnsMidgScore;
    public int matNoPawnsEndgScore;

    // pawn structure

    public int pstDispersionCoefficient;
    public int pstDistorsionCoefficient;

    public int pstIsolaniPawnsMidgScore;
    public int pstIsolaniPawnsEndgScore;

    public int pstPassedPawnsMidgScore;
    public int pstPassedPawnsEndgScore;

    // mobility

    public int mobShortCgScore;
    public int mobLongCgScore;

    public int mobPieceNumerator;
    public int mobTargetAttackDenomntr;
    public int mobTargetProtectnDenomntr;

    public int mobPawnStepScore;
    public int mobPawnDoubleStepScore;

    // arrangement

    public int[] arrWhitePawnMidgScoresList;
    public int[] arrWhitePawnEndgScoresList;
    public int[] arrWhiteKnightMidgScoresList;
    public int[] arrWhiteBishopMidgScoresList;
    public int[] arrWhiteRookMidgScoresList;
    public int[] arrWhiteRookEndgScoresList;
    public int[] arrWhiteQueenMidgScoresList;
    public int[] arrWhiteQueenEndgScoresList;
    public int[] arrWhiteKingMidgScoresList;
    public int[] arrWhiteKingEndgScoresList;

    public transient int[] arrBlackPawnMidgScoresList;
    public transient int[] arrBlackPawnEndgScoresList;
    public transient int[] arrBlackKnightMidgScoresList;
    public transient int[] arrBlackBishopMidgScoresList;
    public transient int[] arrBlackRookMidgScoresList;
    public transient int[] arrBlackRookEndgScoresList;
    public transient int[] arrBlackQueenMidgScoresList;
    public transient int[] arrBlackQueenEndgScoresList;
    public transient int[] arrBlackKingMidgScoresList;
    public transient int[] arrBlackKingEndgScoresList;

    public int arrOnCtrlledSquareDivisor;

    public int arrRookOnOpenFileScore;
    public int arrQueenOnOpenFileScore;

    public int arrRookOnSemiOFileScore;
    public int arrQueenOnSemiOFileScore;

    public int arrCentreControlScore;
    public int arrCRingControlScore;

    // king safety

    public int ksaKingAttackMidgScore;
    public int ksaKingAttackEndgScore;
    public int ksaKRing1AttackMidgScore;
    public int ksaKRing1AttackEndgScore;
    public int ksaKRing2AttackMidgScore;
    public int ksaKRing2AttackEndgScore;

    public int ksaShieldPawnLackScore;

    public int ksaKingUncastledMaxFullmoves;

    // move ordering

    public int morFunctionCoeff;

    public int morTargetRoleCoeff;
    public int morPieceRoleCoeff;
    public int morPromRoleCoeff;

    public int morOnCtrlledSquareScore;

    public int morOnKRing2Score;
    public int morOnKRing1Score;

    public int morOnCRing3Score;
    public int morOnCRing2Score;
    public int morOnCRing1Score;
    public int morOnCentreScore;

    /*
     * evaluation
     */

    public transient static final int START_ALPHA = -30000;
    public transient static final int START_BETA  =  30000;

    public transient static final int[] PST_P_ISLANDS_COUNT_LT = computePawnIslandsCountLt();
    public transient static final int[] PST_ISOLANI_P_COUNT_LT = computeIsolaniPawnsCountLt();

    public transient Node movesNode;
    public transient Node evalNode;

    public transient int allPiecesMidgScore;
    public transient int kingAttacksCounter, kRing1AttacksCounter, kRing2AttacksCounter;
    public transient int centreControlsCounter, cRingControlsCounter;
    public transient int whitePawnsFs, blackPawnsFs;
    public transient int whitePawnIslandsCounter, blackPawnIslandsCounter;
    public transient int taperedScore, midg, endg;
    public transient int whiteMidg, blackMidg, whiteEndg, blackEndg;
    public transient int sideMidg, sideEndg;

    /*
     * perft
     */

    public transient int nodesCt, stepsCt, capturesCt, enPassantCt, castlesCt, shortCCt, longCCt,
            promotionsCt, checksCt, matesCt, stalesCt, notLegalsCt;

    private static int[] computePawnIslandsCountLt() {

        long sidePawnsBb, pawnsFs, pawnIslandsFs, nextPawnIslandFs;

        int[] pawnIslandsCountList = new int[(int) Math.pow(2, 8)];

        for (int i = 0; i <= Math.pow(2, 8) - 1; ++i) {

            sidePawnsBb = i;

            // file set
            pawnsFs = (sidePawnsBb * BitBoards.FILE_A) >>> Squares.SQUARE_A1;

            pawnIslandsFs = (pawnsFs & ~(pawnsFs >>> 1));

            nextPawnIslandFs = pawnIslandsFs & -pawnIslandsFs;
            while (nextPawnIslandFs != BitBoards.EMPTY) {
                ++pawnIslandsCountList[i];
                pawnIslandsFs &= ~nextPawnIslandFs;
                nextPawnIslandFs = pawnIslandsFs & -pawnIslandsFs;
            }

        }

        return pawnIslandsCountList;
    }

    private static int[] computeIsolaniPawnsCountLt() {

        long sidePawnsBb, pawnsFs, isolaniPawnsBb, nextPawnBb;

        int[] isolaniPawnsCountsList = new int[(int) Math.pow(2, 8)];

        for (int i = 0; i <= Math.pow(2, 8) - 1; ++i) {

            sidePawnsBb = i;

            // file set
            pawnsFs = (sidePawnsBb * BitBoards.FILE_A) >>> Squares.SQUARE_A1;

            isolaniPawnsBb = (pawnsFs & ~(pawnsFs >>> 1)) & (pawnsFs & ~(pawnsFs << 1));

            nextPawnBb = isolaniPawnsBb & -isolaniPawnsBb;
            while (nextPawnBb != BitBoards.EMPTY) {
                ++isolaniPawnsCountsList[i];
                isolaniPawnsBb &= ~nextPawnBb;
                nextPawnBb = isolaniPawnsBb & -isolaniPawnsBb;
            }

        }

        return isolaniPawnsCountsList;
    }

    /******************************************************************************************************************
     *** constructors
     ******************************************************************************************************************/

    public Engine () {

        // verrà eventualmente modificato se ci saranno modifiche da EngineStage
        name = DEFAULT_ENGINE_NAME;

        /*
         * search options
         */

        movesToGo = 30;
        searchDepth = 64;
        alphaBetaPruning      = true;
        delayedLegalityCheck  = true;
        delayedMateCheck      = true;
        sortedMovesNumber     = 7;
        searchCheckIncrement  = true;
        quiescentPosSearch    = true;
            qSearchAddedDepth = 3;
            qChecksSearch     = true;
        iterDeepeningSearch   = true;
        principalVarSearch    = true;
        killerHeuristic       = true;
        historyHeuristic      = true;
        transpositionsMap     = true;

        /*
         * material
         */

        matPawnMidgScore   = 100;
        matPawnEndgScore   = 200;
        matKnightMidgScore = 302;
        matKnightEndgScore = 271;
        matBishopMidgScore = 321;
        matBishopEndgScore = 352;
        matRookMidgScore   = 519;
        matRookEndgScore   = 579;
        matQueenMidgScore  = 907;
        matQueenEndgScore  = 997;
        matKingMidgScore   =  88; // king mobility

        matShortCgMidgScore = 34;
        matLongCgMidgScore  = 21;

        matAllPiecesMidgScore = 16 * matPawnMidgScore
                                            +  4 * matKnightMidgScore
                                            +  4 * matBishopMidgScore
                                            +  4 * matRookMidgScore
                                            +  2 * matQueenMidgScore
                                            +  2 * matShortCgMidgScore
                                            +  2 * matLongCgMidgScore;

        matLonelyKnightMidgScore = 20;
        matLonelyKnightEndgScore = 40;
        matLonelyBishopMidgScore = -40;
        matLonelyBishopEndgScore = -80;
        matLonelyRookMidgScore   = 20;
        matLonelyRookEndgScore   = 40;

        matSameFBishopsMidgScore = -50;
        matSameFBishopsEndgScore = -80;

        matNoPawnsMidgScore = -58;
        matNoPawnsEndgScore = -113;

        /*
         * pawn structure
         */

        pstDispersionCoefficient = 3;
        pstDistorsionCoefficient = 1;

        pstIsolaniPawnsMidgScore = -38;
        pstIsolaniPawnsEndgScore = -51;

        pstPassedPawnsMidgScore = 21;
        pstPassedPawnsEndgScore = 43;

        /*
         * mobility
         */

        mobShortCgScore = 13;
        mobLongCgScore  = 12;

        mobPieceNumerator         = 120;
        mobTargetAttackDenomntr   = 61;
        mobTargetProtectnDenomntr = 43;

        mobPawnStepScore       = mobPieceNumerator / matPawnMidgScore;  // integer division
        mobPawnDoubleStepScore = 2 * mobPawnStepScore;

        /*
         * arrangement
         */

        arrWhitePawnMidgScoresList   = new int[] {
                    0,    0,    0,    0,    0,    0,    0,    0,
                    0,    0,    0,    0,    0,    0,    0,    0,
                    0,    0,    0,    0,    0,    0,    0,    0,
                   -7,    0,    0,    0,    0,    0,    0,   -7,
                  -11,   -7,   -7,   -7,   -7,   -7,   -7,  -11,
                  -16,  -14,  -14,  -14,  -14,  -14,  -14,  -16,
                  -22,  -22,  -22,  -22,  -22,  -22,  -22,  -22,
                    0,    0,    0,    0,    0,    0,    0,    0,
        };
        arrBlackPawnMidgScoresList   = verticalMirrorArray(
                arrWhitePawnMidgScoresList);

        arrWhitePawnEndgScoresList   = new int[] {
                    0,    0,    0,    0,    0,    0,    0,    0,
                    2,   56,   56,   56,   56,   56,   56,    2,
                   -6,   13,   13,   13,   13,   13,   13,   -6,
                   -7,    0,    0,    0,    0,    0,    0,   -7,
                  -11,   -7,   -7,   -7,   -7,   -7,   -7,  -11,
                  -16,  -14,  -14,  -14,  -14,  -14,  -14,  -16,
                  -22,  -22,  -22,  -22,  -22,  -22,  -22,  -22,
                    0,    0,    0,    0,    0,    0,    0,    0,
        };
        arrBlackPawnEndgScoresList   = verticalMirrorArray(
                arrWhitePawnEndgScoresList);

        arrWhiteKnightMidgScoresList = new int[] {
                  -48,  -25,  -15,   -8,   -8,  -15,  -25,  -48,
                  -25,   -4,    7,   17,   17,    7,   -4,  -25,
                  -15,    7,   18,   25,   25,   18,    7,  -15,
                   -8,   17,   25,   33,   33,   25,   17,   -8,
                   -8,   17,   25,   33,   33,   25,   17,   -8,
                  -15,    7,   18,   25,   25,   18,    7,  -15,
                  -25,   -4,    7,   17,   17,    7,   -4,  -25,
                  -48,  -25,  -15,   -8,   -8,  -15,  -25,  -48
        };
        arrBlackKnightMidgScoresList = verticalMirrorArray(
                arrWhiteKnightMidgScoresList);

        arrWhiteBishopMidgScoresList = new int[] {
                  -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
                  -1,    0,    0,    0,    0,    0,    0,   -1,
                  -1,    0,    2,    2,    2,    2,    0,   -1,
                  -1,    0,    2,    4,    4,    2,    0,   -1,
                  -1,    0,    2,    4,    4,    2,    0,   -1,
                  -1,    0,    2,    2,    2,    2,    0,   -1,
                  -1,    0,    0,    0,    0,    0,    0,   -1,
                  -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1
        };
        arrBlackBishopMidgScoresList = verticalMirrorArray(
                arrWhiteBishopMidgScoresList);

        arrWhiteRookMidgScoresList   = new int[] {
                -24,  -24,  -24,  -24,  -24,  -24,  -24,  -24,
                -24,  -49,  -49,  -49,  -49,  -49,  -49,  -24,
                -24,  -49,  -74,  -74,  -74,  -74,  -49,  -24,
                -24,  -49,  -74,  -99,  -99,  -74,  -49,  -24,
                -24,  -49,  -74,  -99,  -99,  -74,  -49,  -24,
                -24,  -49,  -74,  -74,  -74,  -74,  -49,  -24,
                -24,  -49,  -49,  -49,  -49,  -49,  -49,  -24,
                -24,  -24,  -24,  -24,  -24,  -24,  -24,  -24
        };
        arrBlackRookMidgScoresList   = verticalMirrorArray(
                arrWhiteRookMidgScoresList);

        arrWhiteRookEndgScoresList   = new int[] {
                0,    0,    0,    0,    0,    0,    0,    0,
                0,    0,    0,    0,    0,    0,    0,    0,
                0,    0,    0,    0,    0,    0,    0,    0,
                0,    0,    0,    0,    0,    0,    0,    0,
                0,    0,    0,    0,    0,    0,    0,    0,
                0,    0,    0,    0,    0,    0,    0,    0,
                0,    0,    0,    0,    0,    0,    0,    0,
                0,    0,    0,    0,    0,    0,    0,    0
        };
        arrBlackRookEndgScoresList   = verticalMirrorArray(
                arrWhiteRookEndgScoresList);

        arrWhiteQueenMidgScoresList  = new int[] {
                -80,  -80,  -80,  -80,  -80,  -80,  -80,  -80,
                -80,  -80,  -80,  -80,  -80,  -80,  -80,  -80,
                -80,  -80,  -80,  -80,  -80,  -80,  -80,  -80,
                -80,  -80,  -80,  -80,  -80,  -80,  -80,  -80,
                -60,  -60,  -60,  -60,  -60,  -60,  -60,  -60,
                -40,  -40,  -40,  -40,  -40,  -40,  -40,  -40,
                -20,  -20,  -20,  -20,  -20,  -20,  -20,  -20,
                  0,    0,    0,    0,    0,    0,    0,    0,
        };
        arrBlackQueenMidgScoresList  = verticalMirrorArray(
                arrWhiteQueenMidgScoresList);

        arrWhiteQueenEndgScoresList  = new int[] {
                -4,   -4,   -4,   -4,   -4,   -4,   -4,   -4,
                -4,    1,    1,    1,    1,    1,    1,   -4,
                -4,    1,    6,    6,    6,    6,    1,   -4,
                -4,    1,    6,   11,   11,    6,    1,   -4,
                -4,    1,    6,   11,   11,    6,    1,   -4,
                -4,    1,    6,    6,    6,    6,    1,   -4,
                -4,    1,    1,    1,    1,    1,    1,   -4,
                -4,   -4,   -4,   -4,   -4,   -4,   -4,   -4
        };
        arrBlackQueenEndgScoresList  = verticalMirrorArray(
                arrWhiteQueenEndgScoresList);

        arrWhiteKingMidgScoresList   = new int[] {
                -88,  -88,  -88,  -88,  -88,  -88,  -88,  -88,
                -88,  -88,  -88,  -88,  -88,  -88,  -88,  -88,
                -88,  -88,  -88,  -88,  -88,  -88,  -88,  -88,
                -88,  -88,  -88,  -88,  -88,  -88,  -88,  -88,
                -88,  -88,  -88,  -88,  -88,  -88,  -88,  -88,
                -88,  -88,  -88,  -88,  -88,  -88,  -88,  -88,
                -50,  -70,  -70,  -70,  -70,  -70,  -70,  -50,
                 25,   30,   30,  -30,  -30,  -30,   40,   35 // recupera i punteggi per gli arrocchi effettuati
        };
        arrBlackKingMidgScoresList   = verticalMirrorArray(
                arrWhiteKingMidgScoresList);

        arrWhiteKingEndgScoresList   = new int[] {
                -32,  -21,  -11,   -7,   -7,  -11,  -21,  -32,
                -21,   -9,    4,    9,    9,    4,   -9,  -21,
                -11,    4,   18,   24,   24,   18,    4,  -11,
                 -7,    9,   24,   30,   30,   24,    9,   -7,
                 -7,    9,   24,   30,   30,   24,    9,   -7,
                -11,    4,   18,   24,   24,   18,    4,  -11,
                -21,   -9,    4,    9,    9,    4,   -9,  -21,
                -32,  -21,  -11,   -7,   -7,  -11,  -21,  -32,
        };
        arrBlackKingEndgScoresList   = verticalMirrorArray(
                arrWhiteKingEndgScoresList);

        arrOnCtrlledSquareDivisor    = 31;

        arrRookOnOpenFileScore       = 27;
        arrQueenOnOpenFileScore      = 15;

        arrRookOnSemiOFileScore      = 19;
        arrQueenOnSemiOFileScore     = 7;

        arrCentreControlScore        = 9;
        arrCRingControlScore         = 5;

        /*
         * king safety
         */

        ksaKingAttackMidgScore       = 70;
        ksaKingAttackEndgScore       = 87;
        ksaKRing1AttackMidgScore     = 16;
        ksaKRing1AttackEndgScore     = 23;
        ksaKRing2AttackMidgScore     = 12;
        ksaKRing2AttackEndgScore     = 19;

        ksaShieldPawnLackScore       = -38;

        ksaKingUncastledMaxFullmoves = 14;

        /*
         * move ordering
         *
         * max values
         *
         * pedone cattura donna e promuove, vicino al re:
         *     morFunctionCoeff   * capture (max function)    1000000 * 5    5000000
         *   + morTargetRoleCoeff *                             80000 * 5     400000
         *   + morPieceRoleCoeff  *                             90000 * 1      90000
         *   + morPromRoleCoeff   * queen (max prom role)       90000 * 5     450000
         *   + morOnKRing2Score                                 50000          50000
         *   + morOnKRing1Score   * 0
         *   + morOnCRing3Score   * 0
         *   + morOnCRing2Score   * 0
         *   + morOnCRing1Score   * 0
         *   + morOnCentreScore   * 0
         *                                                                  --------
         *                                                                   5890000
         *
         * donna cattura donna, al centro vicino al re:
         *     morFunctionCoeff   * capture (max function)    1000000 * 5    5000000
         *   + morTargetRoleCoeff *                             80000 * 5     400000
         *   + morPieceRoleCoeff  *                             90000 * 1     450000
         *   + morPromRoleCoeff   * 0
         *   + morOnKRing2Score                                 50000          50000
         *   + morOnKRing1Score   * 0
         *   + morOnCRing3Score   * 0
         *   + morOnCRing2Score   * 0
         *   + morOnCRing1Score   * 0
         *   + morOnCentreScore                                 20000          20000
         *                                                                  --------
         *                                                                   5920000
         */

        morFunctionCoeff        = 1000000;

        morTargetRoleCoeff      =   80000;
        morPieceRoleCoeff       =   90000;
        morPromRoleCoeff        =   90000;

        morOnCtrlledSquareScore = -700000;

        morOnKRing2Score        =   30000;
        morOnKRing1Score        =   40000;

        morOnCRing3Score        =  -20000;
        morOnCRing2Score        =  -10000;
        morOnCRing1Score        =   10000;
        morOnCentreScore        =   20000;

        /*
         * game tables
         */

        initGameTables();
        initMaps();
        initHeuristicsLists();

    }

    public void initGameTables() {

        lastIrrvLevelsList = new short[MAX_MOVES_IN_GAME];
        treeHashcodesList = new long[MAX_MOVES_IN_GAME];

    }

    public void initMaps() {

        pvMap = new PvMap();

        if (transpositionsMap) {
            tMap = new TMap();
        }

    }

    // ha senso che sia diviso da initMaps perché in nextMove, ad ogni iterazione, queste liste
    // devono essere inizializzate
    public void initHeuristicsLists() {

        if (killerHeuristic) {
            killersRepsList = new int[MAX_MOVES_IN_GAME] [Squares.SQUARES_NUMBER] [Squares.SQUARES_NUMBER];
        }

        if (historyHeuristic) {
            historyRepsList = new int[Colors.COLORS_NUMBER] [Squares.SQUARES_NUMBER] [Squares.SQUARES_NUMBER];
        }

    }

    public static Engine newMiniMaxEngine() {

        Engine miniMaxEngine = new Engine();

        miniMaxEngine.alphaBetaPruning     = false;
        miniMaxEngine.delayedLegalityCheck = false;
        miniMaxEngine.delayedMateCheck     = false;
        miniMaxEngine.searchCheckIncrement = false;
        miniMaxEngine.quiescentPosSearch   = false;
        miniMaxEngine.qChecksSearch        = false;
        miniMaxEngine.iterDeepeningSearch  = false;
        miniMaxEngine.principalVarSearch   = false;
        miniMaxEngine.killerHeuristic      = false;
        miniMaxEngine.historyHeuristic     = false;
        miniMaxEngine.transpositionsMap    = false;

        return miniMaxEngine;
    }

    @SuppressWarnings("RedundantThrows")
    public static Engine readEngine(final File file)
            throws Exception {

        Engine engine = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            engine = (Engine) ois.readObject();

            // ricalcolo dei campi transient non registrati

            engine.arrBlackPawnMidgScoresList   = verticalMirrorArray(engine.arrWhitePawnMidgScoresList);
            engine.arrBlackPawnEndgScoresList   = verticalMirrorArray(engine.arrWhitePawnEndgScoresList);
            engine.arrBlackKnightMidgScoresList = verticalMirrorArray(engine.arrWhiteKnightMidgScoresList);
            engine.arrBlackBishopMidgScoresList = verticalMirrorArray(engine.arrWhiteBishopMidgScoresList);
            engine.arrBlackRookMidgScoresList   = verticalMirrorArray(engine.arrWhiteRookMidgScoresList);
            engine.arrBlackRookEndgScoresList   = verticalMirrorArray(engine.arrWhiteRookEndgScoresList);
            engine.arrBlackQueenMidgScoresList  = verticalMirrorArray(engine.arrWhiteQueenMidgScoresList);
            engine.arrBlackQueenEndgScoresList  = verticalMirrorArray(engine.arrWhiteQueenEndgScoresList);
            engine.arrBlackKingMidgScoresList   = verticalMirrorArray(engine.arrWhiteKingMidgScoresList);
            engine.arrBlackKingEndgScoresList   = verticalMirrorArray(engine.arrWhiteKingEndgScoresList);

            engine.matAllPiecesMidgScore = 16 * engine.matPawnMidgScore
                                         +  4 * engine.matKnightMidgScore
                                         +  4 * engine.matBishopMidgScore
                                         +  4 * engine.matRookMidgScore
                                         +  2 * engine.matQueenMidgScore
                                         +  2 * engine.matShortCgMidgScore
                                         +  2 * engine.matLongCgMidgScore;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return engine;
    }

    @SuppressWarnings("RedundantThrows")
    public static int writeEngine(final Engine engine, final File file)
            throws Exception {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(engine);

            return 0;
        } catch (Exception e) {
            e.printStackTrace();

            return 1;
        }

    }

    /********************************************************************************************************************
     *** compute quantities
     ********************************************************************************************************************/

    public void computeBoardQuantities(final Node newNode) {

        movesNode = newNode;

        long whitePawnsNorthFillBb, whitePawnsSouthFillBb;
        long blackPawnsNorthFillBb, blackPawnsSouthFillBb;

        /*
         * pawns files
         */

        whitePawnsNorthFillBb = movesNode.whitePawnsBb;

        whitePawnsNorthFillBb |= whitePawnsNorthFillBb >>> Squares.EDGE;
        whitePawnsNorthFillBb |= whitePawnsNorthFillBb >>> Squares.EDGE * 2;
        whitePawnsNorthFillBb |= whitePawnsNorthFillBb >>> Squares.EDGE * 4;

        whitePawnsSouthFillBb = movesNode.whitePawnsBb;

        whitePawnsSouthFillBb |= whitePawnsSouthFillBb << Squares.EDGE;
        whitePawnsSouthFillBb |= whitePawnsSouthFillBb << Squares.EDGE * 2;
        whitePawnsSouthFillBb |= whitePawnsSouthFillBb << Squares.EDGE * 4;

        movesNode.whitePawnsFilesBb = whitePawnsNorthFillBb | whitePawnsSouthFillBb;

        blackPawnsNorthFillBb = movesNode.blackPawnsBb;

        blackPawnsNorthFillBb |= blackPawnsNorthFillBb >>> Squares.EDGE;
        blackPawnsNorthFillBb |= blackPawnsNorthFillBb >>> Squares.EDGE * 2;
        blackPawnsNorthFillBb |= blackPawnsNorthFillBb >>> Squares.EDGE * 4;

        blackPawnsSouthFillBb = movesNode.blackPawnsBb;

        blackPawnsSouthFillBb |= blackPawnsSouthFillBb << Squares.EDGE;
        blackPawnsSouthFillBb |= blackPawnsSouthFillBb << Squares.EDGE * 2;
        blackPawnsSouthFillBb |= blackPawnsSouthFillBb << Squares.EDGE * 4;

        movesNode.blackPawnsFilesBb = blackPawnsNorthFillBb | blackPawnsSouthFillBb;

        /*
         * open files
         */

        movesNode.openFilesBb = ~(movesNode.whitePawnsFilesBb | movesNode.blackPawnsFilesBb);

    }

    public void computeSideQuantities(final Node newNode, final byte sideColor)
            throws Exception {

        movesNode = newNode;

        /*
         * semi-open file sets
         */

        switch (sideColor) {
        case Colors.WHITE: movesNode.whiteSemiOFilesBb = ~movesNode.whitePawnsFilesBb & movesNode.blackPawnsFilesBb; break;
        case Colors.BLACK: movesNode.blackSemiOFilesBb = ~movesNode.blackPawnsFilesBb & movesNode.whitePawnsFilesBb; break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

        /*
         * kings rings bitboards
         */

        switch (sideColor) {
        case Colors.WHITE:
            movesNode.blackKRing1Bb = BitBoards.KRING1_LT[Long.numberOfTrailingZeros(movesNode.blackKingBb)];
            movesNode.blackKRing2Bb = BitBoards.KRING2_LT[Long.numberOfTrailingZeros(movesNode.blackKingBb)];
            break;
        case Colors.BLACK:
            movesNode.whiteKRing1Bb = BitBoards.KRING1_LT[Long.numberOfTrailingZeros(movesNode.whiteKingBb)];
            movesNode.whiteKRing2Bb = BitBoards.KRING2_LT[Long.numberOfTrailingZeros(movesNode.whiteKingBb)];
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

    }

    /******************************************************************************************************************
     *** controlled squares
     ******************************************************************************************************************/

    public void computeSideControlled(final Node newNode, final byte sideColor)
            throws Exception {

        movesNode = newNode;

        switch (sideColor) {
        case Colors.WHITE:
            movesNode.whiteControlledBb = BitBoards.EMPTY;
            if (movesNode.whitePawnsBb   != BitBoards.EMPTY) { computeWhitePawnsControlledBb(); }
            if (movesNode.whiteKnightsBb != BitBoards.EMPTY) { computeKnightsControlledBb( Colors.WHITE, movesNode.whiteKnightsBb ); }
            if (movesNode.whiteBishopsBb != BitBoards.EMPTY) { computeBishopsControlledBb( Colors.WHITE, movesNode.whiteBishopsBb ); }
            if (movesNode.whiteRooksBb   != BitBoards.EMPTY) { computeRooksControlledBb(   Colors.WHITE, movesNode.whiteRooksBb   ); }
            if (movesNode.whiteQueensBb  != BitBoards.EMPTY) { computeQueensControlledBb(  Colors.WHITE, movesNode.whiteQueensBb  ); }
            if (movesNode.whiteKingBb    != BitBoards.EMPTY) { computeKingControlledBb(    Colors.WHITE, movesNode.whiteKingBb    ); }
            break;
        case Colors.BLACK:
            movesNode.blackControlledBb = BitBoards.EMPTY;
            if (movesNode.blackPawnsBb   != BitBoards.EMPTY) { computeBlackPawnsControlledBb(); }
            if (movesNode.blackKnightsBb != BitBoards.EMPTY) { computeKnightsControlledBb( Colors.BLACK, movesNode.blackKnightsBb ); }
            if (movesNode.blackBishopsBb != BitBoards.EMPTY) { computeBishopsControlledBb( Colors.BLACK, movesNode.blackBishopsBb ); }
            if (movesNode.blackRooksBb   != BitBoards.EMPTY) { computeRooksControlledBb(   Colors.BLACK, movesNode.blackRooksBb   ); }
            if (movesNode.blackQueensBb  != BitBoards.EMPTY) { computeQueensControlledBb(  Colors.BLACK, movesNode.blackQueensBb  ); }
            if (movesNode.blackKingBb    != BitBoards.EMPTY) { computeKingControlledBb(    Colors.BLACK, movesNode.blackKingBb    ); }
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

    }

    private void computeWhitePawnsControlledBb() {

        /* NW + NW EP */
        movesNode.whiteControlledBb |= (movesNode.whitePawnsBb >>> BitBoards.ANTIGONAL_STEP) & ~BitBoards.FILE_H;

        /* NE + NW EP */
        movesNode.whiteControlledBb |= (movesNode.whitePawnsBb >>> BitBoards.DIAGONAL_STEP) & ~BitBoards.FILE_A;

    }

    private void computeBlackPawnsControlledBb() {

        /* SE + SE EP */
        movesNode.blackControlledBb |= (movesNode.blackPawnsBb << BitBoards.ANTIGONAL_STEP) & ~BitBoards.FILE_A;

        /* SW + SW EP */
        movesNode.blackControlledBb |= (movesNode.blackPawnsBb << BitBoards.DIAGONAL_STEP) & ~BitBoards.FILE_H;

    }

    private void computeKnightsControlledBb(final byte sideColor, final long initKnightsBb)
            throws Exception {

        long knightsBb = initKnightsBb;
        long nextPieceBb = knightsBb & -knightsBb;
        long toSquaresBb;
        byte nextPieceSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
            toSquaresBb = BitBoards.KNIGHT_MOVES_LT[nextPieceSquare];
            switch (sideColor) {
            case Colors.WHITE: movesNode.whiteControlledBb |= toSquaresBb; break;
            case Colors.BLACK: movesNode.blackControlledBb |= toSquaresBb; break;
            default:
                throw new Exception("sideColor=" + sideColor);
            }
            knightsBb &= ~nextPieceBb;
            nextPieceBb = knightsBb & -knightsBb;
        }

    }

    private void computeBishopsControlledBb(final byte sideColor, final long initBishopsBb)
            throws Exception {

        long bishopsBb = initBishopsBb;
        long nextPieceBb = bishopsBb & -bishopsBb;
        long toSquaresBb;
        byte nextPieceSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
            toSquaresBb = BitBoards.diagonalAntigonalMoves(nextPieceSquare, nextPieceBb, movesNode.allPiecesBb);
            switch (sideColor) {
            case Colors.WHITE: movesNode.whiteControlledBb |= toSquaresBb; break;
            case Colors.BLACK: movesNode.blackControlledBb |= toSquaresBb; break;
            default:
                throw new Exception("sideColor=" + sideColor);
            }
            bishopsBb &= ~nextPieceBb;
            nextPieceBb = bishopsBb & -bishopsBb;
        }

    }

    private void computeRooksControlledBb(final byte sideColor, final long initRooksBb)
            throws Exception {

        long rooksBb = initRooksBb;
        long nextPieceBb = rooksBb & -rooksBb;
        long toSquaresBb;
        byte nextPieceSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
            toSquaresBb = BitBoards.horizontalVerticalMoves(nextPieceSquare, nextPieceBb, movesNode.allPiecesBb);
            switch (sideColor) {
            case Colors.WHITE: movesNode.whiteControlledBb |= toSquaresBb; break;
            case Colors.BLACK: movesNode.blackControlledBb |= toSquaresBb; break;
            default:
                throw new Exception("sideColor=" + sideColor);
            }
            rooksBb &= ~nextPieceBb;
            nextPieceBb = rooksBb & -rooksBb;
        }

    }

    private void computeQueensControlledBb(final byte sideColor, final long initQueensBb)
            throws Exception {

        long queensBb = initQueensBb;
        long nextPieceBb = queensBb & -queensBb;
        long toSquaresBb;
        byte nextPieceSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
            toSquaresBb = (BitBoards.horizontalVerticalMoves(nextPieceSquare, nextPieceBb, movesNode.allPiecesBb)
                    | BitBoards.diagonalAntigonalMoves(nextPieceSquare, nextPieceBb, movesNode.allPiecesBb));
            switch (sideColor) {
            case Colors.WHITE: movesNode.whiteControlledBb |= toSquaresBb; break;
            case Colors.BLACK: movesNode.blackControlledBb |= toSquaresBb; break;
            default:
                throw new Exception("sideColor=" + sideColor);
            }
            queensBb &= ~nextPieceBb;
            nextPieceBb = queensBb & -queensBb;
        }

    }

    private void computeKingControlledBb(final byte sideColor, final long kingBb)
            throws Exception {

        final byte pieceSquare = (byte) Long.numberOfTrailingZeros(kingBb);

        final long toSquaresBb = BitBoards.KING_MOVES_LT[pieceSquare];

        switch (sideColor) {
        case Colors.WHITE: movesNode.whiteControlledBb |= toSquaresBb; break;
        case Colors.BLACK: movesNode.blackControlledBb |= toSquaresBb; break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

    }

    public void printMovesList(String searchPhase, Move[] movesList, int movesListMaxIndex) {

        Move[] sortedMovesList = new Move[MAX_MOVES_IN_POSITION];

        int smlCt = 0;
        for (Move m : movesList) {
            if (m != null) {
                ++smlCt;
                sortedMovesList[smlCt] = m;
            }
        }

        try {
            Arrays.sort(sortedMovesList, 0, movesListMaxIndex + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(searchPhase + ": maxIndex=" + movesListMaxIndex +
                " sortedMovesList=" + Arrays.toString(sortedMovesList)
                        .replaceAll(", null", ""));

    }

    /******************************************************************************************************************
     *** moves generation
     ******************************************************************************************************************/

    public void computePlayerMovesBb(final Node newNode)
            throws Exception {

        long whiteTargetsBb, blackTargetsBb, enPassantBb;

        movesNode = newNode;

        movesList = new Move[MAX_MOVES_IN_POSITION];
        movesListMaxIndex = START_MOVES_LIST_INDEX;

        computeBoardQuantities(movesNode);
        computeSideQuantities(movesNode, movesNode.playerColor);
        computeSideControlled(movesNode, (byte) -movesNode.playerColor);

        if (movesNode.enPassantSquare == null) {
            enPassantBb = BitBoards.EMPTY;
        } else {
            enPassantBb = (BitBoards.ONE << movesNode.enPassantSquare);
        }

        switch (movesNode.playerColor) {
        case Colors.WHITE:
            movesNode.blackKRing2Bb = BitBoards.KRING2_LT[Long.numberOfTrailingZeros(movesNode.blackKingBb)];
            if (movesNode.whitePawnsBb   != BitBoards.EMPTY) {
                whiteTargetsBb = movesNode.blackPiecesBb & ~movesNode.blackKingBb;
                computeWhitePawnsMovesBb(whiteTargetsBb | enPassantBb);
            }
            whiteTargetsBb = ~movesNode.whitePiecesBb & ~movesNode.blackKingBb;
            if (movesNode.whiteKnightsBb != BitBoards.EMPTY) { computeKnightsMovesBb( Colors.WHITE, movesNode.whiteKnightsBb, whiteTargetsBb); }
            if (movesNode.whiteBishopsBb != BitBoards.EMPTY) { computeBishopsMovesBb( Colors.WHITE, movesNode.whiteBishopsBb, whiteTargetsBb); }
            if (movesNode.whiteRooksBb   != BitBoards.EMPTY) { computeRooksMovesBb(   Colors.WHITE, movesNode.whiteRooksBb,   whiteTargetsBb);
                computeWhiteShortCg();
                computeWhiteLongCg();
            }
            if (movesNode.whiteQueensBb  != BitBoards.EMPTY) { computeQueensMovesBb(  Colors.WHITE, movesNode.whiteQueensBb,  whiteTargetsBb); }
            whiteTargetsBb = ~movesNode.whitePiecesBb & ~movesNode.blackKingBb & ~movesNode.blackControlledBb;
            if (movesNode.whiteKingBb    != BitBoards.EMPTY) { computeKingMovesBb(    Colors.WHITE, movesNode.whiteKingBb,    whiteTargetsBb); }
            break;
        case Colors.BLACK:
            movesNode.whiteKRing2Bb = BitBoards.KRING2_LT[Long.numberOfTrailingZeros(movesNode.whiteKingBb)];
            if (movesNode.blackPawnsBb   != BitBoards.EMPTY) {
                blackTargetsBb = movesNode.whitePiecesBb & ~movesNode.whiteKingBb;
                computeBlackPawnsMovesBb(blackTargetsBb | enPassantBb);
            }
            blackTargetsBb = ~movesNode.blackPiecesBb & ~movesNode.whiteKingBb;
            if (movesNode.blackKnightsBb != BitBoards.EMPTY) { computeKnightsMovesBb( Colors.BLACK, movesNode.blackKnightsBb, blackTargetsBb); }
            if (movesNode.blackBishopsBb != BitBoards.EMPTY) { computeBishopsMovesBb( Colors.BLACK, movesNode.blackBishopsBb, blackTargetsBb); }
            if (movesNode.blackRooksBb   != BitBoards.EMPTY) { computeRooksMovesBb(   Colors.BLACK, movesNode.blackRooksBb,   blackTargetsBb);
                computeBlackShortCg();
                computeBlackLongCg();
            }
            if (movesNode.blackQueensBb  != BitBoards.EMPTY) { computeQueensMovesBb(  Colors.BLACK, movesNode.blackQueensBb,  blackTargetsBb); }
            blackTargetsBb = ~movesNode.blackPiecesBb & ~movesNode.whiteKingBb & ~movesNode.whiteControlledBb;
            if (movesNode.blackKingBb    != BitBoards.EMPTY) { computeKingMovesBb(    Colors.BLACK, movesNode.blackKingBb,    blackTargetsBb); }
            break;
        default:
            throw new Exception("movesNode.playerColor=" + movesNode.playerColor);
        }

    }

    private void computeWhitePawnsMovesBb(final long whiteTargetsBb)
            throws Exception {

        final byte sidePawn = Pieces.WHITE_PAWN;
        long toSquaresBb, nextToSquareBb, fromSquareBb;
        byte fromSquare, toSquare;

        /* N 1 */
        toSquaresBb = (movesNode.whitePawnsBb >>> BitBoards.VERTICAL_STEP) & ~movesNode.allPiecesBb & ~BitBoards.RANK_8;
        nextToSquareBb = toSquaresBb & -toSquaresBb;
        while (nextToSquareBb != BitBoards.EMPTY) {
            toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
            fromSquare = (byte) (toSquare + BitBoards.VERTICAL_STEP);
            fromSquareBb = (BitBoards.ONE << fromSquare);
            addPawnStep(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            toSquaresBb &= ~nextToSquareBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
        }

        /* N 1 - promotion */
        toSquaresBb = (movesNode.whitePawnsBb >>> BitBoards.VERTICAL_STEP) & ~movesNode.allPiecesBb & BitBoards.RANK_8;
        nextToSquareBb = toSquaresBb & -toSquaresBb;
        while (nextToSquareBb != BitBoards.EMPTY) {
            toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
            fromSquare = (byte) (toSquare + BitBoards.VERTICAL_STEP);
            fromSquareBb = (BitBoards.ONE << fromSquare);
            addPawnStepProm(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            toSquaresBb &= ~nextToSquareBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
        }

        /* N 2 */
        toSquaresBb = (movesNode.whitePawnsBb >>> BitBoards.VERTICAL_DOUBLE_STEP) & ~movesNode.allPiecesBb
                & (~movesNode.allPiecesBb >>> BitBoards.VERTICAL_STEP) & BitBoards.RANK_4;
        nextToSquareBb = toSquaresBb & -toSquaresBb;
        while (nextToSquareBb != BitBoards.EMPTY) {
            toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
            fromSquare = (byte) (toSquare + BitBoards.VERTICAL_DOUBLE_STEP);
            fromSquareBb = (BitBoards.ONE << fromSquare);
            addPawnDoubleStep(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            toSquaresBb &= ~nextToSquareBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
        }

        /* NW + NW EP */
        toSquaresBb = (movesNode.whitePawnsBb >>> BitBoards.ANTIGONAL_STEP) & whiteTargetsBb
                & ~BitBoards.FILE_H & ~BitBoards.RANK_8;
        nextToSquareBb = toSquaresBb & -toSquaresBb;
        while (nextToSquareBb != BitBoards.EMPTY) {
            toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
            fromSquare = (byte) (toSquare + BitBoards.ANTIGONAL_STEP);
            fromSquareBb = (BitBoards.ONE << fromSquare);
            if (movesNode.enPassantSquare != null
                    && toSquare == movesNode.enPassantSquare) {
                addPawnEnPassant(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            } else {
                addPawnCaptures(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            }
            toSquaresBb &= ~nextToSquareBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
        }

        /* NW - promotion */
        toSquaresBb = (movesNode.whitePawnsBb >>> BitBoards.ANTIGONAL_STEP) & whiteTargetsBb
                & ~BitBoards.FILE_H & BitBoards.RANK_8;
        nextToSquareBb = toSquaresBb & -toSquaresBb;
        while (nextToSquareBb != BitBoards.EMPTY) {
            toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
            fromSquare = (byte) (toSquare + BitBoards.ANTIGONAL_STEP);
            fromSquareBb = (BitBoards.ONE << fromSquare);
            addPawnCapturesProm(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            toSquaresBb &= ~nextToSquareBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
        }

        /* NE + NE EP */
        toSquaresBb = (movesNode.whitePawnsBb >>> BitBoards.DIAGONAL_STEP) & whiteTargetsBb
                & ~BitBoards.FILE_A & ~BitBoards.RANK_8;
        nextToSquareBb = toSquaresBb & -toSquaresBb;
        while (nextToSquareBb != BitBoards.EMPTY) {
            toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
            fromSquare = (byte) (toSquare + BitBoards.DIAGONAL_STEP);
            fromSquareBb = (BitBoards.ONE << fromSquare);
            if (movesNode.enPassantSquare != null
                    && toSquare == movesNode.enPassantSquare) {
                addPawnEnPassant(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            } else {
                addPawnCaptures(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            }
            toSquaresBb &= ~nextToSquareBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
        }

        /* NE - promotion */
        toSquaresBb = (movesNode.whitePawnsBb >>> BitBoards.DIAGONAL_STEP) & whiteTargetsBb
                & ~BitBoards.FILE_A & BitBoards.RANK_8;
        nextToSquareBb = toSquaresBb & -toSquaresBb;
        while (nextToSquareBb != BitBoards.EMPTY) {
            toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
            fromSquare = (byte) (toSquare + BitBoards.DIAGONAL_STEP);
            fromSquareBb = (BitBoards.ONE << fromSquare);
            addPawnCapturesProm(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            toSquaresBb &= ~nextToSquareBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
        }

    }

    private void computeBlackPawnsMovesBb(final long blackTargetsBb)
            throws Exception {

        byte sidePawn = Pieces.BLACK_PAWN;
        long toSquaresBb, nextToSquareBb, fromSquareBb;
        byte fromSquare, toSquare;

        /* S 1 */
        toSquaresBb = (movesNode.blackPawnsBb << BitBoards.VERTICAL_STEP) & ~movesNode.allPiecesBb & ~BitBoards.RANK_1;
        nextToSquareBb = toSquaresBb & -toSquaresBb;
        while (nextToSquareBb != BitBoards.EMPTY) {
            toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
            fromSquare = (byte) (toSquare - BitBoards.VERTICAL_STEP);
            fromSquareBb = (BitBoards.ONE << fromSquare);
            addPawnStep(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            toSquaresBb &= ~nextToSquareBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
        }

        /* S 1 - promotion */
        toSquaresBb = (movesNode.blackPawnsBb << BitBoards.VERTICAL_STEP) & ~movesNode.allPiecesBb & BitBoards.RANK_1;
        nextToSquareBb = toSquaresBb & -toSquaresBb;
        while (nextToSquareBb != BitBoards.EMPTY) {
            toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
            fromSquare = (byte) (toSquare - BitBoards.VERTICAL_STEP);
            fromSquareBb = (BitBoards.ONE << fromSquare);
            addPawnStepProm(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            toSquaresBb &= ~nextToSquareBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
        }

        /* S 2 */
        toSquaresBb = (movesNode.blackPawnsBb << BitBoards.VERTICAL_DOUBLE_STEP) & ~movesNode.allPiecesBb
                & (~movesNode.allPiecesBb << BitBoards.VERTICAL_STEP) & BitBoards.RANK_5;
        nextToSquareBb = toSquaresBb & -toSquaresBb;
        while (nextToSquareBb != BitBoards.EMPTY) {
            toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
            fromSquare = (byte) (toSquare - BitBoards.VERTICAL_DOUBLE_STEP);
            fromSquareBb = (BitBoards.ONE << fromSquare);
            addPawnDoubleStep(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            toSquaresBb &= ~nextToSquareBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
        }

        /* SE + SE EP */
        toSquaresBb = (movesNode.blackPawnsBb << BitBoards.ANTIGONAL_STEP) & blackTargetsBb
                & ~BitBoards.FILE_A & ~BitBoards.RANK_1;
        nextToSquareBb = toSquaresBb & -toSquaresBb;
        while (nextToSquareBb != BitBoards.EMPTY) {
            toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
            fromSquare = (byte) (toSquare - BitBoards.ANTIGONAL_STEP);
            fromSquareBb = (BitBoards.ONE << fromSquare);
            if (movesNode.enPassantSquare != null
                    && toSquare == movesNode.enPassantSquare) {
                addPawnEnPassant(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            } else {
                addPawnCaptures(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            }
            toSquaresBb &= ~nextToSquareBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
        }

        /* SE - promotion */
        toSquaresBb = (movesNode.blackPawnsBb << BitBoards.ANTIGONAL_STEP) & blackTargetsBb
                & ~BitBoards.FILE_A & BitBoards.RANK_1;
        nextToSquareBb = toSquaresBb & -toSquaresBb;
        while (nextToSquareBb != BitBoards.EMPTY) {
            toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
            fromSquare = (byte) (toSquare - BitBoards.ANTIGONAL_STEP);
            fromSquareBb = (BitBoards.ONE << fromSquare);
            addPawnCapturesProm(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            toSquaresBb &= ~nextToSquareBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
        }

        /* SW + SW EP */
        toSquaresBb = (movesNode.blackPawnsBb << BitBoards.DIAGONAL_STEP) & blackTargetsBb
                & ~BitBoards.FILE_H & ~BitBoards.RANK_1;
        nextToSquareBb = toSquaresBb & -toSquaresBb;
        while (nextToSquareBb != BitBoards.EMPTY) {
            toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
            fromSquare = (byte) (toSquare - BitBoards.DIAGONAL_STEP);
            fromSquareBb = (BitBoards.ONE << fromSquare);
            if (movesNode.enPassantSquare != null
                    && toSquare == movesNode.enPassantSquare) {
                addPawnEnPassant(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            } else {
                addPawnCaptures(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            }
            toSquaresBb &= ~nextToSquareBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
        }

        /* SW - promotion */
        toSquaresBb = (movesNode.blackPawnsBb << BitBoards.DIAGONAL_STEP) & blackTargetsBb
                & ~BitBoards.FILE_H & BitBoards.RANK_1;
        nextToSquareBb = toSquaresBb & -toSquaresBb;
        while (nextToSquareBb != BitBoards.EMPTY) {
            toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
            fromSquare = (byte) (toSquare - BitBoards.DIAGONAL_STEP);
            fromSquareBb = (BitBoards.ONE << fromSquare);
            addPawnCapturesProm(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
            toSquaresBb &= ~nextToSquareBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
        }

    }

    private void computeKnightsMovesBb(final byte sideColor, final long initKnightsBb, final long sideTargetsBb)
            throws Exception {

        long knightsBb = initKnightsBb;
        byte sideKnight = (byte) (sideColor * Pieces.ROLE_KNIGHT);
        long nextPieceBb = knightsBb & -knightsBb;
        long toSquaresBb, nextToSquareBb;
        byte nextPieceSquare, toSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
            toSquaresBb = BitBoards.KNIGHT_MOVES_LT[nextPieceSquare] & sideTargetsBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                addPieceMoves(sideColor, sideKnight, nextPieceSquare, toSquare, nextPieceBb, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
            knightsBb &= ~nextPieceBb;
            nextPieceBb = knightsBb & -knightsBb;
        }

    }

    private void computeBishopsMovesBb(final byte sideColor, final long initBishopsBb, final long sideTargetsBb)
            throws Exception {

        long bishopsBb = initBishopsBb;
        byte sideBishop = (byte) (sideColor * Pieces.ROLE_BISHOP);
        long nextPieceBb = bishopsBb & -bishopsBb;
        long toSquaresBb, nextToSquareBb;
        byte nextPieceSquare, toSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
            toSquaresBb = BitBoards.diagonalAntigonalMoves(nextPieceSquare, nextPieceBb, movesNode.allPiecesBb) & sideTargetsBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                addPieceMoves(sideColor, sideBishop, nextPieceSquare, toSquare, nextPieceBb, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
            bishopsBb &= ~nextPieceBb;
            nextPieceBb = bishopsBb & -bishopsBb;
        }

    }

    private void computeRooksMovesBb(final byte sideColor, final long initRooksBb, final long sideTargetsBb)
            throws Exception {

        long rooksBb = initRooksBb;
        byte sideRook = (byte) (sideColor * Pieces.ROLE_ROOK);
        long nextPieceBb = rooksBb & -rooksBb;
        long toSquaresBb, nextToSquareBb;
        byte nextPieceSquare, toSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
            toSquaresBb = BitBoards.horizontalVerticalMoves(nextPieceSquare, nextPieceBb, movesNode.allPiecesBb) & sideTargetsBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                addPieceMoves(sideColor, sideRook, nextPieceSquare, toSquare, nextPieceBb, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
            rooksBb &= ~nextPieceBb;
            nextPieceBb = rooksBb & -rooksBb;
        }

    }

    private void computeQueensMovesBb(final byte sideColor, final long initQueensBb, final long sideTargetsBb)
            throws Exception {

        long queensBb = initQueensBb;
        byte sideQueen = (byte) (sideColor * Pieces.ROLE_QUEEN);
        long nextPieceBb = queensBb & -queensBb;
        long toSquaresBb, nextToSquareBb;
        byte nextPieceSquare, toSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
            toSquaresBb = (BitBoards.horizontalVerticalMoves(nextPieceSquare, nextPieceBb, movesNode.allPiecesBb)
                    | BitBoards.diagonalAntigonalMoves(nextPieceSquare, nextPieceBb, movesNode.allPiecesBb)) & sideTargetsBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                addPieceMoves(sideColor, sideQueen, nextPieceSquare, toSquare, nextPieceBb, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
            queensBb &= ~nextPieceBb;
            nextPieceBb = queensBb & -queensBb;
        }

    }

    private void computeKingMovesBb(final byte sideColor, final long kingBb, final long sideTargetsBb)
            throws Exception {

        byte sideKing = (byte) (sideColor * Pieces.ROLE_KING);
        long toSquaresBb, nextToSquareBb;
        byte pieceSquare, toSquare;

        pieceSquare = (byte) Long.numberOfTrailingZeros(kingBb);
        toSquaresBb = BitBoards.KING_MOVES_LT[pieceSquare] & sideTargetsBb;
        nextToSquareBb = toSquaresBb & -toSquaresBb;
        while (nextToSquareBb != BitBoards.EMPTY) {
            toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
            addPieceMoves(sideColor, sideKing, pieceSquare, toSquare, kingBb, nextToSquareBb);
            toSquaresBb &= ~nextToSquareBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
        }

    }

    private void computeWhiteShortCg()
            throws Exception {

        if (movesNode.whiteShortCg
                && (BitBoards.W_S_CG_PATH & ~movesNode.allPiecesBb) == BitBoards.W_S_CG_PATH
                && (BitBoards.W_S_CG_KING_PATH & movesNode.blackControlledBb) == BitBoards.EMPTY) {
            addWhiteShortCg();
        }

    }

    private void computeWhiteLongCg()
            throws Exception {

        if (movesNode.whiteLongCg
                && (BitBoards.W_L_CG_PATH & ~movesNode.allPiecesBb) == BitBoards.W_L_CG_PATH
                && (BitBoards.W_L_CG_KING_PATH & movesNode.blackControlledBb) == BitBoards.EMPTY) {
            addWhiteLongCg();
        }

    }

    private void computeBlackShortCg()
            throws Exception {

        if (movesNode.blackShortCg
                && (BitBoards.B_S_CG_PATH & ~movesNode.allPiecesBb) == BitBoards.B_S_CG_PATH
                && (BitBoards.B_S_CG_KING_PATH & movesNode.whiteControlledBb) == BitBoards.EMPTY) {
            addBlackShortCg();
        }

    }

    private void computeBlackLongCg()
            throws Exception {

        if (movesNode.blackLongCg
                && (BitBoards.B_L_CG_PATH & ~movesNode.allPiecesBb) == BitBoards.B_L_CG_PATH
                && (BitBoards.B_L_CG_KING_PATH & movesNode.whiteControlledBb) == BitBoards.EMPTY) {
            addBlackLongCg();
        }

    }

    /********************************************************************************************************************
     *** add moves
     ********************************************************************************************************************/

    private void addPawnStep(final byte sideColor, final byte piece, final byte fromSquare, final byte toSquare,
            final long fromSquareBb, final long toSquareBb)
                    throws Exception {

        final Move newMove;

        newMove = new Move(Functions.MOVEMENT, fromSquare, toSquare, piece, null, null);
        newMove.orderValue = computeMoveScore(sideColor, newMove, fromSquareBb, toSquareBb);

        if (!delayedLegalityCheck) {
            Node nextNode = new Node(movesNode, newMove);
            if (nextNode.gameState == States.NOT_LEGAL) {
                return;
            }
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void addPawnStepProm(final byte sideColor, final byte piece, final byte fromSquare, final byte toSquare,
            final long fromSquareBb, final long toSquareBb)
                    throws Exception {

        Move newMove;

        if (!delayedLegalityCheck) {
            newMove = new Move(Functions.MOVEMENT, fromSquare, toSquare, piece, null, null);
            Node nextNode = new Node(movesNode, newMove);
            if (nextNode.gameState == States.NOT_LEGAL) {
                return;
            }
        }

        newMove = new Move(Functions.MOVEMENT, fromSquare, toSquare, piece, null, (byte) (sideColor * Pieces.ROLE_QUEEN));
        newMove.orderValue = computeMoveScore(sideColor, newMove, fromSquareBb, toSquareBb);
        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

        newMove = new Move(Functions.MOVEMENT, fromSquare, toSquare, piece, null, (byte) (sideColor * Pieces.ROLE_ROOK));
        newMove.orderValue = computeMoveScore(sideColor, newMove, fromSquareBb, toSquareBb);
        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

        newMove = new Move(Functions.MOVEMENT, fromSquare, toSquare, piece, null, (byte) (sideColor * Pieces.ROLE_BISHOP));
        newMove.orderValue = computeMoveScore(sideColor, newMove, fromSquareBb, toSquareBb);
        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

        newMove = new Move(Functions.MOVEMENT, fromSquare, toSquare, piece, null, (byte) (sideColor * Pieces.ROLE_KNIGHT));
        newMove.orderValue = computeMoveScore(sideColor, newMove, fromSquareBb, toSquareBb);
        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void addPawnDoubleStep(final byte sideColor, final byte piece, final byte fromSquare, final byte toSquare,
            final long fromSquareBb, final long toSquareBb)
                    throws Exception {

        final Move newMove;

        newMove = new Move(Functions.MOVEMENT, fromSquare, toSquare, piece, null, null);
        newMove.orderValue = computeMoveScore(sideColor, newMove, fromSquareBb, toSquareBb);

        if (!delayedLegalityCheck) {
            Node nextNode = new Node(movesNode, newMove);
            if (nextNode.gameState == States.NOT_LEGAL) {
                return;
            }
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void addPawnCaptures(final byte sideColor, final byte piece, final byte fromSquare, final byte toSquare,
            final long fromSquareBb, final long toSquareBb)
                    throws Exception {

        final Move newMove;

        newMove = new Move(Functions.CAPTURE, fromSquare, toSquare, piece, movesNode.squarePieceMap[toSquare], null);
        newMove.orderValue = computeMoveScore(sideColor, newMove, fromSquareBb, toSquareBb);

        if (!delayedLegalityCheck) {
            Node nextNode = new Node(movesNode, newMove);
            if (nextNode.gameState == States.NOT_LEGAL) {
                return;
            }
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void addPawnCapturesProm(final byte sideColor, final byte piece, final byte fromSquare, final byte toSquare,
            final long fromSquareBb, final long toSquareBb)
                    throws Exception {

        final byte targetPiece = movesNode.squarePieceMap[toSquare];

        Move newMove;

        if (!delayedLegalityCheck) {
            newMove = new Move(Functions.CAPTURE, fromSquare, toSquare, piece, targetPiece, null);
            Node nextNode = new Node(movesNode, newMove);
            if (nextNode.gameState == States.NOT_LEGAL) {
                return;
            }
        }

        newMove = new Move(Functions.CAPTURE, fromSquare, toSquare, piece, targetPiece,
                (byte) (sideColor * Pieces.ROLE_QUEEN));
        newMove.orderValue = computeMoveScore(sideColor, newMove, fromSquareBb, toSquareBb);
        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

        newMove = new Move(Functions.CAPTURE, fromSquare, toSquare, piece, targetPiece,
                (byte) (sideColor * Pieces.ROLE_ROOK));
        newMove.orderValue = computeMoveScore(sideColor, newMove, fromSquareBb, toSquareBb);
        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

        newMove = new Move(Functions.CAPTURE, fromSquare, toSquare, piece, targetPiece,
                (byte) (sideColor * Pieces.ROLE_BISHOP));
        newMove.orderValue = computeMoveScore(sideColor, newMove, fromSquareBb, toSquareBb);
        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

        newMove = new Move(Functions.CAPTURE, fromSquare, toSquare, piece, targetPiece,
                (byte) (sideColor * Pieces.ROLE_KNIGHT));
        newMove.orderValue = computeMoveScore(sideColor, newMove, fromSquareBb, toSquareBb);
        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void addPawnEnPassant(final byte sideColor, final byte piece, final byte fromSquare, final byte toSquare,
            final long fromSquareBb, final long toSquareBb)
                    throws Exception {

        final byte targetPiece = (byte) (-sideColor * Pieces.ROLE_PAWN);

        Move newMove;

        // if (squarePieceMap[toSquare] != null) {
        // non necessario testare: la casa e.p. segnala da sé la presenza di un pedone avversario

        // if (sideColor != Math.signum(targetPiece)) {
        // non necessario testare: la casa e.p. si attiva solo per 1 mossa, necessariamente dell'avversario

        newMove = new Move(Functions.EN_PASSANT, fromSquare, toSquare, piece, targetPiece, null);
        newMove.orderValue = computeMoveScore(sideColor, newMove, fromSquareBb, toSquareBb);

        if (!delayedLegalityCheck) {
            Node nextNode = new Node(movesNode, newMove);
            if (nextNode.gameState == States.NOT_LEGAL) {
                return;
            }
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void addPieceMoves(final byte sideColor, final byte piece, final byte fromSquare, final byte toSquare,
            final long fromSquareBb, final long toSquareBb)
                    throws Exception {

        final Byte targetPiece = movesNode.squarePieceMap[toSquare];

        Move newMove;

        if (targetPiece == null) {
            newMove = new Move(Functions.MOVEMENT, fromSquare, toSquare, piece, null, null);
            newMove.orderValue = computeMoveScore(sideColor, newMove, fromSquareBb, toSquareBb);

            if (!delayedLegalityCheck) {
                Node nextNode = new Node(movesNode, newMove);
                if (nextNode.gameState == States.NOT_LEGAL) {
                    return;
                }
            }
        } else {
            newMove = new Move(Functions.CAPTURE, fromSquare, toSquare, piece, targetPiece, null);
            newMove.orderValue = computeMoveScore(sideColor, newMove, fromSquareBb, toSquareBb);

            if (!delayedLegalityCheck) {
                Node nextNode = new Node(movesNode, newMove);
                if (nextNode.gameState == States.NOT_LEGAL) {
                    return;
                }
            }
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;
    }

    private void addWhiteShortCg()
            throws Exception {

        Move newMove;

        newMove = new Move(Functions.SHORT_CG, null, null, null, null, null);
        newMove.orderValue = computeMoveScore(Colors.WHITE, newMove,
                /*fromSquareBb*/BitBoards.EMPTY, /*toSquareBb*/BitBoards.EMPTY);

        if (!delayedLegalityCheck) {
            Node nextNode = new Node(movesNode, newMove);
            if (nextNode.gameState == States.NOT_LEGAL) {
                return;
            }
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void addWhiteLongCg()
            throws Exception {

        Move newMove;

        newMove = new Move(Functions.LONG_CG, null, null, null, null, null);
        newMove.orderValue = computeMoveScore(Colors.WHITE, newMove,
                /*fromSquareBb*/BitBoards.EMPTY, /*toSquareBb*/BitBoards.EMPTY);

        if (!delayedLegalityCheck) {
            Node nextNode = new Node(movesNode, newMove);
            if (nextNode.gameState == States.NOT_LEGAL) {
                return;
            }
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void addBlackShortCg()
            throws Exception {

        Move newMove;

        newMove = new Move(Functions.SHORT_CG, null, null, null, null, null);
        newMove.orderValue = computeMoveScore(Colors.BLACK, newMove,
                /*fromSquareBb*/BitBoards.EMPTY, /*toSquareBb*/BitBoards.EMPTY);

        if (!delayedLegalityCheck) {
            Node nextNode = new Node(movesNode, newMove);
            if (nextNode.gameState == States.NOT_LEGAL) {
                return;
            }
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void addBlackLongCg()
            throws Exception {

        Move newMove;

        newMove = new Move(Functions.LONG_CG, null, null, null, null, null);
        newMove.orderValue = computeMoveScore(Colors.BLACK, newMove,
                /*fromSquareBb*/BitBoards.EMPTY, /*toSquareBb*/BitBoards.EMPTY);

        if (!delayedLegalityCheck) {
            Node nextNode = new Node(movesNode, newMove);
            if (nextNode.gameState == States.NOT_LEGAL) {
                return;
            }
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private Integer computeMoveScore(final byte sideColor, final Move move, final long fromSquareBb, final long toSquareBb)
            throws Exception {

        int value;

        /*
         * functions:
         *         case 1: return "MOVEMENT"
         *         case 2: return "SHORT_CG"
         *         case 3: return "LONG_CG"
         *         case 4: return "EN_PASSANT"
         *         case 5: return "CAPTURE"
         */

        value = morFunctionCoeff * move.function;

        if (move.targetPiece == null) {
            if (move.fromSquare != null) {
                /*
                 * steps: promotions
                 */
                if (move.promotionPiece != null) {
                    value += morPromRoleCoeff * Math.abs(move.promotionPiece);
                }
                /*
                 * steps: killer/history heuristics
                 */
                if (killerHeuristic) {
                    value += killersRepsList[movesNode.treeLevel][move.fromSquare][move.toSquare];
                }
                if (historyHeuristic) {
                    value += historyRepsList[sideColor == Colors.WHITE ? 1 : 0][move.fromSquare][move.toSquare];
                }
                /*
                 * steps: from/to square attacked
                 */
                value += - checkOnControlledScore(sideColor, fromSquareBb);
                value += checkOnControlledScore(sideColor, toSquareBb);
                /*
                 * steps: from/to king rings
                 */
                value += - checkOnKRingsScore(sideColor, fromSquareBb);
                value += checkOnKRingsScore(sideColor, toSquareBb);
                /*
                 * steps: from/to centre / centre rings
                 */
                value += - checkOnCRingsScore(sideColor, fromSquareBb);
                value += checkOnCRingsScore(sideColor, toSquareBb);
            }
        } else {
            /*
             * captures/en-passant: MVV-LVA heuristic
             */
            value += morTargetRoleCoeff  * Math.abs(move.targetPiece);
            if (move.promotionPiece == null) {
                value += -morPieceRoleCoeff * Math.abs(move.piece);
            } else {
                /*
                 * captures: promotions
                 */
                value += -morPromRoleCoeff * Math.abs(move.promotionPiece);
            }
        }

        return value;
    }

    private int checkOnCRingsScore(final byte sideColor, final long squareBb) {

        int cRingsScore = 0;

        if ((BitBoards.CENTRE_RING_2 & squareBb) != BitBoards.EMPTY) {
            cRingsScore += morOnCRing2Score;
        } else if ((BitBoards.CENTRE_RING_1 & squareBb) != BitBoards.EMPTY) {
            cRingsScore += morOnCRing1Score;
        } else if ((BitBoards.CENTRE & squareBb) != BitBoards.EMPTY) {
            cRingsScore += morOnCentreScore;
        }

        return cRingsScore;
    }

    private int checkOnKRingsScore(final byte sideColor, final long squareBb)
            throws Exception {

        int kRingsScore = 0;

        switch (sideColor) {
        case Colors.WHITE:
            if ((movesNode.blackKRing2Bb & squareBb) != BitBoards.EMPTY) {
                kRingsScore += morOnKRing2Score;
            } else if ((movesNode.blackKRing1Bb & squareBb) != BitBoards.EMPTY) {
                kRingsScore += morOnKRing1Score;
            }
            break;
        case Colors.BLACK:
            if ((movesNode.whiteKRing2Bb & squareBb) != BitBoards.EMPTY) {
                kRingsScore += morOnKRing2Score;
            } else if ((movesNode.whiteKRing1Bb & squareBb) != BitBoards.EMPTY) {
                kRingsScore += morOnKRing1Score;
            }
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

        return kRingsScore;
    }

    private int checkOnControlledScore(final byte sideColor, final long squareBb)
            throws Exception {

        switch (sideColor) {
        case Colors.WHITE:
            if ((movesNode.blackControlledBb & squareBb) != BitBoards.EMPTY) {
                return morOnCtrlledSquareScore;
            }
            break;
        case Colors.BLACK:
            if ((movesNode.whiteControlledBb & squareBb) != BitBoards.EMPTY) {
                return morOnCtrlledSquareScore;
            }
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

        return 0;
    }

    /******************************************************************************************************************
     *** moves checking
     ******************************************************************************************************************/

    public void checkPlayerMovesBb(final Node newNode)
            throws Exception {

        long whiteTargetsBb, blackTargetsBb, enPassantBb;

        movesNode = newNode;

        movesList = new Move[MAX_MOVES_IN_POSITION];
        movesListMaxIndex = START_MOVES_LIST_INDEX;

        moveFound = false;

        computeBoardQuantities(movesNode);
        computeSideQuantities(movesNode, movesNode.playerColor);
        computeSideControlled(movesNode, (byte) -movesNode.playerColor);

        if (movesNode.enPassantSquare == null) {
            enPassantBb = BitBoards.EMPTY;
        } else {
            enPassantBb = (BitBoards.ONE << movesNode.enPassantSquare);
        }

        switch (movesNode.playerColor) {
        case Colors.WHITE:
            movesNode.blackKRing2Bb = BitBoards.KRING2_LT[Long.numberOfTrailingZeros(movesNode.blackKingBb)];
            if (movesNode.whitePawnsBb   != BitBoards.EMPTY) {
                whiteTargetsBb = movesNode.blackPiecesBb & ~movesNode.blackKingBb;
                checkWhitePawnsMovesBb(whiteTargetsBb | enPassantBb);
            }
            whiteTargetsBb = ~movesNode.whitePiecesBb & ~movesNode.blackKingBb;
            if (movesNode.whiteKnightsBb != BitBoards.EMPTY) { checkKnightsMovesBb( Colors.WHITE, movesNode.whiteKnightsBb, whiteTargetsBb); }
            if (movesNode.whiteBishopsBb != BitBoards.EMPTY) { checkBishopsMovesBb( Colors.WHITE, movesNode.whiteBishopsBb, whiteTargetsBb); }
            if (movesNode.whiteRooksBb   != BitBoards.EMPTY) { checkRooksMovesBb(   Colors.WHITE, movesNode.whiteRooksBb,   whiteTargetsBb);
                checkWhiteShortCg();
                checkWhiteLongCg();
            }
            if (movesNode.whiteQueensBb  != BitBoards.EMPTY) { checkQueensMovesBb(  Colors.WHITE, movesNode.whiteQueensBb,  whiteTargetsBb); }
            whiteTargetsBb = ~movesNode.whitePiecesBb & ~movesNode.blackKingBb & ~movesNode.blackControlledBb;
            if (movesNode.whiteKingBb    != BitBoards.EMPTY) { checkKingMovesBb(    Colors.WHITE, movesNode.whiteKingBb,    whiteTargetsBb); }
            break;
        case Colors.BLACK:
            movesNode.whiteKRing2Bb = BitBoards.KRING2_LT[Long.numberOfTrailingZeros(movesNode.whiteKingBb)];
            if (movesNode.blackPawnsBb   != BitBoards.EMPTY) {
                blackTargetsBb = movesNode.whitePiecesBb & ~movesNode.whiteKingBb;
                checkBlackPawnsMovesBb(blackTargetsBb | enPassantBb);
            }
            blackTargetsBb = ~movesNode.blackPiecesBb & ~movesNode.whiteKingBb;
            if (movesNode.blackKnightsBb != BitBoards.EMPTY) { checkKnightsMovesBb( Colors.BLACK, movesNode.blackKnightsBb, blackTargetsBb); }
            if (movesNode.blackBishopsBb != BitBoards.EMPTY) { checkBishopsMovesBb( Colors.BLACK, movesNode.blackBishopsBb, blackTargetsBb); }
            if (movesNode.blackRooksBb   != BitBoards.EMPTY) { checkRooksMovesBb(   Colors.BLACK, movesNode.blackRooksBb,   blackTargetsBb);
                checkBlackShortCg();
                checkBlackLongCg();
            }
            if (movesNode.blackQueensBb  != BitBoards.EMPTY) { checkQueensMovesBb(  Colors.BLACK, movesNode.blackQueensBb,  blackTargetsBb); }
            blackTargetsBb = ~movesNode.blackPiecesBb & ~movesNode.whiteKingBb & ~movesNode.whiteControlledBb;
            if (movesNode.blackKingBb    != BitBoards.EMPTY) { checkKingMovesBb(    Colors.BLACK, movesNode.blackKingBb,    blackTargetsBb); }
            break;
        default:
            throw new Exception("movesNode.playerColor=" + movesNode.playerColor);
        }

    }

    private void checkWhitePawnsMovesBb(final long whiteTargetsBb)
            throws Exception {

        final byte sidePawn = Pieces.WHITE_PAWN;

        long toSquaresBb, nextToSquareBb, fromSquareBb;

        byte fromSquare, toSquare;

        if (!moveFound) {
            /* N 1 */
            toSquaresBb = (movesNode.whitePawnsBb >>> BitBoards.VERTICAL_STEP) & ~movesNode.allPiecesBb & ~BitBoards.RANK_8;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (!moveFound
                    && nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                fromSquare = (byte) (toSquare + BitBoards.VERTICAL_STEP);
                fromSquareBb = (BitBoards.ONE << fromSquare);
                checkPawnStep(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
        }

        if (!moveFound) {
            /* N 1 - promotion */
            toSquaresBb = (movesNode.whitePawnsBb >>> BitBoards.VERTICAL_STEP) & ~movesNode.allPiecesBb & BitBoards.RANK_8;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (!moveFound
                    && nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                fromSquare = (byte) (toSquare + BitBoards.VERTICAL_STEP);
                fromSquareBb = (BitBoards.ONE << fromSquare);
                checkPawnStepProm(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
        }

        if (!moveFound) {
            /* N 2 */
            toSquaresBb = (movesNode.whitePawnsBb >>> BitBoards.VERTICAL_DOUBLE_STEP) & ~movesNode.allPiecesBb
                    & (~movesNode.allPiecesBb >>> BitBoards.VERTICAL_STEP) & BitBoards.RANK_4;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (!moveFound
                    && nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                fromSquare = (byte) (toSquare + BitBoards.VERTICAL_DOUBLE_STEP);
                fromSquareBb = (BitBoards.ONE << fromSquare);
                checkPawnDoubleStep(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
        }

        if (!moveFound) {
            /* NW + NW EP */
            toSquaresBb = (movesNode.whitePawnsBb >>> BitBoards.ANTIGONAL_STEP) & whiteTargetsBb
                    & ~BitBoards.FILE_H & ~BitBoards.RANK_8;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (!moveFound
                    && nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                fromSquare = (byte) (toSquare + BitBoards.ANTIGONAL_STEP);
                fromSquareBb = (BitBoards.ONE << fromSquare);
                if (movesNode.enPassantSquare != null
                        && toSquare == movesNode.enPassantSquare) {
                    checkPawnEnPassant(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                } else {
                    checkPawnCaptures(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                }
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
        }

        if (!moveFound) {
            /* NW - promotion */
            toSquaresBb = (movesNode.whitePawnsBb >>> BitBoards.ANTIGONAL_STEP) & whiteTargetsBb
                    & ~BitBoards.FILE_H & BitBoards.RANK_8;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (!moveFound
                    && nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                fromSquare = (byte) (toSquare + BitBoards.ANTIGONAL_STEP);
                fromSquareBb = (BitBoards.ONE << fromSquare);
                checkPawnCapturesProm(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
        }

        if (!moveFound) {
            /* NE + NE EP */
            toSquaresBb = (movesNode.whitePawnsBb >>> BitBoards.DIAGONAL_STEP) & whiteTargetsBb
                    & ~BitBoards.FILE_A & ~BitBoards.RANK_8;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (!moveFound
                    && nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                fromSquare = (byte) (toSquare + BitBoards.DIAGONAL_STEP);
                fromSquareBb = (BitBoards.ONE << fromSquare);
                if (movesNode.enPassantSquare != null
                        && toSquare == movesNode.enPassantSquare) {
                    checkPawnEnPassant(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                } else {
                    checkPawnCaptures(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                }
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
        }

        if (!moveFound) {
            /* NE - promotion */
            toSquaresBb = (movesNode.whitePawnsBb >>> BitBoards.DIAGONAL_STEP) & whiteTargetsBb
                    & ~BitBoards.FILE_A & BitBoards.RANK_8;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (!moveFound
                    && nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                fromSquare = (byte) (toSquare + BitBoards.DIAGONAL_STEP);
                fromSquareBb = (BitBoards.ONE << fromSquare);
                checkPawnCapturesProm(Colors.WHITE, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
        }

    }

    private void checkBlackPawnsMovesBb(final long blackTargetsBb)
            throws Exception {

        byte sidePawn = Pieces.BLACK_PAWN;
        long toSquaresBb, nextToSquareBb, fromSquareBb;
        byte fromSquare, toSquare;

        if (!moveFound) {
            /* S 1 */
            toSquaresBb = (movesNode.blackPawnsBb << BitBoards.VERTICAL_STEP) & ~movesNode.allPiecesBb & ~BitBoards.RANK_1;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (!moveFound
                    && nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                fromSquare = (byte) (toSquare - BitBoards.VERTICAL_STEP);
                fromSquareBb = (BitBoards.ONE << fromSquare);
                checkPawnStep(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
        }

        if (!moveFound) {
            /* S 1 - promotion */
            toSquaresBb = (movesNode.blackPawnsBb << BitBoards.VERTICAL_STEP) & ~movesNode.allPiecesBb & BitBoards.RANK_1;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (!moveFound
                    && nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                fromSquare = (byte) (toSquare - BitBoards.VERTICAL_STEP);
                fromSquareBb = (BitBoards.ONE << fromSquare);
                checkPawnStepProm(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
        }

        if (!moveFound) {
            /* S 2 */
            toSquaresBb = (movesNode.blackPawnsBb << BitBoards.VERTICAL_DOUBLE_STEP) & ~movesNode.allPiecesBb
                    & (~movesNode.allPiecesBb << BitBoards.VERTICAL_STEP) & BitBoards.RANK_5;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (!moveFound
                    && nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                fromSquare = (byte) (toSquare - BitBoards.VERTICAL_DOUBLE_STEP);
                fromSquareBb = (BitBoards.ONE << fromSquare);
                checkPawnDoubleStep(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
        }

        if (!moveFound) {
            /* SE + SE EP */
            toSquaresBb = (movesNode.blackPawnsBb << BitBoards.ANTIGONAL_STEP) & blackTargetsBb
                    & ~BitBoards.FILE_A & ~BitBoards.RANK_1;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (!moveFound
                    && nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                fromSquare = (byte) (toSquare - BitBoards.ANTIGONAL_STEP);
                fromSquareBb = (BitBoards.ONE << fromSquare);
                if (movesNode.enPassantSquare != null
                        && toSquare == movesNode.enPassantSquare) {
                    checkPawnEnPassant(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                } else {
                    checkPawnCaptures(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                }
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
        }

        if (!moveFound) {
            /* SE - promotion */
            toSquaresBb = (movesNode.blackPawnsBb << BitBoards.ANTIGONAL_STEP) & blackTargetsBb
                    & ~BitBoards.FILE_A & BitBoards.RANK_1;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (!moveFound
                    && nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                fromSquare = (byte) (toSquare - BitBoards.ANTIGONAL_STEP);
                fromSquareBb = (BitBoards.ONE << fromSquare);
                checkPawnCapturesProm(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
        }

        if (!moveFound) {
            /* SW + SW EP */
            toSquaresBb = (movesNode.blackPawnsBb << BitBoards.DIAGONAL_STEP) & blackTargetsBb
                    & ~BitBoards.FILE_H & ~BitBoards.RANK_1;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (!moveFound
                    && nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                fromSquare = (byte) (toSquare - BitBoards.DIAGONAL_STEP);
                fromSquareBb = (BitBoards.ONE << fromSquare);
                if (movesNode.enPassantSquare != null
                        && toSquare == movesNode.enPassantSquare) {
                    checkPawnEnPassant(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                } else {
                    checkPawnCaptures(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                }
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
        }

        if (!moveFound) {
            /* SW - promotion */
            toSquaresBb = (movesNode.blackPawnsBb << BitBoards.DIAGONAL_STEP) & blackTargetsBb
                    & ~BitBoards.FILE_H & BitBoards.RANK_1;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (!moveFound
                    && nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                fromSquare = (byte) (toSquare - BitBoards.DIAGONAL_STEP);
                fromSquareBb = (BitBoards.ONE << fromSquare);
                checkPawnCapturesProm(Colors.BLACK, sidePawn, fromSquare, toSquare, fromSquareBb, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
        }

    }

    private void checkKnightsMovesBb(final byte sideColor, final long initKnightsBb, final long sideTargetsBb)
            throws Exception {

        if (!moveFound) {
            long knightsBb = initKnightsBb;
            byte sideKnight = (byte) (sideColor * Pieces.ROLE_KNIGHT);
            long nextPieceBb = knightsBb & -knightsBb;
            long toSquaresBb, nextToSquareBb;
            byte nextPieceSquare, toSquare;

            while (!moveFound
                    && nextPieceBb != BitBoards.EMPTY) {
                nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
                toSquaresBb = BitBoards.KNIGHT_MOVES_LT[nextPieceSquare] & sideTargetsBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
                while (!moveFound
                        && nextToSquareBb != BitBoards.EMPTY) {
                    toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                    checkPieceMoves(sideColor, sideKnight, nextPieceSquare, toSquare, nextPieceBb, nextToSquareBb);
                    toSquaresBb &= ~nextToSquareBb;
                    nextToSquareBb = toSquaresBb & -toSquaresBb;
                }
                knightsBb &= ~nextPieceBb;
                nextPieceBb = knightsBb & -knightsBb;
            }
        }

    }

    private void checkBishopsMovesBb(final byte sideColor, final long initBishopsBb, final long sideTargetsBb)
            throws Exception {

        if (!moveFound) {
            long bishopsBb = initBishopsBb;
            byte sideBishop = (byte) (sideColor * Pieces.ROLE_BISHOP);
            long nextPieceBb = bishopsBb & -bishopsBb;
            long toSquaresBb, nextToSquareBb;
            byte nextPieceSquare, toSquare;

            while (!moveFound
                    && nextPieceBb != BitBoards.EMPTY) {
                nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
                toSquaresBb = BitBoards.diagonalAntigonalMoves(nextPieceSquare, nextPieceBb, movesNode.allPiecesBb) & sideTargetsBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
                while (!moveFound
                        && nextToSquareBb != BitBoards.EMPTY) {
                    toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                    checkPieceMoves(sideColor, sideBishop, nextPieceSquare, toSquare, nextPieceBb, nextToSquareBb);
                    toSquaresBb &= ~nextToSquareBb;
                    nextToSquareBb = toSquaresBb & -toSquaresBb;
                }
                bishopsBb &= ~nextPieceBb;
                nextPieceBb = bishopsBb & -bishopsBb;
            }
        }

    }

    private void checkRooksMovesBb(final byte sideColor, final long initRooksBb, final long sideTargetsBb)
            throws Exception {

        if (!moveFound) {
            long rooksBb = initRooksBb;
            byte sideRook = (byte) (sideColor * Pieces.ROLE_ROOK);
            long nextPieceBb = rooksBb & -rooksBb;
            long toSquaresBb, nextToSquareBb;
            byte nextPieceSquare, toSquare;

            while (!moveFound
                    && nextPieceBb != BitBoards.EMPTY) {
                nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
                toSquaresBb = BitBoards.horizontalVerticalMoves(nextPieceSquare, nextPieceBb, movesNode.allPiecesBb) & sideTargetsBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
                while (!moveFound
                        && nextToSquareBb != BitBoards.EMPTY) {
                    toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                    checkPieceMoves(sideColor, sideRook, nextPieceSquare, toSquare, nextPieceBb, nextToSquareBb);
                    toSquaresBb &= ~nextToSquareBb;
                    nextToSquareBb = toSquaresBb & -toSquaresBb;
                }
                rooksBb &= ~nextPieceBb;
                nextPieceBb = rooksBb & -rooksBb;
            }
        }

    }

    private void checkQueensMovesBb(final byte sideColor, final long initQueensBb, final long sideTargetsBb)
            throws Exception {

        if (!moveFound) {
            long queensBb = initQueensBb;
            byte sideQueen = (byte) (sideColor * Pieces.ROLE_QUEEN);
            long nextPieceBb = queensBb & -queensBb;
            long toSquaresBb, nextToSquareBb;
            byte nextPieceSquare, toSquare;

            while (!moveFound
                    && nextPieceBb != BitBoards.EMPTY) {
                nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
                toSquaresBb = (BitBoards.horizontalVerticalMoves(nextPieceSquare, nextPieceBb, movesNode.allPiecesBb)
                        | BitBoards.diagonalAntigonalMoves(nextPieceSquare, nextPieceBb, movesNode.allPiecesBb)) & sideTargetsBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
                while (!moveFound
                        && nextToSquareBb != BitBoards.EMPTY) {
                    toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                    checkPieceMoves(sideColor, sideQueen, nextPieceSquare, toSquare, nextPieceBb, nextToSquareBb);
                    toSquaresBb &= ~nextToSquareBb;
                    nextToSquareBb = toSquaresBb & -toSquaresBb;
                }
                queensBb &= ~nextPieceBb;
                nextPieceBb = queensBb & -queensBb;
            }
        }

    }

    private void checkKingMovesBb(final byte sideColor, final long kingBb, final long sideTargetsBb)
            throws Exception {

        if (!moveFound) {
            byte sideKing = (byte) (sideColor * Pieces.ROLE_KING);
            long toSquaresBb, nextToSquareBb;
            byte pieceSquare, toSquare;

            //if (!moveFound) {
                pieceSquare = (byte) Long.numberOfTrailingZeros(kingBb);
                toSquaresBb = BitBoards.KING_MOVES_LT[pieceSquare] & sideTargetsBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
                while (!moveFound
                        && nextToSquareBb != BitBoards.EMPTY) {
                    toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                    checkPieceMoves(sideColor, sideKing, pieceSquare, toSquare, kingBb, nextToSquareBb);
                    toSquaresBb &= ~nextToSquareBb;
                    nextToSquareBb = toSquaresBb & -toSquaresBb;
                }
            //}
        }

    }

    private void checkWhiteShortCg()
            throws Exception {

        if (!moveFound) {
            if (movesNode.whiteShortCg
                    && (BitBoards.W_S_CG_PATH & ~movesNode.allPiecesBb) == BitBoards.W_S_CG_PATH
                    && (BitBoards.W_S_CG_KING_PATH & movesNode.blackControlledBb) == BitBoards.EMPTY) {
                checkAddWhiteShortCg();
            }
        }

    }

    private void checkWhiteLongCg()
            throws Exception {

        if (!moveFound) {
            if (movesNode.whiteLongCg
                    && (BitBoards.W_L_CG_PATH & ~movesNode.allPiecesBb) == BitBoards.W_L_CG_PATH
                    && (BitBoards.W_L_CG_KING_PATH & movesNode.blackControlledBb) == BitBoards.EMPTY) {
                checkAddWhiteLongCg();
            }
        }

    }

    private void checkBlackShortCg()
            throws Exception {

        if (!moveFound) {
            if (movesNode.blackShortCg
                    && (BitBoards.B_S_CG_PATH & ~movesNode.allPiecesBb) == BitBoards.B_S_CG_PATH
                    && (BitBoards.B_S_CG_KING_PATH & movesNode.whiteControlledBb) == BitBoards.EMPTY) {
                checkAddBlackShortCg();
            }
        }

    }

    private void checkBlackLongCg()
            throws Exception {

        if (!moveFound) {
            if (movesNode.blackLongCg
                    && (BitBoards.B_L_CG_PATH & ~movesNode.allPiecesBb) == BitBoards.B_L_CG_PATH
                    && (BitBoards.B_L_CG_KING_PATH & movesNode.whiteControlledBb) == BitBoards.EMPTY) {
                checkAddBlackLongCg();
            }
        }

    }

    /********************************************************************************************************************
     *** check add moves
     ********************************************************************************************************************/

    private void checkPawnStep(final byte sideColor, final byte piece, final byte fromSquare, final byte toSquare,
            final long fromSquareBb, final long toSquareBb)
                    throws Exception {

        final Move newMove;

        newMove = new Move(Functions.MOVEMENT, fromSquare, toSquare, piece, null, null);

        Node nextNode = new Node(movesNode, newMove);
        if (nextNode.gameState == States.NOT_LEGAL) {
            return;
        } else {
            moveFound = true;
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void checkPawnStepProm(final byte sideColor, final byte piece, final byte fromSquare, final byte toSquare,
            final long fromSquareBb, final long toSquareBb)
                    throws Exception {

        Move newMove;

        newMove = new Move(Functions.MOVEMENT, fromSquare, toSquare, piece, null, null);
        Node nextNode = new Node(movesNode, newMove);
        if (nextNode.gameState == States.NOT_LEGAL) {
            return;
        } else {
            moveFound = true;
        }

        newMove = new Move(Functions.MOVEMENT, fromSquare, toSquare, piece, null,
                (byte) (sideColor * Pieces.ROLE_QUEEN));
        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

        newMove = new Move(Functions.MOVEMENT, fromSquare, toSquare, piece, null,
                (byte) (sideColor * Pieces.ROLE_ROOK));
        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

        newMove = new Move(Functions.MOVEMENT, fromSquare, toSquare, piece, null,
                (byte) (sideColor * Pieces.ROLE_BISHOP));
        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

        newMove = new Move(Functions.MOVEMENT, fromSquare, toSquare, piece, null,
                (byte) (sideColor * Pieces.ROLE_KNIGHT));
        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void checkPawnDoubleStep(final byte sideColor, final byte piece, final byte fromSquare, final byte toSquare,
            final long fromSquareBb, final long toSquareBb)
                    throws Exception {

        final Move newMove;

        newMove = new Move(Functions.MOVEMENT, fromSquare, toSquare, piece, null, null);

        Node nextNode = new Node(movesNode, newMove);
        if (nextNode.gameState == States.NOT_LEGAL) {
            return;
        } else {
            moveFound = true;
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void checkPawnCaptures(final byte sideColor, final byte piece, final byte fromSquare, final byte toSquare,
            final long fromSquareBb, final long toSquareBb)
                    throws Exception {

        final Move newMove;

        newMove = new Move(Functions.CAPTURE, fromSquare, toSquare, piece, movesNode.squarePieceMap[toSquare], null);

        Node nextNode = new Node(movesNode, newMove);
        if (nextNode.gameState == States.NOT_LEGAL) {
            return;
        } else {
            moveFound = true;
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void checkPawnCapturesProm(final byte sideColor, final byte piece, final byte fromSquare, final byte toSquare,
            final long fromSquareBb, final long toSquareBb)
                    throws Exception {

        final byte targetPiece = movesNode.squarePieceMap[toSquare];

        Move newMove;

        newMove = new Move(Functions.CAPTURE, fromSquare, toSquare, piece, targetPiece, null);
        Node nextNode = new Node(movesNode, newMove);
        if (nextNode.gameState == States.NOT_LEGAL) {
            return;
        } else {
            moveFound = true;
        }

        newMove = new Move(Functions.CAPTURE, fromSquare, toSquare, piece, targetPiece,
                (byte) (sideColor * Pieces.ROLE_QUEEN));
        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

        newMove = new Move(Functions.CAPTURE, fromSquare, toSquare, piece, targetPiece,
                (byte) (sideColor * Pieces.ROLE_ROOK));
        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

        newMove = new Move(Functions.CAPTURE, fromSquare, toSquare, piece, targetPiece,
                (byte) (sideColor * Pieces.ROLE_BISHOP));
        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

        newMove = new Move(Functions.CAPTURE, fromSquare, toSquare, piece, targetPiece,
                (byte) (sideColor * Pieces.ROLE_KNIGHT));
        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void checkPawnEnPassant(final byte sideColor, final byte piece, final byte fromSquare, final byte toSquare,
            final long fromSquareBb, final long toSquareBb)
                    throws Exception {

        final byte targetPiece = (byte) (-sideColor * Pieces.ROLE_PAWN);

        Move newMove;

        // if (squarePieceMap[toSquare] != null) {
        // non necessario testare: la casa e.p. segnala da sé la presenza di un pedone avversario

        // if (sideColor != Math.signum(targetPiece)) {
        // non necessario testare: la casa e.p. si attiva solo per 1 mossa, necessariamente dell'avversario

        newMove = new Move(Functions.EN_PASSANT, fromSquare, toSquare, piece, targetPiece, null);

        Node nextNode = new Node(movesNode, newMove);
        if (nextNode.gameState == States.NOT_LEGAL) {
            return;
        } else {
            moveFound = true;
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void checkPieceMoves(final byte sideColor, final byte piece, final byte fromSquare, final byte toSquare,
            final long fromSquareBb, final long toSquareBb)
                    throws Exception {

        final Byte targetPiece = movesNode.squarePieceMap[toSquare];

        Move newMove;

        if (targetPiece == null) {
            newMove = new Move(Functions.MOVEMENT, fromSquare, toSquare, piece, null, null);

            Node nextNode = new Node(movesNode, newMove);
            if (nextNode.gameState == States.NOT_LEGAL) {
                return;
            } else {
                moveFound = true;
            }
        } else {
            newMove = new Move(Functions.CAPTURE, fromSquare, toSquare, piece, targetPiece, null);

            Node nextNode = new Node(movesNode, newMove);
            if (nextNode.gameState == States.NOT_LEGAL) {
                return;
            } else {
                moveFound = true;
            }
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void checkAddWhiteShortCg()
            throws Exception {

        Move newMove;

        newMove = new Move(Functions.SHORT_CG, null, null, null, null, null);

        Node nextNode = new Node(movesNode, newMove);
        if (nextNode.gameState == States.NOT_LEGAL) {
            return;
        } else {
            moveFound = true;
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void checkAddWhiteLongCg()
            throws Exception {

        Move newMove;

        newMove = new Move(Functions.LONG_CG, null, null, null, null, null);

        Node nextNode = new Node(movesNode, newMove);
        if (nextNode.gameState == States.NOT_LEGAL) {
            return;
        } else {
            moveFound = true;
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void checkAddBlackShortCg()
            throws Exception {

        Move newMove;

        newMove = new Move(Functions.SHORT_CG, null, null, null, null, null);

        Node nextNode = new Node(movesNode, newMove);
        if (nextNode.gameState == States.NOT_LEGAL) {
            return;
        } else {
            moveFound = true;
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    private void checkAddBlackLongCg()
            throws Exception {

        Move newMove;

        newMove = new Move(Functions.LONG_CG, null, null, null, null, null);

        Node nextNode = new Node(movesNode, newMove);
        if (nextNode.gameState == States.NOT_LEGAL) {
            return;
        } else {
            moveFound = true;
        }

        ++movesListMaxIndex;
        movesList[movesListMaxIndex] = newMove;

    }

    /******************************************************************************************************************
     *** perft
     ******************************************************************************************************************/

    public void perftTest(final Game newGame, final Node newRootNode)
            throws Exception {

        game = newGame;
        searchNode = new Node(newRootNode);

        System.out.println("PERFT TEST");
        System.out.println(searchNode.toStringDebug());

        for (int depth = 0; depth <= searchDepth; ++depth) {

            clearPerftStats();

            final long searchStartMillis = System.currentTimeMillis();

            perftSearch(searchNode, depth);

            final long searchTimeMillis = System.currentTimeMillis() - searchStartMillis;

            printPerftStats(depth, searchTimeMillis);

        }

    }

    private void perftSearch(final Node node, final int depth)
            throws Exception {

        Move move;
        Integer score = null, nextScore;
        int checkedMovesCounter = 0;

        Node anteNode = null;

        boolean prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg;
        Byte prevEnPassantSquare;
        short prevHalfmovesClock;
        byte prevGameState;

        if (node.gameState != States.ONGOING
                && node.gameState != States.CHECK) {
            return;
        }

        if (depth == 0) {
            return;
        }

        computePlayerMovesBb(node);
        Move[] movesList = this.movesList;
        int movesListMaxIndex = this.movesListMaxIndex;

        if (movesListMaxIndex != START_MOVES_LIST_INDEX) {

            move = selectNextMove(node, checkedMovesCounter, false, movesList, movesListMaxIndex);

            while (move != null) {
                ++checkedMovesCounter;
                prevWhiteShortCg    = node.whiteShortCg;
                prevWhiteLongCg     = node.whiteLongCg;
                prevBlackShortCg    = node.blackShortCg;
                prevBlackLongCg     = node.blackLongCg;
                prevEnPassantSquare = node.enPassantSquare;
                prevHalfmovesClock  = node.halfmovesClock;
                prevGameState       = node.gameState;

                node.doMove(move);

                if (depth == 1) {
                    addPerftStats(node, move);
                }

                if (node.gameState != States.NOT_LEGAL) {
                    perftSearch(node, depth - 1);
                }

                node.undoMove(move, prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg,
                        prevEnPassantSquare, prevHalfmovesClock, prevGameState);

                move = selectNextMove(node, checkedMovesCounter, false, movesList, movesListMaxIndex);
            }

        }

    }

    private void clearPerftStats() {

        nodesCt      = 0;
        stepsCt      = 0;
        capturesCt   = 0;
        enPassantCt  = 0;
        castlesCt    = 0;
        shortCCt     = 0;
        longCCt      = 0;
        promotionsCt = 0;
        checksCt     = 0;
        matesCt      = 0;
        stalesCt     = 0;
        notLegalsCt  = 0;

    }

    private void addPerftStats(final Node node, final Move genMove)
            throws Exception {

        if (node.gameState == States.NOT_LEGAL) {
            ++notLegalsCt;
        } else {
            ++nodesCt;
            if (genMove != null) {
                switch (genMove.function) {
                    case Functions.MOVEMENT   : ++stepsCt     ; if (genMove.promotionPiece != null) { ++promotionsCt; } break;
                    case Functions.CAPTURE    : ++capturesCt  ; if (genMove.promotionPiece != null) { ++promotionsCt; } break;
                    case Functions.EN_PASSANT : ++capturesCt  ;
                        ++enPassantCt ; break;
                    case Functions.SHORT_CG   : ++shortCCt    ;
                        ++castlesCt   ; break;
                    case Functions.LONG_CG    : ++longCCt     ;
                        ++castlesCt   ; break;
                }
            }
            if (node.gameState == States.CHECK
                    || node.gameState == States.CHECKMATE) {
                ++checksCt;
            }
            switch (node.gameState) {
                case States.CHECKMATE:  ++checksCt; break;
                case States.STALE_MATE: ++stalesCt; break;
            }
            MOVES_ENGINE.checkPlayerMovesBb(node);
            if (!MOVES_ENGINE.moveFound) {
                if (node.gameState == States.CHECK) {
                    ++matesCt;
                } else {
                    ++stalesCt;
                }
            }
        }

    }

    private void printPerftStats(final int depth, final long searchTimeMillis) {

        System.out.println("------------------------------------" );
        System.out.println("depth=" + depth + " searchTimeMillis=" + searchTimeMillis);
        System.out.println(" - nodesCt           " + nodesCt      );
        System.out.println("    - stepsCt        " + stepsCt      );
        System.out.println("    - capturesCt     " + capturesCt   );
        System.out.println("       - enPassantCt " + enPassantCt  );
        System.out.println("    - castlesCt      " + castlesCt    );
        System.out.println("       - shortCt     " + shortCCt     );
        System.out.println("       - longCt      " + longCCt      );
        System.out.println("    - promotionsCt   " + promotionsCt );
        System.out.println("    - checksCt       " + checksCt     );
        System.out.println("    - matesCt        " + matesCt      );
        System.out.println("    - stalesCt       " + stalesCt     );
        System.out.println("    - notLegalsCt    " + notLegalsCt  );

    }

    /******************************************************************************************************************
     *** search
     ******************************************************************************************************************/

    public void searchBestMove(final Game newGame, final Node newRootNode)
            throws Exception {

        game = newGame;

        rootNode = new Node(newRootNode);

        searchNode = new Node(rootNode);
        searchStartMillis = System.currentTimeMillis();
        outsideStop = false;

        Integer newRootNodeValue;

        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println("START SEARCH --- currentSearchDepth=" + currentSearchDepth);
        System.out.println(rootNode.toStringDebug());

        if (iterDeepeningSearch) {
            currentSearchDepth = readPvMoves().size() - qSearchAddedDepth;
            if (currentSearchDepth < 0) { currentSearchDepth = 0; }
        } else {
            currentSearchDepth = searchDepth;
        }

        if (game.timeControlSet) {
            computeSearchLength();
        }

        while ((rootNode.gameState == States.ONGOING
                    || rootNode.gameState == States.CHECK)
                && currentSearchDepth <= searchDepth
                && (rootNodeValue == null || rootNodeValue < START_BETA)
                && (!game.timeControlSet || (System.currentTimeMillis() - searchStartMillis) < searchMaxLengthMillis)
                && !outsideStop) {

            visitedNodesCounter = 0;

            initHeuristicsLists();

            newRootNodeValue = search(searchNode, currentSearchDepth, START_ALPHA, START_BETA);

            if (newRootNodeValue != null) {
                rootNodeValue = newRootNodeValue;
                if (game.timeControlSet) {
                    adjustSearchLength();
                }
            }

            ++currentSearchDepth;

            System.out.println(getPvRecord().toString());

        }

    }

    private void computeSearchLength()
            throws Exception {

        if (inputSearchMaxLengthMillis == null) {
            final int leftTimeMillis;
            switch (rootNode.playerColor) {
                case Colors.WHITE: leftTimeMillis = game.whiteLeftTimeMillis; break;
                case Colors.BLACK: leftTimeMillis = game.blackLeftTimeMillis; break;
                default:
                    throw new Exception("computeSearchLength: rootNode.playerColor=" + rootNode.playerColor);
            }
            if (leftTimeMillis > 0) {
                searchMaxLengthMillis = leftTimeMillis / movesToGo - MainUtils.ENGINE_TIME_MILLIS;
            } else {
                searchMaxLengthMillis = 0;
            }
        } else {
            searchMaxLengthMillis = inputSearchMaxLengthMillis;
        }

        /*
         * minimum time
         */

        if (searchMaxLengthMillis < MainUtils.TIMER_PERIOD_MILLIS) {
            searchMaxLengthMillis = MainUtils.TIMER_PERIOD_MILLIS;
        }

    }

    private void adjustSearchLength()
            throws Exception {

        final int panicTime;

        long leftTimeMillis;
        switch (rootNode.playerColor) {
        case Colors.WHITE: leftTimeMillis = game.whiteLeftTimeMillis; break;
        case Colors.BLACK: leftTimeMillis = game.blackLeftTimeMillis; break;
        default:
            throw new Exception("computeSearchLength: rootNode.playerColor=" + rootNode.playerColor);
        }

        /*
         * panic time
         */

        if (rootNodeValue != null
                && rootNodeValue < 0) {
            panicTime = (int) ( ((double) leftTimeMillis / movesToGo) *
                    ((double) -rootNodeValue / matQueenMidgScore) );
            searchMaxLengthMillis += panicTime;
        }

    }

    public boolean searchTimeOut() {
        return (System.currentTimeMillis() - searchStartMillis) < searchMaxLengthMillis;
    }

    /*
     * search
     */

    private Integer search(final Node node, final int depth, int alpha, int beta)
            throws Exception {

        Move move;
        Integer score = null, nextScore;
        int checkedMovesCounter = 0;
        boolean pvMoveSearch = true;

        TRec tRec = null;

        boolean prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg;
        Byte prevEnPassantSquare;
        short prevHalfmovesClock;
        byte prevGameState;

        if (outsideStop) {
            return evaluation(node);
        }

        if (!delayedMateCheck
                && node.gameState == States.CHECK) {
            checkPlayerMovesBb(node);
            if (!moveFound) {
                node.gameState = States.CHECKMATE;
                return evaluation(node);
            }
        }

        if (node.gameState != States.ONGOING
                && node.gameState != States.CHECK) {
            return evaluation(node);
        }

        if (transpositionsMap) {
            tRec = tMap.get(node.nodeHashCode);
            if (tRec != null && tRec.depth >= depth) {
                score = tRec.nodeValue.intValue();
                /****/ if (tRec.accuracy == Accuracies.EXACT_VALUE) {
                    return score;
                } else if (tRec.accuracy == Accuracies.LOWER_BOUND && score > alpha) {
                    alpha = score;
                } else if (tRec.accuracy == Accuracies.UPPER_BOUND && score < beta) {
                    beta = score;
                }
            }
        }

        if (depth == 0) {
            /****/ if (searchCheckIncrement
                    && node.gameState == States.CHECK) {
                score = search(node, 1, alpha, beta);
            } else if (quiescentPosSearch && qChecksSearch) {
                score = quiescenceSearchWithCheck(node, qSearchAddedDepth, alpha, beta);
            } else if (quiescentPosSearch) {
                score = quiescenceSearch(node, qSearchAddedDepth, alpha, beta);
            } else {
                score = evaluation(node);
            }
            return score;
        }

        computePlayerMovesBb(node);
        Move[] movesList = this.movesList;
        int movesListMaxIndex = this.movesListMaxIndex;

        if (movesListMaxIndex != START_MOVES_LIST_INDEX) {
            //noinspection ConstantConditions
            move = selectNextMove(node, checkedMovesCounter, pvMoveSearch, movesList, movesListMaxIndex);
            while (move != null
                    && !outsideStop) {
                ++checkedMovesCounter;
                prevWhiteShortCg    = node.whiteShortCg;
                prevWhiteLongCg     = node.whiteLongCg;
                prevBlackShortCg    = node.blackShortCg;
                prevBlackLongCg     = node.blackLongCg;
                prevEnPassantSquare = node.enPassantSquare;
                prevHalfmovesClock  = node.halfmovesClock;
                prevGameState       = node.gameState;
                node.doMove(move);
                if (node.gameState != States.NOT_LEGAL) {
                    if (principalVarSearch) {
                        if (lateMoveReduction) {
                            if (pvMoveSearch) {
                                nextScore = search(node, depth - 1, -beta, -alpha);
                                score = (nextScore == null) ? null : -nextScore;
                            } else {
                                if (checkedMovesCounter > lateMoveMinMoves
                                        && depth > lateMoveSubtrDepth
                                        && move.function == Functions.MOVEMENT
                                        && node.gameState == States.ONGOING) {
                                    nextScore = zwSearch(node, depth - 1, -alpha - 1 - lateMoveSubtrDepth, -alpha);
                                    score = (nextScore == null) ? null : -nextScore;
                                    if (score != null
                                            && score > alpha) {
                                        nextScore = zwSearch(node, depth - 1, -alpha - 1, -alpha);
                                        score = (nextScore == null) ? null : -nextScore;
                                    }
                                } else {
                                    nextScore = zwSearch(node, depth - 1, -alpha - 1, -alpha);
                                    score = (nextScore == null) ? null : -nextScore;
                                }
                                if (score != null
                                        && score > alpha) {
                                    nextScore = search(node, depth - 1, -beta, -alpha);
                                    score = (nextScore == null) ? null : -nextScore;
                                }
                            }
                        } else {
                            if (pvMoveSearch) {
                                nextScore = search(node, depth - 1, -beta, -alpha);
                                score = (nextScore == null) ? null : -nextScore;
                            } else {
                                nextScore = zwSearch(node, depth - 1, -alpha - 1, -alpha);
                                score = (nextScore == null) ? null : -nextScore;
                                if (score != null
                                        && score > alpha) {
                                    nextScore = search(node, depth - 1, -beta, -alpha);
                                    score = (nextScore == null) ? null : -nextScore;
                                }
                            }
                        }
                    } else {
                        if (lateMoveReduction
                                && checkedMovesCounter > lateMoveMinMoves
                                && depth > lateMoveSubtrDepth
                                && move.function == Functions.MOVEMENT
                                && node.gameState == States.ONGOING) {
                            nextScore = search(node, depth - 1 - lateMoveSubtrDepth, -beta, -alpha);
                            score = (nextScore == null) ? null : -nextScore;
                            if (score != null
                                    && score > alpha) {
                                nextScore = search(node, depth - 1, -beta, -alpha);
                                score = (nextScore == null) ? null : -nextScore;
                            }
                        } else {
                            nextScore = search(node, depth - 1, -beta, -alpha);
                            score = (nextScore == null) ? null : -nextScore;
                        }
                    }
                }
                node.undoMove(move, prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg,
                        prevEnPassantSquare, prevHalfmovesClock, prevGameState);
                if (game.timeControlSet
                        && (System.currentTimeMillis() - searchStartMillis) >= searchMaxLengthMillis) {
                    return TIME_OUT_SEARCH_VALUE;
                }
                pvMoveSearch = false;
                if (score != null
                        && score > alpha) {
                    pvMap.put(node.nodeHashCode, move);
                    alpha = score;
                    if (historyHeuristic
                            && move.targetPiece == null
                            && move.fromSquare != null) {
                        ++historyRepsList[node.playerColor == 1 ? 1 : 0][move.fromSquare][move.toSquare];
                    }
                    if (alphaBetaPruning
                            && score >= beta) {
                        if (killerHeuristic
                                && move.targetPiece == null
                                && move.fromSquare != null) {
                            ++killersRepsList[node.treeLevel][move.fromSquare][move.toSquare];
                        }
                        if (transpositionsMap
                                && (tRec == null || tRec.depth < depth)) {
                            tMap.put(node.nodeHashCode, new TRec((byte) depth, Accuracies.LOWER_BOUND, score.shortValue()));
                        }
                        return beta;
                    }
                    if (transpositionsMap
                            && (tRec == null || tRec.depth < depth)) {
                        tMap.put(node.nodeHashCode,
                                new TRec((byte) depth, Accuracies.UPPER_BOUND, score.shortValue()));
                    }
                }
                //noinspection ConstantConditions
                move = selectNextMove(node, checkedMovesCounter, pvMoveSearch, movesList, movesListMaxIndex);
            }

        }

        // questa parte serve in caso di stallo, che non viene approfondito come per lo scacco
        if (score == null) {
            if (node.gameState == States.CHECK
                    /*restart*/ || node.gameState == States.CHECKMATE) {
                node.gameState = States.CHECKMATE;
            } else {
                node.gameState = States.STALE_MATE;
            }
            score = evaluation(node);
            if (score > alpha) {
                alpha = score;
            }
        }

        if (transpositionsMap
                && (tRec == null || tRec.depth < depth)) {
            tMap.put(node.nodeHashCode,
                    new TRec((byte) depth, Accuracies.EXACT_VALUE, score.shortValue()));
        }

        return alpha;
    }

    private Integer zwSearch(final Node node, final int depth, int alpha, int beta)
            throws Exception {

        Move move;
        Integer score = null, nextScore;
        int checkedMovesCounter = 0;
        boolean pvMoveSearch = true;

        TRec tRec = null;

        boolean prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg;
        Byte prevEnPassantSquare;
        short prevHalfmovesClock;
        byte prevGameState;

        if (outsideStop) {
            return evaluation(node);
        }

        if (!delayedMateCheck
                && node.gameState == States.CHECK) {
            checkPlayerMovesBb(node);
            if (!moveFound) {
                node.gameState = States.CHECKMATE;
                return evaluation(node);
            }
        }

        if (node.gameState != States.ONGOING
                && node.gameState != States.CHECK) {
            return evaluation(node);
        }

        if (transpositionsMap) {
            tRec = tMap.get(node.nodeHashCode);
            if (tRec != null && tRec.depth >= depth) {
                score = tRec.nodeValue.intValue();
                /****/ if (tRec.accuracy == Accuracies.EXACT_VALUE) {
                    return score;
                } else if (tRec.accuracy == Accuracies.LOWER_BOUND && score > alpha) {
                    alpha = score;
                } else if (tRec.accuracy == Accuracies.UPPER_BOUND && score < beta) {
                    beta = score;
                }
            }
        }

        if (depth == 0) {
            if (searchCheckIncrement
                    && node.gameState == States.CHECK) {
                score = zwSearch(node, 1, alpha, beta);
            } else {
                score = evaluation(node);
            }
            return score;
        }

        computePlayerMovesBb(node);
        Move[] movesList = this.movesList;
        int movesListMaxIndex = this.movesListMaxIndex;

        if (movesListMaxIndex != START_MOVES_LIST_INDEX) {
            //noinspection ConstantConditions
            move = selectNextMove(node, checkedMovesCounter, pvMoveSearch, movesList, movesListMaxIndex);
            while (move != null
                    && !outsideStop) {
                ++checkedMovesCounter;
                prevWhiteShortCg    = node.whiteShortCg;
                prevWhiteLongCg     = node.whiteLongCg;
                prevBlackShortCg    = node.blackShortCg;
                prevBlackLongCg     = node.blackLongCg;
                prevEnPassantSquare = node.enPassantSquare;
                prevHalfmovesClock  = node.halfmovesClock;
                prevGameState       = node.gameState;
                node.doMove(move);
                if (node.gameState != States.NOT_LEGAL) {
                    nextScore = zwSearch(node, depth - 1, -alpha - 1, -alpha);
                    score = (nextScore == null) ? null : -nextScore;
                }
                node.undoMove(move, prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg,
                        prevEnPassantSquare, prevHalfmovesClock, prevGameState);
                if (game.timeControlSet
                        && (System.currentTimeMillis() - searchStartMillis) >= searchMaxLengthMillis) {
                    return TIME_OUT_SEARCH_VALUE;
                }
                pvMoveSearch = false;
                if (score != null
                        && score > alpha) {
                    pvMap.put(node.nodeHashCode, move);
                    alpha = score;
                    if (historyHeuristic
                            && move.targetPiece == null
                            && move.fromSquare != null) {
                        ++historyRepsList[node.playerColor == 1 ? 1 : 0][move.fromSquare][move.toSquare];
                    }
                    if (alphaBetaPruning
                            && score >= beta) {
                        if (killerHeuristic
                                && move.targetPiece == null
                                && move.fromSquare != null) {
                            ++killersRepsList[node.treeLevel][move.fromSquare][move.toSquare];
                        }
                        if (transpositionsMap
                                && (tRec == null || tRec.depth < depth)) {
                            tMap.put(node.nodeHashCode,
                                    new TRec((byte) depth, Accuracies.LOWER_BOUND, score.shortValue()));
                        }
                        return beta;
                    }
                    if (transpositionsMap
                            && (tRec == null || tRec.depth < depth)) {
                        tMap.put(node.nodeHashCode,
                                new TRec((byte) depth, Accuracies.UPPER_BOUND, score.shortValue()));
                    }
                }
                //noinspection ConstantConditions
                move = selectNextMove(node, checkedMovesCounter, pvMoveSearch, movesList, movesListMaxIndex);
            }

        }

        // questa parte serve in caso di stallo, che non viene approfondito come per lo scacco
        if (score == null) {
            if (node.gameState == States.CHECK
                    /*restart*/ || node.gameState == States.CHECKMATE) {
                node.gameState = States.CHECKMATE;
            } else {
                node.gameState = States.STALE_MATE;
            }
            score = evaluation(node);
            if (score > alpha) {
                // ZWS-TT: non registra mai un EXACT_VALUE
                if (transpositionsMap
                        && (tRec == null || tRec.depth < depth)) {
                    tMap.put(node.nodeHashCode,
                            new TRec((byte) depth, Accuracies.UPPER_BOUND, score.shortValue()));
                }
                alpha = score;
            }
        }

        // ZWS-TT: non registra mai un EXACT_VALUE

        return alpha;
    }

    /*
     * quiescence search
     */

    private Integer quiescenceSearch(final Node node, final int depth, int alpha, int beta)
            throws Exception {

        final int standPatScore;

        Move move;
        Integer score = null, nextScore;
        int checkedMovesCounter = 0;

        TRec tRec = null;

        boolean prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg;
        Byte prevEnPassantSquare;
        short prevHalfmovesClock;
        byte prevGameState;

        standPatScore = evaluation(node);

        if (outsideStop) {
            return standPatScore;
        }

        if (!delayedMateCheck
                && node.gameState == States.CHECK) {
            checkPlayerMovesBb(node);
            if (!moveFound) {
                node.gameState = States.CHECKMATE;
                return standPatScore;
            }
        }

        if (node.gameState != States.ONGOING
                && node.gameState != States.CHECK) {
            return standPatScore;
        }

        if (transpositionsMap) {
            tRec = tMap.get(node.nodeHashCode);
            if (tRec != null && tRec.depth >= depth) {
                score = tRec.nodeValue.intValue();
                /****/ if (tRec.accuracy == Accuracies.EXACT_VALUE) {
                    return score;
                } else if (tRec.accuracy == Accuracies.LOWER_BOUND && score > alpha) {
                    alpha = score;
                } else if (tRec.accuracy == Accuracies.UPPER_BOUND && score < beta) {
                    beta = score;
                }
            }
        }

        if (depth == 0) {
            return standPatScore;
        }

        if (standPatScore > alpha) {
            alpha = standPatScore;
            if (standPatScore >= beta) {
                return beta;
            }
        }

        computePlayerMovesBb(node);
        Move[] movesList = this.movesList;
        int movesListMaxIndex = this.movesListMaxIndex;

        if (movesListMaxIndex != START_MOVES_LIST_INDEX) {
            move = selectNextMove(node, checkedMovesCounter, false, movesList, movesListMaxIndex);
            while (move != null
                    && !outsideStop) {
                if (move.targetPiece != null
                        || move.promotionPiece != null
                        || move.function == Functions.SHORT_CG
                        || move.function == Functions.LONG_CG) {
                    ++checkedMovesCounter;
                    prevWhiteShortCg    = node.whiteShortCg;
                    prevWhiteLongCg     = node.whiteLongCg;
                    prevBlackShortCg    = node.blackShortCg;
                    prevBlackLongCg     = node.blackLongCg;
                    prevEnPassantSquare = node.enPassantSquare;
                    prevHalfmovesClock  = node.halfmovesClock;
                    prevGameState       = node.gameState;
                    node.doMove(move);
                    if (node.gameState != States.NOT_LEGAL) {
                        nextScore = quiescenceSearch(node, depth - 1, -beta, -alpha);
                        score = (nextScore == null) ? null : -nextScore;
                    }
                    node.undoMove(move, prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg,
                            prevEnPassantSquare, prevHalfmovesClock, prevGameState);
                    if (game.timeControlSet
                            && (System.currentTimeMillis() - searchStartMillis) >= searchMaxLengthMillis) {
                        return TIME_OUT_SEARCH_VALUE;
                    }
                    if (score != null
                            && score > alpha) {
                        pvMap.put(node.nodeHashCode, move);
                        alpha = score;
                        if (alphaBetaPruning
                                && score >= beta) {
                            if (transpositionsMap
                                    && (tRec == null || tRec.depth < depth)) {
                                tMap.put(node.nodeHashCode,
                                        new TRec((byte) depth, Accuracies.LOWER_BOUND, score.shortValue()));
                            }
                            return beta;
                        }
                        if (transpositionsMap
                                && (tRec == null || tRec.depth < depth)) {
                            tMap.put(node.nodeHashCode,
                                    new TRec((byte) depth, Accuracies.UPPER_BOUND, score.shortValue()));
                        }
                    }
                }
                move = selectNextMove(node, checkedMovesCounter, false, movesList, movesListMaxIndex);

            }
        }

        // QUS-TT: non registra mai un EXACT_VALUE

        return alpha;
    }

    private Integer quiescenceSearchWithCheck(final Node node, final int depth, int alpha, int beta)
            throws Exception {

        final int standPatScore;

        Move move;
        Integer score = null, nextScore;
        int checkedMovesCounter = 0;

        TRec tRec = null;

        boolean prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg;
        Byte prevEnPassantSquare;
        short prevHalfmovesClock;
        byte prevGameState;

        standPatScore = evaluation(node);

        if (!delayedMateCheck
                && node.gameState == States.CHECK) {
            checkPlayerMovesBb(node);
            if (!moveFound) {
                node.gameState = States.CHECKMATE;
                return standPatScore;
            }
        }

        if (outsideStop) {
            return standPatScore;
        }

        if (node.gameState != States.ONGOING
                && node.gameState != States.CHECK) {
            return standPatScore;
        }

        if (depth == 0) {
            return standPatScore;
        }

        if (standPatScore > alpha) {
            alpha = standPatScore;
            if (standPatScore >= beta) {
                return beta;
            }
        }

        if (transpositionsMap) {
            tRec = tMap.get(node.nodeHashCode);
            if (tRec != null && tRec.depth >= depth) {
                score = tRec.nodeValue.intValue();
                /****/ if (tRec.accuracy == Accuracies.EXACT_VALUE) {
                    return score;
                } else if (tRec.accuracy == Accuracies.LOWER_BOUND && score > alpha) {
                    alpha = score;
                } else if (tRec.accuracy == Accuracies.UPPER_BOUND && score < beta) {
                    beta = score;
                }
            }
        }

        computePlayerMovesBb(node);
        Move[] movesList = this.movesList;
        int movesListMaxIndex = this.movesListMaxIndex;

        if (movesListMaxIndex != START_MOVES_LIST_INDEX) {
            move = selectNextMove(node, checkedMovesCounter, false, movesList, movesListMaxIndex);
            while (move != null
                    && !outsideStop) {
                ++checkedMovesCounter;
                prevWhiteShortCg    = node.whiteShortCg;
                prevWhiteLongCg     = node.whiteLongCg;
                prevBlackShortCg    = node.blackShortCg;
                prevBlackLongCg     = node.blackLongCg;
                prevEnPassantSquare = node.enPassantSquare;
                prevHalfmovesClock  = node.halfmovesClock;
                prevGameState       = node.gameState;
                node.doMove(move);
                if (node.gameState != States.NOT_LEGAL) {
                    if (move.targetPiece != null
                            || move.promotionPiece != null
                            || move.function == Functions.SHORT_CG
                            || move.function == Functions.LONG_CG
                            || (qChecksSearch
                                    && node.gameState == States.CHECK)) {
                        nextScore = quiescenceSearchWithCheck(node, depth - 1, -beta, -alpha);
                        score = (nextScore == null) ? null : -nextScore;
                    }
                }
                node.undoMove(move, prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg,
                        prevEnPassantSquare, prevHalfmovesClock, prevGameState);
                if (game.timeControlSet
                        && (System.currentTimeMillis() - searchStartMillis) >= searchMaxLengthMillis) {
                    return TIME_OUT_SEARCH_VALUE;
                }
                if (score != null
                        && score > alpha) {
                    pvMap.put(node.nodeHashCode, move);
                    alpha = score;
                    if (alphaBetaPruning
                            && score >= beta) {
                        if (transpositionsMap
                                && (tRec == null || tRec.depth < depth)) {
                            tMap.put(node.nodeHashCode,
                                    new TRec((byte) depth, Accuracies.LOWER_BOUND, score.shortValue()));
                        }
                        return beta;
                    }
                    if (transpositionsMap
                            && (tRec == null || tRec.depth < depth)) {
                        tMap.put(node.nodeHashCode,
                                new TRec((byte) depth, Accuracies.UPPER_BOUND, score.shortValue()));
                    }
                }
                move = selectNextMove(node, checkedMovesCounter, false, movesList, movesListMaxIndex);

            }
        }

        // QUS-TT: non registra mai un EXACT_VALUE

        return alpha;
    }

    /*
     * move selection
     */

    private Move selectNextMove(final Node node, final int checkedMovesCounter, final boolean pvMoveSearch,
            final Move[] movesList, final int movesListMaxIndex) {

        Move move, bestMove = null;
        int maxValue;

        if (principalVarSearch
                && pvMoveSearch) {
            bestMove = selectPvMove(node, movesList, movesListMaxIndex);
            if (bestMove != null) {
                return bestMove;
            }
        }

        if (checkedMovesCounter < sortedMovesNumber) {
            maxValue = Integer.MIN_VALUE;
            for (int i = 0; i <= movesListMaxIndex; ++i) {
                move = movesList[i];
                if (move == null) {
                    try {
                        System.out.println("move == null for node:");
                        System.out.println(node.toStringDebug());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (move.orderValue != null
                            && move.orderValue > maxValue) {
                        bestMove = move;
                        maxValue = bestMove.orderValue;
                    }
                }
            }
        } else {
            for (int i = 0; i <= movesListMaxIndex; ++i) {
                move = movesList[i];
                if (move.orderValue != null) {
                    bestMove = move;
                    bestMove.orderValue = null;
                    return bestMove;
                }
            }
        }

        if (bestMove != null) {
            bestMove.orderValue = null;
        }

        return bestMove;
    }

    private Move selectPvMove(final Node node, final Move[] movesList, final int movesListMaxIndex) {

        Move pvMove;

        pvMove = pvMap.get(node.nodeHashCode);

        if (pvMove != null) {
            for (int i = 0; i <= movesListMaxIndex; ++i) {
                if (movesList[i].orderValue != null
                        && movesList[i].equals(pvMove)) {
                    movesList[i].orderValue = null;
                    return movesList[i];
                }
            }
        }

        return null;
    }

    /*
     * moves reading
     */

    public MoveRecord getMoveRecord()
            throws Exception {

        final Long searchTimeMillis = System.currentTimeMillis() - searchStartMillis;

        LinkedList<Move> movesLinkedList = new LinkedList<>();

        movesLinkedList.add(null); // root node

        Node node = new Node(rootNode);
        MOVES_ENGINE.computePlayerMovesBb(node);

        for (int i = 0; i <= MOVES_ENGINE.movesList.length - 1
                && i <= MOVES_ENGINE.sortedMovesNumber; ++i) {
            movesLinkedList.add(selectNextMove(node, i, /*pvMoveSearch*/true,
                    MOVES_ENGINE.movesList, MOVES_ENGINE.movesListMaxIndex));
        }

        int tMapSize = 0;
        if (transpositionsMap) {
            tMapSize = tMap.size();
        }

        return new MoveRecord(currentSearchDepth, searchTimeMillis, rootNodeValue, visitedNodesCounter,
                movesLinkedList, tMapSize);
    }

    /*
     * pv reading
     */

    public PvRecord getPvRecord()
            throws Exception {

        final Long searchTimeMillis = System.currentTimeMillis() - searchStartMillis;

        LinkedList<Move> recordPvMovesList = new LinkedList<>();

        recordPvMovesList.add(null); // rootNode
        recordPvMovesList.addAll(readPv());

        int tMapSize = 0;
        if (transpositionsMap) {
            tMapSize = tMap.size();
        }

        return new PvRecord(currentSearchDepth, searchTimeMillis, rootNodeValue, visitedNodesCounter,
                recordPvMovesList, pvMap.size(), tMapSize);
    }

    public LinkedList<Move> readPv()
            throws Exception {

        final LinkedList<Move> pvMovesList = new LinkedList<>();

        Node node = rootNode;
        Move move;

        // non si può leggere fino a che si trova una mossa, perché andrebbe in loop
        // nel caso in cui nella pv ci fosse una ripetizione di mosse
        for (int i = 0; i <= currentSearchDepth; ++i) {
            move = pvMap.get(node.nodeHashCode);
            if (move != null) {
                pvMovesList.add(move);
                node = new Node(node, move);
            }
        }

        return pvMovesList;
    }

    public LinkedList<Move> readPvMoves()
            throws Exception {

        final LinkedList<Move> pvMovesList = new LinkedList<>();

        Node node = rootNode;
        Move move;

        boolean nullMove = false;
        boolean repeatedMove = false;

        while (!nullMove
                && !repeatedMove) {
            move = pvMap.get(node.nodeHashCode);
            if (move == null) {
                nullMove = true;
            } else {
                if (pvMovesList.contains(move)) {
                    repeatedMove = true;
                } else {
                    pvMovesList.add(move);
                    node = new Node(node, move);
                }
            }
        }

        return pvMovesList;
    }

    /********************************************************************************************************************
     *** evaluation
     ********************************************************************************************************************/

    /**
     * Per la stima del punteggio di un nodo.
     * Il punteggio è inteso dal punto di vista del colore al tratto.
     *
     * @param newEvalNode Il nodo da valutare.
     * @return La valutazione del nodo (int).
     * @throws Exception Nel caso per stato del nodo non gestito.
     */
    public int evaluation(final Node newEvalNode)
            throws Exception {

        evalNode = newEvalNode;

        int nodeValue;

        switch (evalNode.gameState) {
            case States.ONGOING:
            case States.CHECK:
                evaluatePosition(evalNode);
                nodeValue = taperedScore;
                break;
            case States.CHECKMATE:
                // chi ha la mossa subisce il matto
                nodeValue = START_ALPHA;
                break;
            case States.STALE_MATE:
            case States.FIFTY_MOVES:
            case States.THREEFOLD_REP:
            case States.IMPOSSIBLE_MATE:
            case States.TIME_OUT:
            case States.DRAW_AGREEMENT:
            case States.GAME_INTERRUPTED:
                nodeValue = 0;
                break;
            default:
                throw new Exception("evalNode.gameState=" + evalNode.gameState);
        }

        return nodeValue;
    }

    private void evaluatePosition(final Node newNode)
            throws Exception {

        evalNode = newNode;

        // quando arriva qui, le quantità non sono state calcolate per le mosse
        computeBoardQuantities(evalNode);

        computeAllPiecesMidgScore();

        sideMidg = 0;
        sideEndg = 0;
        evaluateSidePosition(Colors.WHITE);
        whiteMidg = sideMidg;
        whiteEndg = sideEndg;

        sideMidg = 0;
        sideEndg = 0;
        evaluateSidePosition(Colors.BLACK);
        blackMidg = sideMidg;
        blackEndg = sideEndg;

        switch (evalNode.playerColor) {
            case Colors.WHITE:
                midg = whiteMidg - blackMidg;
                endg = whiteEndg - blackEndg;
                break;
            case Colors.BLACK:
                midg = blackMidg - whiteMidg;
                endg = blackEndg - whiteEndg;
                break;
            default:
                throw new Exception("evalNode.playerColor=" + evalNode.playerColor);
        }

        taperedScore = (int) (midg - (( endg - midg + ON_REALS ) / matAllPiecesMidgScore )
                * ( allPiecesMidgScore - matAllPiecesMidgScore ));

    }

    private void computeAllPiecesMidgScore() {

        allPiecesMidgScore = matPawnMidgScore   * (evalNode.whitePawnsCounter   + evalNode.blackPawnsCounter)
                + matKnightMidgScore * (evalNode.whiteKnightsCounter + evalNode.blackKnightsCounter)
                + matBishopMidgScore * (evalNode.whiteBishopsCounter + evalNode.blackBishopsCounter)
                + matRookMidgScore   * (evalNode.whiteRooksCounter   + evalNode.blackRooksCounter)
                + matQueenMidgScore  * (evalNode.whiteQueensCounter  + evalNode.blackQueensCounter);

        if (evalNode.whiteShortCg) {
            allPiecesMidgScore += matShortCgMidgScore;
        }

        if (evalNode.whiteLongCg) {
            allPiecesMidgScore += matLongCgMidgScore;
        }

        if (evalNode.blackShortCg) {
            allPiecesMidgScore += matShortCgMidgScore;
        }

        if (evalNode.blackLongCg) {
            allPiecesMidgScore += matLongCgMidgScore;
        }

    }

    private void evaluateSidePosition(final byte sideColor)
            throws Exception {

        initCounters();

        computeSideControlled(evalNode, (byte) -sideColor); // needed in arrangement
        computeSideQuantities(evalNode, sideColor);

        // ** score for pieces
        // ** score for available castlings
        // -- malus for 0 pawns
        // -- malus for double knigths
        // ++ bonus for double bishops
        // -- malus for double rooks
        evaluateSideMaterial(sideColor);

        // ** score for pawn islands
        // ** score for dispersion
        // ** score for distorsion
        // -- malus for isolani
        // << records open/semi-open lines
        evaluateSidePawnStructure(sideColor);

        // ** score for function
        // ** score for targetPiece
        // << records king/rings attacks counters
        // << records center/cRing controls counters
        // << records opponent controlled squares
        evaluateSideMobility(sideColor);

        //    all pieces
        // **    score for pieces locations
        // --    malus if on controlled squares
        //    rooks/queens
        // ++    bonus if on open files
        //    pawns
        // ++    bonus for passed pawns
        // ++ bonus for center/cRing controls
        // >> needs opponent controlled squares from mobility
        // >> needs center control counters from mobility
        // >> needs open/semi-open lines from pawn structure
        evaluateSideArrangement(sideColor);

        // -- malus for lack of shield pawns
        // >> needs king/rings attacks counters from mobility
        evaluateSideKingSafety(sideColor);

    }

    private void initCounters() {

        kingAttacksCounter   = 0;
        kRing1AttacksCounter = 0;
        kRing2AttacksCounter = 0;

        centreControlsCounter = 0;
        cRingControlsCounter  = 0;

    }

    /******************************************************************************************************************
     *** evaluate material
     ******************************************************************************************************************/

    private void evaluateSideMaterial(final byte sideColor)
            throws Exception {

        evaluateSideMidgMaterial(sideColor);

        evaluateSideEndgMaterial(sideColor);

    }

    private void evaluateSideMidgMaterial(final byte sideColor)
            throws Exception {

        switch (sideColor) {
            case Colors.WHITE:

                sideMidg += matPawnMidgScore   * evalNode.whitePawnsCounter
                        + matKnightMidgScore * evalNode.whiteKnightsCounter
                        + matBishopMidgScore * evalNode.whiteBishopsCounter
                        + matRookMidgScore   * evalNode.whiteRooksCounter
                        + matQueenMidgScore  * evalNode.whiteQueensCounter;

                if (evalNode.whiteShortCg) {
                    sideMidg += matShortCgMidgScore;
                }
                if (evalNode.whiteLongCg) {
                    sideMidg += matLongCgMidgScore;
                }

                if (evalNode.whiteKnightsCounter == 1) {
                    sideMidg += matLonelyKnightMidgScore;
                }
                if (evalNode.whiteBishopsCounter == 1) {
                    sideMidg += matLonelyBishopMidgScore * evalNode.whiteBishopsCounter;
                }
                if (evalNode.whiteRooksCounter == 1) {
                    sideMidg += matLonelyRookMidgScore * evalNode.whiteRooksCounter;
                }

                if (evalNode.whiteBishopsCounter >= 2
                        && (evalNode.whiteWFBishopsCounter == 0
                        || evalNode.whiteBFBishopsCounter == 0)) {
                    sideMidg += matSameFBishopsMidgScore;
                }

                if (evalNode.whitePawnsCounter == 0) {
                    sideMidg += matNoPawnsMidgScore;
                }

                break;
            case Colors.BLACK:

                sideMidg += matPawnMidgScore   * evalNode.blackPawnsCounter
                        + matKnightMidgScore * evalNode.blackKnightsCounter
                        + matBishopMidgScore * evalNode.blackBishopsCounter
                        + matRookMidgScore   * evalNode.blackRooksCounter
                        + matQueenMidgScore  * evalNode.blackQueensCounter;

                if (evalNode.blackShortCg) {
                    sideMidg += matShortCgMidgScore;
                }
                if (evalNode.blackLongCg) {
                    sideMidg += matLongCgMidgScore;
                }

                if (evalNode.blackKnightsCounter == 1) {
                    sideMidg += matLonelyKnightMidgScore;
                }
                if (evalNode.blackBishopsCounter == 1) {
                    sideMidg += matLonelyBishopMidgScore * evalNode.blackBishopsCounter;
                }
                if (evalNode.blackRooksCounter == 1) {
                    sideMidg += matLonelyRookMidgScore * evalNode.blackRooksCounter;
                }

                if (evalNode.blackBishopsCounter >= 2
                        && (evalNode.blackWFBishopsCounter == 0
                        || evalNode.blackBFBishopsCounter == 0)) {
                    sideMidg += matSameFBishopsMidgScore;
                }

                if (evalNode.blackPawnsCounter == 0) {
                    sideMidg += matNoPawnsMidgScore;
                }

                break;
            default:
                throw new Exception("sideColor=" + sideColor);
        }

    }

    private void evaluateSideEndgMaterial(final byte sideColor)
            throws Exception {

        switch (sideColor) {
            case Colors.WHITE:

                sideEndg += matPawnEndgScore   * evalNode.whitePawnsCounter
                        + matKnightEndgScore * evalNode.whiteKnightsCounter
                        + matBishopEndgScore * evalNode.whiteBishopsCounter
                        + matRookEndgScore   * evalNode.whiteRooksCounter
                        + matQueenEndgScore  * evalNode.whiteQueensCounter;

                if (evalNode.whiteKnightsCounter == 1) {
                    sideEndg += matLonelyKnightEndgScore;
                }
                if (evalNode.whiteBishopsCounter == 1) {
                    sideEndg += matLonelyBishopEndgScore;
                }
                if (evalNode.whiteRooksCounter == 1) {
                    sideEndg += matLonelyRookEndgScore;
                }

                if (evalNode.whiteBishopsCounter >= 2
                        && (evalNode.whiteWFBishopsCounter == 0
                        || evalNode.whiteBFBishopsCounter == 0)) {
                    sideEndg += matSameFBishopsEndgScore;
                }

                if (evalNode.whitePawnsCounter == 0) {
                    sideEndg += matNoPawnsEndgScore;
                }

                break;
            case Colors.BLACK:

                sideEndg += matPawnEndgScore   * evalNode.blackPawnsCounter
                        + matKnightEndgScore * evalNode.blackKnightsCounter
                        + matBishopEndgScore * evalNode.blackBishopsCounter
                        + matRookEndgScore   * evalNode.blackRooksCounter
                        + matQueenEndgScore  * evalNode.blackQueensCounter;

                if (evalNode.blackKnightsCounter == 1) {
                    sideEndg += matLonelyKnightEndgScore;
                }
                if (evalNode.blackBishopsCounter == 1) {
                    sideEndg += matLonelyBishopEndgScore;
                }
                if (evalNode.blackRooksCounter == 1) {
                    sideEndg += matLonelyRookEndgScore;
                }

                if (evalNode.blackBishopsCounter >= 2
                        && (evalNode.blackWFBishopsCounter == 0
                        || evalNode.blackBFBishopsCounter == 0)) {
                    sideEndg += matSameFBishopsEndgScore;
                }

                if (evalNode.blackPawnsCounter == 0) {
                    sideEndg += matNoPawnsEndgScore;
                }

                break;
            default:
                throw new Exception("sideColor=" + sideColor);
        }

    }

    /********************************************************************************************************************
     *** evaluate pawn structure
     ********************************************************************************************************************/

    private void evaluateSidePawnStructure(final byte sideColor)
            throws Exception {

        /*
         * pawn islands file set -- index of a lookup table -> int
         */

        switch (sideColor) {
            case Colors.WHITE: whitePawnsFs = (int) (evalNode.whitePawnsFilesBb >>> Squares.SQUARE_A1); break;
            case Colors.BLACK: blackPawnsFs = (int) (evalNode.blackPawnsFilesBb >>> Squares.SQUARE_A1); break;
            default:
                throw new Exception("sideColor=" + sideColor);
        }

        /*
         * isolani pawns
         */

        switch (sideColor) {
            case Colors.WHITE:
                sideMidg += PST_ISOLANI_P_COUNT_LT[whitePawnsFs] * pstIsolaniPawnsMidgScore;
                sideEndg += PST_ISOLANI_P_COUNT_LT[whitePawnsFs] * pstIsolaniPawnsEndgScore;
                break;
            case Colors.BLACK:
                sideMidg += PST_ISOLANI_P_COUNT_LT[blackPawnsFs] * pstIsolaniPawnsMidgScore;
                sideEndg += PST_ISOLANI_P_COUNT_LT[blackPawnsFs] * pstIsolaniPawnsEndgScore;
                break;
            default:
                throw new Exception("sideColor=" + sideColor);
        }

        /*
         * pawns dispersion
         */

        switch (sideColor) {
            case Colors.WHITE:
                whitePawnIslandsCounter = PST_P_ISLANDS_COUNT_LT[whitePawnsFs];
                sideMidg += - Math.abs(pstDispersionCoefficient
                        * whitePawnIslandsCounter * whitePawnIslandsCounter
                        - evalNode.whitePawnsCounter);
                break;
            case Colors.BLACK:
                blackPawnIslandsCounter = PST_P_ISLANDS_COUNT_LT[blackPawnsFs];
                sideMidg += - Math.abs(pstDispersionCoefficient
                        * blackPawnIslandsCounter * blackPawnIslandsCounter
                        - evalNode.blackPawnsCounter);
                break;
            default:
                throw new Exception("sideColor=" + sideColor);
        }

        /*
         * pawns distorsion
         */

        long sidePawnsRearFillBb, sidePawnsFillDeltaBb;

        long nextSidePawnFillDeltaBb;

        int fillDeltaPopCounter = 0;

        switch (sideColor) {
            case Colors.WHITE:
                sidePawnsRearFillBb = evalNode.whitePawnsBb;
                sidePawnsRearFillBb |= (sidePawnsRearFillBb <<   8);
                sidePawnsRearFillBb |= (sidePawnsRearFillBb <<  16);
                sidePawnsRearFillBb |= (sidePawnsRearFillBb <<  32);
                break;
            case Colors.BLACK:
                sidePawnsRearFillBb = evalNode.blackPawnsBb;
                sidePawnsRearFillBb |= (sidePawnsRearFillBb >>>  8);
                sidePawnsRearFillBb |= (sidePawnsRearFillBb >>> 16);
                sidePawnsRearFillBb |= (sidePawnsRearFillBb >>> 32);
                break;
            default:
                throw new Exception("sideColor=" + sideColor);
        }

        sidePawnsFillDeltaBb = (sidePawnsRearFillBb ^ (sidePawnsRearFillBb << 1)) & ~BitBoards.FILE_A;

        nextSidePawnFillDeltaBb = sidePawnsFillDeltaBb & -sidePawnsFillDeltaBb;
        while (nextSidePawnFillDeltaBb != BitBoards.EMPTY) {
            ++fillDeltaPopCounter;
            sidePawnsFillDeltaBb &= ~nextSidePawnFillDeltaBb;
            nextSidePawnFillDeltaBb = sidePawnsFillDeltaBb & -sidePawnsFillDeltaBb;
        }

        sideMidg += - pstDistorsionCoefficient * fillDeltaPopCounter;

    }

    /********************************************************************************************************************
     *** evaluate mobility
     ********************************************************************************************************************/

    private void evaluateSideMobility(final byte sideColor)
            throws Exception {

        final long whiteTargetsBb, blackTargetsBb;

//        final long enPassantBb;
//        if (evalNode.enPassantSquare == null) {
//            enPassantBb = BitBoards.EMPTY;
//        } else {
//            enPassantBb = (BitBoards.ONE << evalNode.enPassantSquare);
//        }

        switch (sideColor) {
            case Colors.WHITE:
                whiteTargetsBb = evalNode.allPiecesBb;
//                if (evalNode.whitePawnsBb   != BitBoards.EMPTY) { evaluateWhitePawnsMovesBb(whiteTargetsBb | enPassantBb); }
                if (evalNode.whiteKnightsBb != BitBoards.EMPTY) { evaluateKnightsMovesBb( Colors.WHITE, evalNode.whiteKnightsBb, whiteTargetsBb); }
                if (evalNode.whiteBishopsBb != BitBoards.EMPTY) { evaluateBishopsMovesBb( Colors.WHITE, evalNode.whiteBishopsBb, whiteTargetsBb); }
                if (evalNode.whiteRooksBb   != BitBoards.EMPTY) { evaluateRooksMovesBb(   Colors.WHITE, evalNode.whiteRooksBb,   whiteTargetsBb);
                    evaluateWhiteShortCg();
                    evaluateWhiteLongCg();
                }
                if (evalNode.whiteQueensBb  != BitBoards.EMPTY) { evaluateQueensMovesBb(  Colors.WHITE, evalNode.whiteQueensBb,  whiteTargetsBb); }
//                if (evalNode.whiteKingBb    != BitBoards.EMPTY) { evaluateKingMovesBb(    Colors.WHITE, evalNode.whiteKingBb,    whiteTargetsBb); } // no
                break;
            case Colors.BLACK:
                blackTargetsBb = evalNode.allPiecesBb;
//                if (evalNode.blackPawnsBb   != BitBoards.EMPTY) { evaluateBlackPawnsMovesBb(blackTargetsBb | enPassantBb); }
                if (evalNode.blackKnightsBb != BitBoards.EMPTY) { evaluateKnightsMovesBb( Colors.BLACK, evalNode.blackKnightsBb, blackTargetsBb); }
                if (evalNode.blackBishopsBb != BitBoards.EMPTY) { evaluateBishopsMovesBb( Colors.BLACK, evalNode.blackBishopsBb, blackTargetsBb); }
                if (evalNode.blackRooksBb   != BitBoards.EMPTY) { evaluateRooksMovesBb(   Colors.BLACK, evalNode.blackRooksBb,   blackTargetsBb);
                    evaluateBlackShortCg();
                    evaluateBlackLongCg();
                }
                if (evalNode.blackQueensBb  != BitBoards.EMPTY) { evaluateQueensMovesBb(  Colors.BLACK, evalNode.blackQueensBb,  blackTargetsBb); }
//                if (evalNode.blackKingBb    != BitBoards.EMPTY) { evaluateKingMovesBb(    Colors.BLACK, evalNode.blackKingBb,    blackTargetsBb); } // no
                break;
            default:
                throw new Exception("sideColor=" + sideColor);
        }

    }

//    private void evaluateWhitePawnsMovesBb(final long whiteTargetsBb)
//            throws Exception {
//
//        final int sidePawn = Pieces.WHITE_PAWN;
//
//        long toSquaresBb, nextToSquareBb;
//        int fromSquare, toSquare;
//
//        /* N 1 */
//        toSquaresBb = (evalNode.whitePawnsBb >>> BitBoards.VERTICAL_STEP) & ~evalNode.allPiecesBb;
//        nextToSquareBb = toSquaresBb & -toSquaresBb;
//        while (nextToSquareBb != BitBoards.EMPTY) {
//            toSquare = Long.numberOfTrailingZeros(nextToSquareBb);
//            fromSquare = toSquare + BitBoards.VERTICAL_STEP;
//            addPawnStepScore(Colors.WHITE, sidePawn, fromSquare, toSquare, nextToSquareBb);
//            toSquaresBb &= ~nextToSquareBb;
//            nextToSquareBb = toSquaresBb & -toSquaresBb;
//        }
//
//        /* N 2 */
//        toSquaresBb = (evalNode.whitePawnsBb >>> BitBoards.VERTICAL_DOUBLE_STEP) & ~evalNode.allPiecesBb
//                & (~evalNode.allPiecesBb >>> BitBoards.VERTICAL_STEP) & BitBoards.RANK_4;
//        nextToSquareBb = toSquaresBb & -toSquaresBb;
//        while (nextToSquareBb != BitBoards.EMPTY) {
//            toSquare = Long.numberOfTrailingZeros(nextToSquareBb);
//            fromSquare = toSquare + BitBoards.VERTICAL_DOUBLE_STEP;
//            addPawnDoubleStepScore(Colors.WHITE, sidePawn, fromSquare, toSquare, nextToSquareBb);
//            toSquaresBb &= ~nextToSquareBb;
//            nextToSquareBb = toSquaresBb & -toSquaresBb;
//        }
//
//        /* NW + NW EP */
//        toSquaresBb = (evalNode.whitePawnsBb >>> BitBoards.ANTIGONAL_STEP) & whiteTargetsBb & ~BitBoards.FILE_H;
//        nextToSquareBb = toSquaresBb & -toSquaresBb;
//        while (nextToSquareBb != BitBoards.EMPTY) {
//            toSquare = Long.numberOfTrailingZeros(nextToSquareBb);
//            fromSquare = toSquare + BitBoards.ANTIGONAL_STEP;
//            addPawnCapturesScore(Colors.WHITE, sidePawn, fromSquare, toSquare, nextToSquareBb);
//            toSquaresBb &= ~nextToSquareBb;
//            nextToSquareBb = toSquaresBb & -toSquaresBb;
//        }
//
//        /* NE + NE EP */
//        toSquaresBb = (evalNode.whitePawnsBb >>> BitBoards.DIAGONAL_STEP) & whiteTargetsBb & ~BitBoards.FILE_A;
//        nextToSquareBb = toSquaresBb & -toSquaresBb;
//        while (nextToSquareBb != BitBoards.EMPTY) {
//            toSquare = Long.numberOfTrailingZeros(nextToSquareBb);
//            fromSquare = toSquare + BitBoards.DIAGONAL_STEP;
//            addPawnCapturesScore(Colors.WHITE, sidePawn, fromSquare, toSquare, nextToSquareBb);
//            toSquaresBb &= ~nextToSquareBb;
//            nextToSquareBb = toSquaresBb & -toSquaresBb;
//        }
//
//    }

//    private void evaluateBlackPawnsMovesBb(final long blackTargetsBb)
//            throws Exception {
//
//        final int sidePawn = Pieces.BLACK_PAWN;
//
//        long toSquaresBb, nextToSquareBb;
//        int fromSquare, toSquare;
//
//        /* S 1 */
//        toSquaresBb = (evalNode.blackPawnsBb << BitBoards.VERTICAL_STEP) & ~evalNode.allPiecesBb;
//        nextToSquareBb = toSquaresBb & -toSquaresBb;
//        while (nextToSquareBb != BitBoards.EMPTY) {
//            toSquare = Long.numberOfTrailingZeros(nextToSquareBb);
//            fromSquare = toSquare - BitBoards.VERTICAL_STEP;
//            addPawnStepScore(Colors.BLACK, sidePawn, fromSquare, toSquare, nextToSquareBb);
//            toSquaresBb &= ~nextToSquareBb;
//            nextToSquareBb = toSquaresBb & -toSquaresBb;
//        }
//
//        /* S 2 */
//        toSquaresBb = (evalNode.blackPawnsBb << BitBoards.VERTICAL_DOUBLE_STEP) & ~evalNode.allPiecesBb
//                & (~evalNode.allPiecesBb << BitBoards.VERTICAL_STEP) & BitBoards.RANK_5;
//        nextToSquareBb = toSquaresBb & -toSquaresBb;
//        while (nextToSquareBb != BitBoards.EMPTY) {
//            toSquare = Long.numberOfTrailingZeros(nextToSquareBb);
//            fromSquare = toSquare - BitBoards.VERTICAL_DOUBLE_STEP;
//            addPawnDoubleStepScore(Colors.BLACK, sidePawn, fromSquare, toSquare, nextToSquareBb);
//            toSquaresBb &= ~nextToSquareBb;
//            nextToSquareBb = toSquaresBb & -toSquaresBb;
//        }
//
//        /* SE + SE EP */
//        toSquaresBb = (evalNode.blackPawnsBb << BitBoards.ANTIGONAL_STEP) & blackTargetsBb & ~BitBoards.FILE_A;
//        nextToSquareBb = toSquaresBb & -toSquaresBb;
//        while (nextToSquareBb != BitBoards.EMPTY) {
//            toSquare = Long.numberOfTrailingZeros(nextToSquareBb);
//            fromSquare = toSquare - BitBoards.ANTIGONAL_STEP;
//            addPawnCapturesScore(Colors.BLACK, sidePawn, fromSquare, toSquare, nextToSquareBb);
//            toSquaresBb &= ~nextToSquareBb;
//            nextToSquareBb = toSquaresBb & -toSquaresBb;
//        }
//
//        /* SW + SW EP */
//        toSquaresBb = (evalNode.blackPawnsBb << BitBoards.DIAGONAL_STEP) & blackTargetsBb & ~BitBoards.FILE_H;
//        nextToSquareBb = toSquaresBb & -toSquaresBb;
//        while (nextToSquareBb != BitBoards.EMPTY) {
//            toSquare = Long.numberOfTrailingZeros(nextToSquareBb);
//            fromSquare = toSquare - BitBoards.DIAGONAL_STEP;
//            addPawnCapturesScore(Colors.BLACK, sidePawn, fromSquare, toSquare, nextToSquareBb);
//            toSquaresBb &= ~nextToSquareBb;
//            nextToSquareBb = toSquaresBb & -toSquaresBb;
//        }
//
//    }

    private void evaluateKnightsMovesBb(final byte sideColor, final long initKnightsBb, final long sideTargetsBb)
            throws Exception {

        final int sideKnight = sideColor * Pieces.ROLE_KNIGHT;

        long knightsBb = initKnightsBb;
        long nextPieceBb = knightsBb & -knightsBb;
        long toSquaresBb, nextToSquareBb;
        byte nextPieceSquare, toSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
            toSquaresBb = BitBoards.KNIGHT_MOVES_LT[nextPieceSquare] & sideTargetsBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                addPieceMovesScore(sideColor, sideKnight, nextPieceSquare, toSquare, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
            knightsBb &= ~nextPieceBb;
            nextPieceBb = knightsBb & -knightsBb;
        }

    }

    private void evaluateBishopsMovesBb(final byte sideColor, final long initBishopsBb, final long sideTargetsBb)
            throws Exception {

        final int sideBishop = sideColor * Pieces.ROLE_BISHOP;

        long bishopsBb = initBishopsBb;
        long nextPieceBb = bishopsBb & -bishopsBb;
        long toSquaresBb, nextToSquareBb;
        byte nextPieceSquare, toSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
            toSquaresBb = BitBoards.diagonalAntigonalMoves(nextPieceSquare, nextPieceBb, evalNode.allPiecesBb) & sideTargetsBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                addPieceMovesScore(sideColor, sideBishop, nextPieceSquare, toSquare, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
            bishopsBb &= ~nextPieceBb;
            nextPieceBb = bishopsBb & -bishopsBb;
        }

    }

    private void evaluateRooksMovesBb(final byte sideColor, final long initRooksBb, final long sideTargetsBb)
            throws Exception {

        final int sideRook = sideColor * Pieces.ROLE_ROOK;

        long rooksBb = initRooksBb;
        long nextPieceBb = rooksBb & -rooksBb;
        long toSquaresBb, nextToSquareBb;
        byte nextPieceSquare, toSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
            toSquaresBb = BitBoards.horizontalVerticalMoves(nextPieceSquare, nextPieceBb, evalNode.allPiecesBb) & sideTargetsBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                addPieceMovesScore(sideColor, sideRook, nextPieceSquare, toSquare, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
            rooksBb &= ~nextPieceBb;
            nextPieceBb = rooksBb & -rooksBb;
        }

    }

    private void evaluateQueensMovesBb(final byte sideColor, final long initQueensBb, final long sideTargetsBb)
            throws Exception {

        final int sideQueen = sideColor * Pieces.ROLE_QUEEN;

        long queensBb = initQueensBb;
        long nextPieceBb = queensBb & -queensBb;
        long toSquaresBb, nextToSquareBb;
        byte nextPieceSquare, toSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
            toSquaresBb = (BitBoards.horizontalVerticalMoves(nextPieceSquare, nextPieceBb, evalNode.allPiecesBb)
                    | BitBoards.diagonalAntigonalMoves(nextPieceSquare, nextPieceBb, evalNode.allPiecesBb)) & sideTargetsBb;
            nextToSquareBb = toSquaresBb & -toSquaresBb;
            while (nextToSquareBb != BitBoards.EMPTY) {
                toSquare = (byte) Long.numberOfTrailingZeros(nextToSquareBb);
                addPieceMovesScore(sideColor, sideQueen, nextPieceSquare, toSquare, nextToSquareBb);
                toSquaresBb &= ~nextToSquareBb;
                nextToSquareBb = toSquaresBb & -toSquaresBb;
            }
            queensBb &= ~nextPieceBb;
            nextPieceBb = queensBb & -queensBb;
        }

    }

//    private void evaluateKingMovesBb(final byte sideColor, final long kingBb, final long sideTargetsBb) // no
//            throws Exception {
//
//        final int sideKing = sideColor * Pieces.ROLE_KING;
//
//        long toSquaresBb, nextToSquareBb;
//        int pieceSquare, toSquare;
//
//        pieceSquare = Long.numberOfTrailingZeros(kingBb);
//        toSquaresBb = BitBoards.KING_MOVES_LT[pieceSquare] & sideTargetsBb;
//        nextToSquareBb = toSquaresBb & -toSquaresBb;
//        while (nextToSquareBb != BitBoards.EMPTY) {
//            if (!evalNode.areSquaresCheckedBb2(nextToSquareBb, sideColor)) {
//                toSquare = Long.numberOfTrailingZeros(nextToSquareBb);
//                addPieceMovesScore(sideColor, sideKing, pieceSquare, toSquare, nextToSquareBb);
//            }
//            toSquaresBb &= ~nextToSquareBb;
//            nextToSquareBb = toSquaresBb & -toSquaresBb;
//        }
//
//    }

    private void evaluateWhiteShortCg() {

        if (evalNode.whiteShortCg
                && (BitBoards.W_S_CG_PATH & ~evalNode.allPiecesBb) == BitBoards.W_S_CG_PATH
                && (BitBoards.W_S_CG_KING_PATH & evalNode.blackControlledBb) == BitBoards.EMPTY) {
            sideMidg += addShortCgScore();
        }

    }

    private void evaluateWhiteLongCg() {

        if (evalNode.whiteLongCg
                && (BitBoards.W_L_CG_PATH & ~evalNode.allPiecesBb) == BitBoards.W_L_CG_PATH
                && (BitBoards.W_L_CG_KING_PATH & evalNode.blackControlledBb) == BitBoards.EMPTY) {
            sideMidg += addLongCgScore();
        }

    }

    private void evaluateBlackShortCg() {

        if (evalNode.blackShortCg
                && (BitBoards.B_S_CG_PATH & ~evalNode.allPiecesBb) == BitBoards.B_S_CG_PATH
                && (BitBoards.B_S_CG_KING_PATH & evalNode.whiteControlledBb) == BitBoards.EMPTY) {
            sideMidg += addShortCgScore();
        }

    }

    private void evaluateBlackLongCg() {

        if (evalNode.blackLongCg
                && (BitBoards.B_L_CG_PATH & ~evalNode.allPiecesBb) == BitBoards.B_L_CG_PATH
                && (BitBoards.B_L_CG_KING_PATH & evalNode.whiteControlledBb) == BitBoards.EMPTY) {
            sideMidg += addLongCgScore();
        }

    }

    /********************************************************************************************************************
     *** evaluate moves
     ********************************************************************************************************************/

    private void addPawnStepScore(final byte sideColor, final int piece, final int fromSquare, final int toSquare,
                                  final long toSquareBb) {

        sideMidg += mobPawnStepScore;
    }

    private void addPawnDoubleStepScore(final byte sideColor, final int piece, final int fromSquare, final int toSquare,
                                        final long toSquareBb) {

        sideMidg += mobPawnDoubleStepScore;
    }

    private void addPawnCapturesScore(final byte sideColor, final int piece, final int fromSquare, final int toSquare,
                                      final long toSquareBb)
            throws Exception {

        final Byte targetPiece;

        if (evalNode.enPassantSquare != null
                && toSquare == evalNode.enPassantSquare) {
            targetPiece = (byte) (-sideColor * Pieces.ROLE_PAWN);
        } else {
            targetPiece = evalNode.squarePieceMap[toSquare];
        }

        addCenterControlCounters(toSquareBb);
        addKRingsAttackCounters(sideColor, toSquareBb);

        sideMidg += mobPawnStepScore;

        if (Math.signum(targetPiece) == sideColor) {
            sideMidg += (int) ( (pieceMidgValue(targetPiece) + ON_REALS)
                    / mobTargetAttackDenomntr );
        } else {
            sideMidg += (int) ( (pieceMidgValue(targetPiece) + ON_REALS)
                    / mobTargetProtectnDenomntr );
        }

    }

    private void addPieceMovesScore(final byte sideColor, final int piece, final int fromSquare, final int toSquare,
                                    final long toSquareBb)
            throws Exception {

        final Byte targetPiece = evalNode.squarePieceMap[toSquare];

        addCenterControlCounters(toSquareBb);
        addKRingsAttackCounters(sideColor, toSquareBb);

        sideMidg += (int) ((mobPieceNumerator + ON_REALS)
                / pieceMidgValue(piece));

        if (targetPiece != null) {
            if (Math.signum(targetPiece) == sideColor) {
                sideMidg += (int) ( (pieceMidgValue(targetPiece) + ON_REALS)
                        / mobTargetAttackDenomntr );
            } else {
                sideMidg += (int) ( (pieceMidgValue(targetPiece) + ON_REALS)
                        / mobTargetProtectnDenomntr);
            }
        }

        // promotionPiece già gestito nelle tabelle posizione

    }

    private int addShortCgScore() {

        return mobShortCgScore;
    }

    private int addLongCgScore() {

        return mobLongCgScore;
    }

    private int pieceMidgValue(final int piece)
            throws Exception {

        switch (piece) {
            case Pieces.WHITE_PAWN:
            case Pieces.BLACK_PAWN:   return matPawnMidgScore;
            case Pieces.WHITE_KNIGHT:
            case Pieces.BLACK_KNIGHT: return matKnightMidgScore;
            case Pieces.WHITE_BISHOP:
            case Pieces.BLACK_BISHOP: return matBishopMidgScore;
            case Pieces.WHITE_ROOK:
            case Pieces.BLACK_ROOK:   return matRookMidgScore;
            case Pieces.WHITE_QUEEN:
            case Pieces.BLACK_QUEEN:  return matQueenMidgScore;
            case Pieces.WHITE_KING:
            case Pieces.BLACK_KING:   return matKingMidgScore; // king mobility
            default:
                throw new Exception("piece=" + piece);
        }

    }

    /********************************************************************************************************************
     *** evaluate arrangement
     ********************************************************************************************************************/

    private void evaluateSideArrangement(final byte sideColor)
            throws Exception {

        switch (sideColor) {
            case Colors.WHITE:
                if (evalNode.whitePawnsBb   != BitBoards.EMPTY) { evaluatePawnsArrangement(   Colors.WHITE, evalNode.whitePawnsBb   ); }
                if (evalNode.whiteKnightsBb != BitBoards.EMPTY) { evaluateKnightsArrangement( Colors.WHITE, evalNode.whiteKnightsBb ); }
                if (evalNode.whiteBishopsBb != BitBoards.EMPTY) { evaluateBishopsArrangement( Colors.WHITE, evalNode.whiteBishopsBb ); }
                if (evalNode.whiteRooksBb   != BitBoards.EMPTY) { evaluateRooksArrangement(   Colors.WHITE, evalNode.whiteRooksBb   ); }
                if (evalNode.whiteQueensBb  != BitBoards.EMPTY) { evaluateQueensArrangement(  Colors.WHITE, evalNode.whiteQueensBb  ); }
                if (evalNode.whiteKingBb    != BitBoards.EMPTY) { evaluateKingArrangement(    Colors.WHITE, evalNode.whiteKingBb    ); }
                break;
            case Colors.BLACK:
                if (evalNode.blackPawnsBb   != BitBoards.EMPTY) { evaluatePawnsArrangement(   Colors.BLACK, evalNode.blackPawnsBb   ); }
                if (evalNode.blackKnightsBb != BitBoards.EMPTY) { evaluateKnightsArrangement( Colors.BLACK, evalNode.blackKnightsBb ); }
                if (evalNode.blackBishopsBb != BitBoards.EMPTY) { evaluateBishopsArrangement( Colors.BLACK, evalNode.blackBishopsBb ); }
                if (evalNode.blackRooksBb   != BitBoards.EMPTY) { evaluateRooksArrangement(   Colors.BLACK, evalNode.blackRooksBb   ); }
                if (evalNode.blackQueensBb  != BitBoards.EMPTY) { evaluateQueensArrangement(  Colors.BLACK, evalNode.blackQueensBb  ); }
                if (evalNode.blackKingBb    != BitBoards.EMPTY) { evaluateKingArrangement(    Colors.BLACK, evalNode.blackKingBb    ); }
                break;
            default:
                throw new Exception("sideColor=" + sideColor);
        }

        evaluateCentreCRingControls(sideColor);

    }

    private void evaluateCentreCRingControls(final byte sideColor) {

        sideMidg += arrCentreControlScore * centreControlsCounter
                + arrCRingControlScore * cRingControlsCounter;

    }

    private void evaluatePawnsArrangement(final byte sideColor, final long initPiecesBb)
            throws Exception {

        final int sidePiece = sideColor * Pieces.ROLE_PAWN;

        long piecesBb = initPiecesBb;
        long nextPieceBb = piecesBb & -piecesBb;
        byte nextPieceSquare, passedPawnsCounter;

        passedPawnsCounter = 0;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);

            switch (sideColor) {
                case Colors.WHITE:
                    sideMidg += arrWhitePawnMidgScoresList[nextPieceSquare];
                    sideEndg += arrWhitePawnEndgScoresList[nextPieceSquare];
                    if ((BitBoards.WP_BLOCK_C_LT[nextPieceSquare] & evalNode.blackPawnsBb) == BitBoards.EMPTY) {
                        ++passedPawnsCounter;
                    }
                    break;
                case Colors.BLACK:
                    sideMidg += arrBlackPawnMidgScoresList[nextPieceSquare];
                    sideEndg += arrBlackPawnEndgScoresList[nextPieceSquare];
                    if ((BitBoards.BP_BLOCK_C_LT[nextPieceSquare] & evalNode.whitePawnsBb) == BitBoards.EMPTY) {
                        ++passedPawnsCounter;
                    }
                    break;
                default:
                    throw new Exception("sideColor=" + sideColor);
            }

            addOnCtrlledSquareScore(sideColor, sidePiece, nextPieceBb);

            piecesBb &= ~nextPieceBb;
            nextPieceBb = piecesBb & -piecesBb;
        }

        sideMidg += passedPawnsCounter * pstPassedPawnsMidgScore;
        sideEndg += passedPawnsCounter * pstPassedPawnsEndgScore;

    }

    private void evaluateKnightsArrangement(final byte sideColor, final long initPiecesBb)
            throws Exception {

        final int sidePiece = sideColor * Pieces.ROLE_KNIGHT;

        long piecesBb = initPiecesBb;
        long nextPieceBb = piecesBb & -piecesBb;
        byte nextPieceSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);

            switch (sideColor) {
                case Colors.WHITE: sideMidg += arrWhiteKnightMidgScoresList[nextPieceSquare]; break;
                case Colors.BLACK: sideMidg += arrBlackKnightMidgScoresList[nextPieceSquare]; break;
                default:
                    throw new Exception("sideColor=" + sideColor);
            }

            addOnCtrlledSquareScore(sideColor, sidePiece, nextPieceBb);

            piecesBb &= ~nextPieceBb;
            nextPieceBb = piecesBb & -piecesBb;
        }

    }

    private void evaluateBishopsArrangement(final byte sideColor, final long initPiecesBb)
            throws Exception {

        final int sidePiece = sideColor * Pieces.ROLE_BISHOP;

        long piecesBb = initPiecesBb;
        long nextPieceBb = piecesBb & -piecesBb;
        byte nextPieceSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);

            switch (sideColor) {
                case Colors.WHITE: sideMidg += arrWhiteBishopMidgScoresList[nextPieceSquare]; break;
                case Colors.BLACK: sideMidg += arrBlackBishopMidgScoresList[nextPieceSquare]; break;
                default:
                    throw new Exception("sideColor=" + sideColor);
            }

            addOnCtrlledSquareScore(sideColor, sidePiece, nextPieceBb);

            piecesBb &= ~nextPieceBb;
            nextPieceBb = piecesBb & -piecesBb;
        }

    }

    private void evaluateRooksArrangement(final byte sideColor, final long initPiecesBb)
            throws Exception {

        final int sidePiece = sideColor * Pieces.ROLE_ROOK;

        long piecesBb = initPiecesBb;
        long nextPieceBb = piecesBb & -piecesBb;
        byte nextPieceSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);

            switch (sideColor) {
                case Colors.WHITE:
                    sideMidg += arrWhiteRookMidgScoresList[nextPieceSquare];
                    sideEndg += arrWhiteRookEndgScoresList[nextPieceSquare];
                    break;
                case Colors.BLACK:
                    sideMidg += arrBlackRookMidgScoresList[nextPieceSquare];
                    sideEndg += arrBlackRookEndgScoresList[nextPieceSquare];
                    break;
                default:
                    throw new Exception("sideColor=" + sideColor);
            }

            addOnCtrlledSquareScore(sideColor, sidePiece, nextPieceBb);
            addOnOpenSemiOFilesScore(sideColor, sidePiece, nextPieceBb);

            piecesBb &= ~nextPieceBb;
            nextPieceBb = piecesBb & -piecesBb;
        }

    }

    private void evaluateQueensArrangement(final byte sideColor, final long initPiecesBb)
            throws Exception {

        final int sidePiece = sideColor * Pieces.ROLE_QUEEN;

        long piecesBb = initPiecesBb;
        long nextPieceBb = piecesBb & -piecesBb;
        byte nextPieceSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);

            switch (sideColor) {
                case Colors.WHITE:
                    sideMidg += arrWhiteQueenMidgScoresList[nextPieceSquare];
                    sideEndg += arrWhiteQueenEndgScoresList[nextPieceSquare];
                    break;
                case Colors.BLACK:
                    sideMidg += arrBlackQueenMidgScoresList[nextPieceSquare];
                    sideEndg += arrBlackQueenEndgScoresList[nextPieceSquare];
                    break;
                default:
                    throw new Exception("sideColor=" + sideColor);
            }

            addOnCtrlledSquareScore(sideColor, sidePiece, nextPieceBb);
            addOnOpenSemiOFilesScore(sideColor, sidePiece, nextPieceBb);

            piecesBb &= ~nextPieceBb;
            nextPieceBb = piecesBb & -piecesBb;
        }

    }

    private void evaluateKingArrangement(final byte sideColor, final long piecesBb)
            throws Exception {

//        final int sidePiece = sideColor * Pieces.ROLE_KING;

        byte nextPieceSquare;

        nextPieceSquare = (byte) Long.numberOfTrailingZeros(piecesBb);

        switch (sideColor) {
            case Colors.WHITE:
                sideMidg += arrWhiteKingMidgScoresList[nextPieceSquare];
                sideEndg += arrWhiteKingEndgScoresList[nextPieceSquare];
                break;
            case Colors.BLACK:
                sideMidg += arrBlackKingMidgScoresList[nextPieceSquare];
                sideEndg += arrBlackKingEndgScoresList[nextPieceSquare];
                break;
            default:
                throw new Exception("sideColor=" + sideColor);
        }

//        addOnCtrlledSquareScore(sideColor, sidePiece, piecesBb);

    }

    private void addOnCtrlledSquareScore(final byte sideColor, final int piece, final long squareBb)
            throws Exception {

        final int pieceValue = pieceMidgValue(piece);

        switch (sideColor) {
            case Colors.WHITE:
                if ((squareBb & evalNode.blackControlledBb) != BitBoards.EMPTY) {
                    sideMidg += - (int) ((pieceValue + ON_REALS)
                            / arrOnCtrlledSquareDivisor);
                }
                break;
            case Colors.BLACK:
                if ((squareBb & evalNode.whiteControlledBb) != BitBoards.EMPTY) {
                    sideMidg += - (int) ((pieceValue + ON_REALS)
                            / arrOnCtrlledSquareDivisor);
                }
                break;
            default:
                throw new Exception("sideColor=" + sideColor);
        }

    }

    private void addOnOpenSemiOFilesScore(final byte sideColor, final int piece, final long squareBb)
            throws Exception {

        switch (piece) {
            case Pieces.WHITE_ROOK:
            case Pieces.BLACK_ROOK:
                if ((squareBb & evalNode.openFilesBb) != BitBoards.EMPTY) {
                    sideMidg += arrRookOnOpenFileScore;
                }
                break;
            case Pieces.WHITE_QUEEN:
            case Pieces.BLACK_QUEEN:
                if ((squareBb & evalNode.openFilesBb) != BitBoards.EMPTY) {
                    sideMidg += arrQueenOnOpenFileScore;
                }
                break;
            default:
                throw new Exception("piece=" + piece);
        }

        switch (piece) {
            case Pieces.WHITE_ROOK:
                if ((squareBb & evalNode.whiteSemiOFilesBb) != BitBoards.EMPTY) {
                    sideMidg += arrRookOnSemiOFileScore;
                }
                break;
            case Pieces.WHITE_QUEEN:
                if ((squareBb & evalNode.whiteSemiOFilesBb) != BitBoards.EMPTY) {
                    sideMidg += arrQueenOnSemiOFileScore;
                }
                break;
            case Pieces.BLACK_ROOK:
                if ((squareBb & evalNode.blackSemiOFilesBb) != BitBoards.EMPTY) {
                    sideMidg += arrRookOnSemiOFileScore;
                }
                break;
            case Pieces.BLACK_QUEEN:
                if ((squareBb & evalNode.blackSemiOFilesBb) != BitBoards.EMPTY) {
                    sideMidg += arrQueenOnSemiOFileScore;
                }
                break;
            default:
                throw new Exception("piece=" + piece);
        }

    }

    private void addCenterControlCounters(final long toSquareBb) {

        if ((toSquareBb & BitBoards.CENTRE_RING_1) != BitBoards.EMPTY) {
            ++cRingControlsCounter;
        } else if ((toSquareBb & BitBoards.CENTRE) != BitBoards.EMPTY) {
            ++centreControlsCounter;
        }

    }

    /******************************************************************************************************************
     *** evaluate king safety
     ******************************************************************************************************************/

    private void addKRingsAttackCounters(final byte sideColor, final long toSquareBb)
            throws Exception {

        switch (sideColor) {
            case Colors.WHITE:
                if ((evalNode.blackKRing2Bb & toSquareBb) != BitBoards.EMPTY) {
                    ++kRing2AttacksCounter;
                } else if ((evalNode.blackKRing1Bb & toSquareBb) != BitBoards.EMPTY) {
                    ++kRing1AttacksCounter;
                } else if ((evalNode.blackKingBb & toSquareBb) != BitBoards.EMPTY) {
                    ++kingAttacksCounter;
                }
                break;
            case Colors.BLACK:
                if ((evalNode.whiteKRing2Bb & toSquareBb) != BitBoards.EMPTY) {
                    ++kRing2AttacksCounter;
                } else if ((evalNode.whiteKRing1Bb & toSquareBb) != BitBoards.EMPTY) {
                    ++kRing1AttacksCounter;
                } else if ((evalNode.whiteKingBb & toSquareBb) != BitBoards.EMPTY) {
                    ++kingAttacksCounter;
                }
                break;
            default:
                throw new Exception("sideColor=" + sideColor);
        }

    }

    private void evaluateSideKingSafety(final byte sideColor)
            throws Exception {

        evaluateSidePawnShield(sideColor);

        evaluateSideKZoneAttacks(sideColor);

    }

    private void evaluateSidePawnShield(final byte sideColor)
            throws Exception {

        final int kingSquare;

        long sidePawnShieldBb, nextShieldPawnBb;

        int shieldPawnsCounter = 0;

        int normFullmovesNumber;

        switch (sideColor) {
            case Colors.WHITE:
                kingSquare = Long.numberOfTrailingZeros(evalNode.whiteKingBb);
                sidePawnShieldBb = BitBoards.WK_PSHIELD_BB_LT[/*Squares.rank()*/kingSquare % Squares.EDGE] & evalNode.whitePawnsBb;
                break;
            case Colors.BLACK:
                kingSquare = Long.numberOfTrailingZeros(evalNode.blackKingBb);
                sidePawnShieldBb = BitBoards.BK_PSHIELD_BB_LT[/*Squares.rank()*/kingSquare % Squares.EDGE] & evalNode.blackPawnsBb;
                break;
            default:
                throw new Exception("sideColor=" + sideColor);
        }

        nextShieldPawnBb = sidePawnShieldBb & -sidePawnShieldBb;
        while (nextShieldPawnBb != BitBoards.EMPTY) {
            ++shieldPawnsCounter;
            sidePawnShieldBb &= ~nextShieldPawnBb;
            nextShieldPawnBb = sidePawnShieldBb & -sidePawnShieldBb;
        }

        if (evalNode.fullmovesNumber <= ksaKingUncastledMaxFullmoves) {
            normFullmovesNumber = evalNode.fullmovesNumber;
        } else {
            normFullmovesNumber = ksaKingUncastledMaxFullmoves;
        }

        sideMidg += (int) ((MAX_SHIELD_PAWNS_NUMBER - shieldPawnsCounter) * ksaShieldPawnLackScore
                * ((normFullmovesNumber + ON_REALS) / ksaKingUncastledMaxFullmoves));

    }

    private void evaluateSideKZoneAttacks(final byte sideColor) {

        sideMidg += ksaKingAttackMidgScore * kingAttacksCounter
                + ksaKRing1AttackMidgScore * kRing1AttacksCounter
                + ksaKRing2AttackMidgScore * kRing2AttacksCounter;

        sideEndg += ksaKingAttackEndgScore * kingAttacksCounter
                + ksaKRing1AttackEndgScore * kRing1AttacksCounter
                + ksaKRing2AttackEndgScore * kRing2AttacksCounter;

    }

    /******************************************************************************************************************
     *** utilities
     ******************************************************************************************************************/

    /**
     * Riflette verticalmente una matrice 8x8 con informazioni su una posizione.
     * Utilizzato per gestire i valori fissi speculari tra Bianco e Nero.
     *
     * @param array La matrice da riflettere.
     * @return La matrice riflessa.
     */
    public static int[] verticalMirrorArray(final int[] array) {

        final int[] reverseArray = new int[array.length];

        for (int r = 0; r <= Squares.EDGE - 1; ++r) {
            System.arraycopy(array, 8 * (Squares.EDGE - r - 1),
                    reverseArray, 8 * r, Squares.EDGE - 1 + 1);
        }

        return reverseArray;
    }

    /**
     * Formatta un array come Java farebbe con un ArrayList.
     * In caso di elemento null, imposta "_".
     *
     * @param array L'array da formattare.
     * @return La stringa con l'array formattato. Ad es.: [obj1, obj2, _, obj3] .
     */
    public static String arrayToString(final Object[] array) {

        StringBuilder sb = new StringBuilder();
        boolean notNullElement = false;
        int i;

        for (i = array.length - 1; !notNullElement && i >= 0; --i) {
            if (array[i] != null) {
                notNullElement = true;
            }
        }

        ++i;

        if (notNullElement) {
            for (int j = 0; j <= i; ++j) {
                if (sb.length() == 0) {
                    sb.append("[");
                } else {
                    sb.append(",");
                }
                if (array[j] != null) {
                    sb.append(array[j].toString());
                } else {
                    sb.append("_");
                }
            }
            sb.append("]");
        }

        return sb.toString();
    }

}
