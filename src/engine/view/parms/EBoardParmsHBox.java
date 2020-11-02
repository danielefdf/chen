package engine.view.parms;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.elements.Squares;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class EBoardParmsHBox extends HBox {

    private static final int BOARD_TEXT_FIELD_PREF_WIDTH = 40;

    private int[] boardOpegScoresList;
    private int[] boardMidgScoresList;
    private int[] boardEndgScoresList;

    private Label label;
    private GridPane opegScoresGridPane;
    private GridPane midgScoresGridPane;
    private GridPane endgScoresGridPane;

    private EParmListener eParmListener;

    public int[] getBoardOpegScoresList() {
        return boardOpegScoresList;
    }

    public int[] getBoardMidgScoresList() {
        return boardMidgScoresList;
    }

    public int[] getBoardEndgScoresList() {
        return boardEndgScoresList;
    }

    public EBoardParmsHBox(String newParmString, int[] newBoardOpegScoresList, int[] newBoardMidgScoresList,
            int[] newBoardEndgScoresList, EParmListener newEParmListener) {

        boardOpegScoresList = newBoardOpegScoresList;
        boardMidgScoresList = newBoardMidgScoresList;
        boardEndgScoresList = newBoardEndgScoresList;

        label = new Label(newParmString);
        label.setMinWidth(EParmsVBox.LABEL_MIN_WIDTH);

        eParmListener = newEParmListener;

        opegScoresGridPane = new GridPane();
        if (boardOpegScoresList != null) {
            for (byte s = 0; s <= Squares.SQUARES_NUMBER - 1; ++s) {
                TextField squareValueTextField = new TextField(String.valueOf(boardOpegScoresList[s]));
                squareValueTextField.setPrefWidth(BOARD_TEXT_FIELD_PREF_WIDTH);
                opegScoresGridPane.add(squareValueTextField, Squares.file(s), Squares.rank(s));
                final int finalS = s;
                squareValueTextField.textProperty().addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                        boardOpegScoresList[finalS] = Integer.parseInt(squareValueTextField.getText());
                        eParmListener.onValueChanged();
                    }
                });
            }
        } else {
            for (byte s = 0; s <= Squares.SQUARES_NUMBER - 1; ++s) {
                TextField squareValueTextField = new TextField();
                squareValueTextField.setPrefWidth(BOARD_TEXT_FIELD_PREF_WIDTH);
                opegScoresGridPane.add(squareValueTextField, Squares.file(s), Squares.rank(s));
            }
            opegScoresGridPane.setDisable(true);
        }

        midgScoresGridPane = new GridPane();
        if (boardMidgScoresList != null) {
            for (byte s = 0; s <= Squares.SQUARES_NUMBER - 1; ++s) {
                TextField squareValueTextField = new TextField(String.valueOf(boardMidgScoresList[s]));
                squareValueTextField.setPrefWidth(BOARD_TEXT_FIELD_PREF_WIDTH);
                midgScoresGridPane.add(squareValueTextField, Squares.file(s), Squares.rank(s));
                final int finalS = s;
                squareValueTextField.textProperty().addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                        boardMidgScoresList[finalS] = Integer.parseInt(squareValueTextField.getText());
                        eParmListener.onValueChanged();
                    }
                });
            }
        } else {
            for (byte s = 0; s <= Squares.SQUARES_NUMBER - 1; ++s) {
                TextField squareValueTextField = new TextField();
                squareValueTextField.setPrefWidth(BOARD_TEXT_FIELD_PREF_WIDTH);
                midgScoresGridPane.add(squareValueTextField, Squares.file(s), Squares.rank(s));
            }
            midgScoresGridPane.setDisable(true);
        }

        endgScoresGridPane = new GridPane();
        if (boardEndgScoresList != null) {
            for (byte s = 0; s <= Squares.SQUARES_NUMBER - 1; ++s) {
                TextField squareValueTextField = new TextField(String.valueOf(boardEndgScoresList[s]));
                squareValueTextField.setPrefWidth(BOARD_TEXT_FIELD_PREF_WIDTH);
                endgScoresGridPane.add(squareValueTextField, Squares.file(s), Squares.rank(s));
                final int finalS = s;
                squareValueTextField.textProperty().addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                        boardEndgScoresList[finalS] = Integer.parseInt(squareValueTextField.getText());
                        eParmListener.onValueChanged();
                    }
                });
            }
        } else {
            for (byte s = 0; s <= Squares.SQUARES_NUMBER - 1; ++s) {
                TextField squareValueTextField = new TextField();
                squareValueTextField.setPrefWidth(BOARD_TEXT_FIELD_PREF_WIDTH);
                endgScoresGridPane.add(squareValueTextField, Squares.file(s), Squares.rank(s));
            }
            endgScoresGridPane.setDisable(true);
        }

        setSpacing(5);

        setAlignment(Pos.CENTER_LEFT);

        setParmPaneSpinner();

    }

    public void resetBoardParmsHBox(int[] newBoardOpegScoresList, int[] newBoardMidgScoresList,
            int[] newBoardEndgScoresList) {

        boardOpegScoresList = newBoardOpegScoresList;
        boardMidgScoresList = newBoardMidgScoresList;
        boardEndgScoresList = newBoardEndgScoresList;

        opegScoresGridPane.getChildren().clear();
        if (boardOpegScoresList != null) {
            for (byte s = 0; s <= Squares.SQUARES_NUMBER - 1; ++s) {
                TextField squareValueTextField = new TextField(String.valueOf(boardOpegScoresList[s]));
                squareValueTextField.setPrefWidth(BOARD_TEXT_FIELD_PREF_WIDTH);
                opegScoresGridPane.add(squareValueTextField, Squares.file(s), Squares.rank(s));
                final int finalS = s;
                squareValueTextField.textProperty().addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                        boardOpegScoresList[finalS] = Integer.parseInt(squareValueTextField.getText());
                        eParmListener.onValueChanged();
                    }
                });
            }
        } else {
            for (byte s = 0; s <= Squares.SQUARES_NUMBER - 1; ++s) {
                TextField squareValueTextField = new TextField();
                squareValueTextField.setPrefWidth(BOARD_TEXT_FIELD_PREF_WIDTH);
                opegScoresGridPane.add(squareValueTextField, Squares.file(s), Squares.rank(s));
            }
            opegScoresGridPane.setDisable(true);
        }

        midgScoresGridPane.getChildren().clear();
        if (boardMidgScoresList != null) {
            for (byte s = 0; s <= Squares.SQUARES_NUMBER - 1; ++s) {
                TextField squareValueTextField = new TextField(String.valueOf(boardMidgScoresList[s]));
                squareValueTextField.setPrefWidth(BOARD_TEXT_FIELD_PREF_WIDTH);
                midgScoresGridPane.add(squareValueTextField, Squares.file(s), Squares.rank(s));
                final int finalS = s;
                squareValueTextField.textProperty().addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                        boardMidgScoresList[finalS] = Integer.parseInt(squareValueTextField.getText());
                        eParmListener.onValueChanged();
                    }
                });
            }
        } else {
            for (byte s = 0; s <= Squares.SQUARES_NUMBER - 1; ++s) {
                TextField squareValueTextField = new TextField();
                squareValueTextField.setPrefWidth(BOARD_TEXT_FIELD_PREF_WIDTH);
                midgScoresGridPane.add(squareValueTextField, Squares.file(s), Squares.rank(s));
            }
            midgScoresGridPane.setDisable(true);
        }

        endgScoresGridPane.getChildren().clear();
        if (boardEndgScoresList != null) {
            for (byte s = 0; s <= Squares.SQUARES_NUMBER - 1; ++s) {
                TextField squareValueTextField = new TextField(String.valueOf(boardEndgScoresList[s]));
                squareValueTextField.setPrefWidth(BOARD_TEXT_FIELD_PREF_WIDTH);
                endgScoresGridPane.add(squareValueTextField, Squares.file(s), Squares.rank(s));
                final int finalS = s;
                squareValueTextField.textProperty().addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                        boardEndgScoresList[finalS] = Integer.parseInt(squareValueTextField.getText());
                        eParmListener.onValueChanged();
                    }
                });
            }
        } else {
            for (byte s = 0; s <= Squares.SQUARES_NUMBER - 1; ++s) {
                TextField squareValueTextField = new TextField();
                squareValueTextField.setPrefWidth(BOARD_TEXT_FIELD_PREF_WIDTH);
                endgScoresGridPane.add(squareValueTextField, Squares.file(s), Squares.rank(s));
            }
            endgScoresGridPane.setDisable(true);
        }

    }

    private void setParmPaneSpinner() {

        getChildren().clear();

        getChildren().add(label);

        getChildren().add(opegScoresGridPane);
        getChildren().add(midgScoresGridPane);
        getChildren().add(endgScoresGridPane);

    }

}
