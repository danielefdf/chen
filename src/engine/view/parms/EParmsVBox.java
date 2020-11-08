package engine.view.parms;

import engine.model.Engine;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class EParmsVBox extends VBox {

    public static final int LABEL_MIN_WIDTH = 170;

    private Engine engine;

    private EParmTitledPane searchOptionsPTP = new EParmTitledPane("search options");
        private ESpinnerParmHBox movesToGoSPHB;
        private ESpinnerParmHBox searchDepthSPHB;
        private ECheckParmHBox   alphaBetaPruningCPHB;
        private ECheckParmHBox   delayedLegalityCheckCPHB;
        private ESpinnerParmHBox sortedMovesNumberSPHB;
        private ECheckParmHBox   searchCheckIncrementCPHB;
        private ECheckParmHBox   quiescentPosSearchCPHB;
        private ESpinnerParmHBox qSearchAddedDepthSPHB;
        private ECheckParmHBox   iterDeepeningSearchCPHB;
        private ECheckParmHBox   principalVarSearchCPHB;
        private ECheckParmHBox   lateMoveReductionCPHB;
        private ESpinnerParmHBox lateMoveSubtrDepthSPHB;
        private ECheckParmHBox   killerHeuristicCPHB;
        private ECheckParmHBox   historyHeuristicCPHB;
        private ECheckParmHBox   transpositionsMapCPHB;
    private EParmTitledPane materialScoresPTP = new EParmTitledPane("material scores");
        private ESpinnerParmHBox matPawnScoreSPHB;
        private ESpinnerParmHBox matKnightScoreSPHB;
        private ESpinnerParmHBox matBishopScoreSPHB;
        private ESpinnerParmHBox matRookScoreSPHB;
        private ESpinnerParmHBox matQueenScoreSPHB;
        private ESpinnerParmHBox matKingScoreSPHB;
        private ESpinnerParmHBox matShortCgScoreSPHB;
        private ESpinnerParmHBox matLongCgScoreSPHB;
        private ESpinnerParmHBox matOnly1KnightScoreSPHB;
        private ESpinnerParmHBox matOnly1BishopScoreSPHB;
        private ESpinnerParmHBox matOnly1RookScoreSPHB;
        private ESpinnerParmHBox matSameFBishopsScoreSPHB;
        private ESpinnerParmHBox matNoPawnsScoreSPHB;
    private EParmTitledPane kingSafetyScoresPTP = new EParmTitledPane("king safety scores");
        private ESpinnerParmHBox ksaShieldPawnLackScoreSPHB;
        private ESpinnerParmHBox ksaKingAttackScoreSPHB;
        private ESpinnerParmHBox ksaKRing1AttackScoreSPHB;
        private ESpinnerParmHBox ksaKRing2AttackScoreSPHB;
        private ESpinnerParmHBox ksaKingUncastledMaxFullmovesSPHB;
    private EParmTitledPane arrangementPawnScoresPTP = new EParmTitledPane("arrangement - pawns");
        private EBoardParmsHBox arrWhitePawnScoresListBPHB;
    private EParmTitledPane arrangementKnightScoresPTP = new EParmTitledPane("arrangement - knights");
        private EBoardParmsHBox arrWhiteKnightScoresListBPHB;
    private EParmTitledPane arrangementBishopScoresPTP = new EParmTitledPane("arrangement - bishops");
        private EBoardParmsHBox arrWhiteBishopScoresListBPHB;
    private EParmTitledPane arrangementRookScoresPTP = new EParmTitledPane("arrangement - rooks");
        private EBoardParmsHBox arrWhiteRookScoresListBPHB;
    private EParmTitledPane arrangementQueenScoresPTP = new EParmTitledPane("arrangement - queens");
        private EBoardParmsHBox arrWhiteQueenScoresListBPHB;
    private EParmTitledPane arrangementKingScoresPTP = new EParmTitledPane("arrangement - king");
        private EBoardParmsHBox arrWhiteKingScoresListBPHB;
    private EParmTitledPane arrangementScoresPTP = new EParmTitledPane("arrangement - other");
        private ESpinnerParmHBox arrControlledSquareDivisorSPHB;
        private ESpinnerParmHBox arrRookOnOpenFileScoreSPHB;
        private ESpinnerParmHBox arrQueenOnOpenFileScoreSPHB;
        private ESpinnerParmHBox arrRookOnSemiOpenFileScoreSPHB;
        private ESpinnerParmHBox arrQueenOnSemiOpenFileScoreSPHB;
        private ESpinnerParmHBox arrCentreControlScoreSPHB;
        private ESpinnerParmHBox arrCRingControlScoreSPHB;
    private EParmTitledPane pawnStructureScoresPTP = new EParmTitledPane("pawn structure");
        private ESpinnerParmHBox pstDispersionCoefficientSPHB;
        private ESpinnerParmHBox pstDistorsionCoefficientSPHB;
        private ESpinnerParmHBox pstIsolaniPawnsScoreSPHB;
        private ESpinnerParmHBox pstPassedPawnsScoreSPHB;
    private EParmTitledPane mobilityScoresPTP = new EParmTitledPane("mobility");
        private ESpinnerParmHBox mobShortCgScoreSPHB;
        private ESpinnerParmHBox mobLongCgScoreSPHB;
        private ESpinnerParmHBox mobPieceNumeratrSPHB;
        private ESpinnerParmHBox mobTargetAttackDenomntrSPHB;
        private ESpinnerParmHBox mobTargetProtectnDenomntrSPHB;

    private EParmsVBoxListener eParmsVBoxListener;

    public Engine getEngine() {
        return engine;
    }

    public void setEngineParmsVBoxListener(EParmsVBoxListener newEParmsVBoxListener) {
        eParmsVBoxListener = newEParmsVBoxListener;
    }

    public EParmsVBox(Engine newEngine) {

        engine = newEngine;

        newEngineParmsVBox();

    }

    public void resetEngineParmsVBox(Engine newEngine) {

        engine = newEngine;

        resetEngineParmsVBox();

    }

    private void newEngineParmsVBox() {

        newSearchOptionsTitledPane();
        newMaterialScoresTitledPane();
        newKingSafetyScoresTitledPane();
        newArrangementPawnScoresTitledPane();
        newArrangementKnightScoresTitledPane();
        newArrangementBishopScoresTitledPane();
        newArrangementRookScoresTitledPane();
        newArrangementQueenScoresTitledPane();
        newArrangementKingScoresTitledPane();
        newArrangementScoresTitledPane();
        newPawnStructureScoresTitledPane();
        newMobilityScoresTitledPane();

        setAlignment(Pos.TOP_LEFT);

        setEngineParmsVBox();

    }

    private void setEngineParmsVBox() {

        getChildren().clear();

        getChildren().add(searchOptionsPTP);
        getChildren().add(materialScoresPTP);
        getChildren().add(kingSafetyScoresPTP);
        getChildren().add(arrangementPawnScoresPTP);
        getChildren().add(arrangementKnightScoresPTP);
        getChildren().add(arrangementBishopScoresPTP);
        getChildren().add(arrangementRookScoresPTP);
        getChildren().add(arrangementQueenScoresPTP);
        getChildren().add(arrangementKingScoresPTP);
        getChildren().add(arrangementScoresPTP);
        getChildren().add(pawnStructureScoresPTP);
        getChildren().add(mobilityScoresPTP);

    }

    private void resetEngineParmsVBox() {

        resetSearchOptionsTitledPane();
        resetMaterialScoresTitledPane();
        resetKingSafetyScoresTitledPane();
        resetArrangementPawnScoresTitledPane();
        resetArrangementKnightScoresTitledPane();
        resetArrangementBishopScoresTitledPane();
        resetArrangementRookScoresTitledPane();
        resetArrangementQueenScoresTitledPane();
        resetArrangementKingScoresTitledPane();
        resetArrangementScoresTitledPane();
        resetPawnStructureScoresTitledPane();
        resetMobilityScoresTitledPane();

    }

    private void newSearchOptionsTitledPane() {

        movesToGoSPHB = new ESpinnerParmHBox("moves to go", engine.movesToGo, null, () -> {
            engine.movesToGo = movesToGoSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        searchDepthSPHB = new ESpinnerParmHBox("search depth", engine.searchDepth, null, () -> {
            engine.searchDepth = searchDepthSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        alphaBetaPruningCPHB = new ECheckParmHBox("alpha-beta pruning", engine.alphaBetaPruning, () -> {
            engine.alphaBetaPruning = alphaBetaPruningCPHB.getCheckBox().isSelected();
            checkOnEngineParmChanged();
        });
        delayedLegalityCheckCPHB = new ECheckParmHBox("delayedLegalityCheck", engine.delayedLegalityCheck, () -> {
            engine.delayedLegalityCheck = delayedLegalityCheckCPHB.getCheckBox().isSelected();
            checkOnEngineParmChanged();
        });
        sortedMovesNumberSPHB = new ESpinnerParmHBox("sorted moves", engine.sortedMovesNumber, null, () -> {
            engine.sortedMovesNumber = sortedMovesNumberSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        searchCheckIncrementCPHB = new ECheckParmHBox("search check increment", engine.searchCheckIncrement, () -> {
            engine.searchCheckIncrement = searchCheckIncrementCPHB.getCheckBox().isSelected();
            checkOnEngineParmChanged();
        });
        quiescentPosSearchCPHB = new ECheckParmHBox("quiescent pos search", engine.quiescentPosSearch, () -> {
            engine.quiescentPosSearch = quiescentPosSearchCPHB.getCheckBox().isSelected();
            checkOnEngineParmChanged();
        });
        qSearchAddedDepthSPHB = new ESpinnerParmHBox("q-search added depth", engine.qSearchAddedDepth, null, () -> {
            engine.qSearchAddedDepth = qSearchAddedDepthSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        iterDeepeningSearchCPHB = new ECheckParmHBox("iter deepening search", engine.iterDeepeningSearch, () -> {
            engine.iterDeepeningSearch = iterDeepeningSearchCPHB.getCheckBox().isSelected();
            checkOnEngineParmChanged();
        });
        principalVarSearchCPHB = new ECheckParmHBox("principal var search", engine.principalVarSearch, () -> {
            engine.principalVarSearch = principalVarSearchCPHB.getCheckBox().isSelected();
            checkOnEngineParmChanged();
        });
        lateMoveReductionCPHB = new ECheckParmHBox("late move reduction", engine.lateMoveReduction, () -> {
            engine.lateMoveReduction = lateMoveReductionCPHB.getCheckBox().isSelected();
            checkOnEngineParmChanged();
        });
        lateMoveSubtrDepthSPHB = new ESpinnerParmHBox("late move subtr depth", engine.lateMoveSubtrDepth, null, () -> {
            engine.lateMoveSubtrDepth = lateMoveSubtrDepthSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        killerHeuristicCPHB = new ECheckParmHBox("killer heuristic", engine.killerHeuristic, () -> {
            engine.killerHeuristic = killerHeuristicCPHB.getCheckBox().isSelected();
            checkOnEngineParmChanged();
        });
        historyHeuristicCPHB = new ECheckParmHBox("history heuristic", engine.historyHeuristic, () -> {
            engine.historyHeuristic = historyHeuristicCPHB.getCheckBox().isSelected();
            checkOnEngineParmChanged();
        });
        transpositionsMapCPHB = new ECheckParmHBox("transpositions map", engine.transpositionsMap, () -> {
            engine.transpositionsMap = transpositionsMapCPHB.getCheckBox().isSelected();
            checkOnEngineParmChanged();
        });

        searchOptionsPTP.clear();

        int r = -1;
        searchOptionsPTP.add(movesToGoSPHB,            0, ++r);
        searchOptionsPTP.add(searchDepthSPHB,          0, ++r);
        searchOptionsPTP.add(alphaBetaPruningCPHB,     0, ++r);
        searchOptionsPTP.add(delayedLegalityCheckCPHB, 0, ++r);
        searchOptionsPTP.add(sortedMovesNumberSPHB,    0, ++r);
        searchOptionsPTP.add(searchCheckIncrementCPHB, 0, ++r);
        searchOptionsPTP.add(quiescentPosSearchCPHB,   0, ++r);
        searchOptionsPTP.add(qSearchAddedDepthSPHB,    0, ++r);
        searchOptionsPTP.add(iterDeepeningSearchCPHB,  0, ++r);
        searchOptionsPTP.add(principalVarSearchCPHB,   0, ++r);
        searchOptionsPTP.add(lateMoveReductionCPHB,    0, ++r);
        searchOptionsPTP.add(lateMoveSubtrDepthSPHB,   0, ++r);
        searchOptionsPTP.add(killerHeuristicCPHB,      0, ++r);
        searchOptionsPTP.add(historyHeuristicCPHB,     0, ++r);
        searchOptionsPTP.add(transpositionsMapCPHB,    0, ++r);

    }

    private void resetSearchOptionsTitledPane() {

        movesToGoSPHB.resetSpinnerParmHBox(          engine.movesToGo,             null);
        searchDepthSPHB.resetSpinnerParmHBox(        engine.searchDepth,           null);
        alphaBetaPruningCPHB.resetCheckParmHBox(     engine.alphaBetaPruning);
        delayedLegalityCheckCPHB.resetCheckParmHBox( engine.delayedLegalityCheck);
        sortedMovesNumberSPHB.resetSpinnerParmHBox(  engine.sortedMovesNumber,     null);
        searchCheckIncrementCPHB.resetCheckParmHBox( engine.searchCheckIncrement);
        quiescentPosSearchCPHB.resetCheckParmHBox(   engine.quiescentPosSearch);
        qSearchAddedDepthSPHB.resetSpinnerParmHBox(  engine.qSearchAddedDepth,     null);
        iterDeepeningSearchCPHB.resetCheckParmHBox(  engine.iterDeepeningSearch);
        principalVarSearchCPHB.resetCheckParmHBox(   engine.principalVarSearch);
        lateMoveReductionCPHB.resetCheckParmHBox(    engine.lateMoveReduction);
        lateMoveSubtrDepthSPHB.resetSpinnerParmHBox( engine.lateMoveSubtrDepth,    null);
        killerHeuristicCPHB.resetCheckParmHBox(      engine.killerHeuristic);
        historyHeuristicCPHB.resetCheckParmHBox(     engine.historyHeuristic);
        transpositionsMapCPHB.resetCheckParmHBox(    engine.transpositionsMap);

    }

    private void newMaterialScoresTitledPane() {

        matPawnScoreSPHB = new ESpinnerParmHBox("pawn", engine.matPawnMidgScore, engine.matPawnEndgScore, () -> {
            engine.matPawnMidgScore = matPawnScoreSPHB.getMidgScoreSpinner().getValue();
            engine.matPawnEndgScore = matPawnScoreSPHB.getEndgScoreSpinner().getValue();
            resetAllPiecesMidgScore();
            checkOnEngineParmChanged();
        });
        matKnightScoreSPHB = new ESpinnerParmHBox("knight", engine.matKnightMidgScore, engine.matKnightEndgScore, () -> {
            engine.matKnightMidgScore = matKnightScoreSPHB.getMidgScoreSpinner().getValue();
            engine.matKnightEndgScore = matKnightScoreSPHB.getEndgScoreSpinner().getValue();
            resetAllPiecesMidgScore();
            checkOnEngineParmChanged();
        });
        matBishopScoreSPHB = new ESpinnerParmHBox("bishop", engine.matBishopMidgScore, engine.matBishopEndgScore, () -> {
            engine.matBishopMidgScore = matBishopScoreSPHB.getMidgScoreSpinner().getValue();
            engine.matBishopEndgScore = matBishopScoreSPHB.getEndgScoreSpinner().getValue();
            resetAllPiecesMidgScore();
            checkOnEngineParmChanged();
        });
        matRookScoreSPHB = new ESpinnerParmHBox("rook", engine.matRookMidgScore, engine.matRookEndgScore, () -> {
            engine.matRookMidgScore = matRookScoreSPHB.getMidgScoreSpinner().getValue();
            engine.matRookEndgScore = matRookScoreSPHB.getEndgScoreSpinner().getValue();
            resetAllPiecesMidgScore();
            checkOnEngineParmChanged();
        });
        matQueenScoreSPHB = new ESpinnerParmHBox("queen", engine.matQueenMidgScore, engine.matQueenEndgScore, () -> {
            engine.matQueenMidgScore = matQueenScoreSPHB.getMidgScoreSpinner().getValue();
            engine.matQueenEndgScore = matQueenScoreSPHB.getEndgScoreSpinner().getValue();
            resetAllPiecesMidgScore();
            checkOnEngineParmChanged();
        });
        matKingScoreSPHB = new ESpinnerParmHBox("king (mobility)", engine.matKingMidgScore, null, () -> {
            engine.matKingMidgScore = matKingScoreSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        matShortCgScoreSPHB = new ESpinnerParmHBox("short castling", engine.matShortCgMidgScore, null, () -> {
            engine.matShortCgMidgScore = matShortCgScoreSPHB.getMidgScoreSpinner().getValue();
            resetAllPiecesMidgScore();
            checkOnEngineParmChanged();
        });
        matLongCgScoreSPHB = new ESpinnerParmHBox("long castling", engine.matLongCgMidgScore, null, () -> {
            engine.matLongCgMidgScore = matLongCgScoreSPHB.getMidgScoreSpinner().getValue();
            resetAllPiecesMidgScore();
            checkOnEngineParmChanged();
        });
        matOnly1KnightScoreSPHB = new ESpinnerParmHBox("only 1 knight", engine.matLonelyKnightMidgScore, engine.matLonelyKnightEndgScore, () -> {
            engine.matLonelyKnightMidgScore = matOnly1KnightScoreSPHB.getMidgScoreSpinner().getValue();
            engine.matLonelyKnightEndgScore = matOnly1KnightScoreSPHB.getEndgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        matOnly1BishopScoreSPHB = new ESpinnerParmHBox("only 1 bishop", engine.matLonelyBishopMidgScore, engine.matLonelyBishopEndgScore, () -> {
            engine.matLonelyBishopMidgScore = matOnly1BishopScoreSPHB.getMidgScoreSpinner().getValue();
            engine.matLonelyBishopEndgScore = matOnly1BishopScoreSPHB.getEndgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        matOnly1RookScoreSPHB = new ESpinnerParmHBox("only 1 rook", engine.matLonelyRookMidgScore, engine.matLonelyRookEndgScore, () -> {
            engine.matLonelyRookMidgScore = matOnly1RookScoreSPHB.getMidgScoreSpinner().getValue();
            engine.matLonelyRookEndgScore = matOnly1RookScoreSPHB.getEndgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        matSameFBishopsScoreSPHB = new ESpinnerParmHBox("same field bishops", engine.matSameFBishopsMidgScore, engine.matSameFBishopsEndgScore, () -> {
            engine.matSameFBishopsMidgScore = matSameFBishopsScoreSPHB.getMidgScoreSpinner().getValue();
            engine.matSameFBishopsEndgScore = matSameFBishopsScoreSPHB.getEndgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        matNoPawnsScoreSPHB = new ESpinnerParmHBox("no pawns", engine.matNoPawnsMidgScore, engine.matNoPawnsEndgScore, () -> {
            engine.matNoPawnsMidgScore = matNoPawnsScoreSPHB.getMidgScoreSpinner().getValue();
            engine.matNoPawnsEndgScore = matNoPawnsScoreSPHB.getEndgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });

        materialScoresPTP.clear();

        int r = -1;
        materialScoresPTP.add(matPawnScoreSPHB,         0, ++r);
        materialScoresPTP.add(matKnightScoreSPHB,       0, ++r);
        materialScoresPTP.add(matBishopScoreSPHB,       0, ++r);
        materialScoresPTP.add(matRookScoreSPHB,         0, ++r);
        materialScoresPTP.add(matQueenScoreSPHB,        0, ++r);
        materialScoresPTP.add(matKingScoreSPHB,         0, ++r);
        materialScoresPTP.add(matShortCgScoreSPHB,      0, ++r);
        materialScoresPTP.add(matLongCgScoreSPHB,       0, ++r);
        materialScoresPTP.add(matOnly1KnightScoreSPHB,  0, ++r);
        materialScoresPTP.add(matOnly1BishopScoreSPHB,  0, ++r);
        materialScoresPTP.add(matOnly1RookScoreSPHB,    0, ++r);
        materialScoresPTP.add(matSameFBishopsScoreSPHB, 0, ++r);
        materialScoresPTP.add(matNoPawnsScoreSPHB,      0, ++r);

    }

    private void resetMaterialScoresTitledPane() {

        matPawnScoreSPHB.resetSpinnerParmHBox(         engine.matPawnMidgScore,         engine.matPawnEndgScore);
        matKnightScoreSPHB.resetSpinnerParmHBox(       engine.matKnightMidgScore,       engine.matKnightEndgScore);
        matBishopScoreSPHB.resetSpinnerParmHBox(       engine.matBishopMidgScore,       engine.matBishopEndgScore);
        matRookScoreSPHB.resetSpinnerParmHBox(         engine.matRookMidgScore,         engine.matRookEndgScore);
        matQueenScoreSPHB.resetSpinnerParmHBox(        engine.matQueenMidgScore,        engine.matQueenEndgScore);
        matKingScoreSPHB.resetSpinnerParmHBox(         engine.matKingMidgScore,         null);
        matShortCgScoreSPHB.resetSpinnerParmHBox(      engine.matShortCgMidgScore,      null);
        matLongCgScoreSPHB.resetSpinnerParmHBox(       engine.matLongCgMidgScore,       null);
        matOnly1KnightScoreSPHB.resetSpinnerParmHBox(  engine.matLonelyKnightMidgScore, engine.matLonelyKnightEndgScore);
        matOnly1BishopScoreSPHB.resetSpinnerParmHBox(  engine.matLonelyBishopMidgScore, engine.matLonelyBishopEndgScore);
        matOnly1RookScoreSPHB.resetSpinnerParmHBox(    engine.matLonelyRookMidgScore,   engine.matLonelyRookEndgScore);
        matSameFBishopsScoreSPHB.resetSpinnerParmHBox( engine.matSameFBishopsMidgScore, engine.matSameFBishopsEndgScore);
        matNoPawnsScoreSPHB.resetSpinnerParmHBox(      engine.matNoPawnsMidgScore,      engine.matNoPawnsEndgScore);

    }

    private void resetAllPiecesMidgScore() {

        engine.matAllPiecesMidgScore = 16 * engine.matPawnMidgScore
                                     +  4 * engine.matKnightMidgScore
                                     +  4 * engine.matBishopMidgScore
                                     +  4 * engine.matRookMidgScore
                                     +  2 * engine.matQueenMidgScore
                                     +  2 * engine.matShortCgMidgScore
                                     +  2 * engine.matLongCgMidgScore
                                     ;

    }

    private void newKingSafetyScoresTitledPane() {

        ksaShieldPawnLackScoreSPHB = new ESpinnerParmHBox("shield pawn lack", engine.ksaShieldPawnLackScore, null, () -> {
            engine.ksaShieldPawnLackScore = ksaShieldPawnLackScoreSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        ksaKingAttackScoreSPHB = new ESpinnerParmHBox("king attacked", engine.ksaKingAttackMidgScore, engine.ksaKingAttackEndgScore, () -> {
            engine.ksaKingAttackMidgScore = ksaKingAttackScoreSPHB.getMidgScoreSpinner().getValue();
            engine.ksaKingAttackEndgScore = ksaKingAttackScoreSPHB.getEndgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        ksaKRing1AttackScoreSPHB = new ESpinnerParmHBox("king ring 1 attacked", engine.ksaKRing1AttackMidgScore, engine.ksaKRing1AttackEndgScore, () -> {
            engine.ksaKRing1AttackMidgScore = ksaKRing1AttackScoreSPHB.getMidgScoreSpinner().getValue();
            engine.ksaKRing1AttackEndgScore = ksaKRing1AttackScoreSPHB.getEndgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        ksaKRing2AttackScoreSPHB = new ESpinnerParmHBox("king ring 2 attacked", engine.ksaKRing2AttackMidgScore, engine.ksaKRing2AttackEndgScore, () -> {
            engine.ksaKRing2AttackMidgScore = ksaKRing2AttackScoreSPHB.getMidgScoreSpinner().getValue();
            engine.ksaKRing2AttackEndgScore = ksaKRing2AttackScoreSPHB.getEndgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        ksaKingUncastledMaxFullmovesSPHB = new ESpinnerParmHBox("K uncastled max fullmoves", engine.ksaKingUncastledMaxFullmoves, null, () -> {
            engine.ksaKingUncastledMaxFullmoves = ksaKingUncastledMaxFullmovesSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });

        kingSafetyScoresPTP.clear();

        int r = -1;
        kingSafetyScoresPTP.add(ksaShieldPawnLackScoreSPHB,       0, ++r);
        kingSafetyScoresPTP.add(ksaKingAttackScoreSPHB,         0, ++r);
        kingSafetyScoresPTP.add(ksaKRing1AttackScoreSPHB,       0, ++r);
        kingSafetyScoresPTP.add(ksaKRing2AttackScoreSPHB,       0, ++r);
        kingSafetyScoresPTP.add(ksaKingUncastledMaxFullmovesSPHB, 0, ++r);

    }

    private void resetKingSafetyScoresTitledPane() {

        ksaShieldPawnLackScoreSPHB.resetSpinnerParmHBox(       engine.ksaShieldPawnLackScore,       null);
        ksaKingAttackScoreSPHB.resetSpinnerParmHBox(         engine.ksaKingAttackMidgScore,     engine.ksaKingAttackEndgScore);
        ksaKRing1AttackScoreSPHB.resetSpinnerParmHBox(       engine.ksaKRing1AttackMidgScore,   engine.ksaKRing1AttackEndgScore);
        ksaKRing2AttackScoreSPHB.resetSpinnerParmHBox(       engine.ksaKRing2AttackMidgScore,   engine.ksaKRing2AttackEndgScore);
        ksaKingUncastledMaxFullmovesSPHB.resetSpinnerParmHBox( engine.ksaKingUncastledMaxFullmoves, null);

    }

    private void newArrangementPawnScoresTitledPane() {

        arrWhitePawnScoresListBPHB = new EBoardParmsHBox("pawn", null, engine.arrWhitePawnMidgScoresList, engine.arrWhitePawnEndgScoresList, () -> {
            engine.arrWhitePawnMidgScoresList = arrWhitePawnScoresListBPHB.getBoardMidgScoresList();
            engine.arrBlackPawnMidgScoresList = Engine.verticalMirrorArray(engine.arrWhitePawnMidgScoresList);
            checkOnEngineParmChanged();
        });

        arrangementPawnScoresPTP.clear();

        int r = -1;
        arrangementPawnScoresPTP.add(arrWhitePawnScoresListBPHB, 0, ++r);

    }

    private void resetArrangementPawnScoresTitledPane() {

        arrWhitePawnScoresListBPHB.resetBoardParmsHBox(null, engine.arrWhitePawnMidgScoresList, engine.arrWhitePawnEndgScoresList);

    }

    private void newArrangementKnightScoresTitledPane() {

        arrWhiteKnightScoresListBPHB = new EBoardParmsHBox("knight", null, engine.arrWhiteKnightMidgScoresList, null, () -> {
            engine.arrWhiteKnightMidgScoresList = arrWhiteKnightScoresListBPHB.getBoardMidgScoresList();
            engine.arrBlackKnightMidgScoresList = Engine.verticalMirrorArray(engine.arrWhiteKnightMidgScoresList);
            checkOnEngineParmChanged();
        });

        arrangementKnightScoresPTP.clear();

        int r = -1;
        arrangementKnightScoresPTP.add(arrWhiteKnightScoresListBPHB, 0, ++r);

    }

    private void resetArrangementKnightScoresTitledPane() {

        arrWhiteKnightScoresListBPHB.resetBoardParmsHBox(null, engine.arrWhiteKnightMidgScoresList, null);

    }

    private void newArrangementBishopScoresTitledPane() {

        arrWhiteBishopScoresListBPHB = new EBoardParmsHBox("bishop", null, engine.arrWhiteBishopMidgScoresList, null, () -> {
            engine.arrWhiteBishopMidgScoresList = arrWhiteBishopScoresListBPHB.getBoardMidgScoresList();
            engine.arrBlackBishopMidgScoresList = Engine.verticalMirrorArray(engine.arrWhiteBishopMidgScoresList);
            checkOnEngineParmChanged();
        });

        arrangementBishopScoresPTP.clear();

        int r = -1;
        arrangementBishopScoresPTP.add(arrWhiteBishopScoresListBPHB, 0, ++r);

    }

    private void resetArrangementBishopScoresTitledPane() {

        arrWhiteBishopScoresListBPHB.resetBoardParmsHBox(null, engine.arrWhiteBishopMidgScoresList, null);

    }

    private void newArrangementRookScoresTitledPane() {

        arrWhiteRookScoresListBPHB = new EBoardParmsHBox("rook", null, engine.arrWhiteRookMidgScoresList, engine.arrWhiteRookEndgScoresList, () -> {
            engine.arrWhiteRookMidgScoresList = arrWhiteRookScoresListBPHB.getBoardMidgScoresList();
            engine.arrBlackRookMidgScoresList = Engine.verticalMirrorArray(engine.arrWhiteRookMidgScoresList);
            engine.arrWhiteRookEndgScoresList = arrWhiteRookScoresListBPHB.getBoardEndgScoresList();
            engine.arrBlackRookEndgScoresList = Engine.verticalMirrorArray(engine.arrWhiteRookEndgScoresList);
            checkOnEngineParmChanged();
        });

        arrangementRookScoresPTP.clear();

        int r = -1;
        arrangementRookScoresPTP.add(arrWhiteRookScoresListBPHB, 0, ++r);

    }

    private void resetArrangementRookScoresTitledPane() {

        arrWhiteRookScoresListBPHB.resetBoardParmsHBox(null, engine.arrWhiteRookMidgScoresList, engine.arrWhiteRookEndgScoresList);

    }

    private void newArrangementQueenScoresTitledPane() {

        arrWhiteQueenScoresListBPHB = new EBoardParmsHBox("queen", null, engine.arrWhiteQueenMidgScoresList, engine.arrWhiteQueenEndgScoresList, () -> {
            engine.arrWhiteQueenMidgScoresList = arrWhiteQueenScoresListBPHB.getBoardMidgScoresList();
            engine.arrBlackQueenMidgScoresList = Engine.verticalMirrorArray(engine.arrWhiteQueenMidgScoresList);
            engine.arrWhiteQueenEndgScoresList = arrWhiteQueenScoresListBPHB.getBoardEndgScoresList();
            engine.arrBlackQueenEndgScoresList = Engine.verticalMirrorArray(engine.arrWhiteQueenEndgScoresList);
            checkOnEngineParmChanged();
        });

        arrangementQueenScoresPTP.clear();

        int r = -1;
        arrangementQueenScoresPTP.add(arrWhiteQueenScoresListBPHB, 0, ++r);

    }

    private void resetArrangementQueenScoresTitledPane() {

        arrWhiteQueenScoresListBPHB.resetBoardParmsHBox(null, engine.arrWhiteQueenMidgScoresList, engine.arrWhiteQueenEndgScoresList);

    }

    private void newArrangementKingScoresTitledPane() {

        arrWhiteKingScoresListBPHB = new EBoardParmsHBox("king", null, engine.arrWhiteKingMidgScoresList, engine.arrWhiteKingEndgScoresList, () -> {
            engine.arrWhiteKingMidgScoresList = arrWhiteKingScoresListBPHB.getBoardMidgScoresList();
            engine.arrBlackKingMidgScoresList = Engine.verticalMirrorArray(engine.arrWhiteKingMidgScoresList);
            engine.arrWhiteKingEndgScoresList = arrWhiteKingScoresListBPHB.getBoardEndgScoresList();
            engine.arrBlackKingEndgScoresList = Engine.verticalMirrorArray(engine.arrWhiteKingEndgScoresList);
            checkOnEngineParmChanged();
        });

        arrangementKingScoresPTP.clear();

        int r = -1;
        arrangementKingScoresPTP.add(arrWhiteKingScoresListBPHB, 0, ++r);

    }

    private void resetArrangementKingScoresTitledPane() {

        arrWhiteKingScoresListBPHB.resetBoardParmsHBox(null, engine.arrWhiteKingMidgScoresList, engine.arrWhiteKingEndgScoresList);

    }

    private void newArrangementScoresTitledPane() {

        arrControlledSquareDivisorSPHB = new ESpinnerParmHBox("controlled square divisor", engine.arrOnCtrlledSquareDivisor, null, () -> {
            engine.arrOnCtrlledSquareDivisor = arrControlledSquareDivisorSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        arrRookOnOpenFileScoreSPHB = new ESpinnerParmHBox("rook on open file", engine.arrRookOnOpenFileScore, null, () -> {
            engine.arrRookOnOpenFileScore = arrRookOnOpenFileScoreSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        arrQueenOnOpenFileScoreSPHB = new ESpinnerParmHBox("queen on open file", engine.arrQueenOnOpenFileScore, null, () -> {
            engine.arrQueenOnOpenFileScore = arrQueenOnOpenFileScoreSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        arrRookOnSemiOpenFileScoreSPHB = new ESpinnerParmHBox("rook on semi-open file", engine.arrRookOnSemiOFileScore, null, () -> {
            engine.arrRookOnSemiOFileScore = arrRookOnSemiOpenFileScoreSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        arrQueenOnSemiOpenFileScoreSPHB = new ESpinnerParmHBox("queen on semi-open file", engine.arrQueenOnSemiOFileScore, null, () -> {
            engine.arrQueenOnSemiOFileScore = arrQueenOnSemiOpenFileScoreSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        arrCentreControlScoreSPHB = new ESpinnerParmHBox("centre control", engine.arrCentreControlScore, null, () -> {
            engine.arrCentreControlScore = arrCentreControlScoreSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        arrCRingControlScoreSPHB = new ESpinnerParmHBox("center ring control", engine.arrCRingControlScore, null, () -> {
            engine.arrCRingControlScore = arrCRingControlScoreSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });

        arrangementScoresPTP.clear();

        int r = -1;
        arrangementScoresPTP.add(arrControlledSquareDivisorSPHB,  0, ++r);
        arrangementScoresPTP.add(arrRookOnOpenFileScoreSPHB,      0, ++r);
        arrangementScoresPTP.add(arrQueenOnOpenFileScoreSPHB,     0, ++r);
        arrangementScoresPTP.add(arrRookOnSemiOpenFileScoreSPHB,  0, ++r);
        arrangementScoresPTP.add(arrQueenOnSemiOpenFileScoreSPHB, 0, ++r);
        arrangementScoresPTP.add(arrCentreControlScoreSPHB,       0, ++r);
        arrangementScoresPTP.add(arrCRingControlScoreSPHB,        0, ++r);

    }

    private void resetArrangementScoresTitledPane() {

        arrControlledSquareDivisorSPHB.resetSpinnerParmHBox(  engine.arrOnCtrlledSquareDivisor,   null);
        arrRookOnOpenFileScoreSPHB.resetSpinnerParmHBox(      engine.arrRookOnOpenFileScore,      null);
        arrQueenOnOpenFileScoreSPHB.resetSpinnerParmHBox(     engine.arrQueenOnOpenFileScore,     null);
        arrRookOnSemiOpenFileScoreSPHB.resetSpinnerParmHBox(  engine.arrRookOnSemiOFileScore,     null);
        arrQueenOnSemiOpenFileScoreSPHB.resetSpinnerParmHBox( engine.arrQueenOnSemiOFileScore,    null);
        arrCentreControlScoreSPHB.resetSpinnerParmHBox(       engine.arrCentreControlScore,       null);
        arrCRingControlScoreSPHB.resetSpinnerParmHBox(        engine.arrCRingControlScore,        null);

    }

    private void newPawnStructureScoresTitledPane() {

        pstDispersionCoefficientSPHB = new ESpinnerParmHBox("dispersion coefficient", engine.pstDispersionCoefficient, null, () -> {
            engine.pstDispersionCoefficient = pstDispersionCoefficientSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        pstDistorsionCoefficientSPHB = new ESpinnerParmHBox("distorsion coefficient", engine.pstDistorsionCoefficient, null, () -> {
            engine.pstDistorsionCoefficient = pstDistorsionCoefficientSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        pstIsolaniPawnsScoreSPHB = new ESpinnerParmHBox("isolani pawn", engine.pstIsolaniPawnsMidgScore, engine.pstIsolaniPawnsEndgScore, () -> {
            engine.pstIsolaniPawnsMidgScore = pstIsolaniPawnsScoreSPHB.getMidgScoreSpinner().getValue();
            engine.pstIsolaniPawnsEndgScore = pstIsolaniPawnsScoreSPHB.getEndgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        pstPassedPawnsScoreSPHB = new ESpinnerParmHBox("passed pawn", engine.pstPassedPawnsMidgScore, engine.pstPassedPawnsEndgScore, () -> {
            engine.pstPassedPawnsMidgScore = pstPassedPawnsScoreSPHB.getMidgScoreSpinner().getValue();
            engine.pstPassedPawnsEndgScore = pstPassedPawnsScoreSPHB.getEndgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });

        pawnStructureScoresPTP.clear();

        int r = -1;
        pawnStructureScoresPTP.add(pstDispersionCoefficientSPHB, 0, ++r);
        pawnStructureScoresPTP.add(pstDistorsionCoefficientSPHB, 0, ++r);
        pawnStructureScoresPTP.add(pstIsolaniPawnsScoreSPHB,     0, ++r);
        pawnStructureScoresPTP.add(pstPassedPawnsScoreSPHB,      0, ++r);

    }

    private void resetPawnStructureScoresTitledPane() {

        pstDispersionCoefficientSPHB = new ESpinnerParmHBox("dispersion coefficient", engine.pstDispersionCoefficient, null, () -> {
            engine.pstDispersionCoefficient = pstDispersionCoefficientSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        pstDistorsionCoefficientSPHB = new ESpinnerParmHBox("distorsion coefficient", engine.pstDistorsionCoefficient, null, () -> {
            engine.pstDistorsionCoefficient = pstDistorsionCoefficientSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        pstIsolaniPawnsScoreSPHB = new ESpinnerParmHBox("isolani pawn", engine.pstIsolaniPawnsMidgScore, engine.pstIsolaniPawnsEndgScore, () -> {
            engine.pstIsolaniPawnsMidgScore = pstIsolaniPawnsScoreSPHB.getMidgScoreSpinner().getValue();
            engine.pstIsolaniPawnsEndgScore = pstIsolaniPawnsScoreSPHB.getEndgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        pstPassedPawnsScoreSPHB = new ESpinnerParmHBox("passed pawn", engine.pstPassedPawnsMidgScore, engine.pstPassedPawnsEndgScore, () -> {
            engine.pstPassedPawnsMidgScore = pstPassedPawnsScoreSPHB.getMidgScoreSpinner().getValue();
            engine.pstPassedPawnsEndgScore = pstPassedPawnsScoreSPHB.getEndgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });

    }

    private void newMobilityScoresTitledPane() {

        mobShortCgScoreSPHB = new ESpinnerParmHBox("short castling", engine.mobShortCgScore, null, () -> {
            engine.mobShortCgScore = mobShortCgScoreSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        mobLongCgScoreSPHB = new ESpinnerParmHBox("long castling", engine.mobLongCgScore, null, () -> {
            engine.mobLongCgScore = mobLongCgScoreSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        mobPieceNumeratrSPHB = new ESpinnerParmHBox("piece numerator", engine.mobPieceNumerator, null, () -> {
            engine.mobPieceNumerator = mobPieceNumeratrSPHB.getMidgScoreSpinner().getValue();
            engine.mobPawnStepScore       = (int) ((engine.mobPieceNumerator + Engine.ON_REALS) / engine.matPawnMidgScore);
            engine.mobPawnDoubleStepScore = 2 * engine.mobPawnStepScore;
            checkOnEngineParmChanged();
        });
        mobTargetAttackDenomntrSPHB = new ESpinnerParmHBox("target attack denominator", engine.mobTargetAttackDenomntr, null, () -> {
            engine.mobTargetAttackDenomntr = mobTargetAttackDenomntrSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });
        mobTargetProtectnDenomntrSPHB = new ESpinnerParmHBox("target protection denominator", engine.mobTargetProtectnDenomntr, null, () -> {
            engine.mobTargetProtectnDenomntr = mobTargetProtectnDenomntrSPHB.getMidgScoreSpinner().getValue();
            checkOnEngineParmChanged();
        });

        mobilityScoresPTP.clear();

        int r = -1;
        mobilityScoresPTP.add(mobShortCgScoreSPHB,           0, ++r);
        mobilityScoresPTP.add(mobLongCgScoreSPHB,            0, ++r);
        mobilityScoresPTP.add(mobPieceNumeratrSPHB,          0, ++r);
        mobilityScoresPTP.add(mobTargetAttackDenomntrSPHB,   0, ++r);
        mobilityScoresPTP.add(mobTargetProtectnDenomntrSPHB, 0, ++r);

    }

    private void resetMobilityScoresTitledPane() {

        mobShortCgScoreSPHB.resetSpinnerParmHBox(           engine.mobShortCgScore,           null);
        mobLongCgScoreSPHB.resetSpinnerParmHBox(            engine.mobLongCgScore,            null);
        mobPieceNumeratrSPHB.resetSpinnerParmHBox(          engine.mobPieceNumerator,         null);
        mobTargetAttackDenomntrSPHB.resetSpinnerParmHBox(   engine.mobTargetAttackDenomntr,   null);
        mobTargetProtectnDenomntrSPHB.resetSpinnerParmHBox( engine.mobTargetProtectnDenomntr, null);

    }

    private void checkOnEngineParmChanged() {
        try {

            if (eParmsVBoxListener != null) {
                eParmsVBoxListener.onEngineParmChanged(engine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void expandAll() {

        searchOptionsPTP           .setExpanded(true);
        materialScoresPTP          .setExpanded(true);
        kingSafetyScoresPTP        .setExpanded(true);
        arrangementPawnScoresPTP   .setExpanded(true);
        arrangementKnightScoresPTP .setExpanded(true);
        arrangementBishopScoresPTP .setExpanded(true);
        arrangementRookScoresPTP   .setExpanded(true);
        arrangementQueenScoresPTP  .setExpanded(true);
        arrangementKingScoresPTP   .setExpanded(true);
        arrangementScoresPTP       .setExpanded(true);
        pawnStructureScoresPTP     .setExpanded(true);
        mobilityScoresPTP          .setExpanded(true);

    }

    public void collapseAll() {

        searchOptionsPTP           .setExpanded(false);
        materialScoresPTP          .setExpanded(false);
        kingSafetyScoresPTP        .setExpanded(false);
        arrangementPawnScoresPTP   .setExpanded(false);
        arrangementKnightScoresPTP .setExpanded(false);
        arrangementBishopScoresPTP .setExpanded(false);
        arrangementRookScoresPTP   .setExpanded(false);
        arrangementQueenScoresPTP  .setExpanded(false);
        arrangementKingScoresPTP   .setExpanded(false);
        arrangementScoresPTP       .setExpanded(false);
        pawnStructureScoresPTP     .setExpanded(false);
        mobilityScoresPTP          .setExpanded(false);

    }

}
