package view.utilities;

import application.app.MainUtils;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.elements.Colors;

import java.text.DecimalFormat;
import java.util.Random;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class ViewUtils {

    /* resources paths */

    public static final String APP_SYSTEM_PATH = System.getProperty("user.dir");

    public static final String NODEPANE_FIGURES_PATH = APP_SYSTEM_PATH + "\\resources\\figures";
    public static final String CONTROLS_FONTS_PATH   = APP_SYSTEM_PATH + "\\resources\\fonts";

    /* saved elements paths */

    public static final String GAMES_DEFAULT_PATH   = APP_SYSTEM_PATH + "\\games";
    public static final String ENGINES_DEFAULT_PATH = APP_SYSTEM_PATH + "\\engines";

    public static final String DEFAULT_ENGINE_PATH = ENGINES_DEFAULT_PATH + "\\@default";

    /* options file path */

    public static final String OPTIONS_FILE_PATH = APP_SYSTEM_PATH + "\\options\\options.bin";

    public static Image selectRandomIcon() {

        final String[] c = { "w", "b" };
        final String[] p = { "p", "n", "b", "r", "q", "k" };

        @SuppressWarnings("UnnecessaryLocalVariable")
        Image i = new Image("file:\\" + NODEPANE_FIGURES_PATH + "\\c"
                + c[new Random().nextInt(2)]
                + p[new Random().nextInt(6)] + ".png");

        return i;
    }

    public static DropShadow newDropShadow(Byte sideColor, double tileEdge)
            throws Exception {

        DropShadow ds = new DropShadow();

        ds.setSpread(tileEdge / 150.);

        if (sideColor == null) {
            ds.setColor(Color.GRAY);
        } else {
            switch (sideColor) {
            case Colors.WHITE: ds.setColor(Color.DARKORANGE.darker());   break;
            case Colors.BLACK: ds.setColor(Color.LIGHTGREEN.brighter()); break;
            default:
                throw new Exception("sideColor=" + sideColor);
            }
        }

        return ds;
    }

    public static String hhmmssFormat(final Integer leftTimeMillis) {

        DecimalFormat df = new DecimalFormat("00");

        int seconds = leftTimeMillis / MainUtils.MIN_TIME_MILLIS;
        int hours = 0, minutes = 0;

        if (seconds > 60) {
            minutes = seconds / 60;
            seconds = seconds % 60;
            if (minutes > 60) {
                hours   = minutes / 60;
                minutes = minutes % 60;
            }
        }

        return df.format(hours) + ":" + df.format(minutes) + ":" + df.format(seconds);
    }

}
