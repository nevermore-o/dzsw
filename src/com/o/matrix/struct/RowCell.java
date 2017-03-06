package com.o.matrix.struct;

import java.util.ArrayList;

public class RowCell {

	private String cellValue;
	
	private ArrayList rowCodeList=new ArrayList();

	public String getCellValue() {
		return cellValue;
	}

	public void setCellValue(String cellValue) {
		this.cellValue = cellValue;
	}

	public ArrayList getRowCodeList() {
		return rowCodeList;
	}

	public void setRowCodeList(ArrayList rowCodeList) {
		this.rowCodeList = rowCodeList;
	}

	

}
