package dona.handler;

import java.util.List;

import peersim.config.FastConfig;
import peersim.core.Network;
import peersim.core.Node;
import peersim.transport.Transport;
import dona.entity.FaceInterest;
import dona.entity.Message;
import dona.protocol.Infrastructure;
import dona.util.Log;

public class ACKhandler extends Handler{

	@Override
	public void handleMessage(Node node, int protocolID, Message message) {
		// TODO Auto-generated method stub
		Infrastructure inf = (Infrastructure) node.getProtocol(protocolID);
		
		if ( inf.pit.containsKey(message.getDataName()) ){
			// hit in PIT, forward to all query
			inf.fib.addItem(message.getDataName(), (int) message.getInfo("SourceID"),message.getRequester());
			List facelist = (List) inf.pit.get(message.getDataName());
			for (int i=0; i<facelist.size(); i++){
				FaceInterest nexthop = (FaceInterest) facelist.get(i);
				try {
					Message new_mess = message.clone((int)node.getID());
					((Transport)node.getProtocol(FastConfig.getTransport(protocolID))).
					send(node, Network.get(nexthop.faceID), new_mess, protocolID);
					nexthop.remain--;
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			inf.pit.deleteInvalidEntry(message.getDataName());
		}
		else {
			if ((int)message.getInfo("RequesterID") != node.getIndex()) return;
			// reach the requester
			Log.write("Requester reached.");
			
			boolean flag = inf.connectionManager.addSource(message.getDataName(), 
					(int) message.getInfo("SourceID"), (int) message.getInfo("ChunkNum"));
			if (flag){
				if (inf.connectionManager.getActiveNum(message.getDataName()) < inf.path_num){
					inf.connectionManager.activate(message.getDataName());
					int nextIndex = inf.connectionManager.getNextIndex(message.getDataName());
					if ( nextIndex == -1 ){
						// new source available but all REQ sent out
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
	}

}
