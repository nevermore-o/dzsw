package com.o.matrix.struct.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.o.connection.Query;
import com.o.log.Log;
import com.o.matrix.struct.ColCell;
import com.o.matrix.struct.DataCell;
import com.o.matrix.struct.DefaultStructer;
import com.o.matrix.struct.RowCell;
import com.o.matrix.struct.Structer;
import com.o.matrix.struct.service.StructerService;

public class StructerServiceImpl implements StructerService{
	
	
	
	
	/**
	 * 根据sql语句构造有行、列表头，数据数据的结构类Structer
	 * @param sql
	 * @param rowCode
	 * @param colCode
	 * @return
	 */
	public Structer conStruct(String sql, String[] rowCode, String[] colCode) {
		Log.debug("开始构造Structer：：：：：：：：");
		Structer stuc = new DefaultStructer();
		HashMap tempMap = new HashMap();
		tempMap = Query.doSelect(sql, rowCode, colCode);
		ArrayList resultList = new ArrayList();
		ArrayList rowCodeList = new ArrayList();
		RowCell[] rowCell;//列表头
		ColCell[] colCell;//列表头
		int[] eachRowLen;//记录每个rowCode表头的长度
		int[] eachColLen;//记录每个colCode表头的长度
		String[] column;
		String[] type;
		int colSize=1;
		int rowSize=1;
		if (tempMap != null && tempMap.containsKey("resultList")){
			resultList = (ArrayList) tempMap.get("resultList");
			rowCell=(RowCell[])tempMap.get("rowCell");
			colCell = (ColCell[])tempMap.get("colCell");
			eachRowLen=new int[rowCell.length];
			eachColLen=new int[colCell.length];
			//记录每个rowCode表头的长度
			for(int x=0;x<rowCell.length;x++){
				rowSize*=rowCell[x].getRowCodeList().size();
				eachRowLen[x]=rowCell[x].getRowCodeList().size();
			}
			//记录每个colCode表头的长度
			for(int x=0;x<colCell.length;x++){
				colSize*=colCell[x].getColCodeList().size();
				eachColLen[x]=colCell[x].getColCodeList().size();
			}
			column=(String[])tempMap.get("column");
			type=(String[])tempMap.get("type");
		}else{
			Log.debug("结构体Structer构造失败：：：：：：：：");
			return null;
		}
		DataCell[][] dcResult=new DataCell[rowSize][colSize];
		/**
		 *  循环Sql得到的原始数据的每一行
		 */
		for (int i = 0; i < resultList.size() ; i++) {
			HashMap map = new HashMap();
			map = (HashMap) resultList.get(i);
			Object[] mapRowCode = new Object[rowCell.length];
			Object[] mapColCode =new Object[colCell.length];
			int[] rowIndex=new int[rowCell.length];
			int[] colIndex=new int[colCell.length];
			//该行数据的每一个列数据在行表头里的索引
			for(int j=0;j<rowCode.length;j++){
				mapRowCode[j]=(Object)map.get(rowCode[j]);//每一个列表头对应的值
				rowIndex[j]=rowCell[j].getRowCodeList().indexOf(mapRowCode[j]);//没一个列表头值在列表头数组里的索引
			}
			
			//该行数据的每一个列数据在列表头里的索引
			for(int j=0;j<colCode.length;j++){
				mapColCode[j]=(Object)map.get(colCode[j]);//每一个列表头对应的值
				colIndex[j]=colCell[j].getColCodeList().indexOf(mapColCode[j]);//没一个列表头值在列表头数组里的索引
			}
			int m=0;
			int n=0;
			/**
			 * m,为数据区域列行方向的索引
			 */
			for(int p=0;p<rowCell.length;p++){
				if(p==0)
					m=rowIndex[p];
				else{
					m=m*eachRowLen[p]+rowIndex[p];//每次循环相当于一次递归，当前循环行表头的长度*当前列表头值在列表头数组里的索引
				}
			}
			/**
			 * n,为数据区域列方向的索引
			 */
			for(int p=0;p<colCell.length;p++){
				if(p==0)
					n=colIndex[p];
				else{
					n=n*eachColLen[p]+colIndex[p];//每次循环相当于一次递归，当前循环列表头的长度*当前列表头值在列表头数组里的索引
				}
			}
			String value = map.get("COUNT").toString();
			DataCell cell = new DataCell();
			cell.setCellValue(value);
			cell.setRowIndex(rowIndex);
			cell.setColIndex(colIndex);
			dcResult[m][n]=cell;

		}
		for(int i=0;i<rowSize;i++){
			for(int j=0;j<colSize;j++){
				if(dcResult[i][j]==null){
					DataCell cell = new DataCell();
					cell.setCellValue("0");
					dcResult[i][j]=cell;
				}
			}
		}
		stuc.setResultList(resultList);
		stuc.setRowCell(rowCell);
		stuc.setColumn(column);
		stuc.setColCell(colCell);
		stuc.setType(type);
		stuc.setRowSize(rowSize);
		stuc.setColSize(colSize);
		stuc.setDataCell(dcResult);
		Log.debug("Success construct a structer!");
		return stuc;
	}
	
	
		
		

}
