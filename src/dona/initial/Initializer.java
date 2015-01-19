package dona.initial;

import java.util.ArrayList;
import java.util.HashMap;

import dona.entity.ConnectionManager;
import dona.entity.FIB;
import dona.entity.PIT;
import dona.protocol.Infrastructure;
import peersim.config.Configuration;
import peersim.config.FastConfig;
import peersim.core.Control;
import peersim.core.Linkable;
import peersim.core.Network;
import peersim.core.Node;

public class Initializer implements Control{
	
	private static String PAR_PROT_INF = "inf_protocol";
	
	private static int pid_inf;
	
	public Initializer(String prefix){
		pid_inf = Configuration.getPid(prefix+"."+PAR_PROT_INF);
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		int linkableID = FastConfig.getLinkable(pid_inf);
		
		for (int i=0; i<Network.size(); i++){
			Infrastructure inf = (Infrastructure) Network.get(i).getProtocol(pid_inf);
			inf.connectionManager = new ConnectionManager();
			inf.contentStore = new HashMap();
			inf.fib = new FIB();
			inf.pit = new PIT();
			inf.neighbors = new ArrayList();
			
			Linkable linkable = (Linkable) Network.get(i).getProtocol(linkableID);
		    	if (linkable.degree() > 0) {
		    		for (int j=0; j<linkable.degree(); j++){
		        		Node neighbour = linkable.getNeighbor(j);
		        		inf.neighbors.add(neighbour.getIndex());
		        	}
		        }
		}
		return false;
	}

}
