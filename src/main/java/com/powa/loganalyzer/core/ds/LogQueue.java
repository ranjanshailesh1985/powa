package com.powa.loganalyzer.core.ds;

import java.util.ArrayDeque;
import java.util.Deque;

import com.powa.loganalyzer.core.IRule;
import com.powa.loganalyzer.core.bean.LogBean;
import com.powa.loganalyzer.core.bean.LogType;
import com.powa.loganalyzer.core.i.LogTypeRule;
import com.powa.loganalyzer.core.i.MixRuleWithOutOrder;
import com.powa.loganalyzer.core.i.TimeAndLogCountRule;

public class LogQueue<T extends LogBean> {

	IRule permission;
	
	Deque<T> q;

	// tight coupling of object
	public LogQueue() {
		this.permission = new MixRuleWithOutOrder(new LogTypeRule(),new TimeAndLogCountRule());
		this.q = new ArrayDeque<T>();
	}
	
	// working on the cache of request
	public T add(T t){
		if(!q.isEmpty()){
			if( permission.isAllowed(t, q.peek()) ){
				q.add(t);
				if(q.size() >= 5){
					return t;
				}
			} else {
				q.remove();
				add(t);
			}
		} else {
			if(t.getAction() != LogType.SUCCESS)
				q.add(t);
		}
		return null;
	}
	
	public T remove(){
		if(q.isEmpty())
			return null;
		else 
			return q.remove();
	}
	
	public int size(){
		return this.q.size();
	}
}
