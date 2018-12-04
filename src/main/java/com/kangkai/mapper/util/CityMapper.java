package com.kangkai.mapper.util;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.City;

public interface CityMapper {

	List<City> selectAll(Map<String, Object> map);
	/**
	 * 获取所有省份
	 * @param map
	 * @return
	 */
	List<City> selectAll2(Map<String, Object> map);


}
