package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class Address implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5558057895552437892L;
	/**
	 * 
	 */
	private Integer  addressId;
	private Integer  userId;
	private String name;
	private String phone;
	private String province;
	private String city;
	private String district;
	private String detailAddr;
	private String postcode;
	private Integer  isDefault;
	private Date createTime;
	private Integer  isDelete;
	public Address() {
	}
	
	public Integer  getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer  isDelete) {
		this.isDelete = isDelete;
	}

	public Integer  getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer  addressId) {
		this.addressId = addressId;
	}
	public Integer  getUserId() {
		return userId;
	}
	public void setUserId(Integer  userId) {
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDetailAddr() {
		return detailAddr;
	}
	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public Integer  getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer  isDefault) {
		this.isDefault = isDefault;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
