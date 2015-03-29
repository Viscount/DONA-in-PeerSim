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
		detail = new HashMap<String,List>();
	}
	
	public boolean containsKey(String name){
		return detail.containsKey(name);
	}
	
	public boolean containsSource(String name, int source){
		List facelist = detail.get(name);
		boolean flag = false;
		for (int i=0; i<facelist.size(); i++){
			SourceInfo sInfo = (SourceInfo) facelist.get(i);
			if (sInfo.sourceID == source){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public void addItem(String name, int source, int face){
		if ( !detail.containsKey(name) ){
			List facelist = new ArrayList();
			SourceInfo sourceInfo = new SourceInfo(source,face);
			facelist.add(sourceInfo);
			detail.put(name, facelist);
			return;
		}
		else{
			List facelist = detail.get(name);
			boolean flag = false;
			for (int i=0; i<facelist.size(); i++){
				SourceInfo sInfo = (SourceInfo) facelist.get(i);
				if (sInfo.sourceID == source){
					flag = true;
					break;
				}
			}
			if (!flag){
				SourceInfo sourceInfo = new SourceInfo(source,face);
				facelist.add(sourceInfo);
				detail.put(name, facelist);
				return;
			}
			return;
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
	
	public int getNextHop(String name, int sourceID){
		if ( !detail.containsKey(name) ) return -1;
		else {
			List facelist = detail.get(name);
			boolean flag = false;
			for (int i=0; i<facelist.size(); i++){
				SourceInfo sInfo = (SourceInfo) facelist.get(i);
				if (sInfo.sourceID == sourceID){
					flag = true;
					return sInfo.faceID;
				}
			}
			if (!flag) return -1;
		}
		return -1;
	}
	

}

