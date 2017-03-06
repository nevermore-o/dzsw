package com.o.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.o.log.Log;

public class FileUtil {
	
	private static String str="12312313";
	
	/**
	 * ���ַ���д��ָ�����ļ�
	 * @param str ��Ҫд����ַ���
	 * @param targetFileName ָ�����ļ�ȫ·��
	 */
	public static void writeToFile(String str, String targetFileName){
		FileWriter out = null;
		BufferedWriter bw = null;
		try {
			 File file= new File(targetFileName);
			 if(!file.exists())
				 file.createNewFile();
			 out = new FileWriter(file);
			 bw = new BufferedWriter(out);
			 if(str.length() != 0 ){
				 out.write(str, 0, str.length());
				 out.flush();
			 }
			if(out != null)
				out.close();
			if(bw != null)
				bw.close();
			Log.debug("�ɹ����ַ���д�뵽ָ���ļ���");
		} catch(FileNotFoundException e){
			Log.debug("Error:::::�Ҳ����ļ���");
		} catch (IOException e) {
			Log.debug("Error:::::�ַ���д�뵽ָ���ļ�ʱ�����쳣��");
		}
	}
	
	/**
	 * ���ַ�����ȡָ�����ļ�
	 * @param sourceFileName ��Ҫ��ȡ���ļ���ȫ·��
	 * @return
	 */
	public static String parseFileToStr(String sourceFileName){
		FileReader in = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try{
			br = new BufferedReader(new FileReader(new File(sourceFileName)));
			String tempStr = "";
			while((tempStr=br.readLine())!=null){
				sb.append(tempStr);
			}
			if(br!=null)
				br.close();
			if(in!=null)
				in.close();
			Log.debug("�ɹ����ļ��������ת��Ϊ�ַ�����");
		}catch(FileNotFoundException e){
			Log.debug("Error:::::�޷��ҵ� �ļ�");
		}catch(Exception e ){
			Log.debug("Error:::::�ļ��������ת��Ϊ�ַ���ʱ�����쳣��");
		}
		return sb.toString();
	}
	
	
	public static void appendStr2File(String str, String filePath){
		File file = new File(filePath);
		BufferedReader br;
		BufferedWriter bw;
		StringBuffer sb = new StringBuffer();
		
		try {
			String tempStr="";
			br = new BufferedReader(new FileReader(file));
			while((tempStr=br.readLine()) != null){
				sb.append(tempStr+"\n\r");
			}
			sb.append(str);
			
			bw = new BufferedWriter(new FileWriter(filePath));
			if(sb.length()>0){
				bw.write(sb.toString(), 0, sb.length());
				bw.flush();
			}
			if(bw!=null)
				bw.close();
			if(br!=null)
				br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		//writeToFile(parseFileToStr("d:/spider/test/�Ծ���.html"),"d:/1/test.html");
		//System.out.println(parseFileToStr("d:/1/test.html"));
		//appendStr2File("sdfsf", "d:/1.txt");
		try {
//			FileUtils.copyFile(new File("d:/1.txt"), new File("d:/2.txt"));
			FileUtils.deleteDirectory(new File("d:/test1"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
