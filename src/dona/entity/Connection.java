package dona.entity;

import peersim.core.CommonState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Connection {

    private List<Long> startTime;
    private long endTime;
    private int chunkNum;
    private String dataName;
    private List sourceList;
    private Set chunkReceived;
    private long queryID;

    public Connection(long queryId, String dataName){
        this.queryID = queryId;
        this.dataName = dataName;
        this.startTime = new ArrayList<Long>();
        this.startTime.add(CommonState.getTime());
        this.sourceList = new ArrayList();
        this.chunkReceived = new HashSet();
        this.chunkNum = 0;
        this.endTime = 0;
    }

    public void addNewQuery(){
        this.startTime.add(CommonState.getTime());
    }

    public void addSource(long sourceID){
        if ( this.sourceList.contains(sourceID) ) return;
        else this.sourceList.add(sourceID);
    }
}
