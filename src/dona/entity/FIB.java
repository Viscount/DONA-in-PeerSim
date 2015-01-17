package dona.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import peersim.core.Network;


public class FIB {

	/**
	 * @param args
	 */
	public Map<String,List> detail;
	
	public FIB(){
	}
	
	public boolean containsKey(Object key){
		return detail.containsKey(key);
	}
	
	public Object addItem(String key, Object value){
		if ( !detail.containsKey(key) ){
			List facelist = new ArrayList();
			facelist.add(value);
			return detail.put(key, facelist);
		}
		else{
			List facelist = detail.get(key);
			facelist.add(value);
			return detail.put(key, facelist);
		}
	}
	
	public void deleteEntry(String name){
		if (detail.containsKey(name)){
			detail.remove(name, detail.get(name));
		}
	}
	
	public List getNextHop(String name) {
		// TODO Auto-generated method stub
		return detail.get(name);
	}
	

}

