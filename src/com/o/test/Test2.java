package com.o.test;

import java.util.ArrayList;

import com.o.opj.DbManager;



public class Test2 {

	
	public static void main(String[] args){
		StringBuilder sb = new StringBuilder();
		ArrayList resultlist2 = new ArrayList();
		resultlist2=new DbManager().getColumnsByTableName("ajgl_case");
		for(Object o:resultlist2){
			sb.append(o.toString());
		}
		System.out.println(sb.toString());
	}
}
