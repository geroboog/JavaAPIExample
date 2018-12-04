package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.User;
import com.kangkai.pojo.UserInfo;
import com.kangkai.vo.UserInfoVO;
import com.kangkai.vo.UserSimpleVO;

public interface UserInfoMapper {
	/**
	 * 新增一个用户信息
	 * @param user
	 * @return
	 */
	public int insert(UserInfo userInfo);
	/**
	 * 获取一个用户信息
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfo(Integer userId);
	/**
	 * 更新用户信息
	 * @param userInfo
	 */
	public void updateUserInfo(UserInfo userInfo);
	/**
	 * 获取一个用户信息
	 * @param userId
	 * @return
	 */
	public UserInfoVO getUserInfoVO(Integer userId);
	
}
