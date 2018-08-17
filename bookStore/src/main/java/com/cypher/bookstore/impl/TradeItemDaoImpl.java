package com.cypher.bookstore.impl;

import com.cypher.bookstore.Dao.BaseDao;
import com.cypher.bookstore.Dao.TradeItemDao;
import com.cypher.bookstore.domain.ShoppingCartItem;
import com.cypher.bookstore.domain.TradeItem;

import java.util.*;

/**
 * @author Cypher-Z
 * @date 2018/8/13 - 2:11
 */
public class TradeItemDaoImpl extends BaseDao<TradeItem> implements TradeItemDao {
	@Override
	public void batchSave(Collection<TradeItem> tradeItems) {
		String sql = "insert into bookstore.tradeitem (bookid, quantity, tradeid) VALUES (?,?,?)";
		Object[][] args = new Object[tradeItems.size()][3];
		List<TradeItem> list = new ArrayList<>(tradeItems);
		for (int i = 0; i < tradeItems.size(); i++) {
			args[i][0]=list.get(i).getBookId();
			args[i][1]=list.get(i).getQuantity();
			args[i][2]=list.get(i).getTradeId();
		}
		batch(sql,args);
	}

	@Override
	public List<TradeItem> getTradeItemByTradeId(Integer tradeId) {
		String sql = "select * from bookstore.tradeitem where tradeid = ? order by quantity";
		return  queryForList(sql,tradeId);
	}
}
