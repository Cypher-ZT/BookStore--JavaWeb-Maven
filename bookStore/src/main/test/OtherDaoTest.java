import com.cypher.bookstore.domain.Book;
import com.cypher.bookstore.domain.ShoppingCartItem;
import com.cypher.bookstore.domain.Trade;
import com.cypher.bookstore.domain.TradeItem;
import com.cypher.bookstore.impl.AccountDaoImpl;
import com.cypher.bookstore.impl.TradeDaoImpl;
import com.cypher.bookstore.impl.TradeItemDaoImpl;
import com.cypher.bookstore.impl.UserDaoImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;


/**
 * @author Cypher-Z
 * @date 2018/8/13 - 1:49
 */
public class OtherDaoTest {
	private AccountDaoImpl accountDao = new AccountDaoImpl();
	private TradeDaoImpl tradeDao = new TradeDaoImpl();
	private TradeItemDaoImpl tradeItemDao = new TradeItemDaoImpl();
	private UserDaoImpl userDao = new UserDaoImpl();

	//	accountDao
	@Test
	public void testGetAccount() {
		System.out.println(accountDao.getAccount(1));
	}

	@Test
	public void testUpdateBalance() {
		accountDao.updateBalance(1, 10);
	}

	//	tradeDao
	@Test
	public void testInsert() {
		Trade trade = new Trade(5, new Date());
		System.out.println(tradeDao.insert(trade));
	}

	@Test
	public void testGetTradesByUserId() {
		System.out.println(tradeDao.getTradesByUserId(2));
	}

	//	tradeItemDao
	@Test
	public void testBatchSave() {
		ArrayList<TradeItem> tradeItems = new ArrayList<>();
		tradeItems.add(new TradeItem(8, 8, 8));
		tradeItems.add(new TradeItem(9, 9, 9));
		tradeItems.add(new TradeItem(7, 7, 7));
		tradeItemDao.batchSave(tradeItems);
	}

	@Test
	public void testGetTradeItemByTradeId() {
		System.out.println(tradeItemDao.getTradeItemByTradeId(12));
	}

	//	userDao
	@Test
	public void testGetUser() {
		System.out.println(userDao.getUser("Tom"));
	}
	@Test
	public void testBatchUpdateStoreNumberAndSalesNumber() {
		ArrayList<TradeItem> items = new ArrayList<>();
		items.add(new TradeItem(1,2,18));
		items.add(new TradeItem(2,1,18));
		tradeItemDao.batchSave(items);
	}
}
