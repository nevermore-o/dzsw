package com.o.test;

import org.junit.Test;


public class TTest {

	@Test
	public void test(){
		System.out.println(getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath());
		
		
	}
}
