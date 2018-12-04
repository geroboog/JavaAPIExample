package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.User;
import com.kangkai.pojo.UserInfo;
import com.kangkai.pojo.UserWallet;
import com.kangkai.vo.UserSimpleVO;
import com.kangkai.vo.UserWalletVO;

public interface UserWalletMapper {
	/**
	 * 插入一个用户钱包信息
	 * @param user
	 * @return
	 */
	public int insert(UserWallet userWallet);
	/**
	 * 获取一个用户钱包信息
	 * @param userId
	 * @return
	 */
	public UserWalletVO getUserWallet(Integer userId);
	/**
	 * 获取一个用户钱包信息
	 * @param integer
	 * @return
	 */
	public UserWallet getUserWalletNor(Integer integer);
	/**
	 * 更新用户钱包信息
	 * @param userWallet
	 */
	public int updateUserWalletMoney(UserWallet userWallet);
	
}
