package com.kangkai.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangkai.service.appService.ICartitemService;
import com.kangkai.service.utilService.ILBSService;
import com.kangkai.utils.Json;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="LBS")
public class LBSController {
	
	@Resource
	private ILBSService LBSService;
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	


	
	@RequestMapping(value="/getLBSPeople",method = RequestMethod.POST)
	@ResponseBody
	public Json getLBSPeople(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getLBSPeople方法,请求的数据:" + data);
		Integer userId;
		Double x_axis;
		Double y_axis;
		String token;
		try {
			userId = data.getInt("userId");
			x_axis = data.getDouble("x_axis");
			y_axis=data.getDouble("y_axis");
			token=data.getString("token");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = LBSService.getLBSPeople(userId, x_axis, y_axis,token);
		return json;
	}
	
	@RequestMapping(value="/getLBSSurveyor",method = RequestMethod.POST)
	@ResponseBody
	public Json getLBSSurveyor(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getLBSSurveyor方法,请求的数据:" + data);
		Integer surveyorId;
		Double x_axis;
		Double y_axis;
		String token;
		try {
			surveyorId = data.getInt("surveyorId");
			x_axis = data.getDouble("x_axis");
			y_axis=data.getDouble("y_axis");
			token=data.getString("token");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = LBSService.getLBSSurveyor(surveyorId, x_axis, y_axis,token);
		return json;
	}
	
	@RequestMapping(value="/getSurveyorAxis",method = RequestMethod.POST)
	@ResponseBody
	public Json getSurveyorAxis(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getSurveyorAxis方法,请求的数据:" + data);
		Integer userId;
		Integer surveyorId;
		Double x_axis;
		Double y_axis;
		String token;
		try {
			userId = data.getInt("userId");
			token=data.getString("token");
			surveyorId = data.getInt("surveyorId");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = LBSService.getSurveyorAxis(userId,token,surveyorId);
		return json;
	}
	@RequestMapping(value="/getUserAxis",method = RequestMethod.POST)
	@ResponseBody
	public Json getUserAxis(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getUserAxis方法,请求的数据:" + data);
		Integer userId;
		Integer surveyorId;
		Double x_axis;
		Double y_axis;
		String token;
		try {
			userId = data.getInt("userId");
			token=data.getString("token");
			surveyorId = data.getInt("surveyorId");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		
		json = LBSService.getUserAxis(userId,token,surveyorId);
		return json;
	}
	
	
	
	
	
}
