package com.cypher.bookstore.service;

import com.cypher.bookstore.domain.*;
import com.cypher.bookstore.impl.*;
import com.cypher.bookstore.web.CriteriaBook;
import com.cypher.bookstore.web.Page;

import java.util.Collection;
import java.util.List;

/**
 * @author Cypher-Z
 * @date 2018/8/13 - 18:16
 */
public class BookStoreService {
	private BookDaoImpl bookDao = new BookDaoImpl();
	private UserDaoImpl userDao = new UserDaoImpl();
	private AccountDaoImpl accountDao = new AccountDaoImpl();
	private TradeDaoImpl tradeDao = new TradeDaoImpl();
	private TradeItemDaoImpl tradeItemDao = new TradeItemDaoImpl();

	public Page<Book> getPage(CriteriaBook criteriaBook) {
		return bookDao.getPage(criteriaBook);
	}

	public Book getBook(int bookId) {
		return bookDao.getBook(bookId);
	}

	public void addToCart(ShoppingCart shoppingCart, Book book) {
		shoppingCart.addBook(book);
	}

	public User getUser(String userName) {
		return userDao.getUser(userName);
	}

	public int getStoreNumber(int id) {
		return bookDao.getStoreNumber(id);
	}

	public Account getAccount(Integer accountId) {
		return accountDao.getAccount(accountId);
	}

	public void batchUpdateStoreNumberAndSalesNumber(Collection<ShoppingCartItem> items) {
		bookDao.batchUpdateStoreNumberAndSalesNumber(items);
	}

	public void updateBalance(Integer accountId, float amount) {
		accountDao.updateBalance(accountId, amount);
	}

	public int insert(Trade trade) {
		return tradeDao.insert(trade);
	}

	public void batchSave(Collection<TradeItem> tradeItems) {
		tradeItemDao.batchSave(tradeItems);
	}

	public User getUserWithTrades(String userName) {
		User user = userDao.getUser(userName);
		if (user == null) {
			return null;
		}
		int userId = user.getUserId();
		List<Trade> trades = tradeDao.getTradesByUserId(userId);
		if (trades != null) {
			for (Trade trade : trades) {
				List<TradeItem> tradeItems = tradeItemDao.getTradeItemByTradeId(trade.getTradeId());
				if (tradeItems != null) {
					for (TradeItem tradeItem : tradeItems) {
						tradeItem.setBook(bookDao.getBook(tradeItem.getBookId()));
					}
					trade.setItems(tradeItems);
				}
			}
			user.setTrades(trades);
		}
		return user;
	}
}

