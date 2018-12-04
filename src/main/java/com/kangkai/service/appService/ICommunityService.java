package com.kangkai.service.appService;

import com.kangkai.utils.Json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ICommunityService {
	/**
	 * 获取圈子列表
	 * @param isHot
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getCommunityList(Integer isHot, Integer current, Integer pageSize);
	/**
	 * 获取话题列表
	 * @param communityId
	 * @param userId
	 * @param current 
	 * @param pageSize 
	 * @return
	 */
	Json getCommunityTopicList(Integer communityId,Integer userId, Integer current, Integer pageSize);
	/**
	 * 获取话题评论列表
	 * @param communityTopicId
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getCommunityTopicCommentList(Integer communityTopicId, Integer current, Integer pageSize);
	/**
	 * 发表圈子帖子
	 * @param communityId
	 * @param productOrderItemId
	 * @param userId
	 * @param comment
	 * @param title
	 * @param content 
	 * @return
	 */
	Json releaseCommunityTopic(Integer communityId,Integer productOrderItemId, Integer userId, String token,String title, String content);
	/**
	 * 获取用户圈子信息
	 * @param userId
	 * @param token
	 * @return
	 */
	Json getUserCommunityInfo(Integer userId,String token);
	/**
	 * 评论一个圈子帖子
	 * @param communityTopicId
	 * @param userId
	 * @param content
	 * @return
	 */
	Json commentCommunityTopic(Integer userId,  String token,Integer communityTopicId,String content);
	/**
	 * 关注帖子
	 * @param communityId
	 * @param userId
	 * @param token 
	 * @return
	 */
	Json followCommunity(Integer communityId, Integer userId, String token);
	/**
	 * 获取圈子帖子详情
	 * @param communityTopicId
	 * @return
	 */
	Json getCommunityTopicDetail(Integer communityTopicId);
	/**
	 * 获取用户关注圈子列表
	 * @param userId
	 * @param token
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getCommunityFollowList(Integer userId, String token, Integer current, Integer pageSize);
	/**
	 * 获取商品相关圈子帖子列表
	 * @param userId
	 * @param token
	 * @param productId
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getProductCommunityTopicList(Integer userId, String token, Integer productId, Integer current,
			Integer pageSize);
	/**
	 * 获取衣服列表
	 * @param userId
	 * @param token
	 * @param season
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getClothesList(Integer userId, String token, String season, Integer current, Integer pageSize);
	/**
	 * 获取圈内话题列表
	 * @param userId
	 * @param token
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getCommunityTopicFollowList(Integer userId, String token, Integer current, Integer pageSize);
	/**
	 * 获取一个圈子的详细信息
	 * @param communityId
	 * @return
	 */
	Json getCommunityInfo(Integer communityId,Integer userId);
	
}
