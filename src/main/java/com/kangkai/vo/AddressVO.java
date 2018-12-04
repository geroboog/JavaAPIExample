package com.kangkai.vo;

import java.util.Date;

import com.kangkai.pojo.ProductOrder;

public class AddressVO {
	
	private Integer  addressId;
	private String name;
	private String phone;
	private String province;
	private String city;
	private String district;
	private String detailAddr;
	private String postcode;
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
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
	public void setAddress(ProductOrder productOrder) {
		this.setAddressId(0);
		this.setCity(productOrder.getToCity());
		this.setDetailAddr(productOrder.getAddress());
		this.setDistrict(productOrder.getToDistrict());
		this.setName(productOrder.getRecipient());
		this.setPhone(productOrder.getPhone());
		this.setPostcode(productOrder.getPostcode());
		this.setProvince(productOrder.getToProvince());
	}
	
	
}
