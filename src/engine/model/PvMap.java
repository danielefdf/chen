package engine.model;

import java.util.LinkedHashMap;
import java.util.Map;

public final class PvMap extends LinkedHashMap<Long, Move> {

    private static final long serialVersionUID = 1L;

    public static final int T_MAP_LENGTH = 16777216;  // 2^24;

    @Override
    protected boolean removeEldestEntry(final Map.Entry<Long, Move> eldest) {
        return size() > T_MAP_LENGTH;
    }

}
