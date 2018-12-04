package com.kangkai.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;

public class BankUtils {
	
	private static String APP_ID="24101";
	private static String SECRECT_KEY="a80e3ef9203b48998514061dc2781fcc";
	public static String getBankName(String cardNum)
	{
		String bankName="";
		String result="";
		 URL u;
		try {
			u = new URL("http://route.showapi.com/30-7?showapi_appid="+APP_ID+"&cardNum="+cardNum+"&showapi_sign="+SECRECT_KEY+" ");
	        InputStream in=u.openStream();
	        ByteArrayOutputStream out=new ByteArrayOutputStream();
	        try {
	            byte buf[]=new byte[1024];
	            int read = 0;
	            while ((read = in.read(buf)) > 0) {
	                out.write(buf, 0, read);
	            }
	        }  finally {
	            if (in != null) {
	                in.close();
	            }
	        }
	        byte b[]=out.toByteArray( );
	        result=new String(b,"utf-8");
	        JSONObject resultObject=JSONObject.fromObject(result);
	        bankName=resultObject.getJSONObject("showapi_res_body").getString("bankName");
		} catch (Exception e) {
			System.out.print(e.getMessage());
			System.out.print(result);
		}
		return bankName;
	}
	
	
}
