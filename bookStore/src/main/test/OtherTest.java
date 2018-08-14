import com.cypher.bookstore.Dao.TradeItemDao;
import com.cypher.bookstore.domain.Trade;
import com.cypher.bookstore.domain.TradeItem;
import com.cypher.bookstore.impl.AccountDaoImpl;
import com.cypher.bookstore.impl.TradeDaoImpl;

import com.cypher.bookstore.impl.TradeItemDaoImpl;
import com.cypher.bookstore.impl.UserDaoImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/**
 * @author Cypher-Z
 * @date 2018/8/13 - 1:49
 */
public class OtherTest {
	AccountDaoImpl accountDao = new AccountDaoImpl();
	TradeDaoImpl tradeDao = new TradeDaoImpl();
	TradeItemDaoImpl tradeItemDao = new TradeItemDaoImpl();
	UserDaoImpl userDao = new UserDaoImpl();
	@Test
	public void testAccountGet() {
		System.out.println(accountDao.get(1));
	}
	@Test
	public void testInsert() {
		Trade trade = new Trade(5,new Date());
		tradeDao.insert(trade);
	}
	@Test
	public void testUpdateBalance() {
		accountDao.updateBalance(1,10);
	}

	@Test
	public void testGetTradesByUserId() {
		System.out.println(tradeDao.getTradesByUserId(2));
	}
	@Test
	public void TestBatchSave() {
		ArrayList tradeItems = new ArrayList<>();
		tradeItems.add(new TradeItem(8,8,8));
		tradeItems.add(new TradeItem(9,9,9));
		tradeItems.add(new TradeItem(7,7,7));
		tradeItemDao.batchSave(tradeItems);
	}
	@Test
	public void testGetUser() {
		System.out.println(userDao.getUser("BB"));
	}
}
