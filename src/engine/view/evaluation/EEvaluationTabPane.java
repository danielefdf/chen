package engine.view.evaluation;

import engine.model.Engine;
import engine.model.EngineShow;
import engine.model.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import view.analysis.evaluation.BitBoardDisplayHBox;
import view.analysis.evaluation.BitBoardDisplaySidesHBox;
import view.analysis.evaluation.CounterDisplaySidesHBox;
import view.analysis.evaluation.EvaluationTitledPane;
import view.analysis.evaluation.FileSetDisplaySidesHBox;
import view.analysis.evaluation.MobilityDisplaySidesGridPane;
import view.analysis.evaluation.ScoreDisplayHBox;
import view.analysis.evaluation.ScoreDisplaySidesHBox;
import view.analysis.evaluation.TitledPanesToolBar;
import view.analysis.evaluation.TitledPanesToolBarListener;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class EEvaluationTabPane extends TabPane {

    private Engine engine;
    private Node node;

    private BorderPane boardBorderPane = new BorderPane();
        private TitledPanesToolBar boardTitledPanesToolBar;
        private ScrollPane boardScrollPane = new ScrollPane();
            private VBox boardVBox = new VBox();
                private EvaluationTitledPane boardMaterialETP = new EvaluationTitledPane("material");
                    private CounterDisplaySidesHBox pawnsCounterCDSHB;
                    private CounterDisplaySidesHBox knightsCounterCDSHB;
                    private CounterDisplaySidesHBox bishopsCounterCDSHB;
                    private CounterDisplaySidesHBox rooksCounterCDSHB;
                    private CounterDisplaySidesHBox queensCounterCDSHB;
                    private CounterDisplaySidesHBox wFBishopsCounterCDSHB;
                    private CounterDisplaySidesHBox bFBishopsCounterCDSHB;
                private EvaluationTitledPane boardPawnStructureETP = new EvaluationTitledPane("pawn structure");
                    private FileSetDisplaySidesHBox pawnsFsFSDDHB;
                    private CounterDisplaySidesHBox pawnIslandsCDSHB;
                    private BitBoardDisplaySidesHBox distorsionBbBBDSHB;
                private EvaluationTitledPane boardMobilityETP = new EvaluationTitledPane("mobility");
                    private BitBoardDisplaySidesHBox ctrlledSquaresBbBBDSHB;
                    private BitBoardDisplaySidesHBox pawnsMovesBbBBDSHB;
                    private BitBoardDisplaySidesHBox knigthsMovesBbBBDSHB;
                    private BitBoardDisplaySidesHBox bishopsMovesBbBBDSHB;
                    private BitBoardDisplaySidesHBox rooksMovesBbBBDSHB;
                    private BitBoardDisplaySidesHBox queensMovesBbBBDSHB;
                    private BitBoardDisplaySidesHBox kingMovesBbBBDSHB;
                private EvaluationTitledPane boardArrangementETP = new EvaluationTitledPane("arrangement");
                    private BitBoardDisplaySidesHBox bpbPawnsBbBBDSHB;
                    private BitBoardDisplaySidesHBox bpbKnightsBbBBDSHB;
                    private BitBoardDisplaySidesHBox bpbBishopsBbBBDSHB;
                    private BitBoardDisplaySidesHBox bpbRooksBbBBDSHB;
                    private BitBoardDisplaySidesHBox bpbQueensBbBBDSHB;
                    private BitBoardDisplaySidesHBox bpbKingsBbBBDSHB;
                    private BitBoardDisplayHBox openFilesBbBBDHB;
                    private BitBoardDisplaySidesHBox semiOFilesBbBBDSHB;
                private EvaluationTitledPane kingSafetyBbETP = new EvaluationTitledPane("king safety");
                    private BitBoardDisplaySidesHBox kRing1BbBBDSHB;
                    private BitBoardDisplaySidesHBox kRing2BbBBDSHB;
    private BorderPane scoresBorderPane = new BorderPane();
        private TitledPanesToolBar scoresTitledPanesToolBar;
        private ScrollPane scoresScrollPane = new ScrollPane();
            private VBox scoresVBox = new VBox();
                private EvaluationTitledPane materialScoresETP = new EvaluationTitledPane("material");
                    private ScoreDisplaySidesHBox pawnsScoreSDSHB;
                    private ScoreDisplaySidesHBox knightsScoreSDSHB;
                    private ScoreDisplaySidesHBox bishopsScoreSDSHB;
                    private ScoreDisplaySidesHBox rooksScoreSDSHB;
                    private ScoreDisplaySidesHBox queensScoreSDSHB;
                    private ScoreDisplaySidesHBox shortCgsScoreSDSHB;
                    private ScoreDisplaySidesHBox longCgsScoreSDSHB;
                    private ScoreDisplaySidesHBox lonelyKnightsScoreSDSHB;
                    private ScoreDisplaySidesHBox lonelyBishopsScoreSDSHB;
                    private ScoreDisplaySidesHBox lonelyRooksScoreSDSHB;
                    private ScoreDisplaySidesHBox sameFBishopsScoreSDSHB;
                    private ScoreDisplaySidesHBox noPawnsScoreSDSHB;
                    private ScoreDisplayHBox allPiecesMidgScoreSDHB;
                private EvaluationTitledPane pawnStructureScoresETP = new EvaluationTitledPane("pawn structure");
                    private ScoreDisplaySidesHBox dispersionSDSHB;
                    private ScoreDisplaySidesHBox distorsionSDSHB;
                    private ScoreDisplaySidesHBox isolaniPawnsScoreSDSHB;
                private EvaluationTitledPane mobilityScoresETP = new EvaluationTitledPane("mobility");
                    private MobilityDisplaySidesGridPane pawnsMobilityMDSGP;
                    private MobilityDisplaySidesGridPane knightsMobilityMDSGP;
                    private MobilityDisplaySidesGridPane bishopsMobilityMDSGP;
                    private MobilityDisplaySidesGridPane rooksMobilityMDSGP;
                    private MobilityDisplaySidesGridPane queensMobilityMDSGP;
                    private MobilityDisplaySidesGridPane kingsMobilityMDSGP;
                    private MobilityDisplaySidesGridPane castlingsMobilityMDSGP;
                private EvaluationTitledPane arrangementScoresETP = new EvaluationTitledPane("arrangement");
                    private ScoreDisplaySidesHBox pawnsArrgtScoreSDSHB;
                    private ScoreDisplaySidesHBox knightsArrgtScoreSDSHB;
                    private ScoreDisplaySidesHBox bishopsArrgtScoreSDSHB;
                    private ScoreDisplaySidesHBox rooksArrgtScoreSDSHB;
                    private ScoreDisplaySidesHBox queensArrgtScoreSDSHB;
                    private ScoreDisplaySidesHBox kingsArrgtScoreSDSHB;
                    private ScoreDisplaySidesHBox onCtrlledSquaresScoreSDSHB;
                    private ScoreDisplaySidesHBox centreControlMidgScoreSDSHB;
                    private ScoreDisplaySidesHBox cRingControlMidgScoreSDSHB;
                    private ScoreDisplaySidesHBox rookOnOpenFileScoreSDSHB;
                    private ScoreDisplaySidesHBox queenOnOpenFileScoreSDSHB;
                    private ScoreDisplaySidesHBox rookOnSemiOFileScoreSDSHB;
                    private ScoreDisplaySidesHBox queenOnSemiOFileScoreSDSHB;
                    private ScoreDisplaySidesHBox passedPawnsScoreSDSHB;
                private EvaluationTitledPane kingSafetyScoresETP = new EvaluationTitledPane("king safety");
                    private ScoreDisplaySidesHBox shieldPawnLackScoreSDSHB;
                    private ScoreDisplaySidesHBox kingAttackScoreSDSHB;
                    private ScoreDisplaySidesHBox kRing1AttackScoreSDSHB;
                    private ScoreDisplaySidesHBox kRing2AttackScoreSDSHB;

    public Node getNode() {
        return node;
    }

    public EEvaluationTabPane(Engine newEngine, Node newNode)
            throws Exception {

        engine = newEngine;
        node = new Node(newNode);

        EngineShow.evaluation(engine, node);

        newEEvaluationTabPane();

    }

    private void newEEvaluationTabPane()
            throws Exception {

        newBoardBorderPane();
        newScoresBorderPane();

        setEEvaluationTabPane();

    }

    private void setEEvaluationTabPane() {

        getTabs().clear();

        Tab boardTab = new Tab("board", boardBorderPane);
        boardTab.setClosable(false);

        Tab scoresTab = new Tab("scores", scoresBorderPane);
        scoresTab.setClosable(false);

        getTabs().add(0, boardTab);
        getTabs().add(1, scoresTab);

    }

    public void resetEEvaluationTabPane(Engine newEngine, Node newNode)
            throws Exception {

        engine = newEngine;
        node = new Node(newNode);

        EngineShow.evaluation(engine, node);

        resetBoardBorderPane();
        resetScoresBorderPane();

    }

    private void newBoardBorderPane() throws Exception {

        HBox.setHgrow(boardBorderPane, Priority.ALWAYS);

        newBoardTitledPanesToolBar();
        newBoardScrollPane();

        setBoardBorderPane();

    }

    private void setBoardBorderPane() {

        boardBorderPane.getChildren().clear();

        boardBorderPane.setTop(boardTitledPanesToolBar);
        boardBorderPane.setCenter(boardScrollPane);

    }

    private void resetBoardBorderPane()
            throws Exception {

        resetBoardMaterialETP();
        resetBoardPawnStructureETP();
        resetBoardMobilityETP();
        resetBoardArrangementETP();
        resetKingSafetyETP();

    }

    private void newBoardTitledPanesToolBar() {

        boardTitledPanesToolBar = new TitledPanesToolBar();

        boardTitledPanesToolBar.setTitledPanesToolBarListener(new TitledPanesToolBarListener() {

            @Override
            public void onExpandAll()
                    throws Exception {
                boardMaterialETP.setExpanded(true);
                boardPawnStructureETP.setExpanded(true);
                boardMobilityETP.setExpanded(true);
                boardArrangementETP.setExpanded(true);
                kingSafetyBbETP.setExpanded(true);
            }

            @Override
            public void onCollapseAll()
                    throws Exception {
                boardMaterialETP.setExpanded(false);
                boardPawnStructureETP.setExpanded(false);
                boardMobilityETP.setExpanded(false);
                boardArrangementETP.setExpanded(false);
                kingSafetyBbETP.setExpanded(false);
            }

        });

    }

    private void newBoardScrollPane() {

        newBoardVBox();

        boardScrollPane.setFitToWidth(true);

        boardScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        boardScrollPane.setContent(boardVBox);

    }

    private void newBoardVBox() {

        newBoardMaterialETP();
        newBoardPawnStructureETP();
        newBoardMobilityETP();
        newBoardArrangementETP();
        newBoardKingSafetyETP();

        boardVBox.setFillWidth(true);

        boardVBox.getChildren().clear();

        boardVBox.getChildren().add(boardMaterialETP);
        boardVBox.getChildren().add(boardPawnStructureETP);
        boardVBox.getChildren().add(boardMobilityETP);
        boardVBox.getChildren().add(boardArrangementETP);
        boardVBox.getChildren().add(kingSafetyBbETP);

    }

    private void newBoardMaterialETP() {

        pawnsCounterCDSHB     = new CounterDisplaySidesHBox("pawns",
                node.whitePawnsCounter,     node.blackPawnsCounter);
        knightsCounterCDSHB   = new CounterDisplaySidesHBox("knights",
                node.whiteKnightsCounter,   node.blackKnightsCounter);
        bishopsCounterCDSHB   = new CounterDisplaySidesHBox("bishops",
                node.whiteBishopsCounter,   node.blackBishopsCounter);
        rooksCounterCDSHB     = new CounterDisplaySidesHBox("rooks",
                node.whiteRooksCounter,     node.blackRooksCounter);
        queensCounterCDSHB    = new CounterDisplaySidesHBox("queens",
                node.whiteQueensCounter,    node.blackQueensCounter);
        wFBishopsCounterCDSHB = new CounterDisplaySidesHBox("white-field bishops",
                node.whiteWFBishopsCounter, node.blackWFBishopsCounter);
        bFBishopsCounterCDSHB = new CounterDisplaySidesHBox("black-field bishops",
                node.whiteBFBishopsCounter, node.blackBFBishopsCounter);

        boardMaterialETP.clear();

        int r = -1;
        boardMaterialETP.add(pawnsCounterCDSHB,     0, ++r);
        boardMaterialETP.add(knightsCounterCDSHB,   0, ++r);
        boardMaterialETP.add(bishopsCounterCDSHB,   0, ++r);
        boardMaterialETP.add(rooksCounterCDSHB,     0, ++r);
        boardMaterialETP.add(queensCounterCDSHB,    0, ++r);
        boardMaterialETP.add(wFBishopsCounterCDSHB, 0, ++r);
        boardMaterialETP.add(bFBishopsCounterCDSHB, 0, ++r);

    }

    private void resetBoardMaterialETP() {

        pawnsCounterCDSHB.reset(     node.whitePawnsCounter,     node.blackPawnsCounter);
        knightsCounterCDSHB.reset(   node.whiteKnightsCounter,   node.blackKnightsCounter);
        bishopsCounterCDSHB.reset(   node.whiteBishopsCounter,   node.blackBishopsCounter);
        rooksCounterCDSHB.reset(     node.whiteRooksCounter,     node.blackRooksCounter);
        queensCounterCDSHB.reset(    node.whiteQueensCounter,    node.blackQueensCounter);
        wFBishopsCounterCDSHB.reset( node.whiteWFBishopsCounter, node.blackWFBishopsCounter);
        bFBishopsCounterCDSHB.reset( node.whiteBFBishopsCounter, node.blackBFBishopsCounter);

    }

    private void newBoardPawnStructureETP() {

        pawnsFsFSDDHB      = new FileSetDisplaySidesHBox("pawns fs",
                EngineShow.whitePawnsFs, EngineShow.blackPawnsFs);

        pawnIslandsCDSHB   = new CounterDisplaySidesHBox("pawn islands",
                EngineShow.whitePawnIslandsCounter, EngineShow.blackPawnIslandsCounter);

        distorsionBbBBDSHB = new BitBoardDisplaySidesHBox("distorsion",
                EngineShow.whitePawnsFillDeltaBb, EngineShow.blackPawnsFillDeltaBb);

        boardPawnStructureETP.clear();

        int r = -1;
        boardPawnStructureETP.add(pawnsFsFSDDHB,      0, ++r);
        boardPawnStructureETP.add(pawnIslandsCDSHB,   0, ++r);
        boardPawnStructureETP.add(distorsionBbBBDSHB, 0, ++r);

    }

    private void resetBoardPawnStructureETP() {

        pawnsFsFSDDHB.reset(EngineShow.whitePawnsFs, EngineShow.blackPawnsFs);

        pawnIslandsCDSHB.reset(EngineShow.whitePawnIslandsCounter, EngineShow.blackPawnIslandsCounter);

        distorsionBbBBDSHB.reset(EngineShow.whitePawnsFillDeltaBb, EngineShow.blackPawnsFillDeltaBb);

    }

    private void newBoardMobilityETP() {

        ctrlledSquaresBbBBDSHB = new BitBoardDisplaySidesHBox("controlled squares",
                node.whiteControlledBb, node.blackControlledBb);

        pawnsMovesBbBBDSHB = new BitBoardDisplaySidesHBox("pawns moves",
                EngineShow.whitePawnsMovesBb,   EngineShow.blackPawnsMovesBb);

        knigthsMovesBbBBDSHB = new BitBoardDisplaySidesHBox("knights moves",
                EngineShow.whiteKnightsMovesBb, EngineShow.blackKnightsMovesBb);

        bishopsMovesBbBBDSHB = new BitBoardDisplaySidesHBox("bishops moves",
                EngineShow.whiteBishopsMovesBb, EngineShow.blackBishopsMovesBb);

        rooksMovesBbBBDSHB = new BitBoardDisplaySidesHBox("rooks moves",
                EngineShow.whiteRooksMovesBb,   EngineShow.blackRooksMovesBb);

        queensMovesBbBBDSHB = new BitBoardDisplaySidesHBox("queens moves",
                EngineShow.whiteQueensMovesBb,  EngineShow.blackQueensMovesBb);

        kingMovesBbBBDSHB = new BitBoardDisplaySidesHBox("king moves",
                EngineShow.whiteKingMovesBb,    EngineShow.blackKingMovesBb);

        boardMobilityETP.clear();

        int r = -1;
        boardMobilityETP.add(ctrlledSquaresBbBBDSHB, 0, ++r);
        boardMobilityETP.add(pawnsMovesBbBBDSHB,     0, ++r);
        boardMobilityETP.add(knigthsMovesBbBBDSHB,   0, ++r);
        boardMobilityETP.add(bishopsMovesBbBBDSHB,   0, ++r);
        boardMobilityETP.add(rooksMovesBbBBDSHB,     0, ++r);
        boardMobilityETP.add(queensMovesBbBBDSHB,    0, ++r);
        boardMobilityETP.add(kingMovesBbBBDSHB,      0, ++r);

    }

    private void resetBoardMobilityETP() {

        ctrlledSquaresBbBBDSHB.reset( node.whiteControlledBb, node.blackControlledBb );

        pawnsMovesBbBBDSHB.reset(     EngineShow.whitePawnsMovesBb,   EngineShow.blackPawnsMovesBb   );
        knigthsMovesBbBBDSHB.reset(   EngineShow.whiteKnightsMovesBb, EngineShow.blackKnightsMovesBb );
        bishopsMovesBbBBDSHB.reset(   EngineShow.whiteBishopsMovesBb, EngineShow.blackBishopsMovesBb );
        rooksMovesBbBBDSHB.reset(     EngineShow.whiteRooksMovesBb,   EngineShow.blackRooksMovesBb   );
        queensMovesBbBBDSHB.reset(    EngineShow.whiteQueensMovesBb,  EngineShow.blackQueensMovesBb  );
        kingMovesBbBBDSHB.reset(      EngineShow.whiteKingMovesBb,    EngineShow.blackKingMovesBb    );

    }

    private void newBoardArrangementETP() {

        bpbPawnsBbBBDSHB   = new BitBoardDisplaySidesHBox("pawns bb",
                node.whitePawnsBb,   node.blackPawnsBb);
        bpbKnightsBbBBDSHB = new BitBoardDisplaySidesHBox("knights bb",
                node.whiteKnightsBb, node.blackKnightsBb);
        bpbBishopsBbBBDSHB = new BitBoardDisplaySidesHBox("bishops bb",
                node.whiteBishopsBb, node.blackBishopsBb);
        bpbRooksBbBBDSHB   = new BitBoardDisplaySidesHBox("rooks bb",
                node.whiteRooksBb,   node.blackRooksBb);
        bpbQueensBbBBDSHB  = new BitBoardDisplaySidesHBox("queens bb",
                node.whiteQueensBb,  node.blackQueensBb);
        bpbKingsBbBBDSHB   = new BitBoardDisplaySidesHBox("kings bb",
                node.whiteKingBb,    node.blackKingBb);

        openFilesBbBBDHB = new BitBoardDisplayHBox("open files bb",
                node.openFilesBb);

        semiOFilesBbBBDSHB = new BitBoardDisplaySidesHBox("semi-open files bb",
                node.whiteSemiOFilesBb, node.blackSemiOFilesBb);

        boardArrangementETP.clear();

        int r = -1;
        boardArrangementETP.add(bpbPawnsBbBBDSHB,   0, ++r);
        boardArrangementETP.add(bpbKnightsBbBBDSHB, 0, ++r);
        boardArrangementETP.add(bpbBishopsBbBBDSHB, 0, ++r);
        boardArrangementETP.add(bpbRooksBbBBDSHB,   0, ++r);
        boardArrangementETP.add(bpbQueensBbBBDSHB,  0, ++r);
        boardArrangementETP.add(bpbKingsBbBBDSHB,   0, ++r);
        boardArrangementETP.add(openFilesBbBBDHB,   0, ++r);
        boardArrangementETP.add(semiOFilesBbBBDSHB, 0, ++r);

    }

    private void resetBoardArrangementETP() {

        bpbPawnsBbBBDSHB.reset(   node.whitePawnsBb,   node.blackPawnsBb);
        bpbKnightsBbBBDSHB.reset( node.whiteKnightsBb, node.blackKnightsBb);
        bpbBishopsBbBBDSHB.reset( node.whiteBishopsBb, node.blackBishopsBb);
        bpbRooksBbBBDSHB.reset(   node.whiteRooksBb,   node.blackRooksBb);
        bpbQueensBbBBDSHB.reset(  node.whiteQueensBb,  node.blackQueensBb);
        bpbKingsBbBBDSHB.reset(   node.whiteKingBb,    node.blackKingBb);

        openFilesBbBBDHB.reset(   node.openFilesBb );

        semiOFilesBbBBDSHB.reset( node.whiteSemiOFilesBb, node.blackSemiOFilesBb);

    }

    private void newBoardKingSafetyETP() {

        kRing1BbBBDSHB = new BitBoardDisplaySidesHBox("king rings 1 bb",
                node.whiteKRing1Bb, node.blackKRing1Bb);
        kRing2BbBBDSHB = new BitBoardDisplaySidesHBox("king rings 2 bb",
                node.whiteKRing2Bb, node.blackKRing2Bb);

        kingSafetyBbETP.clear();

        int r = -1;

        kingSafetyBbETP.add(kRing1BbBBDSHB, 0, ++r);
        kingSafetyBbETP.add(kRing2BbBBDSHB, 0, ++r);

    }

    private void resetKingSafetyETP() {

        kRing1BbBBDSHB.reset( node.whiteKRing1Bb, node.blackKRing1Bb);
        kRing2BbBBDSHB.reset( node.whiteKRing2Bb, node.blackKRing2Bb);

    }

    private void newScoresBorderPane() throws Exception {

        HBox.setHgrow(scoresBorderPane, Priority.ALWAYS);

        newScoresTitledPanesToolBar();
        newScoresScrollPane();

        scoresBorderPane.getChildren().clear();

        scoresBorderPane.setTop(scoresTitledPanesToolBar);
        scoresBorderPane.setCenter(scoresScrollPane);

    }

    private void resetScoresBorderPane()
            throws Exception {

        resetMaterialScoresETP();
        resetPawnStructureScoresETP();
        resetMobilityScoresETP();
        resetArrangementScoresETP();
        resetKingSafetyScoresETP();

    }

    private void newScoresTitledPanesToolBar() {

        scoresTitledPanesToolBar = new TitledPanesToolBar();

        scoresTitledPanesToolBar.setTitledPanesToolBarListener(new TitledPanesToolBarListener() {

            @Override
            public void onExpandAll()
                    throws Exception {
                materialScoresETP.setExpanded(true);
                pawnStructureScoresETP.setExpanded(true);
                mobilityScoresETP.setExpanded(true);
                arrangementScoresETP.setExpanded(true);
                kingSafetyScoresETP.setExpanded(true);
            }

            @Override
            public void onCollapseAll()
                    throws Exception {
                materialScoresETP.setExpanded(false);
                pawnStructureScoresETP.setExpanded(false);
                mobilityScoresETP.setExpanded(false);
                arrangementScoresETP.setExpanded(false);
                kingSafetyScoresETP.setExpanded(false);
            }

        });

    }

    private void newScoresScrollPane() {

        newScoresVBox();

        scoresScrollPane.setFitToWidth(true);

        scoresScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scoresScrollPane.setContent(scoresVBox);

    }

    private void newScoresVBox() {

        newMaterialScoresETP();
        newPawnStructureScoresETP();
        newMobilityScoresETP();
        newArrangementScoresETP();
        newKingSafetyScoresETP();

        scoresVBox.setFillWidth(true);

        setScoresVBox();

    }

    private void setScoresVBox() {

        scoresVBox.getChildren().clear();

        scoresVBox.getChildren().add(materialScoresETP);
        scoresVBox.getChildren().add(pawnStructureScoresETP);
        scoresVBox.getChildren().add(mobilityScoresETP);
        scoresVBox.getChildren().add(arrangementScoresETP);
        scoresVBox.getChildren().add(kingSafetyScoresETP);

    }

    private void newMaterialScoresETP() {

        pawnsScoreSDSHB         = new ScoreDisplaySidesHBox("pawns",
                null, EngineShow.whitePawnsMatMidg,     EngineShow.whitePawnsMatEndg,
                null, EngineShow.blackPawnsMatMidg,     EngineShow.blackPawnsMatEndg);
        knightsScoreSDSHB       = new ScoreDisplaySidesHBox("knights",
                null, EngineShow.whiteKnightsMatMidg,   EngineShow.whiteKnightsMatEndg,
                null, EngineShow.blackKnightsMatMidg,   EngineShow.blackKnightsMatEndg);
        bishopsScoreSDSHB       = new ScoreDisplaySidesHBox("bishops",
                null, EngineShow.whiteBishopsMatMidg,   EngineShow.whiteBishopsMatEndg,
                null, EngineShow.blackBishopsMatMidg,   EngineShow.blackBishopsMatEndg);
        rooksScoreSDSHB         = new ScoreDisplaySidesHBox("rooks",
                null, EngineShow.whiteRooksMatMidg,     EngineShow.whiteRooksMatEndg,
                null, EngineShow.blackRooksMatMidg,     EngineShow.blackRooksMatEndg);
        queensScoreSDSHB        = new ScoreDisplaySidesHBox("queens",
                null, EngineShow.whiteQueensMatMidg,    EngineShow.whiteQueensMatEndg,
                null, EngineShow.blackQueensMatMidg,    EngineShow.blackQueensMatEndg);
        shortCgsScoreSDSHB      = new ScoreDisplaySidesHBox("short castling",
                null, EngineShow.whiteShortCgMatMidg,   null,
                null, EngineShow.blackShortCgMatMidg,   null);
        longCgsScoreSDSHB       = new ScoreDisplaySidesHBox("long castling",
                null, EngineShow.whiteLongCgMatMidg,    null,
                null, EngineShow.blackLongCgMatMidg,    null);
        lonelyKnightsScoreSDSHB = new ScoreDisplaySidesHBox("lonely knight",
                null, EngineShow.whiteLonelyKnightMidg, EngineShow.whiteLonelyKnightEndg,
                null, EngineShow.blackLonelyKnightMidg, EngineShow.blackLonelyKnightEndg);
        lonelyBishopsScoreSDSHB = new ScoreDisplaySidesHBox("lonely bishop",
                null, EngineShow.whiteLonelyBishopMidg, EngineShow.whiteLonelyBishopEndg,
                null, EngineShow.blackLonelyBishopMidg, EngineShow.blackLonelyBishopEndg);
        lonelyRooksScoreSDSHB   = new ScoreDisplaySidesHBox("lonely rook",
                null, EngineShow.whiteLonelyRookMidg,   EngineShow.whiteLonelyRookEndg,
                null, EngineShow.blackLonelyRookMidg,   EngineShow.blackLonelyRookEndg);
        sameFBishopsScoreSDSHB  = new ScoreDisplaySidesHBox("same field bishops",
                null, EngineShow.whiteSameFBishopsMidg, EngineShow.whiteSameFBishopsEndg,
                null, EngineShow.blackSameFBishopsMidg, EngineShow.blackSameFBishopsEndg);
        noPawnsScoreSDSHB       = new ScoreDisplaySidesHBox("no pawns",
                null, EngineShow.whiteNoPawnsMidg,      EngineShow.whiteNoPawnsEndg,
                null, EngineShow.blackNoPawnsMidg,      EngineShow.blackNoPawnsEndg);
        allPiecesMidgScoreSDHB  = new ScoreDisplayHBox("all pieces",
                null, EngineShow.allPiecesMidgScore, null);

        materialScoresETP.clear();

        int r = -1;
        materialScoresETP.add(pawnsScoreSDSHB,         0, ++r);
        materialScoresETP.add(knightsScoreSDSHB,       0, ++r);
        materialScoresETP.add(bishopsScoreSDSHB,       0, ++r);
        materialScoresETP.add(rooksScoreSDSHB,         0, ++r);
        materialScoresETP.add(queensScoreSDSHB,        0, ++r);
        materialScoresETP.add(shortCgsScoreSDSHB,      0, ++r);
        materialScoresETP.add(longCgsScoreSDSHB,       0, ++r);
        materialScoresETP.add(lonelyKnightsScoreSDSHB, 0, ++r);
        materialScoresETP.add(lonelyBishopsScoreSDSHB, 0, ++r);
        materialScoresETP.add(lonelyRooksScoreSDSHB,   0, ++r);
        materialScoresETP.add(sameFBishopsScoreSDSHB,  0, ++r);
        materialScoresETP.add(noPawnsScoreSDSHB,       0, ++r);
        materialScoresETP.add(allPiecesMidgScoreSDHB,  0, ++r);

    }

    private void resetMaterialScoresETP() {

        pawnsScoreSDSHB.reset(         null, EngineShow.whitePawnsMatMidg,     EngineShow.whitePawnsMatEndg,
                                       null, EngineShow.blackPawnsMatMidg,     EngineShow.blackPawnsMatEndg);
        knightsScoreSDSHB.reset(       null, EngineShow.whiteKnightsMatMidg,   EngineShow.whiteKnightsMatEndg,
                                       null, EngineShow.blackKnightsMatMidg,   EngineShow.blackKnightsMatEndg);
        bishopsScoreSDSHB.reset(       null, EngineShow.whiteBishopsMatMidg,   EngineShow.whiteBishopsMatEndg,
                                       null, EngineShow.blackBishopsMatMidg,   EngineShow.blackBishopsMatEndg);
        rooksScoreSDSHB.reset(         null, EngineShow.whiteRooksMatMidg,     EngineShow.whiteRooksMatEndg,
                                       null, EngineShow.blackRooksMatMidg,     EngineShow.blackRooksMatEndg);
        queensScoreSDSHB.reset(        null, EngineShow.whiteQueensMatMidg,    EngineShow.whiteQueensMatEndg,
                                       null, EngineShow.blackQueensMatMidg,    EngineShow.blackQueensMatEndg);
        shortCgsScoreSDSHB.reset(      null, EngineShow.whiteShortCgMatMidg,   null,
                                       null, EngineShow.blackShortCgMatMidg,   null);
        longCgsScoreSDSHB.reset(       null, EngineShow.whiteLongCgMatMidg,    null,
                                       null, EngineShow.blackLongCgMatMidg,    null);
        lonelyKnightsScoreSDSHB.reset( null, EngineShow.whiteLonelyKnightMidg, EngineShow.whiteLonelyKnightEndg,
                                       null, EngineShow.blackLonelyKnightMidg, EngineShow.blackLonelyKnightEndg);
        lonelyBishopsScoreSDSHB.reset( null, EngineShow.whiteLonelyBishopMidg, EngineShow.whiteLonelyBishopEndg,
                                       null, EngineShow.blackLonelyBishopMidg, EngineShow.blackLonelyBishopEndg);
        lonelyRooksScoreSDSHB.reset(   null, EngineShow.whiteLonelyRookMidg,   EngineShow.whiteLonelyRookEndg,
                                       null, EngineShow.blackLonelyRookMidg,   EngineShow.blackLonelyRookEndg);
        sameFBishopsScoreSDSHB.reset(  null, EngineShow.whiteSameFBishopsMidg, EngineShow.whiteSameFBishopsEndg,
                                       null, EngineShow.blackSameFBishopsMidg, EngineShow.blackSameFBishopsEndg);
        noPawnsScoreSDSHB.reset(       null, EngineShow.whiteNoPawnsMidg,      EngineShow.whiteNoPawnsEndg,
                                       null, EngineShow.blackNoPawnsMidg,      EngineShow.blackNoPawnsEndg);
        allPiecesMidgScoreSDHB.reset(  null, EngineShow.allPiecesMidgScore,    null);

    }

    private void newPawnStructureScoresETP() {

        dispersionSDSHB        = new ScoreDisplaySidesHBox("dispersion",
                null, EngineShow.whiteDispersionMidg, null,
                null, EngineShow.blackDispersionMidg, null);
        distorsionSDSHB        = new ScoreDisplaySidesHBox("distorsion",
                null, EngineShow.whiteDistorsionMidg, null,
                null, EngineShow.blackDistorsionMidg, null);
        isolaniPawnsScoreSDSHB = new ScoreDisplaySidesHBox("isolani pawns",
                null, EngineShow.whiteIsolaniPawnsMidg, EngineShow.whiteIsolaniPawnsEndg,
                null, EngineShow.blackIsolaniPawnsMidg, EngineShow.blackIsolaniPawnsEndg);

        pawnStructureScoresETP.clear();

        int r = -1;
        pawnStructureScoresETP.add(dispersionSDSHB,        0, ++r);
        pawnStructureScoresETP.add(distorsionSDSHB,        0, ++r);
        pawnStructureScoresETP.add(isolaniPawnsScoreSDSHB, 0, ++r);

    }

    private void resetPawnStructureScoresETP() {

        dispersionSDSHB.reset(        null, EngineShow.whiteDispersionMidg, null,
                                      null, EngineShow.blackDispersionMidg, null);
        distorsionSDSHB.reset(        null, EngineShow.whiteDistorsionMidg, null,
                                      null, EngineShow.blackDistorsionMidg, null);
        isolaniPawnsScoreSDSHB.reset( null, EngineShow.whiteIsolaniPawnsMidg, EngineShow.whiteIsolaniPawnsEndg,
                                      null, EngineShow.blackIsolaniPawnsMidg, EngineShow.blackIsolaniPawnsEndg);

    }

    private void newMobilityScoresETP() {

        pawnsMobilityMDSGP = new MobilityDisplaySidesGridPane("pawns",
                EngineShow.whitePawnsStepsMobMidg,
                EngineShow.whitePawnsDoubleStepsMobMidg,
                EngineShow.whitePawnsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EngineShow.blackPawnsStepsMobMidg,
                EngineShow.blackPawnsDoubleStepsMobMidg,
                EngineShow.blackPawnsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        knightsMobilityMDSGP = new MobilityDisplaySidesGridPane("knights",
                EngineShow.whiteKnightsStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.whiteKnightsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EngineShow.blackKnightsStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.blackKnightsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        bishopsMobilityMDSGP = new MobilityDisplaySidesGridPane("bishops",
                EngineShow.whiteBishopsStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.whiteBishopsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EngineShow.blackBishopsStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.blackBishopsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        rooksMobilityMDSGP = new MobilityDisplaySidesGridPane("rooks",
                EngineShow.whiteRooksStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.whiteRooksCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EngineShow.blackRooksStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.blackRooksCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        queensMobilityMDSGP = new MobilityDisplaySidesGridPane("queens",
                EngineShow.whiteQueensStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.whiteQueensCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EngineShow.blackQueensStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.blackQueensCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        kingsMobilityMDSGP = new MobilityDisplaySidesGridPane("kings",
                EngineShow.whiteKingStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.whiteKingCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EngineShow.blackKingStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.blackKingCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        castlingsMobilityMDSGP = new MobilityDisplaySidesGridPane("castlings",
                /*Steps*/null, /*DoubleSteps*/null, /*Captures*/null,
                EngineShow.whiteShortCgMobMidg, EngineShow.whiteLongCgMobMidg,
                /*Steps*/null, /*DoubleSteps*/null, /*Captures*/null,
                EngineShow.blackShortCgMobMidg, EngineShow.blackLongCgMobMidg);

        mobilityScoresETP.clear();

        int r = -1;
        mobilityScoresETP.add(pawnsMobilityMDSGP,     0, ++r);
        mobilityScoresETP.add(knightsMobilityMDSGP,   0, ++r);
        mobilityScoresETP.add(bishopsMobilityMDSGP,   0, ++r);
        mobilityScoresETP.add(rooksMobilityMDSGP,     0, ++r);
        mobilityScoresETP.add(queensMobilityMDSGP,    0, ++r);
        mobilityScoresETP.add(kingsMobilityMDSGP,     0, ++r);
        mobilityScoresETP.add(castlingsMobilityMDSGP, 0, ++r);

    }

    private void resetMobilityScoresETP() {

        pawnsMobilityMDSGP.reset(
                EngineShow.whitePawnsStepsMobMidg,
                EngineShow.whitePawnsDoubleStepsMobMidg,
                EngineShow.whitePawnsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EngineShow.blackPawnsStepsMobMidg,
                EngineShow.blackPawnsDoubleStepsMobMidg,
                EngineShow.blackPawnsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        knightsMobilityMDSGP.reset(
                EngineShow.whiteKnightsStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.whiteKnightsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EngineShow.blackKnightsStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.blackKnightsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        bishopsMobilityMDSGP.reset(
                EngineShow.whiteBishopsStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.whiteBishopsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EngineShow.blackBishopsStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.blackBishopsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        rooksMobilityMDSGP.reset(
                EngineShow.whiteRooksStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.whiteRooksCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EngineShow.blackRooksStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.blackRooksCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        queensMobilityMDSGP.reset(
                EngineShow.whiteQueensStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.whiteQueensCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EngineShow.blackQueensStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.blackQueensCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        kingsMobilityMDSGP.reset(
                EngineShow.whiteKingStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.whiteKingCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EngineShow.blackKingStepsMobMidg,
                /*DoubleSteps*/null,
                EngineShow.blackKingCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        castlingsMobilityMDSGP.reset(
                /*Steps*/null, /*DoubleSteps*/null, /*Captures*/null,
                EngineShow.whiteShortCgMatMidg, EngineShow.whiteLongCgMobMidg,
                /*Steps*/null, /*DoubleSteps*/null, /*Captures*/null,
                EngineShow.blackShortCgMobMidg, EngineShow.blackLongCgMobMidg);

    }

    private void newArrangementScoresETP() {

        pawnsArrgtScoreSDSHB = new ScoreDisplaySidesHBox("pawns",
                /*whiteOpeg*/null, EngineShow.whitePawnsArrMidg,         EngineShow.whitePawnsArrEndg,
                /*blackOpeg*/null, EngineShow.blackPawnsArrMidg,         EngineShow.blackPawnsArrEndg);
        knightsArrgtScoreSDSHB = new ScoreDisplaySidesHBox("knights",
                /*whiteOpeg*/null, EngineShow.whiteKnightsArrMidg,       EngineShow.whiteKnightsArrEndg,
                /*blackOpeg*/null, EngineShow.blackKnightsArrMidg,       EngineShow.blackKnightsArrEndg);
        bishopsArrgtScoreSDSHB = new ScoreDisplaySidesHBox("bishops",
                /*whiteOpeg*/null, EngineShow.whiteBishopsArrMidg,       EngineShow.whiteBishopsArrEndg,
                /*blackOpeg*/null, EngineShow.blackBishopsArrMidg,       EngineShow.blackBishopsArrEndg);
        rooksArrgtScoreSDSHB = new ScoreDisplaySidesHBox("rooks",
                /*whiteOpeg*/null, EngineShow.whiteRooksArrMidg,         EngineShow.whiteRooksArrEndg,
                /*blackOpeg*/null, EngineShow.blackRooksArrMidg,         EngineShow.blackRooksArrEndg);
        queensArrgtScoreSDSHB = new ScoreDisplaySidesHBox("queens",
                /*whiteOpeg*/null, EngineShow.whiteQueensArrMidg,        EngineShow.whiteQueensArrEndg,
                /*blackOpeg*/null, EngineShow.blackQueensArrMidg,        EngineShow.blackQueensArrEndg);
        kingsArrgtScoreSDSHB = new ScoreDisplaySidesHBox("kings",
                /*whiteOpeg*/null, EngineShow.whiteKingArrMidg,          EngineShow.whiteKingArrEndg,
                /*blackOpeg*/null, EngineShow.blackKingArrMidg,          EngineShow.blackKingArrEndg);
        onCtrlledSquaresScoreSDSHB = new ScoreDisplaySidesHBox("on controlled squares",
                /*whiteOpeg*/null, EngineShow.whiteOnCtrlledSquareMidg,  /*whiteEndg*/null,
                /*blackOpeg*/null, EngineShow.blackOnCtrlledSquareMidg,  /*blackEndg*/null);
        centreControlMidgScoreSDSHB = new ScoreDisplaySidesHBox("center control",
                /*whiteOpeg*/null, EngineShow.whiteCentreControlMidg,    /*whiteEndg*/null,
                /*blackOpeg*/null, EngineShow.blackCentreControlMidg,    /*blackEndg*/null);
        cRingControlMidgScoreSDSHB = new ScoreDisplaySidesHBox("central ring control",
                /*whiteOpeg*/null, EngineShow.whiteCRingControlMidg,     /*whiteEndg*/null,
                /*blackOpeg*/null, EngineShow.whiteCRingControlMidg,     /*blackEndg*/null);
        rookOnOpenFileScoreSDSHB = new ScoreDisplaySidesHBox("rooks on open file",
                /*whiteOpeg*/null, EngineShow.whiteRookOnOpenFileMidg,   /*whiteEndg*/null,
                /*blackOpeg*/null, EngineShow.blackRookOnOpenFileMidg,   /*blackEndg*/null);
        queenOnOpenFileScoreSDSHB = new ScoreDisplaySidesHBox("queens on open file",
                /*whiteOpeg*/null, EngineShow.whiteQueenOnOpenFileMidg,  /*whiteEndg*/null,
                /*blackOpeg*/null, EngineShow.blackQueenOnOpenFileMidg,  /*blackEndg*/null);
        rookOnSemiOFileScoreSDSHB = new ScoreDisplaySidesHBox("rooks on semi-open file",
                /*whiteOpeg*/null, EngineShow.whiteRookOnSemiOFileMidg,  /*whiteEndg*/null,
                /*blackOpeg*/null, EngineShow.blackRookOnSemiOFileMidg,  /*blackEndg*/null);
        queenOnSemiOFileScoreSDSHB = new ScoreDisplaySidesHBox("queens on semi-open file",
                /*whiteOpeg*/null, EngineShow.whiteQueenOnSemiOFileMidg, /*whiteEndg*/null,
                /*blackOpeg*/null, EngineShow.blackQueenOnSemiOFileMidg, /*blackEndg*/null);
        passedPawnsScoreSDSHB = new ScoreDisplaySidesHBox("passed pawns",
                /*whiteOpeg*/null, EngineShow.whitePassedPawnsMidg,      EngineShow.whitePassedPawnsEndg,
                /*blackOpeg*/null, EngineShow.blackPassedPawnsMidg,      EngineShow.blackPassedPawnsEndg);

        arrangementScoresETP.clear();

        int r = -1;
        arrangementScoresETP.add(pawnsArrgtScoreSDSHB,        0, ++r);
        arrangementScoresETP.add(knightsArrgtScoreSDSHB,      0, ++r);
        arrangementScoresETP.add(bishopsArrgtScoreSDSHB,      0, ++r);
        arrangementScoresETP.add(rooksArrgtScoreSDSHB,        0, ++r);
        arrangementScoresETP.add(queensArrgtScoreSDSHB,       0, ++r);
        arrangementScoresETP.add(kingsArrgtScoreSDSHB,        0, ++r);
        arrangementScoresETP.add(onCtrlledSquaresScoreSDSHB,  0, ++r);
        arrangementScoresETP.add(centreControlMidgScoreSDSHB, 0, ++r);
        arrangementScoresETP.add(cRingControlMidgScoreSDSHB,  0, ++r);
        arrangementScoresETP.add(rookOnOpenFileScoreSDSHB,    0, ++r);
        arrangementScoresETP.add(queenOnOpenFileScoreSDSHB,   0, ++r);
        arrangementScoresETP.add(rookOnSemiOFileScoreSDSHB,   0, ++r);
        arrangementScoresETP.add(queenOnSemiOFileScoreSDSHB,  0, ++r);
        arrangementScoresETP.add(passedPawnsScoreSDSHB,       0, ++r);

    }

    private void resetArrangementScoresETP() {

        pawnsArrgtScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whitePawnsArrMidg,         EngineShow.whitePawnsArrEndg,
                /*blackOpeg*/null, EngineShow.blackPawnsArrMidg,         EngineShow.blackPawnsArrEndg);
        knightsArrgtScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whiteKnightsArrMidg,       EngineShow.whiteKnightsArrEndg,
                /*blackOpeg*/null, EngineShow.blackKnightsArrMidg,       EngineShow.blackKnightsArrEndg);
        bishopsArrgtScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whiteBishopsArrMidg,       EngineShow.whiteBishopsArrEndg,
                /*blackOpeg*/null, EngineShow.blackBishopsArrMidg,       EngineShow.blackBishopsArrEndg);
        rooksArrgtScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whiteRooksArrMidg,         EngineShow.whiteRooksArrEndg,
                /*blackOpeg*/null, EngineShow.blackRooksArrMidg,         EngineShow.blackRooksArrEndg);
        queensArrgtScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whiteQueensArrMidg,        EngineShow.whiteQueensArrEndg,
                /*blackOpeg*/null, EngineShow.blackQueensArrMidg,        EngineShow.blackQueensArrEndg);
        kingsArrgtScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whiteKingArrMidg,          EngineShow.whiteKingArrEndg,
                /*blackOpeg*/null, EngineShow.blackKingArrMidg,          EngineShow.blackKingArrEndg);
        onCtrlledSquaresScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whiteOnCtrlledSquareMidg,  /*whiteEndg*/null,
                /*blackOpeg*/null, EngineShow.blackOnCtrlledSquareMidg,  /*blackEndg*/null);
        centreControlMidgScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whiteCentreControlMidg,    /*whiteEndg*/null,
                /*blackOpeg*/null, EngineShow.blackCentreControlMidg,    /*blackEndg*/null);
        cRingControlMidgScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whiteCRingControlMidg,     /*whiteEndg*/null,
                /*blackOpeg*/null, EngineShow.blackCRingControlMidg,     /*blackEndg*/null);
        rookOnOpenFileScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whiteRookOnOpenFileMidg,   /*whiteEndg*/null,
                /*blackOpeg*/null, EngineShow.blackRookOnOpenFileMidg,   /*blackEndg*/null);
        queenOnOpenFileScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whiteQueenOnOpenFileMidg,  /*whiteEndg*/null,
                /*blackOpeg*/null, EngineShow.blackQueenOnOpenFileMidg,  /*blackEndg*/null);
        rookOnSemiOFileScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whiteRookOnSemiOFileMidg,  /*whiteEndg*/null,
                /*blackOpeg*/null, EngineShow.blackRookOnSemiOFileMidg,  /*blackEndg*/null);
        queenOnSemiOFileScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whiteQueenOnSemiOFileMidg, /*whiteEndg*/null,
                /*blackOpeg*/null, EngineShow.blackQueenOnSemiOFileMidg, /*blackEndg*/null);
        passedPawnsScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whitePassedPawnsMidg,      EngineShow.whitePassedPawnsEndg,
                /*blackOpeg*/null, EngineShow.blackPassedPawnsMidg,      EngineShow.blackPassedPawnsEndg);

    }

    private void newKingSafetyScoresETP() {

        shieldPawnLackScoreSDSHB = new ScoreDisplaySidesHBox("shield pawn holes",
                /*whiteOpeg*/null, EngineShow.whiteShieldPawnsMidg,    /*whiteEndg*/null,
                /*blackOpeg*/null, EngineShow.blackShieldPawnsMidg,    /*whiteEndg*/null);
        kingAttackScoreSDSHB     = new ScoreDisplaySidesHBox("attacked king",
                /*whiteOpeg*/null, EngineShow.whiteAttackedKingMidg,   EngineShow.whiteAttackedKingEndg,
                /*blackOpeg*/null, EngineShow.blackAttackedKingMidg,   EngineShow.blackAttackedKingEndg);
        kRing1AttackScoreSDSHB   = new ScoreDisplaySidesHBox("attacked king ring 1",
                /*whiteOpeg*/null, EngineShow.whiteAttackedKRing1Midg, EngineShow.whiteAttackedKRing1Endg,
                /*blackOpeg*/null, EngineShow.blackAttackedKRing1Midg, EngineShow.blackAttackedKRing1Endg);
        kRing2AttackScoreSDSHB   = new ScoreDisplaySidesHBox("attacked king ring 2",
                /*whiteOpeg*/null, EngineShow.whiteAttackedKRing2Midg, EngineShow.whiteAttackedKRing2Endg,
                /*blackOpeg*/null, EngineShow.blackAttackedKRing2Midg, EngineShow.blackAttackedKRing2Endg);

        kingSafetyScoresETP.clear();

        int r = -1;
        kingSafetyScoresETP.add(shieldPawnLackScoreSDSHB, 0, ++r);
        kingSafetyScoresETP.add(kingAttackScoreSDSHB,     0, ++r);
        kingSafetyScoresETP.add(kRing1AttackScoreSDSHB,   0, ++r);
        kingSafetyScoresETP.add(kRing2AttackScoreSDSHB,   0, ++r);

    }

    private void resetKingSafetyScoresETP() {

        shieldPawnLackScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whiteShieldPawnsMidg,    /*whiteEndg*/null,
                /*blackOpeg*/null, EngineShow.blackShieldPawnsMidg,    /*whiteEndg*/null);
        kingAttackScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whiteAttackedKingMidg,   EngineShow.whiteAttackedKingEndg,
                /*blackOpeg*/null, EngineShow.blackAttackedKingMidg,   EngineShow.blackAttackedKingEndg);
        kRing1AttackScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whiteAttackedKRing1Midg, EngineShow.whiteAttackedKRing1Endg,
                /*blackOpeg*/null, EngineShow.blackAttackedKRing1Midg, EngineShow.blackAttackedKRing1Endg);
        kRing2AttackScoreSDSHB.reset(
                /*whiteOpeg*/null, EngineShow.whiteAttackedKRing2Midg, EngineShow.whiteAttackedKRing2Endg,
                /*blackOpeg*/null, EngineShow.blackAttackedKRing2Midg, EngineShow.blackAttackedKRing2Endg);

    }

}
