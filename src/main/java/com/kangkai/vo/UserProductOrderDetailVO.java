package com.kangkai.vo;

import java.util.ArrayList;
import java.util.List;

import com.kangkai.pojo.Coupon;
import com.kangkai.pojo.Maker;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.ProductOrderBase;

public class UserProductOrderDetailVO extends ProductOrderBase {
	
	private List<LogisticsInfoVO> logisticsInfoList=new ArrayList<LogisticsInfoVO>();
	private AddressVO addressVO;
	private List<UserProductOrderItemVO> productList;
	private Maker maker;
	private Coupon coupon;
	
	
	public List<LogisticsInfoVO> getLogisticsInfoList() {
		return logisticsInfoList;
	}
	public void setLogisticsInfoList(List<LogisticsInfoVO> logisticsInfoList) {
		this.logisticsInfoList = logisticsInfoList;
	}
	public Maker getMaker() {
		return maker;
	}
	public void setMaker(Maker maker) {
		this.maker = maker;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public List<UserProductOrderItemVO> getProductList() {
		return productList;
	}
	public void setProductList(List<UserProductOrderItemVO> productList) {
		this.productList = productList;
	}
	public void setProductOrder(ProductOrder productOrder) {
	this.setCreateTime(productOrder.getCreateTime());
	this.setFinalPrice(productOrder.getFinalPrice());
	this.setFreight(productOrder.getFreight());
	this.setIsComment(productOrder.getIsComment());
	this.setIsCompleteAfterSales(productOrder.getIsCompleteAfterSales());
	this.setProductOrderId(productOrder.getProductOrderId());
	this.setProductOrderNum(productOrder.getProductOrderNum());
	this.setState(productOrder.getState());
	this.setTotalPrice(productOrder.getTotalPrice());
	this.setIsApplyAfterSales(productOrder.getIsApplyAfterSales());
	this.setCouponId(productOrder.getCouponId());
	this.setIsStore(productOrder.getIsStore());
	}
	
	
	
	
	
}
