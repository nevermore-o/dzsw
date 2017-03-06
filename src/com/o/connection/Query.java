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
			Log.debug("����ִ�У���������������"+sql);
			ResultSetMetaData resultMetaData = rs.getMetaData();
			int i = resultMetaData.getColumnCount();
			column = new String[i];
			type = new String[i];
			for (int j = 0; j < i; j++) {
				column[j] = resultMetaData.getColumnName(j + 1).toUpperCase();
				type[j] = resultMetaData.getColumnTypeName(j + 1).toUpperCase();
				// Log.debug("��"+(j+1)+"�е������ǣ�"+resultMetaData.getColumnName(j+1)
				// );
				// Log.debug("��"+(j+1)+"�е������ǣ�"+resultMetaData.getColumnTypeName(j+1)
				// );
				// Log.debug("��"+(j+1)+"�е�Label�ǣ�"+resultMetaData.getColumnLabel(j+1)
				// );
			}
			resultList = makeArrListBySql(rs, column, type);
		} catch (Exception e) {
			Log.debug("doQueryʱ�����쳣");
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				Log.debug("�ر���������ʱ�����쳣��");
			}
		}
		return resultList;
	}


    /**
	 * ����sql��乹��ArrayList
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
	 * ����sql��乹�����С��б�ͷ���������ݵĽṹ��Structer
	 * @param sql
	 * @param rowCode
	 * @param colCode
	 * @return ���ƴװStructer�ṹ����������ݣ�������map�����£�
	 * resultList �� Sqlִ�к���װ��ArrayList�Ľ����
	 * tota		  �� resultList�ĳ���
	 * rowCodeList���б�ͷ
	 * ColCell[]  : �б�ͷ 
	 * column[]	  : ���������������
	 * type[]	  �� ����������е�����
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
				// Log.debug("��"+(j+1)+"�е������ǣ�"+resultMetaData.getColumnName(j+1)
				// );
				// Log.debug("��"+(j+1)+"�е������ǣ�"+resultMetaData.getColumnTypeName(j+1)
				// );
				// Log.debug("��"+(j+1)+"�е�Label�ǣ�"+resultMetaData.getColumnLabel(j+1)
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
				 * �������е��б�ͷ
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
				 * �������е��б�ͷ
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
