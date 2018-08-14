package com.cypher.bookstore.domain;

import lombok.Data;

/**
 * @author Cypher-Z
 * @date 2018/8/11 - 19:45
 */
@Data
public class Account {
	private int accountId;
	private float balance;

	public Account(int accountId) {
		this.accountId = accountId;
	}

	public Account() {
	}
}
