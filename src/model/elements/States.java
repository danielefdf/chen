package model.elements;

public abstract class States {

    // chi ha la mossa subisce il matto

    public static final byte ONGOING = 1,
                             CHECK = 2,
                             CHECKMATE = 3, STALE_MATE = 4, THREEFOLD_REP = 5,
                             FIFTY_MOVES = 6, IMPOSSIBLE_MATE = 7,
                             TIME_OUT = 8,
                             DRAW_AGREEMENT = 9,
                             PLAYER_RESIGNS = 10,
                             GAME_INTERRUPTED = 11,
                             NOT_LEGAL = 12;

    public static String toString(final byte state)
            throws Exception {

        switch (state) {
        case ONGOING:          return "ONGOING";
        case CHECK:            return "CHECK";
        case CHECKMATE:        return "CHECKMATE";
        case STALE_MATE:       return "STALE_MATE";
        case THREEFOLD_REP:    return "THREEFOLD_REP";
        case FIFTY_MOVES:      return "FIFTY_MOVES";
        case IMPOSSIBLE_MATE:  return "IMPOSSIBLE_MATE";
        case TIME_OUT:         return "TIME_OUT";
        case DRAW_AGREEMENT:   return "DRAW_AGREEMENT";
        case PLAYER_RESIGNS:   return "PLAYER_RESIGNS";
        case GAME_INTERRUPTED: return "GAME_INTERRUPTED";
        case NOT_LEGAL:        return "NOT_LEGAL";
        default:
            throw new Exception("state=" + state);
        }

    }

    public static String dialogDescription(final byte state)
            throws Exception {

        switch (state) {
        case ONGOING:          return "new game"; // utilizzato solo per nuovo gioco
        case CHECK:            return "check";
        case CHECKMATE:        return "checkmate";
        case STALE_MATE:       return "stale mate";
        case THREEFOLD_REP:    return "threefold repetition";
        case FIFTY_MOVES:      return "fifty moves";
        case IMPOSSIBLE_MATE:  return "impossible mate";
        case TIME_OUT:         return "time out";
        case DRAW_AGREEMENT:   return "draw agreement";
        case PLAYER_RESIGNS:   return "player resigns";
        case GAME_INTERRUPTED: return "game interrupted";
//        case NOT_LEGAL:
        default:
            throw new Exception("state=" + state);
        }

    }

}
