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
import com.kangkai.mapper.app.CouponMapper;
import com.kangkai.mapper.app.MakerMapper;
import com.kangkai.mapper.app.ProductAttrMapper;
import com.kangkai.mapper.app.ProductCollectMapper;
import com.kangkai.mapper.app.ProductInfoMapper;
import com.kangkai.mapper.app.ProductMapper;
import com.kangkai.mapper.app.ProductOrderItemMapper;
import com.kangkai.mapper.app.ProductOrderMapper;
import com.kangkai.mapper.app.RedPacketMapper;
import com.kangkai.mapper.app.ShopMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.mapper.util.ExpressMapper;
import com.kangkai.pojo.Cartitem;
import com.kangkai.pojo.Category;
import com.kangkai.pojo.Coupon;
import com.kangkai.pojo.Maker;
import com.kangkai.pojo.ProductAttr;
import com.kangkai.pojo.User;
import com.kangkai.service.appService.ICollectService;
import com.kangkai.service.appService.IProductService;
import com.kangkai.service.appService.IWardrobeService;
import com.kangkai.service.utilService.IOrderNumService;
import com.kangkai.service.utilService.IWasteNumService;
import com.kangkai.utils.ArrUtils;
import com.kangkai.utils.Constants;
import com.kangkai.utils.DateUtils;
import com.kangkai.utils.JPUSHUtils;
import com.kangkai.utils.Json;
import com.kangkai.utils.LogisticsUtils;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;
import com.kangkai.vo.LogisticsInfoVO;
import com.kangkai.vo.UserCartitemVO;
import com.kangkai.vo.UserClothesVO;
import com.kangkai.vo.UserSimpleVO;
import com.kangkai.pojo.Address;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.ProductOrderItem;
import com.kangkai.pojo.RedPacket;
import com.kangkai.pojo.Shop;
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


@Service(value="/collectService")
@Transactional
public class CollectService implements ICollectService{

	
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());
	@Resource
	private UserMapper userMapper;
	@Resource
	private ExpressMapper expressMapper;
	@Resource
	private ProductMapper productMapper;
	@Resource
	private ProductCollectMapper productCollectMapper;
	@Resource
	private ProductInfoMapper productInfoMapper;
	@Resource
	private ProductAttrMapper productAttrMapper;
	@Resource
	private AddressMapper addressMapper;
	@Resource
	private CouponMapper couponMapper;
	@Resource
	private ProductOrderMapper productOrderMapper;
	@Resource
	private MakerMapper makerMapper;
	@Resource
	private IWasteNumService wasteNumService;
	@Resource
	private IOrderNumService orderNumService;
	@Resource
	private ProductOrderItemMapper productOrderItemMapper;
	@Resource
	private ShopMapper shopMapper;
	@Resource
	private IWardrobeService wardrobeService;
	@Resource
	private RedPacketMapper redPacketMapper;
	
	@Override
	public Json collectProduct(Integer userId, String token, Integer productId) {
		Json json=new Json();
		User user=userMapper.selectById(userId);
		
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("productId", productId);
		
		ProductCollect collect=productCollectMapper.getProductCollect(map);
		if(collect!=null)
		{
			if(collect.getIsDelete()==0)
			{
				collect.setIsDelete(1);
			}else
			{
				collect.setIsDelete(0);
			}
			productCollectMapper.updateProductCollect(collect);
		}else
		{
			ProductCollect productCollect=new ProductCollect();
			productCollect.setProductId(productId);
			productCollect.setUserId(userId);
			productCollectMapper.insertProductCollect(productCollect);
		}
		json.setCode(100);
 
		
		return json;
	}
	
	@Override
	public Json getProductCollectList(Integer userId, String token, Integer current, Integer pageSize) {
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
		List<UserProductVO> productList=productCollectMapper.selectProductCollectList(map);
		if(productList!=null)
		{
			json.setCode(100);
			json.setData(productList);
		}else
		{
			json.setCode(112);
		}
		return json;
	}
	
	
	
}
