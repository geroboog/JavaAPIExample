package com.kangkai.service.utilService;

public interface IWasteNumService {
	/**
	 * 获取商品支付流水号
	 * @param userId
	 * @param orderNum
	 * @return
	 */
	String getProductWasteNum(Integer userId,String orderNum);

}
