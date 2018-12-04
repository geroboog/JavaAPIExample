package com.kangkai.service.utilService.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.mapper.util.SystemConfigMapper;
import com.kangkai.pojo.PayWay;
import com.kangkai.service.utilService.ISystemConfigService;
import com.kangkai.utils.Constants;
import com.kangkai.utils.Json;


@Service(value="/systemConfigService")
@Transactional
public class SystemConfigService implements ISystemConfigService {
	
	@Resource
	private SystemConfigMapper systemConfigMapper;
	@Override
	public Json getSystemConfig() {
		Json json=new Json();
		List<PayWay> payWayList=systemConfigMapper.selectAllPayWay();
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("QINIU_URL_PREFIX", Constants.QINIU_URL_PREFIX);
		result.put("payWayLIst", payWayList);
		json.setCode(100);
		json.setData(result);
		return json;
		
	}

}
