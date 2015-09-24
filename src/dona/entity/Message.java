package dona.entity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Message implements Cloneable{

	private String type;
	private long requester;
	private String name;
	
	private Map detailInfo;

	public Message(String type, int request, String name) {
		// TODO Auto-generated constructor stub
		detailInfo = new HashMap();
		detailInfo.put("Type", type);
		detailInfo.put("Requester", request);
		detailInfo.put("Name", name);
	}

	
	public String getMessageType(){
		return (String) detailInfo.get("Type");
	}
	
	public String getDataName(){
		return (String) detailInfo.get("Name");
	}
	
	public int getRequester(){
		return (int) detailInfo.get("Requester");
	}
	
	public Object getInfo(String key){
		return detailInfo.get(key);
	}
	
	public Map getAllInf(){
		return detailInfo;
	}
	
	public void insertInfo(String key, Object value){
		detailInfo.put(key, value);
	}
	
	public void setTTL(int ttl){
		detailInfo.put("TTL", ttl);
	}
	
	public int getTTL(){
		return (int) detailInfo.get("TTL");
	}
	
	public Message clone(int newRequester) throws CloneNotSupportedException{
		Message mess = (Message) super.clone();
		mess.detailInfo.putAll(detailInfo);
		mess.detailInfo.put("Requester", newRequester);
		return mess;
	}
}
