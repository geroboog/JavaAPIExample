package com.kangkai.service.utilService.impl;



import java.io.UnsupportedEncodingException;
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

import com.alipay.api.AlipayApiException;
import com.kangkai.mapper.app.CategoryMapper;
import com.kangkai.mapper.app.ProductOrderMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.mapper.app.WasteNumMapper;
import com.kangkai.mapper.util.BannerMapper;
import com.kangkai.mapper.util.ExpressMapper;
import com.kangkai.pojo.Banner;
import com.kangkai.pojo.Cartitem;
import com.kangkai.pojo.Category;
import com.kangkai.pojo.Coupon;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.User;
import com.kangkai.pojo.WasteNum;
import com.kangkai.service.appService.ICategoryService;
import com.kangkai.service.appService.IProductService;
import com.kangkai.service.utilService.IBannerService;
import com.kangkai.service.utilService.IPaymentService;
import com.kangkai.utils.AlipayUtil;
import com.kangkai.utils.ArrUtils;
import com.kangkai.utils.Constants;
import com.kangkai.utils.DateUtils;
import com.kangkai.utils.JPUSHUtils;
import com.kangkai.utils.Json;
import com.kangkai.utils.LogisticsUtils;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;
import com.kangkai.vo.LogisticsInfoVO;
import com.kangkai.vo.UserSimpleVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service(value="/paymentService")
@Transactional
public class PaymentService implements IPaymentService{

	@Resource
	UserMapper userMapper;
	@Resource
	ProductOrderMapper productOrderMapper;
	@Resource
	WasteNumMapper wasteNumMapper;
	@Resource
	SystemMessageService systemMessageService;
	@Resource
	WasteService wasteService;
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public Json getPayment(Integer userId, String token,String wasteNum,Integer type, String channel) {
		Json json=new Json();
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		
		if(channel.equals("alipay"))
		{
			AlipayUtil alipayUtil=new AlipayUtil();	
			try 
			{
				if(type.equals(new Integer(Constants.BUYTYPE.buyProduct.value())))
				{
				WasteNum wasteNumObject=wasteNumMapper.selectByWasteNum(wasteNum);
				ProductOrder productOrder=productOrderMapper.selectProductOrderDetailByProductOrderNum(wasteNumObject.getProductOrderNum());
				String subject=Constants.BUYSUBJECT[type];
				String result=alipayUtil.recharge(wasteNum, type,subject, productOrder.getFinalPrice()+productOrder.getFreight());
				json.setCode(100);
				json.setData(result);
				}
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				json.setCode(201);
				json.setMsg("编码出问题了");
				e.printStackTrace();
			} catch (AlipayApiException e) {
				json.setCode(202);
				json.setMsg("支付宝SDK调用出错");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return json;
		}else
		{
			return json;
		}
	}

	@Override
	public void webhooks(String out_trade_no,String type, String channel) {
		if(type.equals(Constants.BUYTYPE.buyProduct.value()))
		{
			WasteNum wasteNum=wasteNumMapper.selectByWasteNum(out_trade_no);
			ProductOrder productOrder=productOrderMapper.selectProductOrderDetailByProductOrderNum(wasteNum.getProductOrderNum());
			
			if(productOrder.getState().equals(Constants.PRODUCTNEEDEDPAY_STATE))
			{
			//处理订单状态
				
				productOrder.setState(Constants.PRODUCTNEEDEDSEND_STATE);
				productOrderMapper.updateProductOrderState(productOrder);
				
			//发送消息以及推送
				systemMessageService.sendProductSystemMessage(productOrder.getProductOrderId(),productOrder.getState());
			//处理商品购买流水
				wasteService.processProductWaste(channel, wasteNum.getWasteNum(), productOrder);
			}
			
		}
		
	}
	
	
	
	
}
