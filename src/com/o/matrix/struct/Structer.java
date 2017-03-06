package com.o.matrix.struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface  Structer {
	

	/**
	 * ���������������С�����߻�ȡ����������
	 * @param i
	 * @param j
	 * @return
	 */
	public int[] getRowIndex(int i, int j);
		
		
	public String toHtmlTable();
	/**
	 * ��ȡ����Ϊi�е�����
	 * @param i
	 * @return
	 */
	public String getColumn(int i);
	
	/**
	 * ��������Ϊindex�е�����
	 * @param columnName
	 * @param index
	 */
	public void setColumnName(String columnName, int index);
	
	/**
	 * ��ȡ����Ϊi�е�����
	 * @param i
	 * @return
	 */
	public String getType(int i);
	
	/**
	 * ��ȡ����
	 * @return
	 */
	public int getRowCount();
	
	/**
	 * ��ȡ����
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
