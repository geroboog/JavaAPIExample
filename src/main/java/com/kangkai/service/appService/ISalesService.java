package com.kangkai.service.appService;

import com.kangkai.utils.Json;

public interface ISalesService {
	/**
	 * 获取促销列表
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getSalesList(Integer current, Integer pageSize);
	/**
	 * 获取对应促销列表商品列表
	 * @param salesId
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getSalesListBySaleId(Integer salesId, Integer current, Integer pageSize);

}
