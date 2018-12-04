package com.kangkai.vo;

import java.util.List;

import com.kangkai.pojo.Product;
import com.kangkai.pojo.Sales;

public class SalesVO extends Sales{
	
	private List<Product> productList;

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	

}
