package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.ProductAttr;
import com.kangkai.pojo.ProductInfo;
import com.kangkai.pojo.Province;
import com.kangkai.vo.AttrVO;
import com.kangkai.vo.UserProductDetailVO;
import com.kangkai.vo.UserProductVO;

public interface ProductInfoMapper {
	/**
	 * 获取商品统计信息
	 * @param productId
	 * @return
	 */
	ProductInfo selectProductInfo(Integer productId);
	/**
	 * 更新商品评论
	 * @param productId
	 */
	void updateEvaluation(Integer productId);


	
}
