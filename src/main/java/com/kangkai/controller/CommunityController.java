package com.kangkai.controller;

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
import com.kangkai.utils.Json;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="community")
public class CommunityController {
	@Resource
	private ICommunityService communityService;
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	
	//获取用户圈子基本信息
	
	@ResponseBody
	@RequestMapping(value="/getUserCommunityInfo",method = RequestMethod.POST)
	@OpLogger(id = "getUserCommunityInfo", type=OpType.SEARCH)
	public Json getUserCommunityInfo(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getUserCommunityInfo方法,请求的数据:" + data);
		Integer userId;
		String token="";
		try {
			userId = data.getInt("userId");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
					
		json = communityService.getUserCommunityInfo(userId,token);
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="/getCommunityInfo",method = RequestMethod.POST)
	@OpLogger(id = "getCommunityInfo", type=OpType.SEARCH)
	public Json getCommunityInfo(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getCommunityInfo方法,请求的数据:" + data);
		Integer communityId;
		Integer userId=null;
		try {
			communityId = data.getInt("communityId");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		try {
			userId = data.getInt("userId");
		} catch (Exception e)
		{
		
		}
		json = communityService.getCommunityInfo(communityId,userId);
		return json;
	}
	
	//获取圈子列表
	
	@RequestMapping(value="/getCommunityList",method = RequestMethod.POST)
	@ResponseBody
	public Json getCommunityList(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getCommunityList方法,请求的数据:" + data);
		Integer current;
		Integer pageSize;
		Integer isHot=null;
		try {
			current = data.getInt("current");
			pageSize = data.getInt("pageSize");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		try {
			isHot = data.getInt("isHot");
		} catch (Exception e) {
		
		}
		json = communityService.getCommunityList(isHot,current,pageSize);
		return json;
	}
	//获取用户关注圈子列表
	
	@RequestMapping(value="/getCommunityFollowList",method = RequestMethod.POST)
	@ResponseBody
	public Json getCommunityFollowList(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getCommunityFollowList方法,请求的数据:" + data);
		Integer current;
		Integer pageSize;
		Integer userId;
		String token;
		try {
			userId = data.getInt("userId");
			token = data.getString("token");
			current = data.getInt("current");
			pageSize = data.getInt("pageSize");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		json = communityService.getCommunityFollowList(userId,token,current,pageSize);
		return json;
	}
	//获取圈子首页贴子列表/获取个人帖子列表/获取某圈子中的帖子列表
	
	@RequestMapping(value="/getCommunityTopicList",method = RequestMethod.POST)
	@ResponseBody
	public Json getCommunityTopicList(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getCommunityTopicList方法,请求的数据:" + data);
		Integer current;
		Integer pageSize;
		Integer communityId=null;
		Integer userId=null;
		try {
			current = data.getInt("current");
			pageSize = data.getInt("pageSize");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		try
		{
			communityId=data.getInt("communityId");
		}catch(Exception e)
		{
			
		}
		try
		{
			userId=data.getInt("userId");
		}catch(Exception e)
		{
			
		}
			json = communityService.getCommunityTopicList(communityId,userId,current,pageSize);
			return json;
	}
	
	//获取圈子首页贴子列表/获取个人帖子列表/获取某圈子中的帖子列表
	
		@RequestMapping(value="/getCommunityFollowTopicList",method = RequestMethod.POST)
		@ResponseBody
		public Json getCommunityFollowTopicList(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行getCommunityFollowTopicList方法,请求的数据:" + data);
			Integer current;
			Integer pageSize;
			Integer communityId=null;
			Integer userId=null;
			String token;
			try {
				userId=data.getInt("userId");
				token=data.getString("token");
				current = data.getInt("current");
				pageSize = data.getInt("pageSize");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
			
				json = communityService.getCommunityTopicFollowList(userId,token,current,pageSize);
				return json;
		}
	//获取圈子帖子详情
	
		@RequestMapping(value="/getCommunityTopicDetail",method = RequestMethod.POST)
		@ResponseBody
		public Json getCommunityTopicDetail(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行getCommunityTopicDetail方法,请求的数据:" + data);
			Integer communityTopicId;
			try {
				communityTopicId=data.getInt("communityTopicId");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
						
				json = communityService.getCommunityTopicDetail(communityTopicId);
				return json;
		}
	
	//获取圈子帖子评论列表
	
		@RequestMapping(value="/getCommunityTopicCommentList",method = RequestMethod.POST)
		@ResponseBody
		public Json getCommunityTopicCommentList(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行getCommunityTopicCommentList方法,请求的数据:" + data);
			Integer current;
			Integer pageSize;
			Integer communityTopicId;
			String title;
			try {
				communityTopicId=data.getInt("communityTopicId");
				current = data.getInt("current");
				pageSize = data.getInt("pageSize");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
						
				json = communityService.getCommunityTopicCommentList(communityTopicId,current,pageSize);
				return json;
		}
		
		//发表圈子帖子
		
		@RequestMapping(value="/releaseCommunityTopic",method = RequestMethod.POST)
		@ResponseBody
		@OpLogger(id = "releaseCommunityTopic", type=OpType.SEARCH)
		public Json releaseCommunityTopic(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行releaseCommunityTopic方法,请求的数据:" + data);
			Integer productId;
			Integer productOrderItemId;
			Integer pageSize;
			Integer communityId;
			Integer userId;
			String content;
			String token;
			String title;
			try {
				communityId=data.getInt("communityId");
				productOrderItemId = data.getInt("productOrderItemId");
				title=data.getString("title");
				content=data.getString("content");
				userId = data.getInt("userId");
				token = data.getString("token");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
			
				json = communityService.releaseCommunityTopic(communityId,productOrderItemId,userId,token,title,content);
				return json;
		}
	//评论圈子帖子
		
		@RequestMapping(value="/commentCommunityTopic",method = RequestMethod.POST)
		@ResponseBody
		@OpLogger(id = "commentCommunityTopic", type=OpType.SEARCH)
		public Json commentCommunityTopic(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行commentCommunityTopic方法,请求的数据:" + data);
			Integer productId;
			Integer productOrderId;
			Integer pageSize;
			Integer communityTopicId;
			Integer userId;
			String content;
			String token;
			try {
				communityTopicId=data.getInt("communityTopicId");
				content=data.getString("content");
				userId = data.getInt("userId");
				token = data.getString("token");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
						
				json = communityService.commentCommunityTopic(userId,token,communityTopicId,content);
				return json;
		}
		
		//关注圈子
		
		@RequestMapping(value="/followCommunity",method = RequestMethod.POST)
		@ResponseBody
		public Json followCommunity(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行followCommunity方法,请求的数据:" + data);
			Integer productId;
			Integer productOrderId;
			Integer pageSize;
			Integer communityId;
			Integer userId;
			String token;
			try {
				communityId=data.getInt("communityId");
				userId = data.getInt("userId");
				token = data.getString("token");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
								
				json = communityService.followCommunity(communityId,userId,token);
				return json;
			}

		//评论圈子帖子
		
		@RequestMapping(value="/getProductCommunityTopicList",method = RequestMethod.POST)
		@ResponseBody
		@OpLogger(id = "getProductCommunityTopicList", type=OpType.SEARCH)
		public Json getProductCommunityTopicList(@RequestBody JSONObject data){
				Json json = new Json();
				log.info("请求执行getProductCommunityTopicList方法,请求的数据:" + data);
				Integer productId;
				Integer current;
				Integer pageSize;
				Integer userId;
				String token;
				try {
					productId=data.getInt("productId");
					userId = data.getInt("userId");
					token = data.getString("token");
					current = data.getInt("current");
					pageSize = data.getInt("pageSize");
				} catch (Exception e) {
					json.setCode(0);
					json.setMsg("请求的参数不合法");
					json.setData(null);
					log.error("请求的参数不合法，错误信息:" + e.getMessage());
					return json;
				}
								
				json = communityService.getProductCommunityTopicList(userId,token,productId,current,pageSize);
				return json;
		}
		
		
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
			json = communityService.getClothesList(userId,token,season,current,pageSize);
			return json;
		}
}
