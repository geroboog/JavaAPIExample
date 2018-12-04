package com.kangkai.service.appService;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.kangkai.utils.Json;
import com.kangkai.pojo.User;

import net.sf.json.JSONArray;

public interface IUserInfoService {
	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	Json getUserInfo(Integer userId);

	
}
