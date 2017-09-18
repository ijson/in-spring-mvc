package com.ijson.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * description:  property文件读取类
 * @author cuiyongxu 创建时间：Oct 8, 2015  
 */
public class ProperUtil {

	/**
	 * description:  
	 * 获取Poperties文件
	 * @param filePath 资源文件路径
	 * @return 返回Poperties中文件属性   
	 * @author cuiyongxu
	 * @update Jul 3, 2015
	 */
	public static Properties getProperties(String filePath) {
		Properties prop = null;
		FileInputStream fileInput = null;
		try {
			File file = new File(filePath);
			if (file.exists()) {// 判断文件夹是否存在
				prop = new Properties();
				fileInput = new FileInputStream(filePath);
				prop.load(fileInput);
			}
		} catch (IOException e) {
			System.out.println("文件读取失败 key=" + e.getMessage());
		} finally {
			try {
				fileInput.close();
			} catch (Exception e) {
                LoggerHelper.ie(e);
			}
		}
		return prop;
	}

	/**
	 * 获取Poperties中文件属性
	 * 
	 * @param filePath 资源文件目录
	 * @param filename 资源文件名称
	 * @return         返回Poperties中文件属性   
	 * @author cuiyongxu
	 * @throws java.io.UnsupportedEncodingException
	 * @update Jul 3, 2015
	 */
	public static Properties getProperties(String fileDir, String fileName) {
		if ("".equals(fileDir)) {
			try {
				String osEname = Validator.getSystemType();
				int index = 1;
				if ("windows".equals(osEname)) {
					index = 1;
				} else {
					index = 0;
				}
				if ("Mac OS X".equals(osEname)) {
					fileDir = Thread.currentThread().getContextClassLoader().getResource("/").getPath()
							.substring(index);
					fileDir = "/" + fileDir;
				} else {
					fileDir = Thread.currentThread().getContextClassLoader().getResource("/").getPath()
							.substring(index);
				}
				fileDir = URLDecoder.decode(fileDir, "UTF-8");
			} catch (UnsupportedEncodingException e) {
                LoggerHelper.ie(e);
			}
		}
		return getProperties(fileDir + fileName);
	}

	public static Properties getConfigByPath(String name) {
		if ("".equals(name.trim()) || null == name) {
			return getProperties(Thread.currentThread().getContextClassLoader().getResource("config.properties")
					.getFile());
		} else {
			try {
				Properties prop = new Properties();
				prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(name));
				return prop;
			} catch (IOException e) {
                LoggerHelper.ie(e);
				return null;
			}
		}
	}
}
