package application.app.test;

import application.app.DebugUtils;
import application.uci.UciEngine;
import engine.model.Engine;
import engine.model.Move;
import engine.model.Node;
import model.elements.Kinds;
import model.elements.Zobrist;
import model.game.Game;
import model.game.Player;
import model.utilities.FenStrings;
import view.options.UserOptions;

public class MainTest {

    private static Game game;

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args)
    		throws Exception {

        UserOptions.load();

        Zobrist.setZobristConstants();

        // perft test
        if (false) perftTest();

        // debug -- check elements
        if (false) checkElements();
        if (false) doUndoTest();
        if (false) intMoveTest();

        // debug -- evaluation
        if (false) showEvaluation();
        if (false) evaluationTest();

        // check -- search features
        if (false) checkIncrTest();
        if (false) searchFeaturesTest();
        if (false) qChecksTest();
        if (true) tMapTest();

        // check -- moves generation
        if (false) moveOrderingTest();
        if (false) selectionSortTest();

        // check -- time managing
        if (false) timeManagingTest();

        // check -- search results
        if (false) shortestCheckMate();
        if (false) shortestStalemate();
        if (false) mateTest();
        if (false) stalemateTest();

        // utilities
        if (false) stepByStepDebug();

        // uci
        if (false) uciTest();

    }

    private static void perftTest()
            throws Exception {

        Engine e = Engine.newMiniMaxEngine();
        e.delayedLegalityCheck = false;
        e.delayedMateCheck     = true;  // per verificare il numero di matti

        Node n = new Node(FenStrings.PFT_1);    e.searchDepth = 5;
//        Node n = new Node(FenStrings.PFT_2);    e.searchDepth = 4;
//        Node n = new Node(FenStrings.PFT_3);    e.searchDepth = 5;
//        Node n = new Node(FenStrings.PFT_4);    e.searchDepth = 4;
//        Node n = new Node(FenStrings.PFT_4m);   e.searchDepth = 4;
//        Node n = new Node(FenStrings.PFT_5);    e.searchDepth = 4;
//        Node n = new Node(FenStrings.PFT_6);    e.searchDepth = 4;

        Player p = new Player(Kinds.HUMAN, e);

        Game g = Game.newDefaultAnalysisGame(n, p);

        e.perftTest(g, n);

    }

    private static void checkElements()
            throws Exception {

        Node n = new Node(FenStrings.STD);
        Move m = new Move("e7e8q", n);

        System.out.println(m);

    }

    private static void doUndoTest()
            throws Exception {

        DebugUtils.nodeDoUndoDebug = true;

        Engine e = Engine.newMiniMaxEngine();
        e.delayedLegalityCheck = false;
        e.delayedMateCheck     = true;  // per verificare il numero di matti

        Node n = new Node(FenStrings.PFT_1);    e.searchDepth = 5;
//        Node n = new Node(FenStrings.PFT_2);    e.searchDepth = 4;
//        Node n = new Node(FenStrings.PFT_3);    e.searchDepth = 5;
//        Node n = new Node(FenStrings.PFT_4);    e.searchDepth = 4;
//        Node n = new Node(FenStrings.PFT_4m);   e.searchDepth = 4;
//        Node n = new Node(FenStrings.PFT_5);    e.searchDepth = 4;
//        Node n = new Node(FenStrings.PFT_6);    e.searchDepth = 4;

        Player p = new Player(Kinds.HUMAN, e);

        Game g = Game.newDefaultAnalysisGame(n, p);

        e.perftTest(g, n);

    }

    private static void intMoveTest()
            throws Exception {

        DebugUtils.movesListDebug = true;

        Node node;

        //node = new Node("K1k4b/6P1/8/8/8/8/8/8 w - - 0 1");
        node = new Node(FenStrings.STD);
        System.out.println(node.toStringDebug());

        Engine engine = new Engine();
        engine.computePlayerMovesBb(node);

        Move move;

        for (int m = 0; m <= engine.movesListMaxIndex; ++m) {
            move = engine.movesList[m];
            move.orderValue = (int) (Math.random() * 10000000);
            System.out.println(move.toString() + "(" + move.orderValue + ")");

            move = new Move(move.toIntMove());
            System.out.println(move.toString() + "(" + move.orderValue + ")");
        }

    }

    private static void checkIncrTest()
            throws Exception {

        DebugUtils.searchStepByStepDebug = true;

        Engine e = Engine.newMiniMaxEngine();
        e.searchCheckIncrement = true; // *
        e.searchDepth          = 1;

        String fenString = "4k3/8/8/8/8/R7/8/4K3 w - - 0 1";

        Node n = new Node(fenString);

        Player p = new Player(Kinds.HUMAN, e);

        Game g = Game.newDefaultAnalysisGame(n, p);

        e.nextMove(g, n);

    }

    private static void qChecksTest()
            throws Exception {

        DebugUtils.searchStepByStepDebug = true;

        Engine e = Engine.newMiniMaxEngine();
        e.searchCheckIncrement = true;
//        e.iterDeepeningSearch  = true;
        e.quiescentPosSearch   = true; // *
        e.qChecksSearch        = true; // x
        e.qSearchAddedDepth    = 1;
        e.searchDepth          = 1;

        String fenString;
        fenString = "4k3/7p/8/8/8/8/8/4K2R b K - 0 1"; // cattura, arrocco e scacchi

        Node n = new Node(fenString);

        Player p = new Player(Kinds.HUMAN, e);

        Game g = Game.newDefaultAnalysisGame(n, p);

        e.nextMove(g, n);

    }

    private static void tMapTest()
            throws Exception {

        DebugUtils.searchStepByStepDebug = true;

        Engine e = Engine.newMiniMaxEngine();
        e.alphaBetaPruning         = true;
        e.delayedLegalityCheck     = true;
        e.delayedMateCheck         = true;
        e.searchCheckIncrement     = true;
        e.iterDeepeningSearch      = true;
            e.quiescentPosSearch   = true;
            e.qChecksSearch        = true;
            e.qSearchAddedDepth    = 3;
        e.principalVarSearch       = true;
        e.killerHeuristic          = true;
        e.historyHeuristic         = true;
        e.transpositionsMap        = true;

//        e.searchDepth              = 3;

        String fenString;
        fenString = "7k/R7/1R6/8/8/8/8/7K w - - 0 1";
//        fenString = "4k3/8/8/8/7p/7r/3PPP2/4K3 b K - 0 1";  // nero muove e matta
//        fenString = "3rkr2/8/8/8/8/8/6r1/4K3 w K - 0 1";  // bianco in stallo

        Node n = new Node(fenString);

        Player p = new Player(Kinds.HUMAN, e);

        Game g = Game.newDefaultAnalysisGame(n, p);

        e.nextMove(g, n);

        System.exit(0);
    }

    private static void searchFeaturesTest()
            throws Exception {

        int searchDepthMax = 7;

        for (int i = 0; i <= FenStrings.GRA_LIST.length - 1; ++i) {

            System.out.println((i + 1) + "/" + FenStrings.GRA_LIST.length);

            String fenString = FenStrings.GRA_LIST[i];

            DebugUtils.searchNextMoveDebug = true;

            Node n1 = new Node(fenString);
            Engine e1 = Engine.newMiniMaxEngine();
            e1.alphaBetaPruning     = true;
            e1.delayedLegalityCheck = true;
            e1.delayedMateCheck     = true;
            e1.searchCheckIncrement = true;
            e1.iterDeepeningSearch  = true;
            e1.quiescentPosSearch   = true;
                e1.qChecksSearch    = true;
            e1.principalVarSearch   = true;
            e1.killerHeuristic      = true;
            e1.historyHeuristic     = false;
            e1.transpositionsMap    = false;
            e1.searchDepth          = searchDepthMax;
            Player p1 = new Player(Kinds.HUMAN, e1);
            Game g1 = Game.newDefaultAnalysisGame(n1, p1);
            e1.nextMove(g1, n1);

            Node n2 = new Node(fenString);
            Engine e2 = Engine.newMiniMaxEngine();
            e2.alphaBetaPruning     = true;
            e2.delayedLegalityCheck = true;
            e2.delayedMateCheck     = true;
            e2.searchCheckIncrement = true;
            e2.iterDeepeningSearch  = true;
            e2.quiescentPosSearch   = true;
                e2.qChecksSearch    = true;
            e2.principalVarSearch   = true;
            e2.killerHeuristic      = false;
            e2.historyHeuristic     = true;
            e2.transpositionsMap    = false;
            e2.searchDepth          = searchDepthMax;
            Player p2 = new Player(Kinds.HUMAN, e2);
            Game g2 = Game.newDefaultAnalysisGame(n2, p2);
            e2.nextMove(g2, n2);

        }

    }

    private static void showEvaluation()
            throws Exception {

        Node n = new Node(FenStrings.GRA_7);

        System.out.println(n.toStringDebug());

        Engine e = new Engine();
        e.evaluation(n);

        System.out.println("e.midg=" + e.midg);
        System.out.println("e.endg=" + e.endg);
        System.out.println("e.taperedScore=" + e.taperedScore);

    }

    private static void evaluationTest()
            throws Exception {

        DebugUtils.searchNextMoveDebug    = true;
//        DebugUtils.evaluationFlipPosDebug = true; // x
        DebugUtils.evaluaShowFlipPosDebug = true; // x
//        DebugUtils.evaluaTionVsShowDebug  = true; // x

        Node n = new Node(FenStrings.GRA_3);

        Engine e = new Engine();

        Player p = new Player(Kinds.HUMAN, e);

        Game g = Game.newDefaultAnalysisGame(n, p);

        e.nextMove(g, n);

    }

    private static void moveOrderingTest()
            throws Exception {

        DebugUtils.searchStepByStepDebug = true; // *
        DebugUtils.movesListDebug        = true; // *
        DebugUtils.moveOrderingDebug     = true;
//        DebugUtils.nodeIndentString      = true;
//        DebugUtils.selectionSortDebug    = true;

        String fenString;
//        fenString = FenStrings.MA3_2;
        fenString = "7k/R7/1R6/8/8/8/8/7K w - - 0 1";

        Node n = new Node(fenString);

        Engine e = Engine.newMiniMaxEngine();
        e.searchDepth = 4;

        Player p = new Player(Kinds.HUMAN, e);

        Game g = Game.newDefaultAnalysisGame(n, p);

        e.nextMove(g, n);

    }

    private static void selectionSortTest()
            throws Exception {

        DebugUtils.searchStepByStepDebug = true; // *
        DebugUtils.selectionSortDebug    = true; // *

        Node n = new Node(FenStrings.MA3_2);

        Engine e = Engine.newMiniMaxEngine();
        e.principalVarSearch = true; // *
        e.searchDepth        = 1;

        Player p = new Player(Kinds.HUMAN, e);

        Game g = Game.newDefaultAnalysisGame(n, p);

        e.nextMove(g, n);

    }

    private static void timeManagingTest()
            throws Exception {

        DebugUtils.searchNextMoveDebug = true;
        DebugUtils.timeManagingDebug   = true;

        Node n;
        n = new Node(FenStrings.SBD_1);

        Engine e = new Engine();

        Player p = new Player(Kinds.HUMAN, e);

        Game g = Game.newDefaultAnalysisGame(n, p);
        g.timeControlSet = true;
        g.whitePlayer = p;
        g.whiteStartLeftTimeMillis = 2 * 60000;
        g.whiteTimeIncrementMillis = 1000;
        g.whiteLeftTimeMillis = g.whiteStartLeftTimeMillis;
        g.blackPlayer = p;
        g.blackStartLeftTimeMillis = 2 * 60000;
        g.blackTimeIncrementMillis = 1000;
        g.blackLeftTimeMillis = g.blackStartLeftTimeMillis;

        e.nextMove(g, n);

    }

    private static void shortestCheckMate()
            throws Exception {

//        DebugUtils.searchStepByStepDebug = true;
        DebugUtils.searchNextMoveDebug   = true;
//        DebugUtils.nodeIndentString      = true;
        DebugUtils.movesListDebug        = true;
//        DebugUtils.movesListScoreDebug   = true;
//        DebugUtils.timeManagingDebug     = true;

        String fenString;

        // Rb8# - con un puro minimax a profondità 3 sceglierebbe 1.Rc6 (1...Kg8, 2.Rc8#)
        fenString = "7k/R7/1R6/8/8/8/8/7K w - - 0 1";

        Node n = new Node(fenString);

        Engine e = Engine.newMiniMaxEngine();
        e.alphaBetaPruning        = true;
        e.delayedLegalityCheck    = true;
        e.delayedMateCheck        = true;
        e.searchCheckIncrement    = true;
        e.iterDeepeningSearch     = true;
        e.quiescentPosSearch      = true;
            e.qSearchAddedDepth   = 3;
            e.qChecksSearch       = true;
        e.principalVarSearch      = true;
        e.killerHeuristic         = true;
        e.historyHeuristic        = true;
        e.transpositionsMap       = true;
//        e.searchDepth             = 3;

        Player p = new Player(Kinds.HUMAN, e);

        Game g = Game.newDefaultAnalysisGame(n, p);

        e.nextMove(g, n);

    }

    private static void shortestStalemate()
            throws Exception {

        DebugUtils.searchStepByStepDebug = true;
//        DebugUtils.searchNextMoveDebug   = true;
        DebugUtils.movesListDebug        = true;
//        DebugUtils.timeManagingDebug     = true;

        String fenString;
        fenString = FenStrings.SM_1;

        Node n = new Node(fenString);

        Engine e = Engine.newMiniMaxEngine();
        e.alphaBetaPruning        = true;
        e.delayedLegalityCheck    = true;
        e.delayedMateCheck        = true;
        e.searchCheckIncrement    = true;
        e.iterDeepeningSearch     = false;
        e.quiescentPosSearch      = false;
            e.qSearchAddedDepth   = 3;
            e.qChecksSearch       = false;
        e.principalVarSearch      = false;
        e.killerHeuristic         = true;
        e.historyHeuristic        = true;
        e.transpositionsMap       = false;
        e.searchDepth             = 3;

        Player p = new Player(Kinds.HUMAN, e);

        Game g = Game.newDefaultAnalysisGame(n, p);

        e.nextMove(g, n);

    }

    private static void mateTest()
            throws Exception {

        DebugUtils.searchStepByStepDebug = false;
        DebugUtils.searchNextMoveDebug   = true;
        DebugUtils.timeManagingDebug     = false;

        String fenString;
        fenString = FenStrings.MA10_1;

        Node n = new Node(fenString);

        Engine e = new Engine();
        e.searchDepth = 10;

        Player p = new Player(Kinds.HUMAN, e);

        Game g = Game.newDefaultAnalysisGame(n, p);

        e.nextMove(g, n);

    }

    private static void stalemateTest()
            throws Exception {

        DebugUtils.searchStepByStepDebug = false;
        DebugUtils.searchNextMoveDebug   = true;
        DebugUtils.timeManagingDebug     = false;

        String fenString;
//        fenString = FenStrings.SM_2;  // ok
        fenString = FenStrings.SM_3;  // KO, troppo lontano

        Node n = new Node(fenString);

        Engine e = new Engine();
        e.searchDepth = 10;

        Player p = new Player(Kinds.HUMAN, e);

        Game g = Game.newDefaultAnalysisGame(n, p);

        e.nextMove(g, n);

    }

    private static void stepByStepDebug()
            throws Exception {

        DebugUtils.searchStepByStepDebug = true;
        DebugUtils.searchNextMoveDebug   = false;
        DebugUtils.timeManagingDebug     = true;

        String fenString;
        fenString = FenStrings.STD;

        Node n = new Node(fenString);

        Engine e = new Engine();
        e.quiescentPosSearch  = false;
        e.principalVarSearch  = false;
        e.iterDeepeningSearch = false;
        e.searchDepth         = 6;

        Player p = new Player(Kinds.HUMAN, e);

        Game g = Game.newDefaultAnalysisGame(n, p);

        e.nextMove(g, n);

    }

    private static void uciTest()
            throws Exception {

        DebugUtils.searchNextMoveDebug     = true;
//        DebugUtils.searchStepByStepDebug   = true;
//        DebugUtils.selectionSortDebug      = true;
//        DebugUtils.timeManagingDebug       = true;
//        DebugUtils.endOfGameDebug          = true;
//        DebugUtils.pvMapManagingDebug      = true;
//        DebugUtils.nodeIndentString        = true;

        Engine e = Engine.newMiniMaxEngine();
        e.alphaBetaPruning        = true;
        e.delayedLegalityCheck    = true;
        e.delayedMateCheck        = true;
        e.searchCheckIncrement    = true;
        e.iterDeepeningSearch     = true;
        e.quiescentPosSearch      = true;
            e.qSearchAddedDepth   = 3;
            e.qChecksSearch       = true;
        e.principalVarSearch      = true;
        e.killerHeuristic         = true;
        e.historyHeuristic        = true;
        e.transpositionsMap       = true;
//        e.searchDepth             = 2;

        Player p = new Player(Kinds.HUMAN, e);

        String fenString;
        fenString = FenStrings.STD;

        Node n = new Node(fenString);

        Game g = Game.newDefaultAnalysisGame(n, p);

        UciEngine.init(e, g);
        UciEngine.scanCommand();

    }

}
