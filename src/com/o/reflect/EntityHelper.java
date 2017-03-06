package com.o.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EntityHelper {
	
	
	/**
	 * 如果实体类的get、set方法都是标准命名的方法名，则可以通过以下反射实现对象的复制
	 * 
	 * @param sourceBo
	 *            源对象
	 * @param targetBo
	 *            目标对象
	 */
	public static void copyObject(Object sourceBo, Object targetBo) {
		if (!sourceBo.getClass().getName()
				.equals(targetBo.getClass().getName())) {
			System.out.println("对象类型不匹配，不能进行复制！");
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
					 * "调用了自身的" + m.getName() + "方法,参数是通过反射调用" +
					 * sourceBo.getClass().getName() + "的" +
					 * getterMethod.getName() + "方法获得的,set方法获得的值：" +
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
