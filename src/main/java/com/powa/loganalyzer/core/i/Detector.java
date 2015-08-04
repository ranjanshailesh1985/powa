package com.powa.loganalyzer.core.i;

import com.powa.loganalyzer.core.LogAnalyzer;
import com.powa.loganalyzer.core.bean.LogBean;
import com.powa.loganalyzer.core.bean.LogBeanBuilder;
import com.powa.loganalyzer.core.ds.IpDS;
import com.powa.loganalyzer.core.ds.LogQueue;

public class Detector implements LogAnalyzer {

	public String parseLine(String line) {
		LogBean log = LogBeanBuilder.createLog(line);
		if(IpDS.getInstance().containsKey(log.getIp())){
			// add concurrency for LogQueue.
			LogBean temp = null;
			LogQueue<LogBean> q = IpDS.getInstance().get(log.getIp());
			synchronized (q) {
				temp = q.add(log);
			}
			if(temp != null){
				return temp.getIp();
			} 
		} else {
			// handle add concurrency in map
			synchronized (IpDS.getInstance()) {
				// double check
				if(IpDS.getInstance().containsKey(log.getIp())){
					IpDS.getInstance().get(log.getIp()).add(log);
				} else {
					LogQueue<LogBean> ipReqQueue = new LogQueue<LogBean>();
					ipReqQueue.add(log);
					IpDS.getInstance().put(log.getIp(), ipReqQueue);
				}
			}
		}
		return null;
	}
}
