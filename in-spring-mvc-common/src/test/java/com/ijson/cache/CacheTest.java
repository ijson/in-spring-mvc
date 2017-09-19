package com.ijson.cache;

import com.ijson.cache.manager.ehcache.impl.EhcacheManagerImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by cuiyongxu on 17/9/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CacheTest {

    private CacheManager cache = new EhcacheManagerImpl();


    @Test
    public void saveOrGet() {
        cache.createCacheObject("555", "6666");
        String aa = (String) cache.getCacheCloneByKey("555");
        System.out.println(aa);
    }
}
