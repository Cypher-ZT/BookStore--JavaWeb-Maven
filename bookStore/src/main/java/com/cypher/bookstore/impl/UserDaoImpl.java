package com.cypher.bookstore.impl;

import com.cypher.bookstore.Dao.BaseDao;
import com.cypher.bookstore.Dao.UserDao;
import com.cypher.bookstore.domain.User;

/**
 * @author Cypher-Z
 * @date 2018/8/13 - 2:57
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {
	@Override
	public User getUser(String userName) {
		String sql = "select * from bookstore.userinfo where username = ?";
		return queryForBean(sql,userName);
	}
}
