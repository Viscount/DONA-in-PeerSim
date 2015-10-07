package dona.handler;

import java.util.List;

import peersim.config.FastConfig;
import peersim.core.CommonState;
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
		Infrastructure inf = (Infrastructure) node.getProtocol(protocolID);
		String dataName = message.getName();
		// check if hit in repo or cache
		if (( inf.getContentStore().containsKey(dataName) )||( inf.getCache().find(dataName) != -1)){
			// TODO generate ACK
		}
		// check if hit in pit
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
