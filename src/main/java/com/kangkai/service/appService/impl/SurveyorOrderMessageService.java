package com.kangkai.service.appService.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.utils.Constants;
import com.kangkai.utils.Encrypt;
import com.kangkai.utils.ImTokenUtils;
import com.kangkai.utils.Json;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.SDKTestSendTemplateSMS;
import com.kangkai.utils.SendVcodeUtil;
import com.kangkai.utils.TokenUtil;
import com.kangkai.exception.TokenInvalidException;
import com.kangkai.mapper.app.AddressMapper;
import com.kangkai.mapper.app.SurveyorMapper;
import com.kangkai.mapper.app.SurveyorOrderMapper;
import com.kangkai.mapper.app.SurveyorOrderMessageMapper;
import com.kangkai.mapper.app.UserInfoMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.pojo.Address;
import com.kangkai.pojo.ProductOrderMessage;
import com.kangkai.pojo.Surveyor;
import com.kangkai.pojo.SurveyorOrder;
import com.kangkai.pojo.SurveyorOrderMessage;
import com.kangkai.pojo.User;
import com.kangkai.pojo.UserInfo;
import com.kangkai.service.appService.ISurveyorOrderMessageService;
import com.kangkai.service.appService.ISurveyorOrderService;
import com.kangkai.service.appService.ISurveyorService;
import com.kangkai.service.appService.IUserService;
import com.kangkai.service.utilService.IOrderNumService;
import com.kangkai.service.utilService.impl.OrderNumService;
import com.kangkai.vo.IMUser;
import com.kangkai.vo.SurveyorVO;
import com.kangkai.vo.UserClothesVO;
import com.kangkai.vo.UserProductOrderMessageVO;
import com.kangkai.vo.UserSurveyorOrderMessageVO;
import com.kangkai.vo.UserSurveyorOrderVO;
import com.kangkai.vo.UserVO;
import com.taobao.api.ApiException;

@Service(value="/surveyorOrderMessageService")
@Transactional
public class SurveyorOrderMessageService implements ISurveyorOrderMessageService{

	@Resource
	private UserMapper userMapper;
	@Resource
	private SurveyorOrderMessageMapper surveyorOrderMessageMapper;
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public Json getSurveyorOrderMessage(Integer userId, String token) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}

		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		List<UserSurveyorOrderMessageVO> userSurveyorOrderMessageVO=surveyorOrderMessageMapper.selectSurveyorOrderMessage(map);
		
		if(userSurveyorOrderMessageVO==null||userSurveyorOrderMessageVO.size()<1)
		{
			surveyorOrderMessageMapper.insertSurveyorOrderMessage(map);
			userSurveyorOrderMessageVO=surveyorOrderMessageMapper.selectSurveyorOrderMessage(map);
		}
		json.setCode(100);
		json.setData(userSurveyorOrderMessageVO);
		return json;
	}

	@Override
	public Integer updateSurveyorOrderMessage(Integer userId, Integer state, Integer type) {
		int check=0;
		Integer result=null;
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("state", state);
		map.put("type", type);
		if(type==0)
		{
			SurveyorOrderMessage surveyorOrderMessage=surveyorOrderMessageMapper.selectSurveyorOrderMessageOne(map);
			if(surveyorOrderMessage!=null)
			{
				if(surveyorOrderMessage.getNum()>0)
				{
					check=1;
				}
			}
		}else
		{
			check=1;
		}
		if(check==1)
		{
			result=surveyorOrderMessageMapper.updateSurveyorOrderMessage(map);
		}
		return result;
	}
	

}
