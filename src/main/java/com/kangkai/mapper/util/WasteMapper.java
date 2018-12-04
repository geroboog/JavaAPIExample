package com.kangkai.mapper.util;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Waste;
import com.kangkai.vo.UserWasteVO;

public interface WasteMapper {

	/**
	 *插入一条商品流水信息
	 * @param waste
	 */
	void insertProductWaste(Waste waste);
	/**
	 * 获取用户流水信息列表
	 * @param map
	 * @return
	 */
	List<UserWasteVO> selectWasteList(Map<String, Object> map);
	


}
