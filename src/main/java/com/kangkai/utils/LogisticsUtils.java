package com.kangkai.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.kangkai.mapper.util.ExpressMapper;
import com.kangkai.pojo.Express;
import com.kangkai.vo.LogisticsInfoVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.security.MessageDigest;

public class LogisticsUtils {

	/**
	 * 物流状态(在途中)
	 */
	public static final int ON_WAY_TYPE = 2;
	/**
	 * 物流状态(已签收)
	 */
	public static final int ALREAD_SING_TYPE = 3;
	/**
	 * 物流状态(问题件)
	 */
	public static final int PROBLEM_PIECES_TYPE = 4;

	// 电商ID
	private static String EBusinessID = "1264132";
	// 电商加密私钥，快递鸟提供，注意保管，不要泄漏
	private static String AppKey = "e0166f45-f1c3-47fa-ac45-748b01a405ee";
	// 请求url
	private static String ReqURL = "http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx";

	/**
	 * Json方式 查询订单物流轨迹
	 * 
	 * @throws Exception
	 */
	public static String getOrderTracesByJson(String requestData) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("RequestData", urlEncoder(requestData, "UTF-8"));
		params.put("EBusinessID", EBusinessID);
		params.put("RequestType", "1002");
		String dataSign = encrypt(requestData, AppKey, "UTF-8");
		params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
		params.put("DataType", "2");

		String result = sendPost(ReqURL, params);

		// 根据公司业务处理返回的信息......

		return result;
	}

	public static void main(String[] args) {
		try {
			String requestData = "{'OrderCode':'','ShipperCode':'QFKD','LogisticCode':'390109458972'}";
			System.out.println(getOrderTracesByJson(requestData));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * XML方式 查询订单物流轨迹
	 * 
	 * @throws Exception
	 */
	public String getOrderTracesByXml() throws Exception {
		String requestData = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" + "<Content>" + "<OrderCode></OrderCode>"
				+ "<ShipperCode>SF</ShipperCode>" + "<LogisticCode>589707398027</LogisticCode>" + "</Content>";

		Map<String, String> params = new HashMap<String, String>();
		params.put("RequestData", urlEncoder(requestData, "UTF-8"));
		params.put("EBusinessID", EBusinessID);
		params.put("RequestType", "1002");
		String dataSign = encrypt(requestData, AppKey, "UTF-8");
		params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
		params.put("DataType", "1");

		String result = sendPost(ReqURL, params);

		// 根据公司业务处理返回的信息......

		return result;
	}

	/**
	 * MD5加密
	 * 
	 * @param str
	 *            内容
	 * @param charset
	 *            编码方式
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static String MD5(String str, String charset) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes(charset));
		byte[] result = md.digest();
		StringBuffer sb = new StringBuffer(32);
		for (int i = 0; i < result.length; i++) {
			int val = result[i] & 0xff;
			if (val < 0xf) {
				sb.append("0");
			}
			sb.append(Integer.toHexString(val));
		}
		return sb.toString().toLowerCase();
	}

	/**
	 * base64编码
	 * 
	 * @param str
	 *            内容
	 * @param charset
	 *            编码方式
	 * @throws UnsupportedEncodingException
	 */
	private static String base64(String str, String charset) throws UnsupportedEncodingException {
		String encoded = Base64.encode(str.getBytes(charset));
		return encoded;
	}

	@SuppressWarnings("unused")
	private static String urlEncoder(String str, String charset) throws UnsupportedEncodingException {
		String result = URLEncoder.encode(str, charset);
		return result;
	}

	/**
	 * 电商Sign签名生成
	 * 
	 * @param content
	 *            内容
	 * @param keyValue
	 *            Appkey
	 * @param charset
	 *            编码方式
	 * @throws UnsupportedEncodingException
	 *             ,Exception
	 * @return DataSign签名
	 */
	@SuppressWarnings("unused")
	private static String encrypt(String content, String keyValue, String charset)
			throws UnsupportedEncodingException, Exception {
		if (keyValue != null) {
			return base64(MD5(content + keyValue, charset), charset);
		}
		return base64(MD5(content, charset), charset);
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param params
	 *            请求的参数集合
	 * @return 远程资源的响应结果
	 */
	@SuppressWarnings("unused")
	private static String sendPost(String url, Map<String, String> params) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// POST方法
			conn.setRequestMethod("POST");
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.connect();
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数
			if (params != null) {
				StringBuilder param = new StringBuilder();
				for (Map.Entry<String, String> entry : params.entrySet()) {
					if (param.length() > 0) {
						param.append("&");
					}
					param.append(entry.getKey());
					param.append("=");
					param.append(entry.getValue());
					System.out.println(entry.getKey() + ":" + entry.getValue());
				}
				System.out.println("param:" + param.toString());
				out.write(param.toString());
			}
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}

	public static List<LogisticsInfoVO> getLogisticsUtil(String courier, String courierNum,ExpressMapper expressMapper) {
		List<LogisticsInfoVO> logisticsInfoList=new ArrayList<LogisticsInfoVO>();
		try {
			String logisticCode = courierNum;// 运单号
			String deliverway = "";// 快递公司名称
			Express express = expressMapper.selectByName(courier);
			if(express==null)
			{
				LogisticsInfoVO logisticsInfoVO=new LogisticsInfoVO();
				logisticsInfoVO.setLogisticsInfoId(1);
				logisticsInfoVO.setRemindMessage("无快递信息：您的快递是"+courier+"，快递单号为："+courierNum);
				logisticsInfoVO.setInfoTime(DateUtils.currentDatetime());
				logisticsInfoList.add(logisticsInfoVO);
				return logisticsInfoList;
			}
			String shipperCode = express.getShipperCode();// 快递公司编码
			String requestData = "{'OrderCode':'','ShipperCode':'" + shipperCode + "','LogisticCode':'" + logisticCode
					+ "'}";
			String logisticsData = LogisticsUtils.getOrderTracesByJson(requestData);
			JSONObject jsStr = JSONObject.fromObject(logisticsData);
			int state = Integer.parseInt(jsStr.getString("State"));
			if (state == ON_WAY_TYPE) {
			} else if (state == ALREAD_SING_TYPE) {
			} else if (state == PROBLEM_PIECES_TYPE) {
			}
			JSONArray traceArray = jsStr.getJSONArray("Traces");
			if(traceArray!=null)
			{
				for (int i=0;i<traceArray.size();i++) 
				{
					LogisticsInfoVO traces = new LogisticsInfoVO();
					JSONObject jsStr2 = traceArray.getJSONObject(i);
					String acceptTime = jsStr2.getString("AcceptTime");// 时间
					String acceptStation = jsStr2.getString("AcceptStation");// 接收站
					traces.setLogisticsInfoId(i+1);
					traces.setInfoTime(acceptTime);
					traces.setRemindMessage(acceptStation);
					logisticsInfoList.add(traces);
				}
			}
		} catch (Exception e) {
			LogisticsInfoVO logisticsInfoVO=new LogisticsInfoVO();
			logisticsInfoVO.setLogisticsInfoId(1);
			logisticsInfoVO.setRemindMessage("无快递信息：您的快递是"+courier+"，快递单号为："+courierNum);
			logisticsInfoVO.setInfoTime(DateUtils.currentDatetime());
			logisticsInfoList.add(logisticsInfoVO);
		}
		return logisticsInfoList;
	}

}
