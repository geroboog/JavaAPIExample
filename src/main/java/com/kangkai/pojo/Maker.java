package com.kangkai.pojo;

import java.io.Serializable;

public class Maker implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2202456136129105228L;
	private Integer makerId;
	private String makerName;
	private String makerDetail;
	private Double price;
	private String makerIcon;
	public Integer getMakerId() {
		return makerId;
	}
	public void setMakerId(Integer makerId) {
		this.makerId = makerId;
	}
	public String getMakerName() {
		return makerName;
	}
	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}
	public String getMakerDetail() {
		return makerDetail;
	}
	public void setMakerDetail(String makerDetail) {
		this.makerDetail = makerDetail;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getMakerIcon() {
		return makerIcon;
	}
	public void setMakerIcon(String makerIcon) {
		this.makerIcon = makerIcon;
	}
	
	
	
	
}
