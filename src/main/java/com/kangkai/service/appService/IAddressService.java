package com.kangkai.service.appService;

import com.kangkai.utils.Json;

public interface IAddressService {


	/**
	 * 获取用户地址列表
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @param token
	 * @return
	 */
	Json getUserAddressList(Integer userId, Integer current, Integer pageSize, String token);
	/**
	 * 增加一个用户地址
	 * @param userId
	 * @param token
	 * @param name
	 * @param phone
	 * @param province
	 * @param city
	 * @param district
	 * @param detailArr
	 * @return
	 */
	Json addUserAddress(Integer userId, String token, String name, String phone, String province, String city,
			String district, String detailAddr);
	/**
	 * 选择一个用户地址为默认地址
	 * @param userId
	 * @param token
	 * @param addressId
	 * @return
	 */
	Json selectDefaultAddress(Integer userId, String token, Integer addressId);
	/**
	 * 删除用户地址
	 * @param userId
	 * @param token
	 * @param addressId
	 * @return
	 */
	Json deleteUserAddress(Integer userId, String token, Integer addressId);
	/**
	 * 
	 * @param userId
	 * @param token
	 * @param addressId
	 * @param name
	 * @param phone
	 * @param province
	 * @param city
	 * @param district
	 * @param detailAddr
	 * @return
	 */
	Json updateUserAddress(Integer userId, String token, Integer addressId, String name, String phone, String province,
			String city, String district, String detailAddr);
	/**
	 * 获取省份接口
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getProvinceList(Integer current, Integer pageSize);
	/**
	 * 获取城市接口
	 * @param current
	 * @param pageSize
	 * @param provinceId
	 * @return
	 */
	Json getCityList(Integer current, Integer pageSize, Integer provinceId, Integer type);
	/**
	 * 获取地区接口
	 * @param current
	 * @param pageSize
	 * @param cityId
	 * @return
	 */
	Json getDistrictList(Integer current, Integer pageSize, Integer cityId, Integer type);
	/**
	 * 获取用户默认地址
	 * @param userId
	 * @param token
	 * @return
	 */
	Json getUserDefaultAddress(Integer userId, String token);

	

}
