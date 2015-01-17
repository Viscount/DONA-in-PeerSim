package dona.handler;

import peersim.core.Node;
import dona.entity.Message;

public abstract class Handler {
	
	public abstract void handleMessage(Node node, int protocolID, Message message);

}
