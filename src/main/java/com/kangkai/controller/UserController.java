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
@RequestMapping(value = "user")
public class UserController {

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
	public Json login(@RequestBody JSONObject data) {
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
			json = userService.login(username, password);
		return json;
	}
	
	/**
	 * 获取用户信息
	 * 
	 * @param data
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
	@OpLogger(id = "getUserInfo", type=OpType.SEARCH)
	public Json getUserInfo(@RequestBody JSONObject data) {
		Json json = new Json();
		log.info("请求执行getUserInfo方法,请求的数据:" + data);
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
			json = userInfoService.getUserInfo(userId);
		return json;
	}
	


	/**
	 * 注册
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Json regist(@RequestBody JSONObject data, HttpSession session) {
		Json json = new Json();
		log.info("请求执行regist方法,请求的数据:" + data);
		String phone;
		String vcode;
		String password;
		try {
			phone = data.getString("phone");
			vcode = data.getString("vcode");
			password = data.getString("password");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		String _vcode = (String) session.getAttribute(phone);
		json = userService.regist(phone, vcode, password, _vcode);
		return json;
	}
	
	

	/**
	 * 获取验证码
	 */
	@RequestMapping(value = "/getVcode", method = RequestMethod.POST)
	@ResponseBody
	public Json getVcode(@RequestBody JSONObject data, HttpSession httpSession) throws UnsupportedEncodingException {
		Json json = new Json();
		log.info("请求执行getVcode方法,请求的数据:" + data);
		String phone;
		int msgType;
		try {
			phone = data.getString("phone");
			msgType = data.getInt("msgType");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		
		if(httpSession.getAttribute(phone)!=null)
		{
			json.setCode(201);
			return json;
		}else
		{
			json = userService.getVcode(phone, msgType);
		}
		
		if (json.getData() != null) {
			String vcode = (String) json.getData();
			httpSession.setAttribute(phone, vcode);
			httpSession.setMaxInactiveInterval(120); 
			json.setData(null);
		}

		return json;
	}

	/**
	 * 找回密码
	 */
	@RequestMapping(value = "/findPassword", method = RequestMethod.POST)
	@ResponseBody
	public Json findPassword(@RequestBody JSONObject data, HttpSession session) {
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
	public Json updatePassword(@RequestBody JSONObject data) {
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

	/**
	 * 验证验证码
	 */
	@RequestMapping(value = "/validateVcode", method = RequestMethod.POST)
	@ResponseBody
	public Json validateVcode(@RequestBody JSONObject data, HttpSession session) {
		Json json = new Json();
		log.info("请求执行validateVcode方法,请求的数据:" + data);
		String phone;
		String vcode;
		Integer msgType;
		try {
			phone = data.getString("phone");
			vcode = data.getString("vcode");
			msgType = data.getInt("msgType");
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		String _vcode = (String) session.getAttribute(phone);
		json = userService.validateVcode(phone, vcode, msgType, phone, _vcode);
		return json;
	}
	/**
	 * 上传需求用户信息
	 */
	@RequestMapping(value = "/uploadUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public Json uploadUserInfo(@RequestBody JSONObject data, HttpSession session) {
		Json json = new Json();
		log.info("请求执行uploadUserInfo方法,请求的数据:" + data);
		Integer userId;
		String phone;
		String vcode;
		String token;
		String username;
		String userIcon=null;
		String nickname=null;
		try {
				userId=data.getInt("userId");
				token=data.getString("token");
			 
		} catch (Exception e) {
			json.setCode(0);
			json.setMsg("请求的参数不合法");
			json.setData(null);
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		try 
		{
			 userIcon=data.getString("userIcon");
			 
		}catch(Exception e)
		{
			
		}
		try 
		{
			 nickname=data.getString("nickname");
		}catch(Exception e)
		{
			
		}
		json = userService.uploadUserInfo(userId,token,userIcon, nickname);
		return json;
	}
	
}
