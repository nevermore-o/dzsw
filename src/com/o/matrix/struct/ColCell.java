package com.o.matrix.struct;

import java.util.ArrayList;

public class ColCell {

	private String cellValue;
	
	private ArrayList colCodeList=new ArrayList();

	public String getCellValue() {
		return cellValue;
	}

	public void setCellValue(String cellValue) {
		this.cellValue = cellValue;
	}

	public ArrayList getColCodeList() {
		return colCodeList;
	}

	public void setColCodeList(ArrayList colCodeList) {
		this.colCodeList = colCodeList;
	}
	

}
