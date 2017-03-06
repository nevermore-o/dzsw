package com.o.matrix.struct;

public class DataCell implements Cloneable{

	private String cellValue;
	
	private String url;
	
	private String css;

	//行表头索引
	private int[] rowIndex;
	
	//列表头索引
	private int[] colIndex;


	public String getCellValue() {
		return cellValue;
	}

	public void setCellValue(String cellValue) {
		this.cellValue = cellValue;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public int[] getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int[] rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int[] getColIndex() {
		return colIndex;
	}

	public void setColIndex(int[] colIndex) {
		this.colIndex = colIndex;
	}
	
	
}
