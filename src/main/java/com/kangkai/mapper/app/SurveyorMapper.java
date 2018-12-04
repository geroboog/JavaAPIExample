package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Surveyor;
import com.kangkai.pojo.User;
import com.kangkai.vo.UserSimpleVO;

public interface SurveyorMapper {
	/**
	 * 根据用户Id查找用户
	 * @param userId
	 * @return
	 */
	public Surveyor selectById(Integer surveyorId);
	/**
	 * 根据手机号查询用户
	 * @param username
	 * @return
	 */
	public Surveyor selectByPhone(String username);
	
}
