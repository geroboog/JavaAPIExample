package com.kangkai.utils;

import com.kangkai.utils.PropsConfigReader;

public class RedisConfig {
    private int redisbd ; 
    private String redishost ;
    private String redispass;
    
    private static RedisConfig m_instance = null;
    
    private RedisConfig() {
        reLoad();
    }
    
    
    //每次调用该工厂方法返回该实例
    public synchronized static RedisConfig getInstance() {
    	if(m_instance == null){
    		m_instance = new RedisConfig();
    		
    	}
        return m_instance;  
    }
    
    public synchronized void reLoad() {
    	 PropsConfigReader config = new PropsConfigReader("redis.properties");

         redisbd = config.getInt("redisdb");
         redishost = config.getString("redishost");
         redispass = config.getString("redispass");
    }

	public int getRedisbd() {
		return redisbd;
	}


	public String getRedishost() {
		return redishost;
	}
	
	public String getRedispass() {
		return redispass;
	}
}
