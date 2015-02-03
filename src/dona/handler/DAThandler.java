package dona.handler;

import java.util.List;

import peersim.config.FastConfig;
import peersim.core.CommonState;
import peersim.core.Network;
import peersim.core.Node;
import peersim.transport.Transport;
import dona.entity.FaceInterest;
import dona.entity.Message;
import dona.protocol.Infrastructure;
import dona.util.Log;
import dona.util.Statistic;

public class DAThandler extends Handler{

	@Override
	public void handleMessage(Node node, int protocolID, Message message) {
		// TODO Auto-generated method stub
		Infrastructure inf = (Infrastructure) node.getProtocol(protocolID);
		
		if ( inf.pit.containsKey(message.getDataName()+","+message.getInfo("ChunkNo"))){
			List facelist = ((FaceInterest) inf.pit.get(message.getDataName()+","+message.getInfo("ChunkNo"))).faceList;
			for (int i=0; i<facelist.size(); i++){
				int nexthop = (int) facelist.get(i);
				try {
					Message new_mess = message.clone((int)node.getID());
					((Transport)node.getProtocol(FastConfig.getTransport(protocolID))).
					send(node, Network.get(nexthop), new_mess, protocolID);
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			inf.pit.delete(message.getDataName()+","+message.getInfo("ChunkNo"));
		}
		else {
			if ((int)message.getInfo("RequesterID") != node.getIndex()) return;
			String dataName = message.getDataName();
			inf.connectionManager.receive(dataName);
			int nextIndex = inf.connectionManager.getNextIndex(dataName);
			if ( nextIndex == -1 ){
				//  all REQ sent out, check if last DAT
				if (inf.connectionManager.getChunkNum(dataName) <= inf.connectionManager.getReceivedNum(dataName)){
					
					if ( Statistic.LOG ) Log.write("Query "+node.getIndex()+" for file "+message.getDataName()+
							" Transport completed.");
					
					int query_num = inf.connectionManager.getStartTime(dataName).size();
					Statistic.query_complete += query_num;
					for (int i=0; i<query_num; i++){
						Statistic.total_time += CommonState.getTime() - 
								(long)inf.connectionManager.getStartTime(dataName).get(i);
					}
//					inf.contentStore.put(dataName, inf.connectionManager.getChunkNum(dataName));
					inf.connectionManager.deleteEntry(dataName);
					// generate REG
//					Message reg_mess = new Message("REG",node.getIndex(),dataName);
//					reg_mess.insertInfo("SourceID", node.getIndex());
//					reg_mess.setTTL(Statistic.REG_TTL);
//					List neighbors = inf.neighbors;
//					for (int i=0; i<neighbors.size(); i++){
//						((Transport)node.getProtocol(FastConfig.getTransport(protocolID))).
//						send(node, Network.get((int) inf.neighbors.get(i)), reg_mess, protocolID);
//					}
					return;
				}
			}
			else{
				Message req_message = new Message("REQ",node.getIndex(),message.getDataName());
				req_message.insertInfo("ChunkNo", nextIndex);
				req_message.insertInfo("SourceID", message.getInfo("SourceID"));
				req_message.insertInfo("RequesterID", node.getIndex());
				((Transport)node.getProtocol(FastConfig.getTransport(protocolID))).
				send(node, Network.get(message.getRequester()), req_message, protocolID);
			}
		}
	}

}
