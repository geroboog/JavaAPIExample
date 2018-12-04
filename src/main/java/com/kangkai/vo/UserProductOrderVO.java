package com.kangkai.vo;

import java.util.Date;
import java.util.List;

import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.ProductOrderBase;

public class UserProductOrderVO extends ProductOrderBase {

	private List<UserProductOrderItemVO> productList;

	public List<UserProductOrderItemVO> getProductList() {
		return productList;
	}

	public void setProductList(List<UserProductOrderItemVO> productList) {
		this.productList = productList;
	}

	public void setProductOrder(UserProductOrderProductVO userProductOrderProductVO) {
		this.setProductOrderId(userProductOrderProductVO.getProductOrderId());
		this.setIsComment(userProductOrderProductVO.getIsComment());
		this.setIsCompleteAfterSales(userProductOrderProductVO.getIsCompleteAfterSales());
		this.setIsApplyAfterSales(userProductOrderProductVO.getIsApplyAfterSales());
		this.setFreight(userProductOrderProductVO.getFreight());
		this.setTotalPrice(userProductOrderProductVO.getTotalPrice());
		this.setIsStore(userProductOrderProductVO.getIsStore());
		this.setCouponId(userProductOrderProductVO.getCouponId());
		this.setState(userProductOrderProductVO.getState());
		this.setProductOrderNum(userProductOrderProductVO.getProductOrderNum());
		this.setCreateTime(userProductOrderProductVO.getCreateTime());
		this.setFinalPrice(userProductOrderProductVO.getFinalPrice());
	}
	
}
