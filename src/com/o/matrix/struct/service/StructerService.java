package com.o.matrix.struct.service;

import com.o.matrix.struct.Structer;

public interface StructerService {
	
	
	/**
	 * ����sql��乹�����С��б�ͷ���������ݵĽṹ��Structer
	 * @param sql
	 * @param rowCode
	 * @param colCode
	 * @return
	 */
	public Structer conStruct(String sql, String[] rowCode, String[] colCode);
	
}
