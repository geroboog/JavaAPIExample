package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Cartitem;
import com.kangkai.pojo.Coupon;
import com.kangkai.pojo.Maker;
import com.kangkai.pojo.User;
import com.kangkai.pojo.UserInfo;
import com.kangkai.vo.UserCartitemVO;
import com.kangkai.vo.UserSimpleVO;

public interface CartitemMapper {
	/**
	 * 查询用户购物车商品列表
	 * @param map
	 * @return
	 */
	List<UserCartitemVO> selectUserCartitemList(Map<String, Object> map);
	/**
	 * 查看该商品是否被收藏
	 * @param cartitem
	 * @return
	 */
	Cartitem getCartitemProduct(Cartitem cartitem);
	/**
	 * 更新收藏商品
	 * @param cartitemC
	 */
	void updateCartitemProduct(Cartitem cartitemC);
	/**
	 * 插入一条收藏商品
	 * @param cartitem
	 */
	void addCartitemProduct(Cartitem cartitem);
	/**
	 * 删除购物车商品
	 * @param cartitemId
	 */
	void deleteCartitemProduct(Integer cartitemId);
	
	
}
