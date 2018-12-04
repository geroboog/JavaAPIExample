package com.kangkai.service.appService.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.service.appService.IProductCommentService;
import com.kangkai.mapper.app.ProductCommentMapper;
import com.kangkai.mapper.app.ProductInfoMapper;
import com.kangkai.mapper.app.ProductMapper;
import com.kangkai.mapper.app.ProductOrderItemMapper;
import com.kangkai.mapper.app.ProductOrderMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.pojo.Address;
import com.kangkai.pojo.ProductComment;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.ProductOrderItem;
import com.kangkai.pojo.User;
import com.kangkai.utils.Constants;
import com.kangkai.utils.Json;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;
import com.kangkai.vo.UserCommentVO;
import com.kangkai.vo.UserProductVO;


@Service(value="/commentService")
@Transactional
public class ProductCommentService implements IProductCommentService{

	@Resource
	private UserMapper userMapper;
	@Resource
	private ProductOrderMapper productOrderMapper;
	@Resource
	private ProductOrderItemMapper productOrderItemMapper;
	@Resource
	private ProductCommentMapper productCommentMapper;
	@Resource
	private ProductMapper productMapper;
	@Resource
	private ProductInfoMapper productInfoMapper;
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public Json commentProduct(Integer userId, String token, Integer productOrderId, String content, Double evaluation) {
		Json json=new Json();
		
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}

		
		try
		{
		ProductOrder order=productOrderMapper.selectById(productOrderId);
		List<ProductOrderItem> productList=null;
		if(order==null||order.getIsComment()>0||order.getState().intValue()!=Constants.PRODUCTNEEDEDCOMMENT_STATE)
		{
			json.setCode(118);
			return json;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("productOrderId", productOrderId);
		productList=productOrderItemMapper.selectProductOrderItemListNo(map);
		if(productList!=null&&productList.size()>0)
		{
			for(int i=0;i<productList.size();i++)
			{
				ProductComment commentBean=new ProductComment();
				commentBean.setContent(content);
				commentBean.setProductOrderId(productOrderId);
				commentBean.setProductId(productList.get(i).getProductId());
				commentBean.setEvaluation(evaluation);
				commentBean.setUserId(userId);
				commentBean.setProductId(productList.get(i).getProductId());
				productCommentMapper.insertComment(commentBean);
				productInfoMapper.updateEvaluation(productList.get(i).getProductId());
			}
			Map<String,Object> thisMap=new HashMap<String,Object>();
			thisMap.put("productOrderId",productOrderId);
			thisMap.put("isComment", 1);
			thisMap.put("state", Constants.PRODUCTCOMPLETE_STATE);
			productOrderMapper.updateProductOrderIsComment(thisMap);
			json.setCode(100);
		}else
		{
			json.setCode(112);
		}
		}
		catch(Exception ex)
		{
			json.setCode(112);
			System.out.println(ex.getMessage());
		}
		return json;
	}
	
	@Override
	public Json getProductCommentList(Integer userId, String token, Integer productId, Integer current, Integer pageSize) {
		Json json=new Json();
		
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}

		try
		{
		Map<String,Object> map=PageUtil.getMap(current, pageSize);
		map.put("productId", productId);
		List<UserCommentVO> commentList=productCommentMapper.selectProductCommentList(map);
		if(commentList!=null&&commentList.size()>0)
		{
			json.setCode(100);
			json.setData(commentList);
		}else
		{
			json.setCode(112);
		}
		}
		catch(Exception ex)
		{
			json.setCode(112);
			System.out.println(ex.getMessage());
		}
		return json;
	}
	
	

	
	
	
}
