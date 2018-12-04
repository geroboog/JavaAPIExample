package com.kangkai.utils;

import org.apache.log4j.Logger;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Redis {
	
	private static final Logger logger = Logger.getLogger(Redis.class);
	private static int get_instance_count = 0;
	private static int free_instance_count = 0;
	
	private static JedisPool pool;
	
	public static synchronized JedisPool getPool(){
		if(pool==null){
			logger.warn("Init Redis pool");
			 JedisPoolConfig config = new JedisPoolConfig();  
	         //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；  
	         //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。  
			 config.setMaxTotal(3000);
	         config.setMaxIdle(1000);	         
	         //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；  
	         config.setMaxWaitMillis(1000 * 100);  
	         config.setBlockWhenExhausted(true);
	         //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
	         config.setTestOnBorrow(false); 
	         config.setTestOnReturn(true);  
	 		String redishost = RedisConfig.getInstance().getRedishost();
	         pool = new JedisPool(config, redishost, 6379, 20000, RedisConfig.getInstance().getRedispass());
		}
         return pool;
	} 
	
	public static synchronized Jedis getInstance(){
		Jedis jedis= getPool().getResource();	
		int redisbd = RedisConfig.getInstance().getRedisbd();
		jedis.select(redisbd);
//		get_instance_count++;
//		logger.warn("get_instance_count: " + get_instance_count);
		return jedis;
	} 
	

	
	@SuppressWarnings("deprecation")
	public static synchronized void freeConn(Jedis jedis){
		if (jedis != null) {
			pool.returnResource(jedis);
		}

//		free_instance_count++;
//		logger.warn("free_instance_count: " + free_instance_count);
	} 
	
	
}
