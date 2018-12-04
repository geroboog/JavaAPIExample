package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Category;

public interface CategoryMapper {
	/**
	 * 获取目录列表
	 * @param map
	 * @return
	 */
	List<Category> selectCategory(Map map);


}
