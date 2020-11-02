package view.game;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import engine.model.Move;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class GameStep implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int MOVE_STRING_LENGTH    = 15;
    private static final int SRCHLEN_STRING_LENGTH = 10;

    private Move move;
    private Long srchLen;
    private Integer value;

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public Long getSrchLen() {
        return srchLen;
    }

    public void setSrchLen(Long srchLen) {
        this.srchLen = srchLen;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public GameStep(Move newMove, Long newSrchLen, Integer newValue) {

        move    = newMove;
        srchLen = newSrchLen;
        value   = newValue;

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(move.toString());
        while (sb.length() <= MOVE_STRING_LENGTH) { sb.append(" "); }

        if (srchLen != null) {

            DecimalFormat df1v2 = new DecimalFormat("#.##");
            df1v2.setRoundingMode(RoundingMode.CEILING);

            sb.append(df1v2.format(srchLen / 1000.));
            while (sb.length() <= MOVE_STRING_LENGTH + SRCHLEN_STRING_LENGTH) { sb.append(" "); }

        }

        sb.append(value);

        return sb.toString();
    }

}
