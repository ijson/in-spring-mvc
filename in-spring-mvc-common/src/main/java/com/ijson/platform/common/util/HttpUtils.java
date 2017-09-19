package com.ijson.platform.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * description:  
 * @author cuiyongxu 创建时间：Oct 27, 2015  
 */
public class HttpUtils {
	/**
	 * description:   获取访问IP
	 * @param request
	 * @return  
	 * @author cuiyongxu 
	 * @update Oct 27, 2015
	 */
	protected String getUserIp(HttpServletRequest request) {
		String ip = "";
		try {
			ip = request.getHeader("x-forwarded-for");
			if (Validator.isNull(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (Validator.isNull(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (Validator.isNull(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			if (Validator.isNotNull(ip) && ip.length() > 32) {
				ip = ip.substring(0, 32);
			}
		} catch (Exception e) {
            LoggerHelper.ie(e);
		}
		return ip;
	}
}
