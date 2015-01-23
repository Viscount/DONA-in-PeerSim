package dona.handler;

import peersim.config.FastConfig;
import peersim.core.Network;
import peersim.core.Node;
import peersim.transport.Transport;
import dona.entity.Message;
import dona.protocol.Infrastructure;
import dona.util.Log;
import dona.util.Statistic;

public class REQhandler extends Handler{

	@Override
	public void handleMessage(Node node, int protocolID, Message message) {
		// TODO Auto-generated method stub
		Infrastructure inf = (Infrastructure) node.getProtocol(protocolID);
		
		if ( (int)message.getInfo("SourceID") == node.getIndex() ){
			if (inf.contentStore.containsKey(message.getDataName())){
				// generate DAT
				Message dat_message = new Message("DAT",node.getIndex(),message.getDataName());
				dat_message.insertInfo("ChunkNo", message.getInfo("ChunkNo"));
				dat_message.insertInfo("SourceID", (int)node.getID());
				dat_message.insertInfo("RequesterID", message.getInfo("RequesterID"));
				
				if ( Statistic.LOG ){
					Log.write("Source reached, generate DAT.");
					Log.write(dat_message);
				}
				
				
				((Transport)node.getProtocol(FastConfig.getTransport(protocolID))).
				send(node, Network.get(message.getRequester()), dat_message, protocolID);
			}
		}
		else {
			if (inf.pit.containsKey(message.getDataName()+","+message.getInfo("ChunkNo"))){
				inf.pit.addFace(message.getDataName()+","+message.getInfo("ChunkNo"), message.getRequester(), 1);
			}
			else {
				inf.pit.addEntry(message.getDataName()+","+message.getInfo("ChunkNo"), message.getRequester(), 1);
				if (inf.fib.containsKey(message.getDataName())){
					int nexthop = inf.fib.getNextHop(message.getDataName(), (int)message.getInfo("SourceID"));
					try {
						Message req_mess = message.clone(node.getIndex());
						((Transport)node.getProtocol(FastConfig.getTransport(protocolID))).
						send(node, Network.get(nexthop), req_mess, protocolID);
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
