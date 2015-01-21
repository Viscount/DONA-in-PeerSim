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
	
	public boolean addSource(int target){
		if (!availableSource.contains(target)){
			availableSource.add(target);
			return true;
		}
		else return false;
	}
	
	public int getNextChunkIndex(){
		int result = nextChunkIndex;
		if ( result >= chunkNum ) return -1;
		nextChunkIndex++;
		return result;
	}
	
	public int getCurrentChunkIndex(){
		return nextChunkIndex;
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
