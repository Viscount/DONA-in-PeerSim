package dona.entity;

import java.util.List;

public class FaceInterest {
	
	private List faceList;
	private long remain;
	
	public FaceInterest(List fID, long remain){
		faceList = fID;
		this.remain = remain;
	}

	public List getFaceList() {
		return faceList;
	}

	public void setFaceList(List faceList) {
		this.faceList = faceList;
	}

	public long getRemain() {
		return remain;
	}

	public void setRemain(long remain) {
		this.remain = remain;
	}
}
