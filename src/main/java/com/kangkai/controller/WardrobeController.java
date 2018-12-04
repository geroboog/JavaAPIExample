package com.kangkai.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangkai.SpringAOP.OpLogger;
import com.kangkai.SpringAOP.OpLogger.OpType;
import com.kangkai.service.appService.ICommunityService;
import com.kangkai.service.appService.IWardrobeService;
import com.kangkai.utils.DateUtils;
import com.kangkai.utils.Json;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="wardrobe")
public class WardrobeController {
	@Resource
	private ICommunityService communityService;
	@Resource
	private IWardrobeService wardrobeService;
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	
	//获取根据天气获取衣柜列表
	
	@ResponseBody
	@RequestMapping(value="/getClothesList",method = RequestMethod.POST)
	@OpLogger(id = "getClothesList", type=OpType.SEARCH)
	public Json getClothesList(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getClothesList方法,请求的数据:" + data);
		Integer userId;
		Integer current;
		Integer pageSize;
		String season=null;//spring,summer,autumn,winter
		String token;
		try {
			userId = data.getInt("userId");
			token = data.getString("token");
			current=data.getInt("current");
			pageSize=data.getInt("pageSize");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		try
		{
			season=data.getString("season");
		}catch(Exception e) 
		{
		
		}
		json = wardrobeService.getClothesList(userId,token,season,current,pageSize);
		return json;
	}
	
	//寄存功能
	
		@ResponseBody
		@RequestMapping(value="/sendClothesToShop",method = RequestMethod.POST)
		@OpLogger(id = "sendClothesToShop", type=OpType.SEARCH)
		public Json sendClothesToShop(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行sendClothesToShop方法,请求的数据:" + data);
			Integer userId;
			Integer current;
			Integer pageSize;
			Integer productOrderItemId=null;
			Integer productOrderId=null;
			Integer productId;
			Integer shopId;
			String season=null;//spring,summer,autumn,winter
			String token;
			String phone;
			String remark;
			Date storeTime;
			try {
				userId = data.getInt("userId");
				token = data.getString("token");
				shopId=data.getInt("shopId");
				phone=data.getString("phone");
				remark=data.getString("remark");
				storeTime=DateUtils.parseDatetime(data.getString("storeTime"));
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
			try {
				productOrderItemId=data.getInt("productOrderItemId");
			}catch(Exception ex)
			{
				
			}
			try {
				productOrderId=data.getInt("productOrderId");
			}catch(Exception ex)
			{
				
			}

			json = wardrobeService.sendClothesToShop(userId,token,productOrderItemId,productOrderId,shopId,phone,remark,storeTime);
			return json;
		}
	
	
}
