package com.powa.loganalyzer.core.bean;

public class LogBean {

	private String ip;
	private long logTime;
	private long systemTime;
	private LogType action;
	private String userName;
	
	public long getLogTime(){
		return this.logTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public LogType getAction() {
		return action;
	}

	public void setAction(LogType action) {
		this.action = action;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setLogTime(long logTime) {
		this.logTime = logTime;
	}

	public long getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(long systemTime) {
		this.systemTime = systemTime;
	}
	
}
