package com.kangkai.service.utilService.impl;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.mapper.app.CategoryMapper;
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
import com.kangkai.utils.ArrUtils;
import com.kangkai.utils.Constants;
import com.kangkai.utils.DateUtils;
import com.kangkai.utils.JPUSHUtils;
import com.kangkai.utils.Json;
import com.kangkai.utils.LogisticsUtils;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;
import com.kangkai.vo.LogisticsInfoVO;
import com.kangkai.vo.UserSimpleVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service(value="/BannerService")
@Transactional
public class BannerService implements IBannerService{

	
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());
	@Resource
	private UserMapper userMapper;
	@Resource
	private ExpressMapper expressMapper;
	@Resource
	private CategoryMapper categoryMapper;
	@Resource
	private BannerMapper bannerMapper;
	@Override
	public Json getBannerList(Integer type,Integer current,Integer pageSize) {
		Json json=new Json();
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		map.put("type", type);
		List<Banner> bannerList=bannerMapper.selectBannerList(map);	
		
		if(bannerList!=null&&bannerList.size()>0)
		{
			json.setCode(100);
			json.setData(bannerList);
		}else
		{
			json.setCode(112);
		}
 
		
		return json;
	}
	
	
	
	
}
