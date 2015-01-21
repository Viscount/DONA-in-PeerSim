package dona.initial;

import peersim.config.Configuration;
import peersim.core.CommonState;
import peersim.dynamics.WireGraph;
import peersim.graph.Graph;

public class WireKNode extends WireGraph{
	
	private static final String PAR_DEGREE = "k";
	
	private int k;
	
	public WireKNode(String prefix){
		super(prefix);
		k = Configuration.getInt(prefix+"."+PAR_DEGREE,2);
	}

	@Override
	public void wire(Graph g) {
		// TODO Auto-generated method stub
		final int n = g.size();
		if( n < 2 ) return;
		if( n <= k ) k=n-1;
		int[] nodes = new int[n];
		for(int i=0; i<nodes.length; ++i) nodes[i]=i;
		for(int i=0; i<n; ++i)
		{
			int j=0;
			while(j<k)
			{
				int newedge = j+CommonState.r.nextInt(n-j);
				int tmp = nodes[j];
				nodes[j] = nodes[newedge];
				nodes[newedge] = tmp;
				if( nodes[j] != i )
				{
					g.setEdge(i,nodes[j]);
					g.setEdge(nodes[j],i);
					j++;
				}
			}
		}
	}

}
