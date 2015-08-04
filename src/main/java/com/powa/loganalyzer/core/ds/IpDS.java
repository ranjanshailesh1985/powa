package com.powa.loganalyzer.core.ds;

import java.util.HashMap;
import java.util.Map;

import com.powa.loganalyzer.core.bean.LogBean;

//for handling concurrency
public class IpDS {

	private static final IpDS ipCache = new IpDS();
	
	// IP based cache
	Map<String, LogQueue<LogBean>> ipStore = new HashMap<String, LogQueue<LogBean>>();
	
	public static IpDS getInstance(){
		return ipCache;
	}
	// Write operations, concurrent modification can be handled here.
	public synchronized void put(String ip,LogQueue<LogBean> ipLogQ){
		ipStore.put(ip, ipLogQ);
	}
	
	// Read, operation needed to be concurrent
	public LogQueue<LogBean> get(String ip){
		return ipStore.get(ip);
	}
	
	// Read, operation needed to be concurrent
	public boolean containsKey(String ip){
		return ipStore.containsKey(ip);
	}
}
