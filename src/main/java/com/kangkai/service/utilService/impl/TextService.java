package com.kangkai.service.utilService.impl;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.mapper.app.UserMapper;
import com.kangkai.mapper.util.BadWordMapper;
import com.kangkai.mapper.util.WasteMapper;
import com.kangkai.pojo.BadWord;
import com.kangkai.pojo.ProductOrder;
import com.kangkai.pojo.Waste;
import com.kangkai.service.utilService.ITextService;
import com.kangkai.service.utilService.IWasteService;
import com.kangkai.utils.Constants;
import com.kangkai.utils.Json;
import com.kangkai.utils.TokenUtil;


@Service(value="/textService")
@Transactional
public class TextService implements ITextService{
	
	@Resource
	private BadWordMapper badWordMapper;
	@Resource
	private UserMapper userMapper;
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());
	
	
	@Override
	public Json getContentBadWord(Integer userId, String token, String content) {
		Json json=new Json();
		
		boolean isTrue=TokenUtil.checkToken(userId,userMapper, token);
		if(!isTrue){
			json.setCode(200);
			json.setData(null);
			log.error("无效的token");
			return json;
		}
		String patternStr=getPatternStr();
		 Pattern pattern = Pattern.compile(patternStr);
		 Matcher matcher = pattern.matcher(content);
		 String matchWord="";
		 
		 while(matcher.find())
		 {
			 matchWord+=matcher.group(0)+",";
		 }
		 if(!matchWord.equals(""))
		 {
			 matchWord=matchWord.substring(0,matchWord.length()-1);
		 }
		 json.setCode(100);
		 json.setData(matchWord);
		
		return json;

	}
	private String getPatternStr()
	{
		String patternStr="";
		List<BadWord> badWordList=badWordMapper.selectAll();
		for(int i=0;i<badWordList.size();i++)
		{
			patternStr+=badWordList.get(i).getContent()+"|";
		}
		patternStr=patternStr.substring(0, patternStr.length()-1);
		return patternStr;
		
	}
	
	
}
