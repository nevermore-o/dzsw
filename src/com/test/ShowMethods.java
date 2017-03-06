/*
 * (C) Copyright 1995-2012 �����Ϣ�ɷ����޹�˾��
 * ����������ʹ�á����ơ��޸ĺͷ����������������ĵ���Ȩ��
 * �����������������Ȩ���͹��ʹ�Լ�ı�����δ����Ȩ���Ը��ƻ�
 * �����������ȫ���򲿷֣������ܵ����������º������Ʋã���
 * �ڷ�������ķ�Χ���ܵ������ܵ����ߡ�
 */
package com.test;

import org.apache.log4j.Logger;

/**
 * <p>
 * Title: ShowMethods��
 * </p>
 * <p>
 * Description: [���滻������������Ҫ���ܽ���]
 * </p>
 * 
 * @author oupeng on 2012-5-17
 * @version 1.0
 */
// : ShowMethods.java
// Using Java 1.1 reflection to show all the
// methods of a class, even if the methods are
// defined in the base class.
import java.lang.reflect.*;

public class ShowMethods {
	static final String usage = "usage: \n"
			+ "ShowMethods qualified.class.name\n"
			+ "To show all methods in class or: \n"
			+ "ShowMethods qualified.class.name word\n"
			+ "To search for methods involving 'word'";

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println(usage);
			//System.exit(0);
		}
		//args[0] = "getName";
		try {
			Class c = Class.forName(args[0]);
			Method[] m = c.getMethods();
			Constructor[] ctor = c.getConstructors();
			if (args.length == 1) {
				for (int i = 0; i < m.length; i++)
					System.out.println(m[i].toString());
				for (int i = 0; i < ctor.length; i++)
					System.out.println(ctor[i].toString());
			} else {
				for (int i = 0; i < m.length; i++)
					if (m[i].toString().indexOf(args[1]) != -1)
						System.out.println(m[i].toString());
				for (int i = 0; i < ctor.length; i++)
					if (ctor[i].toString().indexOf(args[1]) != -1)
						System.out.println(ctor[i].toString());
			}
		} catch (ClassNotFoundException e) {
			System.out.println("No such class: " + e);
		}
	}
} 
