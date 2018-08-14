package com.cypher.bookstore.service;

import com.cypher.bookstore.domain.Book;
import com.cypher.bookstore.domain.ShoppingCart;
import com.cypher.bookstore.impl.BookDaoImpl;
import com.cypher.bookstore.web.CriteriaBook;
import com.cypher.bookstore.web.Page;

/**
 * @author Cypher-Z
 * @date 2018/8/13 - 18:16
 */
public class BookService {
	private BookDaoImpl bookDao = new BookDaoImpl();
	public Page<Book> getPage(CriteriaBook criteriaBook){
		return bookDao.getPage(criteriaBook);
	}
	public Book getBook(int bookId) {
		return bookDao.getBook(bookId);
	}
	public void addToCart(ShoppingCart shoppingCart,Book book) {
		shoppingCart.addBook(book);
	}
}
