package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Banner;
import com.kangkai.pojo.Category;
import com.kangkai.pojo.Community;
import com.kangkai.vo.UserCommunityVO;

public interface CommunityMapper {
	/**
	 * 获取圈子列表
	 * @param map
	 * @return
	 */
	List<Community> selectCommunityList(Map map);
	/**
	 * 获取用户关注圈子列表
	 * @param map
	 * @return
	 */
	List<Community> selectUserFollowCommunityList(Map<String, Object> map);
	/**
	 * 获取圈子详细信息
	 * @param map
	 * @return
	 */
	UserCommunityVO selectById(Map<String, Object> map);


}
