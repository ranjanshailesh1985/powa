package com.powa.loganalyzer;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.powa.loganalyzer.core.bean.LogBean;
import com.powa.loganalyzer.core.bean.LogType;
import com.powa.loganalyzer.core.ds.LogQueue;

public class LogQueueTest extends TestCase {

	LogQueue<LogBean> logQ = new LogQueue<LogBean>();
	
	public LogQueueTest(String testName) {
		super(testName);
	}
	
	
	public static Test suites(){
		return new TestSuite(LogQueueTest.class);
	}
	
	public void testQwithAdditionOfLog(){
		LogBean t = new LogBean();
		assertNull(logQ.add(t));
	}
	
	public void testQwithAdditionOfLogOver5(){
		LogBean t = new LogBean();
		logQ.add(t);
		t = new LogBean();
		logQ.add(t);
		t = new LogBean();
		logQ.add(t);
		t = new LogBean();
		logQ.add(t);
		t = new LogBean();
		logQ.add(t);
		t = new LogBean();
		logQ.add(t);
		assertNotNull(logQ.add(t));
	}
	
	public void testQwithAdditionOfLogOver5WithDifferentTime(){
		LogBean t = new LogBean();
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.MINUTE, -7);
		t.setSystemTime(cal.getTimeInMillis());
		logQ.add(t);
		t = new LogBean();
		cal = GregorianCalendar.getInstance();
		cal.add(Calendar.MINUTE, -4);
		t.setSystemTime(cal.getTimeInMillis());
		logQ.add(t);
		t = new LogBean();
		cal = GregorianCalendar.getInstance();
		cal.add(Calendar.MINUTE, -3);
		t.setSystemTime(cal.getTimeInMillis());
		logQ.add(t);
		t = new LogBean();
		cal = GregorianCalendar.getInstance();
		cal.add(Calendar.MINUTE, -2);
		t.setSystemTime(cal.getTimeInMillis());
		logQ.add(t);
		t = new LogBean();
		cal = GregorianCalendar.getInstance();
		cal.add(Calendar.MINUTE, -1);
		t.setSystemTime(cal.getTimeInMillis());
		logQ.add(t);
		t = new LogBean();
		cal = GregorianCalendar.getInstance();
		cal.add(Calendar.MINUTE, -2);
		t.setSystemTime(cal.getTimeInMillis());
		logQ.add(t);
		assertEquals(5, logQ.size());
	}
	
	
	public void testQwithAdditionOfSuccessLogOver5(){
		LogBean t = new LogBean();
		logQ.add(t);
		t = new LogBean();
		logQ.add(t);
		t = new LogBean();
		logQ.add(t);
		t = new LogBean();
		logQ.add(t);
		t = new LogBean();
		logQ.add(t);
		t = new LogBean();
		logQ.add(t);
		t = new LogBean();
		t.setAction(LogType.SUCCESS);
		logQ.add(t);
		assertEquals(0,logQ.size());
	}
}
