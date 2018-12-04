package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.ProductAttr;
import com.kangkai.pojo.ProductInfo;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.ProductOrderMessage;
import com.kangkai.pojo.Province;
import com.kangkai.pojo.SurveyorOrderMessage;
import com.kangkai.vo.AttrVO;
import com.kangkai.vo.UserProductDetailVO;
import com.kangkai.vo.UserProductOrderDetailVO;
import com.kangkai.vo.UserProductOrderMessageVO;
import com.kangkai.vo.UserProductOrderProductVO;
import com.kangkai.vo.UserProductOrderVO;
import com.kangkai.vo.UserProductVO;
import com.kangkai.vo.UserSurveyorOrderMessageVO;

public interface SurveyorOrderMessageMapper  {
	/**
	 * 查询量体师小红点消息
	 * @param map
	 * @return
	 */
	List<UserSurveyorOrderMessageVO> selectSurveyorOrderMessage(Map<String, Object> map);
	/**
	 * 插入量体师小红点消息
	 * @param map
	 */
	void insertSurveyorOrderMessage(Map<String, Object> map);
	/**
	 * 查询一个小红点信息
	 * @param map
	 * @return
	 */
	SurveyorOrderMessage selectSurveyorOrderMessageOne(Map<String, Object> map);
	/**
	 * 更新量体师小红点信息
	 * @param map
	 * @return
	 */
	Integer updateSurveyorOrderMessage(Map<String, Object> map);
	
}
