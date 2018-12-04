package com.kangkai.service.appService.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.utils.Encrypt;
import com.kangkai.utils.ImTokenUtils;
import com.kangkai.utils.Json;
import com.kangkai.utils.SDKTestSendTemplateSMS;
import com.kangkai.utils.SendVcodeUtil;
import com.kangkai.utils.TokenUtil;
import com.kangkai.exception.TokenInvalidException;
import com.kangkai.mapper.app.UserInfoMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.mapper.app.UserWalletMapper;
import com.kangkai.pojo.User;
import com.kangkai.pojo.UserInfo;
import com.kangkai.pojo.UserWallet;
import com.kangkai.service.appService.IUserService;
import com.kangkai.vo.IMUser;
import com.kangkai.vo.UserVO;
import com.kangkai.vo.UserWalletVO;
import com.taobao.api.ApiException;

@Service(value="/userService")
@Transactional
public class UserService implements IUserService{
	//日志记录
		private Log log = LogFactory.getLog(this.getClass());
		@Resource
		private UserMapper userMapper;
		@Resource
		private UserInfoMapper userInfoMapper;
		@Resource
		private UserWalletMapper userWalletMapper;
		//注册
		@Override
		public Json regist(String phone, String vcode, String password, String _vcode) {
			int msgType = 1;
			Json json=new Json();
			Date nowDate=new Date();
			if(_vcode==null||!vcode.equals(_vcode))
			{
				json.setCode(104);
				log.info("验证码有误");
				json.setData(null);
				return json;
			}
			User usercheck = userMapper.selectByPhone(phone);
			if (usercheck != null) 
			{
					json.setCode(108);
					log.error("该手机号码已注册");
					return json;
			}
			if (json.getCode() == 100) {
				// 判断密码格式是否正确
				// Pattern p = Pattern.compile("^(?![^a-zA-Z]+$)(?!\\D+$).{8,15}$");
				Pattern p = Pattern.compile("[a-zA-Z0-9]{6,16}");
				Matcher m = p.matcher(password);
				if (!m.matches()) {
					json.setCode(102);
					log.info("密码格式不正确");
					json.setData(null);
					log.error("密码格式不正确");
					return json;
				}
				User user = new User();
				user.setPhone(phone);
				user.setUsername(phone);
				user.setUserIcon("");
				user.setNickname(phone);
				user.setCreateTime(nowDate);
				String md5AndSha_pwd = Encrypt.md5AndSha(password);
				String HXPWD=Encrypt.md5(md5AndSha_pwd);
				user.setPassword(md5AndSha_pwd);
				userMapper.insert(user);
				
				if(user.getUserId()==null)
				{
					Integer userId=user.getUserId();
					UserInfo userInfo =new UserInfo();
					userInfo.setUserId(userId);
					userInfoMapper.insert(userInfo);
					
					UserWallet userWallet=new UserWallet();
					userWallet.setUserId(userId);
					userWalletMapper.insert(userWallet);
				}
				
				json.setCode(100);
				json.setData(user.getUserId());
				
			}
			return json;
		}



		//获取验证码
			@Override
			public Json getVcode(String phone, int msgType) throws UnsupportedEncodingException{
				Json json = new Json();
				Pattern p = Pattern.compile("^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$");
				Matcher m = p.matcher(phone);
				if (!m.matches()) {// 判断手机号码是否合法
					json.setCode(103);
					log.info("无效的手机号码");
					json.setData(null);
					log.error("无效的手机号码");
					return json;
				}
				User user = userMapper.selectByPhone(phone);
				String vcode1=null;
				if (msgType == 1) {
					if (user != null) {
						json.setCode(108);
						log.error("该手机号码已注册");
						return json;
					}
					try {
						vcode1=SendVcodeUtil.sendRegistMsg(phone);
					} catch (ApiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(vcode1!=null){
						json.setCode(100);
						json.setData(vcode1);
						json.setMsg("发送验证码成功");
						return json;
					}
					
				} 
				else if (msgType == 2) 
				{
					if (user == null) {
						json.setCode(107);
						log.info("该手机号码尚未注册");
						json.setData(null);
						log.error("该手机号码尚未注册");
						return json;
					}
					
					try {
						vcode1=SendVcodeUtil.sendFindPasswordMsg(phone);
					} catch (ApiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(vcode1!=null){
						json.setCode(100);
						json.setData(vcode1);
						json.setMsg("发送验证码成功");
						return json;
					}
				}
				else {
					json.setCode(105);
					log.info("无效的短信类型");
					json.setData(null);
					log.error("无效的短信类型");
					return json;
				}
				
				json.setCode(100);
				log.info("短信发送成功");
				json.setData(vcode1);
				log.info("短信发送成功");
				return json;
			}



			//登陆
			@Override
			public Json login(String username, String password) {
				Json json = new Json();
				User user = userMapper.selectByPhone(username);
				if(user==null)
				{
					json.setCode(107);
					log.info("该账号尚未注册");
					json.setData(null);
					return json;
				}
				// 检测用户
				// 检测密码
				// 将用户收入的密码加密
				String md5AndSha_password = Encrypt.md5AndSha(password);
				// 将加密结果和数据表中的密文对比
				if (!user.getPassword().equals(md5AndSha_password)) {
					json.setCode(106);
					log.error("密码错误");
					return json;
				}
				String token=TokenUtil.getUserToken(user);
				UserVO uservo=new UserVO();
				uservo.setNickname(user.getNickname());
				uservo.setPhone(user.getPhone());
				uservo.setToken(token);
				uservo.setUserId(user.getUserId());
				uservo.setUsername(user.getUsername());
				uservo.setUserIcon(user.getUserIcon());
				uservo.setGender(user.getGender());
				//userVO.setToken(token);
				json.setCode(100);
				json.setMsg("登录成功");
				json.setData(uservo);
				return json;
			}
			
			private String getToken(User user)
			{
				String systemName = "kangkai";
				String content = user.getUserId().toString()+ systemName+user.getPassword();
				String token=Encrypt.md5AndSha(content);
				return token;
			}
			
			
			//找回密码
			@Override
			public Json findPassword(String phone, String vcode, String _phone, String _vcode, String newPassword,
					String newPassword2) {
				int msgType = 2;
				Json json = validateVcode(phone, vcode, msgType, _phone, _vcode);
				if (json.getCode() == 100) {
					Pattern p = Pattern.compile("[a-zA-Z0-9]{6,16}");
					Matcher m = p.matcher(newPassword);
					if (!m.matches()) {
						json.setCode(102);
						// json.setMsg("密码必须包含字母和数字且至少8位");
						json.setData(null);
						log.error("密码格式不正确");
						return json;
					}
					if (!newPassword.equals(newPassword2)) {
						json.setCode(103);
						json.setData(null);
						log.error("两次输入的密码不相同");
						return json;
					}
					User user=userMapper.selectByPhone(phone);
					if (user != null ) 
					{
						String newPassword3 = Encrypt.md5AndSha(newPassword);
						user.setPassword(newPassword3);
						String HXPWD=Encrypt.md5(newPassword3);
						userMapper.updatePassword(user);
//						ObjectNode modifyIMUserPasswordWithAdminTokenNode = (ObjectNode) userAPIImpl
//								.modifyIMUserPasswordWithAdminToken(user.getUsername(),
//										JsonNodeFactory.instance.objectNode().put("newpassword", HXPWD));
//						if (null != modifyIMUserPasswordWithAdminTokenNode) {
//							log.info("重置用户密码 : " + modifyIMUserPasswordWithAdminTokenNode.toString());
//						}
						json.setCode(100);
						json.setData(null);
						log.info("修改密码成功");
					} else {
						json.setCode(107);
						json.setData(null);
						log.error("该账户尚未注册");
						return json;
					}
				}
				return json;
			}

			//修改密码
			@Override
			public Json updatePassword(Integer userId, String oldPassword, String newPassword, String newPassword2,String  token) {
				User user = userMapper.selectById(userId);
				Json json=new Json();
				if (user == null) {
					json.setCode(109);
					json.setMsg("用户不存在");
					json.setData(null);
					log.error("用户不存在");
					return json;
				}
				
				boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
				if(!isTrue){
					json.setCode(200);
					json.setData(null);
					log.error("无效的token");
					return json;
				}
				
				String md5OldPassword=Encrypt.md5AndSha(oldPassword);
				if(!md5OldPassword.equals(user.getPassword())){
					json.setCode(106);
					json.setData(null);
					log.info("您输入的密码不正确");
					return json;
				}
				if(!newPassword.equals(newPassword2)){
					json.setCode(103);
					json.setData(null);
					log.info("两次输入的密码不一致");
					return json;
				}
				String newPassword3 = Encrypt.md5AndSha(newPassword);
				user.setPassword(newPassword3);
				String HXPWD=Encrypt.md5(newPassword3);
				userMapper.updatePassword(user);
				json.setCode(100);
				json.setData(null);
				json.setMsg("修改密码成功");
				return json;
			}
			
			
			/**
			 * 验证验证码
			 * 
			 * @param phone
			 * @param vcode
			 * @param msgType
			 * @param _phone
			 * @param _vcode
			 * @return
			 */
			@Override
			public Json validateVcode(String phone, String vcode, int msgType, String _phone, String _vcode) {
				Json json = new Json();
				if (!(_phone != null && _vcode != null && _phone.equals(phone) && _vcode.equals(vcode))) {
					json.setCode(104);
					json.setData(null);
					log.error("验证码有误");
					return json;
				}
				User user=userMapper.selectByPhone(_phone);
				if (msgType == 1) {
					if (user != null) {
						json.setCode(108);
						json.setData(null);
						log.error("该手机号码已注册");
						return json;
					}
				} else if (msgType == 2) {
					if (user == null) {
						json.setCode(107);
						json.setData(null);
						log.error("该手机号尚未注册");
						return json;
					}
				} else {
					json.setCode(105);
					json.setData(null);
					log.error("无效的短信类型");
					return json;
				}
				json.setCode(100);
				json.setData(null);
				log.info("验证码正确");
				return json;
			}
			@Override
			 public Json validateToken(Integer userId, String token) {
					Json json=new Json();
					User user=userMapper.selectById(userId);
					if (user == null) {
						json.setCode(109);
						json.setMsg("用户不存在");
						json.setData(null);
						log.error("用户不存在");
						return json;
					}
					boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
					if(!isTrue){
						json.setCode(200);
						json.setData(null);
						log.error("无效的token");
						return json;
					}else
					{
						json.setCode(100);
					}
					return json;
			 }



			@Override
			public Json getBalance(Integer userId, String token) {
				User user = userMapper.selectById(userId);
				Json json=new Json();
				if (user == null) {
					json.setCode(109);
					json.setMsg("用户不存在");
					json.setData(null);
					log.error("用户不存在");
					return json;
				}
				
				boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
				if(!isTrue){
					json.setCode(200);
					json.setData(null);
					log.error("无效的token");
					return json;
				}
				
				UserWalletVO userWalletVO=userWalletMapper.getUserWallet(userId);

				if(userWalletVO!=null)
				{
					json.setCode(100);
					json.setData(userWalletVO);
				}else
				{
					json.setCode(112);
				}
				
				return json;
				
			}



			@Override
			public Json uploadUserInfo(Integer userId, String token, String nickname, String userIcon) {
				Json json=new Json();
					User user=userMapper.selectById(userId);
					if (user == null) {
						json.setCode(109);
						json.setMsg("用户不存在");
						json.setData(null);
						log.error("用户不存在");
						return json;
					}
					boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
					if(!isTrue){
						json.setCode(200);
						json.setData(null);
						log.error("无效的token");
						return json;
					}
					
					user.setNickname(nickname);
					user.setUserIcon(userIcon);
					
					userMapper.updateUserInfo(user);
					json.setCode(100);
					return json;
				
			}

}
