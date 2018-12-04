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
import com.kangkai.mapper.app.ApplySurveyorMapper;
import com.kangkai.mapper.app.SurveyorMapper;
import com.kangkai.mapper.app.SurveyorWalletMapper;
import com.kangkai.mapper.app.UserInfoMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.pojo.ApplySurveyor;
import com.kangkai.pojo.Surveyor;
import com.kangkai.pojo.User;
import com.kangkai.pojo.UserInfo;
import com.kangkai.service.appService.ISurveyorService;
import com.kangkai.service.appService.IUserService;
import com.kangkai.vo.IMUser;
import com.kangkai.vo.SurveyorVO;
import com.kangkai.vo.SurveyorWalletVO;
import com.kangkai.vo.UserVO;
import com.kangkai.vo.UserWalletVO;
import com.taobao.api.ApiException;

@Service(value="/surveyorService")
@Transactional
public class SurveyorService implements ISurveyorService{
	//日志记录
		private Log log = LogFactory.getLog(this.getClass());
		@Resource
		private SurveyorMapper surveyorMapper;
		@Resource
		private UserMapper userMapper;
		@Resource
		private ApplySurveyorMapper applySurveyorMapper;
		@Resource
		private SurveyorWalletMapper surveyorWalletMapper;
		//登陆
		@Override
		public Json login(String username, String password) {
			Json json = new Json();
			Surveyor surveyor = surveyorMapper.selectByPhone(username);
			if(surveyor==null)
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
			if (!surveyor.getPassword().equals(md5AndSha_password)) {
				json.setCode(106);
				log.error("密码错误");
				return json;
			}
			String token=TokenUtil.getSurveyorToken(surveyor);
			SurveyorVO surveyorvo=new SurveyorVO();
			surveyorvo.setNickname(surveyor.getNickname());
			surveyorvo.setPhone(surveyor.getPhone());
			surveyorvo.setToken(token);
			surveyorvo.setSurveyorId(surveyor.getSurveyorId());
			surveyorvo.setUsername(surveyor.getUsername());
			surveyorvo.setUserIcon(surveyor.getUserIcon());
			surveyorvo.setGender(surveyor.getGender());
			//userVO.setToken(token);
			json.setCode(100);
			json.setMsg("登录成功");
			json.setData(surveyorvo);
			return json;
		}



		@Override
		public Json getUserInfo(Integer userId) {
			// TODO Auto-generated method stub
			return null;
		}
			
		@Override
		public Json applyForSurveyor(Integer userId, String token,String city,String gender,String name,String img,String phone,String province) 
		{
			Json json=new Json();
			
			boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
			if(!isTrue){
				json.setCode(200);
				json.setData(null);
				log.error("无效的token");
				return json;
			}
			ApplySurveyor applySurveyor=new ApplySurveyor();
			applySurveyor.setCity(city);
			applySurveyor.setGender(gender);
			applySurveyor.setImg(img);
			applySurveyor.setName(name);
			applySurveyor.setPhone(phone);
			applySurveyor.setProvince(province);
			applySurveyor.setUserId(userId);
			applySurveyorMapper.insert(applySurveyor);
			json.setCode(100);
			return json;
		}



		@Override
		public Json getSurveyorBalance(Integer userId,String token) {
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
			
			SurveyorWalletVO surveyorWalletVO=surveyorWalletMapper.getSurveyorWallet(userId);

			if(surveyorWalletVO!=null)
			{
				json.setCode(100);
				json.setData(surveyorWalletVO);
			}else
			{
				json.setCode(112);
			}
			
			return json;
		}
		
			
			

}
