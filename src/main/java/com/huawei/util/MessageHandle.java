package com.huawei.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class MessageHandle {
	
	private static String req2xml(HttpServletRequest req) throws IOException {
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String str = null;
		StringBuffer sb = new StringBuffer();
		while((str = br.readLine())!=null) {
			sb.append(str);
		}
		return sb.toString();
	}
	@SuppressWarnings("unchecked")
	public static Map<String,String> reqMsg2Map(HttpServletRequest req) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		String str = req2xml(req);
		Document document = DocumentHelper.parseText(str);
		Element root = document.getRootElement();
		List<Element> eles = root.elements();
		for(Element e:eles) {
			map.put(e.getName(), e.getTextTrim());
		}
		
		return map;
	}
	public static String map2xml(Map<String,String> map) throws IOException {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("xml");
		Set<String> keys = map.keySet();
		for(String key:keys) {
			root.addElement(key).addText(map.get(key));
		}
		
		StringWriter sw = new StringWriter();
		document.write(sw);
		
		System.out.println(sw.toString());
		
		return sw.toString();
	}
	
	

	public static Object map2Bean(Map<String, String> map, Class<?> beanClass){    
	    if (map == null)  
	        return null;    
	    Object obj = null;
		try {
			obj = beanClass.newInstance();  
			Field[] fields = obj.getClass().getDeclaredFields();   
			for (Field field : fields) {    
			    int mod = field.getModifiers();    
			    if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
			        continue;    
			    }    
			    field.setAccessible(true);    
			    field.set(obj, map.get(field.getName()));   
			}
		} catch (Exception e) {
			e.printStackTrace();
		}   
	    return obj;    
	}   

	
	
}
