package com.o.matrix.service.impl;

import java.util.ArrayList;

import com.o.log.Log;
import com.o.matrix.service.Matrix;
import com.o.matrix.service.MatrixService;
import com.o.matrix.struct.DataCell;
import com.o.matrix.struct.DefaultStructer;
import com.o.matrix.struct.Structer;

public class MatrixServiceImpl implements MatrixService{

	public Matrix createMatrix(String sql, String rowCode, String colCode) {
		Structer structer =new DefaultStructer();//Query.doQuery(sql);
		ArrayList resultList = structer.getResultList();
		DataCell[] lattice = new DataCell[]{};
		int len=0;
		for(int i=0,i1=resultList.size();i<i1;i++){
			for(int j=0,j1=((ArrayList)resultList.get(0)).size();j<j1;j++){
				DataCell lc = new DataCell();
				try{
					Object object = ((ArrayList)resultList.get(i)).get(j);
					if(object ==null || "".equals(object)){
						throw new Exception("该sql语句无法构造正确的matrix矩阵");
					}
					lc.setCellValue(object.toString());
					Log.debug(lc.getCellValue());
					//lattice[len] = lc;
					len++;
				} catch(Exception e){
					Log.debug(e.getMessage());
				}
			}
		}
		Log.debug(lattice.length);
		return null;
	}
	
	
	
	

}
