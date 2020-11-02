package engine.view.evaluation;

import engine.model.Engine;
import engine.model.EvaluaShow;
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

        EvaluaShow.evaluation(engine, node);

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

        EvaluaShow.evaluation(engine, node);

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
                EvaluaShow.whitePawnsFs, EvaluaShow.blackPawnsFs);

        pawnIslandsCDSHB   = new CounterDisplaySidesHBox("pawn islands",
                EvaluaShow.whitePawnIslandsCounter, EvaluaShow.blackPawnIslandsCounter);

        distorsionBbBBDSHB = new BitBoardDisplaySidesHBox("distorsion",
                EvaluaShow.whitePawnsFillDeltaBb, EvaluaShow.blackPawnsFillDeltaBb);

        boardPawnStructureETP.clear();

        int r = -1;
        boardPawnStructureETP.add(pawnsFsFSDDHB,      0, ++r);
        boardPawnStructureETP.add(pawnIslandsCDSHB,   0, ++r);
        boardPawnStructureETP.add(distorsionBbBBDSHB, 0, ++r);

    }

    private void resetBoardPawnStructureETP() {

        pawnsFsFSDDHB.reset(EvaluaShow.whitePawnsFs, EvaluaShow.blackPawnsFs);

        pawnIslandsCDSHB.reset(EvaluaShow.whitePawnIslandsCounter, EvaluaShow.blackPawnIslandsCounter);

        distorsionBbBBDSHB.reset(EvaluaShow.whitePawnsFillDeltaBb, EvaluaShow.blackPawnsFillDeltaBb);

    }

    private void newBoardMobilityETP() {

        ctrlledSquaresBbBBDSHB = new BitBoardDisplaySidesHBox("controlled squares",
                node.whiteControlledBb, node.blackControlledBb);

        pawnsMovesBbBBDSHB = new BitBoardDisplaySidesHBox("pawns moves",
                EvaluaShow.whitePawnsMovesBb,   EvaluaShow.blackPawnsMovesBb);

        knigthsMovesBbBBDSHB = new BitBoardDisplaySidesHBox("knights moves",
                EvaluaShow.whiteKnightsMovesBb, EvaluaShow.blackKnightsMovesBb);

        bishopsMovesBbBBDSHB = new BitBoardDisplaySidesHBox("bishops moves",
                EvaluaShow.whiteBishopsMovesBb, EvaluaShow.blackBishopsMovesBb);

        rooksMovesBbBBDSHB = new BitBoardDisplaySidesHBox("rooks moves",
                EvaluaShow.whiteRooksMovesBb,   EvaluaShow.blackRooksMovesBb);

        queensMovesBbBBDSHB = new BitBoardDisplaySidesHBox("queens moves",
                EvaluaShow.whiteQueensMovesBb,  EvaluaShow.blackQueensMovesBb);

        kingMovesBbBBDSHB = new BitBoardDisplaySidesHBox("king moves",
                EvaluaShow.whiteKingMovesBb,    EvaluaShow.blackKingMovesBb);

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

        pawnsMovesBbBBDSHB.reset(     EvaluaShow.whitePawnsMovesBb,   EvaluaShow.blackPawnsMovesBb   );
        knigthsMovesBbBBDSHB.reset(   EvaluaShow.whiteKnightsMovesBb, EvaluaShow.blackKnightsMovesBb );
        bishopsMovesBbBBDSHB.reset(   EvaluaShow.whiteBishopsMovesBb, EvaluaShow.blackBishopsMovesBb );
        rooksMovesBbBBDSHB.reset(     EvaluaShow.whiteRooksMovesBb,   EvaluaShow.blackRooksMovesBb   );
        queensMovesBbBBDSHB.reset(    EvaluaShow.whiteQueensMovesBb,  EvaluaShow.blackQueensMovesBb  );
        kingMovesBbBBDSHB.reset(      EvaluaShow.whiteKingMovesBb,    EvaluaShow.blackKingMovesBb    );

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
                null, EvaluaShow.whitePawnsMatMidg,     EvaluaShow.whitePawnsMatEndg,
                null, EvaluaShow.blackPawnsMatMidg,     EvaluaShow.blackPawnsMatEndg);
        knightsScoreSDSHB       = new ScoreDisplaySidesHBox("knights",
                null, EvaluaShow.whiteKnightsMatMidg,   EvaluaShow.whiteKnightsMatEndg,
                null, EvaluaShow.blackKnightsMatMidg,   EvaluaShow.blackKnightsMatEndg);
        bishopsScoreSDSHB       = new ScoreDisplaySidesHBox("bishops",
                null, EvaluaShow.whiteBishopsMatMidg,   EvaluaShow.whiteBishopsMatEndg,
                null, EvaluaShow.blackBishopsMatMidg,   EvaluaShow.blackBishopsMatEndg);
        rooksScoreSDSHB         = new ScoreDisplaySidesHBox("rooks",
                null, EvaluaShow.whiteRooksMatMidg,     EvaluaShow.whiteRooksMatEndg,
                null, EvaluaShow.blackRooksMatMidg,     EvaluaShow.blackRooksMatEndg);
        queensScoreSDSHB        = new ScoreDisplaySidesHBox("queens",
                null, EvaluaShow.whiteQueensMatMidg,    EvaluaShow.whiteQueensMatEndg,
                null, EvaluaShow.blackQueensMatMidg,    EvaluaShow.blackQueensMatEndg);
        shortCgsScoreSDSHB      = new ScoreDisplaySidesHBox("short castling",
                null, EvaluaShow.whiteShortCgMatMidg,   null,
                null, EvaluaShow.blackShortCgMatMidg,   null);
        longCgsScoreSDSHB       = new ScoreDisplaySidesHBox("long castling",
                null, EvaluaShow.whiteLongCgMatMidg,    null,
                null, EvaluaShow.blackLongCgMatMidg,    null);
        lonelyKnightsScoreSDSHB = new ScoreDisplaySidesHBox("lonely knight",
                null, EvaluaShow.whiteLonelyKnightMidg, EvaluaShow.whiteLonelyKnightEndg,
                null, EvaluaShow.blackLonelyKnightMidg, EvaluaShow.blackLonelyKnightEndg);
        lonelyBishopsScoreSDSHB = new ScoreDisplaySidesHBox("lonely bishop",
                null, EvaluaShow.whiteLonelyBishopMidg, EvaluaShow.whiteLonelyBishopEndg,
                null, EvaluaShow.blackLonelyBishopMidg, EvaluaShow.blackLonelyBishopEndg);
        lonelyRooksScoreSDSHB   = new ScoreDisplaySidesHBox("lonely rook",
                null, EvaluaShow.whiteLonelyRookMidg,   EvaluaShow.whiteLonelyRookEndg,
                null, EvaluaShow.blackLonelyRookMidg,   EvaluaShow.blackLonelyRookEndg);
        sameFBishopsScoreSDSHB  = new ScoreDisplaySidesHBox("same field bishops",
                null, EvaluaShow.whiteSameFBishopsMidg, EvaluaShow.whiteSameFBishopsEndg,
                null, EvaluaShow.blackSameFBishopsMidg, EvaluaShow.blackSameFBishopsEndg);
        noPawnsScoreSDSHB       = new ScoreDisplaySidesHBox("no pawns",
                null, EvaluaShow.whiteNoPawnsMidg,      EvaluaShow.whiteNoPawnsEndg,
                null, EvaluaShow.blackNoPawnsMidg,      EvaluaShow.blackNoPawnsEndg);
        allPiecesMidgScoreSDHB  = new ScoreDisplayHBox("all pieces",
                null, EvaluaShow.allPiecesMidgScore, null);

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

        pawnsScoreSDSHB.reset(         null, EvaluaShow.whitePawnsMatMidg,     EvaluaShow.whitePawnsMatEndg,
                                       null, EvaluaShow.blackPawnsMatMidg,     EvaluaShow.blackPawnsMatEndg);
        knightsScoreSDSHB.reset(       null, EvaluaShow.whiteKnightsMatMidg,   EvaluaShow.whiteKnightsMatEndg,
                                       null, EvaluaShow.blackKnightsMatMidg,   EvaluaShow.blackKnightsMatEndg);
        bishopsScoreSDSHB.reset(       null, EvaluaShow.whiteBishopsMatMidg,   EvaluaShow.whiteBishopsMatEndg,
                                       null, EvaluaShow.blackBishopsMatMidg,   EvaluaShow.blackBishopsMatEndg);
        rooksScoreSDSHB.reset(         null, EvaluaShow.whiteRooksMatMidg,     EvaluaShow.whiteRooksMatEndg,
                                       null, EvaluaShow.blackRooksMatMidg,     EvaluaShow.blackRooksMatEndg);
        queensScoreSDSHB.reset(        null, EvaluaShow.whiteQueensMatMidg,    EvaluaShow.whiteQueensMatEndg,
                                       null, EvaluaShow.blackQueensMatMidg,    EvaluaShow.blackQueensMatEndg);
        shortCgsScoreSDSHB.reset(      null, EvaluaShow.whiteShortCgMatMidg,   null,
                                       null, EvaluaShow.blackShortCgMatMidg,   null);
        longCgsScoreSDSHB.reset(       null, EvaluaShow.whiteLongCgMatMidg,    null,
                                       null, EvaluaShow.blackLongCgMatMidg,    null);
        lonelyKnightsScoreSDSHB.reset( null, EvaluaShow.whiteLonelyKnightMidg, EvaluaShow.whiteLonelyKnightEndg,
                                       null, EvaluaShow.blackLonelyKnightMidg, EvaluaShow.blackLonelyKnightEndg);
        lonelyBishopsScoreSDSHB.reset( null, EvaluaShow.whiteLonelyBishopMidg, EvaluaShow.whiteLonelyBishopEndg,
                                       null, EvaluaShow.blackLonelyBishopMidg, EvaluaShow.blackLonelyBishopEndg);
        lonelyRooksScoreSDSHB.reset(   null, EvaluaShow.whiteLonelyRookMidg,   EvaluaShow.whiteLonelyRookEndg,
                                       null, EvaluaShow.blackLonelyRookMidg,   EvaluaShow.blackLonelyRookEndg);
        sameFBishopsScoreSDSHB.reset(  null, EvaluaShow.whiteSameFBishopsMidg, EvaluaShow.whiteSameFBishopsEndg,
                                       null, EvaluaShow.blackSameFBishopsMidg, EvaluaShow.blackSameFBishopsEndg);
        noPawnsScoreSDSHB.reset(       null, EvaluaShow.whiteNoPawnsMidg,      EvaluaShow.whiteNoPawnsEndg,
                                       null, EvaluaShow.blackNoPawnsMidg,      EvaluaShow.blackNoPawnsEndg);
        allPiecesMidgScoreSDHB.reset(  null, EvaluaShow.allPiecesMidgScore,    null);

    }

    private void newPawnStructureScoresETP() {

        dispersionSDSHB        = new ScoreDisplaySidesHBox("dispersion",
                null, EvaluaShow.whiteDispersionMidg, null,
                null, EvaluaShow.blackDispersionMidg, null);
        distorsionSDSHB        = new ScoreDisplaySidesHBox("distorsion",
                null, EvaluaShow.whiteDistorsionMidg, null,
                null, EvaluaShow.blackDistorsionMidg, null);
        isolaniPawnsScoreSDSHB = new ScoreDisplaySidesHBox("isolani pawns",
                null, EvaluaShow.whiteIsolaniPawnsMidg, EvaluaShow.whiteIsolaniPawnsEndg,
                null, EvaluaShow.blackIsolaniPawnsMidg, EvaluaShow.blackIsolaniPawnsEndg);

        pawnStructureScoresETP.clear();

        int r = -1;
        pawnStructureScoresETP.add(dispersionSDSHB,        0, ++r);
        pawnStructureScoresETP.add(distorsionSDSHB,        0, ++r);
        pawnStructureScoresETP.add(isolaniPawnsScoreSDSHB, 0, ++r);

    }

    private void resetPawnStructureScoresETP() {

        dispersionSDSHB.reset(        null, EvaluaShow.whiteDispersionMidg, null,
                                      null, EvaluaShow.blackDispersionMidg, null);
        distorsionSDSHB.reset(        null, EvaluaShow.whiteDistorsionMidg, null,
                                      null, EvaluaShow.blackDistorsionMidg, null);
        isolaniPawnsScoreSDSHB.reset( null, EvaluaShow.whiteIsolaniPawnsMidg, EvaluaShow.whiteIsolaniPawnsEndg,
                                      null, EvaluaShow.blackIsolaniPawnsMidg, EvaluaShow.blackIsolaniPawnsEndg);

    }

    private void newMobilityScoresETP() {

        pawnsMobilityMDSGP = new MobilityDisplaySidesGridPane("pawns",
                EvaluaShow.whitePawnsStepsMobMidg,
                EvaluaShow.whitePawnsDoubleStepsMobMidg,
                EvaluaShow.whitePawnsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EvaluaShow.blackPawnsStepsMobMidg,
                EvaluaShow.blackPawnsDoubleStepsMobMidg,
                EvaluaShow.blackPawnsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        knightsMobilityMDSGP = new MobilityDisplaySidesGridPane("knights",
                EvaluaShow.whiteKnightsStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.whiteKnightsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EvaluaShow.blackKnightsStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.blackKnightsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        bishopsMobilityMDSGP = new MobilityDisplaySidesGridPane("bishops",
                EvaluaShow.whiteBishopsStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.whiteBishopsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EvaluaShow.blackBishopsStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.blackBishopsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        rooksMobilityMDSGP = new MobilityDisplaySidesGridPane("rooks",
                EvaluaShow.whiteRooksStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.whiteRooksCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EvaluaShow.blackRooksStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.blackRooksCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        queensMobilityMDSGP = new MobilityDisplaySidesGridPane("queens",
                EvaluaShow.whiteQueensStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.whiteQueensCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EvaluaShow.blackQueensStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.blackQueensCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        kingsMobilityMDSGP = new MobilityDisplaySidesGridPane("kings",
                EvaluaShow.whiteKingStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.whiteKingCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EvaluaShow.blackKingStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.blackKingCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        castlingsMobilityMDSGP = new MobilityDisplaySidesGridPane("castlings",
                /*Steps*/null, /*DoubleSteps*/null, /*Captures*/null,
                EvaluaShow.whiteShortCgMobMidg, EvaluaShow.whiteLongCgMobMidg,
                /*Steps*/null, /*DoubleSteps*/null, /*Captures*/null,
                EvaluaShow.blackShortCgMobMidg, EvaluaShow.blackLongCgMobMidg);

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
                EvaluaShow.whitePawnsStepsMobMidg,
                EvaluaShow.whitePawnsDoubleStepsMobMidg,
                EvaluaShow.whitePawnsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EvaluaShow.blackPawnsStepsMobMidg,
                EvaluaShow.blackPawnsDoubleStepsMobMidg,
                EvaluaShow.blackPawnsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        knightsMobilityMDSGP.reset(
                EvaluaShow.whiteKnightsStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.whiteKnightsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EvaluaShow.blackKnightsStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.blackKnightsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        bishopsMobilityMDSGP.reset(
                EvaluaShow.whiteBishopsStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.whiteBishopsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EvaluaShow.blackBishopsStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.blackBishopsCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        rooksMobilityMDSGP.reset(
                EvaluaShow.whiteRooksStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.whiteRooksCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EvaluaShow.blackRooksStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.blackRooksCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        queensMobilityMDSGP.reset(
                EvaluaShow.whiteQueensStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.whiteQueensCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EvaluaShow.blackQueensStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.blackQueensCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        kingsMobilityMDSGP.reset(
                EvaluaShow.whiteKingStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.whiteKingCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null,
                EvaluaShow.blackKingStepsMobMidg,
                /*DoubleSteps*/null,
                EvaluaShow.blackKingCapturesMobMidg,
                /*ShortCg*/null, /*LongCg*/null);

        castlingsMobilityMDSGP.reset(
                /*Steps*/null, /*DoubleSteps*/null, /*Captures*/null,
                EvaluaShow.whiteShortCgMatMidg, EvaluaShow.whiteLongCgMobMidg,
                /*Steps*/null, /*DoubleSteps*/null, /*Captures*/null,
                EvaluaShow.blackShortCgMobMidg, EvaluaShow.blackLongCgMobMidg);

    }

    private void newArrangementScoresETP() {

        pawnsArrgtScoreSDSHB = new ScoreDisplaySidesHBox("pawns",
                /*whiteOpeg*/null, EvaluaShow.whitePawnsArrMidg,         EvaluaShow.whitePawnsArrEndg,
                /*blackOpeg*/null, EvaluaShow.blackPawnsArrMidg,         EvaluaShow.blackPawnsArrEndg);
        knightsArrgtScoreSDSHB = new ScoreDisplaySidesHBox("knights",
                /*whiteOpeg*/null, EvaluaShow.whiteKnightsArrMidg,       EvaluaShow.whiteKnightsArrEndg,
                /*blackOpeg*/null, EvaluaShow.blackKnightsArrMidg,       EvaluaShow.blackKnightsArrEndg);
        bishopsArrgtScoreSDSHB = new ScoreDisplaySidesHBox("bishops",
                /*whiteOpeg*/null, EvaluaShow.whiteBishopsArrMidg,       EvaluaShow.whiteBishopsArrEndg,
                /*blackOpeg*/null, EvaluaShow.blackBishopsArrMidg,       EvaluaShow.blackBishopsArrEndg);
        rooksArrgtScoreSDSHB = new ScoreDisplaySidesHBox("rooks",
                /*whiteOpeg*/null, EvaluaShow.whiteRooksArrMidg,         EvaluaShow.whiteRooksArrEndg,
                /*blackOpeg*/null, EvaluaShow.blackRooksArrMidg,         EvaluaShow.blackRooksArrEndg);
        queensArrgtScoreSDSHB = new ScoreDisplaySidesHBox("queens",
                /*whiteOpeg*/null, EvaluaShow.whiteQueensArrMidg,        EvaluaShow.whiteQueensArrEndg,
                /*blackOpeg*/null, EvaluaShow.blackQueensArrMidg,        EvaluaShow.blackQueensArrEndg);
        kingsArrgtScoreSDSHB = new ScoreDisplaySidesHBox("kings",
                /*whiteOpeg*/null, EvaluaShow.whiteKingArrMidg,          EvaluaShow.whiteKingArrEndg,
                /*blackOpeg*/null, EvaluaShow.blackKingArrMidg,          EvaluaShow.blackKingArrEndg);
        onCtrlledSquaresScoreSDSHB = new ScoreDisplaySidesHBox("on controlled squares",
                /*whiteOpeg*/null, EvaluaShow.whiteOnCtrlledSquareMidg,  /*whiteEndg*/null,
                /*blackOpeg*/null, EvaluaShow.blackOnCtrlledSquareMidg,  /*blackEndg*/null);
        centreControlMidgScoreSDSHB = new ScoreDisplaySidesHBox("center control",
                /*whiteOpeg*/null, EvaluaShow.whiteCentreControlMidg,    /*whiteEndg*/null,
                /*blackOpeg*/null, EvaluaShow.blackCentreControlMidg,    /*blackEndg*/null);
        cRingControlMidgScoreSDSHB = new ScoreDisplaySidesHBox("central ring control",
                /*whiteOpeg*/null, EvaluaShow.whiteCRingControlMidg,     /*whiteEndg*/null,
                /*blackOpeg*/null, EvaluaShow.whiteCRingControlMidg,     /*blackEndg*/null);
        rookOnOpenFileScoreSDSHB = new ScoreDisplaySidesHBox("rooks on open file",
                /*whiteOpeg*/null, EvaluaShow.whiteRookOnOpenFileMidg,   /*whiteEndg*/null,
                /*blackOpeg*/null, EvaluaShow.blackRookOnOpenFileMidg,   /*blackEndg*/null);
        queenOnOpenFileScoreSDSHB = new ScoreDisplaySidesHBox("queens on open file",
                /*whiteOpeg*/null, EvaluaShow.whiteQueenOnOpenFileMidg,  /*whiteEndg*/null,
                /*blackOpeg*/null, EvaluaShow.blackQueenOnOpenFileMidg,  /*blackEndg*/null);
        rookOnSemiOFileScoreSDSHB = new ScoreDisplaySidesHBox("rooks on semi-open file",
                /*whiteOpeg*/null, EvaluaShow.whiteRookOnSemiOFileMidg,  /*whiteEndg*/null,
                /*blackOpeg*/null, EvaluaShow.blackRookOnSemiOFileMidg,  /*blackEndg*/null);
        queenOnSemiOFileScoreSDSHB = new ScoreDisplaySidesHBox("queens on semi-open file",
                /*whiteOpeg*/null, EvaluaShow.whiteQueenOnSemiOFileMidg, /*whiteEndg*/null,
                /*blackOpeg*/null, EvaluaShow.blackQueenOnSemiOFileMidg, /*blackEndg*/null);
        passedPawnsScoreSDSHB = new ScoreDisplaySidesHBox("passed pawns",
                /*whiteOpeg*/null, EvaluaShow.whitePassedPawnsMidg,      EvaluaShow.whitePassedPawnsEndg,
                /*blackOpeg*/null, EvaluaShow.blackPassedPawnsMidg,      EvaluaShow.blackPassedPawnsEndg);

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
                /*whiteOpeg*/null, EvaluaShow.whitePawnsArrMidg,         EvaluaShow.whitePawnsArrEndg,
                /*blackOpeg*/null, EvaluaShow.blackPawnsArrMidg,         EvaluaShow.blackPawnsArrEndg);
        knightsArrgtScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whiteKnightsArrMidg,       EvaluaShow.whiteKnightsArrEndg,
                /*blackOpeg*/null, EvaluaShow.blackKnightsArrMidg,       EvaluaShow.blackKnightsArrEndg);
        bishopsArrgtScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whiteBishopsArrMidg,       EvaluaShow.whiteBishopsArrEndg,
                /*blackOpeg*/null, EvaluaShow.blackBishopsArrMidg,       EvaluaShow.blackBishopsArrEndg);
        rooksArrgtScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whiteRooksArrMidg,         EvaluaShow.whiteRooksArrEndg,
                /*blackOpeg*/null, EvaluaShow.blackRooksArrMidg,         EvaluaShow.blackRooksArrEndg);
        queensArrgtScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whiteQueensArrMidg,        EvaluaShow.whiteQueensArrEndg,
                /*blackOpeg*/null, EvaluaShow.blackQueensArrMidg,        EvaluaShow.blackQueensArrEndg);
        kingsArrgtScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whiteKingArrMidg,          EvaluaShow.whiteKingArrEndg,
                /*blackOpeg*/null, EvaluaShow.blackKingArrMidg,          EvaluaShow.blackKingArrEndg);
        onCtrlledSquaresScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whiteOnCtrlledSquareMidg,  /*whiteEndg*/null,
                /*blackOpeg*/null, EvaluaShow.blackOnCtrlledSquareMidg,  /*blackEndg*/null);
        centreControlMidgScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whiteCentreControlMidg,    /*whiteEndg*/null,
                /*blackOpeg*/null, EvaluaShow.blackCentreControlMidg,    /*blackEndg*/null);
        cRingControlMidgScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whiteCRingControlMidg,     /*whiteEndg*/null,
                /*blackOpeg*/null, EvaluaShow.blackCRingControlMidg,     /*blackEndg*/null);
        rookOnOpenFileScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whiteRookOnOpenFileMidg,   /*whiteEndg*/null,
                /*blackOpeg*/null, EvaluaShow.blackRookOnOpenFileMidg,   /*blackEndg*/null);
        queenOnOpenFileScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whiteQueenOnOpenFileMidg,  /*whiteEndg*/null,
                /*blackOpeg*/null, EvaluaShow.blackQueenOnOpenFileMidg,  /*blackEndg*/null);
        rookOnSemiOFileScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whiteRookOnSemiOFileMidg,  /*whiteEndg*/null,
                /*blackOpeg*/null, EvaluaShow.blackRookOnSemiOFileMidg,  /*blackEndg*/null);
        queenOnSemiOFileScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whiteQueenOnSemiOFileMidg, /*whiteEndg*/null,
                /*blackOpeg*/null, EvaluaShow.blackQueenOnSemiOFileMidg, /*blackEndg*/null);
        passedPawnsScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whitePassedPawnsMidg,      EvaluaShow.whitePassedPawnsEndg,
                /*blackOpeg*/null, EvaluaShow.blackPassedPawnsMidg,      EvaluaShow.blackPassedPawnsEndg);

    }

    private void newKingSafetyScoresETP() {

        shieldPawnLackScoreSDSHB = new ScoreDisplaySidesHBox("shield pawn holes",
                /*whiteOpeg*/null, EvaluaShow.whiteShieldPawnsMidg,    /*whiteEndg*/null,
                /*blackOpeg*/null, EvaluaShow.blackShieldPawnsMidg,    /*whiteEndg*/null);
        kingAttackScoreSDSHB     = new ScoreDisplaySidesHBox("attacked king",
                /*whiteOpeg*/null, EvaluaShow.whiteAttackedKingMidg,   EvaluaShow.whiteAttackedKingEndg,
                /*blackOpeg*/null, EvaluaShow.blackAttackedKingMidg,   EvaluaShow.blackAttackedKingEndg);
        kRing1AttackScoreSDSHB   = new ScoreDisplaySidesHBox("attacked king ring 1",
                /*whiteOpeg*/null, EvaluaShow.whiteAttackedKRing1Midg, EvaluaShow.whiteAttackedKRing1Endg,
                /*blackOpeg*/null, EvaluaShow.blackAttackedKRing1Midg, EvaluaShow.blackAttackedKRing1Endg);
        kRing2AttackScoreSDSHB   = new ScoreDisplaySidesHBox("attacked king ring 2",
                /*whiteOpeg*/null, EvaluaShow.whiteAttackedKRing2Midg, EvaluaShow.whiteAttackedKRing2Endg,
                /*blackOpeg*/null, EvaluaShow.blackAttackedKRing2Midg, EvaluaShow.blackAttackedKRing2Endg);

        kingSafetyScoresETP.clear();

        int r = -1;
        kingSafetyScoresETP.add(shieldPawnLackScoreSDSHB, 0, ++r);
        kingSafetyScoresETP.add(kingAttackScoreSDSHB,     0, ++r);
        kingSafetyScoresETP.add(kRing1AttackScoreSDSHB,   0, ++r);
        kingSafetyScoresETP.add(kRing2AttackScoreSDSHB,   0, ++r);

    }

    private void resetKingSafetyScoresETP() {

        shieldPawnLackScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whiteShieldPawnsMidg,    /*whiteEndg*/null,
                /*blackOpeg*/null, EvaluaShow.blackShieldPawnsMidg,    /*whiteEndg*/null);
        kingAttackScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whiteAttackedKingMidg,   EvaluaShow.whiteAttackedKingEndg,
                /*blackOpeg*/null, EvaluaShow.blackAttackedKingMidg,   EvaluaShow.blackAttackedKingEndg);
        kRing1AttackScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whiteAttackedKRing1Midg, EvaluaShow.whiteAttackedKRing1Endg,
                /*blackOpeg*/null, EvaluaShow.blackAttackedKRing1Midg, EvaluaShow.blackAttackedKRing1Endg);
        kRing2AttackScoreSDSHB.reset(
                /*whiteOpeg*/null, EvaluaShow.whiteAttackedKRing2Midg, EvaluaShow.whiteAttackedKRing2Endg,
                /*blackOpeg*/null, EvaluaShow.blackAttackedKRing2Midg, EvaluaShow.blackAttackedKRing2Endg);

    }

}
