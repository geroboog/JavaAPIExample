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
import com.kangkai.mapper.app.UserInfoMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.pojo.Address;
import com.kangkai.pojo.Surveyor;
import com.kangkai.pojo.SurveyorOrder;
import com.kangkai.pojo.User;
import com.kangkai.pojo.UserInfo;
import com.kangkai.service.appService.ISurveyorOrderService;
import com.kangkai.service.appService.ISurveyorService;
import com.kangkai.service.appService.IUserService;
import com.kangkai.service.utilService.IOrderNumService;
import com.kangkai.service.utilService.impl.OrderNumService;
import com.kangkai.vo.IMUser;
import com.kangkai.vo.SurveyorVO;
import com.kangkai.vo.UserClothesVO;
import com.kangkai.vo.UserSurveyorOrderVO;
import com.kangkai.vo.UserVO;
import com.taobao.api.ApiException;

import net.sf.json.JSONObject;

@Service(value="/surveyorOrderService")
@Transactional
public class SurveyorOrderService implements ISurveyorOrderService{
	@Resource
	private UserMapper userMapper;
	@Resource
	private SurveyorOrderMapper surveyorOrderMapper;
	@Resource
	private SurveyorMapper surveyorMapper;
	@Resource
	private AddressMapper addressMapper;
	@Resource
	private IOrderNumService orderNumService;
	//日志记录
		private Log log = LogFactory.getLog(this.getClass());

		@Override
		public Json getSurveyorOrderList(Integer userId, Integer state, String token, Integer current,
				Integer pageSize) {
			Json json=new Json();
			
			boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
			if(!isTrue){
				json.setCode(200);
				json.setData(null);
				log.error("无效的token");
				return json;
			}
			Map<String,Object> map=PageUtil.getMap(current, pageSize);
			map.put("userId", userId);
			map.put("state", state);
			List<UserSurveyorOrderVO> surveyorList=surveyorOrderMapper.selectSurveyorOrderByUserId(map);
			if(surveyorList!=null)
			{
				json.setCode(100);
				json.setData(surveyorList);
			}else
			{
				json.setData(surveyorList);	
			}
			return json;
		}

		@Override
		public Json releaseSurveyorOrder(Integer userId, String token, Integer addressId,Date bookTime) {
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
			SurveyorOrder surveyorOrderC=surveyorOrderMapper.selectLastSurveyorOrder(map);
			if(surveyorOrderC.getState()>Constants.SURVEYOR_SERVEING_STATE)
			{
			//接下来对地址进行处理
			Address addressVO=addressMapper.selectById(addressId);
			String surveyorOrderNum=orderNumService.getSurveyorOrderNum(userId);
			SurveyorOrder surveyorOrder=new SurveyorOrder();	
			surveyorOrder.setAddress(addressVO.getDetailAddr());
			surveyorOrder.setToProvince(addressVO.getProvince());
			surveyorOrder.setToCity(addressVO.getCity());
			surveyorOrder.setRecipient(addressVO.getName());
			surveyorOrder.setPhone(addressVO.getPhone());
			surveyorOrder.setToDistrict(addressVO.getDistrict());
			surveyorOrder.setSurveyorOrderNum(surveyorOrderNum);
			surveyorOrder.setBookTime(bookTime);
			surveyorOrder.setUserId(userId);
			
			surveyorOrderMapper.insertSurveyorOrder(surveyorOrder);
			
			json.setCode(100);
			}else
			{
				json.setCode(118);
			}
			return json;
		}

		@Override
		public Json getSurveyorOrderListForSurveyor(Integer surveyorId, String token,Integer state , Integer current,
				Integer pageSize) {
			Json json=new Json();
			
			boolean isTrue=TokenUtil.checkTokenForSurveyor(surveyorId,surveyorMapper, token);
			if(!isTrue){
				json.setCode(200);
				json.setData(null);
				log.error("无效的token");
				return json;
			}
			Map<String,Object> map=PageUtil.getMap(current, pageSize);
			map.put("surveyorId", surveyorId);
			map.put("state", state);
			List<UserSurveyorOrderVO> surveyorList=surveyorOrderMapper.selectSurveyorOrderBySurveyorId(map);
			if(surveyorList!=null)
			{
				json.setCode(100);
				json.setData(surveyorList);
			}else
			{
				json.setData(surveyorList);	
			}
			return json;
		}

		@Override
		public Json startServe(Integer surveyorId, String token, Integer surveyorOrderId) {
			Json json=new Json();
			
			boolean isTrue=TokenUtil.checkTokenForSurveyor(surveyorId,surveyorMapper, token);
			if(!isTrue){
				json.setCode(200);
				json.setData(null);
				log.error("无效的token");
				return json;
			}
			
			SurveyorOrder surveyorOrder=surveyorOrderMapper.selectById(surveyorOrderId);
			if(surveyorOrder.getState().intValue()==Constants.SURVEYOR_NEEDEDSERVE_STATE)
			{
			surveyorOrder.setState(Constants.SURVEYOR_SERVEING_STATE);
			surveyorOrderMapper.updateSurveyorOrderState(surveyorOrder);
			}
			
			return json;
		}
		
		@Override
		public Json finishServe(Integer surveyorId, String token, Integer surveyorOrderId,JSONObject jsonObject) {
			Json json=new Json();
			
			boolean isTrue=TokenUtil.checkTokenForSurveyor(surveyorId,surveyorMapper, token);
			if(!isTrue){
				json.setCode(200);
				json.setData(null);
				log.error("无效的token");
				return json;
			}
			
			SurveyorOrder surveyorOrder=surveyorOrderMapper.selectById(surveyorOrderId);
			if(surveyorOrder.getState().intValue()==Constants.SURVEYOR_SERVEING_STATE)
			{
			surveyorOrder.setState(Constants.SURVEYOR_NEEDEDCOMMENT_STATE);
			surveyorOrderMapper.updateSurveyorOrderState(surveyorOrder);
			}
			
			return json;
		}
		


}
