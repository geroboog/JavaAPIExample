package com.kangkai.mapper.util;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.Article;
import com.kangkai.pojo.Express;

public interface ArticleMapper {
	/**
	 * 插入一条有货小组手消息
	 * @param demandMessage
	 */
	void insert(Article demandMessage);
	/**
	 * 获取系统文档
	 * @param type
	 * @return
	 */
	Article selectSystemDocument(Integer type);
	
	

}
