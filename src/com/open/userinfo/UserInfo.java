/*
 * (C) Copyright 1995-2012 �����Ϣ�ɷ����޹�˾��
 * ����������ʹ�á����ơ��޸ĺͷ����������������ĵ���Ȩ��
 * �����������������Ȩ���͹��ʹ�Լ�ı�����δ����Ȩ���Ը��ƻ�
 * �����������ȫ���򲿷֣������ܵ����������º������Ʋã���
 * �ڷ�������ķ�Χ���ܵ������ܵ����ߡ�
 */
package com.open.userinfo;

import org.apache.log4j.Logger;

/**
 * <p>Title: UserInfo��</p>
 * <p>Description: [���滻������������Ҫ���ܽ���]</p>
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

