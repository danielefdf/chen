package view.game;

import java.io.Serializable;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class GameRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private int fullmoveNumber;
    private GameStep whiteGameStep;
    private GameStep blackGameStep;

    public int getFullmoveNumber() {
        return fullmoveNumber;
    }

    public void setFullmoveNumber(int fullmoveNumber) {
        this.fullmoveNumber = fullmoveNumber;
    }

    public GameStep getWhiteGameStep() {
        return whiteGameStep;
    }

    public void setWhiteGameStep(GameStep whiteGameStep) {
        this.whiteGameStep = whiteGameStep;
    }

    public GameStep getBlackGameStep() {
        return blackGameStep;
    }

    public void setBlackGameStep(GameStep blackGameStep) {
        this.blackGameStep = blackGameStep;
    }

    public GameRecord(int newFullmoveNumber, GameStep newWhiteGameStep, GameStep newBlackGameStep) {

        setFullmoveNumber(newFullmoveNumber);
        setWhiteGameStep(newWhiteGameStep);
        setBlackGameStep(newBlackGameStep);

    }

}
