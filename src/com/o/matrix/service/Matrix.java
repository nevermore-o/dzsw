package com.o.matrix.service;

import com.o.matrix.struct.ColCell;
import com.o.matrix.struct.DataCell;
import com.o.matrix.struct.RowCell;

public class Matrix {
	
	//单元格数据区
	private DataCell[] lattice;
	
	//行表头区
	private RowCell[] rowCell;
	
	//列表头区
	private ColCell[] colCell;
}
