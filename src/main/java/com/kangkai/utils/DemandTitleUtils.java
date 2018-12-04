package com.kangkai.utils;

import java.util.HashMap;
import java.util.Map;

public class DemandTitleUtils {
	private static Map MsgTitle= new HashMap();
	private  DemandTitleUtils() 
	{
		MsgTitle.put(0,"待匹配");
		MsgTitle.put(1, "待付款");
		MsgTitle.put(2,"需求方已支付，待派车接货");
		MsgTitle.put(2.5,"已派车接货");
		MsgTitle.put(3,"已接货回仓,等待配载");
		MsgTitle.put(4,"已发货");
		MsgTitle.put(5,"待评价");
		MsgTitle.put(6,"售后");
		MsgTitle.put(99,"已完成");
		MsgTitle.put(100,"已取消");
		
	}  
    private static final DemandTitleUtils result=new DemandTitleUtils();
     
    //静态工厂方法   
    public static DemandTitleUtils getInstance() {  
        return result;  
    }
    public String getBykey(Object key)
    {
    	return ""+MsgTitle.get(key);
    }
}
