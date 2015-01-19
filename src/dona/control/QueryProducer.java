package dona.control;

import peersim.config.Configuration;
import peersim.core.Control;

public class QueryProducer implements Control{
	
	private static String PAR_PROT_INF = "inf_protocol";
	private static final String PAR_QUERY_SIZE = "query_size";
	private static final String PAR_QUERY_PER_CYC = "query_per_cyc";
	
	private static int pid_inf;
	private static int query_size;
	private static int query_per_cyc;
	
	public QueryProducer(String prefix){
		pid_inf = Configuration.getPid(prefix+"."+PAR_PROT_INF);
		query_size = Configuration.getInt(prefix+"."+PAR_QUERY_SIZE);
		query_per_cyc = Configuration.getInt(prefix+"."+PAR_QUERY_PER_CYC);
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		
		return false;
	}

}
