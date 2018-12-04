package com.kangkai.service.appService;

import java.util.Date;

import com.kangkai.utils.Json;

import net.sf.json.JSONObject;

public interface ISurveyorOrderService {
	/**
	 * 获取量体师订单列表
	 * @param userId
	 * @param state
	 * @param token
	 * @param current
	 * @param pageSize
	 * @return
	 */
	public Json getSurveyorOrderList(Integer userId, Integer state, String token, Integer current, Integer pageSize);
	/**
	 * 发布量体师预约需求
	 * @param userId
	 * @param token
	 * @param addressId
	 * @param bookTime
	 * @return
	 */
	public Json releaseSurveyorOrder(Integer userId, String token, Integer addressId, Date bookTime);
	/**
	 * 量体师获取量体师预约订单
	 * @param surveyorId
	 * @param token
	 * @param state
	 * @param current
	 * @param pageSize
	 * @return
	 */
	public Json getSurveyorOrderListForSurveyor(Integer surveyorId, String token, Integer state, Integer current, Integer pageSize);
	/**
	 * 订单开始服务
	 * @param surveyorId
	 * @param token
	 * @param surveyorOrderId
	 * @return
	 */
	public Json startServe(Integer surveyorId, String token, Integer surveyorOrderId);
	/**
	 * 完成服务
	 * @param surveyorId
	 * @param token
	 * @param surveyorOrderId
	 * @param jsonObject
	 * @return
	 */
	Json finishServe(Integer surveyorId, String token, Integer surveyorOrderId, JSONObject jsonObject);
	
}
