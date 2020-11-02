package view.options;

import java.awt.MouseInfo;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class UserOptions implements Serializable {

    private static final long serialVersionUID = 1L;

    public static boolean showSplashOption;
    public static boolean showWelcomeOption;
    public static boolean showNodeDetailsOption;
    public static boolean showAvailablesOption;
    public static boolean showThinkingOption;
    public static boolean rotateBoardOption;

    public static void setDefaults() {

        showSplashOption      = true;
        showWelcomeOption     = true;
        showNodeDetailsOption = true;
        showAvailablesOption  = true;
        showThinkingOption    = true;
        rotateBoardOption     = true;

    }

    public static void load() {

        try (FileInputStream fis = new FileInputStream(ViewUtils.OPTIONS_FILE_PATH);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            showSplashOption      = (boolean) ois.readObject();
            showWelcomeOption     = (boolean) ois.readObject();
            showNodeDetailsOption = (boolean) ois.readObject();
            showAvailablesOption  = (boolean) ois.readObject();
            showThinkingOption    = (boolean) ois.readObject();
            rotateBoardOption     = (boolean) ois.readObject();

        } catch (FileNotFoundException e) {

            System.out.println("can't find file on path " + ViewUtils.OPTIONS_FILE_PATH);

            setDefaults();

        } catch (IOException e) {

            System.out.println("unable to read file on path " + ViewUtils.OPTIONS_FILE_PATH);

            setDefaults();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void save() {

        try (FileOutputStream fos = new FileOutputStream(ViewUtils.OPTIONS_FILE_PATH);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(showSplashOption);
            oos.writeObject(showWelcomeOption);
            oos.writeObject(showNodeDetailsOption);
            oos.writeObject(showAvailablesOption);
            oos.writeObject(showThinkingOption);
            oos.writeObject(rotateBoardOption);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("info");
            alert.setHeaderText("");
            alert.setContentText("options saved");

            Point p = MouseInfo.getPointerInfo().getLocation();

            alert.setX(p.getX());
            alert.setY(p.getY());

            alert.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("");
            alert.setContentText("error occurred in options saving");
            alert.showAndWait();

            e.printStackTrace();

        }

    }

}
