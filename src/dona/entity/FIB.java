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

	public boolean contains(String dataName){
		return detail.containsKey(dataName);
	}

	public List find(String dataName){
		return detail.get(dataName);
	}

	public void addEntry(String dataName,long sourceId, long faceId){
		if (!contains(dataName)){
			List entryList = new ArrayList();
			entryList.add(new SourceInfo(sourceId,faceId));
			detail.put(dataName,entryList);
			return;
		}
		else {

		}
	}
}

