package com.bway.springproject;

import testing.Calculation;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class CalculationTest {
	
	static Calculation cal = null;
	
	@BeforeClass
	public static void init(){
		cal = new Calculation();
		System.out.println("Before class");
	}
	
	@Test
	public void squareTest(){
		
		int res = cal.square(10);
		
		
		assertEquals(100,res);
	}
	
	@Test
	public void siTest(){
		int res = cal.getSI(5000, 5, 5);
		
		assertEquals(1250, res);
	}
	
	@AfterClass
	public static void closeAll(){
		cal =null;
		System.out.println("After class");
	}
}
