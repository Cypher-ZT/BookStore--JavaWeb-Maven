package com.cypher.bookstore.impl;

import com.cypher.bookstore.Dao.BaseDao;
import com.cypher.bookstore.Dao.TradeItemDao;
import com.cypher.bookstore.domain.TradeItem;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Cypher-Z
 * @date 2018/8/13 - 2:11
 */
public class TradeItemDaoImpl extends BaseDao<TradeItem> implements TradeItemDao {
	@Override
	public void batchSave(Collection<TradeItem> tradeItems) {
		String sql = "insert into bookstore.tradeitem (bookid, quantity, tradeid) VALUES (?,?,?)";
		
//     暂留
	}

	@Override
	public List<TradeItem> getTradeItemByTradeId(Integer tradeId) {
		String sql = "select * from bookstore.tradeitem where tradeid = ?";
		return  queryForList(sql,tradeId);
	}
}
