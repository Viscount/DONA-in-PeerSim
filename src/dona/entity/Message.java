package dona.entity;

import dona.util.Config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Message implements Cloneable{

	private String type;
	private long requester;
	private long receiver;
	private String name;
	private int TTL;
	private long size;
	
	private Map detailInfo;

	public Message(String type, long requester, String name) {
		// TODO Auto-generated constructor stub
		detailInfo = new HashMap();
		this.setName(name);
		this.setRequester(requester);
		this.setType(type);
		this.setTTL(Config.QUE_TTL);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getRequester() {
		return requester;
	}

	public void setRequester(long requester) {
		this.requester = requester;
	}

	public long getReceiver() {
		return receiver;
	}

	public void setReceiver(long receiver) {
		this.receiver = receiver;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTTL() {
		return TTL;
	}

	public void setTTL(int TTL) {
		this.TTL = TTL;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Object getDetailInfo(String fieldName){
		return detailInfo.get(fieldName);
	}

	public void setDetailInfo(String fieldName, Object value){
		this.detailInfo.put(fieldName,value);
	}

	public Message clone() throws CloneNotSupportedException{
		Message mess = (Message) super.clone();
		mess.detailInfo.putAll(detailInfo);
		return mess;
	}
}
