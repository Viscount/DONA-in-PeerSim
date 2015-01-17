package dona.handler;

import java.util.List;

import peersim.config.FastConfig;
import peersim.core.Network;
import peersim.core.Node;
import peersim.transport.Transport;
import dona.entity.Message;
import dona.protocol.Infrastructure;

public class QUEhandler extends Handler{

	@Override
	public void handleMessage(Node node, int protocolID, Message message) {
		// TODO Auto-generated method stub
		Infrastructure inf = (Infrastructure) node.getProtocol(protocolID);
		
		if ( inf.contentStore.containsKey(message.getDataName())){
			// generate ACK
			int chunknum = (int) inf.contentStore.get(message.getDataName());
			Message ack_message = new Message("ACK",(int)node.getID(),message.getDataName());
			ack_message.insertInfo("ChunkNum", chunknum);
			((Transport)node.getProtocol(FastConfig.getTransport(protocolID))).
			send(node, Network.get(message.getRequester()), ack_message, protocolID);
		}
		else {
			if (inf.pit.containsKey(message.getDataName())){
				inf.pit.addFace(message.getDataName(), message.getRequester());
			}
			else {
				inf.pit.addEntry(message.getDataName(), message.getRequester());
				if (inf.fib.containsKey(message.getDataName())){
					// hit in fib, multicast to all source
					List facelist = inf.fib.getNextHop(message.getDataName());
					for (int i=0; i<facelist.size(); i++){
						Message new_mess = new Message("ACK",(int)node.getID(),message.getDataName());
						((Transport)node.getProtocol(FastConfig.getTransport(protocolID))).
						send(node, Network.get((int) facelist.get(i)), new_mess, protocolID);
					}
				}
				else {
					if ( message.getTTL() <= 0 ) return;
					// not hit in fib, floodfill
					for (int i=0; i<inf.neighbors.size(); i++){
						Message new_mess = new Message("ACK",(int)node.getID(),message.getDataName());
						new_mess.setTTL(message.getTTL()-1);
						if ((int)inf.neighbors.get(i) != message.getRequester()){
							((Transport)node.getProtocol(FastConfig.getTransport(protocolID))).
							send(node, Network.get((int) inf.neighbors.get(i)), new_mess, protocolID);
						}
					}
				}
			}
		}
	}

}
