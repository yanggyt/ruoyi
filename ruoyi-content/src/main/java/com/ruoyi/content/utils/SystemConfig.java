package com.ruoyi.content.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 加载配置文件并放入servletContext中
 * @author Leeyao
 *
 */
public class SystemConfig extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Map<String, String> systemConfig = new HashMap<String, String>();

	@Override
	public void init() throws ServletException {
		ServletContext sc = getServletContext();
		Properties prop = new Properties();
		try {
			//查找配置文件存放l路径
			String settingFilePath= ToolUtil.getProperties("/resources/config/sysConfig.properties", "path");
			Map<String, String> systemConfigAll = new HashMap<String, String>();
			//加载配置文件
			File f = new File(settingFilePath);
			InputStream is = new FileInputStream(f);
			prop.load(is);
			//获取key值
			Enumeration enu = prop.keys();
			//取出所有值放入map中
			while (enu.hasMoreElements()) {
				String key = (String)enu.nextElement();
				//String value = (String)prop.getProperty(key);
				String value = new String(prop.getProperty(key).getBytes("ISO8859-1"),"UTF-8");
				systemConfig.put(key, value);
			}
			//将配置放入
			sc.setAttribute("systemConfig", systemConfig);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Map<String, String> getConfig() {
		return systemConfig;
	}
}
