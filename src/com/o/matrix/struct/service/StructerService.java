package com.o.matrix.struct.service;

import com.o.matrix.struct.Structer;

public interface StructerService {
	
	
	/**
	 * 根据sql语句构造有行、列表头，数据数据的结构类Structer
	 * @param sql
	 * @param rowCode
	 * @param colCode
	 * @return
	 */
	public Structer conStruct(String sql, String[] rowCode, String[] colCode);
	
}
