package model.elements;

public abstract class Accuracies {

    public static final byte EXACT_VALUE = 1, LOWER_BOUND = 2, UPPER_BOUND = 3;

    public static String toString(final byte accuracy)
            throws Exception {

        switch (accuracy) {
        case EXACT_VALUE: return "EXACT_VALUE";
        case LOWER_BOUND: return "LOWER_BOUND";
        case UPPER_BOUND: return "UPPER_BOUND";
        default:
            throw new Exception("accuracy=" + accuracy);
        }

    }

}
