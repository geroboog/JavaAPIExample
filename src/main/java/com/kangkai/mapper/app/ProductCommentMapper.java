package com.kangkai.mapper.app;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.ProductAttr;
import com.kangkai.pojo.ProductComment;
import com.kangkai.pojo.Province;
import com.kangkai.vo.AttrVO;
import com.kangkai.vo.UserCartitemVO;
import com.kangkai.vo.UserCommentVO;
import com.kangkai.vo.UserProductDetailVO;
import com.kangkai.vo.UserProductVO;

public interface ProductCommentMapper {
	/**
	 * 插入一条评价
	 * @param commentBean
	 */
	void insertComment(ProductComment commentBean);
	/**
	 * 获取商品评论列表
	 * @param map
	 * @return
	 */
	List<UserCommentVO> selectProductCommentList(Map<String, Object> map);


	
}
