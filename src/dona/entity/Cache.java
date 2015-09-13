package dona.entity;

import dona.Strategy.CacheStrategy;
import dona.Strategy.ReplaceStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TongjiSSE on 2015/9/11.
 */
public class Cache {

    private List cacheContent;
    private static String CACHE_METHOD = "NoCache";
    private static String REPLACE_METHOD = "NoReplace";

    private CacheStrategy cacheStrategy;
    private ReplaceStrategy replaceStrategy;


    public Cache(){
        cacheContent = new ArrayList();

    }

    public void add(String dataName){

    }

    public void delete(String dataName){

    }

    public int find(String dataName){
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
