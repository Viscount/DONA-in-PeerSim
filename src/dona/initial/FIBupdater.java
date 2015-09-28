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
		// TODO Auto-generated method stub
		return false;
	}
}
