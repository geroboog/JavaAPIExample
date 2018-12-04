package com.kangkai.service.appService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;

import com.kangkai.pojo.ProductOrder;
import com.kangkai.utils.Json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IRecommendService {
	/**
	 * 
	 * @param phone
	 * @param redPacketId
	 * @return
	 */
	Json recommendCoupon(String phone, Integer redPacketId);
	
	/**
	 * 分享红包接口
	 * @param userId
	 * @param token
	 * @param productOrderId
	 * @return
	 */
	Json shareRedPacket(Integer userId, String token, Integer productOrderId);
	/**
	 * 分享推荐（分销）
	 * @param userId
	 * @return
	 */
	Json shareRecommend(Integer userId);
	/**
	 * 推荐用户
	 * @param userId
	 * @param phone
	 * @param vcode
	 * @param password
	 * @param _vcode 
	 * @return
	 */
	Json recommendUser(Integer userId, String phone, String vcode, String password, String _vcode);
	/**
	 * 获取分享二维码
	 * @param userId
	 * @param request
	 * @return
	 */
	Json getShareRecommendCode(Integer userId, HttpServletRequest request);
	/**
	 * 获取上下线人数
	 * @param userId
	 * @return
	 */
	Json getRecommendUserNum(Integer userId);
	/**
	 * 购物回扣
	 * @param productOrder
	 * @return
	 */
	Boolean moneyKickback(ProductOrder productOrder);
	
	
}
