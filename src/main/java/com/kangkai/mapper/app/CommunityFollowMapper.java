package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Banner;
import com.kangkai.pojo.Category;
import com.kangkai.pojo.Community;
import com.kangkai.pojo.CommunityFollow;
import com.kangkai.pojo.CommunityTopic;
import com.kangkai.vo.CommunityTopicVO;

public interface CommunityFollowMapper {
	/**
	 * 获取圈子关注列表
	 * @param map
	 * @return
	 */
	List<CommunityFollow> selectCommunityFollowList(Map<String, Object> map);
	/**
	 * 获取圈子关注数
	 * @param map
	 * @return
	 */
	Integer countCommunityFollowList(Map<String, Object> map);
	/**
	 * 获取圈子关注表
	 * @param map
	 * @return
	 */
	CommunityFollow getCommunityFollow(Map<String, Object> map);
	/**
	 * 更新关注状态
	 * @param communityFollow
	 */
	void updateCommunityFollow(CommunityFollow communityFollow);
	/**
	 * 插入关注圈子
	 * @param communityFollow
	 */
	void insertCommunityFollow(CommunityFollow communityFollow);


}
