package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Category;
import com.kangkai.pojo.Product;
import com.kangkai.pojo.RedPacket;
import com.kangkai.pojo.Sales;
import com.kangkai.pojo.SalesProduct;
import com.kangkai.pojo.User;
import com.kangkai.pojo.UserInfo;
import com.kangkai.vo.UserSimpleVO;

public interface RedPacketMapper {
	/**
	 * 根据商品订单Id查找红包
	 * @param productOrderId
	 * @return 
	 */
	RedPacket selectByProductOrderId(Integer productOrderId);
	/**
	 * 插入一条红包信息
	 * @param redPacket
	 */
	void insertRedPacket(RedPacket redPacket);
	/**
	 * 查询一个红包记录
	 * @param redPacketId
	 * @return
	 */
	RedPacket selectById(Integer redPacketId);

	
}
