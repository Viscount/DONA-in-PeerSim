package dona.handler;

import peersim.config.FastConfig;
import peersim.core.Network;
import peersim.core.Node;
import peersim.transport.Transport;
import dona.entity.Message;
import dona.protocol.Infrastructure;

public class REGhandler extends Handler{

	@Override
	public void handleMessage(Node node, int protocolID, Message message) {
		// TODO Auto-generated method stub
		Infrastructure inf = (Infrastructure) node.getProtocol(protocolID);
		
		inf.fib.addItem(message.getDataName(), (int) message.getInfo("SourceID"),message.getRequester());
		if ( message.getTTL() > 0 ){
			try {
				Message reg_mess = message.clone(node.getIndex());
				reg_mess.setTTL(message.getTTL()-1);
				for (int i=0; i<inf.neighbors.size(); i++){
					if ((int)inf.neighbors.get(i) != message.getRequester()){
						((Transport)node.getProtocol(FastConfig.getTransport(protocolID))).
						send(node, Network.get((int) inf.neighbors.get(i)), reg_mess, protocolID);
					}
				}
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else return;
		
	}

}
