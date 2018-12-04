package com.kangkai.service.appService;

import com.kangkai.utils.Json;

public interface IProductCommentService {
	/**
	 * 评论商品
	 * @param userId
	 * @param token
	 * @param productOrderId
	 * @param comment
	 * @param evaluation
	 * @return
	 */
	Json commentProduct(Integer userId, String token, Integer productOrderId, String content, Double evaluation);
	/**
	 * 获取商品评论列表
	 * @param userId
	 * @param token
	 * @param productId
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Json getProductCommentList(Integer userId, String token, Integer productId, Integer current, Integer pageSize);
	

}
