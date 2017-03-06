package com.o.matrix.struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import util.StringUtil;

import com.o.file.FileUtil;

public class DefaultStructer implements Structer{

	private ArrayList resultList = new ArrayList();
	
	private HashMap map = new HashMap();
	
	private Map baseMap = new HashMap();
	
	private String[] column;

	private String[] type;
	
	private DataCell[][] dataCell;
	
	private int rowSize;
	
	private int colSize;
	
	private ColCell[] colCell;
	
	private RowCell[] rowCell;
	

	/**
	 * 将structer实例以table形式输出
	 */
	public String toHtmlTable(){
		int rowTmp[] = new int[rowCell.length];
		int rowTmpLen = rowTmp.length;
		// 最外面的一个行表头各对应里面的几个行表头
		for (int i = 0; i < this.rowCell.length; i++) {
			if (i == 0)
				rowTmp[i] = 1;
			else
				rowTmp[i] = rowTmp[i - 1] * this.rowCell[i].getRowCodeList().size();
		}
		// 每一个行表头各对应数据区域里面的几行数据区
		int rowSpan[] = new int[rowCell.length];
		for (int i = 0; i < rowCell.length; i++) {
			rowSpan[i] = rowTmp[rowTmpLen - 1] / rowTmp[i];
		}
		int x = 1;
		StringBuffer htmlStr = new StringBuffer(
				"<table class='title' border='1'><tr><th rowspan='");
		htmlStr.append(colCell.length + "' colspan='").append(rowCell.length)
				.append("'>").append("</th>");
		int eachColLength = 1;
		for (int p = 0; p < colCell.length; p++) {
			if (p != 0)
				htmlStr.append("<tr>");
			ColCell cc = colCell[p];
			eachColLength *= cc.getColCodeList().size();
			x *= cc.getColCodeList().size();
			String cs = String.valueOf(colSize / x);// colspan
			for (int q = 0; q < eachColLength; q++) {
				int eachColIndex = 0;
				eachColIndex = q % cc.getColCodeList().size();
				Object o = cc.getColCodeList().get(eachColIndex);
				htmlStr.append("<th class='colCell' colspan='").append(cs)
						.append("'>" + o.toString() + "</th>");
			}
			htmlStr.append("</tr>");
		}
		// 逐行、逐列输出
		for (int i = 0; i < rowSize; i++) {
			// 构造行表头
			boolean isGo = false;
			// 拿到第i行，第0列的行表头索引
			int[] rowIndex = this.dataCell[i][0].getRowIndex();
			htmlStr.append("<tr>");
			// 记录下面循环时当前表头的总数量
			int rowNum = 1;
			for (int r = 0; r < rowCell.length; r++) {
				int size = rowCell[r].getRowCodeList().size();
				rowNum *= size;
				if (i % rowSpan[r] == 0 || isGo) {
					htmlStr.append("<td class='rowCell' rowspan='").append(
							rowSpan[r]).append("'>").append(
							rowCell[r].getRowCodeList().get(
									StringUtil
											.removeDot(i / (rowSize / rowNum))
											% size)).append("</td>");
					isGo = true;
				} else
					isGo = false;
			}
			// 数据区域
			for (int j = 0; j < colSize; j++) {
				htmlStr.append("<td class='dataCell'>"
						+ dataCell[i][j].getCellValue() + "</td>");
			}
			htmlStr.append("</tr>");
		}
		htmlStr.append("</table>");
		return htmlStr.toString();
	}
	
	/**
	 * 根据数据区域在行获取行索引数组
	 * @param i
	 * @param j
	 * @return
	 */
	public int[] getRowIndex(int i, int j) {
		// TODO Auto-generated method stub
		int rowNum=1;;
		int[] rowIndex=new int[rowCell.length];
		for (int r = 0; r < this.rowCell.length; r++) {
			int size = rowCell[r].getRowCodeList().size();
			rowNum *= size;
			rowIndex[r]=StringUtil.removeDot(i /(rowSize/rowNum))% size;
		}
		return rowIndex;
	}
	
	/**
	 * 获取索引为i列的列名
	 * @param i
	 * @return
	 */
	public String getColumn(int i){
		return this.column[i];
	}
	
	/**
	 * 设置索引为index列的列名
	 * @param columnName
	 * @param index
	 */
	public void setColumnName(String columnName, int index){
		if(index<=this.column.length){
			column[index] = columnName;
		}else{
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	
	/**
	 * 获取索引为i列的类型
	 * @param i
	 * @return
	 */
	public String getType(int i){
		return this.type[i];
	}
	
	/**
	 * 获取行数
	 * @return
	 */
	public int getRowCount(){
		return this.resultList.size();
	}
	
	/**
	 * 获取列数
	 * @return
	 */
	public int getColCount(){
		return ((ArrayList)this.resultList.get(0)).size();
	}
	
	public String[] getColumn() {
		return column;
	}

	public void setColumn(String[] column) {
		this.column = column;
	}

	public String[] getType() {
		return type;
	}

	public void setType(String[] type) {
		this.type = type;
	}
	

	public ArrayList getResultList() {
		return resultList;
	}

	public void setResultList(ArrayList resultList) {
		this.resultList = resultList;
	}

	public Map getBaseMap() {
		return baseMap;
	}

	public void setBaseMap(Map baseMap) {
		this.baseMap = baseMap;
	}

	public HashMap getMap() {
		return map;
	}

	public void setMap(HashMap map) {
		this.map = map;
	}

	public DataCell[][] getDataCell() {
		return dataCell;
	}

	public void setDataCell(DataCell[][] dataCell) {
		this.dataCell = dataCell;
	}

	public int getRowSize() {
		return rowSize;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public int getColSize() {
		return colSize;
	}

	public void setColSize(int colSize) {
		this.colSize = colSize;
	}

	public ColCell[] getColCell() {
		return this.colCell;
	}

	public void setColCell(ColCell[] collCell) {
		this.colCell = collCell;
	}

	public RowCell[] getRowCell() {
		return rowCell;
	}

	public void setRowCell(RowCell[] rowCell) {
		this.rowCell = rowCell;
	}

}
