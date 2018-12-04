package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.ProductAttr;
import com.kangkai.pojo.ProductInfo;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.Province;
import com.kangkai.pojo.Shop;
import com.kangkai.pojo.Store;
import com.kangkai.pojo.StoreItem;
import com.kangkai.pojo.WasteNum;
import com.kangkai.vo.AttrVO;
import com.kangkai.vo.UserProductDetailVO;
import com.kangkai.vo.UserProductOrderDetailVO;
import com.kangkai.vo.UserProductOrderProductVO;
import com.kangkai.vo.UserProductOrderVO;
import com.kangkai.vo.UserProductVO;

public interface StoreItemMapper  {
	/**
	 * 插入一个寄存物品
	 * @param store
	 */
	void insertStoreItem(StoreItem storeItem);
	
	
}
