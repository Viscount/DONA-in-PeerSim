package dona.protocol;

import java.util.List;

import dona.entity.FIB;
import dona.entity.Message;
import dona.entity.PIT;
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
	
	public String name;
	
	private PIT pit;
	private FIB fib;
	private List contentStore;
	
	
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
		
		// handle query
		if ( message.getMessageType().equals("QUE")){
			
		}
		
		// handle acknowledge
		if ( message.getMessageType().equals("ACK")){
			
		}
		
		// handle request
		if ( message.getMessageType().equals("REQ")){
			
		}
		
		// handle data
		if ( message.getMessageType().equals("DAT")){
			
		}
		
		// handle register
		if ( message.getMessageType().equals("REG")){
			
		}
	}
	

}
