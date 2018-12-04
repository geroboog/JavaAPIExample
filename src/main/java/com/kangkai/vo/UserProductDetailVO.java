package com.kangkai.vo;

import java.util.List;

import com.kangkai.pojo.Product;
import com.kangkai.pojo.ProductAlbum;
import com.kangkai.pojo.ProductAttr;
import com.kangkai.pojo.ProductInfo;


public class UserProductDetailVO extends Product{
	private int isCollect;
	private List<ProductAlbum> productAlbumList;
	private ProductInfo productInfo;
	private List<ProductAttr> productAttrList;
	private int collectNum;
	private double evaluationRate;
	
	
	public int getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}
	public double getEvaluationRate() {
		return evaluationRate;
	}
	public void setEvaluationRate(double evaluationRate) {
		this.evaluationRate = evaluationRate;
	}
	public List<ProductAttr> getProductAttrList() {
		return productAttrList;
	}
	public void setProductAttrList(List<ProductAttr> productAttrList) {
		this.productAttrList = productAttrList;
	}
	public int getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(int isCollect) {
		this.isCollect = isCollect;
	}
	public List<ProductAlbum> getProductAlbumList() {
		return productAlbumList;
	}
	public void setProductAlbumList(List<ProductAlbum> productAlbumList) {
		this.productAlbumList = productAlbumList;
	}
	public ProductInfo getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	
}
