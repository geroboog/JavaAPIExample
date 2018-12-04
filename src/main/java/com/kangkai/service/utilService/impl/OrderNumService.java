package com.kangkai.service.utilService.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.service.utilService.IOrderNumService;

@Service(value="/orderNumService")
@Transactional
public class OrderNumService implements IOrderNumService{
	@Override
	public String getProductOrderNum(int userId)
	{
		String orderNum=(int) (Math.random() * 9)+""+(int) (Math.random() * 9)+""+new Date().getTime()+""+userId;
		
		return orderNum;
	}

	@Override
	public String getSurveyorOrderNum(Integer userId) {
		String orderNum=(int) (Math.random() * 9)+""+new Date().getTime()+""+userId;
		
		return orderNum;
	}
	
}
