package com.kangkai.service.utilService;

import java.util.List;

/** 
 * 日志服务
 */
public interface ILogsService{
	/**
	 * 为后台写日志
	 * @param content
	 * @param adminId
	 */
	public void writeLogsForAdmin(String content, Integer adminId);
	/**
	 * 为用户写日志
	 * @param content
	 * @param userId
	 */
	public void writeLogsForUser(String content, Integer userId);
	/**
	 * 查看表是否存在
	 * @return
	 */
	Boolean checkTable();
	/**
	 * 创建表
	 */
	void createTable();
	
}

