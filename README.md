# chen
A classical chess engine, with a full JavaFx graphical interface to play against the engine, or analyze a position and see how the engine is "thinking".

The engine can also communicate via UCI protocol with different GUIs.

## The engine

The engine uses the main classical techniques:
 * minimax with alpha / beta pruning
 * quiescence search
 * iterative deepening
 * principal variation search
 * late move reduction
 * killer moves
 * history heuristic
 * transpositions table

Each of these aspects can be activated or not, and is configurable:
```java
    public boolean alphaBetaPruning;
    public boolean delayedLegalityCheck;
    public boolean delayedMateCheck;
    public int sortedMovesNumber;
    public boolean searchCheckIncrement;
    public boolean quiescentPosSearch;
        public int qSearchAddedDepth;
        public boolean qChecksSearch;
    public boolean iterDeepeningSearch;
    public boolean principalVarSearch;
    public boolean lateMoveReduction;
        public int lateMoveSubtrDepth;
    public boolean killerHeuristic;
    public boolean historyHeuristic;
    public boolean transpositionsMap;
```

Currently the late move reduction is not active by default, because it didn't show improvements on search performance.

## The GUI

It is possible to set up a match:

![new game](https://github.com/danielefdf/chen/blob/master/docs/newgame.gif)

 * Positions can also be loaded with a FEN string.

Or analyze a position:

![new analysis](https://github.com/danielefdf/chen/blob/master/docs/newanalysis.gif)

It is possible to configure every aspect of the engine:

![configure engine](https://github.com/danielefdf/chen/blob/master/docs/confengine.gif)

Any engine configuration can be saved and retrieved, as well as any game or analysis session.

![load game](https://github.com/danielefdf/chen/blob/master/docs/loadgame.gif)












