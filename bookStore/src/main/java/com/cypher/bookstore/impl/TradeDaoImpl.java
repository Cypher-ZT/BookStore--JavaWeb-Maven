package com.cypher.bookstore.impl;

import com.cypher.bookstore.Dao.BaseDao;
import com.cypher.bookstore.Dao.TradeDao;
import com.cypher.bookstore.domain.Trade;

import java.util.List;
import java.util.Set;

/**
 * @author Cypher-Z
 * @date 2018/8/13 - 1:43
 */
public class TradeDaoImpl extends BaseDao<Trade> implements TradeDao {
	@Override
	public void insert(Trade trade) {
		String sql = "insert into bookstore.trade (userid, tradetime) values (?,?)";
		update(sql,trade.getUserId(),trade.getTradeTime());
	}

	@Override
	public List<Trade> getTradesByUserId(Integer userId) {
		String sql = "select * from bookstore.trade where userid = ?";
		return queryForList(sql,userId);
	}
}
