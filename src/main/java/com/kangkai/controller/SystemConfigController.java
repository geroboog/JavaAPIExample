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
import com.kangkai.service.utilService.ISystemConfigService;
import com.kangkai.service.utilService.impl.SystemConfigService;
import com.kangkai.utils.Json;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="systemConfig")
public class SystemConfigController {

	@Resource
	private ISystemConfigService systemConfigService;
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	

	@RequestMapping(value="/systemConfig",method = RequestMethod.POST)
	@ResponseBody
	public Json getSystemConfig(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getSystemConfig方法,请求的数据:" + data);
		Integer userId;
		Integer current;
		Integer pageSize;
		String token;
		
		json = systemConfigService.getSystemConfig();
		return json;
	}
}
