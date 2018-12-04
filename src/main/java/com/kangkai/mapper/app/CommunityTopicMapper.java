package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Banner;
import com.kangkai.pojo.Category;
import com.kangkai.pojo.Community;
import com.kangkai.pojo.CommunityFollow;
import com.kangkai.pojo.CommunityTopic;
import com.kangkai.vo.CommunityTopicDetailVO;
import com.kangkai.vo.CommunityTopicVO;

public interface CommunityTopicMapper {
	/**
	 * 获取圈子话题列表
	 * @param map
	 * @return
	 */
	List<CommunityTopicVO> selectCommunityTopicVOList(Map<String, Object> map);
	/**
	 * 插入一个圈子话题
	 * @param communityTopic
	 */
	void insertCommunityTopic(CommunityTopic communityTopic);
	/**
	 * 获取圈子话题列表数
	 * @param map
	 * @return
	 */
	Integer countCommunityTopicList(Map<String, Object> map);
	/**
	 * 获取圈子帖子详情
	 * @param map
	 * @return
	 */
	CommunityTopicDetailVO selectCommunityTopicVO(Map<String, Object> map);
	/**
	 * 查询圈内话题列表
	 * @param map
	 * @return
	 */
	List<CommunityTopicVO> selectCommunityFollowTopicVOList(Map<String, Object> map);


}
