package com.cypher.bookstore.Dao;

import com.cypher.bookstore.domain.Book;
import com.cypher.bookstore.domain.ShoppingCartItem;
import com.cypher.bookstore.web.CriteriaBook;
import com.cypher.bookstore.web.Page;

import java.util.Collection;
import java.util.List;

/**
 * @author Cypher-Z
 * @date 2018/8/10 - 21:54
 */
public interface BookDao {
	//	由bookId得到book对象
	Book getBook(int bookId);

	Page<Book> getPage(CriteriaBook criteriaBook);

	long getTotalBookCount(CriteriaBook criteriaBook);

	List<Book> getList(CriteriaBook criteriaBook, int pageSize);

	int getStoreNumber(int id);

	void batchUpdateStoreNumberAndSalesNumber(Collection<ShoppingCartItem> items);
}
