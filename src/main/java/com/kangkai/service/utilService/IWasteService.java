package com.kangkai.service.utilService;

import com.kangkai.pojo.ProductOrder;
import com.kangkai.utils.Json;

public interface IWasteService {
	/**
	 * 处理商品购买流水记录
	 * @param channel
	 * @param wasteNum
	 * @param productOrder
	 */
	void processProductWaste(String channel, String wasteNum, ProductOrder productOrder);
	/**
	 * 获取用户流水信息
	 * @param userId
	 * @param token
	 * @param current
	 * @param pageSize
	 * @param wasteTime
	 * @return
	 */
	Json getUserWasteList(Integer userId, String token, Integer current, Integer pageSize, String wasteTime);
	
}
