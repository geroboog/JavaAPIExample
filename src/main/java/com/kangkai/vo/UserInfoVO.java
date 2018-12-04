package com.kangkai.vo;

import com.kangkai.pojo.UserInfo;

public class UserInfoVO extends UserInfo{
	private double allBalance;
	private int couponNum;
	private String userIcon;
	private String nickname;
	public String getUserIcon() {
		return userIcon;
	}
	
	public double getAllBalance() {
		return allBalance;
	}

	public void setAllBalance(double allBalance) {
		this.allBalance = allBalance;
	}

	public int getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(int couponNum) {
		this.couponNum = couponNum;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
