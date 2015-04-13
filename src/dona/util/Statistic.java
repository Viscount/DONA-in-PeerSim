package dona.util;

import java.io.FileWriter;
import java.util.Map;

import dona.entity.Message;
import peersim.util.IncrementalStats;

public class Statistic {
	
	public static String filename = "connection.log";
	
	public static final int PIT_PENDING_TIME = 5000;
	public static final int QUE_TTL = 6;
	public static final int REG_TTL = 2;
	public static final int FILE_NUM = 100;
	public static final boolean LOG = false;
	public static final boolean CONN_DETAIL = true;
	
	public static Map fileChunkNum;
	public static int query_index;
	public static long total_time;
	public static int query_complete;
	public static IncrementalStats is;
	
	public static void write(long time){
		try {
			FileWriter fwriter = new FileWriter(filename,true);
			fwriter.write(Long.toString(time)+" \r\n");
			fwriter.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
