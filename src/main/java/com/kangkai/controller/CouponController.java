package com.kangkai.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangkai.pojo.User;
import com.kangkai.service.appService.IAddressService;
import com.kangkai.service.appService.ICategoryService;
import com.kangkai.service.appService.ICouponService;
import com.kangkai.service.appService.ISalesService;
import com.kangkai.utils.Json;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.ShardedJedisPool;

@Controller
@RequestMapping(value="coupon")
public class CouponController { 
	
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	
	
	
}
