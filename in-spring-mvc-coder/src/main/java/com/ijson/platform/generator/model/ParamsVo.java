package com.ijson.platform.generator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:  vo方法参数模型
 * @author cuiyongxu 创建时间：Oct 27, 2015  
 */
@SuppressWarnings("serial")
public class ParamsVo<E> implements Serializable {

	private String key;
	private String methodKey;
	private String userId;//当前操作用户ID
	private String infoId;//信息ID
	private E obj;//待操作的单对象
	private List<E> objs = new ArrayList<E>();//待操作批量对象
	private Map<String, Object> params = new HashMap<String, Object>();//执行方法所需参数
	private String userEname;

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public void setParams(String key, Object value) {
		params.put(key, value);
	}

	public Object getParams(String key) {
		return params.get(key);
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public E getObj() {
		return obj;
	}

	public void setObj(E obj) {
		this.obj = obj;
	}

	public List<E> getObjs() {
		return objs;
	}

	public void setObjs(List<E> objs) {
		this.objs = objs;
	}

	public void setObjs(E obj) {
		objs.add(obj);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMethodKey() {
		return methodKey;
	}

	public void setMethodKey(String methodKey) {
		this.methodKey = methodKey;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getUserEname() {
		return userEname;
	}

	public void setUserEname(String userEname) {
		this.userEname = userEname;
	}

}
