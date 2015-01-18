package dona.handler;

import peersim.core.Node;
import dona.entity.Message;
import dona.protocol.Infrastructure;

public class REQhandler extends Handler{

	@Override
	public void handleMessage(Node node, int protocolID, Message message) {
		// TODO Auto-generated method stub
		Infrastructure inf = (Infrastructure) node.getProtocol(protocolID);
		
		
	}

}
