package com.kangkai.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TemperatureUtil {
	private String method="truck.temperature.getHistory";
	private String app_key="zhanhao_api";
	private String app_secrect="4015235c7db8b34ff558f9b96e6021ab";
	private String gpsno2="71003011";
	private String url="http://g7s.api.huoyunren.com/interface/index.php";
	public String getGPSNOTemperature(String gpsno)
	{
		String temperature="0";
		try
		{
		Date nowDate=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = calendar.getTime();
		
		String sign="";
		
		String timestamp=DateUtils.formatDatetime(nowDate);
		String lastTimestamp=DateUtils.formatDatetime(lastDate);
		Map<String,Object> dataMap=new HashMap<String,Object>();
		dataMap.put("pageNo", 1);
		dataMap.put("pageSize", 1);
		dataMap.put("gpsno", gpsno);
		dataMap.put("starttime", lastTimestamp);
		dataMap.put("endtime", timestamp);
		JSONObject json=JSONObject.fromObject(dataMap);
		String data=json.toString();
		sign=Encrypt.md5(app_secrect+"app_key"+app_key+"data"+data+"method"+method+"timestamp"+timestamp+app_secrect).toUpperCase();	
		String param="method="+method+"&&app_key="+app_key+"&&timestamp="+timestamp+"&&data="+data+"&&sign="+sign;
		String result=sendPost(url,param);
		JSONObject resultJson=JSONObject.fromObject(result);
			if(resultJson.getInt("code")==0)
			{
				JSONObject resultObject=resultJson.getJSONObject("data");
				JSONArray dataArray=resultObject.getJSONArray("result");
				JSONObject dataObject=dataArray.getJSONObject(0);
				System.out.println(dataObject.toString());
				JSONArray probeArray=dataObject.getJSONArray("probeinfo");
				JSONObject probeObject=probeArray.getJSONObject(0);
				temperature=probeObject.getString("probetemp");
				
			}else
			{
				temperature="0";
			}
		}catch(Exception e)
		{
		}
		return temperature;
	}
	
	 public static String sendPost(String url, String param) {
		 PrintWriter out = null;
	        BufferedReader in = null;
	        String result = "";
	        try {
	            URL realUrl = new URL(url);
	            // 打开和URL之间的连接
	            URLConnection conn = realUrl.openConnection();
	            // 设置通用的请求属性
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("connection", "Keep-Alive");
	            conn.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // 发送POST请求必须设置如下两行
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            // 获取URLConnection对象对应的输出流
	            out = new PrintWriter(conn.getOutputStream());
	            // 发送请求参数
	            out.print(param);
	            // flush输出流的缓冲
	            out.flush();
	            // 定义BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(
	                    new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("发送 POST 请求出现异常！"+e);
	            e.printStackTrace();
	        }
	        //使用finally块来关闭输出流、输入流
	        finally{
	            try{
	                if(out!=null){
	                    out.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
	        return result;
	    }
}
