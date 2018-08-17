package com.cypher.bookstore.impl;

import com.cypher.bookstore.Dao.AccountDao;
import com.cypher.bookstore.Dao.BaseDao;
import com.cypher.bookstore.domain.Account;

/**
 * @author Cypher-Z
 * @date 2018/8/13 - 1:36
 */
public class AccountDaoImpl extends BaseDao<Account> implements AccountDao {
	@Override
	public Account getAccount(Integer accountId) {
		String sql = "select * from bookstore.account where accountid = ?";
		return queryForBean(sql,accountId);
	}

	@Override
	public void updateBalance(Integer accountId, float amount) {
		String sql = "update bookstore.account set balance = (balance - ?) where accountid = ?";
		update(sql,amount,accountId);
	}
}
