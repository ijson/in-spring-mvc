package com.ijson.platform.generator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProperUtil {

	/**
	 * 获取Poperties文件
	 * 
	 * @param filePath 资源文件路径
	 * @return         返回Poperties中文件属性   
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
				System.out.println("文件流关闭失败 key=" + e.getMessage());
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
	 */
	public static Properties getProperties(String fileDir, String fileName) {
		if ("".equals(fileDir)) {
			fileDir = Thread.currentThread().getContextClassLoader().getResource("/").getPath().substring(1);
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
				e.printStackTrace();
				return null;
			}
		}
	}
}
