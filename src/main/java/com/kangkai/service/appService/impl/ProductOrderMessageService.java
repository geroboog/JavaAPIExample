package com.kangkai.service.appService.impl;



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

import com.kangkai.service.appService.IProductOrderMessageService;
import com.kangkai.mapper.app.ProductOrderItemMapper;
import com.kangkai.mapper.app.ProductOrderMapper;
import com.kangkai.mapper.app.ProductOrderMessageMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.ProductOrderMessage;
import com.kangkai.utils.Json;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;
import com.kangkai.vo.AddressVO;
import com.kangkai.vo.UserProductOrderDetailVO;
import com.kangkai.vo.UserProductOrderItemVO;
import com.kangkai.vo.UserProductOrderMessageVO;
import com.kangkai.vo.UserProductOrderProductVO;
import com.kangkai.vo.UserProductOrderVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service(value="/productOrderMessageService")
@Transactional
public class ProductOrderMessageService implements IProductOrderMessageService{

	@Resource
	private UserMapper userMapper;
	@Resource
	private ProductOrderMapper productOrderMapper;
	@Resource
	private ProductOrderItemMapper productOrderItemMapper;
	@Resource
	private ProductOrderMessageMapper productOrderMessageMapper;
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());


	@Override
	public Json getProductOrderMessage(Integer userId, String token) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}

		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		List<UserProductOrderMessageVO> userProductOrderMessageVO=productOrderMessageMapper.selectProductOrderMessage(map);
		
		if(userProductOrderMessageVO==null||userProductOrderMessageVO.size()<1)
		{
			productOrderMessageMapper.insertProductOrderMessage(map);
			userProductOrderMessageVO=productOrderMessageMapper.selectProductOrderMessage(map);
		}
		json.setCode(100);
		json.setData(userProductOrderMessageVO);
		return json;
	}
	
	
	@Override
	public Integer updateProductOrderMessage(Integer userId, Integer state,Integer type) {
		int check=0;
		Integer result=null;
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("state", state);
		map.put("type", type);
		if(type==0)
		{
			ProductOrderMessage productOrderMessage=productOrderMessageMapper.selectProductOrderMessageOne(map);
			if(productOrderMessage!=null)
			{
				if(productOrderMessage.getNum()>0)
				{
					check=1;
				}
			}
		}else
		{
			check=1;
		}
		if(check==1)
		{
		result=productOrderMessageMapper.updateProductOrderMessage(map);
		}
		return result;
	}
	
	
	
	
}
