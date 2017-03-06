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
	 * ����sql��乹�����С��б�ͷ���������ݵĽṹ��Structer
	 * @param sql
	 * @param rowCode
	 * @param colCode
	 * @return
	 */
	public Structer conStruct(String sql, String[] rowCode, String[] colCode) {
		Log.debug("��ʼ����Structer����������������");
		Structer stuc = new DefaultStructer();
		HashMap tempMap = new HashMap();
		tempMap = Query.doSelect(sql, rowCode, colCode);
		ArrayList resultList = new ArrayList();
		ArrayList rowCodeList = new ArrayList();
		RowCell[] rowCell;//�б�ͷ
		ColCell[] colCell;//�б�ͷ
		int[] eachRowLen;//��¼ÿ��rowCode��ͷ�ĳ���
		int[] eachColLen;//��¼ÿ��colCode��ͷ�ĳ���
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
			//��¼ÿ��rowCode��ͷ�ĳ���
			for(int x=0;x<rowCell.length;x++){
				rowSize*=rowCell[x].getRowCodeList().size();
				eachRowLen[x]=rowCell[x].getRowCodeList().size();
			}
			//��¼ÿ��colCode��ͷ�ĳ���
			for(int x=0;x<colCell.length;x++){
				colSize*=colCell[x].getColCodeList().size();
				eachColLen[x]=colCell[x].getColCodeList().size();
			}
			column=(String[])tempMap.get("column");
			type=(String[])tempMap.get("type");
		}else{
			Log.debug("�ṹ��Structer����ʧ�ܣ���������������");
			return null;
		}
		DataCell[][] dcResult=new DataCell[rowSize][colSize];
		/**
		 *  ѭ��Sql�õ���ԭʼ���ݵ�ÿһ��
		 */
		for (int i = 0; i < resultList.size() ; i++) {
			HashMap map = new HashMap();
			map = (HashMap) resultList.get(i);
			Object[] mapRowCode = new Object[rowCell.length];
			Object[] mapColCode =new Object[colCell.length];
			int[] rowIndex=new int[rowCell.length];
			int[] colIndex=new int[colCell.length];
			//�������ݵ�ÿһ�����������б�ͷ�������
			for(int j=0;j<rowCode.length;j++){
				mapRowCode[j]=(Object)map.get(rowCode[j]);//ÿһ���б�ͷ��Ӧ��ֵ
				rowIndex[j]=rowCell[j].getRowCodeList().indexOf(mapRowCode[j]);//ûһ���б�ͷֵ���б�ͷ�����������
			}
			
			//�������ݵ�ÿһ�����������б�ͷ�������
			for(int j=0;j<colCode.length;j++){
				mapColCode[j]=(Object)map.get(colCode[j]);//ÿһ���б�ͷ��Ӧ��ֵ
				colIndex[j]=colCell[j].getColCodeList().indexOf(mapColCode[j]);//ûһ���б�ͷֵ���б�ͷ�����������
			}
			int m=0;
			int n=0;
			/**
			 * m,Ϊ�����������з��������
			 */
			for(int p=0;p<rowCell.length;p++){
				if(p==0)
					m=rowIndex[p];
				else{
					m=m*eachRowLen[p]+rowIndex[p];//ÿ��ѭ���൱��һ�εݹ飬��ǰѭ���б�ͷ�ĳ���*��ǰ�б�ͷֵ���б�ͷ�����������
				}
			}
			/**
			 * n,Ϊ���������з��������
			 */
			for(int p=0;p<colCell.length;p++){
				if(p==0)
					n=colIndex[p];
				else{
					n=n*eachColLen[p]+colIndex[p];//ÿ��ѭ���൱��һ�εݹ飬��ǰѭ���б�ͷ�ĳ���*��ǰ�б�ͷֵ���б�ͷ�����������
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
