package com.kangkai.vo;

public class UserCommunityInfo {
	
	private  int userCommunityTopicNum;
	private int userCommunityNum;
	private String nickname;
	private String userIcon;
	public int getUserCommunityTopicNum() {
		return userCommunityTopicNum;
	}
	public void setUserCommunityTopicNum(int userCommunityTopicNum) {
		this.userCommunityTopicNum = userCommunityTopicNum;
	}
	public int getUserCommunityNum() {
		return userCommunityNum;
	}
	public void setUserCommunityNum(int userCommunityNum) {
		this.userCommunityNum = userCommunityNum;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	public void setUserInfo(UserInfoVO userInfo) {
		this.setNickname(userInfo.getNickname());
		this.setUserIcon(userInfo.getUserIcon());
	}
	
	
}
