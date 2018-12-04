package com.kangkai.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

public class AlipayUtil {
	private static String APP_ID="2016090900470944";//"2016122704658237";
	private static String method="alipay.trade.app.pay";
	private static String format="json";
	private static String charset ="UTF-8";
	private static String sign_type="RSA";
	private static String version="1.0";
	private static String notify_url=Constants.WEBIP+"charge/webhooks.do";
	private static String private_key="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJc1FgutAeENHT16BXRk06vFaZq8Y1LZHSoVGo/FmLIvrhVNTqoMS4C+LMou7v408LFbyJFLgpWrg1RqCZogTBWK8Dr1xrcfKCCdcUs++Lc8zscjPHzD7c8sgu6bTRrU6SA6NhiFvLMoGzEJUh2Xwp1lnpv1Da99xX7SROcdslSJAgMBAAECgYA5tTT4t8qjzZF/SPaT+Xcfc3Re"
								+"bc+qatpKmUtN26fvOWtDDgIbvB2EwQ/vCgKEWkgSKGlfqmXoIVE6Ak4v2GuwpuaM0742m/10RyShAZlhzKTM//GB0IL/2CMfqVWPxSYV5bHL2nXlGbPfGx7jUEU4fBAZucDnYF7/1itwzSyb0QJBAMhF10ipnYC350aq1CBBDmbhacVDgOwPTpzujyMimRtD9GDQDlI8u6PPKx9MGcmyThfg47h8kk/BIj62TMYJLfsCQQDBSCKjtQvsGgA4l8qyh1Crm/Ve+G783vLqhMbWzr+EcdeDrDIdibcUrwIy6vglQt0GqaP+Oo31FexD2oRP"
								+"atRLAkBvMB867zhLe06CtFr64UN+qM0RQM31wPXTOLrtNcstEQg/K6OX0rIWWvFE+fojOcQTi77B8R9F+iLqEEoFdJk7AkB6gD68JWZeCQi01bf5uPuvGJTG0Yx+psutSeRAhx8pUYH3iW7l+7/W0ykAnp4R97Pgoio3WmNYv8FULC4Jq7rLAkBSfqyeXpvrnUmq1LIiaMcFKei9XntggAcI7gRnIWErxqFO6IHTOp1SHfQ2YkJnNqIF88o5pLz2GauJ3NfzKcXv";
	private static String public_key="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";


	public static String recharge(String out_trade_no,Integer type,String subject, double money) throws UnsupportedEncodingException, AlipayApiException {

	Map<String, String> sParaTemp = new HashMap<String, String>();
	Date nowDate=new Date();
	String biz_content = "{\"subject\":\"" + subject+",\"out_trade_no\":\"" + out_trade_no + "\",\"total_amount\":\""
			+ String.valueOf(money) + "\",\"product_code\":\"" + "QUICK_MSECURITY_PAY" + "\",\"timeout_express\":\"30m\"}";

	sParaTemp.put("app_id", APP_ID);
	sParaTemp.put("method", method);
	sParaTemp.put("format", format);
	sParaTemp.put("charset", charset);
	sParaTemp.put("sign_type", sign_type);
	sParaTemp.put("version", version);
	sParaTemp.put("notify_url", notify_url);
	sParaTemp.put("body",""+ type);
	sParaTemp.put("timestamp", DateUtils.formatDatetime(nowDate));
	sParaTemp.put("biz_content", biz_content);
	String sign = AlipaySignature.rsaSign(sParaTemp, private_key, charset);
	sParaTemp.put("sign", sign);
	// Map<String, String> alipayparas =
	// AlipaySubmit.buildRequestPara(sParaTemp);
	// StringBuffer alipay_url = new
	// StringBuffer("https://mapi.alipay.com/gateway.do?");
	StringBuffer paraString = new StringBuffer("");
	for (String key : sParaTemp.keySet()) {
		String value = sParaTemp.get(key);
		System.out.println(key + ":" + value);
		String encode = URLEncoder.encode(value, "utf-8");
		paraString.append(key + "=" + encode + "&");
	}
	return paraString.toString().substring(0, paraString.toString().length() - 1);
	
	}

	public static boolean varidate(Map<String, String> params) throws AlipayApiException {
		return AlipaySignature.rsaCheckV1(params, public_key, charset);
	}
}
