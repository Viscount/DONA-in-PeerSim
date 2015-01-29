package dona.protocol;

import java.util.List;
import java.util.Map;

import dona.entity.ConnectionManager;
import dona.entity.FIB;
import dona.entity.Message;
import dona.entity.PIT;
import dona.handler.Handler;
import dona.handler.HandlerFactory;
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
	
	private static final String PAR_MODE = "mode";
	private static final String PAR_PATH_NUM = "path_num";
	
	public static String mode;
	public static int path_num;
	
	public PIT pit;
	public FIB fib;
	public Map contentStore;
	public List neighbors;
	
	public ConnectionManager connectionManager;
	
	
	public Infrastructure(String prefix) {
		// TODO Auto-generated constructor stub
		super(prefix);
		mode = Configuration.getString(prefix+"."+PAR_MODE);
		path_num = Configuration.getInt(prefix+"."+PAR_PATH_NUM);
	}

	@Override
	public void processEvent(Node node, int protocolID, Object event) {
		// TODO Auto-generated method stub
		Message message = (Message) event;
		// drop the message with no requester
		if ( message.getRequester() == -1 ) return;
		
//		if ( Statistic.LOG ){
//			Log.write("Node received message.");
//			Log.write(node, protocolID);
//			Log.write(message);
//		}
		
		Handler handler = HandlerFactory.createHandler(message.getMessageType());
		handler.handleMessage(node, protocolID, message);
		
//		if ( Statistic.LOG ){
//			Log.write("Handler Complete.");
//			Log.write(node, protocolID);
//		}
	}
}
