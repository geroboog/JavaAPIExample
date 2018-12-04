package com.kangkai.service.utilService;

public interface IOrderNumService {

	/**
	 * 生成商城订单号
	 * @param userId
	 * @return
	 */
	String getProductOrderNum(int userId);
	/**
	 * 生成量体师单号
	 * @param userId
	 * @return
	 */
	String getSurveyorOrderNum(Integer userId);

}
