package com.ijson.cache.manager.ehcache;

import com.ijson.cache.manager.ehcache.impl.LoadCacheResource;
import com.ijson.common.util.LoggerHelper;
import com.ijson.common.util.Validator;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import java.net.URL;

/**
 * description:  ehcache资源配置类
 *
 * @author heppy1.com 创建时间：Jan 24, 2015
 */
public class EhcacheConfigurer {

    private CacheResource resource;
    private static CacheManager manager = null;

    //private Map<String, Cache> param = new HashMap<String, Cache>();

    public void setLocations(CacheResource resource) {
        this.resource = resource;
    }

    private void init() {
        try {
            if (null == manager) {
                if (null == resource) {
                    resource = new LoadCacheResource("ehcache.xml");
                }
                URL url = resource.getURL();
                manager = new CacheManager(url);
            }
        } catch (Exception e) {
            LoggerHelper.ie(e);
        }
    }

    public String[] getNames() {
        init();
        return manager.getCacheNames();
    }

    /**
     * description: 获得缓存
     *
     * @param storage 存储库名称
     * @return 返回一个cache对象
     * @author cuiyongxu
     * @update Jul 3, 2015
     */
    public Cache getCache(String storage) {
        init();
        if (null == storage || "".equals(storage))
            storage = "ijsonCache";
        if ("defaultCache".equalsIgnoreCase(storage)) {
            if (Validator.isEmpty(manager.getCache(storage))) {
                manager.addCache("defaultCache");
            }
        }
        return manager.getCache(storage);
    }

    /**
     * description:    关闭ehcache缓存，每次调用完cache，需要关闭缓存。
     *
     * @author cuiyongxu
     * @update Jul 3, 2015
     */
    public void shutdownCache() {
        manager.shutdown();
    }
}
