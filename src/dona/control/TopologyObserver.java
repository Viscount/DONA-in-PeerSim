package dona.control;

import peersim.config.Configuration;
import peersim.config.FastConfig;
import peersim.core.Control;
import peersim.core.Linkable;
import peersim.core.Network;
import peersim.core.Node;

public class TopologyObserver implements Control{
	
	private static String PAR_PROT_INF = "inf_protocol";
	
	private static int pid_inf;
	
	
	public TopologyObserver(String prefix){
		pid_inf = Configuration.getPid(prefix+"."+PAR_PROT_INF);
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		
		int linkableID = FastConfig.getLinkable(pid_inf);
		for (int i=0; i<Network.size(); i++){
			Node node = Network.get(i);
	        Linkable linkable = (Linkable) node.getProtocol(linkableID);
	        if (linkable.degree() > 0) {
	        	for (int j=0; j<linkable.degree(); j++){
	        		Node neighbour = linkable.getNeighbor(j);
	        	}
	        }
		}
		return false;
	}

}
