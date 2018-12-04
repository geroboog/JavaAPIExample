package com.kangkai.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;


import cn.jpush.api.JPushClient;  
import cn.jpush.api.common.resp.APIConnectionException;  
import cn.jpush.api.common.resp.APIRequestException;  
import cn.jpush.api.push.PushResult;  
import cn.jpush.api.push.model.Message;  
import cn.jpush.api.push.model.Options;  
import cn.jpush.api.push.model.Platform;  
import cn.jpush.api.push.model.PushPayload;  
import cn.jpush.api.push.model.audience.Audience;  
import cn.jpush.api.push.model.audience.AudienceTarget;  
import cn.jpush.api.push.model.notification.AndroidNotification;  
import cn.jpush.api.push.model.notification.IosNotification;  
import cn.jpush.api.push.model.notification.Notification;
import net.sf.json.JSONObject;  
  
public class JPUSHUtils {  
     protected static final Logger LOG = LoggerFactory.getLogger(JPUSHUtils.class);  
  
     // demo App defined in resources/jpush-api.conf   
    private static final String appKey =Constants.JPUSHAPPKEY;
    private static final String masterSecret= Constants.JPUSHMASTERSECRECT;
    
    
      
    public  static JPushClient jpushClient=null;  
      
    public static void sendPush(List<String> tags,String alert,String msg_content) {  
          
          
         jpushClient = new JPushClient(masterSecret, appKey, 3);  
          
        // HttpProxy proxy = new HttpProxy("localhost", 3128);  
        // Can use this https proxy: https://github.com/Exa-Networks/exaproxy  
         
          
        // For push, all you need do is to build PushPayload object.  
        //PushPayload payload = buildPushObject_all_all_alert();  
         //生成推送的内容，这里我们先测试全部推送  
         JSONObject jsonObject=JSONObject.fromObject(alert);
        PushPayload payload1=buildPushObject_ios(tags,jsonObject.getString("content"),msg_content);
        PushPayload payload2=buildPushObject_android(tags,jsonObject.getString("content"),msg_content);
       // payload1.resetOptionsTimeToLive(timeToLive);
          
        try {  
            System.out.println(payload1.toString());  
            PushResult result = jpushClient.sendPush(payload1);
            System.out.println(result+"................................");  
              
            LOG.info("Got result - " + result);  
              
        } catch (APIConnectionException e) {  
            LOG.error("Connection error. Should retry later. ", e);  
              
        } catch (APIRequestException e) {  
            LOG.error("Error response from JPush server. Should review and fix it. ", e);  
            LOG.info("HTTP Status: " + e.getStatus());  
            LOG.info("Error Code: " + e.getErrorCode());  
            LOG.info("Error Message: " + e.getErrorMessage());  
            LOG.info("Msg ID: " + e.getMsgId());  
        }  
        
        try {  
            System.out.println(payload2.toString());  
            PushResult result = jpushClient.sendPush(payload2);
            System.out.println(result+"................................");  
              
            LOG.info("Got result - " + result);  
              
        } catch (APIConnectionException e) {  
            LOG.error("Connection error. Should retry later. ", e);  
              
        } catch (APIRequestException e) {  
            LOG.error("Error response from JPush server. Should review and fix it. ", e);  
            LOG.info("HTTP Status: " + e.getStatus());  
            LOG.info("Error Code: " + e.getErrorCode());  
            LOG.info("Error Message: " + e.getErrorMessage());  
            LOG.info("Msg ID: " + e.getMsgId());  
        }  
    }  
      
    public static PushPayload buildPushObject_all_all_alert(String alert) {  
        return PushPayload.alertAll(alert);  
    }  
      
    public static PushPayload buildPushObject_all_alias_alert(String alert) {  
        return PushPayload.newBuilder()  
                .setPlatform(Platform.all())//设置接受的平台  
                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到  
                .setNotification(Notification.alert(alert))  
                .build();  
    }  
      
    public static PushPayload buildPushObject_android_tag_alertWithTitle(String alert,String title) {  
        return PushPayload.newBuilder()  
                .setPlatform(Platform.android())  
                .setAudience(Audience.all())  
                .setNotification(Notification.android(alert, title, null))  
                .build();  
    }  
      
    public static PushPayload buildPushObject_android(List<String> tags,String alert,String msg_content) {  
    	Map<String,String> resultMap=new HashMap<String,String>();
    	resultMap.put("msg_content", msg_content);
    	
    	return PushPayload.newBuilder()  
        .setPlatform(Platform.android())  
        .setAudience(Audience.tag(tags))  
        .setMessage(Message.content(msg_content))  
        .setNotification(Notification.alert(alert).android(alert, alert, resultMap))  
        .build();  
    }  
    public static PushPayload buildPushObject_ios(List<String> tags,String alert,String msg_content) {  
    	Map<String,String> resultMap=new HashMap<String,String>();
    	resultMap.put("msg_content", msg_content);
    	
        return PushPayload.newBuilder()  
                .setPlatform(Platform.ios())  
                .setAudience(Audience.tag(tags))  
                .setMessage(Message.content(msg_content))  
                .setNotification(Notification.newBuilder()
                		.addPlatformNotification(IosNotification.newBuilder()  
                        .setAlert(alert)  
                        .setBadge(1)  
                        .setSound("default") 
                        .addExtras(resultMap)
                        .build())
                		.build()) 
                .build();  
    }  
      
    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(String alert,String msg_content,List<String> tags) {  
    	return PushPayload.newBuilder()  
                .setPlatform(Platform.ios())  
                .setAudience(Audience.tag(tags))  
                .setNotification(Notification.newBuilder()  
                        .addPlatformNotification(IosNotification.newBuilder()  
                                .setAlert(alert)  
                                .setBadge(5)  
                                .setSound("happy") 
                                .addExtra("from", "JPush")  
                                .build())  
                        .build())  
                 .setMessage(Message.content(msg_content))  
                 .setOptions(Options.newBuilder()  
                         .setApnsProduction(true)  
                         .build())  
                 .build();  
    }  
      
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String msg_content) {  
        return PushPayload.newBuilder()  
                .setPlatform(Platform.android_ios())  
                .setAudience(Audience.newBuilder()  
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))  
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))  
                        .build())  
                .setMessage(Message.newBuilder()  
                        .setMsgContent(msg_content)  
                        .addExtra("from", "JPush")  
                        .build())  
                .build();  
    }
    
    public static void sendPushAlias(String alias,String alert,String msg_content) {  
        
        
        jpushClient = new JPushClient(masterSecret, appKey, 3);  
         
       // HttpProxy proxy = new HttpProxy("localhost", 3128);  
       // Can use this https proxy: https://github.com/Exa-Networks/exaproxy  
        
         
       // For push, all you need do is to build PushPayload object.  
       //PushPayload payload = buildPushObject_all_all_alert();  
        //生成推送的内容，这里我们先测试全部推送  
        JSONObject jsonObject=JSONObject.fromObject(alert);
       PushPayload payload1=buildPushObject_android(alias,jsonObject.getString("content"),msg_content);
       PushPayload payload2=buildPushObject__ios(alias,jsonObject.getString("content"),msg_content);
        
         
       try {  
           System.out.println(payload1.toString());  
           PushResult result = jpushClient.sendPush(payload1);  
           System.out.println(result+"................................");  
             
           LOG.info("Got result - " + result);  
             
       } catch (APIConnectionException e) {  
           LOG.error("Connection error. Should retry later. ", e);  
             
       } catch (APIRequestException e) {  
           LOG.error("Error response from JPush server. Should review and fix it. ", e);  
           LOG.info("HTTP Status: " + e.getStatus());  
           LOG.info("Error Code: " + e.getErrorCode());  
           LOG.info("Error Message: " + e.getErrorMessage());  
           LOG.info("Msg ID: " + e.getMsgId());  
       }  
       
       try {  
           System.out.println(payload2.toString());  
           PushResult result = jpushClient.sendPush(payload2);  
           System.out.println(result+"................................");  
             
           LOG.info("Got result - " + result);  
             
       } catch (APIConnectionException e) {  
           LOG.error("Connection error. Should retry later. ", e);  
             
       } catch (APIRequestException e) {  
           LOG.error("Error response from JPush server. Should review and fix it. ", e);  
           LOG.info("HTTP Status: " + e.getStatus());  
           LOG.info("Error Code: " + e.getErrorCode());  
           LOG.info("Error Message: " + e.getErrorMessage());  
           LOG.info("Msg ID: " + e.getMsgId());  
       }  
   }

	private static PushPayload buildPushObject__ios(String alias, String alert, String msg_content) {
		Map<String,String> resultMap=new HashMap<String,String>();
    	resultMap.put("msg_content", msg_content);
		
	
		 return  PushPayload.newBuilder()  
	                .setPlatform(Platform.ios())  
	                .setAudience(Audience.alias(alias))  
	                .setMessage(Message.content(msg_content))  
	                .setNotification(Notification.newBuilder()
	                		.addPlatformNotification(IosNotification.newBuilder()  
	                        .setAlert(alert)  
	                        .setBadge(1)  
	                        .setSound("default") 
	                        .addExtras(resultMap)
	                        .build())
	                		.build())   
	                .build(); 
	}  
	private static PushPayload buildPushObject_android(String alias, String alert, String msg_content) {
		Map<String,String> resultMap=new HashMap<String,String>();
    	resultMap.put("msg_content", msg_content);
		
		 return PushPayload.newBuilder()  
	                .setPlatform(Platform.android())  
	                .setAudience(Audience.alias(alias))  
	                .setMessage(Message.content(msg_content))  
	                .setNotification(Notification.alert(alert).android(alert, alert, resultMap))  
	                .build();  
		 
	}  

}  
