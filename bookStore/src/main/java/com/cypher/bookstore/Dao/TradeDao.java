package com.cypher.bookstore.Dao;

import com.cypher.bookstore.domain.Trade;

import java.util.List;

/**
 * @author Cypher-Z
 * @date 2018/8/11 - 19:50
 */
public interface TradeDao {
	//	新增交易
	int insert(Trade trade);

	//	由userID得到该用户所有的trade集合
	List<Trade> getTradesByUserId(Integer userId);

}
