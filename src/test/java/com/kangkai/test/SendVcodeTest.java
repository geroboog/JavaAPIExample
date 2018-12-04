package com.kangkai.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.kangkai.mapper.util.ExpressMapper;
import com.kangkai.utils.Encrypt;
import com.kangkai.utils.GetuiUtil;
import com.kangkai.utils.HttpRequest;
import com.kangkai.utils.JPUSHUtils;
import com.kangkai.utils.LogisticsUtils;
import com.kangkai.utils.SDKTestSendTemplateSMS;
import com.kangkai.utils.SendVcodeUtil;
import com.kangkai.utils.TemperatureUtil;
import com.taobao.api.ApiException;
import com.kangkai.vo.LogisticsInfoVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SendVcodeTest {
	private ExpressMapper expressMapper;
	@Test
	public void fun() throws ApiException {
		GetuiUtil.pushProductOrderMessageToSomeBody("123", "qwe23", "user1", 13);
	}
}
