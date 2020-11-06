# chen
A simple classical chess engine,

    with a full JavaFx graphical interface to play against the engine, or analyze a position and see how the engine is "thinking".

The engine can also communicate via UCI protocol with different GUIs.

The engine uses the main classical techniques:
  * minimax with alpha / beta pruning
  * quiescence search
  * iterative deepening
  * principal variation search
  * late move reduction
  * killer moves
  * history heuristic
  * transpositions table

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



