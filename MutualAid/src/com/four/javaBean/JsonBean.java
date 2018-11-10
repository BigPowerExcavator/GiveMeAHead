package com.four.javaBean;

import java.util.Map;

public class JsonBean {
	private String status;
	private Map<String,?> data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Map<String, ?> getData() {
		return data;
	}
	public void setData(Map<String, ?> data) {
		this.data = data;
	}
}
