package com.kangkai.service.appService;

import com.kangkai.utils.Json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ICategoryService {
	/**
	 * 获取商品目录列表
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getCategoryList(Integer current, Integer pageSize);
	
}
