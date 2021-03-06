package com.cypher.bookstore.domain;

/**
 * @author Cypher-Z
 * @date 2018/8/13 - 21:36
 */
public class ShoppingCartItem {
	private Book book;
	private int quantity;

	public ShoppingCartItem(Book book) {
		this.book = book;
		setQuantity(1);
	}

	public ShoppingCartItem() {
	}

	public Book getBook() {
		return book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getItemMoney(){
		return book.getPrice()*quantity;
	}

	public void increment(){
		quantity++;
	}  
}
