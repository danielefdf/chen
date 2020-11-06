package application.app.test;

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
import view.game.GameStage;
import view.options.UserOptions;
import view.splash.SplashStage;

/**
 * Per il gioco tra umani e motori, in ogni combinazione.
 * Lancia un'interfaccia grafica in JavaFx.
 */
public class MainMatchGui extends Application {

    /* default stage: start(Stage initStage) */
    private static SplashStage splashStage;
    private static GameStage gameStage;

    private static Game game;

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) {

        UserOptions.load();

        Zobrist.setZobristConstants();

        // lancia GUI da zero
        if (true) launch(args);

        // lancia GUI con match preimpostato
        Platform.runLater(() -> {
            try {

                if (false) runAnalysis();
                if (false) runMatchGui();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private static void runAnalysis()
            throws Exception {

        setAnalysis();

        gameStage = new GameStage();
        gameStage.show();

        if (game != null) {
            gameStage.startNewGame(game, GameType.ANALYSIS);
        }

    }

    private static void setAnalysis()
            throws Exception {

        String fenString;
        fenString = FenStrings.GRA_7;

        Node n = new Node(fenString);

        Engine ae = Engine.newMiniMaxEngine();
        ae.alphaBetaPruning        = true;
        ae.delayedLegalityCheck    = true;
        ae.delayedMateCheck        = true;
        ae.searchCheckIncrement    = true;
        ae.iterDeepeningSearch     = true;
        ae.quiescentPosSearch      = true;
            ae.qSearchAddedDepth   = 3;
//            ae.qChecksSearch       = true;
        ae.principalVarSearch      = true;
        ae.killerHeuristic         = true;
        ae.historyHeuristic        = true;
        ae.transpositionsMap       = true;
//        ae.searchDepth             = 2;

        Player ap = new Player(Kinds.ENGINE, ae);

        game = new Game();
        game.gameType = GameType.ANALYSIS;
        game.timeControlSet = false;
        game.whitePlayer = ap;
        game.whiteStartLeftTimeMillis = 2 * 60000;
        game.whiteTimeIncrementMillis = 1000;
        game.whiteLeftTimeMillis = game.whiteStartLeftTimeMillis;
        game.blackPlayer = ap;
        game.blackStartLeftTimeMillis = 2 * 60000;
        game.blackTimeIncrementMillis = 1000;
        game.blackLeftTimeMillis = game.blackStartLeftTimeMillis;
        game.analysisPlayer = ap;
        game.startNode = n;

    }

    private static void runMatchGui()
            throws Exception {

        setMatch();

        gameStage = new GameStage();
        gameStage.show();

        if (game != null) {
            gameStage.startNewGame(game, GameType.MATCH);
        }

    }

    private static void setMatch()
            throws Exception {

        String fenString;
        fenString = FenStrings.STD;
//        fenString = "7k/R7/1R6/8/8/8/8/7K w - - 0 1";

        Node n = new Node(fenString);

        Engine we = Engine.newMiniMaxEngine();
        we.alphaBetaPruning        = true;
        we.delayedLegalityCheck    = true;
        we.delayedMateCheck        = true;
        we.searchCheckIncrement    = true;
        we.iterDeepeningSearch     = true;
        we.quiescentPosSearch      = true;
            we.qSearchAddedDepth   = 3;
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
            be.qSearchAddedDepth   = 3;
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
    public void init() {
        Platform.runLater(() -> splashStage = new SplashStage());
    }

    @Override
    public void start(Stage initStage) {

        if (UserOptions.showSplashOption) {
            //noinspection Convert2MethodRef
            splashStage.showSplash(() -> showMainStage());
        } else {
            showMainStage();
        }

    }

    private void showMainStage() {

        try {
            gameStage = new GameStage();
            gameStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
