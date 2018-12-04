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
@RequestMapping(value="address")
public class AddressController { 
	@Resource
	private IAddressService addressService;
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	

	
	@RequestMapping(value="/getUserAddressList",method = RequestMethod.POST)
	@ResponseBody
	public Json getUserAddressList(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getUserAddressList方法,请求的数据:" + data);
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
		
		
		json = addressService.getUserAddressList(userId, current, pageSize,token);
		return json;
	}
	
	@RequestMapping(value="/addUserAddress",method = RequestMethod.POST)
	@ResponseBody
	public Json addUserAddress(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行addUserAddress方法,请求的数据:" + data);
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
		try {
			userId = data.getInt("userId");
			token=data.getString("token");
			name=data.getString("name");
			phone=data.getString("phone");
			province=data.getString("province");
			city=data.getString("city");
			district=data.getString("district");
			detailAddr=data.getString("detailAddr");
			
			
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = addressService.addUserAddress(userId,token,name,phone,province,city,district,detailAddr);
		return json;
	}
	
	@RequestMapping(value="/updateUserAddress",method = RequestMethod.POST)
	@ResponseBody
	public Json updateUserAddress(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行addUserAddress方法,请求的数据:" + data);
		Integer userId;
		Integer current;
		Integer pageSize;
		Integer addressId;
		String token;
		String name;
		String phone;
		String province;
		String city;
		String district;
		String detailAddr;
		try {
			userId = data.getInt("userId");
			token=data.getString("token");
			addressId=data.getInt("addressId");
			name=data.getString("name");
			phone=data.getString("phone");
			province=data.getString("province");
			city=data.getString("city");
			district=data.getString("district");
			detailAddr=data.getString("detailAddr");
			
			
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = addressService.updateUserAddress(userId,token,addressId,name,phone,province,city,district,detailAddr);
		return json;
	}
	
	@RequestMapping(value="/selectDefaultAddress",method = RequestMethod.POST)
	@ResponseBody
	public Json selectDefaultAddress(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行addUserAddress方法,请求的数据:" + data);
		Integer userId;
		Integer current;
		Integer pageSize;
		String token;
		Integer addressId;
		try {
			userId = data.getInt("userId");
			token=data.getString("token");
			addressId=data.getInt("addressId");
			
			
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = addressService.selectDefaultAddress(userId,token,addressId);
		return json;
	}
	
	@RequestMapping(value="/deleteUserAddress",method = RequestMethod.POST)
	@ResponseBody
	public Json deleteUserAddress(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行deleteUserAddress方法,请求的数据:" + data);
		Integer userId;
		Integer current;
		Integer pageSize;
		String token;
		Integer addressId;
		try {
			userId = data.getInt("userId");
			token=data.getString("token");
			addressId=data.getInt("addressId");
			
			
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = addressService.deleteUserAddress(userId,token,addressId);
		return json;
	}
	
	@RequestMapping(value="/getProvinceList",method = RequestMethod.POST)
	@ResponseBody
	public Json getProvinceList(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getProvinceList方法,请求的数据:" + data);
		Integer userId;
		Integer current;
		Integer pageSize;
		String token;
		Integer addressId;
		try {
			current=data.getInt("current");
			pageSize=data.getInt("pageSize");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = addressService.getProvinceList(current,pageSize);
		return json;
	}
	
	@RequestMapping(value="/getCityList",method = RequestMethod.POST)
	@ResponseBody
	public Json getCityList(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getProvinceList方法,请求的数据:" + data);
		Integer userId;
		Integer current;
		Integer pageSize;
		Integer provinceId;
		String token;
		Integer addressId;
		Integer type;
		try {
			current=data.getInt("current");
			pageSize=data.getInt("pageSize");
			provinceId=data.getInt("provinceId");
			type=data.getInt("type");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		json = addressService.getCityList(current,pageSize,provinceId,type);
		return json;
	}
	
	@RequestMapping(value="/getDistrictList",method = RequestMethod.POST)
	@ResponseBody
	public Json getDistrictList(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getProvinceList方法,请求的数据:" + data);
		Integer userId;
		Integer current;
		Integer pageSize;
		Integer cityId;
		String token;
		Integer addressId;
		Integer type;
		try {
			current=data.getInt("current");
			pageSize=data.getInt("pageSize");
			cityId=data.getInt("cityId");
			type=data.getInt("type");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = addressService.getDistrictList(current,pageSize,cityId,type);
		return json;
	}
	/*
	 * 获取用户默认地址
	 */
	@RequestMapping(value="/getUserDefaultAddress",method = RequestMethod.POST)
	@ResponseBody
	public Json getUserDefaultAddress(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getUserDefaultAddress方法,请求的数据:" + data);
		Integer userId;
		Integer current;
		Integer pageSize;
		String token;
		try {
			userId = data.getInt("userId");
			token=data.getString("token");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = addressService.getUserDefaultAddress(userId,token);
		return json;
	}
	
}
