package com.o.log;

import java.util.Date;

public class Log {
	
	public static void debug(Object logStr){
		System.out.println(new Date()+"--Log--"+logStr);
	}
}
