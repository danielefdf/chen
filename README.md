# chen
A simple classical chess engine


Each of these aspects is configurable.
```java
    public boolean alphaBetaPruning;
    public boolean delayedLegalityCheck;
    public boolean delayedMateCheck;  // utile per perft test
    public int sortedMovesNumber;
    public boolean searchCheckIncrement;
    public boolean quiescentPosSearch;
        public int qSearchAddedDepth;
        public boolean qChecksSearch;
    public boolean iterDeepeningSearch;
    public boolean principalVarSearch;
    public boolean lateMoveReduction;
        public int lateMoveMinMoves;
        public int lateMoveSubtrDepth;
    public boolean killerHeuristic;
    public boolean historyHeuristic;
    public boolean transpositionsMap;
```



