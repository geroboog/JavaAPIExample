package com.kangkai.mapper.util;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Banner;
import com.kangkai.pojo.Category;
import com.kangkai.pojo.SurveyorSystemMessage;
import com.kangkai.pojo.UserSystemMessage;

public interface SystemMessageMapper {


	/**
	 * 查看日志表是否存在
	 * @param logs
	 */
	public Integer existTable(String tableName);
	/**
	 * 创建日志
	 * @param logs
	 */
	public void createUserSystemMessageTable(String tableName);
	/**
	 * 创建日志
	 * @param logs
	 */
	public void createSurveyorSystemMessageTable(String tableName);
	/**
	 * 创建主键日志
	 * @param logs
	 */
	public void alterUserSystemMessageTable(String tableName);
	/**
	 * 创建主键日志
	 * @param logs
	 */
	public void alterSurveyorSystemMessageTable(String tableName);
	/**
	 * 插入一条用户系统信息
	 * @param userSystemMessage
	 */
	public void insertUserSystemMessage(Map<String, Object> userSystemMessage);
	/**
	 * 插入量体师系统消息
	 * @param surveyorSystemMessage
	 */
	public void insertSurveyorSystemMessage(Map<String, Object> surveyorSystemMessage);
	/**
	 * 获取用户系统消息列表
	 * @param map
	 * @return
	 */
	public List<UserSystemMessage> selectUserSystemMessageListByUser(Map<String, Object> map);
	/**
	 *  获取量体师系统消息列表
	 * @param map
	 * @return
	 */
	public List<SurveyorSystemMessage> selectSurveyorSystemMessageListBySurveyor(Map<String, Object> map);
	/**
	 * 根据Id获取用户系统消息
	 * @param map
	 * @return
	 */
	public UserSystemMessage selectUserSystemMessageById(Map<String, Object> map);
	/**
	 * 更新用户信息阅读状态
	 * @param map
	 * @return
	 */
	public int updateUserSystemMessageIsRead(Map<String, Object> map);
	
}
