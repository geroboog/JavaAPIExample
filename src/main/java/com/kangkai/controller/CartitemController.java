package com.kangkai.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangkai.service.appService.ICartitemService;
import com.kangkai.utils.Json;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="cartitem")
public class CartitemController {
	
	@Resource
	private ICartitemService cartitemService;
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	


	
	@RequestMapping(value="/getUserCartitem",method = RequestMethod.POST)
	@ResponseBody
	public Json getUserCartitem(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getUserCartitem方法,请求的数据:" + data);
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
		
		
		json = cartitemService.getUserCartitem(userId, current, pageSize,token);
		return json;
	}
	
	@RequestMapping(value="/addUserCartitemProduct",method = RequestMethod.POST)
	@ResponseBody
	public Json addUserCartitemProduct(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getUserCartitem方法,请求的数据:" + data);
		Integer userId;
		Integer productId;
		Integer current;
		Integer pageSize;
		Integer count;
		String attrs;
		String token;
		try {
			userId = data.getInt("userId");
			productId=data.getInt("productId");
			attrs=data.getString("attrs");
			count=data.getInt("count");
			token=data.getString("token");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = cartitemService.addUserCartitemProduct(userId,productId,attrs,token);
		return json;
	}
	
	
	@RequestMapping(value="/updateUserCartitemProduct",method = RequestMethod.POST)
	@ResponseBody
	public Json updateUserCartitemProduct(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getUserCartitem方法,请求的数据:" + data);
		Integer userId;
		Integer productId;
		Integer current;
		Integer pageSize;
		Integer count;
		String attrs;
		String token;
		try {
			userId = data.getInt("userId");
			productId=data.getInt("productId");
			attrs=data.getString("attrs");
			count=data.getInt("count");
			token=data.getString("token");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = cartitemService.updateUserCartitemProduct(userId,productId,attrs,count,token);
		return json;
	}
	
	
	
	
	
}
