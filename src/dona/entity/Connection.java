package dona.entity;

import java.util.ArrayList;
import java.util.List;

public class Connection {
	
	private List activeSource;
	private int nextChunkIndex;
	
	public Connection(){
		activeSource = new ArrayList();
		nextChunkIndex = 0;
	}
	
	public List getActiveSource(){
		return activeSource;
	}
	
	public void addSource(String target){
		activeSource.add(target);
	}
	
	public int getNextChunkIndex(){
		return nextChunkIndex;
	}

}
