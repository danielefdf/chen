package view.game;

import java.io.Serializable;
import model.game.GameType;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class GameData implements Serializable {

    private static final long serialVersionUID = 1L;

    private GameType gameType;

    private String startFenString;

    private boolean timeControlSet;

    private byte whitePlayerKind;
    private String whiteEngineName;
    private Integer whiteLeftTimeMillis;
    private Integer whiteTimeIncrementMillis;

    private byte blackPlayerKind;
    private String blackEngineName;
    private Integer blackLeftTimeMillis;
    private Integer blackTimeIncrementMillis;

    private GameStep[] gameStepsArray;

    private byte analysisPlayerKind;
    private String analysisEngineName;

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public String getStartFenString() {
        return startFenString;
    }

    public void setStartFenString(String startFenString) {
        this.startFenString = startFenString;
    }

    public boolean isTimeControlSet() {
        return timeControlSet;
    }

    public void setTimeControlSet(boolean timeControlSet) {
        this.timeControlSet = timeControlSet;
    }

    public byte getWhitePlayerKind() {
        return whitePlayerKind;
    }

    public void setWhitePlayerKind(byte whitePlayerKind) {
        this.whitePlayerKind = whitePlayerKind;
    }

    public String getWhiteEngineName() {
        return whiteEngineName;
    }

    public void setWhiteEngineName(String whiteEngineName) {
        this.whiteEngineName = whiteEngineName;
    }

    public Integer getWhiteLeftTimeMillis() {
        return whiteLeftTimeMillis;
    }

    public void setWhiteLeftTimeMillis(Integer whiteLeftTimeMillis) {
        this.whiteLeftTimeMillis = whiteLeftTimeMillis;
    }

    public Integer getWhiteTimeIncrementMillis() {
        return whiteTimeIncrementMillis;
    }

    public void setWhiteTimeIncrementMillis(Integer whiteTimeIncrementMillis) {
        this.whiteTimeIncrementMillis = whiteTimeIncrementMillis;
    }

    public byte getBlackPlayerKind() {
        return blackPlayerKind;
    }

    public String getBlackEngineName() {
        return blackEngineName;
    }

    public void setBlackPlayerKind(byte blackPlayerKind) {
        this.blackPlayerKind = blackPlayerKind;
    }

    public void setBlackEngineName(String blackEngineName) {
        this.blackEngineName = blackEngineName;
    }

    public Integer getBlackLeftTimeMillis() {
        return blackLeftTimeMillis;
    }

    public void setBlackLeftTimeMillis(Integer blackLeftTimeMillis) {
        this.blackLeftTimeMillis = blackLeftTimeMillis;
    }

    public Integer getBlackTimeIncrementMillis() {
        return blackTimeIncrementMillis;
    }

    public void setBlackTimeIncrementMillis(Integer blackTimeIncrementMillis) {
        this.blackTimeIncrementMillis = blackTimeIncrementMillis;
    }

    public GameStep[] getGameStepsArray() {
        return gameStepsArray;
    }

    public void setGameStepsArray(GameStep[] gameStepsArray) {
        this.gameStepsArray = gameStepsArray;
    }

    public byte getAnalysisPlayerKind() {
        return analysisPlayerKind;
    }

    public void setAnalysisPlayerKind(byte analysisPlayerKind) {
        this.analysisPlayerKind = analysisPlayerKind;
    }

    public String getAnalysisEngineName() {
        return analysisEngineName;
    }

    public void setAnalysisEngineName(String analysisEngineName) {
        this.analysisEngineName = analysisEngineName;
    }

}
