package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class Surveyor implements Serializable{
	private Integer surveyorId;
	private String username;
	private String phone;
	private String password;
	private String userIcon;
	private String nickname;
	private String gender;
	private Date createTime;
	private Integer isDelete;
	public Integer getSurveyorId() {
		return surveyorId;
	}
	public void setSurveyorId(Integer surveyorId) {
		this.surveyorId = surveyorId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
	
}
