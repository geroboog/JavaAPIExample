package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.ProductAttr;
import com.kangkai.pojo.ProductInfo;
import com.kangkai.pojo.Province;
import com.kangkai.vo.AttrVO;
import com.kangkai.vo.UserProductDetailVO;
import com.kangkai.vo.UserProductVO;

public interface ProductAttrMapper {
	
	/**
	 * 获取商品属性	
	 * @param productId
	 * @return
	 */
	List<ProductAttr> getProductAttrs(Integer productId);
	
}
