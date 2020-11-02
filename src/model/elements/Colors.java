package model.elements;

public abstract class Colors {

    public static final byte COLORS_NUMBER = 2;
    public static final byte WHITE = 1, BLACK = -1;

    public static String toString(final byte color)
            throws Exception {

        switch (color) {
        case WHITE: return "WHITE";
        case BLACK: return "BLACK";
        default:
            throw new Exception("color=" + color);
        }

    }

}
