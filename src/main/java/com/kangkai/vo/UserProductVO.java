package com.kangkai.vo;

import com.kangkai.pojo.Product;

public class UserProductVO extends Product{
	private int count;
	private String productAttr;
	
	public String getProductAttr() {
		return productAttr;
	}

	public void setProductAttr(String productAttr) {
		this.productAttr = productAttr;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
