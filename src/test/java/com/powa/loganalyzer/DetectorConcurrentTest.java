package com.powa.loganalyzer;

import java.util.concurrent.CountDownLatch;

import com.powa.loganalyzer.core.LogAnalyzer;
import com.powa.loganalyzer.core.i.Detector;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DetectorConcurrentTest extends TestCase {

	LogAnalyzer log = new Detector();
	
	public DetectorConcurrentTest() {
		super("ConcurrentRequestDetectorTest");
	}
	
	public static Test suite(){
		return new TestSuite(DetectorConcurrentTest.class);
	}
	
	public void testConcurrentReqMoreThan5Within5mins(){
		CountDownLatch startOff = new CountDownLatch(1);
		CountDownLatch stopFlag = new CountDownLatch(10);
		for(int i=0;i<10;i++){
			new Thread(new ConcurrentReq(log, "30.212.19.124,1336129421,FAILURE,Thomas.Davenport", startOff,stopFlag)).start();
		}
		startOff.countDown();
		try {
			stopFlag.await();
			assertNotNull(log.parseLine("30.212.19.124,1336129421,FAILURE,Thomas.Davenport"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void testConcurrentReqMoreThan5Within5minsWith1Success(){
		CountDownLatch startOff = new CountDownLatch(1);
		CountDownLatch stopFlag = new CountDownLatch(10);
		for(int i=0;i<10;i++){
			if( i != 6)
				new Thread(new ConcurrentReq(log, "30.212.19.125,1336129421,FAILURE,Thomas.Davenport", startOff,stopFlag)).start();
			else 
				new Thread(new ConcurrentReq(log, "30.212.19.125,1336129421,SUCCESS,Thomas.Davenport", startOff,stopFlag)).start();
		}
		startOff.countDown();
		try {
			stopFlag.await();
			assertNotNull(log.parseLine("30.212.19.124,1336129421,FAILURE,Thomas.Davenport"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}


class ConcurrentReq implements Runnable {

	private LogAnalyzer log;
	private CountDownLatch startOff;
	private CountDownLatch stopFlag;
	private String line;
	
	public ConcurrentReq(LogAnalyzer log,String line,CountDownLatch startOff,CountDownLatch stopFlag) {
		this.log = log;
		this.line = line;
		this.startOff = startOff;
		this.stopFlag = stopFlag;
	}
	
	public void run() {
		try {
			startOff.await();
			log.parseLine(line);
			stopFlag.countDown();
		} catch (InterruptedException e) {}
	}
	
}