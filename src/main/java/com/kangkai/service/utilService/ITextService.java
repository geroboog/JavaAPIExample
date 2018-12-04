package com.kangkai.service.utilService;

import com.kangkai.pojo.ProductOrder;
import com.kangkai.utils.Json;

public interface ITextService {
	/**
	 * 获取一段文本中的敏感词
	 * @param userId
	 * @param token
	 * @param content
	 * @return
	 */
	Json getContentBadWord(Integer userId, String token, String content);
	
}
