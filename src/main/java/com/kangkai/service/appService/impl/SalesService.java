package com.kangkai.service.appService.impl;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.service.appService.IProductOrderService;
import com.kangkai.service.appService.ISalesService;
import com.kangkai.mapper.app.SalesMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.mapper.util.ExpressMapper;
import com.kangkai.pojo.Cartitem;
import com.kangkai.pojo.Category;
import com.kangkai.pojo.Coupon;
import com.kangkai.pojo.Product;
import com.kangkai.pojo.SalesProduct;
import com.kangkai.pojo.User;
import com.kangkai.pojo.Sales;
import com.kangkai.utils.ArrUtils;
import com.kangkai.utils.Constants;
import com.kangkai.utils.DateUtils;
import com.kangkai.utils.JPUSHUtils;
import com.kangkai.utils.Json;
import com.kangkai.utils.LogisticsUtils;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;
import com.kangkai.vo.LogisticsInfoVO;
import com.kangkai.vo.SalesVO;
import com.kangkai.vo.UserSimpleVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service(value="/salesService")
@Transactional
public class SalesService implements ISalesService{

	@Resource
	private SalesMapper salesMapper;
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public Json getSalesList(Integer current, Integer pageSize) {
		Json json=new Json();
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		List<Sales> salesList=salesMapper.selectSalesList(map);
		List<SalesVO> salesVOList=new ArrayList<SalesVO>();
		List<Product> productList=new ArrayList<Product>();
		if(salesList!=null)
		{
			for(int i=0;i<salesList.size();i++)
			{
				SalesVO salesVo=new SalesVO();
				salesVo.setSalesId(salesList.get(i).getSalesId());
				salesVo.setShowImg(salesList.get(i).getShowImg());
				salesVo.setSalesTitle(salesList.get(i).getSalesTitle());
				Map<String,Object> thisMap=new HashMap<String,Object>();
				thisMap.put("salesId", salesList.get(i).getSalesId());
				thisMap.put("begin", 0);
				thisMap.put("pageSize",5);
				List<Product> salesProductList=salesMapper.selectSalesProductList(thisMap);	
				if(salesProductList!=null)
				{
					salesVo.setProductList(salesProductList);
				}else
				{
					salesVo.setProductList(productList);
				}
				salesVOList.add(salesVo);
			}
		}
		if(salesVOList!=null&&salesVOList.size()>0)
		{
			json.setCode(100);
			json.setData(salesVOList);
		}else
		{
			json.setCode(112);
		}
 
		
		return json;
	}

	@Override
	public Json getSalesListBySaleId(Integer salesId, Integer current, Integer pageSize) {
		Json json=new Json();
		Sales sales=salesMapper.selectById(salesId);
		List<SalesVO> salesVOList=new ArrayList<SalesVO>();
		List<Product> productList=new ArrayList<Product>();
		if(sales!=null)
		{
			
				SalesVO salesVo=new SalesVO();
				salesVo.setSalesId(sales.getSalesId());
				salesVo.setShowImg(sales.getShowImg());
				salesVo.setSalesTitle(sales.getSalesTitle());
				Map<String,Object> thisMap=PageUtil.getMap(current, pageSize);
				thisMap.put("salesId", salesId);
				List<Product> salesProductList=salesMapper.selectSalesProductList(thisMap);	
				if(salesProductList!=null)
				{
					salesVo.setProductList(salesProductList);
				}else
				{
					salesVo.setProductList(productList);
				}
				salesVOList.add(salesVo);
		}
		if(salesVOList!=null&&salesVOList.size()>0)
		{
			json.setCode(100);
			json.setData(salesVOList.get(0));
		}else
		{
			json.setCode(112);
		}
 
		
		return json;
	}

	
	
	
	
}
