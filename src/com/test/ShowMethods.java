/*
 * (C) Copyright 1995-2012 万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.test;

import org.apache.log4j.Logger;

/**
 * <p>
 * Title: ShowMethods类
 * </p>
 * <p>
 * Description: [请替换这里，描述该类概要功能介绍]
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
