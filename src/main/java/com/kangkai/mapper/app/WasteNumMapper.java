package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.ProductAttr;
import com.kangkai.pojo.ProductInfo;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.Province;
import com.kangkai.pojo.WasteNum;
import com.kangkai.vo.AttrVO;
import com.kangkai.vo.UserProductDetailVO;
import com.kangkai.vo.UserProductOrderDetailVO;
import com.kangkai.vo.UserProductOrderProductVO;
import com.kangkai.vo.UserProductOrderVO;
import com.kangkai.vo.UserProductVO;

public interface WasteNumMapper  {
	/**
	 * 根据流水号查询流水号内容
	 * @param out_trade_no
	 * @return
	 */
	WasteNum selectByWasteNum(String out_trade_no);
	/**
	 * 插入一条流水单号
	 * @param wasteNum
	 */
	void insertWasteNum(WasteNum wasteNum);

	
}
