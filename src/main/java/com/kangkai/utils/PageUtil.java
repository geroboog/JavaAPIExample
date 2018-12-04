package com.kangkai.utils;

import java.util.HashMap;
import java.util.Map;

public class PageUtil {
	public static Map<String, Object> getMap(Integer current,Integer pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		Page page = new Page();
		if(current==null)
		{
			current=1;
		}
		if(pageSize==null)
		{
			pageSize=Integer.MAX_VALUE;
		}
		page.setCurrent(current);
		page.setPageSize(pageSize);
		map.put("begin", page.getBegin());
		map.put("pageSize", pageSize);
		return map;
	}
}
