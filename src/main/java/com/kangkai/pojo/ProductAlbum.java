package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class ProductAlbum implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7216992879200169721L;
	private Integer productAlbumId;
	private Integer productId;
	private String showImg;
	public Integer getProductAlbumId() {
		return productAlbumId;
	}
	public void setProductAlbumId(Integer productAlbumId) {
		this.productAlbumId = productAlbumId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getShowImg() {
		return showImg;
	}
	public void setShowImg(String showImg) {
		this.showImg = showImg;
	}
	
	
	
	
}
