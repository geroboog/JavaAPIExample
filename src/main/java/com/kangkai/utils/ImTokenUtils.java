package com.kangkai.utils;

import java.util.HashMap;
import java.util.Map;

public class ImTokenUtils {
	private static Map<String,Object> ImToken= new HashMap<String,Object>();
	private  ImTokenUtils() 
	{
		ImToken.put("token","xxxx");
		ImToken.put("dateTime", new Long("1469524642950"));
	}  
    private static final ImTokenUtils result=new ImTokenUtils();
     
    //静态工厂方法   
    public static ImTokenUtils getInstance() {  
        return result;  
    }
    public void setBykey(String key,Object value)
    {
    	ImToken.put(key, value); 
    }
    public Object getBykey(Object key)
    {
    	return ImToken.get(key);
    }
}
