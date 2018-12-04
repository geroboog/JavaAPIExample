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

import com.kangkai.service.appService.IProductOrderService;
import com.kangkai.mapper.app.CouponMapper;
import com.kangkai.mapper.app.MakerMapper;
import com.kangkai.mapper.app.ProductOrderItemMapper;
import com.kangkai.mapper.app.ProductOrderMapper;
import com.kangkai.mapper.app.ProductOrderMessageMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.mapper.util.ExpressMapper;
import com.kangkai.pojo.Cartitem;
import com.kangkai.pojo.Category;
import com.kangkai.pojo.Coupon;
import com.kangkai.pojo.Maker;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.User;
import com.kangkai.utils.ArrUtils;
import com.kangkai.utils.Constants;
import com.kangkai.utils.DateUtils;
import com.kangkai.utils.JPUSHUtils;
import com.kangkai.utils.Json;
import com.kangkai.utils.LogisticsUtils;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;
import com.kangkai.vo.AddressVO;
import com.kangkai.vo.BuyProductDetailVO;
import com.kangkai.vo.LogisticsInfoVO;
import com.kangkai.vo.UserProductOrderDetailVO;
import com.kangkai.vo.UserProductOrderItemVO;
import com.kangkai.vo.UserProductOrderMessageVO;
import com.kangkai.vo.UserProductOrderProductVO;
import com.kangkai.vo.UserProductOrderVO;
import com.kangkai.vo.UserProductVO;
import com.kangkai.vo.UserSimpleVO;

import jxl.HeaderFooter.Contents;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service(value="/productOrderService")
@Transactional
public class ProductOrderService implements IProductOrderService{

	@Resource
	private UserMapper userMapper;
	@Resource
	private ExpressMapper expressMapper;
	@Resource
	private ProductOrderMapper productOrderMapper;
	@Resource
	private ProductOrderItemMapper productOrderItemMapper;
	@Resource
	private ProductOrderMessageMapper productOrderMessageMapper;
	@Resource
	private ProductOrderMessageService productOrderMessageService;
	@Resource
	private CouponMapper couponMapper;
	@Resource
	private MakerMapper makerMapper;
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public Json getProductOrderDetail(Integer userId, Integer productOrderId, String token) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}

		Map<String,Object> map=new HashMap<String,Object>();
		ProductOrder productOrder=productOrderMapper.selectById(productOrderId);
		if(productOrder!=null)
		{
			UserProductOrderDetailVO productOrderDetailVO=new UserProductOrderDetailVO();
			map.put("productOrderId", productOrder.getProductOrderId());
			List<UserProductOrderItemVO> productList=productOrderItemMapper.selectProductOrderItemList(map);
			AddressVO addressVO=new AddressVO();
			addressVO.setAddress(productOrder);
			
			productOrderDetailVO.setProductOrder(productOrder);
			productOrderDetailVO.setAddressVO(addressVO);
			productOrderDetailVO.setProductList(productList);
			
			if(productOrder.getCouponId()!=null&&productOrder.getCouponId()>0)
			{
				Coupon coupon=couponMapper.selectById(productOrder.getCouponId());
				productOrderDetailVO.setCoupon(coupon);
			}
			if(productOrder.getMakerId()!=null&&productOrder.getMakerId()>0)
			{
				Maker maker=makerMapper.selectById(productOrder.getMakerId());
				productOrderDetailVO.setMaker(maker);	
			}
			if(productOrder.getCourierNum()!=null&&!productOrder.getCourierNum().equals(""))
			{
				List<LogisticsInfoVO> li=LogisticsUtils.getLogisticsUtil(productOrder.getCourier(), productOrder.getCourierNum(), expressMapper);
				
				productOrderDetailVO.setLogisticsInfoList(li);
			}
			json.setCode(100);
			json.setData(productOrderDetailVO);
		}else
		{
			json.setCode(112);
		}
 
		return json;
	}

	@Override
	public Json getProductOrderList(Integer userId, Integer state, String token,Integer current,Integer pageSize) {
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
		map.put("state", state);
		
		if(state!=null)
		{
			productOrderMessageService.updateProductOrderMessage(userId, state, 0);
		}
		
		List<UserProductOrderProductVO> userProductOrderProductVO=productOrderMapper.selectProductOrderProductList(map);
		List<UserProductOrderVO> userProductOrderList=new  ArrayList<UserProductOrderVO>();
		if(userProductOrderProductVO!=null && userProductOrderProductVO.size()>0)
		{
			UserProductOrderVO userProductOrderVO=new UserProductOrderVO();
			
			List<UserProductOrderItemVO> userProductVOList=new ArrayList<UserProductOrderItemVO>();
			
			UserProductOrderItemVO userProductVO=new UserProductOrderItemVO();
			userProductVO.setProductOrderProduct(userProductOrderProductVO.get(0));
			userProductVOList.add(userProductVO);
			userProductOrderVO.setProductOrder(userProductOrderProductVO.get(0));
			userProductOrderVO.setProductList(userProductVOList);
			
			userProductOrderList.add(userProductOrderVO);
			for(int i=1;i<userProductOrderProductVO.size();i++)
			{
				int nowSize=userProductOrderList.size();
				if(userProductOrderProductVO.get(i).getProductOrderId().equals(userProductOrderList.get(nowSize-1).getProductOrderId()))
				{
					UserProductOrderItemVO userProductOrderItemVO2=new UserProductOrderItemVO();
					userProductOrderItemVO2.setProductOrderProduct(userProductOrderProductVO.get(i));
					userProductOrderList.get(nowSize-1).getProductList().add(userProductOrderItemVO2);
				}else
				{
					UserProductOrderVO userProductOrderVO2=new UserProductOrderVO();
					List<UserProductOrderItemVO> userProductVOList2=new ArrayList<UserProductOrderItemVO>();
					UserProductOrderItemVO userProductOrderItemVO2=new UserProductOrderItemVO();
					userProductOrderItemVO2.setProductOrderProduct(userProductOrderProductVO.get(i));
					
					userProductVOList2.add(userProductOrderItemVO2);
					userProductOrderVO2.setProductOrder(userProductOrderProductVO.get(i));
					userProductOrderVO2.setProductList(userProductVOList2);
					userProductOrderList.add(userProductOrderVO2);
				}
			}
			json.setCode(100);
			json.setData(userProductOrderList);
		}else
		{
			json.setCode(112);
		}
 
		return json;
	}

	@Override
	public Json getProductOrderLogisticsInfoList(Integer userId, String token, Integer productOrderId) {
		Json json=new Json();
		ProductOrder productOrder=productOrderMapper.selectById(productOrderId);
		String courier=productOrder.getCourier();
		String courierNum=productOrder.getCourierNum();
		//获取物流
		if(productOrder.getCourier()!=null&&!productOrder.getCourier().equals(""))
		{
			List<LogisticsInfoVO> li=LogisticsUtils.getLogisticsUtil(courier, courierNum, expressMapper);
			json.setCode(100);
			json.setData(li);
		}else
		{
			json.setCode(112);
		}
 
		return json;
	}

	@Override
	public Json applyAfterSales(Integer userId, String token, Integer productOrderId) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}

		Map<String,Object> map=new HashMap<String,Object>();
		ProductOrder productOrder=productOrderMapper.selectById(productOrderId);
		if(productOrder!=null)
		{
			productOrder.setIsApplyAfterSales(Constants.IS_USE_NO);
			productOrderMapper.updateApplyAfterSales(productOrder);
			json.setCode(100);
		}else
		{
			json.setCode(112);
		}
 
		return json;
	}

	@Override
	public Json signProductOrder(Integer userId, String token, Integer productOrderId) {			
		Json json=new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}

		Map<String,Object> map=new HashMap<String,Object>();
		ProductOrder productOrder=productOrderMapper.selectById(productOrderId);
		if(productOrder!=null)
		{
			productOrder.setState(Constants.PRODUCTNEEDEDCOMMENT_STATE);
			productOrderMapper.updateProductOrderState(productOrder);
			json.setCode(100);
		}else
		{
			json.setCode(112);
		}
	 
			return json;
		}

	
	
	
	
}
