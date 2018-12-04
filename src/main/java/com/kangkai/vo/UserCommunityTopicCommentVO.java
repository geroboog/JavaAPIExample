package com.kangkai.vo;

import com.kangkai.pojo.CommunityTopicComment;

public class UserCommunityTopicCommentVO extends CommunityTopicComment{
	private String nickname;
	private String userIcon;
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
