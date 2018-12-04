package com.kangkai.service.utilService.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangkai.mapper.util.ArticleMapper;
import com.kangkai.pojo.Address;
import com.kangkai.pojo.Article;
import com.kangkai.pojo.User;
import com.kangkai.service.utilService.IArticleService;
import com.kangkai.utils.Json;
import com.kangkai.utils.PageUtil;
import com.kangkai.utils.TokenUtil;


@Service(value="/articleService")
@Transactional
public class ArticleService implements IArticleService{

	@Resource
	private ArticleMapper articleMapper;
	//日志记录
	private Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public Json getArticle( Integer type) 
	{
		Json json=new Json();
		Article article=articleMapper.selectSystemDocument(type);
		if(article!=null)
		{
			json.setCode(100);
			json.setData(article.getContent());
		}
		return json;
	}

	

	
	
}
