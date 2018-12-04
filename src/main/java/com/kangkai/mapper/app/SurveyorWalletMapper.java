package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.SurveyorWallet;
import com.kangkai.pojo.User;
import com.kangkai.pojo.UserInfo;
import com.kangkai.pojo.UserWallet;
import com.kangkai.vo.SurveyorWalletVO;
import com.kangkai.vo.UserSimpleVO;
import com.kangkai.vo.UserWalletVO;

public interface SurveyorWalletMapper {
	/**
	 * 插入一个量体师钱包信息
	 * @param user
	 * @return
	 */
	public int insert(SurveyorWallet surveyorWallet);
	/**
	 * 获取一个量体师钱包信息
	 * @param userId
	 * @return
	 */
	public SurveyorWalletVO getSurveyorWallet(Integer surveyorId);
	/**
	 * 获取一个用户钱包信息
	 * @param integer
	 * @return
	 */
	public SurveyorWallet getSurveyorWalletNor(Integer surveyorId);
	
}
