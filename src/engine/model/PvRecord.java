package engine.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.LinkedList;

public final class PvRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer depth;
    private final Integer value;
    private final Long timeLength;
    private final int visitedNodes;
    private final int pvMapSize;
    private final int tMapSize;
    private final LinkedList<Move> pvMovesList;

    private Move pvMove0,  pvMove1,  pvMove2,  pvMove3,  pvMove4,  pvMove5,  pvMove6,  pvMove7,  pvMove8,  pvMove9,
                 pvMove10, pvMove11, pvMove12, pvMove13, pvMove14, pvMove15, pvMove16, pvMove17, pvMove18, pvMove19;

    private final DecimalFormat dfd = new DecimalFormat("00");
    private final DecimalFormat dfl = new DecimalFormat("000000");
    private final DecimalFormat dfv = new DecimalFormat("00000");

    // getters utilizzati implicitamente in PvTableView

    public Integer getDepth() { return depth; }

    public Integer getValue() { return value; }

    public Long getTimeLength() { return timeLength; }

    public int getVisitedNodes() { return visitedNodes; }

    public int getTMapSize() { return tMapSize; }

    public Move getPvMove0()  { return pvMove0;  }
    public Move getPvMove1()  { return pvMove1;  }
    public Move getPvMove2()  { return pvMove2;  }
    public Move getPvMove3()  { return pvMove3;  }
    public Move getPvMove4()  { return pvMove4;  }
    public Move getPvMove5()  { return pvMove5;  }
    public Move getPvMove6()  { return pvMove6;  }
    public Move getPvMove7()  { return pvMove7;  }
    public Move getPvMove8()  { return pvMove8;  }
    public Move getPvMove9()  { return pvMove9;  }
    public Move getPvMove10() { return pvMove10; }
    public Move getPvMove11() { return pvMove11; }
    public Move getPvMove12() { return pvMove12; }
    public Move getPvMove13() { return pvMove13; }
    public Move getPvMove14() { return pvMove14; }
    public Move getPvMove15() { return pvMove15; }
    public Move getPvMove16() { return pvMove16; }
    public Move getPvMove17() { return pvMove17; }
    public Move getPvMove18() { return pvMove18; }
    public Move getPvMove19() { return pvMove19; }

    public PvRecord(final Integer newDepth, final Long newTimeLength, final Integer newValue,
            final int newNodesCounter, final LinkedList<Move> newPvMovesList,
                    final int newPvMapSize, final int newTMapSize) {

        depth = newDepth;
        value = newValue;

        pvMovesList = newPvMovesList;

        for (int i = 0; i <= pvMovesList.size() - 1; ++i) {
            switch (i) {
            case  0:  pvMove0 = pvMovesList.get(i); break;
            case  1:  pvMove1 = pvMovesList.get(i); break;
            case  2:  pvMove2 = pvMovesList.get(i); break;
            case  3:  pvMove3 = pvMovesList.get(i); break;
            case  4:  pvMove4 = pvMovesList.get(i); break;
            case  5:  pvMove5 = pvMovesList.get(i); break;
            case  6:  pvMove6 = pvMovesList.get(i); break;
            case  7:  pvMove7 = pvMovesList.get(i); break;
            case  8:  pvMove8 = pvMovesList.get(i); break;
            case  9:  pvMove9 = pvMovesList.get(i); break;
            case 10: pvMove10 = pvMovesList.get(i); break;
            case 11: pvMove11 = pvMovesList.get(i); break;
            case 12: pvMove12 = pvMovesList.get(i); break;
            case 13: pvMove13 = pvMovesList.get(i); break;
            case 14: pvMove14 = pvMovesList.get(i); break;
            case 15: pvMove15 = pvMovesList.get(i); break;
            case 16: pvMove16 = pvMovesList.get(i); break;
            case 17: pvMove17 = pvMovesList.get(i); break;
            case 18: pvMove18 = pvMovesList.get(i); break;
            case 19: pvMove19 = pvMovesList.get(i); break;
            }
        }

        timeLength = newTimeLength;
        visitedNodes = newNodesCounter;
        pvMapSize = newPvMapSize;
        tMapSize = newTMapSize;

    }

    @Override
    public String toString() {

        StringBuilder statsSb = new StringBuilder();

        statsSb.append(" d= ")
                .append(dfd.format(depth).replaceAll("\\G0", " "));
        statsSb.append(" l= ")
                .append(dfl.format(timeLength).replaceAll("\\G0", " "));

        if (value == null) {
            statsSb.append(" v= null");
        } else if (value == 0) {
            statsSb.append(" v=      0");
        } else if (value < 0) {
            statsSb.append(" v= -");
            statsSb.append(dfv.format(Math.abs(value)).replaceAll("\\G0", " "));
        } else {
            statsSb.append(" v=  ");
            statsSb.append(dfv.format(Math.abs(value)).replaceAll("\\G0", " "));
        }

        statsSb.append(" pv= ")
                .append(pvMovesList);

        statsSb.append(" pvMapSize=")
                .append(pvMapSize);

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

        PvRecord other = (PvRecord) obj;

        if (depth == null) {
            if (other.depth != null) {
                return false;
            }
        } else if (!depth.equals(other.depth)) {
            return false;
        }

        if (pvMovesList == null) {
            if (other.pvMovesList != null) {
                return false;
            }
        } else if (!pvMovesList.equals(other.pvMovesList)) {
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
