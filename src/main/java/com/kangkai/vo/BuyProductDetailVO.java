package com.kangkai.vo;

import java.util.List;


public class BuyProductDetailVO {
	private int payable=1;
	private int couponId;
	private double money;
	private double freight;
	private AddressVO address;
	private List<UserCartitemVO> productList;
	private CouponVO coupon;
	
	
	public AddressVO getAddress() {
		return address;
	}

	public void setAddress(AddressVO address) {
		this.address = address;
	}

	public List<UserCartitemVO> getProductList() {
		return productList;
	}

	public void setProductList(List<UserCartitemVO> productList) {
		this.productList = productList;
	}

	public CouponVO getCoupon() {
		return coupon;
	}

	public void setCoupon(CouponVO coupon) {
		this.coupon = coupon;
	}

	public double getFreight() {
		return freight;
	}
	
	public void setFreight(double freight) {
		this.freight = freight;
	}
	
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getPayable() {
		return payable;
	}
	public void setPayable(int payable) {
		this.payable = payable;
	}
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	
	
	
	
	
}
