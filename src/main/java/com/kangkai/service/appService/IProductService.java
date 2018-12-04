package com.kangkai.service.appService;

import java.util.Map;

import com.kangkai.utils.Json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IProductService {
	/**
	 * 根据商品名称和类别查询商品列表
	 * @param current
	 * @param categoryId
	 * @param pageSize
	 * @param title
	 * @return
	 */
	Json searchProductByTitleAndCategoryId(Integer current, Integer categoryId, Integer pageSize, String title);
	/**
	 * 获取商品详情
	 * @param productId
	 * @param userId
	 * @return
	 */
	Json getProductDetail(Integer productId, Integer userId);
	/**
	 * 进入购买页面
	 * @param userId
	 * @param cartitemIds
	 * @param token
	 * @return
	 */
	Json getBuyProductDetail(Integer userId, String cartitemIds, String token);
	/**
	 * 获取优惠券列表
	 * @param userId
	 * @param money
	 * @param token
	 * @return
	 */
	Json getCouponList(Integer userId, Double money, String token);
	/**
	 * 获取制作商列表
	 * @param userId
	 * @param token
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getMakerList(Integer userId, String token, Integer current, Integer pageSize);
	/**
	 * 订单购买页面
	 * @param userId
	 * @param productOrderId
	 * @param token
	 * @return
	 */
	Json getBuyProductDetailForOrder(Integer userId, Integer productOrderId, String token);
	/**
	 * 收藏/取消收藏商品
	 * @param userId
	 * @param token
	 * @param productId
	 * @return
	 */
	Json collectProduct(Integer userId, String token, Integer productId);
	/**
	 * 购买商品/拍下商品
	 * @param userId
	 * @param cartitemIds
	 * @param addressId
	 * @param shopId
	 * @param couponId
	 * @param makerId
	 * @param invitedCode
	 * @param remark
	 * @param token
	 * @return
	 */
	Json buyProduct(Integer userId, String cartitemIds, Integer addressId,Integer shopId, Integer couponId,Integer makerId,String invitedCode,String remark, String token);
	/**
	 * 支付订单
	 * @param userId
	 * @param productOrderId
	 * @param token
	 * @return
	 */
	Json buyProductForOrder(Integer userId, Integer productOrderId, String token);
	/**
	 * 获取商品收藏列表
	 * @param userId
	 * @param token
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getProductCollectList(Integer userId, String token, Integer current, Integer pageSize);
	/**
	 * 获取门店列表
	 * @param userId
	 * @param token
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getShopList(Integer userId, String token, Integer current, Integer pageSize);
	
}
