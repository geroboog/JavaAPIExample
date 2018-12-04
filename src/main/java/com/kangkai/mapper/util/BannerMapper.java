package com.kangkai.mapper.util;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Banner;
import com.kangkai.pojo.Category;

public interface BannerMapper {
	/**
	 * 获取广告列表
	 * @param map
	 * @return
	 */
	List<Banner> selectBannerList(Map map);


}
