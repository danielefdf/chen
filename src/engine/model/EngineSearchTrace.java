package engine.model;

import application.app.DebugUtils;
import model.elements.Accuracies;
import model.elements.Functions;
import model.elements.States;

import java.util.LinkedList;

public class EngineSearchTrace {

    public Engine engine;

    /*
     * search
     */

    public void setEngine(final Engine newEngine) {
       engine = newEngine;
    }

    /*
     * search
     */

    public Integer search(final Node node, final int depth, int alpha, int beta)
            throws Exception {

        Move move = null;
        Integer score = null, nextScore;
        int checkedMovesCounter = 0;
        boolean pvMoveSearch = true;

        TRec tRec = null;

        boolean prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg;
        Byte prevEnPassantSquare;
        short prevHalfmovesClock;
        byte prevGameState;

        if (DebugUtils.nodeHashCodeDebug > 0x0L) {
            if (node.treeLevel == DebugUtils.nodeLevelDebug
                    && node.nodeHashCode == DebugUtils.nodeHashCodeDebug) {
                DebugUtils.searchStepByStepDebug = true;
            } else {
                if (node.treeLevel < DebugUtils.nodeLevelDebug) {
                    DebugUtils.searchStepByStepDebug = false;
                }
            }
        } else if (DebugUtils.nodeLevelDebug > 0x0L) {
            if (node.treeLevel == DebugUtils.nodeLevelDebug) {
                DebugUtils.searchStepByStepDebug = true;
            } else {
                if (node.treeLevel < DebugUtils.nodeLevelDebug) {
                    DebugUtils.searchStepByStepDebug = false;
                }
            }
        }

        if (DebugUtils.searchStepByStepDebug) {
            System.out.println("SRCH: alpha=" + alpha + " beta=" + beta);
        }

        if (engine.outsideStop) {
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("SRCH: outsideStop -> " +
                        "RETURN evaluation()=" + engine.evaluation(node));
            }
            return engine.evaluation(node);
        }

        if (!engine.delayedMateCheck
                && node.gameState == States.CHECK) {
            engine.checkPlayerMovesBb(node);
            if (!engine.moveFound) {
                node.gameState = States.CHECKMATE;
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println("SRCH: !delayedMateCheck && node.gameState == CHECK && !moveFound -> " +
                            "RETURN evaluation()=" + engine.evaluation(node));
                }
                return engine.evaluation(node);
            }
        }

        if (node.gameState != States.ONGOING
                && node.gameState != States.CHECK) {
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("SRCH: node.gameState != States.ONGOING && CHECK -> " +
                        "RETURN evaluation()=" + engine.evaluation(node));
            }
            return engine.evaluation(node);
        }

        if (engine.transpositionsMap) {
            tRec = engine.tMap.get(node.nodeHashCode);
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("SRCH-TT: tRec=" + tRec);
            }
            if (tRec != null && tRec.depth >= depth) {
                score = tRec.nodeValue.intValue();
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println("SRCH-TT: score=" + score);
                }
                /****/ if (tRec.accuracy == Accuracies.EXACT_VALUE) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("SRCH-TT: tRec.accuracy == Accuracies.EXACT_VALUE -> RETURN");
                    }
                    return score;
                } else if (tRec.accuracy == Accuracies.LOWER_BOUND && score > alpha) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("SRCH-TT: tRec.accuracy == Accuracies.LOWER_BOUND && tRec.score > alpha");
                    }
                    alpha = score;
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("SRCH-TT: alpha=" + alpha);
                    }
                } else if (tRec.accuracy == Accuracies.UPPER_BOUND && score < beta) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("SRCH-TT: tRec.accuracy == Accuracies.UPPER_BOUND && tRec.score < beta");
                    }
                    beta = score;
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("SRCH-TT: beta=" + beta);
                    }
                }
            }
        }

        if (depth == 0) {
            /****/ if (engine.searchCheckIncrement
                    && node.gameState == States.CHECK) {
                score = search(node, 1, alpha, beta);
            } else if (engine.quiescentPosSearch && engine.qChecksSearch) {
                score = quiescenceSearchWithCheck(node, engine.qSearchAddedDepth, alpha, beta);
            } else if (engine.quiescentPosSearch) {
                score = quiescenceSearch(node, engine.qSearchAddedDepth, alpha, beta);
            } else {
                score = engine.evaluation(node);
            }
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("SRCH: score=" + score + " node.gameState=" + States.toString(node.gameState));
            }
            return score;
        }

        engine.computePlayerMovesBb(node);
        Move[] movesList = engine.movesList;
        int movesListMaxIndex = engine.movesListMaxIndex;

        if (DebugUtils.movesListDebug) {
            engine.printMovesList("SRCH - ante", movesList, movesListMaxIndex);
        }

        if (movesListMaxIndex != Engine.START_MOVES_LIST_INDEX) {
            //noinspection ConstantConditions
            move = selectNextMove(node, checkedMovesCounter, pvMoveSearch, movesList, movesListMaxIndex);
            while (move != null
                    && !engine.outsideStop) {
                ++checkedMovesCounter;
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println("SRCH: move=" + move + " checkedMovesCounter=" + checkedMovesCounter);
                }
                prevWhiteShortCg    = node.whiteShortCg;
                prevWhiteLongCg     = node.whiteLongCg;
                prevBlackShortCg    = node.blackShortCg;
                prevBlackLongCg     = node.blackLongCg;
                prevEnPassantSquare = node.enPassantSquare;
                prevHalfmovesClock  = node.halfmovesClock;
                prevGameState       = node.gameState;
                node.doMove(move);
                if (DebugUtils.nodesCounterDebug) {
                    ++engine.visitedNodesCounter;
                }
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println(node.toStringDebug());
                }
                if (node.gameState != States.NOT_LEGAL) {
                    if (engine.principalVarSearch) {
                        if (pvMoveSearch) {
                            nextScore = search(node, depth - 1, -beta, -alpha);
                            score = (nextScore == null) ? null : -nextScore;
                        } else {
                            nextScore = zwSearch(node, depth - 1, -alpha - 1, -alpha);
                            score = (nextScore == null) ? null : -nextScore;
                            if (score != null
                                    && score > alpha) {
                                nextScore = search(node, depth - 1, -beta, -alpha);
                                score = (nextScore == null) ? null : -nextScore;
                            }
                        }
                    } else {
                        nextScore = search(node, depth - 1, -beta, -alpha);
                        score = (nextScore == null) ? null : -nextScore;
                    }
                }
                node.undoMove(move, prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg,
                        prevEnPassantSquare, prevHalfmovesClock, prevGameState);
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println(node.toStringDebug());
                }
                if (engine.game.timeControlSet
                        && (System.currentTimeMillis() - engine.searchStartMillis) >= engine.searchMaxLengthMillis) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("SRCH: timeControl && searchStop() -> " +
                                "RETURN TIME_OUT_SEARCH_VALUE=" + Engine.TIME_OUT_SEARCH_VALUE);
                    }
                    return Engine.TIME_OUT_SEARCH_VALUE;
                }
                pvMoveSearch = false;
                if (score != null
                        && score > alpha) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("SRCH: score=" + score + " != null && > alpha=" + alpha);
                    }
                    engine.pvMap.put(node.nodeHashCode, move.toIntMove());
                    alpha = score;
                    if (engine.historyHeuristic
                            && move.targetPiece == null
                            && move.fromSquare != null) {
                        ++engine.historyRepsList[node.playerColor == 1 ? 1 : 0][move.fromSquare][move.toSquare];
                    }
                    if (engine.alphaBetaPruning
                            && score >= beta) {
                        if (DebugUtils.searchStepByStepDebug) {
                            System.out.println("SRCH: score=" + score + " >= beta=" + beta);
                        }
                        if (engine.killerHeuristic
                                && move.targetPiece == null
                                && move.fromSquare != null) {
                            ++engine.killersRepsList[node.treeLevel][move.fromSquare][move.toSquare];
                        }
                        if (engine.transpositionsMap
                                && (tRec == null || tRec.depth < depth)) {
                            final int intMove = move.toIntMove();
                            engine.tMap.put(node.nodeHashCode,
                                    new TRec((byte) depth, Accuracies.LOWER_BOUND, score.shortValue()));
                            if (DebugUtils.searchStepByStepDebug) {
                                System.out.println("SRCH-TT: TT.put(" + node.nodeHashCode + ", " +
                                        new TRec((byte) depth, Accuracies.LOWER_BOUND, score.shortValue()));
                            }
                        }
                        return beta;
                    }
                    if (engine.transpositionsMap
                            && (tRec == null || tRec.depth < depth)) {
                        final int intMove = move.toIntMove();
                        engine.tMap.put(node.nodeHashCode,
                                new TRec((byte) depth, Accuracies.UPPER_BOUND, score.shortValue()));
                        if (DebugUtils.searchStepByStepDebug) {
                            System.out.println("SRCH-TT: TT.put(" + node.nodeHashCode + ", " +
                                    new TRec((byte) depth, Accuracies.UPPER_BOUND, score.shortValue()));
                        }
                    }
                }
                //noinspection ConstantConditions
                move = selectNextMove(node, checkedMovesCounter, pvMoveSearch, movesList, movesListMaxIndex);
            }

            if (DebugUtils.movesListDebug) {
                engine.printMovesList("SRCH - post", movesList, movesListMaxIndex);
            }

        }

        // questa parte serve in caso di stallo, che non viene approfondito come per lo scacco
        if (score == null) {
            if (node.gameState == States.CHECK
                    /*restart*/ || node.gameState == States.CHECKMATE) {
                node.gameState = States.CHECKMATE;
            } else {
                node.gameState = States.STALE_MATE;
            }
            score = engine.evaluation(node);
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("SRCH: !!! node.gameState=" + States.toString(node.gameState) + " -> " +
                        "score=" + score);
            }
            if (score > alpha) {
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println("SRCH: !!! score=" + score + " > alpha=" + alpha);
                }
                alpha = score;
            }
        }

        if (engine.transpositionsMap
                && (tRec == null || tRec.depth < depth)) {
            final int intMove = (move == null) ? 0 : move.toIntMove();
            engine.tMap.put(node.nodeHashCode,
                    new TRec((byte) depth, Accuracies.EXACT_VALUE, score.shortValue()));
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("SRCH-TT: TT.put(" + node.nodeHashCode + ", " +
                        new TRec((byte) depth, Accuracies.EXACT_VALUE, score.shortValue()));
            }
        }

        return alpha;
    }

    private Integer zwSearch(final Node node, final int depth, int alpha, int beta)
            throws Exception {

        Move move = null;
        Integer score = null, nextScore;
        int checkedMovesCounter = 0;
        boolean pvMoveSearch = true;

        TRec tRec = null;

        boolean prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg;
        Byte prevEnPassantSquare;
        short prevHalfmovesClock;
        byte prevGameState;

        if (DebugUtils.nodeHashCodeDebug > 0x0L) {
            if (node.treeLevel == DebugUtils.nodeLevelDebug
                    && node.nodeHashCode == DebugUtils.nodeHashCodeDebug) {
                DebugUtils.searchStepByStepDebug = true;
            } else {
                if (node.treeLevel < DebugUtils.nodeLevelDebug) {
                    DebugUtils.searchStepByStepDebug = false;
                }
            }
        } else if (DebugUtils.nodeLevelDebug > 0x0L) {
            if (node.treeLevel == DebugUtils.nodeLevelDebug) {
                DebugUtils.searchStepByStepDebug = true;
            } else {
                if (node.treeLevel < DebugUtils.nodeLevelDebug) {
                    DebugUtils.searchStepByStepDebug = false;
                }
            }
        }

        if (DebugUtils.searchStepByStepDebug) {
            System.out.println("ZWS: alpha=" + alpha + " beta=" + beta);
        }

        if (engine.outsideStop) {
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("ZWS: outsideStop -> " +
                        "RETURN evaluation()=" + engine.evaluation(node));
            }
            return engine.evaluation(node);
        }

        if (!engine.delayedMateCheck
                && node.gameState == States.CHECK) {
            engine.checkPlayerMovesBb(node);
            if (!engine.moveFound) {
                node.gameState = States.CHECKMATE;
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println("ZWS: !delayedMateCheck && node.gameState == CHECK && !moveFound -> " +
                            "RETURN evaluation()=" + engine.evaluation(node));
                }
                return engine.evaluation(node);
            }
        }

        if (node.gameState != States.ONGOING
                && node.gameState != States.CHECK) {
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("ZWS: node.gameState != States.ONGOING && CHECK -> " +
                        "RETURN evaluation()=" + engine.evaluation(node));
            }
            return engine.evaluation(node);
        }

        if (engine.transpositionsMap) {
            tRec = engine.tMap.get(node.nodeHashCode);
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("ZWS-TT: tRec=" + tRec);
            }
            if (tRec != null && tRec.depth >= depth) {
                score = tRec.nodeValue.intValue();
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println("ZWS-TT: score=" + score);
                }
                /****/ if (tRec.accuracy == Accuracies.EXACT_VALUE) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("ZWS-TT: tRec.accuracy == Accuracies.EXACT_VALUE -> RETURN");
                    }
                    return score;
                } else if (tRec.accuracy == Accuracies.LOWER_BOUND && score > alpha) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("ZWS-TT: tRec.accuracy == Accuracies.LOWER_BOUND && tRec.score > alpha");
                    }
                    alpha = score;
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("ZWS-TT: alpha=" + alpha);
                    }
                } else if (tRec.accuracy == Accuracies.UPPER_BOUND && score < beta) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("ZWS-TT: tRec.accuracy == Accuracies.UPPER_BOUND && tRec.score < beta");
                    }
                    beta = score;
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("ZWS-TT: beta=" + beta);
                    }
                }
            }
        }

        if (depth == 0) {
            if (engine.searchCheckIncrement
                    && node.gameState == States.CHECK) {
                score = zwSearch(node, 1, alpha, beta);
            } else {
                score = engine.evaluation(node);
            }
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("ZWS: score=" + score + " node.gameState=" + States.toString(node.gameState));
            }
            return score;
        }

        engine.computePlayerMovesBb(node);
        Move[] movesList = engine.movesList;
        int movesListMaxIndex = engine.movesListMaxIndex;

        if (DebugUtils.movesListDebug) {
            engine.printMovesList("ZWS - ante", movesList, movesListMaxIndex);
        }

        if (movesListMaxIndex != Engine.START_MOVES_LIST_INDEX) {
            //noinspection ConstantConditions
            move = selectNextMove(node, checkedMovesCounter, pvMoveSearch, movesList, movesListMaxIndex);
            while (move != null
                    && !engine.outsideStop) {
                ++checkedMovesCounter;
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println("ZWS: move=" + move + " checkedMovesCounter=" + checkedMovesCounter);
                }
                prevWhiteShortCg    = node.whiteShortCg;
                prevWhiteLongCg     = node.whiteLongCg;
                prevBlackShortCg    = node.blackShortCg;
                prevBlackLongCg     = node.blackLongCg;
                prevEnPassantSquare = node.enPassantSquare;
                prevHalfmovesClock  = node.halfmovesClock;
                prevGameState       = node.gameState;
                node.doMove(move);
                if (DebugUtils.nodesCounterDebug) {
                    ++engine.visitedNodesCounter;
                }
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println(node.toStringDebug());
                }
                if (node.gameState != States.NOT_LEGAL) {
                    nextScore = zwSearch(node, depth - 1, -alpha - 1, -alpha);
                    score = (nextScore == null) ? null : -nextScore;
                }
                node.undoMove(move, prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg,
                        prevEnPassantSquare, prevHalfmovesClock, prevGameState);
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println(node.toStringDebug());
                }
                if (engine.game.timeControlSet
                        && (System.currentTimeMillis() - engine.searchStartMillis) >= engine.searchMaxLengthMillis) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("ZWS: timeControl && searchStop() -> " +
                                "RETURN TIME_OUT_SEARCH_VALUE=" + Engine.TIME_OUT_SEARCH_VALUE);
                    }
                    return Engine.TIME_OUT_SEARCH_VALUE;
                }
                pvMoveSearch = false;
                if (score != null
                        && score > alpha) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("ZWS: score=" + score + " != null && > alpha=" + alpha);
                    }
                    engine.pvMap.put(node.nodeHashCode, move.toIntMove()); // se non registro la pv anche qui, è più lento e non mostra la pv calcolata per fine partita
                    alpha = score;
                    if (engine.historyHeuristic
                            && move.targetPiece == null
                            && move.fromSquare != null) {
                        ++engine.historyRepsList[node.playerColor == 1 ? 1 : 0][move.fromSquare][move.toSquare];
                    }
                    if (engine.alphaBetaPruning
                            && score >= beta) {
                        if (DebugUtils.searchStepByStepDebug) {
                            System.out.println("ZWS: score=" + score + " >= beta=" + beta);
                        }
                        if (engine.killerHeuristic
                                && move.targetPiece == null
                                && move.fromSquare != null) {
                            ++engine.killersRepsList[node.treeLevel][move.fromSquare][move.toSquare];
                        }
                        if (engine.transpositionsMap
                                && (tRec == null || tRec.depth < depth)) {
                            final int intMove = move.toIntMove();
                            engine.tMap.put(node.nodeHashCode,
                                    new TRec((byte) depth, Accuracies.LOWER_BOUND, score.shortValue()));
                            if (DebugUtils.searchStepByStepDebug) {
                                System.out.println("ZWS-TT: TT.put(" + node.nodeHashCode + ", " +
                                        new TRec((byte) depth, Accuracies.LOWER_BOUND, score.shortValue()));
                            }
                        }
                        return beta;
                    }
                    if (engine.transpositionsMap
                            && (tRec == null || tRec.depth < depth)) {
                        final int intMove = move.toIntMove();
                        engine.tMap.put(node.nodeHashCode,
                                new TRec((byte) depth, Accuracies.UPPER_BOUND, score.shortValue()));
                        if (DebugUtils.searchStepByStepDebug) {
                            System.out.println("ZWS-TT: TT.put(" + node.nodeHashCode + ", " +
                                    new TRec((byte) depth, Accuracies.UPPER_BOUND, score.shortValue()));
                        }
                    }
                }
                //noinspection ConstantConditions
                move = selectNextMove(node, checkedMovesCounter, pvMoveSearch, movesList, movesListMaxIndex);
            }

            if (DebugUtils.movesListDebug) {
                engine.printMovesList("ZWS", movesList, movesListMaxIndex);
            }

        }

        // questa parte serve in caso di stallo, che non viene approfondito come per lo scacco
        if (score == null) {
            if (node.gameState == States.CHECK
                    /*restart*/ || node.gameState == States.CHECKMATE) {
                node.gameState = States.CHECKMATE;
            } else {
                node.gameState = States.STALE_MATE;
            }
            score = engine.evaluation(node);
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("ZWS: !!! node.gameState=" + States.toString(node.gameState) + " -> " +
                        "score=" + score);
            }
            if (score > alpha) {
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println("ZWS: !!! score=" + score + " > alpha=" + alpha);
                }
                // ZWS-TT: non registra mai un EXACT_VALUE
                if (engine.transpositionsMap
                        && (tRec == null || tRec.depth < depth)) {
                    final int intMove = (move == null) ? 0 : move.toIntMove();
                    engine.tMap.put(node.nodeHashCode,
                            new TRec((byte) depth, Accuracies.UPPER_BOUND, score.shortValue()));
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("ZWS-TT: !!! TT.put(" + node.nodeHashCode + ", " +
                                new TRec((byte) depth, Accuracies.UPPER_BOUND, score.shortValue()));
                    }
                }
                alpha = score;
            }
        }

        // ZWS-TT: non registra mai un EXACT_VALUE

        return alpha;
    }

    /*
     * quiescence search
     */

    private Integer quiescenceSearch(final Node node, final int depth, int alpha, int beta)
            throws Exception {

        final int standPatScore;

        Move move;
        Integer score = null, nextScore;
        int checkedMovesCounter = 0;

        TRec tRec = null;

        boolean prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg;
        Byte prevEnPassantSquare;
        short prevHalfmovesClock;
        byte prevGameState;

        if (DebugUtils.nodeHashCodeDebug > 0x0L) {
            if (node.treeLevel == DebugUtils.nodeLevelDebug
                    && node.nodeHashCode == DebugUtils.nodeHashCodeDebug) {
                DebugUtils.searchStepByStepDebug = true;
            } else {
                if (node.treeLevel < DebugUtils.nodeLevelDebug) {
                    DebugUtils.searchStepByStepDebug = false;
                }
            }
        } else if (DebugUtils.nodeLevelDebug > 0x0L) {
            if (node.treeLevel == DebugUtils.nodeLevelDebug) {
                DebugUtils.searchStepByStepDebug = true;
            } else {
                if (node.treeLevel < DebugUtils.nodeLevelDebug) {
                    DebugUtils.searchStepByStepDebug = false;
                }
            }
        }

        if (DebugUtils.searchStepByStepDebug) {
            System.out.println("QUS: alpha=" + alpha + " beta=" + beta);
        }

        standPatScore = engine.evaluation(node);

        if (engine.outsideStop) {
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("QUS: outsideStop -> " +
                        "RETURN=" + standPatScore);
            }
            return standPatScore;
        }

        if (!engine.delayedMateCheck
                && node.gameState == States.CHECK) {
            engine.checkPlayerMovesBb(node);
            if (!engine.moveFound) {
                node.gameState = States.CHECKMATE;
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println("QUS: !delayedMateCheck && node.gameState == CHECK && !moveFound -> " +
                            "RETURN=" + standPatScore);
                }
                return standPatScore;
            }
        }

        if (node.gameState != States.ONGOING
                && node.gameState != States.CHECK) {
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("QUS: node.gameState != States.ONGOING && CHECK -> " +
                        "RETURN=" + standPatScore);
            }
            return standPatScore;
        }

        if (engine.transpositionsMap) {
            tRec = engine.tMap.get(node.nodeHashCode);
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("QUS-TT: tRec=" + tRec);
            }
            if (tRec != null && tRec.depth >= depth) {
                score = tRec.nodeValue.intValue();
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println("QUS-TT: score=" + score);
                }
                /****/ if (tRec.accuracy == Accuracies.EXACT_VALUE) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("QUS-TT: tRec.accuracy == Accuracies.EXACT_VALUE -> RETURN");
                    }
                    return score;
                } else if (tRec.accuracy == Accuracies.LOWER_BOUND && score > alpha) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("QUS-TT: tRec.accuracy == Accuracies.LOWER_BOUND && tRec.score > alpha");
                    }
                    alpha = score;
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("QUS-TT: alpha=" + alpha);
                    }
                } else if (tRec.accuracy == Accuracies.UPPER_BOUND && score < beta) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("QUS-TT: tRec.accuracy == Accuracies.UPPER_BOUND && tRec.score < beta");
                    }
                    beta = score;
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("QUS-TT: beta=" + beta);
                    }
                }
            }
        }

        if (depth == 0) {
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("QUS: depth == 0 -> " +
                        "RETURN=" + standPatScore);
            }
            return standPatScore;
        }

        if (standPatScore > alpha) {
            alpha = standPatScore;
            if (standPatScore >= beta) {
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println("QUS: standPatScore > alpha && standPatScore >= beta -> " +
                            "RETURN=" + standPatScore);
                }
                return beta;
            }
        }

        engine.computePlayerMovesBb(node);
        Move[] movesList = engine.movesList;
        int movesListMaxIndex = engine.movesListMaxIndex;

        if (DebugUtils.movesListDebug) {
            engine.printMovesList("QUS", movesList, movesListMaxIndex);
        }

        if (movesListMaxIndex != Engine.START_MOVES_LIST_INDEX) {
            move = selectNextMove(node, checkedMovesCounter, false, movesList, movesListMaxIndex);
            while (move != null
                    && !engine.outsideStop) {
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println("QUS: move=" + move);
                }
                if (move.targetPiece != null
                        || move.promotionPiece != null
                        || move.function == Functions.SHORT_CG
                        || move.function == Functions.LONG_CG) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("QUS: significative move");
                    }
                    ++checkedMovesCounter;
                    prevWhiteShortCg    = node.whiteShortCg;
                    prevWhiteLongCg     = node.whiteLongCg;
                    prevBlackShortCg    = node.blackShortCg;
                    prevBlackLongCg     = node.blackLongCg;
                    prevEnPassantSquare = node.enPassantSquare;
                    prevHalfmovesClock  = node.halfmovesClock;
                    prevGameState       = node.gameState;
                    node.doMove(move);
                    if (DebugUtils.nodesCounterDebug) {
                        ++engine.visitedNodesCounter;
                    }
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println(node.toStringDebug());
                    }
                    if (node.gameState != States.NOT_LEGAL) {
                        nextScore = quiescenceSearch(node, depth - 1, -beta, -alpha);
                        score = (nextScore == null) ? null : -nextScore;
                    }
                    node.undoMove(move, prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg,
                            prevEnPassantSquare, prevHalfmovesClock, prevGameState);
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println(node.toStringDebug());
                    }
                    if (engine.game.timeControlSet
                            && (System.currentTimeMillis() - engine.searchStartMillis) >= engine.searchMaxLengthMillis) {
                        if (DebugUtils.searchStepByStepDebug) {
                            System.out.println("QUS: timeControl && searchStop()");
                        }
                        return Engine.TIME_OUT_SEARCH_VALUE;
                    }
                    if (score != null
                            && score > alpha) {
                        if (DebugUtils.searchStepByStepDebug) {
                            System.out.println("QUS: score != null && > alpha");
                        }
                        alpha = score;
                        if (engine.alphaBetaPruning
                                && score >= beta) {
                            if (DebugUtils.searchStepByStepDebug) {
                                System.out.println("QUS: score >= betaValue");
                            }
                            if (engine.transpositionsMap
                                    && (tRec == null || tRec.depth < depth)) {
                                final int intMove = move.toIntMove();
                                engine.tMap.put(node.nodeHashCode,
                                        new TRec((byte) depth, Accuracies.LOWER_BOUND, score.shortValue()));
                                if (DebugUtils.searchStepByStepDebug) {
                                    System.out.println("QUS-TT: TT.put(" + node.nodeHashCode + ", " +
                                            new TRec((byte) depth, Accuracies.LOWER_BOUND, score.shortValue()));
                                }
                            }
                            return beta;
                        }
                        if (engine.transpositionsMap
                                && (tRec == null || tRec.depth < depth)) {
                            final int intMove = move.toIntMove();
                            engine.tMap.put(node.nodeHashCode,
                                    new TRec((byte) depth, Accuracies.UPPER_BOUND, score.shortValue()));
                            if (DebugUtils.searchStepByStepDebug) {
                                System.out.println("QUS-TT: TT.put(" + node.nodeHashCode + ", " +
                                        new TRec((byte) depth, Accuracies.UPPER_BOUND, score.shortValue()));
                            }
                        }
                    }
                }
                move = selectNextMove(node, checkedMovesCounter, false, movesList, movesListMaxIndex);

                if (DebugUtils.movesListDebug) {
                    engine.printMovesList("QUS", movesList, movesListMaxIndex);
                }

            }
        }

        // QUS-TT: non registra mai un EXACT_VALUE

        return alpha;
    }

    private Integer quiescenceSearchWithCheck(final Node node, final int depth, int alpha, int beta)
            throws Exception {

        final int standPatScore;

        Move move;
        Integer score = null, nextScore;
        int checkedMovesCounter = 0;

        TRec tRec = null;

        boolean prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg;
        Byte prevEnPassantSquare;
        short prevHalfmovesClock;
        byte prevGameState;

        if (DebugUtils.nodeHashCodeDebug > 0x0L) {
            if (node.treeLevel == DebugUtils.nodeLevelDebug
                    && node.nodeHashCode == DebugUtils.nodeHashCodeDebug) {
                DebugUtils.searchStepByStepDebug = true;
            } else {
                if (node.treeLevel < DebugUtils.nodeLevelDebug) {
                    DebugUtils.searchStepByStepDebug = false;
                }
            }
        } else if (DebugUtils.nodeLevelDebug > 0x0L) {
            if (node.treeLevel == DebugUtils.nodeLevelDebug) {
                DebugUtils.searchStepByStepDebug = true;
            } else {
                if (node.treeLevel < DebugUtils.nodeLevelDebug) {
                    DebugUtils.searchStepByStepDebug = false;
                }
            }
        }

        if (DebugUtils.searchStepByStepDebug) {
            System.out.println("QUS: ALPHA=" + alpha + " BETA=" + beta);
        }

        standPatScore = engine.evaluation(node);

        if (!engine.delayedMateCheck
                && node.gameState == States.CHECK) {
            engine.checkPlayerMovesBb(node);
            if (!engine.moveFound) {
                node.gameState = States.CHECKMATE;
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println("QUS: !delayedMateCheck && node.gameState == CHECK && !moveFound -> " +
                            "RETURN=" + standPatScore);
                }
                return standPatScore;
            }
        }

        if (engine.outsideStop) {
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("QUS: outsideStop -> " +
                        "RETURN=" + standPatScore);
            }
            return standPatScore;
        }

        if (node.gameState != States.ONGOING
                && node.gameState != States.CHECK) {
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("QUS: node.gameState != States.ONGOING && CHECK -> " +
                        "RETURN=" + standPatScore);
            }
            return standPatScore;
        }

        if (depth == 0) {
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("QUS: depth == 0 -> " +
                        "RETURN=" + standPatScore);
            }
            return standPatScore;
        }

        if (standPatScore > alpha) {
            alpha = standPatScore;
            if (standPatScore >= beta) {
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println("QUS: standPatScore > alpha && standPatScore >= betaValue -> " +
                            "RETURN=" + standPatScore);
                }
                return beta;
            }
        }

        if (engine.transpositionsMap) {
            tRec = engine.tMap.get(node.nodeHashCode);
            if (DebugUtils.searchStepByStepDebug) {
                System.out.println("QUS-TT: tRec=" + tRec);
            }
            if (tRec != null && tRec.depth >= depth) {
                score = tRec.nodeValue.intValue();
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println("QUS-TT: score=" + score);
                }
                /****/ if (tRec.accuracy == Accuracies.EXACT_VALUE) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("QUS-TT: tRec.accuracy == Accuracies.EXACT_VALUE -> RETURN");
                    }
                    return score;
                } else if (tRec.accuracy == Accuracies.LOWER_BOUND && score > alpha) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("QUS-TT: tRec.accuracy == Accuracies.LOWER_BOUND && tRec.score > alpha");
                    }
                    alpha = score;
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("QUS-TT: alpha=" + alpha);
                    }
                } else if (tRec.accuracy == Accuracies.UPPER_BOUND && score < beta) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("QUS-TT: tRec.accuracy == Accuracies.UPPER_BOUND && tRec.score < beta");
                    }
                    beta = score;
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("QUS-TT: beta=" + beta);
                    }
                }
            }
        }

        engine.computePlayerMovesBb(node);
        Move[] movesList = engine.movesList;
        int movesListMaxIndex = engine.movesListMaxIndex;

        if (DebugUtils.movesListDebug) {
            engine.printMovesList("QUS", movesList, movesListMaxIndex);
        }

        if (movesListMaxIndex != Engine.START_MOVES_LIST_INDEX) {
            move = selectNextMove(node, checkedMovesCounter, false, movesList, movesListMaxIndex);
            while (move != null
                    && !engine.outsideStop) {
                ++checkedMovesCounter;
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println("QUS: move=" + move + " checkedMovesCounter=" + checkedMovesCounter);
                }
                prevWhiteShortCg    = node.whiteShortCg;
                prevWhiteLongCg     = node.whiteLongCg;
                prevBlackShortCg    = node.blackShortCg;
                prevBlackLongCg     = node.blackLongCg;
                prevEnPassantSquare = node.enPassantSquare;
                prevHalfmovesClock  = node.halfmovesClock;
                prevGameState       = node.gameState;
                node.doMove(move);
                if (DebugUtils.nodesCounterDebug) {
                    ++engine.visitedNodesCounter;
                }
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println(node.toStringDebug());
                }
                if (node.gameState != States.NOT_LEGAL) {
                    if (move.targetPiece != null
                            || move.promotionPiece != null
                            || move.function == Functions.SHORT_CG
                            || move.function == Functions.LONG_CG
                            || (engine.qChecksSearch
                                    && node.gameState == States.CHECK)) {
                        if (DebugUtils.searchStepByStepDebug) {
                            System.out.println("QUS: significative node");
                        }
                        nextScore = quiescenceSearchWithCheck(node, depth - 1, -beta, -alpha);
                        score = (nextScore == null) ? null : -nextScore;
                    }
                }
                node.undoMove(move, prevWhiteShortCg, prevWhiteLongCg, prevBlackShortCg, prevBlackLongCg,
                        prevEnPassantSquare, prevHalfmovesClock, prevGameState);
                if (DebugUtils.searchStepByStepDebug) {
                    System.out.println(node.toStringDebug());
                }
                if (engine.game.timeControlSet
                        && (System.currentTimeMillis() - engine.searchStartMillis) >= engine.searchMaxLengthMillis) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("QUS: timeControl && searchStop()");
                    }
                    return Engine.TIME_OUT_SEARCH_VALUE;
                }
                if (score != null
                        && score > alpha) {
                    if (DebugUtils.searchStepByStepDebug) {
                        System.out.println("QUS: score != null && > alpha");
                    }
                    alpha = score;
                    if (engine.alphaBetaPruning
                            && score >= beta) {
                        if (DebugUtils.searchStepByStepDebug) {
                            System.out.println("QUS: score >= betaValue");
                        }
                        if (engine.transpositionsMap
                                && (tRec == null || tRec.depth < depth)) {
                            final int intMove = move.toIntMove();
                            engine.tMap.put(node.nodeHashCode,
                                    new TRec((byte) depth, Accuracies.LOWER_BOUND, score.shortValue()));
                            if (DebugUtils.searchStepByStepDebug) {
                                System.out.println("QUS-TT: TT.put(" + node.nodeHashCode + ", " +
                                        new TRec((byte) depth, Accuracies.LOWER_BOUND, score.shortValue()));
                            }
                        }
                        return beta;
                    }
                    if (engine.transpositionsMap
                            && (tRec == null || tRec.depth < depth)) {
                        final int intMove = move.toIntMove();
                        engine.tMap.put(node.nodeHashCode,
                                new TRec((byte) depth, Accuracies.UPPER_BOUND, score.shortValue()));
                        if (DebugUtils.searchStepByStepDebug) {
                            System.out.println("QUS-TT: TT.put(" + node.nodeHashCode + ", " +
                                    new TRec((byte) depth, Accuracies.UPPER_BOUND, score.shortValue()));
                        }
                    }
                }
                move = selectNextMove(node, checkedMovesCounter, false, movesList, movesListMaxIndex);

                if (DebugUtils.movesListDebug) {
                    engine.printMovesList("QUS", movesList, movesListMaxIndex);
                }

            }
        }

        // QUS-TT: non registra mai un EXACT_VALUE

        return alpha;
    }

    /*
     * move selection
     */

    private Move selectNextMove(final Node node, final int checkedMovesCounter, final boolean pvMoveSearch,
            final Move[] movesList, final int movesListMaxIndex)
            throws Exception {

        Move move, bestMove = null;
        int maxValue;

        if (engine.principalVarSearch
                && pvMoveSearch) {
            bestMove = selectPvMove(node, movesList, movesListMaxIndex);
            if (bestMove != null) {
                return bestMove;
            }
        }

        if (checkedMovesCounter < engine.sortedMovesNumber) {
            maxValue = Integer.MIN_VALUE;
            for (int i = 0; i <= movesListMaxIndex; ++i) {
                move = movesList[i];
                if (move == null) {
                    try {
                        System.out.println("move == null for node:");
                        System.out.println(node.toStringDebug());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (move.orderValue != null
                            && move.orderValue > maxValue) {
                        bestMove = move;
                        maxValue = bestMove.orderValue;
                    }
                }
            }
        } else {
            for (int i = 0; i <= movesListMaxIndex; ++i) {
                move = movesList[i];
                if (move.orderValue != null) {
                    bestMove = move;
                    bestMove.orderValue = null;
                    return bestMove;
                }
            }
        }

        if (bestMove != null) {
            bestMove.orderValue = null;
        }

        return bestMove;
    }

    private Move selectPvMove(final Node node, final Move[] movesList, final int movesListMaxIndex)
            throws Exception {

        Integer intMove;
        Move pvMove;

        if (DebugUtils.selectionSortDebug) {
            System.out.println(" *** PV selectPvMove: pvMap=" + engine.pvMap);
            System.out.println(" *** PV selectPvMove: movesList=" + Engine.arrayToString(movesList));
        }

        intMove = engine.pvMap.get(node.nodeHashCode);

        if (intMove != null) {
            pvMove = new Move(intMove);
            if (DebugUtils.selectionSortDebug) {
                System.out.println(" *** PV selectPvMove: pvMove=" + pvMove);
            }
            for (int i = 0; i <= movesListMaxIndex; ++i) {
                if (movesList[i].orderValue != null
                        && movesList[i].equals(pvMove)) {
                    movesList[i].orderValue = null;
                    if (DebugUtils.selectionSortDebug) {
                        System.out.println(" *** PV selectPvMove: movesList=" + Engine.arrayToString(movesList));
                    }
                    return movesList[i];
                }
            }
        }

        return null;
    }

    /*
     * moves reading
     */

    public MoveRecord getMoveRecord()
            throws Exception {

        final Long searchTimeMillis = System.currentTimeMillis() - engine.searchStartMillis;

        LinkedList<Move> movesLinkedList = new LinkedList<>();

        movesLinkedList.add(null); // root node

        Node node = new Node(engine.rootNode);
        Engine.MOVES_ENGINE.computePlayerMovesBb(node);

        for (int i = 0; i <= Engine.MOVES_ENGINE.movesList.length - 1
                && i <= Engine.MOVES_ENGINE.sortedMovesNumber; ++i) {
            movesLinkedList.add(selectNextMove(node, i, /*pvMoveSearch*/true,
                    Engine.MOVES_ENGINE.movesList, Engine.MOVES_ENGINE.movesListMaxIndex));
        }

        int tMapSize = 0;
        if (engine.transpositionsMap) {
            tMapSize = engine.tMap.size();
        }

        return new MoveRecord(engine.currentSearchDepth, searchTimeMillis, engine.rootNodeValue, engine.visitedNodesCounter,
                movesLinkedList, tMapSize);
    }

}
