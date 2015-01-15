package dona.entity;

public class Message {
	
	private String messageType;
	private int requester;
	private int dataSize;
	private String dataName;
	private String chunkName;
	private int TTL;
	

	public Message(String type, int request, String name) {
		// TODO Auto-generated constructor stub
		this.messageType = type;
		this.requester = request;
		this.dataName = name;
	}
	
	public Message(String type, int request, String name, String chunkname){
		this.messageType = type;
		this.requester = request;
		this.dataName = name;
		this.chunkName = chunkname;
	}
	
	public int getDataSize(){
		return dataSize;
	}
	
	public String getMessageType(){
		return messageType;
	}
	
	public String getDataName(){
		return dataName;
	}
	
	public String getChunkName(){
		return chunkName;
	}
	
	public int getRequester(){
		return requester;
	}
	
	public void setTTL(int ttl){
		this.TTL = ttl;
	}
	
	public int getTTL(){
		return TTL;
	}
}
