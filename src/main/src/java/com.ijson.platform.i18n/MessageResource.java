package com.ijson.platform.i18n;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ijson.platform.i18n.entity.Resource;
import com.ijson.platform.i18n.util.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by cuiyongxu on 17/10/26.
 */

public class MessageResource extends AbstractMessageSource implements ResourceLoaderAware {

    @SuppressWarnings("unused")
    private ResourceLoader resourceLoader;
    private final Map<String, String> properties = Maps.newHashMap();


    @PostConstruct
    public void reload() {
        properties.clear();
        properties.putAll(loadTexts());
    }

    /**
     * 描述：TODO
     * 查询数据 虚拟数据，可以从数据库读取信息，此处省略
     */
    private List<Resource> getResource() {
        List<Resource> resources = Lists.newArrayList();
        try {
            String str = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("i18n/i18n.json"), Charset.forName("UTF-8")).replaceAll(" ", "");
            List<Map<String, Object>> rst = JsonUtil.fromJson(str, List.class);
            rst.forEach(e -> {
                resources.add(mapToBean(e, Resource.class));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resources;
    }

    private static <T> T mapToBean(Map<String, Object> map, Class<T> obj) {
        try {
            if (map == null) {
                return null;
            }
            Set<Map.Entry<String, Object>> sets = map.entrySet();
            T t = obj.newInstance();
            Method[] methods = obj.getDeclaredMethods();
            for (Map.Entry<String, Object> entry : sets) {
                String str = entry.getKey();
                String setMethod = "set" + str.substring(0, 1).toUpperCase() + str.substring(1);
                for (Method method : methods) {
                    if (method.getName().equals(setMethod)) {
                        method.invoke(t, entry.getValue());
                    }
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> loadTexts() {
        Map<String, String> mapResource = new HashMap<>();
        List<Resource> resources = getResource();
        for (Resource item : resources) {
            mapResource.put(item.getEname() + "-" + item.getLanguage(), item.getCname());
        }
        return mapResource;
    }


    private String getText(String code, Locale locale) {
        String localeCode = locale.getLanguage();
        String key = code + "-"+localeCode;
        String localeText = properties.get(key);
        String resourceText = code;

        if (localeText != null) {
            resourceText = localeText;
        } else {
            localeCode = Locale.ENGLISH.getLanguage();
            key = code + localeCode;
            localeText = properties.get(key);
            if (localeText != null) {
                resourceText = localeText;
            } else {
                try {
                    if (getParentMessageSource() != null) {
                        resourceText = getParentMessageSource().getMessage(code, null, locale);
                    }
                } catch (Exception e) {
                    logger.error("Cannot find message with code: " + code);
                }
            }
        }
        return resourceText;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = (resourceLoader != null ? resourceLoader : new DefaultResourceLoader());
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String msg = getText(code, locale);
        return createMessageFormat(msg, locale);
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        return getText(code, locale);
    }
}
