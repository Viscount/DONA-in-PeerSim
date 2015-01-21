package dona.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionManager {
	
	public Map<String, Connection> detail;
	
	public ConnectionManager(){
		detail = new HashMap();
	}
	
	public void addQuery(String file){
		Connection connection = new Connection();
		detail.put(file, connection);
	}
	
	public void addSource(String file, int target, int chunkNum){
		if ( detail.containsKey(file) ){
			Connection connection = detail.get(file);
			connection.addSource(target);
			connection.setChunkNum(chunkNum);
			detail.put(file, connection);
		}
		else {
//			Connection connection = new Connection();
//			connection.addSource(target);
//			connection.setChunkNum(chunkNum);
//			detail.put(file, connection);
		}
	}
	
	public List getAvailableSource(String file){
		return detail.get(file).getAvailableSource();
	}
	
	public int getActiveNum(String file){
		return detail.get(file).getActiveNum();
	}
	
	public long getStartTime(String file){
		return detail.get(file).getStartTime();
	}
	
	public void activate(String file){
		detail.get(file).activate();
	}
	
	public void receive(String file){
		detail.get(file).receive();
	}
	
	public int getChunkNum(String file){
		return detail.get(file).getChunkNum();
	}
	
	public int getReceivedNum(String file){
		return detail.get(file).getReceivedNum();
	}
	
	public int getNextIndex(String file){
		return detail.get(file).getNextChunkIndex();
	}
	
	public void deleteEntry(String file){
		detail.remove(file);
	}

}
