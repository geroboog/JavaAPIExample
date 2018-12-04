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
import com.kangkai.mapper.app.ProductAlbumMapper;
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
import com.kangkai.pojo.ProductAlbum;
import com.kangkai.vo.BuyProductDetailVO;
import com.kangkai.pojo.ProductCollect;
import com.kangkai.pojo.ProductInfo;
import com.kangkai.vo.AttrVO;
import com.kangkai.vo.UserProductDetailVO;
import com.kangkai.vo.UserProductVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service(value="/productService")
@Transactional
public class ProductService implements IProductService{

	
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
	@Resource
	private ProductAlbumMapper productAlbumMapper;
	@Override
	public Json searchProductByTitleAndCategoryId(Integer current, Integer categoryId, Integer pageSize,
			String title) {
		Json json=new Json();

		List<UserProductVO> productList=null;
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		map.put("titleArr",title.split(" "));
		map.put("categoryId", categoryId);
		productList=productMapper.searchProductByTitleAndCategoryId(map);	
		if(productList!=null&&productList.size()>0)
		{
			json.setCode(100);
			json.setData(productList);
		}else
		{
			json.setCode(112);
		}
 
		
		return json;
	}
	@Override
	public Json getProductDetail(Integer productId,Integer userId) {
		Json json=new Json();
		UserProductDetailVO userProductDetailVO=productMapper.getProductDetail(productId);	
		
		if(userProductDetailVO!=null)
		{
			ProductInfo productInfo=productInfoMapper.selectProductInfo(productId);
			List<ProductAttr> attrList=checkProductAttr(productId);
			List<ProductAlbum> productAlbumList=productAlbumMapper.selectProductAlbumList(productId);
			int isCollect=checkProductCollect(productId,userId);
			userProductDetailVO.setProductAlbumList(productAlbumList);
			userProductDetailVO.setIsCollect(isCollect);
			userProductDetailVO.setProductAttrList(attrList);
			userProductDetailVO.setProductInfo(productInfo);
			userProductDetailVO.setCollectNum(checkProductCollectNum(productId));
			userProductDetailVO.setEvaluationRate(calculateEvaluationRate(productInfo));
			json.setCode(100);
			json.setData(userProductDetailVO);
		}else
		{
			json.setCode(112);
		}
 
		return json;
	}
	private int checkProductCollectNum(Integer productId)
	{
		int collectNum=productCollectMapper.countProductCollectNum(productId);
		return collectNum;
	}
	private double calculateEvaluationRate(ProductInfo productInfo)
	{
		double evaluationRate=productInfo.getEvaluation()/Constants.EVALUATIONMAX;
		return evaluationRate;
	}
	private int checkProductCollect(Integer productId,Integer userId)
	{
		if(userId!=null)
		{
			Map<String,Object> collectMap=new HashMap<String,Object>();
			collectMap.put("productId", productId);
			collectMap.put("userId", userId);
			ProductCollect collect=productCollectMapper.getProductCollect(collectMap);
			if(collect!=null)
			{	
				return collect.getIsDelete();
			}else
			{
				return 0;
			}
		}else
		{
			return 0;
		}
	}
	private List<ProductAttr> checkProductAttr(Integer productId)
	{
			List<ProductAttr> productAttr=productAttrMapper.getProductAttrs(productId);
			if(productAttr!=null&&productAttr.size()>0)
			{
				return productAttr;
			}else
			{
				return new ArrayList<ProductAttr>();
			}
	}
	@Override
	public Json getBuyProductDetail(Integer userId, String cartitemIds,  String token) {
		Json json=new Json();
		User user=userMapper.selectById(userId);
		
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		try
		{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("cartitemIds", cartitemIds);
		List<UserCartitemVO> productList=productMapper.selectCartitemProductListByCartitemIds(map);
		BuyProductDetailVO buyProductDetailVO=getBuyProductDetailVO(productList,userId,null);
		json.setCode(100);
		json.setData(buyProductDetailVO);
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			json.setCode(112);
		}
 
		
		return json;
	}
	private BuyProductDetailVO getBuyProductDetailVO(List<UserCartitemVO> productList,Integer userId,ProductOrder productOrder)
	{
		Double money=0.0;
		Double freight=0.0;
		AddressVO addressVO=null;
		BuyProductDetailVO buyProductDetailVO=new BuyProductDetailVO();
		if(productOrder!=null)
		{
			money=productOrder.getFinalPrice();
			buyProductDetailVO.setCouponId(productOrder.getCouponId());
			addressVO=new AddressVO();
			addressVO.setAddress(productOrder);
		}else
		{
			for(int i=0;i<productList.size();i++)
			{
				money+=productList.get(i).getProductPrice()*productList.get(i).getCount();
			}
			//获取默认地址
			addressVO=addressMapper.selectUserDefaultAddress(userId);
		}
		
		if(addressVO!=null)
		{
		buyProductDetailVO.setAddress(addressVO);
		}else
		{
		buyProductDetailVO.setPayable(0);
		}
		
		if(productOrder!=null&&productOrder.getCouponId()>0)
		{
			Coupon coupon=couponMapper.selectById(productOrder.getCouponId());
			CouponVO couponVO=new CouponVO();
			couponVO.setCouponId(coupon.getCouponId());
			couponVO.setCouponName(coupon.getCouponName());
			couponVO.setExpiryDate(coupon.getExpiryDate());
			couponVO.setLimitMoney(coupon.getLimitMoney());
			couponVO.setType(coupon.getType());
			couponVO.setWorth(coupon.getWorth());
			buyProductDetailVO.setCoupon(couponVO);
		}
		//在这里计算运费
		if(productOrder==null)
		{
		freight=0.0;
		}else
		{
			freight=productOrder.getFreight();
		}

		buyProductDetailVO.setMoney(money);
		buyProductDetailVO.setProductList(productList);
		buyProductDetailVO.setFreight(freight);
		return buyProductDetailVO;
	}
	@Override
	public Json getCouponList(Integer userId, Double money, String token) {
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
		map.put("limitMoney", money);
		map.put("type", 0);
		List<Coupon> couponList=couponMapper.selectCouponByLimitMoneyAndUserId(map);
		if(couponList!=null)
		{
			json.setCode(100);
			json.setData(couponList);
		}else
		{
			json.setCode(112);
		}
		return json;
	}
	@Override
	public Json getMakerList(Integer userId, String token,Integer current,Integer pageSize) {
		Json json=new Json();
		User user=userMapper.selectById(userId);
		
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		List<Maker> makerList=makerMapper.selectByMap(map);
		if(makerList!=null)
		{
			json.setCode(100);
			json.setData(makerList);
		}else
		{
			json.setCode(112);
		}
		return json;
	}
	@Override
	public Json getBuyProductDetailForOrder(Integer userId, Integer productOrderId, String token) {
		Json json=new Json();
		User user=userMapper.selectById(userId);
		
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		try
		{
		Map<String,Object> map=new HashMap<String,Object>();
		List<UserCartitemVO> productList=productMapper.selectProductListByProductOrderId(productOrderId);
		ProductOrder productOrder=productOrderMapper.selectById(productOrderId);
		BuyProductDetailVO buyProductDetailVO=getBuyProductDetailVO(productList,userId,productOrder);
		json.setCode(100);
		json.setData(buyProductDetailVO);
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			json.setCode(112);
		}
 
		
		return json;
	}
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
	public Json buyProduct(Integer userId, String cartitemIds, Integer addressId,Integer shopId, Integer couponId,Integer makerId,String invitedCode,String remark, String token) {
		Json json=new Json();
		User user=userMapper.selectById(userId);
		
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		try
		{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("cartitemIds", cartitemIds);
		List<UserCartitemVO> productList=productMapper.selectCartitemProductListByCartitemIds(map);
		json=getPayWasteNumAndPutOrder(productList,userId,couponId,addressId,shopId,null,makerId,invitedCode,remark,token);
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			json.setCode(112);
		}
 
		
		return json;
	}
	@Override
	public Json buyProductForOrder(Integer userId, Integer productOrderId, String token) {
		Json json=new Json();
		User user=userMapper.selectById(userId);
		
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		try
		{
		ProductOrder productOrder=productOrderMapper.selectById(productOrderId);
		
		json=getPayWasteNumAndPutOrder(null,userId,null,null,null,productOrder,null,null,null,token);

		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			json.setCode(112);
		}
 
		
		return json;
	}
	
	private Json getPayWasteNumAndPutOrder(List<UserCartitemVO> productList,Integer userId,Integer couponId,Integer addressId,Integer shopId,ProductOrder productOrder,Integer makerId,String invitedCode,String remark,String token)
	{
		Json json=new Json();
		Double money=0.0;
		Double freight=0.0;
		BuyProductDetailVO buyProductDetailVO=new BuyProductDetailVO();
		String productNames="";
		String productOrderNum="";
		String wasteNum="";
		String postcode="";
		if(productOrder!=null)
		{
			wasteNum=wasteNumService.getProductWasteNum(userId,productOrder.getProductOrderNum());
			json.setCode(100);
			json.setData(wasteNum);
		}else
		{
			//**防止过度插入
			ProductOrder productOrderc=productOrderMapper.selectLastProductOrderByUserId(userId);
			long checkTime=System.currentTimeMillis()-productOrderc.getCreateTime().getTime();
			if(checkTime<10000)
			{
				json.setCode(118);
				return json;
			}
						
			//接下来对所有的商品进行价格计算生成价格
			double totalPrice=0.0;
			double finalPrice=0.0;
			for(int i=0;i<productList.size();i++)
			{
				if(productList.get(i).getCustomPrice()>0.0)
				{
				totalPrice+=productList.get(i).getCustomPrice()*productList.get(i).getCount();
				}else
				{
				totalPrice+=productList.get(i).getProductPrice()*productList.get(i).getCount();	
				}
			}
			finalPrice=totalPrice;
			
			//首先生成订单号
			productOrderNum=orderNumService.getProductOrderNum(userId);
			productOrder=new ProductOrder();
			productOrder.setProductOrderNum(productOrderNum);
			
			//普通的地址处理
			if(addressId!=null)
			{
			//接下来对地址进行处理
			Address addressVO=addressMapper.selectById(addressId);	
			productOrder.setAddress(addressVO.getDetailAddr());
			productOrder.setToProvince(addressVO.getProvince());
			productOrder.setToCity(addressVO.getCity());
			productOrder.setRecipient(addressVO.getName());
			productOrder.setPhone(addressVO.getPhone());
			productOrder.setToDistrict(addressVO.getDistrict());
			}
			//寄存处理
			if(shopId!=null)
			{
				Shop shop=shopMapper.selectById(shopId);
				productOrder.setAddress(shop.getShopAddress());
				productOrder.setToProvince(shop.getProvince());
				productOrder.setToCity(shop.getCity());
				productOrder.setRecipient(shop.getShopName());
				productOrder.setPhone(shop.getPhone());
				productOrder.setToDistrict(shop.getDistrict());
			}
			
			if(makerId!=null)
			{
				productOrder.setMakerId(makerId);
			}
			productOrder.setUserId(userId);
			//优惠券处理
			if(couponId!=null&&couponId!=0)
			{
				Coupon coupon=couponMapper.selectById(couponId);
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("isUse", 1);
				map.put("couponId", couponId);
				couponMapper.updateIsUseById(map);
				finalPrice=finalPrice-coupon.getWorth();
				productOrder.setCouponId(couponId);
			}
			
			//TODO运费
			
			productOrder.setTotalPrice(totalPrice);
			productOrder.setFinalPrice(finalPrice);
			productOrder.setState(Constants.PRODUCTNEEDEDPAY_STATE);
			productOrder.setFreight(freight);
			productOrder.setPostcode(postcode);
			productOrder.setInvitedCode(invitedCode);
			productOrder.setRemark(remark);
			productOrderMapper.insertProductOrder(productOrder);
			
			for(int i=0;i<productList.size();i++)
			{
				UserCartitemVO product=productList.get(i);
				ProductOrderItem orderitem=new ProductOrderItem();
				orderitem.setCustomAttrs(product.getCustomAttrs());
				orderitem.setAttrs(product.getAttrs());
				orderitem.setProductTitle(product.getProductTitle());
				orderitem.setCount(product.getCount());
				orderitem.setProductShowImg(product.getProductShowImg());
				orderitem.setType(product.getType());		
				orderitem.setProductId(product.getProductId());
				orderitem.setProductOrderId(productOrder.getProductOrderId());
				orderitem.setProductNum(product.getProductNum());
				productNames+=product.getProductTitle()+"，";
				if(product.getCustomPrice()>0)
				{
				orderitem.setProductPrice(product.getCustomPrice());
				}else
				{
				orderitem.setProductPrice(product.getProductPrice());	
				}
				productOrderItemMapper.insertOrderItem(orderitem);
			}
			
			
			
			//首先生成流水号
			wasteNum=wasteNumService.getProductWasteNum(userId,productOrderNum);
			if(shopId!=null)
			{
				wardrobeService.sendClothesToShop(userId, token, null, productOrder.getProductOrderId(), shopId,"","",null);
			}
			//处理寄存
			json.setCode(100);
			json.setData(wasteNum);
		
		}
		
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
	@Override
	public Json getShopList(Integer userId, String token, Integer current, Integer pageSize) {
		Json json=new Json();
		User user=userMapper.selectById(userId);
		
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		List<Shop> shopList=shopMapper.selectByMap(map);
		if(shopList!=null)
		{
			json.setCode(100);
			json.setData(shopList);
		}else
		{
			json.setCode(112);
		}
		return json;
	}
	
	
	
}
