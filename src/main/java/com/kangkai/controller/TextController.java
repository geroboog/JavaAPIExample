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

import com.kangkai.SpringAOP.OpLogger;
import com.kangkai.SpringAOP.OpLogger.OpType;
import com.kangkai.pojo.User;
import com.kangkai.service.appService.IAddressService;
import com.kangkai.service.appService.ICategoryService;
import com.kangkai.service.appService.ISalesService;
import com.kangkai.service.utilService.ITextService;
import com.kangkai.utils.Json;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.ShardedJedisPool;

@Controller
@RequestMapping(value="text")
public class TextController { 
	@Resource
	private ITextService textService;
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	

	
	@ResponseBody
	@RequestMapping(value="/getContentBadWord",method = RequestMethod.POST)
	@OpLogger(id = "getContentBadWord", type=OpType.SEARCH)
	public Json getContentBadWord(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getContentBadWord方法,请求的数据:" + data);
		Integer userId;
		String token;
		String content;
		try {
			userId = data.getInt("userId");
			token=data.getString("token");
			content=data.getString("content");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
					
		json = textService.getContentBadWord(userId,token,content);
		return json;
	}
	
}
