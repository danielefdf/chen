package engine.model;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class MoveRecord {

    private final Integer depth;
    private final Integer value;
    private final Long timeLength;
    private final int visitedNodes;
    private final int tMapSize;
    private final LinkedList<Move> movesList;

    private Move move0,  move1,  move2,  move3,  move4,  move5,  move6,  move7,  move8,  move9,
                 move10, move11, move12, move13, move14, move15, move16, move17, move18, move19;

    private final DecimalFormat dfd = new DecimalFormat("00");
    private final DecimalFormat dfl = new DecimalFormat("000000");
    private final DecimalFormat dfv = new DecimalFormat("00000");

    // getters utilizzati implicitamente in MoveTableView

    public Integer getDepth() { return depth; }

    public Integer getValue() { return value; }

    public Long getTimeLength() { return timeLength; }

    public int getVisitedNodes() { return visitedNodes; }

    public int getTMapSize() { return tMapSize; }

    public Move getMove0()  { return move0;  }
    public Move getMove1()  { return move1;  }
    public Move getMove2()  { return move2;  }
    public Move getMove3()  { return move3;  }
    public Move getMove4()  { return move4;  }
    public Move getMove5()  { return move5;  }
    public Move getMove6()  { return move6;  }
    public Move getMove7()  { return move7;  }
    public Move getMove8()  { return move8;  }
    public Move getMove9()  { return move9;  }
    public Move getMove10() { return move10; }
    public Move getMove11() { return move11; }
    public Move getMove12() { return move12; }
    public Move getMove13() { return move13; }
    public Move getMove14() { return move14; }
    public Move getMove15() { return move15; }
    public Move getMove16() { return move16; }
    public Move getMove17() { return move17; }
    public Move getMove18() { return move18; }
    public Move getMove19() { return move19; }

    public MoveRecord(final Integer newDepth, final Long newTimeLength,
            final Integer newValue, final int newNodesCounter, final LinkedList<Move> newPvMovesList,
                      final int newTMapSize) {

        depth = newDepth;
        value = newValue;

        movesList = newPvMovesList;

        for (int i = 0; i <= movesList.size() - 1; ++i) {
            switch (i) {
            case  0:  move0 = movesList.get(i); break;
            case  1:  move1 = movesList.get(i); break;
            case  2:  move2 = movesList.get(i); break;
            case  3:  move3 = movesList.get(i); break;
            case  4:  move4 = movesList.get(i); break;
            case  5:  move5 = movesList.get(i); break;
            case  6:  move6 = movesList.get(i); break;
            case  7:  move7 = movesList.get(i); break;
            case  8:  move8 = movesList.get(i); break;
            case  9:  move9 = movesList.get(i); break;
            case 10: move10 = movesList.get(i); break;
            case 11: move11 = movesList.get(i); break;
            case 12: move12 = movesList.get(i); break;
            case 13: move13 = movesList.get(i); break;
            case 14: move14 = movesList.get(i); break;
            case 15: move15 = movesList.get(i); break;
            case 16: move16 = movesList.get(i); break;
            case 17: move17 = movesList.get(i); break;
            case 18: move18 = movesList.get(i); break;
            case 19: move19 = movesList.get(i); break;
            }
        }

        timeLength = newTimeLength;

        visitedNodes = newNodesCounter;

        tMapSize = newTMapSize;

    }

    @Override
    public String toString() {

        StringBuilder statsSb = new StringBuilder();

        statsSb.append(" d= ")
                .append(dfd.format(depth).replaceAll("\\G0", " "));
        statsSb.append(" l= ")
                .append(dfl.format(timeLength).replaceAll("\\G0", " "));
        if (value < 0) {
            statsSb.append(" v= -");
        } else {
            statsSb.append(" v=  ");
        }
        statsSb.append(dfv.format(Math.abs(value)).replaceAll("\\G0", " "));
        statsSb.append(" moves= ")
                .append(movesList);

        statsSb.append(" tMapSize=")
                .append(tMapSize);

        return statsSb.toString();
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        MoveRecord other = (MoveRecord) obj;

        if (depth == null) {
            if (other.depth != null) {
                return false;
            }
        } else if (!depth.equals(other.depth)) {
            return false;
        }

        if (movesList == null) {
            if (other.movesList != null) {
                return false;
            }
        } else if (!movesList.equals(other.movesList)) {
            return false;
        }

        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }

        //noinspection RedundantIfStatement
        if (visitedNodes != other.visitedNodes) {
            return false;
        }

        return true;
    }

}
