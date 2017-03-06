package com.o.opj.entity;

import static org.junit.Assert.*;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import util.StringUtil;

public class EntityXmlManageTest {

	@Test
	public void testGenerateEntityXmlFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteXmlToFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNameByInputName() {
		String name = new EntityXmlManage().getNameByInputName("test", "lineCode");
		System.out.println(name);
		assertEquals(StringUtils.isNotBlank(name), true);
	}

	@Test
	public void testGetDocumentByTableName() {
		fail("Not yet implemented");
	}

}
