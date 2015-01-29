package dona.control;

import java.util.List;

import dona.entity.Message;
import dona.protocol.Infrastructure;
import dona.util.Log;
import dona.util.Statistic;
import peersim.config.Configuration;
import peersim.config.FastConfig;
import peersim.core.CommonState;
import peersim.core.Control;
import peersim.core.Network;
import peersim.core.Node;
import peersim.transport.Transport;

public class QueryProducer implements Control{
	
	private static final String PAR_PROT_INF = "inf_protocol";
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
		if ( Statistic.query_index < query_size ){
			int startIndex = Statistic.query_index;
			for (int i=startIndex; i<startIndex + query_per_cyc; i++){
				if ( i >= query_size ) break;
				Statistic.query_index++;
				int random = CommonState.r.nextInt(Network.size());
				Infrastructure inf = (Infrastructure) Network.get(random).getProtocol(pid_inf);
				int query = CommonState.r.nextInt(Statistic.FILE_NUM);
				
				if ( Statistic.LOG ) Log.write("Query "+Statistic.query_index+" Node "+random+" requires file "+query);
				
				inf.connectionManager.addQuery(Integer.toString(query));
				if (inf.contentStore.containsKey(Integer.toString(query))){
					
					if ( Statistic.LOG ) Log.write("Query "+random+" for file "+query+
							" Transport completed.");
					
					Statistic.query_complete++;
					inf.connectionManager.deleteEntry(Integer.toString(query));
				}
				else {
					Message que_mess = new Message("QUE",random,Integer.toString(query));
					que_mess.insertInfo("RequesterID", random);
					que_mess.setTTL(Statistic.QUE_TTL);
					List neighbors = inf.neighbors;
					for (int k=0; k<neighbors.size(); k++){
						Node node = Network.get(random);
						((Transport)node.getProtocol(FastConfig.getTransport(pid_inf))).
						send(node, Network.get((int) inf.neighbors.get(k)), que_mess, pid_inf);
					}
				}
			}
		}
		return false;
	}

}
