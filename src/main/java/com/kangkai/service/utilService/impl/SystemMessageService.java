package com.kangkai.service.utilService.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.mapper.app.ProductOrderMapper;
import com.kangkai.mapper.app.SurveyorMapper;
import com.kangkai.mapper.app.SurveyorOrderMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.mapper.util.SystemMessageMapper;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.SurveyorOrder;
import com.kangkai.pojo.SurveyorSystemMessage;
import com.kangkai.pojo.User;
import com.kangkai.pojo.UserSystemMessage;
import com.kangkai.service.utilService.ISystemMessageService;
import com.kangkai.utils.Constants;
import com.kangkai.utils.GetuiUtil;
import com.kangkai.utils.Json;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;
import com.kangkai.vo.BuyProductDetailVO;
import com.kangkai.vo.UserProductVO;

@Service(value="/SystemMessageService")
@Transactional
public class SystemMessageService implements ISystemMessageService{
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());
	@Resource 
	private SystemMessageMapper systemMessageMapper;
	@Resource
	private ProductOrderMapper productOrderMapper;
	@Resource
	private SurveyorOrderMapper surveyorOrderMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private SurveyorMapper surveyorMapper;
	@Override
	public void sendProductSystemMessage(Integer productOrderId,Integer state)
	{
		ProductOrder productOrder=productOrderMapper.selectById(productOrderId);
		String message="订单号："+productOrder.getProductOrderNum()+","+Constants.PRODUCTMESSAGE[state];
		
		int userId=productOrder.getUserId();
		String tableName=checkAndCreateTable(userId,0);
		
		Map<String,Object> userSystemMessage=new HashMap<String,Object>();
		userSystemMessage.put("content",message);
		userSystemMessage.put("productOrderId",productOrderId);
		userSystemMessage.put("userId",userId);
		userSystemMessage.put("type",Constants.PRODUCTORDERMESSAGETYPE.productOrderMessage.value());
		userSystemMessage.put("tableName", tableName);
		systemMessageMapper.insertUserSystemMessage(userSystemMessage);
		
		try
		{
		GetuiUtil.pushProductOrderMessageToSomeBody("您有新的商品订单信息", message, "user"+productOrder.getUserId(), productOrderId);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void sendSurveyorSystemMessage(Integer surveyorOrderId,Integer state)
	{
		SurveyorOrder surveyorOrder=surveyorOrderMapper.selectById(surveyorOrderId);
		String userMessage="订单号："+surveyorOrder.getSurveyorOrderNum()+","+Constants.USERSURVEYORMESSAGE[state];
		String surveyorMessage="订单号："+surveyorOrder.getSurveyorOrderNum()+","+Constants.SURVEYORSURVEYORMESSAGE[state];
		
		int userId=surveyorOrder.getUserId();
		int surveyorId=surveyorOrder.getSurveyorId();
		String tableName=checkAndCreateTable(userId,0);
		
		Map<String,Object> userSystemMessage=new HashMap<String,Object>();
		userSystemMessage.put("content",userMessage);
		userSystemMessage.put("surveyorOrderId",surveyorOrderId);
		userSystemMessage.put("userId",userId);
		userSystemMessage.put("type",Constants.PRODUCTORDERMESSAGETYPE.surveyorOrderMessage);
		userSystemMessage.put("tableName", tableName);
		systemMessageMapper.insertUserSystemMessage(userSystemMessage);
		
		//TODO 消息推送
		
		
		tableName=checkAndCreateTable(surveyorId,1);
		
		Map<String,Object> surveyorSystemMessage=new HashMap<String,Object>();
		surveyorSystemMessage.put("content",surveyorMessage);
		surveyorSystemMessage.put("surveyorOrderId",surveyorOrderId);
		surveyorSystemMessage.put("surveyorId",surveyorId);
		surveyorSystemMessage.put("type",0);
		surveyorSystemMessage.put("tableName", tableName);
		systemMessageMapper.insertSurveyorSystemMessage(surveyorSystemMessage);
		
		//TODO 消息推送
	}
	
	private String checkAndCreateTable(int userId,int type)
	{
		String tableName="";
		if(type==0)
		{
			tableName=getTableName(userId,type);
			Integer result=systemMessageMapper.existTable(tableName);
			if(result<1)
			{
				systemMessageMapper.createUserSystemMessageTable(tableName);
				systemMessageMapper.alterUserSystemMessageTable(tableName);
			}
		}
		if(type==1)
		{
			tableName=getTableName(userId,type);
			Integer result=systemMessageMapper.existTable(tableName);
			if(result<1)
			{
				systemMessageMapper.createSurveyorSystemMessageTable(tableName);
				systemMessageMapper.alterSurveyorSystemMessageTable(tableName);
			}
		}
		return tableName;
	}
	private String getTableName(int userId,int type)
	{
		String number=""+(userId/100);
		String tableName="";
		if(type==0)
		{
			tableName="sys_user_system_message"+number;
		}else
		{
			tableName="sys_surveyor_system_message"+number;
		}
		return tableName;
	}
	@Override
	public Json getUserSystemMessageList(Integer userId, Integer current, Integer pageSize, String token) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		String tableName=this.getTableName(userId, 0);
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		map.put("userId", userId);
		map.put("tableName",tableName);
		List<UserSystemMessage> userSystemMessage=systemMessageMapper.selectUserSystemMessageListByUser(map);
		if(userSystemMessage!=null)
		{
			json.setCode(100);
			json.setData(userSystemMessage);
		}else
		{
			
			json.setCode(112);
		}
 
		
		return json;
	}
	@Override
	public Json getSurveyorSystemMessageList(Integer surveyorId, Integer current, Integer pageSize, String token) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkTokenForSurveyor(surveyorId,surveyorMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}

		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		String tableName=this.getTableName(surveyorId, 1);
		map.put("surveyorId", surveyorId);
		map.put("tableName", tableName);
		List<SurveyorSystemMessage> surveyorSystemMessage=systemMessageMapper.selectSurveyorSystemMessageListBySurveyor(map);
		if(surveyorSystemMessage!=null)
		{
			json.setCode(100);
			json.setData(surveyorSystemMessage);
		}else
		{
			
			json.setCode(112);
		}
 
		
		return json;
	}
	@Override
	public Json getUserMessageDetail(Integer userId, String token, Integer userSystemMessageId) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		String tableName=this.getTableName(userId, 0);
		map.put("userSystemMessageId", userSystemMessageId);
		map.put("tableName", tableName);
		map.put("isRead", Constants.IS_USE_NO);
		
		UserSystemMessage userSystemMessage=systemMessageMapper.selectUserSystemMessageById(map);
		if(userSystemMessage!=null)
		{
			
			if(userSystemMessage.getIsRead().equals(0))
			{
				systemMessageMapper.updateUserSystemMessageIsRead(map);
					
			}
			
			json.setCode(100);
			json.setData(userSystemMessage);
		}else
		{
			json.setCode(112);
		}
 
		
		return json;
	}
}
