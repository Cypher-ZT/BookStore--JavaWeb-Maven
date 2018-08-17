package com.cypher.bookstore.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Cypher-Z
 * @date 2018/8/7 - 19:48
 */
public class JDBCUtils {
	private static DataSource dataSource;

	static {
		dataSource = new ComboPooledDataSource("mysql");
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	public static void release(PreparedStatement preparedStatement,ResultSet resultSet) {
		try {
			if (preparedStatement != null) {
				DbUtils.close(preparedStatement);
			}
			if (resultSet != null) {
				DbUtils.close(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
