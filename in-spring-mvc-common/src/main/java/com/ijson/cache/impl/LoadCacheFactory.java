package com.ijson.cache.impl;

import com.ijson.cache.CacheManager;
import com.ijson.cache.manager.ehcache.impl.EhcacheManagerImpl;
import com.ijson.common.util.Validator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


/**
 * description:  创建缓存实例工厂
 *
 * @author heppy1.com 创建时间：Jan 24, 2015
 */
public class LoadCacheFactory {

    private static LoadCacheFactory instance;
    private static Map<String, CacheManager> ehcaches = new HashMap<String, CacheManager>();
    private static String filePath = "";
    private static long lastUpateTime = 0l;//最后修改时间
    private Map<String, String> constant = new HashMap<String, String>();//存放系统配置参数

    private LoadCacheFactory() {
        filePath = Validator.getClassLoaderPath();
    }

    public static LoadCacheFactory getInstance() {
        if (null == instance)
            instance = new LoadCacheFactory();
        return instance;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private String initConfig(String key) {
        File oldfile = new File(filePath + "config/cache-conf.properties");
        long lastModletime = oldfile.lastModified();
        if (lastUpateTime < lastModletime) {//有更新从新加载
            lastUpateTime = lastModletime;
            Properties prop = Validator.getProperties(filePath, "config/cache-conf.properties");
            Map<String, String> map = new HashMap<String, String>((Map) prop);
            Set propertySet = map.entrySet();
            for (Object o : propertySet) {
                Map.Entry entry = (Map.Entry) o;
                String key1 = entry.getKey().toString();
                constant.put(key1, entry.getValue().toString());
            }
        }
        return constant.get(key);
    }

    /**
     * description:  获取缓存实例
     *
     * @param cacheName 缓存空间名称
     * @return
     * @author cuiyongxu
     * @update Jul 3, 2015
     */
    public CacheManager getCacheManager(String cacheName) {
        return getEhcacheManager(cacheName);
    }

    public String getProperty(String key) {
        return initConfig(key);
    }

    /**
     * description: 获取ehcache缓存实例
     *
     * @param cacheName 缓存空间名称
     * @return
     * @author cuiyongxu
     * @update Jul 3, 2015
     */
    private CacheManager getEhcacheManager(String cacheName) {
        if (Validator.isNull(cacheName))
            cacheName = Validator.getDefaultStr(initConfig("cache_default_name"), "ijsonCache");
        if (null == ehcaches.get(cacheName)) {
            CacheManager cacheManager = new EhcacheManagerImpl();
            cacheManager.setCacheName(cacheName);
            ehcaches.put(cacheName, cacheManager);
        }
        return ehcaches.get(cacheName);
    }

}
