package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Banner;
import com.kangkai.pojo.Category;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.ProductOrderItem;
import com.kangkai.vo.UserProductOrderItemVO;

public interface ProductOrderItemMapper {
	/**
	 * 获取订单商品列表
	 * @param map
	 * @return
	 */
	List<UserProductOrderItemVO> selectProductOrderItemList(Map<String, Object> map);
	/**
	 * 插入订单商品数据
	 * @param orderitem
	 */
	void insertOrderItem(ProductOrderItem orderitem);
	/**
	 * 更新寄存状态
	 * @param map
	 */
	void updateIsStore(Map<String, Object> map);
	/**
	 * 查询订单商品
	 * @param productOrderItemId
	 * @return
	 */
	ProductOrderItem selectById(Integer productOrderItemId);
	/**
	 * 获取订单商品列表
	 * @param map
	 * @return
	 */
	List<ProductOrderItem> selectProductOrderItemListNo(Map<String, Object> map);
	

}
