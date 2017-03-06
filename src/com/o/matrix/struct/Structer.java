package com.o.matrix.struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface  Structer {
	

	/**
	 * 根据数据区域在行、列左边获取行索引数组
	 * @param i
	 * @param j
	 * @return
	 */
	public int[] getRowIndex(int i, int j);
		
		
	public String toHtmlTable();
	/**
	 * 获取索引为i列的列名
	 * @param i
	 * @return
	 */
	public String getColumn(int i);
	
	/**
	 * 设置索引为index列的列名
	 * @param columnName
	 * @param index
	 */
	public void setColumnName(String columnName, int index);
	
	/**
	 * 获取索引为i列的类型
	 * @param i
	 * @return
	 */
	public String getType(int i);
	
	/**
	 * 获取行数
	 * @return
	 */
	public int getRowCount();
	
	/**
	 * 获取列数
	 * @return
	 */
	public int getColCount();
	
	public String[] getColumn();

	public void setColumn(String[] column);

	public String[] getType() ;

	public void setType(String[] type);
	

	public ArrayList getResultList() ;

	public void setResultList(ArrayList resultList);

	public Map getBaseMap();
	
	public void setBaseMap(Map baseMap);

	public HashMap getMap() ;

	public void setMap(HashMap map) ;

	public DataCell[][] getDataCell() ;

	public void setDataCell(DataCell[][] dataCell) ;

	public int getRowSize();

	public void setRowSize(int rowSize) ;

	public int getColSize() ;
	
	public void setColSize(int colSize) ;

	public ColCell[] getColCell() ;

	public void setColCell(ColCell[] collCell) ;

	public RowCell[] getRowCell();

	public void setRowCell(RowCell[] rowCell) ;
	
	
}
