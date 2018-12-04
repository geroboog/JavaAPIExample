package com.kangkai.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangkai.service.appService.ICategoryService;
import com.kangkai.service.utilService.IBannerService;
import com.kangkai.utils.Json;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="banner")
public class BannerController {
	@Resource
	private IBannerService bannerService;
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	
	//获取商品目录列表
	
	@RequestMapping(value="/getBannerList",method = RequestMethod.POST)
	@ResponseBody
	public Json getBannerList(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getBannerList方法,请求的数据:" + data);
		Integer current;
		Integer pageSize;
		Integer type;
		String title;
		try {
			type = data.getInt("type");
			current = data.getInt("current");
			pageSize = data.getInt("pageSize");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
				
		
		json = bannerService.getBannerList(type,current,pageSize);
		return json;
	}
}
