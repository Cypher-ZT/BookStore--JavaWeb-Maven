package com.cypher.bookstore.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cypher-Z
 * @date 2018/8/13 - 21:44
 */
public class ShoppingCart {
	private Map<Integer, ShoppingCartItem> books = new HashMap<>();

	public void addBook(Book book) {
		ShoppingCartItem shoppingCartItem = books.get(book.getId());
		if (shoppingCartItem != null) {
			shoppingCartItem.increment();
		} else {
			books.put(book.getId(), new ShoppingCartItem(book));
		}
	}

	public boolean hasBook(Integer bookId) {
		return books.containsKey(bookId);
	}

	public Map<Integer, ShoppingCartItem> getBooks() {
		return books;
	}

	public Collection<ShoppingCartItem> getItems() {
		return books.values();
	}

	public int getBookNumber() {
		int bookNumber = 0;
		for (ShoppingCartItem item : books.values()) {
			bookNumber += item.getQuantity();
		}
		return bookNumber;
	}

	public float getTotalMoney() {
		float totalMoney = 0;
		for (ShoppingCartItem item : books.values()) {
			totalMoney += item.getItemMoney();
		}
		return totalMoney;
	}

	public boolean isEmpty() {
		return books.isEmpty();
	}

	public void clear() {
		books.clear();
	}

	public void removeItem(int bookId) {
		books.remove(bookId);
	}

	public void updateItemQuantity(int bookId, int newQuantity) {
		ShoppingCartItem shoppingCartItem = books.get(bookId);
		if (shoppingCartItem!=null){
			shoppingCartItem.setQuantity(newQuantity);
		}
	}
}
