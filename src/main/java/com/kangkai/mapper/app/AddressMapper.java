package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Address;
import com.kangkai.vo.AddressVO;

public interface AddressMapper {

	/**
	 * 根据addressId查找地址
	 * @param userId
	 * @return
	 */
	public Address selectById(Integer addressId);
	/**
	 * 查找出某个用户的地址列表
	 * @param map
	 * @return
	 */
	public List<Address> selectAddressListWithPageByUserId(Map<String, Object> map);
	/**
	 * 插入一条地址信息
	 * @param address
	 */
	public int insertAddress(Address address);
	/**
	 * 把所有个人地址的默认状态改为非默认
	 * @param userId
	 */
	public void initDefaultAddress(Integer userId);
	/**
	 * 设置为默认地址
	 * @param addressId
	 */
	public void updateDefaultAddress(Integer addressId);
	/**
	 * 逻辑删除地址
	 * @param addressId
	 */
	public void deleteAddress(Integer addressId);
	/**
	 * 编辑地址
	 * @param address
	 */
	public void updateAddress(Address address);
	/**
	 * 获取用户默认地址
	 * @param userId
	 * @return
	 */
	public AddressVO selectUserDefaultAddress(Integer userId);
	/**
	 * 获取用户默认地址
	 * @param userId
	 * @return
	 */
	public Address selectUserDefaultAddress2(Integer userId);

}
