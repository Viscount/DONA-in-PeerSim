package dona.control;

import dona.util.Statistic;
import peersim.core.Control;

public class IndicatorObserver implements Control{
	
	public IndicatorObserver(String prefix){
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		System.out.println("Query Complete: " + Statistic.query_complete);
		System.out.println("Total Time: " + Statistic.total_time);
		System.out.println("Average Time: " + Statistic.total_time * 1.0 / Statistic.query_complete);
		return false;
	}

}
