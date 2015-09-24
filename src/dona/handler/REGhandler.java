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
	}

}
