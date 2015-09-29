package dona.entity;

import java.util.List;

public class FaceInterest {
	
	private long faceId;
	private long remain;
	
	public FaceInterest(long fID, long remain){
		faceId = fID;
		this.remain = remain;
	}

	public long getFaceId() {
		return faceId;
	}

	public void setFaceId(long fId) {
		this.faceId = fId;
	}

	public long getRemain() {
		return remain;
	}

	public void setRemain(long remain) {
		this.remain = remain;
	}
}
