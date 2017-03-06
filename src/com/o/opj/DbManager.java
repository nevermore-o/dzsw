package com.o.opj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.o.connection.Query;

public class DbManager {
	
	
	/**
	 * 获取所有的表名
	 * @return
	 */
	public static ArrayList getDbTables(){
		ArrayList tableList = new ArrayList();
		ArrayList tempList=Query.doQuery("SELECT TABLE_NAME FROM TABS");
		int len=tempList.size();
		for(int i=0;i<len;i++){
			Map tMap=(HashMap)tempList.get(i);
			tableList.add(tMap.get("TABLE_NAME").toString().toUpperCase());
		}
		return tableList;
	}
	
	/**
	 * 获取某张表的所有字段名
	 * @param tableName
	 * @return
	 */
	public static ArrayList getColumnsByTableName(String tableName){
		ArrayList tableList = new ArrayList();
		ArrayList tempList=Query.doQuery(
				"SELECT * FROM USER_TAB_COLUMNS T WHERE T.TABLE_NAME='"+tableName.toUpperCase()+"'");
		int len=tempList.size();
		for(int i=0;i<len;i++){
			Map tMap=(HashMap)tempList.get(i);
			tableList.add(tMap.get("COLUMN_NAME").toString().toUpperCase());
		}
		return tableList;
		
	}
}
