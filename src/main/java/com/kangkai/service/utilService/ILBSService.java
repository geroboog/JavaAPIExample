package com.kangkai.service.utilService;

import com.kangkai.utils.Json;

public interface ILBSService {
	/**
	 * 获取LBS附近的人
	 * @param userId
	 * @param x_axis
	 * @param y_axis
	 * @param token
	 * @return
	 */
	Json getLBSPeople(Integer userId, Double x_axis, Double y_axis, String token);
	/**
	 * 获取LBS附近的量体师
	 * @param userId
	 * @param x_axis
	 * @param y_axis
	 * @param token
	 * @return
	 */
	Json getLBSSurveyor(Integer userId, Double x_axis, Double y_axis, String token);
	/**
	 * 获取量体师坐标
	 * @param userId
	 * @param token
	 * @param surveyorId
	 * @return
	 */
	Json getSurveyorAxis(Integer userId, String token, Integer surveyorId);
	/**
	 * 获取某个用户坐标
	 * @param surveyorId
	 * @param token
	 * @param userId
	 * @return
	 */
	Json getUserAxis(Integer surveyorId, String token, Integer userId);

}
