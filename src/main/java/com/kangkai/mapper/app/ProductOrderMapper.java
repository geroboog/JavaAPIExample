package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.ProductAttr;
import com.kangkai.pojo.ProductInfo;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.Province;
import com.kangkai.vo.AttrVO;
import com.kangkai.vo.UserClothesVO;
import com.kangkai.vo.UserCommentVO;
import com.kangkai.vo.UserProductDetailVO;
import com.kangkai.vo.UserProductOrderDetailVO;
import com.kangkai.vo.UserProductOrderProductVO;
import com.kangkai.vo.UserProductOrderVO;
import com.kangkai.vo.UserProductVO;

public interface ProductOrderMapper  {
	/**
	 * 获取订单信息
	 * @param productId
	 * @return
	 */
	ProductOrder selectById(Integer productOrderId);
	/**
	 * 根据Id查询商品订单详情
	 * @param productOrderId
	 * @return
	 */
	UserProductOrderDetailVO selectProductOrderDetailById(Integer productOrderId);
	/**
	 * 查询用户订单列表
	 * @param map
	 * @return
	 */
	List<UserProductOrderVO> selectProductOrderList(Map<String, Object> map);
	/**
	 * 查询用户订单列表
	 * @param map
	 * @return
	 */
	List<UserProductOrderProductVO> selectProductOrderProductList(Map<String, Object> map);
	/**
	 * 根据订单号查询订单
	 * @param productOrderNum
	 * @return
	 */
	ProductOrder selectProductOrderDetailByProductOrderNum(String productOrderNum);
	/**
	 * 更新订单状态
	 * @param productOrder
	 */
	void updateProductOrderState(ProductOrder productOrder);
	/**
	 * 根据用户ID查询最近一张订单
	 * @param userId
	 * @return
	 */
	ProductOrder selectLastProductOrderByUserId(Integer userId);
	/**
	 * 插入一条订单
	 * @param productOrder
	 * @return
	 */
	void insertProductOrder(ProductOrder productOrder);
	/**
	 * 根据天气查看衣柜衣服列表
	 * @param map
	 * @return
	 */
	List<UserClothesVO> selectClothesByUserIdAndSeason(Map<String, Object> map);
	/**
	 * 更新商品评论状态
	 * @param map
	 */
	void updateProductOrderIsComment(Map<String, Object> map);
	/**
	 * 更新申请售后状态
	 * @param productOrder
	 */
	void updateApplyAfterSales(ProductOrder productOrder);
	/**
	 * 更新寄存状态
	 * @param productOrder
	 */
	void updateIsStore(ProductOrder productOrder);
	
	
}
