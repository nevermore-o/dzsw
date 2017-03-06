package com.o.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.o.log.Log;
import com.o.matrix.struct.ColCell;
import com.o.matrix.struct.DefaultStructer;
import com.o.matrix.struct.RowCell;
import com.o.matrix.struct.Structer;

public class Query {

	public static ArrayList doQuery(String sql) {
		ArrayList  resultList=new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String[] column;
		String[] type;
		try {
			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			Log.debug("正在执行：：：：：：：："+sql);
			ResultSetMetaData resultMetaData = rs.getMetaData();
			int i = resultMetaData.getColumnCount();
			column = new String[i];
			type = new String[i];
			for (int j = 0; j < i; j++) {
				column[j] = resultMetaData.getColumnName(j + 1).toUpperCase();
				type[j] = resultMetaData.getColumnTypeName(j + 1).toUpperCase();
				// Log.debug("第"+(j+1)+"列的名称是："+resultMetaData.getColumnName(j+1)
				// );
				// Log.debug("第"+(j+1)+"列的类型是："+resultMetaData.getColumnTypeName(j+1)
				// );
				// Log.debug("第"+(j+1)+"列的Label是："+resultMetaData.getColumnLabel(j+1)
				// );
			}
			resultList = makeArrListBySql(rs, column, type);
		} catch (Exception e) {
			Log.debug("doQuery时出现异常");
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				Log.debug("关闭数据连接时出现异常！");
			}
		}
		return resultList;
	}


    /**
	 * 根据sql语句构建ArrayList
	 */
	private static ArrayList makeArrListBySql(ResultSet rs, String[] column,
			String[] type) {
		ArrayList resultList = new ArrayList();
		try {
			while (rs.next()) {
				Map rowMap=new HashMap();
				for (int i = 0, j = column.length; i < j; i++) {
					rowMap.put(column[i],rs.getObject(i + 1));
				}
				resultList.add(rowMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	/**
	 * 根据sql语句构造有行、列表头，数据数据的结构类Structer
	 * @param sql
	 * @param rowCode
	 * @param colCode
	 * @return 存放拼装Structer结构体所需的数据，并放在map中如下：
	 * resultList ： Sql执行后组装成ArrayList的结果集
	 * tota		  ： resultList的长度
	 * rowCodeList：行表头
	 * ColCell[]  : 列表头 
	 * column[]	  : 结果集的所有列名
	 * type[]	  ： 结果集所有列的类型
	 */
	public static HashMap doSelect(String sql, String[] rowCode, String[] colCode) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList resultList = new ArrayList();
		HashMap resultMap = new HashMap();
		String[] column;
		String[] type;
		int colLen = colCode.length;
		int rowLen = rowCode.length;
		ColCell[] colCell=new ColCell[colLen];
		RowCell[] rowCell=new RowCell[rowLen];
		try {
			Connection conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmData = rs.getMetaData();
			int leng=rsmData.getColumnCount();
			column=new String[leng];
			type=new String[leng];
			for (int j = 0; j <leng; j++) {
				column[j] = rsmData.getColumnName(j + 1);
				type[j] = rsmData.getColumnTypeName(j + 1);
				// Log.debug("第"+(j+1)+"列的名称是："+resultMetaData.getColumnName(j+1)
				// );
				// Log.debug("第"+(j+1)+"列的类型是："+resultMetaData.getColumnTypeName(j+1)
				// );
				// Log.debug("第"+(j+1)+"列的Label是："+resultMetaData.getColumnLabel(j+1)
				// );
			}
			while (rs.next()) {
				HashMap colMap = new HashMap();
				boolean hasNullVal = false;
				for (int i = 0, i1 = rsmData.getColumnCount(); i < i1; i++) {
					Object obj = rs.getObject(rsmData.getColumnName(i + 1));
					if (obj == null){
						hasNullVal =true;
						break;
					}
					colMap.put(rsmData.getColumnName(i + 1).toUpperCase(), obj);
				}
				if(!hasNullVal)
					resultList.add(colMap);
				/**
				 * 构造所有的行表头
				 */
				if (rowCode != null) {
					for(int i=0;i<rowLen;i++){
						if(rowCell[i]==null)
							rowCell[i]=new RowCell();
						Object rsRowObj = rs.getObject(rowCode[i]);
						if (rsRowObj != null 
								&& !rowCell[i].getRowCodeList().contains(rsRowObj))
							rowCell[i].getRowCodeList().add(rsRowObj);
					}
				}
				/**
				 * 构造所有的列表头
				 */
				if (colCode != null) {
					for(int i=0;i<colCode.length;i++){
						if(colCell[i]==null)
							colCell[i] =new ColCell();
						Object rsColObj = rs.getObject(colCode[i]);
						if (rsColObj != null
								&& !colCell[i].getColCodeList().contains(rsColObj))
							colCell[i].getColCodeList().add(rsColObj);
					}
				}
			}
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			resultMap.put("resultList", resultList);
			resultMap.put("total", resultList.size());
			resultMap.put("rowCell", rowCell);
			resultMap.put("colCell", colCell);
			resultMap.put("column", column);
			resultMap.put("type", type);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (Exception e1) {
				e1.printStackTrace();
				return null;
			}
		}
		return resultMap;
	}
	
	
}
