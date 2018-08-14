package com.cypher.bookstore.Dao;

import com.cypher.bookstore.domain.User;

/**
 * @author Cypher-Z
 * @date 2018/8/11 - 20:12
 */
public interface UserDao {
	User getUser(String userName);
}
