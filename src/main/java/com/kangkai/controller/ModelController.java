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
import com.kangkai.utils.Json;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.ShardedJedisPool;

@Controller
@RequestMapping(value="model")
public class ModelController { 
	@Resource
	private IAddressService addressService;
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	

	/**
	 * 获取用户模型列表
	 * @param data
	 * @return
	 */
	@RequestMapping(value="/getModelList",method = RequestMethod.POST)
	@ResponseBody
	public Json getModelList(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getModelList方法,请求的数据:" + data);
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
		
		
		//json = addressService.getUserAddressList(userId, current, pageSize,token);
		return json;
	}
	
	/**
	 * 上传三维数据生成模型编号
	 * @param data
	 * @return
	 */
	@RequestMapping(value="/uploadModelInfo",method = RequestMethod.POST)
	@ResponseBody
	public Json uploadModelInfo(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行uploadModelInfo方法,请求的数据:" + data);
		Integer userId;
		Integer current;
		Integer pageSize;
		String token;
		String name;
		String phone;
		String province;
		String city;
		String district;
		String detailAddr;
		JSONObject bodyData;
		JSONObject faceData;
		try {
			userId = data.getInt("userId");
			token=data.getString("token");
			bodyData=data.getJSONObject("bodyData");
			faceData=data.getJSONObject("faceData");
			
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		//json = addressService.addUserAddress(userId,token,name,phone,province,city,district,detailAddr);
		return json;
	}
	/**
	 * 删除模型
	 * @param data
	 * @return
	 */
	@RequestMapping(value="/deleteModel",method = RequestMethod.POST)
	@ResponseBody
	public Json deleteModel(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行deleteModel方法,请求的数据:" + data);
		Integer userId;
		String token;
		Integer modelId;
		try {
			userId = data.getInt("userId");
			token=data.getString("token");
			modelId=data.getInt("modelId");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		//json = addressService.deleteUserAddress(userId,token,addressId);
		return json;
	}

	
}
