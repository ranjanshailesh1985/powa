package com.powa.loganalyzer.core.i;

import com.powa.loganalyzer.core.IRule;
import com.powa.loganalyzer.core.bean.LogBean;

public class MixRuleWithOutOrder implements IRule{

	IRule[] rules;
	
	public MixRuleWithOutOrder(IRule... rules) {
		this.rules = rules;
	}

	public boolean isAllowed(LogBean newLog, LogBean oldLog) {
		boolean result = true;
		for(IRule _per: rules){
			result &= _per.isAllowed(newLog, oldLog);
			if(!result)
				return result;
		}
		return result;
	}
}
