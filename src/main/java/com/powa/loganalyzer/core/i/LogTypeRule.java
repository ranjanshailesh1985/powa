package com.powa.loganalyzer.core.i;

import com.powa.loganalyzer.core.IRule;
import com.powa.loganalyzer.core.bean.LogBean;
import com.powa.loganalyzer.core.bean.LogType;

public class LogTypeRule implements IRule {

	public boolean isAllowed(LogBean newLog, LogBean oldLog) {
		return newLog.getAction() == LogType.FAILURE;
	}

}
