package dona.entity;

import java.util.ArrayList;
import java.util.List;

public class Connection {
	
	private List availableSource;
	private int nextChunkIndex;
	private int activeNum;
	private int chunkNum;
	
	public Connection(){
		availableSource = new ArrayList();
		nextChunkIndex = 0;
		chunkNum = 0;
	}
	
	public void setChunkNum(int num){
		chunkNum = num;
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

}
