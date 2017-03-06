package com.o.opj.entity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.o.log.Log;
import com.o.opj.DbManager;

public class EntityXmlManage {
	
	/*
	 * 生成表对应实体的xml
	 */
	public void generateEntityXmlFile(String tableName,String filePath){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		ArrayList columnList=DbManager.getColumnsByTableName(tableName);
		Document document=DocumentHelper.createDocument();
		Element rootElement =document.addElement("entity")
			.addAttribute("table", tableName.toUpperCase())
			.addAttribute("createdTime", sdf.format(new Date()));
		int len=columnList.size();
		for(int i=0;i<len;i++){
			String column=columnList.get(i).toString();
			String field=this.parseColumnToField(column);
			rootElement.addElement("text").addAttribute("name", column)
				.addAttribute("inputName", field);
		}
		this.writeXmlToFile(document, filePath);
	}
	
	/**
	 * 数据字段命名
	 * @param column
	 * @return
	 */
	private String parseColumnToField(String column){
		String[] temp=column.split("_");
		String field="";
		for(String o:temp)
			field+=String.valueOf(o.charAt(0)).toUpperCase()
				 +o.substring(1, o.length()).toLowerCase();
		field=String.valueOf(field.charAt(0)).toLowerCase()
			+field.substring(1, field.length());
		return field;
	}
	
	public static void main(String[] args) throws IOException{
		new EntityXmlManage().generateEntityXmlFile("ajgl_case", "d:\\test.xml");
		Log.debug("success");
	}
	
	public void writeXmlToFile(Document document, String filePath){
		XMLWriter writer=null;
		try{
			//格式化输出
			OutputFormat format=OutputFormat.createPrettyPrint();
			format.setEncoding("GBK");
			writer=new XMLWriter(new FileWriter(filePath), format);
			writer.write(document);
		}catch(Exception e){
			Log.debug("将实体的document对象输出到文件时出现了异常!");
		}finally{
			try {
				writer.flush();
				if(writer!=null)
					writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//根据表名、inputName名得到对应的数据库字段名
	public String getNameByInputName(String tableName, String inputName){
		String name="";
		tableName = tableName.toUpperCase();
		Document document = this.getDocumentByTableName(tableName);
		Element element = (Element)document.selectSingleNode("//text[@inputName='"+inputName+"']");
		return element.attributeValue("name");
	}
	
	//根据表名得到对应的document对象
	public Document getDocumentByTableName(String tableName){
		SAXReader reader  = new SAXReader();
		Document document;
		try {
			document = reader.read(new File("d:\\test.xml"));
			if(document != null)
				return document;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
