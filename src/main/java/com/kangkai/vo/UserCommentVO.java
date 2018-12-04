package com.kangkai.vo;

import java.io.Serializable;
import java.util.Date;

import com.kangkai.pojo.ProductComment;

public class UserCommentVO extends ProductComment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6545306148589802235L;
	
	private String nickName;
	private String userIcon;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	
	
}
