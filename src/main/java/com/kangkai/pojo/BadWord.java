package com.kangkai.pojo;

import java.io.Serializable;

public class BadWord implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -242721989435321282L;
	private Integer badWordId;
	private String content;
	public Integer getBadWordId() {
		return badWordId;
	}
	public void setBadWordId(Integer badWordId) {
		this.badWordId = badWordId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
