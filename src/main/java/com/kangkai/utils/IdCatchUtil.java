package com.kangkai.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.ehcache.statistics.sampled.SampledCacheStatisticsImpl;

public class IdCatchUtil {
	public  static String catchId(Object object){
		ObjectMapper objectMapper = new ObjectMapper();
		String message = String.valueOf(object);
		String id="";
		if (message != null) {
			try {
				JsonNode jsonNode = objectMapper.readTree(message);
				String data = jsonNode.get("data").toString();
				JsonNode jsonNode2 = objectMapper.readTree(data);
				String sourseid = jsonNode2.get("groupid").toString();
				 id= sourseid.substring(1, 19);
			} catch (JsonProcessingException e) {
				return "";
			} catch (IOException e) {
				return "";
			}
		}
		return id;
	}

}
