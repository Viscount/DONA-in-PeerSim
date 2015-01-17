package dona.entity;

import java.util.HashMap;
import java.util.Map;

public class ConnectionManager {
	
	private Map<String, Connection> detail;
	
	public ConnectionManager(){
		detail = new HashMap();
	}
	
	public void addSource(String file, String target){
		if ( detail.containsKey(file) ){
			Connection connection = detail.get(file);
			connection.addSource(target);
		}
		else {
			Connection connection = new Connection();
			connection.addSource(target);
			detail.put(file, connection);
		}
	}
	
	public int getNextIndex(String file){
		return detail.get(file).getNextChunkIndex();
	}

}
