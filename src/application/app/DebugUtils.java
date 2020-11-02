package application.app;

public abstract class DebugUtils {

    // serialized Zobrist keys

    public static final boolean serializedZobristConstants = true;
    public static final String serializedZobristFile = System.getProperty("user.dir") + "\\zobrist\\keys.bin";

    // search
    public static boolean searchNextMoveDebug   = false;
    public static boolean searchStepByStepDebug = false;
        public static long nodeHashCodeDebug    = 0L;
        public static int  nodeLevelDebug       = 0;
    public static boolean nodesCounterDebug     = false;

    // node
    public static boolean nodeDoUndoDebug  = false;

    // evaluation
    public static boolean evaluationFlipPosDebug = false;
    public static boolean evaluaShowFlipPosDebug = false;
    public static boolean evaluaTionVsShowDebug  = false;

    // move ordering
    public static boolean selectionSortDebug  = false;
    public static boolean moveOrderingDebug   = false;
    public static boolean movesListDebug      = false;
    public static boolean movesListScoreDebug = false;
    public static boolean pvMapManagingDebug  = false;

    // time managing
    public static boolean timeManagingDebug = false;

    // end of game
    public static boolean endOfGameDebug = false;

}
