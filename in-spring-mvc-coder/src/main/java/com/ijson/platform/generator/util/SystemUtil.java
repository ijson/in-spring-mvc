package com.ijson.platform.generator.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

public class SystemUtil {

	private static SystemUtil instance;

	private static long lastUpateTime = 0l;//最后修改时间

	private Map<String, String> constant = new HashMap<String, String>();//存放系统配置参数

	private static String filePath = "";

	private SystemUtil() {
		filePath = Validator.getClassLoaderPath();
	}

	/**
	  * description: 启用单例模式
	  * @return
	 */
	public synchronized static SystemUtil getInstance() {
		if (null == instance) {
			instance = new SystemUtil();
		}
		return instance;
	}

	/**
	  * description: 加载配置文件
	 */
	@SuppressWarnings( { "rawtypes", "unchecked" })
	private void initConfig() {
		File oldfile = new File(filePath + "config.properties");
		long lastModletime = oldfile.lastModified();
		if (lastUpateTime < lastModletime) {//有更新从新加载
			lastUpateTime = lastModletime;
			//Properties prop = ProperUtil.getProperties("", "config.properties");
			Properties prop = ProperUtil.getProperties(filePath, "config.properties");
			Map<String, String> map = new HashMap<String, String>((Map) prop);
			Set propertySet = map.entrySet();
			for (Object o : propertySet) {
				Map.Entry entry = (Map.Entry) o;
				String key = entry.getKey().toString();
				constant.put(key, entry.getValue().toString());
			}
		}
	}

	/**
	  * description: 获取所有配置信息
	  * @return
	 */
	public Map<String, String> getConstant() {
		initConfig();
		return constant;
	}

	/**
	  * description: 根据指定的KEY获取相应配置信息
	  * @param key   指定的KEY
	  * @return
	 */
	public String getConstant(String key) {
		initConfig();
		return constant.get(key);
	}

	/**
	*获取UUID主键生成器的方法
	*生成规则：32位字符串由本机的IP+时间无符号右移8位（当前中时间）+当前短时间+当前长时间+计数器 组成
	*
	*@return 返回UUID32位字符串
	*/
	public synchronized static String generate() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
