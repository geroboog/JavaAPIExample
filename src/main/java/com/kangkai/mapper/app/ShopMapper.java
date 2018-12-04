package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.ProductAttr;
import com.kangkai.pojo.ProductInfo;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.Province;
import com.kangkai.pojo.Shop;
import com.kangkai.pojo.WasteNum;
import com.kangkai.vo.AttrVO;
import com.kangkai.vo.UserProductDetailVO;
import com.kangkai.vo.UserProductOrderDetailVO;
import com.kangkai.vo.UserProductOrderProductVO;
import com.kangkai.vo.UserProductOrderVO;
import com.kangkai.vo.UserProductVO;

public interface ShopMapper  {
	/**
	 * 根据Id选取
	 * @param shopId
	 * @return
	 */
	public Shop selectById(Integer shopId);
	/**
	 * 获取门店列表
	 * @param map
	 * @return
	 */
	public List<Shop> selectByMap(Map<String, Object> map);
	
	
}
