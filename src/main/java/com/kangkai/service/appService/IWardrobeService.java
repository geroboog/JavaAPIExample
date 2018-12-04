package com.kangkai.service.appService;

import java.util.Date;

import com.kangkai.utils.Json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IWardrobeService {
	/**
	 * 据天气获取衣服列表
	 * @param userId
	 * @param token
	 * @param season
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getClothesList(Integer userId, String token, String season, Integer current, Integer pageSize);
	/**
	 * 寄送衣服到商店
	 * @param userId
	 * @param token
	 * @param productOrderItemId
	 * @param productOrderId
	 * @param shopId
	 * @param phone
	 * @param remark
	 * @param storeTime
	 * @return
	 */
	Json sendClothesToShop(Integer userId, String token, Integer productOrderItemId, Integer productOrderId,
			Integer shopId, String phone, String remark, Date storeTime);
	
}
