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
import com.kangkai.mapper.app.ShopMapper;
import com.kangkai.mapper.app.StoreItemMapper;
import com.kangkai.mapper.app.StoreMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.mapper.util.BannerMapper;
import com.kangkai.mapper.util.ExpressMapper;
import com.kangkai.pojo.Address;
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
import com.kangkai.pojo.Shop;
import com.kangkai.pojo.Store;
import com.kangkai.pojo.StoreItem;
import com.kangkai.pojo.User;
import com.kangkai.service.appService.ICategoryService;
import com.kangkai.service.appService.ICommunityService;
import com.kangkai.service.appService.IProductService;
import com.kangkai.service.appService.IWardrobeService;
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
import com.kangkai.vo.UserProductOrderItemVO;
import com.kangkai.vo.UserSimpleVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service(value="/wardrobeService")
@Transactional
public class WardrobeService implements IWardrobeService{

	@Resource
	private UserMapper userMapper;
	@Resource
	private ProductOrderMapper productOrderMapper;
	@Resource
	private ShopMapper shopMapper;
	@Resource
	private StoreMapper storeMapper;
	@Resource
	private ProductOrderItemMapper productOrderItemMapper;
	@Resource
	private StoreItemMapper storeItemMapper;
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public Json getClothesList(Integer userId, String token, String season,Integer current,Integer pageSize) {
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
	public Json sendClothesToShop(Integer userId, String token, Integer productOrderItemId,Integer productOrderId,Integer shopId,String phone,String remark,Date storeTime) {
		Json json=new Json();
		
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		List<ProductOrderItem> productOrderItemList=new ArrayList<ProductOrderItem>();
		if(productOrderId!=null)
		{
			ProductOrder productOrder=productOrderMapper.selectById(productOrderId);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("productOrderId", productOrderId);
			productOrder.setIsStore(Constants.CLOTHES_SLOVING);
			
			productOrderMapper.updateIsStore(productOrder);
			productOrderItemList=productOrderItemMapper.selectProductOrderItemListNo(map);
		}
		if(productOrderItemId!=null)
		{
			ProductOrderItem productOrderItem=productOrderItemMapper.selectById(productOrderItemId);
			productOrderItemList.add(productOrderItem);
			if(!productOrderItem.getIsStore().equals(Constants.IS_USE_YES))
			{
				json.setCode(118);
				return json;
			}
		}
		//插入寄存
		Shop shop=shopMapper.selectById(shopId);
		Store store=new Store();
		store.setShopAddress(shop.getShopAddress());
		store.setShopName(shop.getShopName());
		store.setShopId(shopId);
		store.setUserId(userId);
		store.setStoreTime(storeTime);
		store.setPhone(phone);
		store.setRemark(remark);
		storeMapper.insertStore(store);
		
		for(int i=0;i<productOrderItemList.size();i++)
		{
		//插入寄存物品
		StoreItem storeItem=new StoreItem();
		storeItem.setProductOrderItemId(productOrderItemList.get(i).getProductOrderItemId());
		storeItem.setStoreId(store.getStoreId());
		storeItemMapper.insertStoreItem(storeItem);
		}
		//更新状态
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("productOrderItemId", productOrderItemId);
		map.put("productOrderId", productOrderId);
		map.put("isStore", Constants.CLOTHES_SLOVING);
		productOrderItemMapper.updateIsStore(map);
		json.setCode(100);
		
		return json;
	}
	
	
	
	
	
	
}
