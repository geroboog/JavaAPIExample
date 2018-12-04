package com.kangkai.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangkai.service.utilService.IArticleService;
import com.kangkai.utils.Json;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="article")
public class ArticleController {
	
	@Resource
	private IArticleService articleService;
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	


	@RequestMapping(value="/getArticles",method = RequestMethod.POST)
	@ResponseBody
	public Json getArticles(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getArticles方法,请求的数据:" + data);
		Integer userId;
		Integer type;
		String token;
		try {
			type=data.getInt("type");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = articleService.getArticle(type);
		return json;
	}
	
	
	
}
