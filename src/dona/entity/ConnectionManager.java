package dona.entity;

import dona.util.Statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionManager {
	
	public Map<String, Connection> detail;
	
	public ConnectionManager(){
		detail = new HashMap();
	}

	public void addConnection(String dataName){
		if ( detail.containsKey(dataName) ){
			Connection connection = detail.get(dataName);
			connection.addNewQuery();
		}
		else {
			Connection connection = new Connection(Statistic.query_index, dataName);
			detail.put(dataName,connection);
		}
	}

}
