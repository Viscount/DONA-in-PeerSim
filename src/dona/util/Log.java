package dona.util;

import java.io.FileWriter;
import java.io.IOException;

import peersim.core.CommonState;

public class Log {
	
	public static String filename = "dona.log";
	
	public static void write(String event){
		try {
			FileWriter fwriter = new FileWriter(filename,true);
			event = CommonState.getTime()+ " " + event;
			fwriter.write(event);
			fwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
