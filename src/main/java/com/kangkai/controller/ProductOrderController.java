package com.kangkai.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangkai.service.appService.IProductOrderMessageService;
import com.kangkai.service.appService.IProductOrderService;
import com.kangkai.utils.Json;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="productOrder")
public class ProductOrderController {
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	@Resource
	IProductOrderService productOrderService;
	@Resource
	IProductOrderMessageService productOrderMessageService;
	//购买商品优惠券列表
	@RequestMapping(value="/getProductOrderList",method = RequestMethod.POST)
	@ResponseBody
	public Json getProductOrderList(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行getProductOrderList方法,请求的数据:" + data);
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
			json = productOrderService.getProductOrderList(userId,state,token,current,pageSize);
			return json;
	}
	
	@RequestMapping(value="/getProductOrderDetail",method = RequestMethod.POST)
	@ResponseBody
	public Json getProductOrderDetail(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行getProductOrderDetail方法,请求的数据:" + data);
		Integer userId;
		Integer productOrderId;
		String token;
		try {
			userId = data.getInt("userId");
			productOrderId = data.getInt("productOrderId");
			token=data.getString("token");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		try {
			userId = data.getInt("userId");
		} catch (Exception e) {
			
		}
		json = productOrderService.getProductOrderDetail(userId,productOrderId,token);
		return json;
	}
	
		@RequestMapping(value="/getProductOrderMessage",method = RequestMethod.POST)
		@ResponseBody
		public Json getProductOrderMessage(@RequestBody JSONObject data){
				Json json = new Json();
				log.info("请求执行getProductOrderMessage方法,请求的数据:" + data);
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
				json = productOrderMessageService.getProductOrderMessage(userId,token);
				return json;
		}
		
		@RequestMapping(value="/getProductOrderLogisticsInfoList",method = RequestMethod.POST)
		@ResponseBody
		public Json getProductOrderLogisticsInfoList(@RequestBody JSONObject data){
				Json json = new Json();
				log.info("请求执行getProductOrderLogisticsInfoList方法,请求的数据:" + data);
				Integer userId;
				Integer productOrderId;
				String token;
				try {
					userId = data.getInt("userId");
					productOrderId=data.getInt("productOrderId");
					token=data.getString("token");
				} catch (Exception e) {
					json.setCode(0);
					json.setMsg("请求的参数不合法");
					json.setData(null);
					log.error("请求的参数不合法，错误信息:" + e.getMessage());
					return json;
				}
				json = productOrderService.getProductOrderLogisticsInfoList(userId,token,productOrderId);
				return json;
		}
		
		//签收商品
		@RequestMapping(value="/signProductOrder",method = RequestMethod.POST)
		@ResponseBody
		public Json signProductOrder(@RequestBody JSONObject data){
				Json json = new Json();
				log.info("请求执行signProductOrder方法,请求的数据:" + data);
				Integer userId;
				Integer state=null;
				Integer productOrderId;
				Integer current;
				Integer pageSize;
				String token;
				try {
					userId = data.getInt("userId");
					token=data.getString("token");
					productOrderId=data.getInt("productOrderId");
				} catch (Exception e) {
					json.setCode(0);
					json.setMsg("请求的参数不合法");
					json.setData(null);
					log.error("请求的参数不合法，错误信息:" + e.getMessage());
					return json;
				}

				json = productOrderService.signProductOrder(userId,token,productOrderId);
				return json;
		}
		//申请售后
				@RequestMapping(value="/applyAfterSales",method = RequestMethod.POST)
				@ResponseBody
				public Json applyAfterSales(@RequestBody JSONObject data){
						Json json = new Json();
						log.info("请求执行applyAfterSales方法,请求的数据:" + data);
						Integer userId;
						Integer state=null;
						Integer productOrderId;
						Integer current;
						Integer pageSize;
						String token;
						try {
							userId = data.getInt("userId");
							token=data.getString("token");
							productOrderId=data.getInt("productOrderId");
						} catch (Exception e) {
							json.setCode(0);
							json.setMsg("请求的参数不合法");
							json.setData(null);
							log.error("请求的参数不合法，错误信息:" + e.getMessage());
							return json;
						}

						json = productOrderService.applyAfterSales(userId,token,productOrderId);
						return json;
				}
}	
