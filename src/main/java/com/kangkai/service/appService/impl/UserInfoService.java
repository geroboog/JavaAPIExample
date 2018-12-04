package com.kangkai.service.appService.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.mapper.app.CouponMapper;
import com.kangkai.mapper.app.UserInfoMapper;
import com.kangkai.mapper.app.UserWalletMapper;
import com.kangkai.pojo.UserInfo;
import com.kangkai.service.appService.IUserInfoService;
import com.kangkai.utils.Json;
import com.kangkai.vo.UserInfoVO;
import com.kangkai.vo.UserWalletVO;


@Service(value="/userInfoService")
@Transactional
public class UserInfoService implements IUserInfoService{
	
	@Resource
	private UserInfoMapper userInfoMapper;
	@Resource
	private UserWalletMapper userWalletMapper;
	@Resource
	private CouponMapper couponMapper;
	@Override
	public Json getUserInfo(Integer userId) {
		Json json=new Json();
		UserInfoVO userInfo=userInfoMapper.getUserInfoVO(userId);
		UserWalletVO userWalletvo=userWalletMapper.getUserWallet(userId);
		Integer couponNum=couponMapper.countUserUsefulCoupon(userId);
		userInfo.setAllBalance(userWalletvo.getBalance()+userWalletvo.getWithDrawBalance());
		userInfo.setCouponNum(couponNum);
		if(userInfo !=null)
		{
			json.setCode(100);
			json.setData(userInfo);
		}else
		{
			json.setCode(112);
		}

		return json;
	}

	

}
