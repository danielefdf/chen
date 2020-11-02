package model.elements;

public abstract class Kinds {

    public static final byte HUMAN = 1, ENGINE = 2;

    public static String toString(final byte kind)
            throws Exception {

        switch (kind) {
        case 1: return "HUMAN";
        case 2: return "ENGINE";
        default:
            throw new Exception("kind=" + kind);
        }

    }

    public static byte switchKind(final byte kind)
            throws Exception {

        switch (kind) {
        case ENGINE: return HUMAN;
        case HUMAN:  return ENGINE;
        default:
            throw new Exception("kind=" + kind);
        }

    }

}
