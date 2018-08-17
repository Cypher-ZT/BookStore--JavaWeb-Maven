package com.cypher.bookstore.domain;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Cypher-Z
 * @date 2018/8/11 - 19:49
 */
@Data
public class Trade {
	private int tradeId;

	private int userId;

	private Date tradeTime;

	private List<TradeItem>items = new ArrayList<>();

	public Trade(int userId, Date tradeTime) {
		this.userId = userId;
		this.tradeTime = tradeTime;
	}

	public Trade() {
	}

}
