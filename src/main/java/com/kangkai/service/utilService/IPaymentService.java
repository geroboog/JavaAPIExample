package com.kangkai.service.utilService;

import com.kangkai.utils.Json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IPaymentService {
	/**
	 * 客户端获取支付参数
	 * @param userId
	 * @param token
	 * @param wasteNum
	 * @param type
	 * @param channel
	 * @return
	 */
	Json getPayment(Integer userId, String token, String wasteNum,Integer type, String channel);
	/**
	 * 处理回调结果
	 * @param out_trade_no
	 * @param type
	 * @param channel
	 */
	void webhooks(String out_trade_no, String type, String channel);
	
}
