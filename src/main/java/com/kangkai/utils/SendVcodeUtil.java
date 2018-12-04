package com.kangkai.utils;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 发送短信验证码工具
 * 
 * @author 曾伟雄
 * 
 */
public class SendVcodeUtil {

	/**
	 * 发送注册短信
	 * 
	 * @param phone
	 * @return
	 * @throws ApiException
	 */
	@SuppressWarnings("unused")
	public static String sendRegistMsg(String phone) throws ApiException {
		String url = "http://gw.api.taobao.com/router/rest";
		String appkey = "23268844";
		String secret = "8da3c0e3197a685a462bf89022368eb3";

		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName("注册验证");
		String vcode = createRandomVcode();
		req.setSmsParam("{'code':'" + vcode + "','product':'有货'}");
		req.setRecNum(phone);
		req.setSmsTemplateCode("SMS_2240402");
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		return vcode;
	}

	/**
	 * 发送找回密码短信
	 * 
	 * @param phone
	 * @return
	 * @throws ApiException
	 */
	@SuppressWarnings("unused")
	public static String sendFindPasswordMsg(String phone) throws ApiException {
		String url = "http://gw.api.taobao.com/router/rest";
		String appkey = "23268844";
		String secret = "8da3c0e3197a685a462bf89022368eb3";

		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName("找回密码");
		String vcode = createRandomVcode();
		req.setSmsParam("{'code':'" + vcode + "','product':'有货'}");
		req.setRecNum(phone);
		req.setSmsTemplateCode("SMS_2815168");
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		return vcode;
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
