package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.ProductAttr;
import com.kangkai.pojo.ProductInfo;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.ProductOrderMessage;
import com.kangkai.pojo.Province;
import com.kangkai.vo.AttrVO;
import com.kangkai.vo.UserProductDetailVO;
import com.kangkai.vo.UserProductOrderDetailVO;
import com.kangkai.vo.UserProductOrderMessageVO;
import com.kangkai.vo.UserProductOrderProductVO;
import com.kangkai.vo.UserProductOrderVO;
import com.kangkai.vo.UserProductVO;

public interface ProductOrderMessageMapper  {
	/**
	 * 获取订单小红点消息
	 * @param map
	 * @return
	 */
	List<UserProductOrderMessageVO> selectProductOrderMessage(Map<String, Object> map);
	/**
	 * 插入订单数量消息
	 * @param map
	 */
	void insertProductOrderMessage(Map<String, Object> map);
	/**
	 * 更新小红点信息
	 * @param map
	 * @return
	 */
	Integer updateProductOrderMessage(Map<String, Object> map);
	/**
	 * 获取某特定状态小红点数
	 * @param map
	 * @return
	 */
	ProductOrderMessage selectProductOrderMessageOne(Map<String, Object> map);
	
}
