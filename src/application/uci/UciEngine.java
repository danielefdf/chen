package application.uci;

import engine.model.Engine;
import engine.model.Move;
import engine.model.Node;
import model.elements.Colors;
import model.game.Game;
import model.notations.Pan;
import model.utilities.FenStrings;

import java.util.Scanner;

/**
 * Consente la comunicazione tra Engine e GUI, secondo il protocollo UCI.
 * I comandi e i parametri errati vengono ignorati.
 */
public class UciEngine {

    public static Engine engine;
    public static Game game;
    public static Node node;

    public static void init(Engine newEngine, Game newGame) {
        engine = newEngine;
        game = newGame;
    }

    public static void scanCommand()
            throws Exception {

        try (Scanner inputScanner = new Scanner(System.in)) {
            //while (true) {
            while (inputScanner.hasNext()) {
                String inputString = inputScanner.nextLine();
                /**/ if (inputString.equals("uci"))            inputUci();
                else if (inputString.equals("isready"))        inputIsReady();
                else if (inputString.startsWith("setoption"))  inputSetOption(inputString);
                else if (inputString.equals("ucinewgame"))     inputUciNewGame();
                else if (inputString.startsWith("position"))   inputPosition(inputString);
                else if (inputString.startsWith("go"))         inputGo(inputString);
                else if (inputString.equals("stop"))           inputStop();
                else if (inputString.equals("quit"))           inputQuit();
            }
        }

    }

    public static void inputUci() {

        System.out.println("id name Chessgame");
        System.out.println("id author Daniele Filippone");

        System.out.println("option name alphaBetaPruning     type check default true");
        System.out.println("option name delayedLegalityCheck type check default true");
        System.out.println("option name delayedMateCheck     type check default true");
        System.out.println("option name sortedMovesNumber    type spin default 7 min 0 max 64");
        System.out.println("option name searchCheckIncrement type check default true");
        System.out.println("option name quiescentPosSearch   type check default true");
        System.out.println("option name qSearchAddedDepth    type spin default 5 min 0 max 64");
        System.out.println("option name qChecksSearch        type check default true");
        System.out.println("option name iterDeepeningSearch  type check default true");
        System.out.println("option name principalVarSearch   type check default true");
        System.out.println("option name killerHeuristic      type check default true");
        System.out.println("option name historyHeuristic     type check default true");
        System.out.println("option name transpositionsMap    type check default true");

        System.out.println("uciok");

    }

    public static void inputIsReady() {
        System.out.println("readyok");
    }

    public static void inputSetOption(final String inputString) {

        String string = UciUtils.substringAfter(inputString, "name");

        String valueString = UciUtils.wordAfter(inputString, "value");

        switch (string) {
            case "alphaBetaPruning":
                engine.alphaBetaPruning = Boolean.getBoolean(valueString);
                break;
            case "delayedLegalityCheck":
                engine.delayedLegalityCheck = Boolean.getBoolean(valueString);
                break;
            case "delayedMateCheck":
                engine.delayedMateCheck = Boolean.getBoolean(valueString);
                break;
            case "sortedMovesNumber":
                Integer smn = Integer.getInteger(valueString);
                if (smn != null && smn >= 0 && smn <= 64) {
                    engine.sortedMovesNumber = smn;
                }
                break;
            case "searchCheckIncrement":
                engine.searchCheckIncrement = Boolean.getBoolean(valueString);
                break;
            case "quiescentPosSearch":
                engine.quiescentPosSearch = Boolean.getBoolean(valueString);
                break;
            case "qSearchAddedDepth":
                Integer qsad = Integer.getInteger(valueString);
                if (qsad != null && qsad >= 0 && qsad <= 64) {
                    engine.qSearchAddedDepth = qsad;
                }
                break;
            case "qChecksSearch":
                engine.qChecksSearch = Boolean.getBoolean(valueString);
                break;
            case "iterDeepeningSearch":
                engine.iterDeepeningSearch = Boolean.getBoolean(valueString);
                break;
            case "principalVarSearch":
                engine.principalVarSearch = Boolean.getBoolean(valueString);
                break;
            case "killerHeuristic":
                engine.killerHeuristic = Boolean.getBoolean(valueString);
                break;
            case "historyHeuristic":
                engine.historyHeuristic = Boolean.getBoolean(valueString);
                break;
            case "transpositionsMap":
                engine.transpositionsMap = Boolean.getBoolean(valueString);
                break;
        }
    }

    public static void inputUciNewGame() {

        game = new Game();

        engine.initGameTables();
        engine.initMaps();
        engine.initHeuristicsLists();

    }

    public static void inputPosition(final String inputString)
            throws Exception {

        // position fen rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
        // position startpos moves e2e4

        String string = UciUtils.substringAfter(inputString, "position");

        if (string.contains("startpos")) {
            game.startNode = new Node(FenStrings.STD);
        } else if (string.contains("fen")) {
            string = UciUtils.substringAfter(string, "fen");
            game.startNode = new Node(string);
        }

        node = game.startNode;

        String inputMove;
        Move move;

        if (string.contains("moves")) {
            string = UciUtils.substringAfter(string, "moves") + " ";
            while (string.length() > 0
                    && string.contains(" ")) {
                inputMove = string.substring(0, string.indexOf(" "));
                move = new Move(inputMove, node);
                node.doMove(move);
                string = string.substring(string.indexOf(" ") + 1);
            }
        }

    }

    public static void inputGo(final String inputString) {

        // go depth 6 wtime 180000 btime 100000 binc 1000 winc 1000 movetime 1000 movestogo 40

        if (inputString.contains("infinite")) {
            engine.searchDepth = Engine.MAX_DEPTH;
            game.timeControlSet = false;
        }

        if (game.timeControlSet) {
            if (inputString.contains("wtime") && game.startNode.playerColor == Colors.WHITE) {
                game.whiteLeftTimeMillis = Integer.valueOf(UciUtils.wordAfter(inputString, "wtime"));
            }

            if (inputString.contains("btime") && game.startNode.playerColor == Colors.BLACK) {
                game.blackLeftTimeMillis = Integer.valueOf(UciUtils.wordAfter(inputString, "btime"));
            }

            if (inputString.contains("winc") && game.startNode.playerColor == Colors.WHITE) {
                game.whiteTimeIncrementMillis = Integer.valueOf(UciUtils.wordAfter(inputString, "winc"));
            }

            if (inputString.contains("binc") && game.startNode.playerColor == Colors.BLACK) {
                game.blackTimeIncrementMillis = Integer.valueOf(UciUtils.wordAfter(inputString, "binc"));
            }
        }

        if (inputString.contains("movestogo")) {
            engine.movesToGo = Integer.parseInt(UciUtils.wordAfter(inputString, "movestogo"));
        }

        if (inputString.contains("depth")) {
            engine.searchDepth = Integer.valueOf(UciUtils.wordAfter(inputString, "depth"));
        }

        if (inputString.contains("movetime")) {
            engine.inputSearchMaxLengthMillis = Integer.valueOf(UciUtils.wordAfter(inputString, "movetime"));
        } else {
            // se null, verrà ricalcolato
            engine.inputSearchMaxLengthMillis = null;
        }

        /*
         * UCI protocol: the engine must always be able to process input from stdin, even while thinking.
         */
        new Thread(() -> {
            try {
                engine.nextMove(game, node);

                Integer integerMove = engine.pvMap.get(node.nodeHashCode);

                if (integerMove == null) {
                    System.out.println("bestmove " + Pan.NULL_MOVE);
                } else {
                    Move move = new Move(integerMove);
                    System.out.println("bestmove " + move.toPan(game.startNode.playerColor));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    public static void inputStop() {
        engine.outsideStop = true;
    }

    public static void inputQuit() {
        System.exit(0);
    }

}
