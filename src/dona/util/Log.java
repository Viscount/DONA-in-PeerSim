package dona.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import dona.entity.Connection;
import dona.entity.FaceInterest;
import dona.entity.Message;
import dona.entity.SourceInfo;
import dona.protocol.Infrastructure;

import peersim.core.CommonState;
import peersim.core.Node;

public class Log {
	
	public static String filename = "dona.log";
	
	public static void write(String event){
		try {
			FileWriter fwriter = new FileWriter(filename,true);
			event = CommonState.getTime()+ " " + event;
			fwriter.write(event+"\r\n");
			fwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void write(Message message){
		try {
			FileWriter fwriter = new FileWriter(filename,true);
			fwriter.write(CommonState.getTime()+ " ");
			fwriter.write("Message Info: \r\n");
			fwriter.write("Type="+message.getMessageType()+"\r\n");
			fwriter.write("Requester="+message.getRequester()+"\r\n");
			fwriter.write("DataName="+message.getDataName()+"\r\n");
			fwriter.write("TTL="+message.getTTL()+"\r\n");
			fwriter.write("Additional={");
			for (Iterator it = message.getAllInf().entrySet().iterator();it.hasNext();){
				Entry entry = (Entry) it.next();
				fwriter.write(entry.getKey()+"-"+entry.getValue()+",");
			}
			fwriter.write("}\r\n");
			fwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void write(Node node, int protocolID){
		try {
			Infrastructure inf = (Infrastructure) node.getProtocol(protocolID);
			FileWriter fwriter = new FileWriter(filename,true);
			fwriter.write(CommonState.getTime()+ " ");
			fwriter.write("Node Info: \r\n");
			fwriter.write("NodeID="+node.getIndex()+"\r\n");
			fwriter.write("ContentSotre: \r\n");
			for (Iterator it = inf.contentStore.entrySet().iterator();it.hasNext();){
				Entry entry = (Entry) it.next();
				fwriter.write(entry.getKey()+"-"+entry.getValue()+"\r\n");
			}
			fwriter.write("FIB: \r\n");
			for (Iterator it = inf.fib.detail.entrySet().iterator();it.hasNext();){
				Entry entry = (Entry) it.next();
				List facelist = (List) entry.getValue();
				for (int i=0; i<facelist.size(); i++){
					SourceInfo sInfo = (SourceInfo) facelist.get(i);
					fwriter.write(entry.getKey()+"-("+sInfo.sourceID+","+sInfo.faceID+")\r\n");
				}
			}
			fwriter.write("PIT: \r\n");
//			for (Iterator it = inf.pit.detail.entrySet().iterator();it.hasNext();){
//				Entry entry = (Entry) it.next();
//				List facelist = (List) entry.getValue();
//				for (int i=0; i<facelist.size(); i++){
//					FaceInterest fInt = (FaceInterest) facelist.get(i);
//					fwriter.write(entry.getKey()+"-("+fInt.faceID+","+fInt.remain+")\r\n");
//				}
//			}
			fwriter.write("ConnectionManager: \r\n");
			for (Iterator it = inf.connectionManager.detail.entrySet().iterator();it.hasNext();){
				Entry entry = (Entry) it.next();
				Connection connection = (Connection) entry.getValue();
				fwriter.write("  Connection "+entry.getKey()+" : \r\n");
				fwriter.write("ChunkNum="+connection.getChunkNum()+" ");
				fwriter.write("ActiveNum="+connection.getActiveNum()+" ");
				fwriter.write("CurrentIndex="+connection.getCurrentChunkIndex()+" ");
				fwriter.write("ReceivedNum="+connection.getReceivedNum()+" ");
				fwriter.write("\r\n");
				fwriter.write("  Source : ");
				for (int i=0; i<connection.getAvailableSource().size();i++){
					fwriter.write( connection.getAvailableSource().get(i)+",");
				}
				fwriter.write("  StartTime : ");
				for (int i=0; i<connection.getStartTime().size();i++){
					fwriter.write( connection.getStartTime().get(i)+",");
				}
				fwriter.write("\r\n");
			}
			fwriter.write("\r\n");
			fwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
