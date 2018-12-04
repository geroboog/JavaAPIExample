package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Category;
import com.kangkai.pojo.Product;
import com.kangkai.pojo.RecommendRedPacket;
import com.kangkai.pojo.RedPacket;
import com.kangkai.pojo.Sales;
import com.kangkai.pojo.SalesProduct;
import com.kangkai.pojo.User;
import com.kangkai.pojo.UserInfo;
import com.kangkai.vo.UserSimpleVO;

public interface RecommendRedPacketMapper {
	/**
	 * 根据手机和红包Id查询
	 * @param map
	 * @return
	 */
	RecommendRedPacket selectByPhoneAndRedPacketId(Map<String, Object> map);
	/**
	 * 插入一条推荐红包信息
	 * @param recommendRedPacket
	 */
	void insertRecommendRedPacket(RecommendRedPacket recommendRedPacket);
	
	
}
