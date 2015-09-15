package dona.Strategy;

import dona.entity.Cache;

/**
 * Created by TongjiSSE on 2015/9/15.
 */
public class LRUReplaceStrategy implements ReplaceStrategy{
    @Override
    public void replace(Cache cache, String dataName) {
        if ( cache.find(dataName) != -1 ) return;
        if ( cache.getCurrentSize() < cache.getSize() ) cache.add(dataName);
        else {
            int index = cache.findEarliestTime();
            cache.remove(index);
            cache.add(dataName);
        }
    }
}
