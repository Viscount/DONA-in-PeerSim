package dona.handler;

import java.util.List;

import peersim.config.FastConfig;
import peersim.core.Network;
import peersim.core.Node;
import peersim.transport.Transport;
import dona.entity.Message;
import dona.entity.SourceInfo;
import dona.protocol.Infrastructure;
import dona.util.Log;
import dona.util.Statistic;

public class QUEhandler extends Handler{

	@Override
	public void handleMessage(Node node, int protocolID, Message message) {
		// TODO Auto-generated method stub
		Infrastructure inf = (Infrastructure) node.getProtocol(protocolID);
		
		if ( inf.contentStore.containsKey(message.getDataName())){
			// generate ACK
			int chunknum = (int) inf.contentStore.get(message.getDataName());
			Message ack_message = new Message("ACK",node.getIndex(),message.getDataName());
			ack_message.insertInfo("ChunkNum", chunknum);
			ack_message.insertInfo("SourceID", (int)node.getID());
			ack_message.insertInfo("RequesterID", message.getInfo("RequesterID"));
			
			if ( Statistic.LOG ) Log.write("Source found, generate ACK.");
			if ( Statistic.LOG )Log.write(ack_message);
			
			((Transport)node.getProtocol(FastConfig.getTransport(protocolID))).
			send(node, Network.get(message.getRequester()), ack_message, protocolID);
		}
		else {
			if (inf.pit.containsKey(message.getDataName())){
				inf.pit.addFace(message.getDataName(), message.getRequester(), Statistic.PIT_PENDING_TIME);
			}
			else {
				inf.pit.addEntry(message.getDataName(), message.getRequester(), Statistic.PIT_PENDING_TIME);
				if (inf.fib.containsKey(message.getDataName())){
					// hit in fib, multicast to all source
					List facelist = inf.fib.getNextHop(message.getDataName());
					for (int i=0; i<facelist.size(); i++){
						try {
							Message new_mess;
							new_mess = message.clone(node.getIndex());
							SourceInfo sInfo= (SourceInfo) facelist.get(i);
							((Transport)node.getProtocol(FastConfig.getTransport(protocolID))).
							send(node, Network.get(sInfo.faceID), new_mess, protocolID);
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else {
					if ( message.getTTL() <= 0 ) return;
					// not hit in fib, floodfill
					for (int i=0; i<inf.neighbors.size(); i++){
						try {
							Message new_mess;
							new_mess = message.clone(node.getIndex());
							new_mess.setTTL(message.getTTL()-1);
							if ((int)inf.neighbors.get(i) != message.getRequester()){
								((Transport)node.getProtocol(FastConfig.getTransport(protocolID))).
								send(node, Network.get((int) inf.neighbors.get(i)), new_mess, protocolID);
							}
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			}
		}
	}

}
