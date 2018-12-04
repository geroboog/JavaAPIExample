package com.kangkai.pojo;

import java.io.Serializable;

public class UserInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1362114789330684194L;
	private Integer userInfoId;
	private Integer userId;
	private String province;
	private String city;
	private String shareRecommendCode;
	
	
	public String getShareRecommendCode() {
		return shareRecommendCode;
	}
	public void setShareRecommendCode(String shareRecommendCode) {
		this.shareRecommendCode = shareRecommendCode;
	}
	public Integer getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	
}
