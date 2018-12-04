package com.kangkai.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangkai.pojo.User;
import com.kangkai.service.appService.ICategoryService;
import com.kangkai.service.appService.IProductService;
import com.kangkai.service.appService.ISalesService;
import com.kangkai.utils.Json;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="product")
public class ProductController {
	
	@Resource
	private ICategoryService categoryService;
	@Resource
	private ISalesService salesService;
	@Resource
	private IProductService productService;
	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	
	//获取商品目录列表
	
		@RequestMapping(value="/getCategoryList",method = RequestMethod.POST)
		@ResponseBody
		public Json getCategoryList(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行getCategoryList方法,请求的数据:" + data);
			Integer categoryId;
			Integer current;
			Integer pageSize;
			String title;
			try {
				current = data.getInt("current");
				pageSize = data.getInt("pageSize");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
			
			
			json = categoryService.getCategoryList(current,pageSize);
			return json;
		}
		//获取促销列表
		@RequestMapping(value="/getSalesList",method = RequestMethod.POST)
		@ResponseBody
		public Json getSalesList(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行getSalesList方法,请求的数据:" + data);
			Integer categoryId;
			Integer current;
			Integer pageSize;
			String title;
			try {
				current = data.getInt("current");
				pageSize = data.getInt("pageSize");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
			
			json = salesService.getSalesList(current,pageSize);
			return json;
		}
		
		//获取促销详情列表
		@RequestMapping(value="/getSalesListBySalesId",method = RequestMethod.POST)
				@ResponseBody
				public Json getSalesListBySalesId(@RequestBody JSONObject data){
					Json json = new Json();
					log.info("请求执行getSalesListBySalesId方法,请求的数据:" + data);
					Integer salesId;
					Integer current;
					Integer pageSize;
					String title;
					try {
						current = data.getInt("current");
						pageSize = data.getInt("pageSize");
						salesId=data.getInt("salesId");
					} catch (Exception e) {
						json.setCode(0);
						json.setMsg("请求的参数不合法");
						json.setData(null);
						log.error("请求的参数不合法，错误信息:" + e.getMessage());
						return json;
					}
					
					json = salesService.getSalesListBySaleId(salesId, current, pageSize);
					return json;
				}
		
		//获取促销列表
				@RequestMapping(value="/getProductListBySalesId",method = RequestMethod.POST)
				@ResponseBody
				public Json getProductListBySalesId(@RequestBody JSONObject data){
					Json json = new Json();
					log.info("请求执行getProductListBySalesId方法,请求的数据:" + data);
					Integer salesId;
					Integer current;
					Integer pageSize;
					String title;
					try {
						salesId=data.getInt("salesId");
						current = data.getInt("current");
						pageSize = data.getInt("pageSize");
					} catch (Exception e) {
						json.setCode(0);
						json.setMsg("请求的参数不合法");
						json.setData(null);
						log.error("请求的参数不合法，错误信息:" + e.getMessage());
						return json;
					}
					
					json = salesService.getSalesListBySaleId(salesId,current,pageSize);
					return json;
				}
		
		
		
		//根据名称和类别搜索商品
		@RequestMapping(value="/searchProductByTitleAndCategoryId",method = RequestMethod.POST)
		@ResponseBody
		public Json searchProductByTitleAndCategoryId(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行searchProductByTitleAndCategoryId方法,请求的数据:" + data);
			Integer userId;
			Integer type;
			Integer current;
			Integer pageSize;
			Integer categoryId=null;
			String token;
			String title=null;
			try {
				current=data.getInt("current");
				pageSize=data.getInt("pageSize");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
			try
			{
				categoryId = data.getInt("categoryId");
			}catch(Exception e)
			{
				
			}
			try
			{
				title = data.getString("title");
			}catch(Exception e)
			{
				
			}
			
			json = productService.searchProductByTitleAndCategoryId(current,categoryId,pageSize,title);
			return json;
		}
		//获取商品详情
		
		@RequestMapping(value="/getProductDetail",method = RequestMethod.POST)
		@ResponseBody
		public Json getProductDetail(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行getProductDetail方法,请求的数据:" + data);
			Integer userId=null;
			Integer productId;
			try {
				productId = data.getInt("productId");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
			try {
				userId = data.getInt("userId");
			} catch (Exception e) {
				
			}
			json = productService.getProductDetail(productId,userId);
			return json;
		}
		//购买商品页面
		@RequestMapping(value="/getBuyProductDetail",method = RequestMethod.POST)
		@ResponseBody
		public Json getBuyProductDetail(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行getBuyProductDetail方法,请求的数据:" + data);
			Integer userId;
			Integer count;
			String cartitemIds;
			String attrs;
			String token;
			try {
				userId = data.getInt("userId");
				cartitemIds = data.getString("cartitemIds");
				token=data.getString("token");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
			
			json = productService.getBuyProductDetail(userId,cartitemIds,token);
			return json;
		}
		//购买商品页面
		@RequestMapping(value="/getBuyProductDetailForOrder",method = RequestMethod.POST)
		@ResponseBody
		public Json getBuyProductDetailForOrder(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行getBuyProductDetailForOrder方法,请求的数据:" + data);
			Integer userId;
			Integer productOrderId;
			String token;
			try {
				userId = data.getInt("userId");
				productOrderId = data.getInt("productOrderId");
				token=data.getString("token");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
					
			json = productService.getBuyProductDetailForOrder(userId,productOrderId,token);
			return json;
		}
		/**
		 * 购买商品
		 * @param data
		 * @return
		 */
		@RequestMapping(value="/buyProduct",method = RequestMethod.POST)
		@ResponseBody
		public Json buyProduct(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行buyProduct方法,请求的数据:" + data);
			Integer userId;
			Integer couponId=null;
			String token;
			JSONArray productList;
			Integer  addressId=null;
			Integer shopId=null;
			Integer makerId=null;
			String cartitemIds;
			String invitedCode=null;
			String remark=null;
			try {
				userId = data.getInt("userId");
				cartitemIds=data.getString("cartitemIds");
				token=data.getString("token");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
			try
			{
				couponId=data.getInt("couponId");
			}catch(Exception e)
			{
				
			}
			try {
				addressId=data.getInt("addressId");
			} catch (Exception e) {
			}
			try {
				shopId=data.getInt("shopId");
			} catch (Exception e) {
			}
			try {
				makerId=data.getInt("makerId");
			} catch (Exception e) {
			}
			
			try {
				invitedCode=data.getString("invitedCode");
			} catch (Exception e) {
			}
			
			try {
				remark=data.getString("remark");
			} catch (Exception e) {
			}
			
			json = productService.buyProduct(userId, cartitemIds, addressId,shopId, couponId,makerId,invitedCode,remark, token);
			return json;
		}
		//付款订单
		@RequestMapping(value="/buyProductForOrder",method = RequestMethod.POST)
		@ResponseBody
		public Json buyProductForOrder(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行buyProductForOrder方法,请求的数据:" + data);
			Integer userId;
			Integer productOrderId;
			String token;
			try {
				userId = data.getInt("userId");
				productOrderId=data.getInt("productOrderId");
				token=data.getString("token");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
			
			json = productService.buyProductForOrder(userId, productOrderId, token);
			return json;
		}
		//购买商品优惠券列表
		@RequestMapping(value="/getCouponList",method = RequestMethod.POST)
		@ResponseBody
		public Json getCouponList(@RequestBody JSONObject data){
			Json json = new Json();
			log.info("请求执行getCouponList方法,请求的数据:" + data);
			Integer userId;
			Double money;
			String token;
			try {
				userId = data.getInt("userId");
				money = data.getDouble("money");
				token=data.getString("token");
			} catch (Exception e) {
				json.setCode(0);
				json.setMsg("请求的参数不合法");
				json.setData(null);
				log.error("请求的参数不合法，错误信息:" + e.getMessage());
				return json;
			}
			
			json = productService.getCouponList(userId,money,token);
			return json;
		}
		//制作商列表
		@RequestMapping(value="/getMakerList",method = RequestMethod.POST)
		@ResponseBody
		public Json getMakerList(@RequestBody JSONObject data){
				Json json = new Json();
				log.info("请求执行getMakerList方法,请求的数据:" + data);
				Integer userId;
				Double money;
				String token;
				Integer current;
				Integer pageSize;
				try {
					userId = data.getInt("userId");
					current=data.getInt("current");
					pageSize=data.getInt("pageSize");
					token=data.getString("token");
				} catch (Exception e) {
					json.setCode(0);
					json.setMsg("请求的参数不合法");
					json.setData(null);
					log.error("请求的参数不合法，错误信息:" + e.getMessage());
					return json;
				}
					
				json = productService.getMakerList(userId,token,current,pageSize);
				return json;
			}
		
		//获取门店列表
				@RequestMapping(value="/getShopList",method = RequestMethod.POST)
				@ResponseBody
				public Json getShopList(@RequestBody JSONObject data){
						Json json = new Json();
						log.info("请求执行getShopList方法,请求的数据:" + data);
						Integer userId;
						Double money;
						String token;
						Integer current;
						Integer pageSize;
						try {
							userId = data.getInt("userId");
							current=data.getInt("current");
							pageSize=data.getInt("pageSize");
							token=data.getString("token");
						} catch (Exception e) {
							json.setCode(0);
							json.setMsg("请求的参数不合法");
							json.setData(null);
							log.error("请求的参数不合法，错误信息:" + e.getMessage());
							return json;
						}
							
						json = productService.getShopList(userId,token,current,pageSize);
						return json;
					}
		
		
		
		

}
