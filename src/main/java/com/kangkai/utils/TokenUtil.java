package com.kangkai.utils;

import com.kangkai.utils.Encrypt;
import com.kangkai.mapper.app.SurveyorMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.pojo.Surveyor;
import com.kangkai.pojo.User;

public class TokenUtil {
	public static boolean checkToken(Integer userId,UserMapper userMapper,String token){
		//核对token是否一致
		User user=userMapper.selectById(userId);
		String token1=user.getUserId().toString()+"kangkai"+user.getPassword();
		String handleToken=Encrypt.md5AndSha(token1);
		String soureToken=token;
		if(!handleToken.equals(soureToken)){
			return false;
		}else{
			return true;
		}
	}
	public static String getUserToken(User user)
	{
		String systemName = "kangkai";
		String content = user.getUserId().toString()+ systemName+user.getPassword();
		String token=Encrypt.md5AndSha(content);
		return token;
	}
	public static String getSurveyorToken(Surveyor surveyor)
	{
		String systemName = "kangkaiSurveyor";
		String content = surveyor.getSurveyorId().toString()+ systemName+surveyor.getPassword();
		String token=Encrypt.md5AndSha(content);
		return token;
	}

	public static boolean checkTokenForSurveyor(Integer surveyorId, SurveyorMapper surveyorMapper, String token) {
		//核对token是否一致
		Surveyor surveyor=surveyorMapper.selectById(surveyorId);
		String token1=surveyor.getSurveyorId().toString()+"kangkaiSurveyor"+surveyor.getPassword();
		String handleToken=Encrypt.md5AndSha(token1);
		String soureToken=token;
		if(!handleToken.equals(soureToken)){
			return false;
		}else{
			return true;
		}
	}
	
}
