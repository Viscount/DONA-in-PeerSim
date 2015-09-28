package dona.entity;

public class SourceInfo {
	
	private long sourceID;
	private long faceID;
	
	public SourceInfo(long SID, long FID){
		sourceID = SID;
		faceID = FID;
	}

	public long getSourceID() {
		return sourceID;
	}

	public void setSourceID(long sourceID) {
		this.sourceID = sourceID;
	}

	public long getFaceID() {
		return faceID;
	}

	public void setFaceID(long faceID) {
		this.faceID = faceID;
	}
}
