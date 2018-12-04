package com.kangkai.service.utilService;

import com.kangkai.utils.Json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IBannerService {
	/**
	 * 获取广告牌列表
	 * @param type
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getBannerList(Integer type,Integer current, Integer pageSize);
	
}
