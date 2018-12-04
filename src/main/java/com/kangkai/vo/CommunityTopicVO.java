package com.kangkai.vo;

import com.kangkai.pojo.CommunityTopic;

public class CommunityTopicVO extends CommunityTopic {
	
	private String communityTitle;
	private String nickname;
	private String userIcon;
	
	
	
	public String getCommunityTitle() {
		return communityTitle;
	}
	public void setCommunityTitle(String communityTitle) {
		this.communityTitle = communityTitle;
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

	
	
}
