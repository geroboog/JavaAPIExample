package com.kangkai.pojo;

import java.io.Serializable;

public class ProductInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 28942331424211946L;
	private Integer productInfoId;
	private Integer productId;
	private Integer saleNUm;
	private Double evaluation;
	private Integer evaluationNum;
	
	public Integer getProductInfoId() {
		return productInfoId;
	}
	public void setProductInfoId(Integer productInfoId) {
		this.productInfoId = productInfoId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getSaleNUm() {
		return saleNUm;
	}
	public void setSaleNUm(Integer saleNUm) {
		this.saleNUm = saleNUm;
	}
	public Double getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(Double evaluation) {
		this.evaluation = evaluation;
	}
	public Integer getEvaluationNum() {
		return evaluationNum;
	}
	public void setEvaluationNum(Integer evaluationNum) {
		this.evaluationNum = evaluationNum;
	}
	
	
}
