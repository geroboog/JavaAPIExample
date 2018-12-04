package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.ProductCollect;
import com.kangkai.pojo.Province;
import com.kangkai.vo.AttrVO;
import com.kangkai.vo.UserProductDetailVO;
import com.kangkai.vo.UserProductVO;

public interface ProductCollectMapper {
	/**
	 * 获取商品收藏结果
	 * @param collectMap
	 * @return
	 */
	ProductCollect getProductCollect(Map<String, Object> collectMap);
	/**
	 * 更新收藏状态
	 * @param collect
	 */
	void updateProductCollect(ProductCollect collect);
	/**
	 * 插入一条收藏记录
	 * @param productCollect
	 */
	void insertProductCollect(ProductCollect productCollect);
	/**
	 * 获取商品收藏列表
	 * @param map
	 * @return
	 */
	List<UserProductVO> selectProductCollectList(Map<String, Object> map);
	/**
	 * 计算商品收藏数
	 * @param productId
	 * @return
	 */
	Integer countProductCollectNum(Integer productId);


	
}
