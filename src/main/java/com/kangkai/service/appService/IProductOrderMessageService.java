package com.kangkai.service.appService;

import com.kangkai.utils.Json;

public interface IProductOrderMessageService {
	/**
	 * 获取商品订单小红点消息
	 * @param userId
	 * @param token
	 * @return
	 */
	public Json getProductOrderMessage(Integer userId, String token);
	/**
	 * 更新商品订单小红点消息0为删除1为增加
	 * @param userId
	 * @param state
	 * @param state
	 * @return
	 */
	Integer updateProductOrderMessage(Integer userId, Integer state,Integer type);

}
