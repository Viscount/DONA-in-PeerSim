package dona.Strategy;

import dona.entity.Cache;

/**
 * Created by TongjiSSE on 2015/9/11.
 */
public interface CacheStrategy {
    public boolean isCacheHere( Cache cache, String dataName );
}
