package com.kangkai.service.appService;

import com.kangkai.utils.Json;

public interface ISurveyorOrderMessageService {
	/**
	 * 获取量体师订单小红点消息
	 * @param userId
	 * @param token
	 * @return
	 */
	public Json getSurveyorOrderMessage(Integer userId, String token);
	/**
	 * 更新量体师订单小红点消息0为删除1为增加
	 * @param userId
	 * @param state
	 * @param state
	 * @return
	 */
	Integer updateSurveyorOrderMessage(Integer userId, Integer state,Integer type);

}
