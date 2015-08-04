package com.powa.loganalyzer;

import com.powa.loganalyzer.core.i.Detector;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DetectorTest extends TestCase {

	Detector detector = new Detector();
	
	public DetectorTest(String testName) {
		super(testName);
	}
	
	public static Test suite(){
		return new TestSuite(DetectorTest.class);
	}
	
	
	public void testParseLineForLessThan5ReqPer5Min(){
		assertNull(detector.parseLine("30.212.19.124,1336129421,SUCCESS,Thomas.Davenport"));
	}
	
	
	public void testParseLineMoreThan5ReqIn5min(){
		for(int i=0;i<4;i++){
			try{Thread.sleep(1000);}catch(Exception ex){}
			detector.parseLine("30.212.19.124,1336129421,SUCCESS,Thomas.Davenport");
		}
		assertEquals("30.212.19.124", detector.parseLine("30.212.19.124,1336129421,SUCCESS,Thomas.Davenport"));
	}
	
	public void testParseLineForLessThan5ReqMoreThan5Min(){
		for(int i=0;i<4;i++){
			try{Thread.sleep(1000);}catch(Exception ex){}
			detector.parseLine("30.212.19.124,1336129421,SUCCESS,Thomas.Davenport");
		}
		assertNull(detector.parseLine("30.212.19.124,1336129421,SUCCESS,Thomas.Davenport"));
	}
	
	
	public void testParseLineFor5ReqinLessThan5MinWith1Success(){
		for(int i=0;i<4;i++){
			try{Thread.sleep(1000);}catch(Exception ex){}
			detector.parseLine("30.212.19.124,1336129421,FAILURE,Thomas.Davenport");
		}
		assertNull(detector.parseLine("30.212.19.124,1336129421,SUCCESS,Thomas.Davenport"));
	}
	
	public void testParseLineFor3ReqinLessThan5MinWith1Success(){
		for(int i=0;i<2;i++){
			try{Thread.sleep(1000);}catch(Exception ex){}
			detector.parseLine("30.212.19.124,1336129421,FAILURE,Thomas.Davenport");
		}
		assertNull(detector.parseLine("30.212.19.124,1336129421,SUCCESS,Thomas.Davenport"));
	}
}
