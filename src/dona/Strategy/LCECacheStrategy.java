package dona.Strategy;

import dona.entity.Cache;

/**
 * Created by TongjiSSE on 2015/9/15.
 */
public class LCECacheStrategy implements CacheStrategy{

    @Override
    public boolean isCacheHere(Cache cache, String dataName) {
        return true;
    }
}
