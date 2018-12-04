package com.kangkai.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangkai.SpringAOP.OpLogger;
import com.kangkai.SpringAOP.OpLogger.OpType;
import com.kangkai.utils.FTPUtils;
import com.kangkai.utils.Json;
import com.kangkai.utils.MySQLDatabaseBackup;
import com.kangkai.service.appService.ISurveyorService;
import com.kangkai.service.appService.IUserInfoService;
import com.kangkai.service.appService.IUserService;
import com.kangkai.service.appService.impl.UserService;
import com.kangkai.service.utilService.IWasteService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "wallet")
public class WalletController {

	@Resource
	private IUserService userService;
	@Resource
	private IWasteService wasteService;
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());

	/**
	 * 获取余额
	 * 
	 * @param data
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getBalance", method = RequestMethod.POST)
	public Json getBalance(@RequestBody JSONObject data) {
		Json json = new Json();
		log.info("请求执行getBalance方法,请求的数据:" + data);
		Integer userId;
		String token;
		Integer type=null;
		int loginType;
			try
			{
				userId = data.getInt("userId");
				token = data.getString("token");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
			
			json = userService.getBalance(userId, token);
		return json;
	}
	
	/**
	 * 获取用户信息
	 * 
	 * @param data
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserWasteList", method = RequestMethod.POST)
	@OpLogger(id = "getUserWasteList", type=OpType.SEARCH)
	public Json getUserWasteList(@RequestBody JSONObject data) {
		Json json = new Json();
		log.info("请求执行getUserWasteList方法,请求的数据:" + data);
		Integer userId;
		Integer current;
		Integer pageSize;
		String token;
		String wasteTime=null;
		try
		{
		userId = data.getInt("userId");
		token  = data.getString("token");
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
			wasteTime=data.getString("wasteTime");
		}catch(Exception e)
		{
			
		}
			json = wasteService.getUserWasteList(userId,token,current,pageSize,wasteTime);
		return json;
	}
	
	/**
	 * 用户提现
	 * 
	 * @param data
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/withDrawCash", method = RequestMethod.POST)
	@OpLogger(id = "withDrawCash", type=OpType.SEARCH)
	public Json withDrawCash(@RequestBody JSONObject data) {
		Json json = new Json();
		log.info("请求执行withDrawCash方法,请求的数据:" + data);
		Integer userId;
		String token;
		try
		{
		userId = data.getInt("userId");
		token  = data.getString("token");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		return json;
	}
	
	/**
	 * 量体师提现
	 * 
	 * @param data
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/withDrawCashForSurveyor", method = RequestMethod.POST)
	@OpLogger(id = "withDrawCashForSurveyor", type=OpType.SEARCH)
	public Json withDrawCashForSurveyor(@RequestBody JSONObject data) {
		Json json = new Json();
		log.info("请求执行withDrawCashForSurveyor方法,请求的数据:" + data);
		Integer userId;
		String token;
		try
		{
		userId = data.getInt("userId");
		token  = data.getString("token");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		return json;
	}
	

	
}
