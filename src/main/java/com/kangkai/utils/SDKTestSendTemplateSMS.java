package com.kangkai.utils;

import java.util.HashMap;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.cloopen.rest.sdk.CCPRestSDK.BodyType;

public class SDKTestSendTemplateSMS {

	/**
	 * @param args
	 * @return 
	 */
	public static String getVcode(String phone) {
		return getVcodePri(phone,"120866");
	}
	public static String getFindPasswordVcode(String phone) {
		return getVcodePri(phone,"120867");
	}
	private static String getVcodePri(String phone,String sms)
	{
		HashMap<String, Object> result = null;
		String vcode = createRandomVcode();
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(Constants.SMSSID, Constants.SMSTOKEN);// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(Constants.SMSAPPID);// 初始化应用ID
		result = restAPI.sendTemplateSMS(phone,sms ,new String[]{vcode});

		System.out.println("SDKTestSendTemplateSMS result=" + result);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
			return vcode;
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
			return "";
		}
	}
	
	// 生成随机位验证码
	private static String createRandomVcode() {
		// 验证码
		String vcode = "";
		for (int i = 0; i < 6; i++) {
			vcode = vcode + (int) (Math.random() * 9);
		}
		return vcode;
	}
}
