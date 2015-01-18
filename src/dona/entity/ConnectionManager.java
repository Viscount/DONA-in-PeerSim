package dona.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionManager {
	
	private Map<String, Connection> detail;
	
	public ConnectionManager(){
		detail = new HashMap();
	}
	
	public void addSource(String file, int target, int chunkNum){
		if ( detail.containsKey(file) ){
			Connection connection = detail.get(file);
			connection.addSource(target);
		}
		else {
			Connection connection = new Connection();
			connection.addSource(target);
			connection.setChunkNum(chunkNum);
			detail.put(file, connection);
		}
	}
	
	public List getAvailableSource(String file){
		return detail.get(file).getAvailableSource();
	}
	
	public int getActiveNum(String file){
		return detail.get(file).getActiveNum();
	}
	
	public void activate(String file){
		detail.get(file).activate();
	}
	
	public int getNextIndex(String file){
		return detail.get(file).getNextChunkIndex();
	}

}
