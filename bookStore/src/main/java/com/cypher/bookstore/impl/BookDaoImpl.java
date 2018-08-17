package com.cypher.bookstore.impl;

import com.cypher.bookstore.Dao.BaseDao;
import com.cypher.bookstore.Dao.BookDao;
import com.cypher.bookstore.domain.Book;
import com.cypher.bookstore.domain.ShoppingCartItem;
import com.cypher.bookstore.web.CriteriaBook;
import com.cypher.bookstore.web.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Cypher-Z
 * @date 2018/8/12 - 19:07
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {
	@Override
	public Book getBook(int bookId) {
		String sql = "select id,author,Title,Price,PublishingDate,SalesAmount,StoreNumber,Remark from bookstore.mybooks where Id = ?";
		return queryForBean(sql, bookId);
	}

	@Override
	public Page<Book> getPage(CriteriaBook criteriaBook) {
		Page<Book> page = new Page<>(criteriaBook.getPageNo());
		page.setTotalItemCount(getTotalBookCount(criteriaBook));
		criteriaBook.setPageNo(page.getPageNo());
		page.setPageList(getList(criteriaBook, 3));
		page.setPageSize(3);
		return page;
	}

	@Override
	public long getTotalBookCount(CriteriaBook criteriaBook) {
		//language=MySQL
		String sql = "select count(*) from bookstore.mybooks where Price >= ? and Price <= ? ";
		return queryForScalar(sql, criteriaBook.getMinPrice(), criteriaBook.getMaxPrice());
	}

	@Override
	public List<Book> getList(CriteriaBook criteriaBook, int pageSize) {
		//language=MySQL
		String sql = "select * from bookstore.mybooks where Price >= ? and Price <= ? limit ?,?";
		return queryForList(sql, criteriaBook.getMinPrice(), criteriaBook.getMaxPrice(), (criteriaBook.getPageNo() - 1) * pageSize, pageSize);
	}

	@Override
	public int getStoreNumber(int id) {
		String sql = "select Storenumber from bookstore.mybooks where id = ?";
		return queryForScalar(sql, id);
	}

	@Override
	public void batchUpdateStoreNumberAndSalesNumber(Collection<ShoppingCartItem> items) {
		Object[][] args = new Object[items.size()][3];
		List<ShoppingCartItem> list = new ArrayList<>(items);
		for (int i = 0; i < items.size(); i++) {
			args[i][0]=list.get(i).getQuantity();
			args[i][1]=list.get(i).getQuantity();
			args[i][2]=list.get(i).getBook().getId();
		}

		String sql = "update bookstore.mybooks set Storenumber = Storenumber - ?,Salesamount = Salesamount + ? where id = ?";
		batch(sql,args);
	}
}
