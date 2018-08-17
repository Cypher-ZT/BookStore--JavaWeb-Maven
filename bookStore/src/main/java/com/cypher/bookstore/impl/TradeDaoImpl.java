package com.cypher.bookstore.impl;

import com.cypher.bookstore.Dao.BaseDao;
import com.cypher.bookstore.Dao.TradeDao;
import com.cypher.bookstore.domain.Trade;

import java.util.Date;
import java.util.List;

/**
 * @author Cypher-Z
 * @date 2018/8/13 - 1:43
 */
public class TradeDaoImpl extends BaseDao<Trade> implements TradeDao {
	@Override
	public int insert(Trade trade) {
		String sql = "insert into bookstore.trade (userid, tradetime) values (?,?)";
		return insert(sql,trade.getUserId(),new Date());
	}

	@Override
	public List<Trade> getTradesByUserId(Integer userId) {
		String sql = "select * from bookstore.trade where userid = ? order by tradetime desc ";
		return queryForList(sql,userId);
	}
}
