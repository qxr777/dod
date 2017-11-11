package edu.whut.cs.oo.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8023098825815057094L;

	private String controller;
	
	private int code;
	
	private String msg;
	
	private Map<String, Object> params = new HashMap<String, Object>();
	
	private Object data;

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public Object getParameter(String key) {
		return params.get(key);
	}
	
	public void setParameter(String name, Object value) {
		params.put(name, value);
	}
}
