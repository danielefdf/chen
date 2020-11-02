package model.elements;

public abstract class Functions {

    // in ordine di importanza crescente per impostazione valore mossa
    public static final byte MOVEMENT = 1, LONG_CG = 2, SHORT_CG = 3, EN_PASSANT = 4, CAPTURE = 5;

    public static String toString(final byte function)
            throws Exception {

        switch (function) {
        case 1: return "MOVEMENT";
        case 2: return "SHORT_CG";
        case 3: return "LONG_CG";
        case 4: return "EN_PASSANT";
        case 5: return "CAPTURE";
        default:
            throw new Exception("function=" + function);
        }

    }

}
