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
	private Map<String,Map<Long,Long>> detail;
	
	public FIB(){
		detail = new HashMap<String,Map<Long,Long>>();
	}

	public boolean contains(String dataName){
		return detail.containsKey(dataName);
	}

	public Map find(String dataName){
		return detail.get(dataName);
	}

	public long find(String dataName,long sourceId){
		return (detail.get(dataName).get(sourceId));
	}

	public void addEntry(String dataName,long sourceId, long faceId){
		if (!contains(dataName)){
			Map entryDetail = new HashMap<Long,Long>();
			entryDetail.put(sourceId,faceId);
			detail.put(dataName,entryDetail);
			return;
		}
		else {
			Map entryDetail = detail.get(dataName);
			if (entryDetail.containsKey(sourceId)) return;
			else entryDetail.put(sourceId,faceId);
			detail.put(dataName,entryDetail);
		}
	}
}

