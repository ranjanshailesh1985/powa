package com.powa.loganalyzer;

import java.util.Timer;

public class TimerForQManagement {

	
	static Timer timer;
	
	static{
		timer = new Timer();
		timer.scheduleAtFixedRate(new QManagementTask(), 200l, 5*60*1000);
	}
	
	
	
}
