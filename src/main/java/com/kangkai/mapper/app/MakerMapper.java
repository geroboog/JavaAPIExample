package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Coupon;
import com.kangkai.pojo.Maker;
import com.kangkai.pojo.User;
import com.kangkai.pojo.UserInfo;
import com.kangkai.vo.UserSimpleVO;

public interface MakerMapper {
	/**
	 * 根据map查询
	 * @param map
	 * @return
	 */
	List<Maker> selectByMap(Map<String, Object> map);
	/**
	 * 根据Id查询制作商
	 * @param maker
	 * @return
	 */
	Maker selectById(Integer maker);
	
	
}
