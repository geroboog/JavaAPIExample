package com.kangkai.service.appService;

import com.kangkai.utils.Json;

public interface IProductOrderService {
	/**
	 * 获取订单详情
	 * @param userId
	 * @param productOrderId
	 * @param token
	 * @return
	 */
	public Json getProductOrderDetail(Integer userId, Integer productOrderId, String token);
	/**
	 * 获取用户订单列表
	 * @param userId
	 * @param state
	 * @param token
	 * @param current
	 * @param pageSize
	 * @return
	 */
	public Json getProductOrderList(Integer userId, Integer state, String token, Integer current, Integer pageSize);
	/**
	 * 获取物流信息
	 * @param userId
	 * @param token
	 * @param productOrderId
	 * @return
	 */
	public Json getProductOrderLogisticsInfoList(Integer userId, String token, Integer productOrderId);
	/**
	 * 申请售后
	 * @param userId
	 * @param token
	 * @param productOrderId
	 * @return
	 */
	public Json applyAfterSales(Integer userId, String token, Integer productOrderId);
	/**
	 * 签收订单
	 * @param userId
	 * @param token
	 * @param productOrderId
	 * @return
	 */
	public Json signProductOrder(Integer userId, String token, Integer productOrderId);

}
