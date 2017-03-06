package com.test;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MiddleConnectionManager {
	
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			Context envCtx = (Context)ctx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("dzswDataSource");
			conn = ds.getConnection();
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "dzsw", "open");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeConnection(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		Connection connection = getConnection();
		System.out.println("");
		
	}
}
