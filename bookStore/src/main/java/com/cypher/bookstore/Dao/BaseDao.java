package com.cypher.bookstore.Dao;

import com.cypher.bookstore.utils.JDBCUtils;
import com.cypher.bookstore.utils.ReflectionUtils;
import com.cypher.bookstore.web.ConnectionContext;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;
import java.util.List;

/**
 * @author Cypher-Z
 * @date 2018/8/10 - 20:18
 */
public class BaseDao<T> implements Dao<T> {
	private Class clazz;
	private QueryRunner queryRunner = new QueryRunner();

	public BaseDao() {
		clazz = ReflectionUtils.getSuperGenericType(getClass());
	}

	@Override
	public int insert(String sql, Object... args) {
		ResultSet resultSet = null;
		Connection connection;
		PreparedStatement preparedStatement = null;
		int id = 0;
		try {
			connection = ConnectionContext.getInstance().get();
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					preparedStatement.setObject(i + 1, args[i]);
				}
			}
			preparedStatement.execute();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(preparedStatement, resultSet);
		}
		return id;
	}

	@Override
	public void update(String sql, Object... args) {
		try {
			Connection connection = ConnectionContext.getInstance().get();
			queryRunner.update(connection, sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<T> queryForList(String sql, Object... args) {
		try {
			Connection connection = ConnectionContext.getInstance().get();
			return (List<T>) queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public T queryForBean(String sql, Object... args) {
		try {
			Connection connection = ConnectionContext.getInstance().get();
			return (T) queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <E> E queryForScalar(String sql, Object... args) {
		try {
			Connection connection = ConnectionContext.getInstance().get();
			return (E) queryRunner.query(connection, sql, new ScalarHandler<>(), args);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void batch(String sql, Object[]... args) {
		try {
			Connection connection = ConnectionContext.getInstance().get();
			queryRunner.batch(connection, sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
