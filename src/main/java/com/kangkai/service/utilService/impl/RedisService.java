package com.kangkai.service.utilService.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.utils.Redis;
import com.kangkai.utils.SerializeUtil;

import redis.clients.jedis.Jedis;

@Service(value="/RedisService")
@Transactional
public class RedisService {

	private static final Logger logger = Logger.getLogger(RedisService.class);
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat sdf_day = new SimpleDateFormat("yyyy-MM-dd");

	public void setKeyRedis(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = Redis.getInstance();
			 jedis.set(key, value);
			 jedis.expire(key, 3000);
		} finally {
			if (jedis != null) {
				Redis.freeConn(jedis);
			}
		}
	}
	public void setKeyRedis(String key,String field, String value) {
		Jedis jedis = null;
		try {
			jedis = Redis.getInstance();
			 jedis.hset(key,field, value);
		} finally {
			if (jedis != null) {
				Redis.freeConn(jedis);
			}
		}
	}
	
	public void setHMKeyRedis(String key,Map<String,String> hash) {
		Jedis jedis = null;
		try {
			jedis = Redis.getInstance();
			 jedis.hmset(key, hash);
		} finally {
			if (jedis != null) {
				Redis.freeConn(jedis);
			}
		}
	}

	public String getKeyRedis(String key) {
		Jedis jedis = null;
		try {
			jedis = Redis.getInstance();
			return  jedis.get(key);
			
		} finally {
			if (jedis != null) {
				Redis.freeConn(jedis);
			}
		}
	}
	public String getKeyRedis(String key,String field) {
		Jedis jedis = null;
		try {
			jedis = Redis.getInstance();
			 return jedis.hget(key, field);
			
		} finally {
			if (jedis != null) {
				Redis.freeConn(jedis);
			}
		}
	}
	
	public List<String> getHMKeyRedis(String key,String[] field) {
		Jedis jedis = null;
		try {
			jedis = Redis.getInstance();
			 return jedis.hmget(key, field);
			
		} finally {
			if (jedis != null) {
				Redis.freeConn(jedis);
			}
		}
	}
	public Map<String,List<String>> getAllHMSET(String pattern, String[] field)
	{
		Jedis jedis = null;
		try {
			jedis = Redis.getInstance();
			 Set<String> keys= jedis.keys(pattern);
			 Map<String,List<String>> userLocationMap=new HashMap<String,List<String>>();
			 String[] keyArray =new String[keys.size()];
			 keyArray=keys.toArray(keyArray);
			 userLocationMap=test(jedis,keyArray,field);
			 return userLocationMap;
			 
		} finally {
			if (jedis != null) {
				Redis.freeConn(jedis);
			}
		}

	}
	
	private Map<String, List<String>> test(Jedis jedis,String[] keyArray, String[] field)
	{
		 Map<String,List<String>> userLocationMap=new HashMap<String,List<String>>();
		 for (int k=0;k<keyArray.length;k++) 
			 {
				List<String> thisUseLoctionMap=jedis.hmget(keyArray[k], field);
				 userLocationMap.put(keyArray[k], thisUseLoctionMap);
			 }
		return userLocationMap;
	}
	
	public Object getKeyRedisOb(String key) {
		Jedis jedis = null;
		try {
			jedis = Redis.getInstance();
			 byte[] value=jedis.get(key.getBytes());
			 Object object = SerializeUtil.unserialize(value); 
			 return object;
		} finally {
			if (jedis != null) {
				Redis.freeConn(jedis);
			}
		}
	}
	
}
