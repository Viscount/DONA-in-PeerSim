package dona.protocol;

import java.util.List;
import java.util.Map;

import dona.entity.*;
import dona.handler.Handler;
import dona.handler.HandlerFactory;
import dona.util.JsonUtil;
import dona.util.Log;
import dona.util.Statistic;
import peersim.config.Configuration;
import peersim.config.FastConfig;
import peersim.core.Network;
import peersim.core.Node;
import peersim.edsim.EDProtocol;
import peersim.transport.Transport;
import peersim.vector.SingleValueHolder;

public class Infrastructure extends SingleValueHolder implements EDProtocol{
	
	private static final String PAR_PROT_BANDWIDTH = "bandwidth";
	private static final String PAR_MODE = "mode";
	private static final String PAR_PATH_NUM = "path_num";
	
	public static int pid_bandwidth;
	public static String mode;
	public static int path_num;
	
	private PIT pit;
	private FIB fib;
	private Map contentStore;
	private Cache cache;
	
	private ConnectionManager connectionManager;
	
	
	public Infrastructure(String prefix) {
		// TODO Auto-generated constructor stub
		super(prefix);
		pid_bandwidth = Configuration.getPid(prefix+"."+PAR_PROT_BANDWIDTH);
		mode = Configuration.getString(prefix+"."+PAR_MODE);
		path_num = Configuration.getInt(prefix+"."+PAR_PATH_NUM);
	}

	@Override
	public void processEvent(Node node, int protocolID, Object event) {
		// TODO Auto-generated method stub
		Message message = JsonUtil.toObject((String)event,Message.class);
		// drop the message with no requester
		if ( message.getRequester() == -1 ) return;
		
		Handler handler = HandlerFactory.createHandler(message.getType());
		handler.handleMessage(node, protocolID, message);

	}

	// getter and setter

	public PIT getPit() {
		return pit;
	}

	public FIB getFib() {
		return fib;
	}

	public Map getContentStore() {
		return contentStore;
	}

	public Cache getCache() {
		return cache;
	}

	public ConnectionManager getConnectionManager() {
		return connectionManager;
	}
}
