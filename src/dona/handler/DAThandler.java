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
		Infrastructure inf = (Infrastructure) node.getProtocol(protocolID);
		String dataName = message.getName();
		// check if arrive query source
		if ( message.getRequester() == node.getID() ){
			// TODO update CM
		}
		// check if pit entry exist
		else if ( inf.getPit().find(dataName) != null ){
			// TODO forward according to pit entry
		}
	}

}
