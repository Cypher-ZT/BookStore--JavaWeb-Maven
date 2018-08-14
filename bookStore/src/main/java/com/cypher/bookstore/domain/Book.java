package com.cypher.bookstore.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author Cypher-Z
 * @date 2018/8/10 - 20:53
 */
@Data
public class Book {
	private int id;
	private String author;
	private String title;
	private float price;
	private Date publishingDate;
	private int salesAmount;
	private int storeNumber;
	private String remark;

	public Book(int id, String author, String title, float price, Date publishingDate, int salesAmount, int storeNumber, String remark) {

		this.id = id;
		this.author = author;
		this.title = title;
		this.price = price;
		this.publishingDate = publishingDate;
		this.salesAmount = salesAmount;
		this.storeNumber = storeNumber;
		this.remark = remark;
	}

	public Book(String author, String title, float price, Date publishingDate, int salesAmount, int storeNumber, String remark) {
		this.author = author;
		this.title = title;
		this.price = price;
		this.publishingDate = publishingDate;
		this.salesAmount = salesAmount;
		this.storeNumber = storeNumber;
		this.remark = remark;
	}

	public Book() {
	}
}
