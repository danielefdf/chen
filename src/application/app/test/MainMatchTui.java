package application.app.test;

import application.app.DebugUtils;
import application.app.MainUtils;
import application.app.MatchTui;
import engine.model.Engine;
import engine.model.Node;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.elements.Kinds;
import model.elements.Zobrist;
import model.game.Game;
import model.game.GameType;
import model.game.Player;
import model.utilities.FenStrings;
import view.options.UserOptions;

/**
 * Per il gioco tra umani e motori, in ogni combinazione.
 * Lancia un'interfaccia testuale, in cui è possibile vedere
 * lo svolgimento della partita e inserire le mosse.
 * Gestisce comunque JavaFx perché per i Thread ho usato JavaFx.
 */
public class MainMatchTui extends Application {

    private static Game game;

    private static MatchTui matchTui;

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) {

        UserOptions.load();

        Zobrist.setZobristConstants();

        // TUI match
        Platform.runLater(() -> {
            try {

                if (true) runMatchTui();
                if (false) runMatchesLoopTui();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private static void runMatchTui()
            throws Exception {

        setMatch();

        matchTui = new MatchTui();

        if (game != null) {
            matchTui.startMatch(game);
        }

    }

    private static void runMatchesLoopTui()
            throws Exception {

        runMatchTuiN(0);

    }

    private static void runMatchTuiN(int fenIndex)
            throws Exception {

        setMatch();

        String fenString;
        //fenString = FenStrings.BKT_5;
        //fenString = FenStrings.OPENINGS_LIST[fenIndex];
        fenString = FenStrings.GRA_LIST[fenIndex];

        game.startNode = new Node(fenString);

        matchTui = new MatchTui();

        matchTui.setGameBackgroundListener(() -> {

            Thread.sleep(MainUtils.THREAD_GAME_PAUSE_MILLIS);

            game = null;
            matchTui = null;
            System.gc();

            if (fenIndex + 1 <= FenStrings.OPENINGS_LIST.length - 1) {
                runMatchTuiN(fenIndex + 1);
            }

        });

        if (game != null) {
            matchTui.startMatch(game);
        }

    }

    private static void setMatch()
            throws Exception {

        DebugUtils.searchNextMoveDebug     = true;
//        DebugUtils.searchStepByStepDebug   = true;
//        DebugUtils.selectionSortDebug      = true;
//        DebugUtils.timeManagingDebug       = true;
//        DebugUtils.endOfGameDebug          = true;
//        DebugUtils.pvMapManagingDebug      = true;
//        DebugUtils.nodeIndentString        = true;

        String fenString;
        fenString = FenStrings.STD;
        //fenString = "7k/R7/1R6/8/8/8/8/7K b - - 0 1";

        Node n = new Node(fenString);

        Engine we = Engine.newMiniMaxEngine();
        we.alphaBetaPruning        = true;
        we.delayedLegalityCheck    = true;
        we.delayedMateCheck        = true;
        we.searchCheckIncrement    = true;
        we.iterDeepeningSearch     = true;
        we.quiescentPosSearch      = true;
            we.qSearchAddedDepth   = 5;
            we.qChecksSearch       = true;
        we.principalVarSearch      = true;
        we.killerHeuristic         = true;
        we.historyHeuristic        = true;
        we.transpositionsMap       = true;
//        we.searchDepth             = 2;

        Engine be = Engine.newMiniMaxEngine();
        be.alphaBetaPruning        = true;
        be.delayedLegalityCheck    = true;
        be.delayedMateCheck        = true;
        be.searchCheckIncrement    = true;
        be.iterDeepeningSearch     = true;
        be.quiescentPosSearch      = true;
            be.qSearchAddedDepth   = 5;
            be.qChecksSearch       = true;
        be.principalVarSearch      = true;
        be.killerHeuristic         = true;
        be.historyHeuristic        = true;
        be.transpositionsMap       = true;
//        be.searchDepth             = 2;

        Player wp = new Player(Kinds.ENGINE, we);
        Player bp = new Player(Kinds.ENGINE, be);
        Player ap = new Player(Kinds.ENGINE, new Engine());

        game = new Game();
        game.gameType = GameType.MATCH;
        game.timeControlSet = true;
        game.whitePlayer = wp;
        game.whiteStartLeftTimeMillis = 2 * 60000;
        game.whiteTimeIncrementMillis = 1000;
        game.whiteLeftTimeMillis = game.whiteStartLeftTimeMillis;
        game.blackPlayer = bp;
        game.blackStartLeftTimeMillis = 2 * 60000;
        game.blackTimeIncrementMillis = 1000;
        game.blackLeftTimeMillis = game.blackStartLeftTimeMillis;
        game.startNode = n;
        game.analysisPlayer = ap;

    }

    @Override
    public void init() {}

    @Override
    public void start(Stage s) {}

}
