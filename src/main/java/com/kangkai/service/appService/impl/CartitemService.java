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

import com.kangkai.mapper.app.AddressMapper;
import com.kangkai.mapper.app.CartitemMapper;
import com.kangkai.mapper.app.CouponMapper;
import com.kangkai.mapper.app.MakerMapper;
import com.kangkai.mapper.app.ProductAttrMapper;
import com.kangkai.mapper.app.ProductCollectMapper;
import com.kangkai.mapper.app.ProductInfoMapper;
import com.kangkai.mapper.app.ProductMapper;
import com.kangkai.mapper.app.ProductOrderMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.mapper.util.ExpressMapper;
import com.kangkai.pojo.Cartitem;
import com.kangkai.pojo.Category;
import com.kangkai.pojo.Coupon;
import com.kangkai.pojo.Maker;
import com.kangkai.pojo.ProductAttr;
import com.kangkai.pojo.User;
import com.kangkai.service.appService.ICartitemService;
import com.kangkai.service.appService.IProductService;
import com.kangkai.utils.ArrUtils;
import com.kangkai.utils.Constants;
import com.kangkai.utils.DateUtils;
import com.kangkai.utils.JPUSHUtils;
import com.kangkai.utils.Json;
import com.kangkai.utils.LogisticsUtils;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;
import com.kangkai.vo.LogisticsInfoVO;
import com.kangkai.vo.UserSimpleVO;
import com.kangkai.vo.UserCartitemVO;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.vo.AddressVO;
import com.kangkai.vo.CouponVO;
import com.kangkai.pojo.Product;
import com.kangkai.vo.BuyProductDetailVO;
import com.kangkai.pojo.ProductCollect;
import com.kangkai.pojo.ProductInfo;
import com.kangkai.vo.AttrVO;
import com.kangkai.vo.UserProductDetailVO;
import com.kangkai.vo.UserProductVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service(value="/cartitemService")
@Transactional
public class CartitemService implements ICartitemService{

	@Resource
	private UserMapper userMapper;
	@Resource
	private CartitemMapper cartitemMapper;
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public Json getUserCartitem(Integer userId, Integer current, Integer pageSize, String token) {
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
		List<UserCartitemVO> userCartitemList=cartitemMapper.selectUserCartitemList(map);
		if(userCartitemList!=null&&userCartitemList.size()>0)
		{
			json.setCode(100);
			json.setData(userCartitemList);
		}else
		{
			json.setCode(112);
		}
		return json;
	}

	@Override
	public Json addUserCartitemProduct(Integer userId, Integer productId, String attrs, String token) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper,token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("productId", productId);
		map.put("attrs", attrs);
		map.put("userId", userId);
		//首先查看一下该商品是否已经在
		Cartitem cartitem=new Cartitem();
		cartitem.setAttrs(attrs);
		cartitem.setProductId(productId);
		cartitem.setCount(1);
		cartitem.setUserId(userId);
		Cartitem cartitemC=cartitemMapper.getCartitemProduct(cartitem);
		if(cartitemC!=null)
		{
			cartitemC.setCount(cartitemC.getCount()+1);
			cartitemMapper.updateCartitemProduct(cartitemC);
		}else
		{
			cartitemMapper.addCartitemProduct(cartitem);
			json.setCode(100);
		}
		return json;
	}

	@Override
	public Json updateUserCartitemProduct(Integer userId, Integer productId, String attrs, Integer count,
			String token) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper,token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("productId", productId);
		map.put("attrs", attrs);
		map.put("count", count);
		map.put("userId", userId);
		//首先查看一下该商品是否已经在
		Cartitem cartitem=new Cartitem();
		cartitem.setAttrs(attrs);
		cartitem.setProductId(productId);
		cartitem.setCount(1);
		cartitem.setUserId(userId);
		Cartitem cartitemC=cartitemMapper.getCartitemProduct(cartitem);
		if(cartitemC!=null)
		{
		if(count.equals(0))
		{
			cartitemMapper.deleteCartitemProduct(cartitemC.getCartitemId());
		}else
		{
			cartitemC.setCount(count);
			cartitemMapper.updateCartitemProduct(cartitemC);
		}
		}else
		{
			json.setCode(112);
		}
 
		json.setCode(100);
		return json;
	}
	
	
}
