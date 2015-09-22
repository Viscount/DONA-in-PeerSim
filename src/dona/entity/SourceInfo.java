package dona.entity;

public class SourceInfo {
	
	private int sourceID;
	private int faceID;
	
	public SourceInfo(int SID, int FID){
		sourceID = SID;
		faceID = FID;
	}

	public int getSourceID() {
		return sourceID;
	}

	public void setSourceID(int sourceID) {
		this.sourceID = sourceID;
	}

	public int getFaceID() {
		return faceID;
	}

	public void setFaceID(int faceID) {
		this.faceID = faceID;
	}
}
