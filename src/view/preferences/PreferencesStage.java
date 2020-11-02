package view.preferences;

import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.options.UserOptions;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class PreferencesStage extends Stage {

    private static final int DEFAULT_OFFSET = 55;

    private Stage ownerStage;

    private PreferencesGridPane preferencesGridPane;

    public PreferencesStage(Stage newOwnerStage, MenuItem prefsMenuItem)
            throws Exception {

        ownerStage = newOwnerStage;

        newPreferencesGridPane();

        setTitle("preferences");
        initModality(Modality.APPLICATION_MODAL);
        initOwner(ownerStage);
        setResizable(false);
//        initStyle(StageStyle.UNDECORATED);
        setOnCloseRequest(e -> UserOptions.save());

        getIcons().add(ViewUtils.selectRandomIcon());

        setX(ownerStage.getX() + DEFAULT_OFFSET);
        setY(ownerStage.getY() + DEFAULT_OFFSET);

        setPreferencesStage();

    }

    private void newPreferencesGridPane()
            throws Exception {

        preferencesGridPane = new PreferencesGridPane();

    }

    private void setPreferencesStage() {

        Scene s = new Scene(preferencesGridPane);

        setScene(s);

        preferencesGridPane.requestFocus();

    }

}
