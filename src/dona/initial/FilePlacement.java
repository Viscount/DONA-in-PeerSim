package dona.initial;

import java.util.List;

import dona.entity.Message;
import dona.protocol.Infrastructure;
import dona.util.Statistic;
import peersim.config.Configuration;
import peersim.config.FastConfig;
import peersim.core.CommonState;
import peersim.core.Control;
import peersim.core.Network;
import peersim.core.Node;
import peersim.transport.Transport;

public class FilePlacement implements Control{
	
	private static final String PAR_PROT_INF = "inf_protocol";
	private static final String PAR_INIT_SOURCE = "init_source"; 
	
	private static int pid_inf;
	private static int init_source;
	
	public FilePlacement(String prefix){
		pid_inf = Configuration.getPid(prefix+"."+PAR_PROT_INF);
		init_source = Configuration.getInt(prefix+"."+PAR_INIT_SOURCE);
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		for (int i=0; i<Statistic.FILE_NUM; i++){
			for (int j=0; j<init_source; j++){
				int random = CommonState.r.nextInt(Network.size());
				Infrastructure inf = (Infrastructure) Network.get(random).getProtocol(pid_inf);
				inf.contentStore.put(Integer.toString(i), Statistic.fileChunkNum.get(Integer.toString(i)));
				
				// optional, REG for initial resource
				
//				Message reg_mess = new Message("REG",random,Integer.toString(i));
//				reg_mess.insertInfo("SourceID", random);
//				reg_mess.setTTL(Statistic.REG_TTL);
//				List neighbors = inf.neighbors;
//				for (int k=0; k<neighbors.size(); k++){
//					Node node = Network.get(random);
//					((Transport)node.getProtocol(FastConfig.getTransport(pid_inf))).
//					send(node, Network.get((int) inf.neighbors.get(k)), reg_mess, pid_inf);
//				}
			}
		}
		return false;
	}

}
