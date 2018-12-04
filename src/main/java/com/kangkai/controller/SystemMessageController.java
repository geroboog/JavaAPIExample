package com.kangkai.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangkai.pojo.User;
import com.kangkai.service.appService.IAddressService;
import com.kangkai.service.appService.ICategoryService;
import com.kangkai.service.appService.ISalesService;
import com.kangkai.service.utilService.ISystemMessageService;
import com.kangkai.utils.Json;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.ShardedJedisPool;

@Controller
@RequestMapping(value="systemMessage")
public class SystemMessageController { 
	@Resource
	private ISystemMessageService systemMessageService;
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	

	
	@RequestMapping(value="/getUserSystemMessageList",method = RequestMethod.POST)
	@ResponseBody
	public Json getUserSystemMessageList(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getUserSystemMessageList方法,请求的数据:" + data);
		Integer userId;
		Integer current;
		Integer pageSize;
		String token;
		try {
			userId = data.getInt("userId");
			current = data.getInt("current");
			pageSize=data.getInt("pageSize");
			token=data.getString("token");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = systemMessageService.getUserSystemMessageList(userId, current, pageSize,token);
		return json;
	}
	
	@RequestMapping(value="/getUserMessageDetail",method = RequestMethod.POST)
	@ResponseBody
	public Json getUserMessageDetail(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getUserMessageDetail方法,请求的数据:" + data);
		Integer userId;
		Integer userSystemMessageId;
		String token;
		try {
			userId = data.getInt("userId");
			userSystemMessageId = data.getInt("userSystemMessageId");
			token=data.getString("token");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = systemMessageService.getUserMessageDetail(userId,token, userSystemMessageId);
		return json;
	}
	
	@RequestMapping(value="/getSurveyorSystemMessageList",method = RequestMethod.POST)
	@ResponseBody
	public Json getSurveyorSystemMessageList(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getSurveyorSystemMessageList方法,请求的数据:" + data);
		Integer surveyorId;
		Integer current;
		Integer pageSize;
		String token;
		try {
			surveyorId = data.getInt("surveyorId");
			current = data.getInt("current");
			pageSize=data.getInt("pageSize");
			token=data.getString("token");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = systemMessageService.getSurveyorSystemMessageList(surveyorId, current, pageSize,token);
		return json;
	}
	
}
