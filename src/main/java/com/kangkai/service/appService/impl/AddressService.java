package com.kangkai.service.appService.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.mapper.app.AddressMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.mapper.util.CityMapper;
import com.kangkai.mapper.util.DistrictMapper;
import com.kangkai.mapper.util.ProvinceMapper;
import com.kangkai.pojo.Address;
import com.kangkai.pojo.City;
import com.kangkai.pojo.District;
import com.kangkai.pojo.Province;
import com.kangkai.pojo.User;
import com.kangkai.service.appService.IAddressService;
import com.kangkai.utils.Json;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;
import com.kangkai.vo.AddressVO;


@Service(value="/addressService")
@Transactional
public class AddressService implements IAddressService{

	
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());
	@Resource
	private AddressMapper addressMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private ProvinceMapper provinceMapper;
	@Resource
	private CityMapper cityMapper;
	@Resource
	private DistrictMapper districtMapper;
	
	
	@Override
	public Json getUserAddressList(Integer userId, Integer current, Integer pageSize, String token) {
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
		List<Address> addressList=addressMapper.selectAddressListWithPageByUserId(map);
		if(addressList!=null&&addressList.size()>0)
		{
			json.setCode(100);
			json.setData(addressList);
		}else
		{
			json.setCode(112);
			json.setData(new ArrayList());
		}
		return json;
	}
	@Override
	public Json addUserAddress(Integer userId, String token, String name, String phone, String province, String city,
			String district, String detailAddr) {
		Json json=new Json();
		
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		
		Address address =new Address();
		address.setCity(city);
		address.setPhone(phone);
		address.setProvince(province);
		address.setName(name);
		address.setUserId(userId);
		address.setDistrict(district);
		address.setDetailAddr(detailAddr);
		addressMapper.insertAddress(address);
		json.setCode(100);
		return json;
	}
	@Override
	public Json selectDefaultAddress(Integer userId, String token, Integer addressId) {
		Json json=new Json();
		addressMapper.initDefaultAddress(userId);
		addressMapper.updateDefaultAddress(addressId);
		json.setCode(100);
		return json;
	}
	@Override
	public Json deleteUserAddress(Integer userId, String token, Integer addressId) {
		Json json=new Json();
		addressMapper.deleteAddress(addressId);
		Address address=addressMapper.selectById(addressId);
		if(address.getIsDefault().equals(1))
		{
			Map<String,Object> map=PageUtil.getMap(1,1);
			map.put("userId", userId);
			List<Address> addressList=addressMapper.selectAddressListWithPageByUserId(map);
			if(addressList!=null&&addressList.size()>0)
			{
			addressMapper.updateDefaultAddress(addressList.get(0).getAddressId());	
			}
		}
		json.setCode(100);
		return json;
	}
	@Override
	public Json updateUserAddress(Integer userId, String token, Integer addressId, String name, String phone,
			String province, String city, String district, String detailAddr) {
		
		Json json=new Json();
		
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		
		Address address =addressMapper.selectById(addressId);
		address.setCity(city);
		address.setPhone(phone);
		address.setProvince(province);
		address.setName(name);
		address.setUserId(userId);
		address.setDistrict(district);
		address.setDetailAddr(detailAddr);
		addressMapper.updateAddress(address);
		json.setCode(100);
		return json;
	}
	@Override
	public Json getProvinceList(Integer current, Integer pageSize) {
		Json json=new Json();
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		List<Province> provinceList=provinceMapper.selectAll(map);
		if(provinceList!=null&&provinceList.size()>0)
		{
			json.setCode(100);
			json.setData(provinceList);
		}else
		{
			json.setCode(112);
		}
		return json;
		
	}
	@Override
	public Json getCityList(Integer current, Integer pageSize, Integer provinceId,Integer type) {
		Json json=new Json();
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		map.put("provinceId", provinceId);
		List<City> cityList=null;
		if(type.equals(0))
		{
			cityList=cityMapper.selectAll(map);
		}else
		{
			cityList=cityMapper.selectAll2(map);
		}
		if(cityList!=null&&cityList.size()>0)
		{
			json.setCode(100);
			json.setData(cityList);
		}else
		{
			json.setCode(112);
		}
		return json;
	}
	@Override
	public Json getDistrictList(Integer current, Integer pageSize, Integer cityId,Integer type) {
		Json json=new Json();
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		map.put("cityId", cityId);
		List<District> districtList=null;
		if(type.equals(0))
		{
		districtList=districtMapper.selectAll(map);
		}else
		{
			districtList=districtMapper.selectAll2(map);
		}
		if(districtList!=null&&districtList.size()>0)
		{
			json.setCode(100);
			json.setData(districtList);
		}else
		{
			json.setCode(112);
		}
		return json;
	}
	
	@Override
	public Json getUserDefaultAddress(Integer userId, String token) {
		Json json=new Json();
		
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		AddressVO address=addressMapper.selectUserDefaultAddress(userId);
		if(address!=null)
		{
			json.setCode(100);
			json.setData(address);
		}else
		{
			json.setCode(112);
		}
		return json;
	}
	
	
}
