package com.o.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.o.log.Log;

public class ConnectionManager {

	private static String driver;
	private static String url;
	private static String user;
	private static String password;

	static {
		try {
			Properties prop = new Properties();
			prop.load(ConnectionManager.class.getClassLoader()
					.getResourceAsStream("custom.properties"));
			driver = prop.getProperty("jdbc.driver");
			url = prop.getProperty("jdbc.connection");
			user = prop.getProperty("connection.user");
			password = prop.getProperty("connection.password");
			Class.forName(driver);
		} catch (IOException e){
			Log.debug("读取custom.properties文件时出现异常!");
		} catch (ClassNotFoundException e) {
			Log.debug("初始化加载ConnectionManager类时出现异常!");
			Log.debug(e.getMessage());
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Connection getConnection(String jndi) {
		Connection conn = null;
		try {
			 Context ctx = new InitialContext(); 
			 Context envCtx =(Context)ctx.lookup("java:comp/env"); 
			 DataSource ds = (DataSource)envCtx.lookup(jndi); 
			 conn =ds.getConnection();
		} catch (Exception e) {
			Log.debug("通过jndi:"+jndi+"查找数据源时出现异常");
		}
		return conn;
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
