/*
 * (C) Copyright 1995-2012 万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.open.userinfo;

import org.apache.log4j.Logger;

/**
 * <p>Title: UserInfo类</p>
 * <p>Description: [请替换这里，描述该类概要功能介绍]</p>
 * @author oupeng on 2012-5-15
 * @version 1.0
 */
public class UserInfo {

	public static final Logger logger = Logger.getLogger(UserInfo.class);
	
	private String id;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}

