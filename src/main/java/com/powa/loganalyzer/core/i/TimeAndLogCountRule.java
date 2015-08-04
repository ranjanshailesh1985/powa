package com.powa.loganalyzer.core.i;

import com.powa.loganalyzer.core.IRule;
import com.powa.loganalyzer.core.bean.LogBean;

public class TimeAndLogCountRule implements IRule {

	public TimeAndLogCountRule() {}

	final static long FIVE_MINS =  5*60*1000L;
	
	public boolean isAllowed(LogBean newLog, LogBean oldLog) {
		long timeDiff = newLog.getSystemTime() - oldLog.getSystemTime();
		return timeDiff<=FIVE_MINS;
	}

}
