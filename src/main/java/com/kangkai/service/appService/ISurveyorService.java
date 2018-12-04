package com.kangkai.service.appService;

import java.io.UnsupportedEncodingException;

import com.kangkai.utils.Json;

public interface ISurveyorService {
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	Json login(String username, String password);

	Json getUserInfo(Integer userId);
	/**
	 * 申请成为量体师
	 * @param userId
	 * @param token
	 * @param city
	 * @param gender
	 * @param name
	 * @param img
	 * @param phone
	 * @param province
	 * @return
	 */
	Json applyForSurveyor(Integer userId, String token, String city, String gender, String name, String img,
			String phone, String province);
	/**
	 * 获取量体师钱包信息
	 * @param userId
	 * @param token
	 * @return
	 */
	Json getSurveyorBalance(Integer userId, String token);
	

}
