package com.kangkai.service.utilService;

import com.kangkai.utils.Json;

public interface ISystemMessageService {
	/**
	 * 发送商品信息
	 * @param Integer productOrderId
	 * @param state
	 */
	public void sendProductSystemMessage(Integer productOrderId,Integer state);
	/**
	 * 发送量体师信息
	 * @param surveyorOrderId
	 * @param state
	 */
	void sendSurveyorSystemMessage(Integer surveyorOrderId, Integer state);
	/**
	 * 获取用户系统消息列表
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @param token
	 * @return
	 */
	public Json getUserSystemMessageList(Integer userId, Integer current, Integer pageSize, String token);
	/**
	 * 获取量体师系统消息列表
	 * @param surveyorId
	 * @param current
	 * @param pageSize
	 * @param token
	 * @return
	 */
	public Json getSurveyorSystemMessageList(Integer surveyorId, Integer current, Integer pageSize, String token);
	/**
	 * 获取消息详情
	 * @param userId
	 * @param token
	 * @param userSystemMessageId
	 * @return
	 */
	public Json getUserMessageDetail(Integer userId, String token, Integer userSystemMessageId);

}
