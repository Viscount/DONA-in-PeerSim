package dona.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dona.util.Statistic;

import peersim.core.CommonState;

public class PIT {

	/**
	 * @param args
	 */
	public Map detail;
	
	public PIT(){
		detail = new HashMap<String,FaceInterest>();
	}
	
	public boolean containsKey(Object key){
		return detail.containsKey(key);
	}
	
	public Object put(Object key, Object value){
		return detail.put(key, value);
	}
	
	public Object get(Object key){
		return detail.get(key);
	}
	
	public Set entrySet(){
		return detail.entrySet();
	}
	
	public void addFace(String name, int nodeID){
		if (!detail.containsKey(name)) return;
		FaceInterest fInt = (FaceInterest) detail.get(name);
		List facelist = fInt.faceList;
		facelist.add(nodeID);
		detail.put(name, fInt);
	}
	
	public void addEntry(String name, int nodeID, long time){
		if ( !detail.containsKey(name) ){
			List facelist = new ArrayList();
			facelist.add(nodeID);
			FaceInterest fInt = new FaceInterest(facelist,CommonState.getTime());
			detail.put(name, fInt);
			return;
		}
	}
	
	public List getFace(String name){
		if (!detail.containsKey(name)) return null;
		List facelist = ((FaceInterest) detail.get(name)).faceList;
		return facelist;
	}
	
	public void delete(String name){
		if (detail.containsKey(name)) detail.remove(name);
	}
	
	public void deleteInvalidEntry(String name){
		if (detail.containsKey(name)) {
			FaceInterest fInt = (FaceInterest) detail.get(name);
			if ( CommonState.getTime() - fInt.remain >= Statistic.PIT_PENDING_TIME ) delete(name);
		}
	}

}

