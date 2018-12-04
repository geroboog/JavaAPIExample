package com.kangkai.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangkai.service.appService.ICollectService;
import com.kangkai.utils.Json;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="collect")
public class CollectController {
	

	@Resource
	private ICollectService collectService;
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	


	//收藏商品，第二字点击为取消收藏
	
	@RequestMapping(value="/collectProduct",method = RequestMethod.POST)
	@ResponseBody
	public Json collectProduct(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行collectProduct方法,请求的数据:" + data);
			Integer userId;
			Integer productId;
			String token;
			try {
				userId = data.getInt("userId");
				token=data.getString("token");
				productId = data.getInt("productId");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
			
			json = collectService.collectProduct(userId,token,productId);
			return json;
		}
	
	//获取商品收藏列表
	
	@RequestMapping(value="/getProductCollectList",method = RequestMethod.POST)
	@ResponseBody
	public Json getProductCollectList(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行getProductCollectList方法,请求的数据:" + data);
			Integer userId;
			Integer productId;
			String token;
			Integer current;
			Integer pageSize;
			try {
				userId = data.getInt("userId");
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
					
			json = collectService.getProductCollectList(userId,token,current,pageSize);
			return json;
	}
	
	
	
}
