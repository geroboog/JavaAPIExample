package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5856833004282015401L;
	private Integer articleId;
	private String content;
	private Integer type;
	private Date createTime;
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
