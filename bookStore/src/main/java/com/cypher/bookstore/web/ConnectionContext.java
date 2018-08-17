package com.cypher.bookstore.web;

import java.sql.Connection;

/**
 * @author Cypher-Z
 * @date 2018/8/16 - 9:02
 */
public class ConnectionContext {
	private ConnectionContext() {
	}

	private static ConnectionContext instance = new ConnectionContext();

	public static ConnectionContext getInstance() {
		return instance;
	}

	private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

	public void bind(Connection connection) {
		connectionThreadLocal.set(connection);
	}

	public Connection get() {
		return connectionThreadLocal.get();
	}
	public void remove(){
		connectionThreadLocal.remove();
	}
}
