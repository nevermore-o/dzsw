/*
 * (C) Copyright 1995-2012 �����Ϣ�ɷ����޹�˾��
 * ����������ʹ�á����ơ��޸ĺͷ����������������ĵ���Ȩ��
 * �����������������Ȩ���͹��ʹ�Լ�ı�����δ����Ȩ���Ը��ƻ�
 * �����������ȫ���򲿷֣������ܵ����������º������Ʋã���
 * �ڷ�������ķ�Χ���ܵ������ܵ����ߡ�
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
 * Title: TestCase��
 * </p>
 * <p>
 * Description: [���滻������������Ҫ���ܽ���]
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
