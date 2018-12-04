package com.kangkai.service.appService;

import java.util.Map;

import com.kangkai.utils.Json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ICollectService {
	/**
	 * 收藏/取消收藏商品
	 * @param userId
	 * @param token
	 * @param productId
	 * @return
	 */
	Json collectProduct(Integer userId, String token, Integer productId);
	/**
	 * 获取商品收藏列表
	 * @param userId
	 * @param token
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getProductCollectList(Integer userId, String token, Integer current, Integer pageSize);
	
}
