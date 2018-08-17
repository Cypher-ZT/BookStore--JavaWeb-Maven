package com.cypher.bookstore.Dao;

import java.util.List;

/**
 * @author Cypher-Z
 * @date 2018/8/7 - 19:28
 */
public interface Dao<T> {

	int insert(String sql, Object... args);

	void update(String sql, Object... args);

	List<T> queryForList(String sql, Object... args);

	T queryForBean(String sql, Object... args);

	<E> E queryForScalar(String sql, Object... args);

	void batch(String sql, Object[]... args);

}
