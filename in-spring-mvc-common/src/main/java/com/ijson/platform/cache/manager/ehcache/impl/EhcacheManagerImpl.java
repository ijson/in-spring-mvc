package com.ijson.platform.cache.manager.ehcache.impl;

import com.ijson.platform.cache.CacheManager;
import com.ijson.platform.cache.manager.ehcache.EhcacheConfigurer;
import com.ijson.platform.common.util.Validator;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import java.util.ArrayList;
import java.util.List;


/**
 * description:  ehcache缓存实现类
 *
 * @author heppy1.com 创建时间：Jan 24, 2015
 */
public class EhcacheManagerImpl implements CacheManager {

    //读取ehcache.xml映射文件
    private Cache cache;

    private EhcacheConfigurer cacheConfig;//做spring注入时可以注入
    private String cacheName = "ijsonCache";//做spring注入时需要注入

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
        cache = getCacheConfig().getCache(this.cacheName);
    }

    private Cache getCache() {
        if (null == cache) {
            cache = getCacheConfig().getCache(this.cacheName);
        }
        return cache;
    }

    public boolean createCacheObject(String key, Object object) {
        boolean flag = false;
        if (Validator.isNotNull(key) && object != null) {
            getCache().remove(key);
            Element element = new Element(key, object);
            getCache().put(element);
            flag = true;
        }
        return flag;
    }

    public boolean checkCacheObject(String key) {
        boolean flag = false;
        if (Validator.isNotNull(key)) {
            Element element = getCache().get(key);
            if (element != null) {
                flag = true;
            }
        }
        return flag;
    }

    public Object getCacheObjectByKey(String key) {
        if (Validator.isNotNull(key)) {
            Element element = getCache().get(key);
            if (element == null) {
                return null;
            } else {
                Object cacheOject = element.getObjectValue();
                return cacheOject;
            }
        } else
            return null;
    }

    public Object getCacheCloneByKey(String key) {
        if (Validator.isNotNull(key)) {
            Element element = getCache().get(key);
            if (element == null) {
                return null;
            } else {
                return Validator.clone(element.getObjectValue());
            }
        } else
            return null;
    }

    public List<Object> getObjects(List<String> keys) {
        if (Validator.isEmpty(keys))
            return null;
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object object = getCacheCloneByKey(key);
            if (!Validator.isEmpty(object)) {
                list.add(object);
            }
        }
        return list;
    }

    public List<Object> getObjects(List<String> keys, String prefix) {
        if (Validator.isEmpty(keys))
            return null;
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < keys.size(); i++) {
            String key = prefix + keys.get(i);
            Object object = getCacheCloneByKey(key);
            if (!Validator.isEmpty(object)) {
                list.add(object);
            }
        }
        return list;
    }

    public List<String> getObjects(List<String> keys, String prefix, List<Object> objs) {
        if (Validator.isEmpty(keys))
            return null;
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < keys.size(); i++) {
            String key = prefix + keys.get(i);
            Object object = getCacheCloneByKey(key);
            if (!Validator.isEmpty(object)) {
                objs.add(object);
            } else {
                list.add(keys.get(i));
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<String> getAllKeys() {
        return getCache().getKeys();
    }

    public boolean removeCacheObject(String key) {
        boolean flag = false;
        if (Validator.isNotNull(key)) {
            getCache().remove(key);
            flag = true;
        }
        return flag;
    }

    public boolean updateCacheObject(String key, Object value) {
        return createCacheObject(key, value);
    }

    public void shutdownCache() {
        cacheConfig.shutdownCache();
    }

    public void removeAll() {
        getCache().removeAll();
    }

    private EhcacheConfigurer getCacheConfig() {
        if (null == cacheConfig) {
            cacheConfig = new EhcacheConfigurer();
        }
        return cacheConfig;
    }

    public void setCacheConfig(EhcacheConfigurer cacheConfig) {
        this.cacheConfig = cacheConfig;
    }

}
