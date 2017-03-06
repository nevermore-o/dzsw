package com.o.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EntityHelper {
	
	
	/**
	 * ���ʵ�����get��set�������Ǳ�׼�����ķ������������ͨ�����·���ʵ�ֶ���ĸ���
	 * 
	 * @param sourceBo
	 *            Դ����
	 * @param targetBo
	 *            Ŀ�����
	 */
	public static void copyObject(Object sourceBo, Object targetBo) {
		if (!sourceBo.getClass().getName()
				.equals(targetBo.getClass().getName())) {
			System.out.println("�������Ͳ�ƥ�䣬���ܽ��и��ƣ�");
			return;
		}
		Class entityClass = sourceBo.getClass();
		Method[] methods = entityClass.getMethods();
		for (Method m : methods) {
			if (m.getName().startsWith("set")) {
				Method getterMethod;
				try {
					getterMethod = entityClass.getMethod(m.getName()
							.replaceAll("set", "get"));
					/***********************************************************
					 * System.out.println(targetBo.getClass().getName() +
					 * "�����������" + m.getName() + "����,������ͨ���������" +
					 * sourceBo.getClass().getName() + "��" +
					 * getterMethod.getName() + "������õ�,set������õ�ֵ��" +
					 * getterMethod.invoke(sourceBo, null));
					 **********************************************************/
					m.invoke(targetBo, getterMethod.invoke(sourceBo, null));
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
