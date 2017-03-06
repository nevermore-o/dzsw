package com.o.properties;

import java.io.FileNotFoundException;
import java.util.Properties;

import com.o.log.Log;

public class ReadProperties {

	public static String getValue(String fileName, String key){
		Properties props = new Properties();
		try{
			props.load(ReadProperties.class.getClassLoader().getResourceAsStream(fileName));
		} catch(FileNotFoundException e ){
			Log.debug("û���ҵ��ļ�:::"+fileName);
		} catch (Exception e){
			Log.debug("��ȡ�ļ�:::"+fileName+":::ʱ�����쳣");
		}
		finally{
			return props.getProperty(key);
		}
	}
	
}
