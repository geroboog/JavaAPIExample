package com.kangkai.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;

public class GetuiUtil {
	
	private static String appId="pHBv12E52M8POK8xSnSs56";
	private static String appKey="QS2VD5OfoVADVYYxGZDQxA";
	private static String masterSecret="EUXchN5Heo9rUPGECfNR2A";
	private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
	private static enum type{
		productOrderMessage(0);
		private int _value;
		 private type(int value)
		   {
		         _value = value;
		   }
		  public int value()
		   {
		      return _value;
		   }	
	};
	public static void pushProductOrderMessageToSomeBody(String messageTitle,String messageContent,String user,Integer productOrderId)
	{
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        String tranContent="{\"productOrderId\":"+productOrderId+",\"type\":"+type.productOrderMessage.value()+"}";
        System.out.println(tranContent);
        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        NotificationTemplate template=new NotificationTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle(messageTitle);
        template.setText(messageContent);
        template.setTransmissionContent(tranContent);

        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);

        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        SingleMessage message = new SingleMessage();
        message.setData(template);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);
        Target target=new Target();
        target.setAppId(appId);
        target.setAlias(user);
        IPushResult ret = push.pushMessageToSingle(message, target);
        System.out.println(ret.getResponse().toString());
	}
	
	public static void main(String[] args) throws IOException {

	        IGtPush push = new IGtPush(url, appKey, masterSecret);

	        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
	        NotificationTemplate template=new NotificationTemplate();
	        template.setAppId(appId);
	        template.setAppkey(appKey);
	        template.setTransmissionContent("{\"1\":\"2\"}");
	        template.setTitle("欢迎使用个推!");
	        template.setText("这是一条推送消息~");

	        List<String> appIds = new ArrayList<String>();
	        appIds.add(appId);

	        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
	        AppMessage message = new AppMessage();
	        message.setData(template);
	        message.setAppIdList(appIds);
	        message.setOffline(true);
	        message.setOfflineExpireTime(1000 * 600);

	        IPushResult ret = push.pushMessageToApp(message);
	        System.out.println(ret.getResponse().toString());
	    }
}
