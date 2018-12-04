package com.kangkai.service.appService.impl;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.mapper.app.CategoryMapper;
import com.kangkai.mapper.app.CommunityFollowMapper;
import com.kangkai.mapper.app.CommunityMapper;
import com.kangkai.mapper.app.CommunityTopicCommentMapper;
import com.kangkai.mapper.app.CommunityTopicMapper;
import com.kangkai.mapper.app.ProductOrderItemMapper;
import com.kangkai.mapper.app.ProductOrderMapper;
import com.kangkai.mapper.app.UserInfoMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.mapper.util.BannerMapper;
import com.kangkai.mapper.util.ExpressMapper;
import com.kangkai.pojo.Banner;
import com.kangkai.pojo.Cartitem;
import com.kangkai.pojo.Category;
import com.kangkai.pojo.Community;
import com.kangkai.pojo.CommunityFollow;
import com.kangkai.pojo.CommunityTopic;
import com.kangkai.pojo.CommunityTopicComment;
import com.kangkai.pojo.Coupon;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.ProductOrderItem;
import com.kangkai.pojo.User;
import com.kangkai.service.appService.ICategoryService;
import com.kangkai.service.appService.ICommunityService;
import com.kangkai.service.appService.IProductService;
import com.kangkai.service.utilService.IBannerService;
import com.kangkai.service.utilService.impl.RedisService;
import com.kangkai.utils.ArrUtils;
import com.kangkai.utils.Constants;
import com.kangkai.utils.DateUtils;
import com.kangkai.utils.JPUSHUtils;
import com.kangkai.utils.Json;
import com.kangkai.utils.LogisticsUtils;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;
import com.kangkai.vo.CommunityTopicDetailVO;
import com.kangkai.vo.CommunityTopicVO;
import com.kangkai.vo.LogisticsInfoVO;
import com.kangkai.vo.UserClothesVO;
import com.kangkai.vo.UserCommunityInfo;
import com.kangkai.vo.UserCommunityTopicCommentVO;
import com.kangkai.vo.UserCommunityVO;
import com.kangkai.vo.UserInfoVO;
import com.kangkai.vo.UserProductOrderItemVO;
import com.kangkai.vo.UserSimpleVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service(value="/coummunityService")
@Transactional
public class CommunityService implements ICommunityService{

	
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());
	@Resource
	private UserMapper userMapper;
	@Resource
	private CommunityMapper communityMapper;
	@Resource
	private CommunityTopicMapper communityTopicMapper;
	@Resource
	private CommunityTopicCommentMapper communityTopicCommentMapper;
	@Resource
	private CommunityFollowMapper communityFollowMapper;
	@Resource
	private ProductOrderItemMapper productOrderItemMapper;
	@Resource
	private ProductOrderMapper productOrderMapper;
	@Resource
	private UserInfoMapper userInfoMapper;
	private RedisService redisService=new RedisService();
	
	@Override
	public Json getCommunityList(Integer isHot,Integer current,Integer pageSize) {
		Json json=new Json();
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		map.put("isHot",isHot);
		List<Community> communityList=null;
		
		communityList=communityMapper.selectCommunityList(map);
			
		
		if(communityList!=null&&communityList.size()>0)
		{
			json.setCode(100);
			json.setData(communityList);
		}else
		{
			json.setCode(112);
		}
 
		
		return json;
	}
	@Override
	public Json getCommunityTopicList(Integer communityId,Integer userId, Integer current, Integer pageSize) {
		Json json=new Json();
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		map.put("communityId",communityId);
		map.put("userId",userId);
		List<CommunityTopicVO> communityList=communityTopicMapper.selectCommunityTopicVOList(map);	
		
		if(communityList!=null&&communityList.size()>0)
		{
			json.setCode(100);
			json.setData(communityList);
		}else
		{
			json.setCode(112);
		}
 
		
		return json;
	}
	@Override
	public Json getCommunityTopicCommentList(Integer communityTopicId, Integer current, Integer pageSize) {
		Json json=new Json();
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		map.put("communityTopicId",communityTopicId);
		List<UserCommunityTopicCommentVO> communityTopicCommentList=communityTopicCommentMapper.selectCommunityTopicCommentList(map);	
		
		if(communityTopicCommentList!=null&&communityTopicCommentList.size()>0)
		{
			json.setCode(100);
			json.setData(communityTopicCommentList);
		}else
		{
			json.setCode(112);
		}
 
		
		return json;
	}
	@Override
	public Json releaseCommunityTopic(Integer communityId,Integer productOrderItemId, Integer userId,String token,String title,String content) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper,token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		CommunityTopic communityTopic=new CommunityTopic();
		Map<String,Object> map=new HashMap<String,Object>();
		ProductOrderItem productOrderItem =productOrderItemMapper.selectById(productOrderItemId);
		Integer productId=productOrderItem.getProductId();
		Integer productOrderId=productOrderItem.getProductOrderId();
		map.put("begin", 0);
		map.put("pageSize", 1);
		map.put("communityId", communityId);
		map.put("productOrderId", productOrderId);
		map.put("productId", productId);
		map.put("userId", userId);
		
		
		Integer topicNum=communityTopicMapper.countCommunityTopicList(map);	
		if(topicNum.intValue()<1)
		{
			communityTopic.setTitle(title);
			communityTopic.setCommunityId(communityId);
			communityTopic.setProductId(productId);
			communityTopic.setProductOrderId(productOrderId);
			communityTopic.setContent(content);
			communityTopic.setUserId(userId);
			communityTopic.setProductShowImg(productOrderItem.getProductShowImg());
			communityTopic.setProductTitle(productOrderItem.getProductTitle());
			communityTopicMapper.insertCommunityTopic(communityTopic);
		}
		if(communityTopic.getCommunityTopicId()!=null)
		{
			json.setCode(100);
		}else
		{
			json.setCode(118);
		}
 
		
		return json;
	}
	
	@Override
	public Json commentCommunityTopic( Integer userId,String token,Integer communityTopicId,String content) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper,token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		CommunityTopicComment communityTopicComment=new CommunityTopicComment();
		communityTopicComment.setCommunityTopicId(communityTopicId);
		communityTopicComment.setContent(content);
		communityTopicComment.setUserId(userId);
		communityTopicCommentMapper.insertCommunityTopicComment(communityTopicComment);
		if(communityTopicComment.getCommunityTopicCommentId()!=null)
		{
			json.setCode(100);
		}else
		{
			json.setCode(112);
		}
 
		
		return json;
	}
	@Override
	public Json getUserCommunityInfo(Integer userId,String token) {
		Json json=new Json();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		Integer communityTopicNum=communityTopicMapper.countCommunityTopicList(map);
		Integer communityFollowNum= communityFollowMapper.countCommunityFollowList(map);
		UserInfoVO userInfo=userInfoMapper.getUserInfoVO(userId);
		UserCommunityInfo userCommunityInfo=new UserCommunityInfo();
		if(communityTopicNum!=null)
		{
		userCommunityInfo.setUserCommunityNum(communityTopicNum);
		}
		if(communityFollowNum!=null)
		{
		userCommunityInfo.setUserCommunityTopicNum(communityFollowNum);
		}
		userCommunityInfo.setUserInfo(userInfo);
		json.setCode(100);
		json.setData(userCommunityInfo);
		// TODO Auto-generated method stub
		return json;
	}
	@Override
	public Json followCommunity(Integer communityId, Integer userId,String token) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper,token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("communityId", communityId);
		map.put("userId", userId);
		CommunityFollow communityFollow=communityFollowMapper.getCommunityFollow(map);	
		if(communityFollow!=null)
		{
			if(communityFollow.getIsDelete().equals(0))
			{
				communityFollow.setIsDelete(1);
			}else
			{
				communityFollow.setIsDelete(0);
			}
			communityFollowMapper.updateCommunityFollow(communityFollow);
		}else
		{
			communityFollow=new CommunityFollow();
			communityFollow.setUserId(userId);
			communityFollow.setCommunityId(communityId);
			communityFollowMapper.insertCommunityFollow(communityFollow);
			
		}
			json.setCode(100);
		
		return json;
	}
	@Override
	public Json getCommunityTopicDetail(Integer communityTopicId) {
		Json json=new Json();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("communityTopicId",communityTopicId);
		CommunityTopicDetailVO communityDetail=communityTopicMapper.selectCommunityTopicVO(map);	
		
		if(communityDetail!=null)
		{
			json.setCode(100);
			json.setData(communityDetail);
		}else
		{
			json.setCode(112);
		}
 
		
		return json;
	}
	@Override
	public Json getCommunityFollowList(Integer userId, String token, Integer current, Integer pageSize) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper,token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		map.put("userId",userId);
		List<Community> communityList=communityMapper.selectUserFollowCommunityList(map);	
		
		if(communityList!=null&&communityList.size()>0)
		{
			json.setCode(100);
			json.setData(communityList);
		}else
		{
			json.setCode(112);
		}
 
		
		return json;
	}
	@Override
	public Json getProductCommunityTopicList(Integer userId, String token, Integer productId,Integer current,Integer pageSize) {
		Json json=new Json();
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		map.put("productId",productId);
		List<CommunityTopicVO> communityList=communityTopicMapper.selectCommunityTopicVOList(map);	
		
		if(communityList!=null&&communityList.size()>0)
		{
			json.setCode(100);
			json.setData(communityList);
		}else
		{
			json.setCode(112);
		}
 
		
		return json;
	}
	@Override
	public Json getClothesList(Integer userId, String token, String season, Integer current, Integer pageSize) {
		Json json=new Json();
		
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		map.put("userId", userId);
		map.put("season",season);
		List<UserClothesVO> clothesList=productOrderMapper.selectClothesByUserIdAndSeason(map);
		if(clothesList!=null)
		{
			json.setCode(100);
			json.setData(clothesList);
		}else
		{
			json.setData(clothesList);	
		}
		return json;
	}
	@Override
	public Json getCommunityTopicFollowList(Integer userId, String token, Integer current, Integer pageSize) {
		Json json=new Json();
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		map.put("userId",userId);
		List<CommunityTopicVO> communityList=communityTopicMapper.selectCommunityFollowTopicVOList(map);	
		
		if(communityList!=null&&communityList.size()>0)
		{
			json.setCode(100);
			json.setData(communityList);
		}else
		{
			json.setCode(112);
		}
 
		
		return json;
	}
	@Override
	public Json getCommunityInfo(Integer communityId,Integer userId) {
		Json json=new Json();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("communityId", communityId);
		map.put("userId", userId);
		UserCommunityVO userCommunityVO=communityMapper.selectById(map);	
		
		if(userCommunityVO!=null)
		{
			json.setCode(100);
			json.setData(userCommunityVO);
		}else
		{
			json.setCode(112);
		}
 
		
		return json;
	}
	
	
	
	
}
