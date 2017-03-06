/*
 * (C) Copyright 1995-2012 万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.test;


import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.open.service.IService;
import com.open.userinfo.UserInfo;

/**
 * <p>
 * Title: TestCase类
 * </p>
 * <p>
 * Description: [请替换这里，描述该类概要功能介绍]
 * </p>
 * 
 * @author oupeng on 2012-5-15
 * @version 1.0
 */
public class TestCase {

	public static final Logger logger = Logger.getLogger(TestCase.class);

	@Test
	public static void test1() throws Exception {
		Configuration cf = new Configuration().addClass(UserInfo.class);
		SessionFactory sessions = cf.buildSessionFactory();
		Session session = sessions.openSession();
	}

	@Test
	public void test2() {
	}
	
	public static void main(String[] args){
		System.out.println("11111");
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		System.out.println(wac.getBeanDefinitionCount());
	}
	
	
	
	public static void testWac(){
		System.out.println("11111");
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		IService s = (IService)wac.getBean("service");
		UserInfo user = new UserInfo();
		user.setId(UUID.randomUUID().toString());
		s.save(user);
	}
}
