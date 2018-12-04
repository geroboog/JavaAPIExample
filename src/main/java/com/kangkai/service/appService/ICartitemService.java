package com.kangkai.service.appService;

import com.kangkai.utils.Json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ICartitemService {

	/**
	 * 获取用户购物车商品列表
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @param token
	 * @return
	 */
	public Json getUserCartitem(Integer userId, Integer current, Integer pageSize, String token);
	/**
	 * 添加商品到用户购物车
	 * @param userId
	 * @param productId
	 * @param attrs
	 * @param token
	 * @return
	 */
	public Json addUserCartitemProduct(Integer userId, Integer productId, String attrs, String token);
	/**
	 * 更新用户购物车商品数量
	 * @param userId
	 * @param productId
	 * @param attrs
	 * @param count
	 * @param token
	 * @return
	 */
	public Json updateUserCartitemProduct(Integer userId, Integer productId, String attrs, Integer count, String token);
	
	
}
