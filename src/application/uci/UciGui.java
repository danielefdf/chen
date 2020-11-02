package application.uci;

import model.notations.Pan;

import java.util.Scanner;

/**
 * BOZZA: Consente la comunicazione tra Engine e GUI, secondo il protocollo UCI.
 * I comandi e i parametri errati vengono ignorati.
 *
 * Deprecated: comunque la GUI interna non può dialogare con il motore via UCI,
 * perché ha bisogno di raggiungere i parametri del motore. Ci vorrebbero troppe opzioni
 * da gestire via UCI.
 */
@Deprecated
public class UciGui {

    public static void setCommand(String command) {
        System.out.println(command);
    }

    public static String scanResponse(String response) {

        try (Scanner inputScanner = new Scanner(System.in)) {
            //while (true) {
            while (inputScanner.hasNext()) {
                String inputString = inputScanner.nextLine();
                /**/ if (inputString.equals("uciok"))          inputUciOk();
                else if (inputString.equals("readyok"))        inputReadyOk();
                else if (inputString.startsWith("bestmove"))   return inputBestMove(inputString);
            }
        }

        return "???";
    }

    public static String waitForResponse(String response) {

        String inputString = Pan.NULL_MOVE;
        boolean responseOk = false;

        try (Scanner inputScanner = new Scanner(System.in)) {
            while (!responseOk) {
                System.out.println("!responseOk");
                //while (true) {
                while (inputScanner.hasNext()) {
                    System.out.println("inputScanner.hasNext()");
                    inputString = inputScanner.nextLine();
                    if (inputString.startsWith("bestmove")) {
                        responseOk = true;
                    }
                }
            }
        }

        return inputBestMove(inputString);
    }

    private static void inputUciOk() {
    }

    private static void inputReadyOk() {
    }

    private static String inputBestMove(String inputString) {

        return UciUtils.substringAfter(inputString, "bestmove");
    }

}
