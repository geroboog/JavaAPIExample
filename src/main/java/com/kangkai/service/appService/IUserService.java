package com.kangkai.service.appService;

import java.io.UnsupportedEncodingException;

import com.kangkai.utils.Json;

public interface IUserService {
	;
	/**
	 * 注册
	 * @param phone
	 * @param vcode
	 * @param password
	 * @param _vcode
	 * @param registType
	 * @return
	 */
	Json regist(String phone, String vcode, String password, String _vcode);
	/**
	 * 获取验证码
	 * @param phone
	 * @param msgType
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	Json getVcode(String phone, int msgType) throws UnsupportedEncodingException;
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	Json login(String username, String password);
	/**
	 * 修改密码
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @param newPassword2
	 * @param token
	 * @return
	 */
	Json updatePassword(Integer userId, String oldPassword, String newPassword, String newPassword2, String token);
	/**
	 * 找回密码
	 * @param phone
	 * @param vcode
	 * @param _phone
	 * @param _vcode
	 * @param newPassword
	 * @param newPassword2
	 * @return
	 */
	Json findPassword(String phone, String vcode, String _phone, String _vcode, String newPassword,
			String newPassword2);
	/**
	 * 验证验证码
	 * @param phone
	 * @param vcode
	 * @param msgType
	 * @param _phone
	 * @param _vcode
	 * @return
	 */
	Json validateVcode(String phone, String vcode, int msgType, String _phone, String _vcode);
	/**
	 * 验证验证码
	 * @param userId
	 * @param token
	 * @return
	 */
	Json validateToken(Integer userId, String token);
	/**
	 * 获取账户余额
	 * @param userId
	 * @param token
	 * @return
	 */
	Json getBalance(Integer userId, String token);
	/**
	 * 上传用户信息
	 * @param userId
	 * @param token
	 * @param nickname
	 * @param userIcon
	 * @return
	 */
	Json uploadUserInfo(Integer userId, String token, String nickname, String userIcon);

}
