package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.User;
import com.kangkai.vo.UserSimpleVO;

public interface UserMapper {
	/**
	 * 新增一个用户
	 * @param user
	 * @return
	 */
	public int insert(User user);
	/**
	 * 根据用户Id查找用户
	 * @param userId
	 * @return
	 */
	public User selectById(Integer userId);
	
	public int count();
	/**
	 * 根据手机号查找用户
	 * @param phone
	 * @return
	 */
	public User selectByPhone(String phone);
	/**
	 * 更新用户信息
	 * @param user
	 */
	public void updatePassword(User user);
	/**
	 * 更新用户信息
	 * @param map
	 */
	public void updateUserInfo(Map<String, Object> map);
	/**
	 * 查找聊天列表用户头像
	 * @param usernames
	 * @return
	 */
	public List<UserSimpleVO> selectByUsernames(String usernames);
	/**
	 * 获取company对应用户列表
	 * @param companyId
	 * @return
	 */
	public List<User> selectByCompanyId(Integer companyId);
	/**
	 * 更新余额
	 * @param demandUser
	 */
	public void updateMoney(User demandUser);
	/**
	 * 更新用户信息
	 * @param user
	 */
	public void updateUserInfo(User user);
}
