package com.kangkai.mapper.util;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Banner;
import com.kangkai.pojo.Category;
import com.kangkai.pojo.PayWay;
import com.kangkai.pojo.SurveyorSystemMessage;
import com.kangkai.pojo.UserSystemMessage;

public interface SystemConfigMapper {
	/**
	 * 获取所有支付方式
	 * @return
	 */
	List<PayWay> selectAllPayWay();


}
