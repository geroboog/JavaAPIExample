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
import com.kangkai.service.appService.ISurveyorOrderMessageService;
import com.kangkai.service.appService.ISurveyorOrderService;
import com.kangkai.service.appService.ISurveyorOrderService;
import com.kangkai.utils.DateUtils;
import com.kangkai.utils.Json;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="surveyorOrder")
public class SurveyorOrderController {
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	@Resource
	ISurveyorOrderService surveyorOrderService;
	@Resource
	ISurveyorOrderMessageService surveyorOrderMessageService;
	
		
	
	//购买量体师订单列表
	@ResponseBody
	@RequestMapping(value="/getSurveyorOrderList",method = RequestMethod.POST)
	@OpLogger(id = "getSurveyorOrderList", type=OpType.SEARCH)
	public Json getSurveyorOrderList(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行getSurveyorOrderList方法,请求的数据:" + data);
			Integer userId;
			Integer state=null;
			Integer current;
			Integer pageSize;
			String token;
			try {
				userId = data.getInt("userId");
				token=data.getString("token");
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
					state = data.getInt("state");
				}catch(Exception e)
				{
					
				}
				json = surveyorOrderService.getSurveyorOrderList(userId,state,token,current,pageSize);
				return json;
			}
	
	//量体师获取购买量体师订单列表
	@ResponseBody
	@RequestMapping(value="/getSurveyorOrderListForSurveyor",method = RequestMethod.POST)
	@OpLogger(id = "getSurveyorOrderListForSurveyor", type=OpType.SEARCH)
	public Json getSurveyorOrderListForSurveyor(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行getSurveyorOrderListForSurve方法,请求的数据:" + data);
			Integer surveyorId;
			Integer state=null;
			Integer current;
			Integer pageSize;
			String token;
			try {
				surveyorId = data.getInt("surveyorId");
				token=data.getString("token");
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
				state = data.getInt("state");
			}catch(Exception e)
			{
				
			}
			
			json = surveyorOrderService.getSurveyorOrderListForSurveyor(surveyorId,token,state,current,pageSize);
			return json;
	}
	
	//发布量体师订单
	@ResponseBody
	@RequestMapping(value="/releaseSurveyorOrder",method = RequestMethod.POST)
	@OpLogger(id = "releaseSurveyorOrder", type=OpType.SEARCH)
	public Json releaseSurveyorOrder(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行releaseSurveyorOrder方法,请求的数据:" + data);
			Integer userId;
			String token;
			Integer addressId;
			Date bookTime;
			try {
				userId = data.getInt("userId");
				token=data.getString("token");
				String bookTimestr=data.getString("bookTime");
				bookTime=DateUtils.parseDatetime(bookTimestr);
				addressId=data.getInt("addressId");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}

				json = surveyorOrderService.releaseSurveyorOrder(userId,token,addressId,bookTime);
				return json;
			}
	
	@RequestMapping(value="/getSurveyorOrderMessage",method = RequestMethod.POST)
	@ResponseBody
	public Json getSurveyorOrderMessage(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行getSurveyorOrderMessage方法,请求的数据:" + data);
			Integer userId;
			Integer state=null;
			Integer current;
			Integer pageSize;
			String token;
			try {
				userId = data.getInt("userId");
				token=data.getString("token");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
			json = surveyorOrderMessageService.getSurveyorOrderMessage(userId,token);
			return json;
	}
	
	//量体师获取购买量体师订单列表
		@ResponseBody
		@RequestMapping(value="/startServe",method = RequestMethod.POST)
		@OpLogger(id = "startServe", type=OpType.SEARCH)
		public Json startServe(@RequestBody JSONObject data){
				Json json = new Json();
				log.info("请求执行startServe方法,请求的数据:" + data);
				Integer surveyorId;
				Integer surveyorOrderId;
				Integer state=null;
				Integer current;
				Integer pageSize;
				String token;
				try {
					surveyorId = data.getInt("surveyorId");
					surveyorOrderId = data.getInt("surveyorOrderId");
					token=data.getString("token");
				} catch (Exception e) {
					json.setCode(0);
					json.setMsg("请求的参数不合法");
					json.setData(null);
					log.error("请求的参数不合法，错误信息:" + e.getMessage());
					return json;
				}
				
				json = surveyorOrderService.startServe(surveyorId,token,surveyorOrderId);
				return json;
		}
}	
