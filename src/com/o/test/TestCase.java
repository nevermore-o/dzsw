package com.o.test;

import org.junit.Test;

import com.o.file.FileUtil;
import com.o.matrix.struct.Structer;
import com.o.matrix.struct.service.StructerService;
import com.o.matrix.struct.service.impl.StructerServiceImpl;

public class TestCase {

	@Test
	public void test() {
		StructerService ss = new StructerServiceImpl();
		Structer structer = ss
				.conStruct(
						"select t.unit_name, t.case_type_name,t.IS_END,t.REMOVE,count(*) count from "
								+ "ajgl_case t group by t.unit_name, t.case_type_name,t.IS_END,T.REMOVE",
						new String[] { "UNIT_NAME", "IS_END" },
						new String[] { "CASE_TYPE_NAME" , "REMOVE"});
		String htmlStr=structer.toHtmlTable();
		FileUtil.writeToFile(htmlStr, "d:/1/test1.html");

	}

}
