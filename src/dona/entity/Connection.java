package dona.entity;

import java.util.ArrayList;
import java.util.List;

public class Connection {
	
	private List availableSource;
	private int nextChunkIndex;
	private int activeNum;
	
	public Connection(){
		availableSource = new ArrayList();
		nextChunkIndex = 0;
	}
	
	public List getAvailableSource(){
		return availableSource;
	}
	
	public void addSource(int target){
		availableSource.add(target);
	}
	
	public int getNextChunkIndex(){
		return nextChunkIndex;
	}
	
	public int getActiveNum(){
		return activeNum;
	}
	
	public void activate(){
		activeNum++;
	}

}
