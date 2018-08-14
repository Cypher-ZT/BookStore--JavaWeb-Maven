package com.cypher.bookstore.Dao;

import com.cypher.bookstore.domain.TradeItem;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Cypher-Z
 * @date 2018/8/11 - 19:52
 */
public interface TradeItemDao {

	//	批量保存交易项
	void batchSave(Collection<TradeItem> tradeItems);

	//	根据tradeId获取tradeItem集合
	List<TradeItem> getTradeItemByTradeId(Integer tradeId);

}
