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
			Log.debug("没有找到文件:::"+fileName);
		} catch (Exception e){
			Log.debug("读取文件:::"+fileName+":::时遇到异常");
		}
		finally{
			return props.getProperty(key);
		}
	}
	
}
