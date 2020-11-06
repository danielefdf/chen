package engine.model;

import model.elements.BitBoards;
import model.elements.Colors;
import model.elements.Pieces;
import model.elements.Squares;
import model.elements.States;

public abstract class EvaluaShow {

    public static Engine engine;
    public static Node node;

    public static int allPiecesMidgScore;

    public static int kingAttacksCounter,
                      kRing1AttacksCounter,
                      kRing2AttacksCounter;

    public static int centreControlsCounter,
                      cRingControlsCounter;

    public static int whitePawnsFs, blackPawnsFs;

    public static long whitePawnsFillDeltaBb, blackPawnsFillDeltaBb;

    public static int whitePawnIslandsCounter, blackPawnIslandsCounter;

    public static int taperedScore,
                          midg,   endg;

    public static int whiteMidg, blackMidg,             whiteEndg, blackEndg,
                          whiteMatMidg, blackMatMidg,       whiteMatEndg, blackMatEndg,
                          whitePstMidg, blackPstMidg,       whitePstEndg, blackPstEndg,
                          whiteMobMidg, blackMobMidg,       whiteMobEndg, blackMobEndg,
                          whiteArrMidg, blackArrMidg,       whiteArrEndg, blackArrEndg,
                          whiteKsaMidg, blackKsaMidg,       whiteKsaEndg, blackKsaEndg;

    public static int whitePawnsMatMidg,   blackPawnsMatMidg,     whitePawnsMatEndg,   blackPawnsMatEndg,
                      whiteKnightsMatMidg, blackKnightsMatMidg,   whiteKnightsMatEndg, blackKnightsMatEndg,
                      whiteBishopsMatMidg, blackBishopsMatMidg,   whiteBishopsMatEndg, blackBishopsMatEndg,
                      whiteRooksMatMidg,   blackRooksMatMidg,     whiteRooksMatEndg,   blackRooksMatEndg,
                      whiteQueensMatMidg,  blackQueensMatMidg,    whiteQueensMatEndg,  blackQueensMatEndg,
                      whiteKingMatMidg,    blackKingMatMidg,      whiteKingMatEndg,    blackKingMatEndg,
                      whiteShortCgMatMidg, blackShortCgMatMidg,
                      whiteLongCgMatMidg,  blackLongCgMatMidg;

    public static int whiteLonelyKnightMidg, blackLonelyKnightMidg,
                      whiteLonelyKnightEndg, blackLonelyKnightEndg,
                      whiteLonelyBishopMidg, blackLonelyBishopMidg,   whiteLonelyBishopEndg, blackLonelyBishopEndg,
                      whiteLonelyRookMidg,   blackLonelyRookMidg,     whiteLonelyRookEndg,   blackLonelyRookEndg;

    public static int whiteSameFBishopsMidg, blackSameFBishopsMidg,   whiteSameFBishopsEndg, blackSameFBishopsEndg;

    public static int whiteNoPawnsMidg, blackNoPawnsMidg,   whiteNoPawnsEndg, blackNoPawnsEndg;

    public static int whiteDispersionMidg,   blackDispersionMidg,
                      whiteDistorsionMidg,   blackDistorsionMidg,
                      whiteIsolaniPawnsMidg, blackIsolaniPawnsMidg,   whiteIsolaniPawnsEndg, blackIsolaniPawnsEndg;

    public static long whitePawnsMovesBb,   blackPawnsMovesBb,
                       whiteKnightsMovesBb, blackKnightsMovesBb,
                       whiteBishopsMovesBb, blackBishopsMovesBb,
                       whiteRooksMovesBb,   blackRooksMovesBb,
                       whiteQueensMovesBb,  blackQueensMovesBb,
                       whiteKingMovesBb,    blackKingMovesBb;

    public static int whitePawnsStepsMobMidg,        blackPawnsStepsMobMidg,
                      whitePawnsDoubleStepsMobMidg,  blackPawnsDoubleStepsMobMidg,
                      whitePawnsCapturesMobMidg,     blackPawnsCapturesMobMidg;

    public static int whiteKnightsStepsMobMidg,    blackKnightsStepsMobMidg,
                      whiteKnightsCapturesMobMidg, blackKnightsCapturesMobMidg,
                      whiteBishopsStepsMobMidg,    blackBishopsStepsMobMidg,
                      whiteBishopsCapturesMobMidg, blackBishopsCapturesMobMidg,
                      whiteRooksStepsMobMidg,      blackRooksStepsMobMidg,
                      whiteRooksCapturesMobMidg,   blackRooksCapturesMobMidg,
                      whiteQueensStepsMobMidg,     blackQueensStepsMobMidg,
                      whiteQueensCapturesMobMidg,  blackQueensCapturesMobMidg,
                      whiteKingStepsMobMidg,       blackKingStepsMobMidg,
                      whiteKingCapturesMobMidg,    blackKingCapturesMobMidg,
                      whiteShortCgMobMidg,         blackShortCgMobMidg,
                      whiteLongCgMobMidg,          blackLongCgMobMidg;

    public static int whitePawnsArrMidg,   blackPawnsArrMidg,     whitePawnsArrEndg,   blackPawnsArrEndg,
                      whiteKnightsArrMidg, blackKnightsArrMidg,   whiteKnightsArrEndg, blackKnightsArrEndg,
                      whiteBishopsArrMidg, blackBishopsArrMidg,   whiteBishopsArrEndg, blackBishopsArrEndg,
                      whiteRooksArrMidg,   blackRooksArrMidg,     whiteRooksArrEndg,   blackRooksArrEndg,
                      whiteQueensArrMidg,  blackQueensArrMidg,    whiteQueensArrEndg,  blackQueensArrEndg,
                      whiteKingArrMidg,    blackKingArrMidg,      whiteKingArrEndg,    blackKingArrEndg;

    public static int whiteOnCtrlledSquareMidg,  blackOnCtrlledSquareMidg,
                      whiteCentreControlMidg,    blackCentreControlMidg,
                      whiteCRingControlMidg,     blackCRingControlMidg,
                      whiteRookOnOpenFileMidg,   blackRookOnOpenFileMidg,
                      whiteQueenOnOpenFileMidg,  blackQueenOnOpenFileMidg,
                      whiteRookOnSemiOFileMidg,  blackRookOnSemiOFileMidg,
                      whiteQueenOnSemiOFileMidg, blackQueenOnSemiOFileMidg,
                      whitePassedPawnsMidg,      blackPassedPawnsMidg,        whitePassedPawnsEndg, blackPassedPawnsEndg;

    public static int whiteShieldPawnsMidg,    blackShieldPawnsMidg,
                      whiteAttackedKingMidg,   blackAttackedKingMidg,     whiteAttackedKingEndg,   blackAttackedKingEndg,
                      whiteAttackedKRing1Midg, blackAttackedKRing1Midg,   whiteAttackedKRing1Endg, blackAttackedKRing1Endg,
                      whiteAttackedKRing2Midg, blackAttackedKRing2Midg,   whiteAttackedKRing2Endg, blackAttackedKRing2Endg;

    /********************************************************************************************************************
     *** node evaluation
     ********************************************************************************************************************/

    public static int evaluation(final Engine newEngine, final Node newNode)
            throws Exception {

        engine = newEngine;
        node = newNode;

        final int nodeValue;

        switch (node.gameState) {
            case States.ONGOING:
            case States.CHECK:
                evaluatePosition(node);
                nodeValue = taperedScore;
                break;
            case States.CHECKMATE:
                // chi ha la mossa subisce il matto
                nodeValue = Engine.START_ALPHA;
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
                throw new Exception("node.gameState=" + node.gameState);
        }

        return nodeValue;
    }

	private static void evaluatePosition(final Node newNode)
	        throws Exception {

	    node = newNode;

        engine.computeBoardQuantities(node);

        initVariables();

        computeAllPiecesMidgScore();

        evaluateSidePosition(Colors.WHITE);
        evaluateSidePosition(Colors.BLACK);

        switch (node.playerColor) {
        case Colors.WHITE:
            midg = whiteMidg - blackMidg;
            endg = whiteEndg - blackEndg;
            break;
        case Colors.BLACK:
            midg = blackMidg - whiteMidg;
            endg = blackEndg - whiteEndg;
            break;
        default:
            throw new Exception("node.playerColor=" + node.playerColor);
        }

        taperedScore = (int) (midg - (( endg - midg + Engine.ON_REALS ) / engine.matAllPiecesMidgScore )
                                        * ( allPiecesMidgScore - engine.matAllPiecesMidgScore ));

    }

    private static void initVariables() {

        taperedScore = 0;
            midg = 0;   endg = 0;

        whiteMidg = 0; blackMidg = 0;             whiteEndg = 0; blackEndg = 0;
            whiteMatMidg = 0; blackMatMidg = 0;       whiteMatEndg = 0; blackMatEndg = 0;
            whitePstMidg = 0; blackPstMidg = 0;       whitePstEndg = 0; blackPstEndg = 0;
            whiteMobMidg = 0; blackMobMidg = 0;       whiteMobEndg = 0; blackMobEndg = 0;
            whiteArrMidg = 0; blackArrMidg = 0;       whiteArrEndg = 0; blackArrEndg = 0;
            whiteKsaMidg = 0; blackKsaMidg = 0;       whiteKsaEndg = 0; blackKsaEndg = 0;

        whitePawnsMatMidg   = 0; blackPawnsMatMidg   = 0;   whitePawnsMatEndg   = 0; blackPawnsMatEndg   = 0;
        whiteKnightsMatMidg = 0; blackKnightsMatMidg = 0;   whiteKnightsMatEndg = 0; blackKnightsMatEndg = 0;
        whiteBishopsMatMidg = 0; blackBishopsMatMidg = 0;   whiteBishopsMatEndg = 0; blackBishopsMatEndg = 0;
        whiteRooksMatMidg   = 0; blackRooksMatMidg   = 0;   whiteRooksMatEndg   = 0; blackRooksMatEndg   = 0;
        whiteQueensMatMidg  = 0; blackQueensMatMidg  = 0;   whiteQueensMatEndg  = 0; blackQueensMatEndg  = 0;
        whiteKingMatMidg    = 0; blackKingMatMidg    = 0;   whiteKingMatEndg    = 0; blackKingMatEndg    = 0;
        whiteShortCgMatMidg = 0; blackShortCgMatMidg = 0;
        whiteLongCgMatMidg  = 0; blackLongCgMatMidg  = 0;

        whiteLonelyKnightMidg = 0; blackLonelyKnightMidg = 0;
        whiteLonelyKnightEndg = 0; blackLonelyKnightEndg = 0;
        whiteLonelyBishopMidg = 0; blackLonelyBishopMidg = 0;   whiteLonelyBishopEndg = 0; blackLonelyBishopEndg = 0;
        whiteLonelyRookMidg   = 0; blackLonelyRookMidg   = 0;   whiteLonelyRookEndg   = 0; blackLonelyRookEndg   = 0;

        whiteSameFBishopsMidg = 0; blackSameFBishopsMidg = 0;   whiteSameFBishopsEndg = 0; blackSameFBishopsEndg = 0;

        whiteNoPawnsMidg = 0; blackNoPawnsMidg = 0;   whiteNoPawnsEndg = 0; blackNoPawnsEndg = 0;

        whiteDispersionMidg   = 0; blackDispersionMidg   = 0;
        whiteDistorsionMidg   = 0; blackDistorsionMidg   = 0;
        whiteIsolaniPawnsMidg = 0; blackIsolaniPawnsMidg = 0;   whiteIsolaniPawnsEndg = 0; blackIsolaniPawnsEndg = 0;

        whitePawnsMovesBb   = BitBoards.EMPTY; blackPawnsMovesBb   = BitBoards.EMPTY;
        whiteKnightsMovesBb = BitBoards.EMPTY; blackKnightsMovesBb = BitBoards.EMPTY;
        whiteBishopsMovesBb = BitBoards.EMPTY; blackBishopsMovesBb = BitBoards.EMPTY;
        whiteRooksMovesBb   = BitBoards.EMPTY; blackRooksMovesBb   = BitBoards.EMPTY;
        whiteQueensMovesBb  = BitBoards.EMPTY; blackQueensMovesBb  = BitBoards.EMPTY;
        whiteKingMovesBb    = BitBoards.EMPTY; blackKingMovesBb    = BitBoards.EMPTY;

        whitePawnsStepsMobMidg       = 0; blackPawnsStepsMobMidg       = 0;
        whitePawnsDoubleStepsMobMidg = 0; blackPawnsDoubleStepsMobMidg = 0;
        whitePawnsCapturesMobMidg    = 0; blackPawnsCapturesMobMidg    = 0;

        whiteKnightsStepsMobMidg    = 0; blackKnightsStepsMobMidg    = 0;
        whiteKnightsCapturesMobMidg = 0; blackKnightsCapturesMobMidg = 0;
        whiteBishopsStepsMobMidg    = 0; blackBishopsStepsMobMidg    = 0;
        whiteBishopsCapturesMobMidg = 0; blackBishopsCapturesMobMidg = 0;
        whiteRooksStepsMobMidg      = 0; blackRooksStepsMobMidg      = 0;
        whiteRooksCapturesMobMidg   = 0; blackRooksCapturesMobMidg   = 0;
        whiteQueensStepsMobMidg     = 0; blackQueensStepsMobMidg     = 0;
        whiteQueensCapturesMobMidg  = 0; blackQueensCapturesMobMidg  = 0;
        whiteKingStepsMobMidg       = 0; blackKingStepsMobMidg       = 0;
        whiteKingCapturesMobMidg    = 0; blackKingCapturesMobMidg    = 0;
        whiteShortCgMobMidg         = 0; blackShortCgMobMidg         = 0;
        whiteLongCgMobMidg          = 0; blackLongCgMobMidg          = 0;

        whitePawnsArrMidg   = 0; blackPawnsArrMidg   = 0;   whitePawnsArrEndg   = 0; blackPawnsArrEndg   = 0;
        whiteKnightsArrMidg = 0; blackKnightsArrMidg = 0;   whiteKnightsArrEndg = 0; blackKnightsArrEndg = 0;
        whiteBishopsArrMidg = 0; blackBishopsArrMidg = 0;   whiteBishopsArrEndg = 0; blackBishopsArrEndg = 0;
        whiteRooksArrMidg   = 0; blackRooksArrMidg   = 0;   whiteRooksArrEndg   = 0; blackRooksArrEndg   = 0;
        whiteQueensArrMidg  = 0; blackQueensArrMidg  = 0;   whiteQueensArrEndg  = 0; blackQueensArrEndg  = 0;
        whiteKingArrMidg    = 0; blackKingArrMidg    = 0;   whiteKingArrEndg    = 0; blackKingArrEndg    = 0;

        whiteOnCtrlledSquareMidg  = 0; blackOnCtrlledSquareMidg  = 0;
        whiteCentreControlMidg    = 0; blackCentreControlMidg    = 0;
        whiteCRingControlMidg     = 0; blackCRingControlMidg     = 0;
        whiteRookOnOpenFileMidg   = 0; blackRookOnOpenFileMidg   = 0;
        whiteQueenOnOpenFileMidg  = 0; blackQueenOnOpenFileMidg  = 0;
        whiteRookOnSemiOFileMidg  = 0; blackRookOnSemiOFileMidg  = 0;
        whiteQueenOnSemiOFileMidg = 0; blackQueenOnSemiOFileMidg = 0;
        whitePassedPawnsMidg      = 0; blackPassedPawnsMidg      = 0;   whitePassedPawnsEndg = 0; blackPassedPawnsEndg = 0;

        whiteShieldPawnsMidg    = 0; blackShieldPawnsMidg    = 0;
        whiteAttackedKingMidg   = 0; blackAttackedKingMidg   = 0;   whiteAttackedKingEndg   = 0; blackAttackedKingEndg   = 0;
        whiteAttackedKRing1Midg = 0; blackAttackedKRing1Midg = 0;   whiteAttackedKRing1Endg = 0; blackAttackedKRing1Endg = 0;
        whiteAttackedKRing2Midg = 0; blackAttackedKRing2Midg = 0;   whiteAttackedKRing2Endg = 0; blackAttackedKRing2Endg = 0;

    }

    private static void computeAllPiecesMidgScore() {

        allPiecesMidgScore = engine.matPawnMidgScore   * (node.whitePawnsCounter   + node.blackPawnsCounter)
                           + engine.matKnightMidgScore * (node.whiteKnightsCounter + node.blackKnightsCounter)
                           + engine.matBishopMidgScore * (node.whiteBishopsCounter + node.blackBishopsCounter)
                           + engine.matRookMidgScore   * (node.whiteRooksCounter   + node.blackRooksCounter)
                           + engine.matQueenMidgScore  * (node.whiteQueensCounter  + node.blackQueensCounter);

        if (node.whiteShortCg) {
            allPiecesMidgScore += engine.matShortCgMidgScore;
        }

        if (node.whiteLongCg) {
            allPiecesMidgScore += engine.matLongCgMidgScore;
        }

        if (node.blackShortCg) {
            allPiecesMidgScore += engine.matShortCgMidgScore;
        }

        if (node.blackLongCg) {
            allPiecesMidgScore += engine.matLongCgMidgScore;
        }

    }

    private static void evaluateSidePosition(final byte sideColor)
	        throws Exception {

        initCounters();

        engine.computeSideControlled(node, (byte) -sideColor); // needed in arrangement
        engine.computeSideQuantities(node, sideColor);

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

        evaluateSideMidgScore(sideColor);

    }

    private static void initCounters() {

        kingAttacksCounter   = 0;
        kRing1AttacksCounter = 0;
        kRing2AttacksCounter = 0;

        centreControlsCounter = 0;
        cRingControlsCounter  = 0;

    }

    /********************************************************************************************************************
     *** evaluate material
     ********************************************************************************************************************/

    private static void evaluateSideMaterial(final byte sideColor)
            throws Exception {

        evaluateSideMidgMaterial(sideColor);

        evaluateSideEndgMaterial(sideColor);

    }

    private static void evaluateSideMidgMaterial(final byte sideColor)
            throws Exception {

        switch (sideColor) {
        case Colors.WHITE:

            whitePawnsMatMidg   = engine.matPawnMidgScore   * node.whitePawnsCounter;
            whiteKnightsMatMidg = engine.matKnightMidgScore * node.whiteKnightsCounter;
            whiteBishopsMatMidg = engine.matBishopMidgScore * node.whiteBishopsCounter;
            whiteRooksMatMidg   = engine.matRookMidgScore   * node.whiteRooksCounter;
            whiteQueensMatMidg  = engine.matQueenMidgScore  * node.whiteQueensCounter;

            if (node.whiteShortCg) {
                whiteShortCgMatMidg = engine.matShortCgMidgScore;
            }
            if (node.whiteLongCg) {
                whiteLongCgMatMidg = engine.matLongCgMidgScore;
            }

            if (node.whiteKnightsCounter == 1) {
                whiteLonelyKnightMidg = engine.matLonelyKnightMidgScore;
            }
            if (node.whiteBishopsCounter == 1) {
                whiteLonelyBishopMidg = engine.matLonelyBishopMidgScore * node.whiteBishopsCounter;
            }
            if (node.whiteRooksCounter == 1) {
                whiteLonelyRookMidg = engine.matLonelyRookMidgScore * node.whiteRooksCounter;
            }

            if (node.whiteBishopsCounter >= 2
            		&& (node.whiteWFBishopsCounter == 0
                            || node.whiteBFBishopsCounter == 0)) {
                whiteSameFBishopsMidg = engine.matSameFBishopsMidgScore;
            }

            if (node.whitePawnsCounter == 0) {
                whiteNoPawnsMidg = engine.matNoPawnsMidgScore;
            }

            break;
        case Colors.BLACK:

            blackPawnsMatMidg   = engine.matPawnMidgScore   * node.blackPawnsCounter;
            blackKnightsMatMidg = engine.matKnightMidgScore * node.blackKnightsCounter;
            blackBishopsMatMidg = engine.matBishopMidgScore * node.blackBishopsCounter;
            blackRooksMatMidg   = engine.matRookMidgScore   * node.blackRooksCounter;
            blackQueensMatMidg  = engine.matQueenMidgScore  * node.blackQueensCounter;

            if (node.blackShortCg) {
                blackShortCgMatMidg = engine.matShortCgMidgScore;
            }
            if (node.blackLongCg) {
                blackLongCgMatMidg = engine.matLongCgMidgScore;
            }

            if (node.blackKnightsCounter == 1) {
                blackLonelyKnightMidg = engine.matLonelyKnightMidgScore;
            }
            if (node.blackBishopsCounter == 1) {
                blackLonelyBishopMidg = engine.matLonelyBishopMidgScore * node.blackBishopsCounter;
            }
            if (node.blackRooksCounter == 1) {
                blackLonelyRookMidg = engine.matLonelyRookMidgScore * node.blackRooksCounter;
            }

            if (node.blackBishopsCounter >= 2
            		&& (node.blackWFBishopsCounter == 0
                            || node.blackBFBishopsCounter == 0)) {
                blackSameFBishopsMidg = engine.matSameFBishopsMidgScore;
            }

            if (node.blackPawnsCounter == 0) {
                blackNoPawnsMidg = engine.matNoPawnsMidgScore;
            }

            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

    }

    private static void evaluateSideEndgMaterial(final byte sideColor)
            throws Exception {

        switch (sideColor) {
        case Colors.WHITE:

            whitePawnsMatEndg   = engine.matPawnEndgScore   * node.whitePawnsCounter;
            whiteKnightsMatEndg = engine.matKnightEndgScore * node.whiteKnightsCounter;
            whiteBishopsMatEndg = engine.matBishopEndgScore * node.whiteBishopsCounter;
            whiteRooksMatEndg   = engine.matRookEndgScore   * node.whiteRooksCounter;
            whiteQueensMatEndg  = engine.matQueenEndgScore  * node.whiteQueensCounter;

            if (node.whiteKnightsCounter == 1) {
                whiteLonelyKnightEndg = engine.matLonelyKnightEndgScore;
            }
            if (node.whiteBishopsCounter == 1) {
                whiteLonelyBishopEndg = engine.matLonelyBishopEndgScore;
            }
            if (node.whiteRooksCounter == 1) {
                whiteLonelyRookEndg = engine.matLonelyRookEndgScore;
            }

            if (node.whiteBishopsCounter >= 2
            		&& (node.whiteWFBishopsCounter == 0
                            || node.whiteBFBishopsCounter == 0)) {
                whiteSameFBishopsEndg = engine.matSameFBishopsEndgScore;
            }

            if (node.whitePawnsCounter == 0) {
                whiteSameFBishopsEndg = engine.matNoPawnsEndgScore;
            }

            break;
        case Colors.BLACK:

            blackPawnsMatEndg   = engine.matPawnEndgScore   * node.blackPawnsCounter;
            blackKnightsMatEndg = engine.matKnightEndgScore * node.blackKnightsCounter;
            blackBishopsMatEndg = engine.matBishopEndgScore * node.blackBishopsCounter;
            blackRooksMatEndg   = engine.matRookEndgScore   * node.blackRooksCounter;
            blackQueensMatEndg  = engine.matQueenEndgScore  * node.blackQueensCounter;

            if (node.blackKnightsCounter == 1) {
                blackLonelyKnightEndg = engine.matLonelyKnightEndgScore;
            }
            if (node.blackBishopsCounter == 1) {
                blackLonelyBishopEndg = engine.matLonelyBishopEndgScore;
            }
            if (node.blackRooksCounter == 1) {
                blackLonelyRookEndg = engine.matLonelyRookEndgScore;
            }

            if (node.blackBishopsCounter >= 2
            		&& (node.blackWFBishopsCounter == 0
                            || node.blackBFBishopsCounter == 0)) {
                blackSameFBishopsEndg = engine.matSameFBishopsEndgScore;
            }

            if (node.blackPawnsCounter == 0) {
                blackNoPawnsEndg = engine.matNoPawnsEndgScore;
            }

            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

    }

    /********************************************************************************************************************
     *** evaluate pawn structure
     ********************************************************************************************************************/

    private static void evaluateSidePawnStructure(final byte sideColor)
            throws Exception {

        /*
         * pawn islands file set -- index of a lookup table -> int
         */

        switch (sideColor) {
        case Colors.WHITE:
            whitePawnsFs = (int) (node.whitePawnsFilesBb >>> Squares.SQUARE_A1);
            break;
        case Colors.BLACK:
            blackPawnsFs = (int) (node.blackPawnsFilesBb >>> Squares.SQUARE_A1);
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

        /*
         * isolani pawns
         */

        switch (sideColor) {
        case Colors.WHITE:
            whiteIsolaniPawnsMidg = Engine.PST_ISOLANI_P_COUNT_LT[whitePawnsFs] * engine.pstIsolaniPawnsMidgScore;
            whiteIsolaniPawnsEndg = Engine.PST_ISOLANI_P_COUNT_LT[whitePawnsFs] * engine.pstIsolaniPawnsEndgScore;
            break;
        case Colors.BLACK:
            blackIsolaniPawnsMidg = Engine.PST_ISOLANI_P_COUNT_LT[blackPawnsFs] * engine.pstIsolaniPawnsMidgScore;
            blackIsolaniPawnsEndg = Engine.PST_ISOLANI_P_COUNT_LT[blackPawnsFs] * engine.pstIsolaniPawnsEndgScore;
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

        /*
         * pawns dispersion
         */

        switch (sideColor) {
        case Colors.WHITE:
            whitePawnIslandsCounter = Engine.PST_P_ISLANDS_COUNT_LT[whitePawnsFs];
            whiteDispersionMidg = - Math.abs(engine.pstDispersionCoefficient
                                        * whitePawnIslandsCounter * whitePawnIslandsCounter
                                            - node.whitePawnsCounter);
            break;
        case Colors.BLACK:
            blackPawnIslandsCounter = Engine.PST_P_ISLANDS_COUNT_LT[blackPawnsFs];
            blackDispersionMidg = - Math.abs(engine.pstDispersionCoefficient
                                        * blackPawnIslandsCounter * blackPawnIslandsCounter
                                            - node.blackPawnsCounter);
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
            sidePawnsRearFillBb = node.whitePawnsBb;
            sidePawnsRearFillBb |= (sidePawnsRearFillBb <<   8);
            sidePawnsRearFillBb |= (sidePawnsRearFillBb <<  16);
            sidePawnsRearFillBb |= (sidePawnsRearFillBb <<  32);
            break;
        case Colors.BLACK:
            sidePawnsRearFillBb = node.blackPawnsBb;
            sidePawnsRearFillBb |= (sidePawnsRearFillBb >>>  8);
            sidePawnsRearFillBb |= (sidePawnsRearFillBb >>> 16);
            sidePawnsRearFillBb |= (sidePawnsRearFillBb >>> 32);
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

        sidePawnsFillDeltaBb = (sidePawnsRearFillBb ^ (sidePawnsRearFillBb << 1)) & ~BitBoards.FILE_A;

        switch (sideColor) {
        case Colors.WHITE: whitePawnsFillDeltaBb = sidePawnsFillDeltaBb; break;
        case Colors.BLACK: blackPawnsFillDeltaBb = sidePawnsFillDeltaBb; break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

        nextSidePawnFillDeltaBb = sidePawnsFillDeltaBb & -sidePawnsFillDeltaBb;
        while (nextSidePawnFillDeltaBb != BitBoards.EMPTY) {
            ++fillDeltaPopCounter;
            sidePawnsFillDeltaBb &= ~nextSidePawnFillDeltaBb;
            nextSidePawnFillDeltaBb = sidePawnsFillDeltaBb & -sidePawnsFillDeltaBb;
        }

        switch (sideColor) {
        case Colors.WHITE: whiteDistorsionMidg = - engine.pstDistorsionCoefficient * fillDeltaPopCounter; break;
        case Colors.BLACK: blackDistorsionMidg = - engine.pstDistorsionCoefficient * fillDeltaPopCounter; break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

    }

    /********************************************************************************************************************
     *** evaluate mobility
     ********************************************************************************************************************/

    private static void evaluateSideMobility(final byte sideColor)
            throws Exception {

        long whiteTargetsBb, blackTargetsBb;

//        long enPassantBb;
//        if (node.enPassantSquare == null) {
//            enPassantBb = BitBoards.EMPTY;
//        } else {
//            enPassantBb = (BitBoards.ONE << node.enPassantSquare);
//        }

        switch (sideColor) {
        case Colors.WHITE:
            whiteTargetsBb = node.allPiecesBb;
//            if (node.whitePawnsBb   != BitBoards.EMPTY) { evaluateWhitePawnsMovesBb(whiteTargetsBb | enPassantBb); }
            if (node.whiteKnightsBb != BitBoards.EMPTY) { evaluateKnightsMovesBb( Colors.WHITE, node.whiteKnightsBb, whiteTargetsBb); }
            if (node.whiteBishopsBb != BitBoards.EMPTY) { evaluateBishopsMovesBb( Colors.WHITE, node.whiteBishopsBb, whiteTargetsBb); }
            if (node.whiteRooksBb   != BitBoards.EMPTY) { evaluateRooksMovesBb(   Colors.WHITE, node.whiteRooksBb,   whiteTargetsBb);
                evaluateWhiteShortCg();
                evaluateWhiteLongCg();
            }
            if (node.whiteQueensBb  != BitBoards.EMPTY) { evaluateQueensMovesBb(  Colors.WHITE, node.whiteQueensBb,  whiteTargetsBb); }
//            if (node.whiteKingBb    != BitBoards.EMPTY) { evaluateKingMovesBb(    Colors.WHITE, node.whiteKingBb,    whiteTargetsBb); } // no
            break;
        case Colors.BLACK:
            blackTargetsBb = node.allPiecesBb;
//            if (node.blackPawnsBb   != BitBoards.EMPTY) { evaluateBlackPawnsMovesBb(blackTargetsBb | enPassantBb); }
            if (node.blackKnightsBb != BitBoards.EMPTY) { evaluateKnightsMovesBb( Colors.BLACK, node.blackKnightsBb, blackTargetsBb); }
            if (node.blackBishopsBb != BitBoards.EMPTY) { evaluateBishopsMovesBb( Colors.BLACK, node.blackBishopsBb, blackTargetsBb); }
            if (node.blackRooksBb   != BitBoards.EMPTY) { evaluateRooksMovesBb(   Colors.BLACK, node.blackRooksBb,   blackTargetsBb);
                evaluateBlackShortCg();
                evaluateBlackLongCg();
            }
            if (node.blackQueensBb  != BitBoards.EMPTY) { evaluateQueensMovesBb(  Colors.BLACK, node.blackQueensBb,  blackTargetsBb); }
//            if (node.blackKingBb    != BitBoards.EMPTY) { evaluateKingMovesBb(    Colors.BLACK, node.blackKingBb,    blackTargetsBb); } // no
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

    }

//    private static void evaluateWhitePawnsMovesBb(final long whiteTargetsBb)
//            throws Exception {
//
//        final int sidePawn = Pieces.WHITE_PAWN;
//
//        long toSquaresBb, nextToSquareBb;
//        int fromSquare, toSquare;
//
//        /* N 1 */
//        toSquaresBb = (node.whitePawnsBb >>> BitBoards.VERTICAL_STEP) & ~node.allPiecesBb;
//        whitePawnsMovesBb |= toSquaresBb;
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
//        toSquaresBb = (node.whitePawnsBb >>> BitBoards.VERTICAL_DOUBLE_STEP) & ~node.allPiecesBb
//                & (~node.allPiecesBb >>> BitBoards.VERTICAL_STEP) & BitBoards.RANK_4;
//        whitePawnsMovesBb |= toSquaresBb;
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
//        toSquaresBb = (node.whitePawnsBb >>> BitBoards.ANTIGONAL_STEP) & whiteTargetsBb & ~BitBoards.FILE_H;
//        whitePawnsMovesBb |= toSquaresBb;
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
//        toSquaresBb = (node.whitePawnsBb >>> BitBoards.DIAGONAL_STEP) & whiteTargetsBb & ~BitBoards.FILE_A;
//        whitePawnsMovesBb |= toSquaresBb;
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

//    private static void evaluateBlackPawnsMovesBb(final long blackTargetsBb)
//            throws Exception {
//
//        final int sidePawn = Pieces.BLACK_PAWN;
//
//        long toSquaresBb, nextToSquareBb;
//        int fromSquare, toSquare;
//
//        /* S 1 */
//        toSquaresBb = (node.blackPawnsBb << BitBoards.VERTICAL_STEP) & ~node.allPiecesBb;
//        blackPawnsMovesBb |= toSquaresBb;
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
//        toSquaresBb = (node.blackPawnsBb << BitBoards.VERTICAL_DOUBLE_STEP) & ~node.allPiecesBb
//                & (~node.allPiecesBb << BitBoards.VERTICAL_STEP) & BitBoards.RANK_5;
//        blackPawnsMovesBb |= toSquaresBb;
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
//        toSquaresBb = (node.blackPawnsBb << BitBoards.ANTIGONAL_STEP) & blackTargetsBb & ~BitBoards.FILE_A;
//        blackPawnsMovesBb |= toSquaresBb;
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
//        toSquaresBb = (node.blackPawnsBb << BitBoards.DIAGONAL_STEP) & blackTargetsBb & ~BitBoards.FILE_H;
//        blackPawnsMovesBb |= toSquaresBb;
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

    private static void evaluateKnightsMovesBb(final byte sideColor, final long initKnightsBb, final long sideTargetsBb)
            throws Exception {

        final int sideKnight = sideColor * Pieces.ROLE_KNIGHT;

        long knightsBb = initKnightsBb;
        long nextPieceBb = knightsBb & -knightsBb;
        long toSquaresBb, nextToSquareBb;
        byte nextPieceSquare, toSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
            toSquaresBb = BitBoards.KNIGHT_MOVES_LT[nextPieceSquare] & sideTargetsBb;
            switch (sideColor) {
            case Colors.WHITE: whiteKnightsMovesBb |= toSquaresBb; break;
            case Colors.BLACK: blackKnightsMovesBb |= toSquaresBb; break;
            default:
                throw new Exception("sideColor=" + sideColor);
            }
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

    private static void evaluateBishopsMovesBb(final byte sideColor, final long initBishopsBb, final long sideTargetsBb)
            throws Exception {

        final int sideBishop = sideColor * Pieces.ROLE_BISHOP;

        long bishopsBb = initBishopsBb;
        long nextPieceBb = bishopsBb & -bishopsBb;
        long toSquaresBb, nextToSquareBb;
        byte nextPieceSquare, toSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
            toSquaresBb = BitBoards.diagonalAntigonalMoves(nextPieceSquare, nextPieceBb, node.allPiecesBb) & sideTargetsBb;
            switch (sideColor) {
            case Colors.WHITE: whiteBishopsMovesBb |= toSquaresBb; break;
            case Colors.BLACK: blackBishopsMovesBb |= toSquaresBb; break;
            default:
                throw new Exception("sideColor=" + sideColor);
            }
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

    private static void evaluateRooksMovesBb(final byte sideColor, final long initRooksBb, final long sideTargetsBb)
            throws Exception {

        final int sideRook = sideColor * Pieces.ROLE_ROOK;

        long rooksBb = initRooksBb;
        long nextPieceBb = rooksBb & -rooksBb;
        long toSquaresBb, nextToSquareBb;
        byte nextPieceSquare, toSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
            toSquaresBb = BitBoards.horizontalVerticalMoves(nextPieceSquare, nextPieceBb, node.allPiecesBb) & sideTargetsBb;
            switch (sideColor) {
            case Colors.WHITE: whiteRooksMovesBb |= toSquaresBb; break;
            case Colors.BLACK: blackRooksMovesBb |= toSquaresBb; break;
            default:
                throw new Exception("sideColor=" + sideColor);
            }
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

    private static void evaluateQueensMovesBb(final byte sideColor, final long initQueensBb, final long sideTargetsBb)
            throws Exception {

        final int sideQueen = sideColor * Pieces.ROLE_QUEEN;

        long queensBb = initQueensBb;
        long nextPieceBb = queensBb & -queensBb;
        long toSquaresBb, nextToSquareBb;
        byte nextPieceSquare, toSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);
            toSquaresBb = (BitBoards.horizontalVerticalMoves(nextPieceSquare, nextPieceBb, node.allPiecesBb)
                    | BitBoards.diagonalAntigonalMoves(nextPieceSquare, nextPieceBb, node.allPiecesBb)) & sideTargetsBb;
            switch (sideColor) {
            case Colors.WHITE: whiteQueensMovesBb |= toSquaresBb; break;
            case Colors.BLACK: blackQueensMovesBb |= toSquaresBb; break;
            default:
                throw new Exception("sideColor=" + sideColor);
            }
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

//    private static void evaluateKingMovesBb(final byte sideColor, final long kingBb, final long sideTargetsBb) // no
//            throws Exception {
//
//        final int sideKing = sideColor * Pieces.ROLE_KING;
//
//        long toSquaresBb, nextToSquareBb;
//        int pieceSquare, toSquare;
//
//        pieceSquare = Long.numberOfTrailingZeros(kingBb);
//        toSquaresBb = BitBoards.KING_MOVES_LT[pieceSquare] & sideTargetsBb;
//        switch (sideColor) {
//        case Colors.WHITE: whiteKingMovesBb |= toSquaresBb; break;
//        case Colors.BLACK: blackKingMovesBb |= toSquaresBb; break;
//        default:
//            throw new Exception("sideColor=" + sideColor);
//        }
//        nextToSquareBb = toSquaresBb & -toSquaresBb;
//        while (nextToSquareBb != BitBoards.EMPTY) {
//            if (!node.areSquaresCheckedBb2(nextToSquareBb, sideColor)) {
//                toSquare = Long.numberOfTrailingZeros(nextToSquareBb);
//                addPieceMovesScore(sideColor, sideKing, pieceSquare, toSquare, nextToSquareBb);
//            }
//            toSquaresBb &= ~nextToSquareBb;
//            nextToSquareBb = toSquaresBb & -toSquaresBb;
//        }
//
//    }

    private static void evaluateWhiteShortCg() {

        if (node.whiteShortCg
                && (BitBoards.W_S_CG_PATH & ~node.allPiecesBb) == BitBoards.W_S_CG_PATH
                && (BitBoards.W_S_CG_KING_PATH & node.blackControlledBb) == BitBoards.EMPTY) {
            whiteShortCgMobMidg = addShortCgScore();
        }

    }

    private static void evaluateWhiteLongCg() {

        if (node.whiteLongCg
                && (BitBoards.W_L_CG_PATH & ~node.allPiecesBb) == BitBoards.W_L_CG_PATH
                && (BitBoards.W_L_CG_KING_PATH & node.blackControlledBb) == BitBoards.EMPTY) {
            whiteLongCgMobMidg = addLongCgScore();
        }

    }

    private static void evaluateBlackShortCg() {

        if (node.blackShortCg
                && (BitBoards.B_S_CG_PATH & ~node.allPiecesBb) == BitBoards.B_S_CG_PATH
                && (BitBoards.B_S_CG_KING_PATH & node.whiteControlledBb) == BitBoards.EMPTY) {
            blackShortCgMobMidg = addShortCgScore();
        }

    }

    private static void evaluateBlackLongCg() {

        if (node.blackLongCg
                && (BitBoards.B_L_CG_PATH & ~node.allPiecesBb) == BitBoards.B_L_CG_PATH
                && (BitBoards.B_L_CG_KING_PATH & node.whiteControlledBb) == BitBoards.EMPTY) {
            blackLongCgMobMidg = addLongCgScore();
        }

    }

    /********************************************************************************************************************
     *** evaluate moves
     ********************************************************************************************************************/

    private static void addPawnStepScore(final byte sideColor, final int piece, final int fromSquare, final int toSquare,
            final long toSquareBb)
                    throws Exception {

        switch (sideColor) {
        case Colors.WHITE: whitePawnsStepsMobMidg += engine.mobPawnStepScore; break;
        case Colors.BLACK: blackPawnsStepsMobMidg += engine.mobPawnStepScore; break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

    }

    private static void addPawnDoubleStepScore(final byte sideColor, final int piece, final int fromSquare, final int toSquare,
            final long toSquareBb)
                    throws Exception {

        switch (sideColor) {
        case Colors.WHITE: whitePawnsDoubleStepsMobMidg += engine.mobPawnDoubleStepScore; break;
        case Colors.BLACK: blackPawnsDoubleStepsMobMidg += engine.mobPawnDoubleStepScore; break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

    }

    private static void addPawnCapturesScore(final byte sideColor, final int piece, final int fromSquare, final int toSquare,
            final long toSquareBb)
                    throws Exception {

        final Byte targetPiece;

        if (node.enPassantSquare != null
                && toSquare == node.enPassantSquare) {
            targetPiece = (byte) (-sideColor * Pieces.ROLE_PAWN);
        } else {
            targetPiece = node.squarePieceMap[toSquare];
        }

        addCenterControlCounters(toSquareBb);
        addKRingsAttackCounters(sideColor, toSquareBb);

        switch (sideColor) {
        case Colors.WHITE: whitePawnsStepsMobMidg += engine.mobPawnStepScore; break;
        case Colors.BLACK: blackPawnsStepsMobMidg += engine.mobPawnStepScore; break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

        switch (sideColor) {
        case Colors.WHITE:
            if (Math.signum(targetPiece) == sideColor) {
                whitePawnsCapturesMobMidg += (int) ((pieceMidgValue4Pawns(targetPiece) + Engine.ON_REALS)
                                                        / engine.mobTargetAttackDenomntr);
            } else {
                whitePawnsCapturesMobMidg += (int) ((pieceMidgValue4Pawns(targetPiece) + Engine.ON_REALS)
                                                        / engine.mobTargetProtectnDenomntr);
            }
            break;
        case Colors.BLACK:
            if (Math.signum(targetPiece) == sideColor) {
                blackPawnsCapturesMobMidg += (int) ((pieceMidgValue4Pawns(targetPiece) + Engine.ON_REALS)
                                                        / engine.mobTargetAttackDenomntr);
            } else {
                blackPawnsCapturesMobMidg += (int) ((pieceMidgValue4Pawns(targetPiece) + Engine.ON_REALS)
                                                        / engine.mobTargetProtectnDenomntr);
            }
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

    }

    private static void addPieceMovesScore(final byte sideColor, final int piece, final int fromSquare, final int toSquare,
            final long toSquareBb)
                    throws Exception {

        final Byte targetPiece = node.squarePieceMap[toSquare];

        final int pieceScore, targetPieceScore;

        addCenterControlCounters(toSquareBb);
        addKRingsAttackCounters(sideColor, toSquareBb);

        pieceScore = (int) ((engine.mobPieceNumerator + Engine.ON_REALS)
                                / pieceMidgValue(piece));
        switch (piece) {
        case Pieces.WHITE_KNIGHT: whiteKnightsCapturesMobMidg += pieceScore; break;
        case Pieces.WHITE_BISHOP: whiteBishopsCapturesMobMidg += pieceScore; break;
        case Pieces.WHITE_ROOK:   whiteRooksCapturesMobMidg   += pieceScore; break;
        case Pieces.WHITE_QUEEN:  whiteQueensCapturesMobMidg  += pieceScore; break;
        case Pieces.WHITE_KING:   whiteKingCapturesMobMidg    += pieceScore; break;
        case Pieces.BLACK_KNIGHT: blackKnightsCapturesMobMidg += pieceScore; break;
        case Pieces.BLACK_BISHOP: blackBishopsCapturesMobMidg += pieceScore; break;
        case Pieces.BLACK_ROOK:   blackRooksCapturesMobMidg   += pieceScore; break;
        case Pieces.BLACK_QUEEN:  blackQueensCapturesMobMidg  += pieceScore; break;
        case Pieces.BLACK_KING:   blackKingCapturesMobMidg    += pieceScore; break;
        default:
            throw new Exception("piece=" + piece);
        }

        if (targetPiece != null) {
            if (Math.signum(targetPiece) == sideColor) {
                targetPieceScore = (int) ((pieceMidgValue(targetPiece) + Engine.ON_REALS)
                                                / engine.mobTargetAttackDenomntr);
            } else {
                targetPieceScore = (int) ((pieceMidgValue(targetPiece) + Engine.ON_REALS)
                                                / engine.mobTargetProtectnDenomntr);
            }
            switch (piece) {
            case Pieces.WHITE_KNIGHT: whiteKnightsCapturesMobMidg += targetPieceScore; break;
            case Pieces.WHITE_BISHOP: whiteBishopsCapturesMobMidg += targetPieceScore; break;
            case Pieces.WHITE_ROOK:   whiteRooksCapturesMobMidg   += targetPieceScore; break;
            case Pieces.WHITE_QUEEN:  whiteQueensCapturesMobMidg  += targetPieceScore; break;
            case Pieces.WHITE_KING:   whiteKingCapturesMobMidg    += targetPieceScore; break;
            case Pieces.BLACK_KNIGHT: blackKnightsCapturesMobMidg += targetPieceScore; break;
            case Pieces.BLACK_BISHOP: blackBishopsCapturesMobMidg += targetPieceScore; break;
            case Pieces.BLACK_ROOK:   blackRooksCapturesMobMidg   += targetPieceScore; break;
            case Pieces.BLACK_QUEEN:  blackQueensCapturesMobMidg  += targetPieceScore; break;
            case Pieces.BLACK_KING:   blackKingCapturesMobMidg    += targetPieceScore; break;
            default:
                throw new Exception("piece=" + piece);
            }
        }

    }

    private static int addShortCgScore() {

        return engine.mobShortCgScore;
    }

    private static int addLongCgScore() {

        return engine.mobLongCgScore;
    }

    private static int pieceMidgValue4Pawns(final Byte piece)
            throws Exception {

        if (piece == null) {
            return 1;
        } else {
            return pieceMidgValue(piece);
        }

    }

    private static int pieceMidgValue(final int piece)
            throws Exception {

        switch (piece) {
        case Pieces.WHITE_PAWN:
        case Pieces.BLACK_PAWN:   return engine.matPawnMidgScore;
        case Pieces.WHITE_KNIGHT:
        case Pieces.BLACK_KNIGHT: return engine.matKnightMidgScore;
        case Pieces.WHITE_BISHOP:
        case Pieces.BLACK_BISHOP: return engine.matBishopMidgScore;
        case Pieces.WHITE_ROOK:
        case Pieces.BLACK_ROOK:   return engine.matRookMidgScore;
        case Pieces.WHITE_QUEEN:
        case Pieces.BLACK_QUEEN:  return engine.matQueenMidgScore;
        case Pieces.WHITE_KING:
        case Pieces.BLACK_KING:   return engine.matKingMidgScore; // king mobility
        default:
            throw new Exception("piece=" + piece);
        }

    }

    /********************************************************************************************************************
     *** evaluate arrangement
     ********************************************************************************************************************/

    private static void evaluateSideArrangement(final byte sideColor)
            throws Exception {

        switch (sideColor) {
        case Colors.WHITE:
            if (node.whitePawnsBb   != BitBoards.EMPTY) { evaluatePawnsArrangement(   Colors.WHITE, node.whitePawnsBb   ); }
            if (node.whiteKnightsBb != BitBoards.EMPTY) { evaluateKnightsArrangement( Colors.WHITE, node.whiteKnightsBb ); }
            if (node.whiteBishopsBb != BitBoards.EMPTY) { evaluateBishopsArrangement( Colors.WHITE, node.whiteBishopsBb ); }
            if (node.whiteRooksBb   != BitBoards.EMPTY) { evaluateRooksArrangement(   Colors.WHITE, node.whiteRooksBb   ); }
            if (node.whiteQueensBb  != BitBoards.EMPTY) { evaluateQueensArrangement(  Colors.WHITE, node.whiteQueensBb  ); }
            if (node.whiteKingBb    != BitBoards.EMPTY) { evaluateKingArrangement(    Colors.WHITE, node.whiteKingBb    ); }
            break;
        case Colors.BLACK:
            if (node.blackPawnsBb   != BitBoards.EMPTY) { evaluatePawnsArrangement(   Colors.BLACK, node.blackPawnsBb   ); }
            if (node.blackKnightsBb != BitBoards.EMPTY) { evaluateKnightsArrangement( Colors.BLACK, node.blackKnightsBb ); }
            if (node.blackBishopsBb != BitBoards.EMPTY) { evaluateBishopsArrangement( Colors.BLACK, node.blackBishopsBb ); }
            if (node.blackRooksBb   != BitBoards.EMPTY) { evaluateRooksArrangement(   Colors.BLACK, node.blackRooksBb   ); }
            if (node.blackQueensBb  != BitBoards.EMPTY) { evaluateQueensArrangement(  Colors.BLACK, node.blackQueensBb  ); }
            if (node.blackKingBb    != BitBoards.EMPTY) { evaluateKingArrangement(    Colors.BLACK, node.blackKingBb    ); }
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

        evaluateCentreCRingControls(sideColor);

    }

    private static void evaluateCentreCRingControls(final byte sideColor)
            throws Exception {

        switch (sideColor) {
        case Colors.WHITE:
            whiteCentreControlMidg = engine.arrCentreControlScore * centreControlsCounter
                                            + engine.arrCRingControlScore * cRingControlsCounter;
            break;
        case Colors.BLACK:
            blackCentreControlMidg = engine.arrCentreControlScore * centreControlsCounter
                                            + engine.arrCRingControlScore * cRingControlsCounter;
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

	}

	private static void evaluatePawnsArrangement(final byte sideColor, final long initPiecesBb)
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
                whitePawnsArrMidg += engine.arrWhitePawnMidgScoresList[nextPieceSquare];
                whitePawnsArrEndg += engine.arrWhitePawnEndgScoresList[nextPieceSquare];
                if ((BitBoards.WP_BLOCK_C_LT[nextPieceSquare] & node.blackPawnsBb) == BitBoards.EMPTY) {
                    ++passedPawnsCounter;
                }
                break;
            case Colors.BLACK:
                blackPawnsArrMidg += engine.arrBlackPawnMidgScoresList[nextPieceSquare];
                blackPawnsArrEndg += engine.arrBlackPawnEndgScoresList[nextPieceSquare];
                if ((BitBoards.BP_BLOCK_C_LT[nextPieceSquare] & node.whitePawnsBb) == BitBoards.EMPTY) {
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

        switch (sideColor) {
        case Colors.WHITE:
            whitePassedPawnsMidg = passedPawnsCounter * engine.pstPassedPawnsMidgScore;
            whitePassedPawnsEndg = passedPawnsCounter * engine.pstPassedPawnsEndgScore;
            break;
        case Colors.BLACK:
            blackPassedPawnsMidg = passedPawnsCounter * engine.pstPassedPawnsMidgScore;
            blackPassedPawnsEndg = passedPawnsCounter * engine.pstPassedPawnsEndgScore;
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

    }

    private static void evaluateKnightsArrangement(final byte sideColor, final long initPiecesBb)
            throws Exception {

        final int sidePiece = sideColor * Pieces.ROLE_KNIGHT;

        long piecesBb = initPiecesBb;
        long nextPieceBb = piecesBb & -piecesBb;
        byte nextPieceSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);

            switch (sideColor) {
            case Colors.WHITE: whiteKnightsArrMidg += engine.arrWhiteKnightMidgScoresList[nextPieceSquare]; break;
            case Colors.BLACK: blackKnightsArrMidg += engine.arrBlackKnightMidgScoresList[nextPieceSquare]; break;
            default:
                throw new Exception("sideColor=" + sideColor);
            }

            addOnCtrlledSquareScore(sideColor, sidePiece, nextPieceBb);

            piecesBb &= ~nextPieceBb;
            nextPieceBb = piecesBb & -piecesBb;
        }

    }

    private static void evaluateBishopsArrangement(final byte sideColor, final long initPiecesBb)
            throws Exception {

        final int sidePiece = sideColor * Pieces.ROLE_BISHOP;

        long piecesBb = initPiecesBb;
        long nextPieceBb = piecesBb & -piecesBb;
        byte nextPieceSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);

            switch (sideColor) {
            case Colors.WHITE: whiteBishopsArrMidg += engine.arrWhiteBishopMidgScoresList[nextPieceSquare]; break;
            case Colors.BLACK: blackBishopsArrMidg += engine.arrBlackBishopMidgScoresList[nextPieceSquare]; break;
            default:
                throw new Exception("sideColor=" + sideColor);
            }

            addOnCtrlledSquareScore(sideColor, sidePiece, nextPieceBb);

            piecesBb &= ~nextPieceBb;
            nextPieceBb = piecesBb & -piecesBb;
        }

    }

    private static void evaluateRooksArrangement(final byte sideColor, final long initPiecesBb)
            throws Exception {

        final int sidePiece = sideColor * Pieces.ROLE_ROOK;

        long piecesBb = initPiecesBb;
        long nextPieceBb = piecesBb & -piecesBb;
        byte nextPieceSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);

            switch (sideColor) {
            case Colors.WHITE:
                whiteRooksArrMidg += engine.arrWhiteRookMidgScoresList[nextPieceSquare];
                whiteRooksArrEndg += engine.arrWhiteRookEndgScoresList[nextPieceSquare];
                break;
            case Colors.BLACK:
                blackRooksArrMidg += engine.arrBlackRookMidgScoresList[nextPieceSquare];
                blackRooksArrEndg += engine.arrBlackRookEndgScoresList[nextPieceSquare];
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

    private static void evaluateQueensArrangement(final byte sideColor, final long initPiecesBb)
            throws Exception {

        final int sidePiece = sideColor * Pieces.ROLE_QUEEN;

        long piecesBb = initPiecesBb;
        long nextPieceBb = piecesBb & -piecesBb;
        byte nextPieceSquare;

        while (nextPieceBb != BitBoards.EMPTY) {
            nextPieceSquare = (byte) Long.numberOfTrailingZeros(nextPieceBb);

            switch (sideColor) {
            case Colors.WHITE:
                whiteQueensArrMidg += engine.arrWhiteQueenMidgScoresList[nextPieceSquare];
                whiteQueensArrEndg += engine.arrWhiteQueenEndgScoresList[nextPieceSquare];
                break;
            case Colors.BLACK:
                blackQueensArrMidg += engine.arrBlackQueenMidgScoresList[nextPieceSquare];
                blackQueensArrEndg += engine.arrBlackQueenEndgScoresList[nextPieceSquare];
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

    private static void evaluateKingArrangement(final byte sideColor, final long piecesBb)
            throws Exception {

//        final int sidePiece = sideColor * Pieces.ROLE_KING;

        byte nextPieceSquare;

        nextPieceSquare = (byte) Long.numberOfTrailingZeros(piecesBb);

        switch (sideColor) {
        case Colors.WHITE:
            whiteKingArrMidg += engine.arrWhiteKingMidgScoresList[nextPieceSquare];
            whiteKingArrEndg += engine.arrWhiteKingEndgScoresList[nextPieceSquare];
            break;
        case Colors.BLACK:
            blackKingArrMidg += engine.arrBlackKingMidgScoresList[nextPieceSquare];
            blackKingArrEndg += engine.arrBlackKingEndgScoresList[nextPieceSquare];
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

//        addOnCtrlledSquareScore(sideColor, sidePiece, piecesBb);

    }

    private static void addOnCtrlledSquareScore(final byte sideColor, final int piece, final long squareBb)
            throws Exception {

        final int pieceValue = pieceMidgValue(piece);

        switch (sideColor) {
        case Colors.WHITE:
            if ((squareBb & node.blackControlledBb) != BitBoards.EMPTY) {
                whiteOnCtrlledSquareMidg += - (int) ((pieceValue + Engine.ON_REALS)
                                                        / engine.arrOnCtrlledSquareDivisor);
            }
            break;
        case Colors.BLACK:
            if ((squareBb & node.whiteControlledBb) != BitBoards.EMPTY) {
                blackOnCtrlledSquareMidg += - (int) ((pieceValue + Engine.ON_REALS)
                                                        / engine.arrOnCtrlledSquareDivisor);
            }
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

    }

    private static void addOnOpenSemiOFilesScore(final byte sideColor, final int piece, final long squareBb)
            throws Exception {

        switch (piece) {
        case Pieces.WHITE_ROOK:
            if ((squareBb & node.openFilesBb) != BitBoards.EMPTY) {
                whiteRookOnOpenFileMidg += engine.arrRookOnOpenFileScore;
            }
            if ((squareBb & node.whiteSemiOFilesBb) != BitBoards.EMPTY) {
                whiteRookOnSemiOFileMidg += engine.arrRookOnSemiOFileScore;
            }
            break;
        case Pieces.BLACK_ROOK:
            if ((squareBb & node.openFilesBb) != BitBoards.EMPTY) {
                blackRookOnOpenFileMidg += engine.arrRookOnOpenFileScore;
            }
            if ((squareBb & node.blackSemiOFilesBb) != BitBoards.EMPTY) {
                blackRookOnSemiOFileMidg += engine.arrRookOnSemiOFileScore;
            }
            break;
        case Pieces.WHITE_QUEEN:
            if ((squareBb & node.openFilesBb) != BitBoards.EMPTY) {
                whiteQueenOnOpenFileMidg += engine.arrQueenOnOpenFileScore;
            }
            if ((squareBb & node.whiteSemiOFilesBb) != BitBoards.EMPTY) {
                whiteQueenOnSemiOFileMidg += engine.arrQueenOnSemiOFileScore;
            }
            break;
        case Pieces.BLACK_QUEEN:
            if ((squareBb & node.openFilesBb) != BitBoards.EMPTY) {
                blackQueenOnOpenFileMidg += engine.arrQueenOnOpenFileScore;
            }
            if ((squareBb & node.blackSemiOFilesBb) != BitBoards.EMPTY) {
                blackQueenOnSemiOFileMidg += engine.arrQueenOnSemiOFileScore;
            }
            break;
        default:
            throw new Exception("piece=" + piece);
        }

    }

    private static void addCenterControlCounters(final long toSquareBb) {

        if ((toSquareBb & BitBoards.CENTRE_RING_1) != BitBoards.EMPTY) {
            ++cRingControlsCounter;
        } else if ((toSquareBb & BitBoards.CENTRE) != BitBoards.EMPTY) {
            ++centreControlsCounter;
        }

    }

    /********************************************************************************************************************
     *** evaluate king safety
     ********************************************************************************************************************/

    private static void addKRingsAttackCounters(final byte sideColor, final long toSquareBb)
            throws Exception {

        switch (sideColor) {
        case Colors.WHITE:
            if ((node.blackKRing2Bb & toSquareBb) != BitBoards.EMPTY) {
                ++kRing2AttacksCounter;
            } else if ((node.blackKRing1Bb & toSquareBb) != BitBoards.EMPTY) {
                ++kRing1AttacksCounter;
            } else if ((node.blackKingBb & toSquareBb) != BitBoards.EMPTY) {
                ++kingAttacksCounter;
            }
            break;
        case Colors.BLACK:
            if ((node.whiteKRing2Bb & toSquareBb) != BitBoards.EMPTY) {
                ++kRing2AttacksCounter;
            } else if ((node.whiteKRing1Bb & toSquareBb) != BitBoards.EMPTY) {
                ++kRing1AttacksCounter;
            } else if ((node.whiteKingBb & toSquareBb) != BitBoards.EMPTY) {
                ++kingAttacksCounter;
            }
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

    }

    private static void evaluateSideKingSafety(final byte sideColor)
            throws Exception {

        evaluateSidePawnShield(sideColor);

        evaluateSideKZoneAttacks(sideColor);

    }

    private static void evaluateSidePawnShield(final byte sideColor)
            throws Exception {

        final int kingSquare;

        long sidePawnShieldBb, nextShieldPawnBb;

        int shieldPawnsCounter = 0;

        int normFullmovesNumber, sideShieldPawnsMidg;

        switch (sideColor) {
        case Colors.WHITE:
            kingSquare = Long.numberOfTrailingZeros(node.whiteKingBb);
            sidePawnShieldBb = BitBoards.WK_PSHIELD_BB_LT[/*Squares.rank()*/kingSquare % Squares.EDGE] & node.whitePawnsBb;
            break;
        case Colors.BLACK:
            kingSquare = Long.numberOfTrailingZeros(node.blackKingBb);
            sidePawnShieldBb = BitBoards.BK_PSHIELD_BB_LT[/*Squares.rank()*/kingSquare % Squares.EDGE] & node.blackPawnsBb;
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

        if (node.fullmovesNumber <= engine.ksaKingUncastledMaxFullmoves) {
            normFullmovesNumber = node.fullmovesNumber;
        } else {
            normFullmovesNumber = engine.ksaKingUncastledMaxFullmoves;
        }

        sideShieldPawnsMidg = (int) ((Engine.MAX_SHIELD_PAWNS_NUMBER - shieldPawnsCounter) * engine.ksaShieldPawnLackScore
                                        * ((normFullmovesNumber + Engine.ON_REALS) / engine.ksaKingUncastledMaxFullmoves));

        switch (sideColor) {
        case Colors.WHITE: whiteShieldPawnsMidg = sideShieldPawnsMidg; break;
        case Colors.BLACK: blackShieldPawnsMidg = sideShieldPawnsMidg; break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

    }

    private static void evaluateSideKZoneAttacks(final byte sideColor)
            throws Exception {

        switch (sideColor) {
        case Colors.WHITE:
            whiteAttackedKingMidg   = engine.ksaKingAttackMidgScore   * kingAttacksCounter;
            whiteAttackedKingEndg   = engine.ksaKingAttackEndgScore   * kingAttacksCounter;
            whiteAttackedKRing1Midg = engine.ksaKRing1AttackMidgScore * kRing1AttacksCounter;
            whiteAttackedKRing1Endg = engine.ksaKRing1AttackEndgScore * kRing1AttacksCounter;
            whiteAttackedKRing2Midg = engine.ksaKRing2AttackMidgScore * kRing2AttacksCounter;
            whiteAttackedKRing2Endg = engine.ksaKRing2AttackEndgScore * kRing2AttacksCounter;
            break;
        case Colors.BLACK:
            blackAttackedKingMidg   = engine.ksaKingAttackMidgScore   * kingAttacksCounter;
            blackAttackedKingEndg   = engine.ksaKingAttackEndgScore   * kingAttacksCounter;
            blackAttackedKRing1Midg = engine.ksaKRing1AttackMidgScore * kRing1AttacksCounter;
            blackAttackedKRing1Endg = engine.ksaKRing1AttackEndgScore * kRing1AttacksCounter;
            blackAttackedKRing2Midg = engine.ksaKRing2AttackMidgScore * kRing2AttacksCounter;
            blackAttackedKRing2Endg = engine.ksaKRing2AttackEndgScore * kRing2AttacksCounter;
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

    }

    private static void evaluateSideMidgScore(final byte sideColor)
            throws Exception {

        switch (sideColor) {
        case Colors.WHITE:
            evaluateWhiteMidgScore();
            evaluateWhiteEndgScore();
            break;
        case Colors.BLACK:
            evaluateBlackMidgScore();
            evaluateBlackEndgScore();
            break;
        default:
            throw new Exception("sideColor=" + sideColor);
        }

    }

    private static void evaluateWhiteMidgScore() {

        whiteMatMidg = whitePawnsMatMidg
                     + whiteKnightsMatMidg
                     + whiteBishopsMatMidg
                     + whiteRooksMatMidg
                     + whiteQueensMatMidg
                     + whiteKingMatMidg
                     + whiteShortCgMatMidg
                     + whiteLongCgMatMidg
                     + whiteLonelyKnightMidg
                     + whiteLonelyBishopMidg
                     + whiteLonelyRookMidg
                     + whiteSameFBishopsMidg
                     + whiteNoPawnsMidg;

        whitePstMidg = whiteDispersionMidg
                     + whiteDistorsionMidg
                     + whiteIsolaniPawnsMidg;

        whiteMobMidg = whitePawnsStepsMobMidg
                     + whitePawnsDoubleStepsMobMidg
                     + whitePawnsCapturesMobMidg
                     + whiteKnightsStepsMobMidg
                     + whiteKnightsCapturesMobMidg
                     + whiteBishopsStepsMobMidg
                     + whiteBishopsCapturesMobMidg
                     + whiteRooksStepsMobMidg
                     + whiteRooksCapturesMobMidg
                     + whiteQueensStepsMobMidg
                     + whiteQueensCapturesMobMidg
                     + whiteKingStepsMobMidg
                     + whiteKingCapturesMobMidg
                     + whiteShortCgMobMidg
                     + whiteLongCgMobMidg;

        whiteArrMidg = whitePawnsArrMidg
                     + whiteKnightsArrMidg
                     + whiteBishopsArrMidg
                     + whiteRooksArrMidg
                     + whiteQueensArrMidg
                     + whiteKingArrMidg
                     + whiteOnCtrlledSquareMidg
                     + whiteCentreControlMidg
                     + whiteCRingControlMidg
                     + whiteRookOnOpenFileMidg
                     + whiteQueenOnOpenFileMidg
                     + whiteRookOnSemiOFileMidg
                     + whiteQueenOnSemiOFileMidg
                     + whitePassedPawnsMidg;

        whiteKsaMidg = whiteShieldPawnsMidg
                     + whiteAttackedKingMidg
                     + whiteAttackedKRing1Midg
                     + whiteAttackedKRing2Midg;

        whiteMidg = whiteMatMidg
                  + whitePstMidg
                  + whiteMobMidg
                  + whiteArrMidg
                  + whiteKsaMidg;

    }

    private static void evaluateWhiteEndgScore() {

        whiteMatEndg = whitePawnsMatEndg
                     + whiteKnightsMatEndg
                     + whiteBishopsMatEndg
                     + whiteRooksMatEndg
                     + whiteQueensMatEndg
                     + whiteKingMatEndg
                     + whiteLonelyKnightEndg
                     + whiteLonelyBishopEndg
                     + whiteLonelyRookEndg
                     + whiteSameFBishopsEndg
                     + whiteNoPawnsEndg;

        whitePstEndg = whiteIsolaniPawnsEndg;

        whiteMobEndg = 0;

        whiteArrEndg = whitePawnsArrEndg
                     + whiteKnightsArrEndg
                     + whiteBishopsArrEndg
                     + whiteRooksArrEndg
                     + whiteQueensArrEndg
                     + whiteKingArrEndg
                     + whitePassedPawnsEndg;

        whiteKsaEndg = whiteAttackedKingEndg
                     + whiteAttackedKRing1Endg
                     + whiteAttackedKRing2Endg;

        whiteEndg = whiteMatEndg
                  + whitePstEndg
                  + whiteMobEndg
                  + whiteArrEndg
                  + whiteKsaEndg;

    }

    private static void evaluateBlackMidgScore() {

        blackMatMidg = blackPawnsMatMidg
                     + blackKnightsMatMidg
                     + blackBishopsMatMidg
                     + blackRooksMatMidg
                     + blackQueensMatMidg
                     + blackKingMatMidg
                     + blackShortCgMatMidg
                     + blackLongCgMatMidg
                     + blackLonelyKnightMidg
                     + blackLonelyBishopMidg
                     + blackLonelyRookMidg
                     + blackSameFBishopsMidg
                     + blackNoPawnsMidg;

        blackPstMidg = blackDispersionMidg
                     + blackDistorsionMidg
                     + blackIsolaniPawnsMidg;

        blackMobMidg = blackPawnsStepsMobMidg
                     + blackPawnsDoubleStepsMobMidg
                     + blackPawnsCapturesMobMidg
                     + blackKnightsStepsMobMidg
                     + blackKnightsCapturesMobMidg
                     + blackBishopsStepsMobMidg
                     + blackBishopsCapturesMobMidg
                     + blackRooksStepsMobMidg
                     + blackRooksCapturesMobMidg
                     + blackQueensStepsMobMidg
                     + blackQueensCapturesMobMidg
                     + blackKingStepsMobMidg
                     + blackKingCapturesMobMidg
                     + blackShortCgMobMidg
                     + blackLongCgMobMidg;

        blackArrMidg = blackPawnsArrMidg
                     + blackKnightsArrMidg
                     + blackBishopsArrMidg
                     + blackRooksArrMidg
                     + blackQueensArrMidg
                     + blackKingArrMidg
                     + blackOnCtrlledSquareMidg
                     + blackCentreControlMidg
                     + blackCRingControlMidg
                     + blackRookOnOpenFileMidg
                     + blackQueenOnOpenFileMidg
                     + blackRookOnSemiOFileMidg
                     + blackQueenOnSemiOFileMidg
                     + blackPassedPawnsMidg;

        blackKsaMidg = blackShieldPawnsMidg
                     + blackAttackedKingMidg
                     + blackAttackedKRing1Midg
                     + blackAttackedKRing2Midg;

        blackMidg = blackMatMidg
                  + blackPstMidg
                  + blackMobMidg
                  + blackArrMidg
                  + blackKsaMidg;

    }

    private static void evaluateBlackEndgScore() {

        blackMatEndg = blackPawnsMatEndg
                     + blackKnightsMatEndg
                     + blackBishopsMatEndg
                     + blackRooksMatEndg
                     + blackQueensMatEndg
                     + blackKingMatEndg
                     + blackLonelyKnightEndg
                     + blackLonelyBishopEndg
                     + blackLonelyRookEndg
                     + blackSameFBishopsEndg
                     + blackNoPawnsEndg;

        blackPstEndg = blackIsolaniPawnsEndg;

        blackMobEndg = 0;

        blackArrEndg = blackPawnsArrEndg
                     + blackKnightsArrEndg
                     + blackBishopsArrEndg
                     + blackRooksArrEndg
                     + blackQueensArrEndg
                     + blackKingArrEndg
                     + blackPassedPawnsEndg;

        blackKsaEndg = blackAttackedKingEndg
                     + blackAttackedKRing1Endg
                     + blackAttackedKRing2Endg;

        blackEndg = blackMatEndg
                  + blackPstEndg
                  + blackMobEndg
                  + blackArrEndg
                  + blackKsaEndg;

    }

}
