package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Category;
import com.kangkai.pojo.Product;
import com.kangkai.pojo.RecommendRedPacket;
import com.kangkai.pojo.RecommendUserRelationship;
import com.kangkai.pojo.RedPacket;
import com.kangkai.pojo.Sales;
import com.kangkai.pojo.SalesProduct;
import com.kangkai.pojo.User;
import com.kangkai.pojo.UserInfo;
import com.kangkai.vo.RecommendUserRelationshipVO;
import com.kangkai.vo.UserSimpleVO;

public interface RecommendUserRelationshipMapper {
	/**
	 * 插入一个关系
	 * @param recommendUserRelationship
	 */
	void insertRecommendUserRelationship(RecommendUserRelationship recommendUserRelationship);
	/**
	 * 获取一个用户所有下级
	 * @param map
	 * @return
	 */
	List<RecommendUserRelationshipVO> selectRecommendUserRelationship(Map<String, Object> map);
	/**
	 * 获取一个用户所有上级
	 * @param map
	 * @return
	 */
	RecommendUserRelationshipVO selectRecommendUserRelationshipUp(Map<String, Object> map);
	
	
}
