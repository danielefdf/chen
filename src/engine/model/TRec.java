package engine.model;

import model.elements.Accuracies;

public final class TRec {

    public byte depth;
    public byte accuracy;
    public Short nodeValue;

    public TRec(final byte newDepth, final byte newAccuracy, final Short newNodeValue) {

        depth = newDepth;
        accuracy = newAccuracy;
        nodeValue = newNodeValue;

    }

    @Override
    public String toString() {
        try {

            return "(" + depth + "," + Accuracies.toString(accuracy) + "," + nodeValue + ")";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
