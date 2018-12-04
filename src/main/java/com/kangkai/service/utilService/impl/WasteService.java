package com.kangkai.service.utilService.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.mapper.app.UserMapper;
import com.kangkai.mapper.util.WasteMapper;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.Waste;
import com.kangkai.service.utilService.IWasteService;
import com.kangkai.utils.Constants;
import com.kangkai.utils.Json;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;
import com.kangkai.vo.UserProductVO;
import com.kangkai.vo.UserWasteVO;


@Service(value="/wasteService")
@Transactional
public class WasteService implements IWasteService{
	@Resource
	private WasteMapper wasteMapper;
	@Resource
	private UserMapper userMapper;
	
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());
		
	@Override
	public void processProductWaste(String channel,String wasteNum,ProductOrder productOrder)
	{
		Date nowDate=new Date();
		Double money=productOrder.getFinalPrice()+productOrder.getFreight();
		//记录流水
		Waste waste=new Waste();
		waste.setWasteNum(wasteNum);
		waste.setDescribe(productOrder.getProductOrderNum()+"商城订单商品费");
		waste.setMoney(money);
		waste.setType(Constants.WASTETYPE.buy.value());
		waste.setPayWay(channel);
		waste.setUserId(productOrder.getUserId());
		wasteMapper.insertProductWaste(waste);
		
		
		//TODO 记录分销金额
		
	}
	@Override
	public Json getUserWasteList(Integer userId, String token, Integer current, Integer pageSize,String wasteTime) {
	Json json=new Json();
		
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		map.put("userId", userId);
		map.put("wasteTime", wasteTime);
		List<UserWasteVO> wasteList=wasteMapper.selectWasteList(map);
		if(wasteList!=null)
		{
			json.setCode(100);
			json.setData(wasteList);
		}else
		{
			json.setCode(112);
		}
		return json;
	}
}
