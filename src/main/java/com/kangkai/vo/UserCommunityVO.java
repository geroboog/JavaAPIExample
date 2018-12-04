package com.kangkai.vo;

import com.kangkai.pojo.Community;

public class UserCommunityVO extends Community{
	private int userFollowNum;
	private int isFollow;
	
	public int getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(int isFollow) {
		this.isFollow = isFollow;
	}

	public int getUserFollowNum() {
		return userFollowNum;
	}

	public void setUserFollowNum(int userFollowNum) {
		this.userFollowNum = userFollowNum;
	}
	
}
