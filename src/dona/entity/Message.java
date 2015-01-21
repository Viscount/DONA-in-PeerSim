package dona.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Message implements Cloneable{
	
	private String messageType;
	private int requester;
	private int dataSize;
	private String dataName;
	private int TTL;
	private Map additionalInfo;
	

	public Message(String type, int request, String name) {
		// TODO Auto-generated constructor stub
		this.messageType = type;
		this.requester = request;
		this.dataName = name;
		this.additionalInfo = new HashMap<String,Object>();
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
	
	public int getRequester(){
		return requester;
	}
	
	public Object getInfo(String key){
		return additionalInfo.get(key);
	}
	
	public Map getAllInf(){
		return additionalInfo;
	}
	
	public void insertInfo(String key, Object value){
		additionalInfo.put(key, value);
	}
	
	public void setTTL(int ttl){
		this.TTL = ttl;
	}
	
	public int getTTL(){
		return TTL;
	}
	
	public Message clone(int newRequester) throws CloneNotSupportedException{
		Message mess = (Message) super.clone();
		mess.requester = newRequester;
		mess.additionalInfo.putAll(additionalInfo);
		return mess;
	}
}
