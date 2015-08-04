package com.powa.loganalyzer.core.bean;

import java.util.StringTokenizer;


public class LogBeanBuilder {

	/**
	•	ip:  is like  30.212.19.124
	•	date: is in the epoch format like 1336129421
	•	action:  can be SUCCESS or FAILURE
	•	username: is a String like Thomas.Davenport
	*/
	
	public static LogBean createLog(String line){
		StringTokenizer token = new StringTokenizer(line,",");
		int elePos = 1;
		LogBean log = new LogBean();
		while(token.hasMoreTokens()){
			log = getLogObject(elePos, token.nextElement().toString(), log);
			elePos++;
		}
		return log;
	}
	
	protected static LogBean getLogObject(int elePos,String ip,LogBean log){
		try{
			if(elePos == 1){
				log.setIp(ip);
			}
			if(elePos == 2){
				log.setLogTime(Long.valueOf(ip));
			}
			if(elePos == 3){
				log.setAction(LogType.valueOf(ip));
			}
			if(elePos == 4){
				log.setUserName(ip);
				log.setSystemTime(System.currentTimeMillis());
			}
		} catch(Exception ex){
			System.out.println("Unable to Build Log Object"+ex.getLocalizedMessage());
			//TODO throw exception
		}
		return log;
	}
}