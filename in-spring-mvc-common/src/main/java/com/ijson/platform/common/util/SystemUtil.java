package com.ijson.platform.common.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * description:  系统工具类
 *
 * @author cuiyongxu 创建时间：Oct 8, 2015
 */
public class SystemUtil {

    private static SystemUtil instance;

    private static long lastUpateTime = 0l;//最后修改时间

    private Map<String, String> constant = new HashMap<String, String>();//存放系统配置参数
    private static String fileNames = "config/config.properties";
    private static String filePath = "";

    private SystemUtil() {
        filePath = Validator.getClassLoaderPath();
    }

    /**
     * description:  启用单例模式
     *
     * @return
     * @author cuiyongxu
     * @update Jul 3, 2015
     */
    public synchronized static SystemUtil getInstance() {
        if (null == instance) {
            instance = new SystemUtil();
        }
        return instance;
    }

    /**
     * description:  加载配置文件
     *
     * @author cuiyongxu
     * @update Jul 3, 2015
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void initConfig() {
        try {
            filePath = URLDecoder.decode(filePath, "UTF-8");
            File oldfile = new File(filePath + fileNames);
            long lastModletime = oldfile.lastModified();
            if (lastUpateTime < lastModletime) {//有更新从新加载
                lastUpateTime = lastModletime;
                Properties prop = ProperUtil.getProperties("", fileNames);
                Map<String, String> map = new HashMap<String, String>((Map) prop);
                Set propertySet = map.entrySet();
                for (Object o : propertySet) {
                    Map.Entry entry = (Map.Entry) o;
                    String key = entry.getKey().toString();
                    constant.put(key, entry.getValue().toString());
                }
            }
        } catch (UnsupportedEncodingException e) {
            LoggerHelper.ie(e);
        }
    }

    /**
     * description:  获取所有配置信息
     *
     * @return
     * @author cuiyongxu
     * @update Jul 3, 2015
     */
    public Map<String, String> getConstant() {
        initConfig();
        return constant;
    }

    /**
     * description:  根据指定的KEY获取相应配置信息
     *
     * @param key 指定的KEY
     * @return
     * @author cuiyongxu
     * @update Jul 3, 2015
     */
    public String getConstant(String key) {
        initConfig();
        return constant.get(key);
    }
}
