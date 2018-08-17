package com.cypher.bookstore.domain;

import lombok.Data;

/**
 * @author Cypher-Z
 * @date 2018/8/11 - 19:50
 */
@Data
public class TradeItem {
	private int itemId;
	private int bookId;
	private int quantity;
	private int tradeId;
	private Book book;

	public TradeItem(int bookId, int quantity, int tradeId) {
		this.bookId = bookId;
		this.quantity = quantity;
		this.tradeId = tradeId;
	}

	public TradeItem() {
	}
}
