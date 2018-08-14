package com.cypher.bookstore.domain;

import lombok.Data;

/**
 * @author Cypher-Z
 * @date 2018/8/11 - 19:50
 */
@Data
public class User {
	private int userId;
	private String userName;
	private int accountId;

	public User(int userId, String userName, int accountId) {
		this.userId = userId;
		this.userName = userName;
		this.accountId = accountId;
	}

	public User() {
	}
}
