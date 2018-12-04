package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.ProductAttr;
import com.kangkai.pojo.Province;
import com.kangkai.vo.AttrVO;
import com.kangkai.vo.UserCartitemVO;
import com.kangkai.vo.UserProductDetailVO;
import com.kangkai.vo.UserProductVO;

public interface ProductMapper {
	/**
	 * 根据商品名称和类别查询商品
	 * @param map
	 * @return
	 */
	List<UserProductVO> searchProductByTitleAndCategoryId(Map<String, Object> map);
	/**
	 * 获取商品详情
	 * @param productId
	 * @return
	 */
	UserProductDetailVO getProductDetail(Integer productId);
	/**
	 * 根据购物车商品Id查询购物车商品列表
	 * @param map
	 * @return
	 */
	List<UserCartitemVO> selectCartitemProductListByCartitemIds(Map<String, Object> map);
	/**
	 * 根据订单Id获取商品列表
	 * @param productOrderId
	 * @return
	 */
	List<UserCartitemVO> selectProductListByProductOrderId(Integer productOrderId);
	
	
}
