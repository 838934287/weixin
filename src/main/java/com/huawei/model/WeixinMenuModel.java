package com.huawei.model;

import java.util.List;

public class WeixinMenuModel {
	private int id;
	private String name;
	private String type;
	private String url;
	private String key;
	private int pid;
	private List<WeixinMenuModel> sub_button;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public List<WeixinMenuModel> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<WeixinMenuModel> sub_button) {
		this.sub_button = sub_button;
	}

}
