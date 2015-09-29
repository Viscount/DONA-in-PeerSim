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
	private Map<String,List<FaceInterest>> detail;
	
	public PIT(){
		detail = new HashMap<String,List<FaceInterest>>();
	}

	public void addEntry(String dataName, long faceId){
		if( detail.containsKey(dataName) ){
			List<FaceInterest> entryDetail = detail.get(dataName);
			int index = find(dataName,faceId);
			if (  index == -1 ) entryDetail.add(new FaceInterest(faceId,CommonState.getTime()));
			else{
				FaceInterest faceInterest = entryDetail.get(index);
				faceInterest.setRemain(CommonState.getTime());
			}
			detail.put(dataName,entryDetail);
		}
		else {
			List<FaceInterest> entryDetail = new ArrayList<FaceInterest>();
			entryDetail.add(new FaceInterest(faceId,CommonState.getTime()));
			detail.put(dataName,entryDetail);
		}
	}

	public int find(String dataName, long faceId){
		if ( !detail.containsKey(dataName)) return -1;
		List<FaceInterest> entryDetail = detail.get(dataName);
		int index = -1;
		for ( FaceInterest faceInterest : entryDetail ){
			if ( faceInterest.getFaceId() == faceId ) {
				return entryDetail.indexOf(faceInterest);
			}
		}
		return index;
	}

	public List find(String dataName){
		return detail.get(dataName);
	}
}

