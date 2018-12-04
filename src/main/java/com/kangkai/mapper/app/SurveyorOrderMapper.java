package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.ProductAttr;
import com.kangkai.pojo.ProductInfo;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.Province;
import com.kangkai.pojo.SurveyorOrder;
import com.kangkai.vo.AttrVO;
import com.kangkai.vo.UserProductDetailVO;
import com.kangkai.vo.UserProductVO;
import com.kangkai.vo.UserSurveyorOrderVO;

public interface SurveyorOrderMapper  {
	/**
	 * 查询一张量体师预约订单
	 * @param surveyorOrderId
	 * @return
	 */
	SurveyorOrder selectById(Integer surveyorOrderId);
	/**
	 * 根据用户Id获取量体师订单
	 * @param map
	 * @return
	 */
	List<UserSurveyorOrderVO> selectSurveyorOrderByUserId(Map<String, Object> map);
	/**
	 * 插入量体师订单
	 * @param surveyorOrder
	 */
	void insertSurveyorOrder(SurveyorOrder surveyorOrder);
	/**
	 * 查询最近的一单量体师预约
	 * @param map
	 * @return
	 */
	SurveyorOrder selectLastSurveyorOrder(Map<String, Object> map);
	/**
	 * 根据量体师Id查询量体师预约列表
	 * @param map
	 * @return
	 */
	List<UserSurveyorOrderVO> selectSurveyorOrderBySurveyorId(Map<String, Object> map);
	/**
	 * 更新量体师预约订单状态
	 * @param surveyorOrder
	 */
	void updateSurveyorOrderState(SurveyorOrder surveyorOrder);
	
	
}
