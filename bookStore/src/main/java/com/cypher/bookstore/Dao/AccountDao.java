package com.cypher.bookstore.Dao;

import com.cypher.bookstore.domain.Account;

/**
 * @author Cypher-Z
 * @date 2018/8/11 - 19:44
 */
public interface AccountDao {
	 Account getAccount(Integer accountId);
//	 扣除amount数量的钱数
	 void  updateBalance(Integer accountId,float amount);
}
