package com.kangkai.service.utilService.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.mapper.app.WasteNumMapper;
import com.kangkai.pojo.WasteNum;
import com.kangkai.service.utilService.IWasteNumService;

@Service(value="/wasteNumService")
@Transactional
public class WasteNumService implements IWasteNumService{
	
	@Resource
	WasteNumMapper wasteNumMapper;
	@Override
	public String getProductWasteNum(Integer userId, String orderNum) {
			String wasteNumStr=(int) (Math.random() * 9)+""+(int) (Math.random() * 9)+""+new Date().getTime()+""+userId;
			
			WasteNum wasteNum=new WasteNum();
			wasteNum.setProductOrderNum(orderNum);
			wasteNum.setUserId(userId);
			wasteNum.setWasteNum(wasteNumStr);
			wasteNumMapper.insertWasteNum(wasteNum);
			
			return wasteNumStr;
		
	}

}
