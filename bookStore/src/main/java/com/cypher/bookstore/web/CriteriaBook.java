package com.cypher.bookstore.web;

import lombok.Data;

/**
 * @author Cypher-Z
 * @date 2018/8/10 - 20:54
 */

//封装查询条件的对象
@Data
public class CriteriaBook {
	private float minPrice = 0;
	private float maxPrice = Integer.MAX_VALUE;
	private int pageNo;

	public CriteriaBook(float minPrice, float maxPrice, int pageNo) {
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.pageNo = pageNo;
	}

	public CriteriaBook() {
	}
}
