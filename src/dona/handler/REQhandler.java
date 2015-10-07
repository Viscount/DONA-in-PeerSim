package dona.handler;

import peersim.config.FastConfig;
import peersim.core.CommonState;
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
		Infrastructure inf = (Infrastructure) node.getProtocol(protocolID);
		String dataName = message.getName();
		// check if reach the source or hit in cache
		if (( Long.valueOf(message.getDetailInfo("SourceID").toString()) == node.getID() )
				||( inf.getCache().find(dataName) != -1 )){
			// TODO generate DAT
		}
		// check if hit the pit
		else if ( inf.getPit().find(dataName) != null ){

		}
		// add entry in pit
		else {
			// forward according to fib
			if ( inf.getFib().find(dataName) != null ){
				// TODO forward to next hop
			}
		}
	}

}
