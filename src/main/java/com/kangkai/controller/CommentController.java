package com.kangkai.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangkai.service.appService.IProductCommentService;
import com.kangkai.utils.Json;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="comment")
public class CommentController {
	
	@Resource
	private IProductCommentService productCommentService;

	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	
	
	@RequestMapping(value="/commentProduct",method = RequestMethod.POST)
	@ResponseBody
	public Json commentProduct(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行commentProduct方法,请求的数据:" + data);
		Integer userId;
		String token;
		String content;
		Double evaluation;
		Integer productOrderId;
		try {
			userId = data.getInt("userId");
			token=data.getString("token");
			productOrderId=data.getInt("productOrderId");
			content=data.getString("content");
			evaluation=data.getDouble("evaluation");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = productCommentService.commentProduct(userId,token,productOrderId,content,evaluation);
	
		return json;
	}
	
	@RequestMapping(value="/getProductCommentList",method = RequestMethod.POST)
	@ResponseBody
	public Json getProductCommentList(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getProductCommentList方法,请求的数据:" + data);
		Integer userId;
		String token;
		Integer current;
		Integer pageSize;
		Integer productId;
		try {
			userId = data.getInt("userId");
			token=data.getString("token");
			productId=data.getInt("productId");
			current=data.getInt("current");
			pageSize=data.getInt("pageSize");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = productCommentService.getProductCommentList(userId, token, productId, current, pageSize);
	
		return json;
	}
	
	
	
}
