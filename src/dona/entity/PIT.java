package dona.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PIT {

	/**
	 * @param args
	 */
	public Map detail;
	
	public PIT(){
		detail = new HashMap<String,List>();
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
	
	public void addFace(String name, int nodeID, int times){
		if (!detail.containsKey(name)) return;
		List facelist = (List) detail.get(name);
		boolean flag = false;
		for (int i=0; i<facelist.size(); i++){
			FaceInterest fInt = (FaceInterest) facelist.get(i);
			if (fInt.faceID == nodeID){
				flag = true;
				break;
			}
		}
		if (!flag){
			FaceInterest fInt = new FaceInterest(nodeID,times);
			facelist.add(fInt);
			detail.put(name, facelist);
			return;
		}
		return;
	}
	
	public void addEntry(String name, int nodeID, int times){
		if ( !detail.containsKey(name) ){
			List facelist = new ArrayList();
			FaceInterest fInt = new FaceInterest(nodeID,times);
			facelist.add(fInt);
			detail.put(name, facelist);
			return;
		}
	}
	
//	public void addItem(String name, int nodeID, int times){
//		if ( !detail.containsKey(name) ){
//			List facelist = new ArrayList();
//			FaceInterest fInt = new FaceInterest(nodeID,times);
//			facelist.add(fInt);
//			detail.put(name, facelist);
//			return;
//		}
//		else{
//			List facelist = (List) detail.get(name);
//			boolean flag = false;
//			for (int i=0; i<facelist.size(); i++){
//				FaceInterest fInt = (FaceInterest) facelist.get(i);
//				if (fInt.faceID == nodeID){
//					flag = true;
//					break;
//				}
//			}
//			if (!flag){
//				FaceInterest fInt = new FaceInterest(nodeID,times);
//				facelist.add(fInt);
//				detail.put(name, facelist);
//				return;
//			}
//			return;
//		}
//	}
	
	public List getFace(String name){
		if (!detail.containsKey(name)) return null;
		List facelist = (List) detail.get(name);
		return facelist;
	}
	
	public void deleteInvalidEntry(String name){
		if (detail.containsKey(name)) {
			List facelist = (List) detail.get(name);
			for (int i=0; i<facelist.size(); i++){
				if (((FaceInterest)facelist.get(i)).remain <= 0) facelist.remove(i);
			}
			if (facelist.size() == 0) detail.remove(name);
		}
	}

}

