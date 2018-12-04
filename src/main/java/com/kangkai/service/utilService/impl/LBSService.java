package com.kangkai.service.utilService.impl;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.mapper.app.CategoryMapper;
import com.kangkai.mapper.app.SurveyorMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.mapper.util.BannerMapper;
import com.kangkai.mapper.util.ExpressMapper;
import com.kangkai.pojo.Banner;
import com.kangkai.pojo.Cartitem;
import com.kangkai.pojo.Category;
import com.kangkai.pojo.Coupon;
import com.kangkai.pojo.User;
import com.kangkai.service.appService.ICategoryService;
import com.kangkai.service.appService.IProductService;
import com.kangkai.service.utilService.IBannerService;
import com.kangkai.service.utilService.ILBSService;
import com.kangkai.utils.ArrUtils;
import com.kangkai.utils.Constants;
import com.kangkai.utils.DateUtils;
import com.kangkai.utils.JPUSHUtils;
import com.kangkai.utils.Json;
import com.kangkai.utils.LogisticsUtils;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.Redis;
import com.kangkai.utils.TokenUtil;
import com.kangkai.utils.LBS.GeoHash;
import com.kangkai.vo.LogisticsInfoVO;
import com.kangkai.vo.UserSimpleVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;


@Service(value="/LBSService")
@Transactional
public class LBSService implements ILBSService{
	private int level=6;
	@Resource
	UserMapper userMapper;
	@Resource
	SurveyorMapper surveyorMapper;
	private RedisService redisService=new RedisService();
	@Override
	public Json getLBSPeople(Integer userId, Double x_axis, Double y_axis, String token) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			return json;
		}
		Map<String,String> locationMap=new HashMap<String,String>();
		GeoHash geoHash=new GeoHash(x_axis,y_axis);
		String code=geoHash.getGeoHashBase32();
		locationMap.put("userId", ""+userId);
		locationMap.put("x_axis", ""+x_axis);
		locationMap.put("y_axis", ""+y_axis);
		locationMap.put("code", code);
		String codeC=code.substring(0, level);
		String locationNum=JSONObject.fromObject(locationMap).toString();
		redisService.setHMKeyRedis("userLocation"+":"+userId,locationMap);
		Map<String,List<String>> allUserLocation=getAllSurveyorLocation();
		Map<String,List<String>> resultLocation=new HashMap<String,List<String>>();
		for (String key : allUserLocation.keySet()) 
		{
			List<String> thisLocationDetail=allUserLocation.get(key);
			String thisCode=thisLocationDetail.get(3);
			if(thisCode.substring(0,level).equals(codeC))
			{
				resultLocation.put(key, thisLocationDetail);
			}
		}
		
		json.setCode(100);
		json.setData(resultLocation);
		return json;
	}
	
	@Override
	public Json getLBSSurveyor(Integer userId, Double x_axis, Double y_axis, String token) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkTokenForSurveyor(userId, surveyorMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			return json;
		}

		Map<String,String> locationMap=new HashMap<String,String>();
		GeoHash geoHash=new GeoHash(x_axis,y_axis);
		String code=geoHash.getGeoHashBase32();
		locationMap.put("surveyorId", ""+userId);
		locationMap.put("x_axis", ""+x_axis);
		locationMap.put("y_axis", ""+y_axis);
		locationMap.put("code", code);
		String codeC=code.substring(0, level);
		String locationNum=JSONObject.fromObject(locationMap).toString();
		redisService.setHMKeyRedis("surveyorLocation"+":"+userId,locationMap);
		Map<String,List<String>> allUserLocation=getAllUserLocation();
		Map<String,List<String>> resultLocation=new HashMap<String,List<String>>();
		for (String key : allUserLocation.keySet()) 
		{
			List<String> thisLocationDetail=allUserLocation.get(key);
			String thisCode=thisLocationDetail.get(3);
			if(thisCode.substring(0,level).equals(codeC))
			{
				resultLocation.put(key, thisLocationDetail);
			}
		}
		
		json.setCode(100);
		json.setData(resultLocation);
		return json;
	}
	public Map<String,List<String>> getAllUserLocation()
	{
		String pattern="userLocation*";
		String[] field={"userId","x_axis","y_axis","code"};
		return redisService.getAllHMSET(pattern,field);
		
	}
	public Map<String,List<String>> getAllSurveyorLocation()
	{
		String pattern="surveyorLocation*";
		String[] field={"surveyorId","x_axis","y_axis","code"};
		return redisService.getAllHMSET(pattern,field);
		
	}

	@Override
	public Json getSurveyorAxis(Integer userId, String token, Integer surveyorId) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			return json;
		}
		String[] field={"userId","x_axis","y_axis","code"};
		List<String> data=redisService.getHMKeyRedis("surveyorLocation"+":"+surveyorId,field);
		json.setCode(100);
		json.setData(data);
		return json;
	}
	@Override
	public Json getUserAxis(Integer surveyorId, String token, Integer userId) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkTokenForSurveyor(surveyorId,surveyorMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			return json;
		}
		String[] field={"userId","x_axis","y_axis","code"};
		List<String> data=redisService.getHMKeyRedis("userLocation"+":"+userId,field);
		json.setCode(100);
		json.setData(data);
		return json;
	}

	
	
	
}
