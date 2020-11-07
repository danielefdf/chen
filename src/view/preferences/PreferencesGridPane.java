package view.preferences;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import view.options.UserOptions;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class PreferencesGridPane extends GridPane {

    private CheckBox showSplashCheckBox;
    private Label    showSplashLabel;
    private CheckBox showNodeDetailsCheckBox;
    private Label    showNodeDetailsLabel;
    private CheckBox showAvailablesCheckBox;
    private Label    showAvailablesLabel;
    private CheckBox showThinkingCheckBox;
    private Label    showThinkingLabel;
    private CheckBox rotateBoardCheckBox;
    private Label    rotateBoardLabel;

    public PreferencesGridPane()
            throws Exception {

        newOptionsNodes();

        setPreferencesGridPane();

    }

    private void setPreferencesGridPane() {

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20, 20, 20, 20));

        add(showSplashCheckBox,      0, 0);
        add(showSplashLabel,         1, 0);
        add(showAvailablesCheckBox,  0, 1);
        add(showAvailablesLabel,     1, 1);
        add(showNodeDetailsCheckBox, 0, 2);
        add(showNodeDetailsLabel,    1, 2);
        add(showThinkingCheckBox,    0, 3);
        add(showThinkingLabel,       1, 3);
        add(rotateBoardCheckBox,     0, 4);
        add(rotateBoardLabel,        1, 4);

        Insets i = new Insets(15);

        GridPane.setMargin(showSplashCheckBox,      i);
        GridPane.setMargin(showNodeDetailsCheckBox, i);
        GridPane.setMargin(showAvailablesCheckBox,  i);
        GridPane.setMargin(showThinkingCheckBox,    i);
        GridPane.setMargin(rotateBoardCheckBox,     i);

        Insets j = new Insets(15, 15, 15, 0);

        GridPane.setMargin(showSplashLabel,      j);
        GridPane.setMargin(showNodeDetailsLabel, j);
        GridPane.setMargin(showAvailablesLabel,  j);
        GridPane.setMargin(showThinkingLabel,    j);
        GridPane.setMargin(rotateBoardLabel,     j);

    }

    private void newOptionsNodes() {

        showSplashCheckBox = new CheckBox();
        showSplashCheckBox.setSelected(UserOptions.showSplashOption);
        showSplashCheckBox.setOnAction(e -> {
            CheckBox cb = (CheckBox) e.getSource();
            UserOptions.showSplashOption = cb.isSelected();
        });

        showSplashLabel = new Label("show splash screen");

        showNodeDetailsCheckBox = new CheckBox();
        showNodeDetailsCheckBox.setSelected(UserOptions.showNodeDetailsOption);
        showNodeDetailsCheckBox.setOnAction(e -> {
            CheckBox cb = (CheckBox) e.getSource();
            UserOptions.showNodeDetailsOption = cb.isSelected();
        });

        showNodeDetailsLabel = new Label("show node details");

        showAvailablesCheckBox = new CheckBox();
        showAvailablesCheckBox.setSelected(UserOptions.showAvailablesOption);
        showAvailablesCheckBox.setOnAction(e -> {
            CheckBox cb = (CheckBox) e.getSource();
            UserOptions.showAvailablesOption = cb.isSelected();
        });

        showAvailablesLabel = new Label("show available moves");

        showThinkingCheckBox = new CheckBox();
        showThinkingCheckBox.setSelected(UserOptions.showThinkingOption);
        showThinkingCheckBox.setOnAction(e -> {
            CheckBox cb = (CheckBox) e.getSource();
            UserOptions.showThinkingOption = cb.isSelected();
        });

        showThinkingLabel = new Label("show engine thinking");

        rotateBoardCheckBox = new CheckBox();
        rotateBoardCheckBox.setSelected(UserOptions.rotateBoardOption);
        rotateBoardCheckBox.setOnAction(e -> {
            CheckBox cb = (CheckBox) e.getSource();
            UserOptions.rotateBoardOption = cb.isSelected();
        });

        rotateBoardLabel = new Label("rotate board to player pov");

    }

}
