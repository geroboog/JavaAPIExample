package com.kangkai.mapper.util;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.BadWord;
import com.kangkai.pojo.Waste;

public interface BadWordMapper {
	/**
	 * 获取所有的敏感词
	 * @return
	 */
	List<BadWord> selectAll();



}
