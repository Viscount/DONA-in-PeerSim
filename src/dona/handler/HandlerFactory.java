package dona.handler;

public class HandlerFactory {
	
	public static Handler createHandler(String messageType){
		Handler handler = null;
		switch (messageType){
		case "QUE":
			handler = new QUEhandler();
			break;
		case "ACK":
			handler = new ACKhandler();
			break;
		case "REQ":
			handler = new REQhandler();
			break;
		case "DAT":
			handler = new DAThandler();
			break;
		case "REG":
			handler = new REGhandler();
			break;
		}
		return handler;
	}

}
