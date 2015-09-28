package dona.initial;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import dona.entity.Cache;
import dona.entity.ConnectionManager;
import dona.entity.FIB;
import dona.entity.PIT;
import dona.protocol.Infrastructure;
import dona.util.Statistic;
import peersim.config.Configuration;
import peersim.config.FastConfig;
import peersim.core.CommonState;
import peersim.core.Control;
import peersim.core.Linkable;
import peersim.core.Network;
import peersim.core.Node;
import peersim.util.IncrementalStats;

public class Initializer implements Control{
	
	private static final String PAR_PROT_INF = "inf_protocol";
	
	private static int pid_inf;
	
	public Initializer(String prefix){
		pid_inf = Configuration.getPid(prefix+"."+PAR_PROT_INF);
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}

}
