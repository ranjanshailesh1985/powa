package com.powa.loganalyzer.core;

import com.powa.loganalyzer.core.bean.LogBean;

public interface IRule {

	boolean isAllowed(LogBean newLog,LogBean oldLog);
	
}
