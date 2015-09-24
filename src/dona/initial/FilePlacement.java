package dona.initial;

import java.util.ArrayList;
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

		return false;
	}

}
