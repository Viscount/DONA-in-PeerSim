package dona.entity;

import java.util.ArrayList;
import java.util.List;

import peersim.core.CommonState;

public class Connection {
	
	private List availableSource;
	private int nextChunkIndex;
	private int activeNum;
	private int chunkNum;
	private int chunkReceived;
	private long startTime;
	
	public Connection(){
		availableSource = new ArrayList();
		nextChunkIndex = 0;
		chunkNum = 0;
		chunkReceived = 0;
		startTime = CommonState.getTime();
	}
	
	public void setChunkNum(int num){
		chunkNum = num;
	}
	
	public long getStartTime(){
		return startTime;
	}
	
	public int getChunkNum(){
		return chunkNum;
	}
	
	public int getReceivedNum(){
		return chunkReceived;
	}
	
	public List getAvailableSource(){
		return availableSource;
	}
	
	public void addSource(int target){
		availableSource.add(target);
	}
	
	public int getNextChunkIndex(){
		int result = nextChunkIndex;
		if ( result >= chunkNum ) return -1;
		nextChunkIndex++;
		return result;
	}
	
	public int getActiveNum(){
		return activeNum;
	}
	
	public void activate(){
		activeNum++;
	}
	
	public void receive(){
		chunkReceived++;
	}

}
