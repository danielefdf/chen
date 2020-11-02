package view.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import engine.model.Engine;
import engine.model.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.game.Game;
import model.game.Player;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class GameLoader {

    public static GameData getGameData(Game game)
            throws Exception {

        GameData gameData = new GameData();

        gameData.setGameType(game.gameType);

        gameData.setStartFenString(game.startNode.toFen());

        gameData.setTimeControlSet(game.timeControlSet);

        gameData.setWhitePlayerKind(game.whitePlayer.kind);
        gameData.setWhiteEngineName(game.whitePlayer.engine.name);
        gameData.setWhiteLeftTimeMillis(game.whiteLeftTimeMillis);
        gameData.setWhiteTimeIncrementMillis(game.whiteTimeIncrementMillis);

        gameData.setBlackPlayerKind(game.blackPlayer.kind);
        gameData.setBlackEngineName(game.blackPlayer.engine.name);
        gameData.setBlackLeftTimeMillis(game.blackLeftTimeMillis);
        gameData.setBlackTimeIncrementMillis(game.blackTimeIncrementMillis);

        gameData.setAnalysisPlayerKind(game.analysisPlayer.kind);
        gameData.setAnalysisEngineName(game.analysisPlayer.engine.name);

        gameData.setGameStepsArray(new GameStep[game.movesList.size()]);

        for (int i = 0; i <= game.movesList.size() - 1; ++i) {
            gameData.getGameStepsArray()[i] = new GameStep(
                    game.movesList.get(i), game.srchLensList.get(i), game.valuesList.get(i));
        }

        return gameData;
    }

    public static Game getGame(GameData gameData)
            throws Exception {

        Game game = new Game();

        game.gameType = gameData.getGameType();

        game.timeControlSet = gameData.isTimeControlSet();

        File whitePlayerFile = new File(ViewUtils.ENGINES_DEFAULT_PATH
                                            + "\\" + gameData.getWhiteEngineName());

        Engine we = Engine.readEngine(whitePlayerFile);
        if (we == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("");
            alert.setContentText("error occurred in import white engine");
            alert.showAndWait();
        }
        game.whitePlayer              = new Player(gameData.getWhitePlayerKind(), we);
        game.whiteLeftTimeMillis      = gameData.getWhiteLeftTimeMillis();
        game.whiteTimeIncrementMillis = gameData.getWhiteTimeIncrementMillis();

        File blackPlayerFile = new File(ViewUtils.ENGINES_DEFAULT_PATH
                                            + "\\" + gameData.getBlackEngineName());

        Engine be = Engine.readEngine(blackPlayerFile);
        if (be == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("");
            alert.setContentText("error occurred in import black engine");
            alert.showAndWait();
        }
        game.blackPlayer              = new Player(gameData.getBlackPlayerKind(), be);
        game.blackLeftTimeMillis      = gameData.getBlackLeftTimeMillis();
        game.blackTimeIncrementMillis = gameData.getBlackTimeIncrementMillis();

        File analysisPlayerFile = new File(ViewUtils.ENGINES_DEFAULT_PATH
                                            + "\\" + gameData.getAnalysisEngineName());

        Engine ae = Engine.readEngine(analysisPlayerFile);
        if (ae == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("");
            alert.setContentText("error occurred in import analysis engine");
            alert.showAndWait();
        }
        game.analysisPlayer           = new Player(gameData.getAnalysisPlayerKind(), ae);

        game.startNode = new Node(gameData.getStartFenString());

        game.movesList = new LinkedList<>();

        Node n = new Node(game.startNode);

        for (int i = 0; i <= gameData.getGameStepsArray().length - 1; ++i) {

            GameStep gs = gameData.getGameStepsArray()[i];

            game.movesList.add(gs.getMove());
            game.valuesList.add(gs.getValue());
            game.srchLensList.add(gs.getSrchLen());

            n = new Node(n, gs.getMove());

            game.nodesList.add(n);

        }

        return game;
    }

    public static void writeGame(Game game, File file)
            throws Exception {

        int rc;

        File whitePlayerFile = new File(ViewUtils.ENGINES_DEFAULT_PATH + "\\" + game.whitePlayer.engine.name);
        rc = Engine.writeEngine(game.whitePlayer.engine, whitePlayerFile);
        if (rc != 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("");
            alert.setContentText("error occurred in saving white player engine");
            alert.showAndWait();
        }

        File blackPlayerFile = new File(ViewUtils.ENGINES_DEFAULT_PATH + "\\" + game.blackPlayer.engine.name);
        rc = Engine.writeEngine(game.blackPlayer.engine, blackPlayerFile);
        if (rc != 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("");
            alert.setContentText("error occurred in saving black player engine");
            alert.showAndWait();
        }

        File analysisPlayerFile = new File(ViewUtils.ENGINES_DEFAULT_PATH + "\\" + game.analysisPlayer.engine.name);
        rc = Engine.writeEngine(game.analysisPlayer.engine, analysisPlayerFile);
        if (rc != 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("");
            alert.setContentText("error occurred in saving analysis engine");
            alert.showAndWait();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

            oos.writeObject(GameLoader.getGameData(game));

//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("info");
//            alert.setHeaderText("");
//            alert.setContentText("save ok");
//            alert.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("");
            alert.setContentText("error occurred in saving game");
            alert.showAndWait();

            e.printStackTrace();

        }

    }

    public static Game readGame(File file)
            throws Exception {

        Game game = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            GameData gameData = (GameData) ois.readObject();

            game = GameLoader.getGame(gameData);

//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("info");
//            alert.setHeaderText("");
//            alert.setContentText("import ok");
//            alert.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("");
            alert.setContentText("error occurred in import game");
            alert.showAndWait();

            e.printStackTrace();

        }

        return game;
    }

}
