package dona.handler;

import dona.protocol.Infrastructure;

public class HandlerFactory {
	
	public static Handler createHandler(String messageType){
		Handler handler = null;
		try {
			Class className = Class.forName("dona.handler." + Infrastructure.mode + messageType + "handler");
			handler = (Handler)className.newInstance();
		} catch ( Exception e ){
			e.printStackTrace();
		}
		return handler;
	}

}
