package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Category;
import com.kangkai.pojo.Product;
import com.kangkai.pojo.Sales;
import com.kangkai.pojo.SalesProduct;
import com.kangkai.pojo.User;
import com.kangkai.pojo.UserInfo;
import com.kangkai.vo.UserSimpleVO;

public interface SalesMapper {

	/**
	 * 获取促销列表
	 * @param map
	 * @return
	 */
	List<Product> selectSalesProductList(Map map);

	List<Sales> selectSalesList(Map map);
	/**
	 * 根据Id查询促销列表
	 * @param salesId
	 * @return
	 */
	Sales selectById(Integer salesId);
}
