package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Coupon;
import com.kangkai.pojo.User;
import com.kangkai.pojo.UserInfo;
import com.kangkai.vo.UserSimpleVO;

public interface CouponMapper {
	/**
	 * 获取一张优惠券
	 * @param couponId
	 * @return
	 */
	Coupon selectById(Integer couponId);
	/**
	 * 根据限制金额和用户Id查询优惠券
	 * @param map
	 * @return
	 */
	List<Coupon> selectCouponByLimitMoneyAndUserId(Map<String, Object> map);
	/**
	 * 更新优惠券状态
	 * @param map
	 * @return
	 */
	public Integer updateIsUseById(Map<String, Object> map);
	/**
	 * 插入一张优惠券
	 * @param coupon
	 */
	void insertCoupon(Coupon coupon);
	/**
	 * 计算用户可用优惠券数
	 * @param userId
	 * @return
	 */
	Integer countUserUsefulCoupon(Integer userId);

	
}
