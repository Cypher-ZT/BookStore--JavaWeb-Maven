import com.cypher.bookstore.domain.Book;
import com.cypher.bookstore.domain.ShoppingCartItem;
import com.cypher.bookstore.impl.BookDaoImpl;
import com.cypher.bookstore.web.CriteriaBook;
import com.cypher.bookstore.web.Page;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Cypher-Z
 * @date 2018/8/7 - 21:07
 */
public class bookDaoTest {
	private BookDaoImpl bookDao = new BookDaoImpl();

	@Test
	public void test() {
//		System.out.println(JDBCUtils.getConnection());
		System.out.println(new Page<>(1).getPageCount());
	}

	@Test
	public void testInsert() {
		String sql = "insert into bookstore.trade(userid,tradetime) values (?,?)";

		System.out.println(bookDao.insert(sql, 2, new Date()));
	}

	@Test
	public void testUpdate() {
		String sql = "update bookstore.trade set userid = ? where tradeid=?";

		bookDao.update(sql, 2, 18);
	}

	@Test
	public void testQueryForBean() {
		String sql = "select id,Author,Title,Price,PublishingDate,SalesAmount,StoreNumber,Remark from bookstore.mybooks where Id = ?";

		System.out.println(bookDao.queryForBean(sql, 20));
	}

	@Test
	public void testQueryForList() {
		String sql = "select id,Author,Title,Price,Publishingdate,Salesamount,Storenumber,Remark from bookstore.mybooks ";
		System.out.println(bookDao.queryForList(sql));
	}

	@Test
	public void testQueryForScalar() {
		String sql = "select Remark from bookstore.mybooks where Id = ?";

		System.out.println((String) bookDao.queryForScalar(sql, 20));
	}

	@Test
	public void testBatch() {
		String sql = "update bookstore.trade set userid =? where tradeid=?";
		Object[][] objects = {{1, 12}, {2, 13}, {1, 14}};
		for (Object[] object : objects) {
			System.out.println(Arrays.toString(object));
		}
		bookDao.batch(sql, objects);
	}

	@Test
	public void testGetBook() {

		System.out.println(bookDao.getBook(1));
	}

	@Test
	public void testGetTotalBookCount() {
		CriteriaBook criteriaBook = new CriteriaBook(52, 55, 1);
		System.out.println(bookDao.getTotalBookCount(criteriaBook));
	}

	@Test
	public void testGetList() {
		CriteriaBook criteriaBook = new CriteriaBook(51, 60, 1);
		System.out.println(bookDao.getList(criteriaBook, 4));
	}

	@Test
	public void testGetPage() {
		CriteriaBook criteriaBook = new CriteriaBook(50, 54, 3);
		Page page = bookDao.getPage(criteriaBook);
		System.out.println(page);
		System.out.println(page.isHasNext());
		System.out.println(page.isHasPrev());
		System.out.println(page.getPrevNo());
		System.out.println(page.getNextNo());
		System.out.println(page.getPageCount());
	}

	@Test
	public void testBatchUpdateStoreNumberAndSalesNumber() {
		ArrayList<ShoppingCartItem> items = new ArrayList<>();
		items.add(new ShoppingCartItem(new Book(1, "Tom", "Java 编程思想", 50, new Date(), 0, 100, "Good Java Book"
		)));
		items.add(new ShoppingCartItem(new Book(2, "Tom", "Java", 10, new Date(), 1, 100, "Good"
		)));
		bookDao.batchUpdateStoreNumberAndSalesNumber(items);
	}
}
