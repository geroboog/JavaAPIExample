package com.kangkai.vo;

import java.util.List;

import com.kangkai.pojo.ProductOrderItem;

public class UserProductOrderItemVO extends ProductOrderItem{

	public void setProductOrderProduct(UserProductOrderProductVO userProductOrderProductVO) {
		this.setProductOrderItemId(userProductOrderProductVO.getProductOrderItemId());
		this.setProductNum(userProductOrderProductVO.getProductNum());
		this.setProductId(userProductOrderProductVO.getProductId());
		this.setProductOrderId(userProductOrderProductVO.getProductOrderId());
		this.setProductTitle(userProductOrderProductVO.getProductTitle());
		this.setProductPrice(userProductOrderProductVO.getProductPrice());
		this.setAttrs(userProductOrderProductVO.getAttrs());
		this.setCustomAttrs(userProductOrderProductVO.getCustomAttrs());
		this.setProductShowImg(userProductOrderProductVO.getProductShowImg());
		this.setCount(userProductOrderProductVO.getCount());
		this.setType(userProductOrderProductVO.getType());
		this.setIsStore(userProductOrderProductVO.getIsStore());
	}

}
