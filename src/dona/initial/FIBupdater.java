package dona.initial;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import dona.protocol.Infrastructure;
import peersim.config.Configuration;
import peersim.core.Control;
import peersim.core.Network;
import peersim.core.Node;

public class FIBupdater implements Control{
	
	private static final String PAR_PROT_INF = "inf_protocol";
	
	private static int pid_inf;
	
	public FIBupdater(String prefix){
		pid_inf = Configuration.getPid(prefix+"."+PAR_PROT_INF);
	}

	@Override
	public boolean execute() {
		for (int i=0; i<Network.size(); i++){
			Node node = Network.get(i);
			Infrastructure inf = (Infrastructure) node.getProtocol(pid_inf);
			if (!inf.contentStore.isEmpty()){
				for (Iterator it = inf.contentStore.entrySet().iterator(); it.hasNext();){
					Entry entry = (Entry) it.next();
					String contentName = (String) entry.getKey();
					spread(node,contentName);
				}
			}
		}
		// TODO Auto-generated method stub
		return false;
	}
	
	private void spread(Node node, String content){
		List queue = new ArrayList();
		queue.add(node);
		while(!queue.isEmpty()){
			Node now_node = (Node) queue.get(0);
			Infrastructure inf = (Infrastructure) node.getProtocol(pid_inf);
			for (int i=0; i<inf.neighbors.size(); i++){
				Node neighbor = Network.get((int) inf.neighbors.get(i));
				Infrastructure neighbor_inf = (Infrastructure) neighbor.getProtocol(pid_inf);
				if ( neighbor_inf.fib.containsSource(content,node.getIndex())){
					neighbor_inf.fib.addItem(content, node.getIndex(), now_node.getIndex());
					queue.add(neighbor);
				}
			}
			queue.remove(0);
		}
	}

}
