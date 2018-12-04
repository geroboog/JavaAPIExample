package com.kangkai.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

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

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "surveyor")
public class SurveyorController {

	@Resource
	private IUserService userService;
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private ISurveyorService surveyorService;
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());

	/**
	 * 登陆
	 * 
	 * @param data
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Json login(@RequestBody JSONObject data) 
	{
		Json json = new Json();
		log.info("请求执行login方法,请求的数据:" + data);
		String username;
		String password;
		int loginType;
			try
			{
			username = data.getString("username");
			password = data.getString("password");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
			json = surveyorService.login(username, password);
		return json;
	}
	
	/**
	 * 获取用户信息
	 * 
	 * @param data
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSurveyorWalletInfo", method = RequestMethod.POST)
	@OpLogger(id = "getSurveyorWalletInfo", type=OpType.SEARCH)
	public Json getSurveyorWalletInfo(@RequestBody JSONObject data) 
	{
		Json json = new Json();
		log.info("请求执行getSurveyorWalletInfo方法,请求的数据:" + data);
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
			json = surveyorService.getSurveyorBalance(userId,token);
		return json;
	}
	

	/**
	 * 找回密码
	 */
	@RequestMapping(value = "/findPassword", method = RequestMethod.POST)
	@ResponseBody
	public Json findPassword(@RequestBody JSONObject data, HttpSession session) 
	{
		Json json = new Json();
		log.info("请求执行findPassword方法,请求的数据:" + data);
		String phone;
		String vcode;
		String newPassword;
		String newPassword2;
		try {
			phone = data.getString("phone");
			vcode = data.getString("vcode");
			newPassword = data.getString("newPassword");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		String _vcode = (String) session.getAttribute(phone);
		json = userService.findPassword(phone, vcode, phone, _vcode, newPassword, newPassword);
		return json;
	}

	/*
	 * 修改密码
	 * 
	 */
	@RequestMapping(value = "/updatePassword")
	@ResponseBody
	public Json updatePassword(@RequestBody JSONObject data) 
	{
		Json json = new Json();
		log.info("请求执行updatePassword方法,请求的数据:" + data);
		Integer userId;
		String oldPassword;
		String newPassword;
		String newPassword2;
		String token;
		try {
			userId = data.getInt("userId");
			oldPassword = data.getString("oldPassword");
			newPassword = data.getString("newPassword");
			token = data.getString("token");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		json = userService.updatePassword(userId, oldPassword, newPassword, newPassword, token);
		return json;

	}
	
	//申请成为量体师
	@ResponseBody
	@RequestMapping(value="/applyForSurveyor",method = RequestMethod.POST)
	@OpLogger(id = "applyForSurveyor", type=OpType.SEARCH)
	public Json applyForSurveyor(@RequestBody JSONObject data)
	{
			Json json = new Json();
			log.info("请求执行applyForSurveyor方法,请求的数据:" + data);
			Integer userId;
			String token;
			String name;
			String phone;
			String province;
			String city;
			String img;
			String gender;
			try 
			{
				userId = data.getInt("userId");
				token=data.getString("token");
				name=data.getString("name");
				phone=data.getString("phone");
				province=data.getString("province");
				city=data.getString("city");
				img=data.getString("img");
				gender=data.getString("gender");
			} catch (Exception e) 
			{
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
					
				json = surveyorService.applyForSurveyor(userId, token, city, gender, name, img, phone, province);
				return json;
	}
	
	
}
