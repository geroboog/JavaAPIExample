package com.kangkai.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.parser.deserializer.StringFieldDeserializer;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.kangkai.SpringAOP.OpLogger;
import com.kangkai.SpringAOP.OpLogger.OpType;
import com.kangkai.service.utilService.IPaymentService;
import com.kangkai.utils.AlipayUtil;
import com.kangkai.utils.Json;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Event;
import com.pingplusplus.model.Webhooks;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/charge")
public class ChargeController {
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	private IPaymentService paymentService;
	private String ALIPAY="alipay";
	private String WX="wx";
	@ResponseBody
	@RequestMapping(value="/payment",method = RequestMethod.POST)
	@OpLogger(id = "payment", type=OpType.SEARCH)
	public Json payment(@RequestBody JSONObject data){
		Json json = new Json();
		log.info("请求执行payment方法,请求的数据:" + data);
		Integer userId;
		Integer type;
		String token;
		String wasteNum;
		String channel;
		try {
			userId = data.getInt("userId");
			token = data.getString("token");
			wasteNum=data.getString("wasteNum");
			type=data.getInt("type");
			channel=data.getString("channel");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
			json=paymentService.getPayment(userId,token,wasteNum,type,channel);
		
			return json;
		
	}
	
	@RequestMapping(value="/webhooks",method = RequestMethod.POST)
	@ResponseBody
	@OpLogger(id = "webhooks", type=OpType.SEARCH)
	public String webhooks(HttpServletRequest request){
			// 获取支付宝POST过来反馈信息
			try 
			{

				Map<String, String> params = new HashMap<String, String>();
				Map requestParams = request.getParameterMap();
				for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
					String name = (String) iter.next();
					String[] values = (String[]) requestParams.get(name);
					String valueStr = "";
					for (int i = 0; i < values.length; i++) {
						valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
					}
					// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
					// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
					// "gbk");
					params.put(name, valueStr);
				}

				// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
				// 商户订单号

				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
				
				String type = new String(request.getParameter("body").getBytes("ISO-8859-1"), "UTF-8");

				// 支付宝交易号

				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

				// 交易状态
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

				if (AlipayUtil.varidate(params)) {
					// 请在这里加上商户的业务逻辑程序代码

					// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

					if (trade_status.equals("TRADE_FINISHED") ) {
					
					} else if (trade_status.equals("TRADE_SUCCESS")) {
						paymentService.webhooks(out_trade_no,type,ALIPAY);
						
					}
					// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
					return "success";
				} else {// 验证失败
					log.error("exception in alipay: exception fail");
					return "failure";
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return "failure";
			}
	}
	
	@RequestMapping(value="/webhooksTest",method = RequestMethod.POST)
	@ResponseBody
	@OpLogger(id = "webhooksTest", type=OpType.SEARCH)
	public String webhooksTest(@RequestBody JSONObject data){
			// 获取支付宝POST过来反馈信息
			try 
			{

				

				String out_trade_no = data.getString("out_trade_no");
				
				String type = data.getString("body");

						paymentService.webhooks(out_trade_no,type,ALIPAY);
					return "success";	
					
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return "failure";
			}
	}

}
