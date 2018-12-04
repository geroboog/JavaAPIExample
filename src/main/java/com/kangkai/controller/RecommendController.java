package com.kangkai.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangkai.pojo.User;
import com.kangkai.service.appService.IAddressService;
import com.kangkai.service.appService.ICategoryService;
import com.kangkai.service.appService.ICouponService;
import com.kangkai.service.appService.IProductService;
import com.kangkai.service.appService.IRecommendService;
import com.kangkai.service.appService.ISalesService;
import com.kangkai.service.appService.IUserService;
import com.kangkai.utils.Json;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.ShardedJedisPool;

@Controller
@RequestMapping(value="recommend")
public class RecommendController { 
	
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	@Resource
	private IRecommendService recommendService;
	@Resource
	private IUserService userService;
	//点击链接分发红包给用户
	
	@RequestMapping(value="/recommendCoupon")
	@ResponseBody
	public Json recommendCoupon(Integer redPacketId,String phone){
			Json json = new Json();
			log.info("请求执行recommendCoupon方法,请求的数据:" + phone+":"+redPacketId);
					
				json = recommendService.recommendCoupon(phone,redPacketId);
				return json;
			}
	
	
	//分享红包
	@RequestMapping(value="/shareRedPacket",method = RequestMethod.POST)
	@ResponseBody
	public Json shareRedPacket(@RequestBody JSONObject data){
			log.info("请求执行shareRedPacket方法,请求的数据:" + data);
			Integer productOrderId;
			Integer userId;
			String token;
			Json json=new Json();
			try {
				userId=data.getInt("userId");
				productOrderId = data.getInt("productOrderId");
				token=data.getString("token");
			} catch (Exception e) {
				json.setCode(0);
				return json;
			}
				
			 json = recommendService.shareRedPacket(userId,token,productOrderId);
			
			return json;
	}
	
	//分享红包
	@RequestMapping(value="/shareRecommend",method = RequestMethod.POST)
	@ResponseBody
	public Json shareRecommend(@RequestBody JSONObject data)
	{
				log.info("请求执行shareRecommend方法,请求的数据:" + data);
				Integer productOrderId;
				Integer userId;
				String token;
				Json json=new Json();
				try {
					userId=data.getInt("userId");
					token=data.getString("token");
				} catch (Exception e) {
					json.setCode(0);
					return json;
				}
				json=userService.validateToken(userId, token);
				if(json.getCode()==100)
				{
					json = recommendService.shareRecommend(userId);	
				}
				return json;
	}
	//分享红包
	@RequestMapping(value="/getShareRecommendCode",method = RequestMethod.POST)
	@ResponseBody
	public Json getShareRecommendCode(@RequestBody JSONObject data,HttpServletRequest request)
	{
				log.info("请求执行getShareRecommendCode方法,请求的数据:" + data);
				Integer userId;
				String token;
				Json json=new Json();
				try {
					userId=data.getInt("userId");
					token=data.getString("token");
				} catch (Exception e) {
					json.setCode(0);
					return json;
				}
				json=userService.validateToken(userId, token);
				if(json.getCode()==100)
				{
					json = recommendService.getShareRecommendCode(userId,request);
					
				}
				
				return json;
	}
	
	//获取上下线关系人数
		@RequestMapping(value="/getRecommendUserNum",method = RequestMethod.POST)
		@ResponseBody
		public Json getRecommendUserNum(@RequestBody JSONObject data,HttpServletRequest request)
		{
					log.info("请求执行getRecommendUserNum方法,请求的数据:" + data);
					Integer userId;
					String token;
					Json json=new Json();
					try {
						userId=data.getInt("userId");
						token=data.getString("token");
					} catch (Exception e) {
						json.setCode(0);
						return json;
					}
					json=userService.validateToken(userId, token);
					if(json.getCode()==100)
					{
						json = recommendService.getRecommendUserNum(userId);
						
					}
					
					return json;
		}
		
	//进入红包页面
	@RequestMapping(value="/recommendRedPacket")
	public String recommendRedPacket(Integer redPacketId,ModelMap map){
		map.put("redPacketId", redPacketId);
		return "recommendRedPacket";
	}
	
	//进入分销注册页面
	@RequestMapping(value="/recommend")
	public String recommend(Integer userId,ModelMap map){
		map.put("userId", userId);
		return "recommend";
	}
	//注册推荐用户
	@RequestMapping(value="/recommendUser")
	@ResponseBody
	public Json recommendUser(Integer userId,String phone,String vcode,String password,HttpSession session){
		Json json = new Json();
		log.info("请求执行recommendUser方法,请求的数据:" + phone+":"+vcode+":"+password);
		String _vcode = (String) session.getAttribute(phone);
			json = recommendService.recommendUser(userId,phone,vcode,password,_vcode);
			return json;
	}
	
	
}
