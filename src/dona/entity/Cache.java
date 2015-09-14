package dona.entity;

import dona.Strategy.CacheStrategy;
import dona.Strategy.ReplaceStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TongjiSSE on 2015/9/11.
 */
public class Cache {

    private static final String CLASSPATH = "dona.Strategy.";

    private List cacheContent;
    private int size;
    private static String CACHE_METHOD = "NoCache";
    private static String REPLACE_METHOD = "NoReplace";

    private CacheStrategy cacheStrategy;
    private ReplaceStrategy replaceStrategy;


    public Cache(){
        try {
            cacheContent = new ArrayList();
            size = 0;
            Class cacheStrategyClass = Class.forName(CLASSPATH + CACHE_METHOD + "Strategy");
            Class replaceStrategyClass = Class.forName(CLASSPATH + REPLACE_METHOD + "Strategy");
            this.cacheStrategy = (CacheStrategy) cacheStrategyClass.newInstance();
            this.replaceStrategy = (ReplaceStrategy) replaceStrategyClass.newInstance();
        } catch ( Exception e ){
            e.printStackTrace();
        }
    }

    public void add(String dataName){
        if ( find(dataName) != -1 ) return;
        else {
            boolean cacheHere = cacheStrategy.isCacheHere(this,dataName);
            if ( cacheHere ) replaceStrategy.replace(this,dataName);
            else return;
        }
    }

    public void delete(String dataName){
        int pos = find(dataName);
        if ( pos == -1 ) return;
        else cacheContent.remove(pos);
    }

    public int getSize(){
        return size;
    }

    public int find( String dataName ){
        if ( !cacheContent.contains(dataName) ) return -1;
        else return cacheContent.indexOf(dataName);
    }


    public void setCacheMethod( CacheStrategy cacheStrategy){
        this.cacheStrategy = cacheStrategy;
    }

    public void setReplaceMethod( ReplaceStrategy replaceStrategy){
        this.replaceStrategy = replaceStrategy;
    }


}
