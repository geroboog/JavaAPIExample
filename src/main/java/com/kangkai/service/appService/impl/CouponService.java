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

import com.kangkai.mapper.app.AddressMapper;
import com.kangkai.mapper.app.CartitemMapper;
import com.kangkai.mapper.app.CouponMapper;
import com.kangkai.mapper.app.MakerMapper;
import com.kangkai.mapper.app.ProductAttrMapper;
import com.kangkai.mapper.app.ProductCollectMapper;
import com.kangkai.mapper.app.ProductInfoMapper;
import com.kangkai.mapper.app.ProductMapper;
import com.kangkai.mapper.app.ProductOrderMapper;
import com.kangkai.mapper.app.RecommendRedPacketMapper;
import com.kangkai.mapper.app.RedPacketMapper;
import com.kangkai.mapper.app.UserMapper;
import com.kangkai.mapper.util.ExpressMapper;
import com.kangkai.pojo.Cartitem;
import com.kangkai.pojo.Category;
import com.kangkai.pojo.Coupon;
import com.kangkai.pojo.Maker;
import com.kangkai.pojo.ProductAttr;
import com.kangkai.pojo.User;
import com.kangkai.service.appService.ICartitemService;
import com.kangkai.service.appService.ICouponService;
import com.kangkai.service.appService.IProductService;
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
import com.kangkai.vo.UserCartitemVO;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.ProductOrderItem;
import com.kangkai.pojo.RecommendRedPacket;
import com.kangkai.pojo.RedPacket;
import com.kangkai.vo.AddressVO;
import com.kangkai.vo.CouponVO;
import com.kangkai.pojo.Product;
import com.kangkai.vo.BuyProductDetailVO;
import com.kangkai.pojo.ProductCollect;
import com.kangkai.pojo.ProductComment;
import com.kangkai.pojo.ProductInfo;
import com.kangkai.vo.AttrVO;
import com.kangkai.vo.UserProductDetailVO;
import com.kangkai.vo.UserProductVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service(value="/couponService")
@Transactional
public class CouponService implements ICouponService{

	

	
}
