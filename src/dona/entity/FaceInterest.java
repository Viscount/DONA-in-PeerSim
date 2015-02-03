package dona.entity;

import java.util.List;

public class FaceInterest {
	
	public List faceList;
	public long remain;
	
	public FaceInterest(List fID, long remain){
		faceList = fID;
		this.remain = remain;
	}

}
