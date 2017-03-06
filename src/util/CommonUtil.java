package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class CommonUtil {

	
	/**
	 * 以二进制方式克隆对象
	 * @param srcObj
	 * @param destObj
	 */
	public static void copyObj(Object srcObj, Object destObj){
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(srcObj);
			oos.close();
			
			ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bis);
			destObj = ois.readObject();
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args){
		Map srcObj = new HashMap();
		srcObj.put("a", "test");
		Map destObj = new HashMap();
		copyObj(srcObj, destObj);
		System.out.println(destObj.get("a"));
		
	}
}
