package com.kangkai.service.appService.impl;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpRequest;
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
import com.kangkai.mapper.app.RecommendRedPacketMapper;
import com.kangkai.mapper.app.RecommendUserRelationshipMapper;
import com.kangkai.mapper.app.RedPacketMapper;
import com.kangkai.mapper.app.UserInfoMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.mapper.app.UserWalletMapper;
import com.kangkai.mapper.util.ExpressMapper;
import com.kangkai.pojo.Cartitem;
import com.kangkai.pojo.Category;
import com.kangkai.pojo.Coupon;
import com.kangkai.pojo.Maker;
import com.kangkai.pojo.ProductAttr;
import com.kangkai.pojo.User;
import com.kangkai.pojo.UserInfo;
import com.kangkai.pojo.UserWallet;
import com.kangkai.service.appService.ICartitemService;
import com.kangkai.service.appService.IProductService;
import com.kangkai.service.appService.IRecommendService;
import com.kangkai.service.appService.IUserService;
import com.kangkai.utils.ArrUtils;
import com.kangkai.utils.Constants;
import com.kangkai.utils.DateUtils;
import com.kangkai.utils.JPUSHUtils;
import com.kangkai.utils.Json;
import com.kangkai.utils.LogisticsUtils;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.QrCodeUtils;
import com.kangkai.utils.TokenUtil;
import com.kangkai.vo.LogisticsInfoVO;
import com.kangkai.vo.RecommendRelationshipNumVO;
import com.kangkai.vo.RecommendUserRelationshipVO;
import com.kangkai.vo.UserSimpleVO;
import com.kangkai.vo.UserWalletVO;
import com.kangkai.vo.UserCartitemVO;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.RecommendRedPacket;
import com.kangkai.pojo.RecommendUserRelationship;
import com.kangkai.pojo.RedPacket;
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


@Service(value="/recommendService")
@Transactional
public class RecommendService implements IRecommendService{

	@Resource
	private RedPacketMapper redPacketMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private RecommendRedPacketMapper recommendRedPacketMapper;
	@Resource
	private CouponMapper couponMapper;
	@Resource
	private ProductOrderMapper productOrderMapper;
	@Resource
	private RecommendUserRelationshipMapper recommendUserRelationshipMapper;
	@Resource
	private IUserService userService;
	@Resource
	private UserInfoMapper userInfoMapper;
	@Resource
	private UserWalletMapper userWalletMapper;
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public Json recommendCoupon(String phone, Integer redPacketId) {
		Json json=new Json();
		
		RedPacket redPacket=redPacketMapper.selectById(redPacketId);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("phone", phone);
		map.put("redPacketId", redPacket.getRedPacketId());
		RecommendRedPacket recommendRedPacket=recommendRedPacketMapper.selectByPhoneAndRedPacketId(map);
		try
		{
			if(recommendRedPacket==null)
			{
				User user=userMapper.selectByPhone(phone);
				Double money=0.0;
				Double limitMoney=0.0;
				if(user.getUserId().equals(redPacket.getUserId()))
				{
					money=redPacket.getMoney()*Constants.MYREDPACKETRATE;
				}else
				{
					money=redPacket.getMoney()*Constants.OTHERREDPACKETRATE;
				}
				
				limitMoney=money*Constants.LIMITMONEYRATE;
				recommendRedPacket=new RecommendRedPacket();
				recommendRedPacket.setRedPacketId(redPacketId);
				recommendRedPacket.setPhone(phone);
				recommendRedPacket.setMoney(money);
				
				recommendRedPacketMapper.insertRecommendRedPacket(recommendRedPacket);

				if(user!=null)
				{
					insertCouponForUser(money,limitMoney,user);
				}
			}
			json.setCode(100);
			json.setMsg("成功获取红包");
		}
		catch(Exception ex)
		{
			json.setCode(112);
			System.out.println(ex.getMessage());
		}
		return json;
	}
	
	private void insertCouponForUser(Double money,Double limitMoney,User user)
	{
		Calendar calendar=Calendar.getInstance();
		Coupon coupon=new Coupon();
		coupon.setCouponName(money+"元优惠券");
		coupon.setExpiryDate(DateUtils.calculateCalendarForMonth(new Date(), calendar, 1).getTime());
		coupon.setLimitMoney(limitMoney);
		coupon.setType(0);
		coupon.setUserId(user.getUserId());
		coupon.setWorth(money);
		couponMapper.insertCoupon(coupon);
	}
	
	@Override
	public Json shareRedPacket(Integer userId, String token, Integer productOrderId) {
		Json json =new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(0);
			return json;
		}
		ProductOrder productOrder=productOrderMapper.selectById(productOrderId);
		RedPacket redPacket=new RedPacket();
		if(productOrder!=null&&productOrder.getState()>Constants.PRODUCTNEEDEDPAY_STATE)
		{
			redPacket.setMaxNum(Constants.getRedPacketNum);
			redPacket.setMoney(productOrder.getFinalPrice());
			redPacket.setProductOrderId(productOrderId);
			redPacket.setUserId(userId);
		}else
		{
			json.setCode(112);
			return json;
		}
		
		RedPacket redPacketC=redPacketMapper.selectByProductOrderId(productOrder.getProductOrderId());
		Integer redPacketId=0;
		if(redPacketC!=null)
		{
			redPacketId=redPacketC.getRedPacketId();
		}else
		{
			redPacketMapper.insertRedPacket(redPacket);
			redPacketId=redPacket.getRedPacketId();
		}
		json.setCode(100);
		json.setData("recommend/recommendRedPacket.action"+"?"+"redPacket="+redPacketId);
		return json;
	}

	@Override
	public Json shareRecommend(Integer userId) {
		Json json =new Json();
		
		json.setCode(100);
		json.setData("recommend/recommend.action"+"?"+"userId="+userId);
		return json;
	}

	@Override
	public Json recommendUser(Integer userId, String phone, String vcode, String password,String _vcode) {
		Json json=new Json();
		Json rejson=userService.regist(phone, vcode, password, _vcode);
		if(rejson.getCode()==100)
		{
			Integer reUserId=(Integer) json.getData();
			RecommendUserRelationship recommendUserRelationship=new RecommendUserRelationship();
			recommendUserRelationship.setUserId(reUserId);
			recommendUserRelationship.setRecommendUserId(reUserId);
			recommendUserRelationshipMapper.insertRecommendUserRelationship(recommendUserRelationship);
			json.setCode(100);
		}else
		{
			json.setCode(rejson.getCode());
		}
		return json;
	}

	@Override
	public Json getShareRecommendCode(Integer userId,HttpServletRequest request) {
		Json json=shareRecommend(userId);
		String saveName="";
		if(json.getCode()==100)
		{
			UserInfo userInfo=userInfoMapper.getUserInfo(userId);
			if(userInfo.getShareRecommendCode().equals(""))
			{
				String[] requestUrlArr=request.getRequestURL().toString().split("/");
				String requestUrl=requestUrlArr[0]+"//"+requestUrlArr[2]+"/"+requestUrlArr[3]+"/";
				String code=requestUrl+(String)json.getData();
				saveName=QrCodeUtils.uploadQrCodeImg(code);
				userInfo.setShareRecommendCode(saveName);
				userInfoMapper.updateUserInfo(userInfo);
			}else
			{
				saveName=userInfo.getShareRecommendCode();
			}
			json.setData(saveName);
			
		}
		return json;
	}

	@Override
	public Json getRecommendUserNum(Integer userId) {
		Json json =new Json();

		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		List<RecommendUserRelationshipVO> relationShipList=recommendUserRelationshipMapper.selectRecommendUserRelationship(map);
		RecommendRelationshipNumVO numVO=new RecommendRelationshipNumVO();
		int one=0;
		int two=0;
		int three=0;
		for(int i=0;i<relationShipList.size();i++)
		{
			if(relationShipList.get(i).getLevelOneUserId()!=null)
			{
				one++;
			}
			if(relationShipList.get(i).getLevelTwoUserId()!=null)
			{
				two++;
			}
			if(relationShipList.get(i).getLevelThreeUserId()!=null)
			{
				three++;
			}
		}
		numVO.setLevelOneNum(one);
		numVO.setLevelTwoNum(two);
		numVO.setLevelThreeNum(three);
		json.setCode(100);
		json.setData(numVO);
		return json;
	}
	@Override
	public Boolean moneyKickback(ProductOrder productOrder) {
		Boolean result=false;
		if(productOrder.getState().equals(Constants.PRODUCTNEEDEDPAY_STATE))
		{
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("userId", productOrder.getUserId());
			RecommendUserRelationshipVO relationShipVO=recommendUserRelationshipMapper.selectRecommendUserRelationshipUp(map);
			List<Integer> userList=new ArrayList<Integer>();
			if(relationShipVO.getLevelOneUserId()!=null)
			{
				userList.add(relationShipVO.getLevelOneUserId());
			}
			if(relationShipVO.getLevelTwoUserId()!=null)
			{
				userList.add(relationShipVO.getLevelTwoUserId());
			}
			if(relationShipVO.getLevelThreeUserId()!=null)
			{
				userList.add(relationShipVO.getLevelThreeUserId());
			}
			double moneyRate=Constants.MONEYRATEFORRECOMMEND;
			double thisMoney=productOrder.getFinalPrice()*moneyRate;
			for(int i=0;i<userList.size();i++)
			{
				UserWallet userWallet=userWalletMapper.getUserWalletNor(userList.get(i));
				userWallet.setWithDrawBalance(thisMoney);
				userWallet.setBalance(null);
				userWalletMapper.updateUserWalletMoney(userWallet);
				thisMoney=thisMoney*moneyRate;
			}
			
		}
		return result;
	}
	
}
