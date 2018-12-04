package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class ApplySurveyor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1243468896367059430L;
	private Integer applySurveyorId;
	private Integer userId;
	private String name;
	private String phone;
	private String gender;
	private String province;
	private String city;
	private Date createTime;
	private String img;
	private Integer isDelete;
	private Integer isSolved;
	
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getIsSolved() {
		return isSolved;
	}
	public void setIsSolved(Integer isSolved) {
		this.isSolved = isSolved;
	}
	public Integer getApplySurveyorId() {
		return applySurveyorId;
	}
	public void setApplySurveyorId(Integer applySurveyorId) {
		this.applySurveyorId = applySurveyorId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
	
}
