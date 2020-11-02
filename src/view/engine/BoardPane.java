package view.engine;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import model.elements.Squares;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class BoardPane extends GridPane {

    private int[] boardValuesList;

    private TextArea squareValueTextArea;

    public BoardPane(int[] newBoardValuesList) {

        boardValuesList = newBoardValuesList;

        for (byte s = 0; s <= Squares.SQUARES_NUMBER - 1; ++s) {

            squareValueTextArea = new TextArea(String.valueOf(boardValuesList[s]));
            squareValueTextArea.setPrefSize(10, 10);

            add(squareValueTextArea, Squares.file(s), Squares.rank(s));

        }

    }

}
