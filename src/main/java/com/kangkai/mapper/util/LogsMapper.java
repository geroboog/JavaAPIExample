package com.kangkai.mapper.util;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Logs;

public interface LogsMapper {
	/**
	 * 插入日志
	 * @param logs
	 */
	void insert(Logs logs);
	
	/**
	 * 查看日志表是否存在
	 * @param logs
	 */
	Integer existTable(String tableName);
	/**
	 * 创建日志
	 * @param logs
	 */
	void createLogTable(String tableName);
	/**
	 * 创建主键日志
	 * @param logs
	 */
	void alterLogTable(String tableName);
}
