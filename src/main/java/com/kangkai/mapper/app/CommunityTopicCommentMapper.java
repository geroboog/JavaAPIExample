package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Banner;
import com.kangkai.pojo.Category;
import com.kangkai.pojo.Community;
import com.kangkai.pojo.CommunityTopicComment;
import com.kangkai.vo.CommunityTopicVO;
import com.kangkai.vo.UserCommunityTopicCommentVO;

public interface CommunityTopicCommentMapper {
	/**
	 * 圈子话题评论
	 * @param map
	 * @return
	 */
	List<UserCommunityTopicCommentVO> selectCommunityTopicCommentList(Map<String, Object> map);
	/**
	 * 插入一个帖子评论
	 * @param communityTopicComment
	 */
	void insertCommunityTopicComment(CommunityTopicComment communityTopicComment);


}
