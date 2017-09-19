package com.ijson.platform.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * description:  日志辅助类,用于日常日志
 * @author cuiyongxu 创建时间：2016年1月9日
 */
public class LoggerHelper {

    private static Logger logger = LoggerFactory.getLogger(LoggerHelper.class);

    /**
     * 添加info注释
     * @param e
     */
    public static void i(Exception e){
        logger.info("内容处理:[{}]",e.getMessage());
    }

    public static void ie(Exception e){
        logger.info("异常处理:[{}]",e.getMessage());
    }


    public static void e(Exception e){
        logger.error("异常处理:[{}]",e.getMessage());
    }

    public static void d(Exception e){
        logger.debug("debug处理:[{}]",e.getMessage());
    }
}
