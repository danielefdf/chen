package engine.model;

import java.util.LinkedHashMap;
import java.util.Map;

public final class TMap extends LinkedHashMap<Long, TRec> {

    private static final long serialVersionUID = 1L;

    public static final int T_MAP_LENGTH = 16777216;  // 2^24;

    @Override
    protected boolean removeEldestEntry(Map.Entry<Long, TRec> eldest) {
        return size() > T_MAP_LENGTH;
    }

}
