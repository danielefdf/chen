package application.app;

import application.uci.UciEngine;
import engine.model.Engine;
import javafx.application.Application;
import javafx.stage.Stage;
import model.elements.Zobrist;
import model.game.Game;

/**
 * @author Daniele Filippone
 * @version 01.00
 *
 * Lancia il motore e lo mette in ascolto delle istruzioni della GUI.
 */
public class Main extends Application {

    public static void main(String[] args)
    		throws Exception {

        Zobrist.setZobristConstants();

        Engine e = new Engine();
        Game g = new Game();

        UciEngine.init(e, g);
        UciEngine.scanCommand();

    }

    @Override
    public void init() {}

    @Override
    public void start(Stage s) {}

}
