package dona.util;

import java.util.Map;

import peersim.util.IncrementalStats;

public class Statistic {
	
	public static final int PIT_PENDING_TIME = 5000;
	public static final int QUE_TTL = 5;
	public static final int REG_TTL = 2;
	public static final int FILE_NUM = 100;
	public static final boolean LOG = false;
	
	public static Map fileChunkNum;
	public static int query_index;
	public static long total_time;
	public static int query_complete;
	public static IncrementalStats is;

}
