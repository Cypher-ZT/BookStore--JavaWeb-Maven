import com.cypher.bookstore.impl.BookDaoImpl;
import com.cypher.bookstore.web.CriteriaBook;
import com.cypher.bookstore.web.Page;
import org.junit.Test;

import javax.xml.transform.Source;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author Cypher-Z
 * @date 2018/8/7 - 21:07
 */
public class test {
	private BookDaoImpl bookDao = new BookDaoImpl();

	@Test
	public void test() throws SQLException {
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

		System.out.println((String) bookDao.queryForScalar(sql,20));
	}

	@Test
	public void testBatch() {
		String sql = "update bookstore.trade set userid = ? where tradeid=?";

		bookDao.batch(sql, new Object[]{2,12},new Object[]{3,13},new Object[]{4,14});
	}

	@Test
	public void testGetBook() {

		System.out.println(bookDao.getBook(1));
	}
	@Test
	public void testGetTotalBookCount() {
		CriteriaBook criteriaBook = new CriteriaBook(52,55,1);
		System.out.println(bookDao.getTotalBookCount(criteriaBook));
	}
	@Test
	public void testGetList() {
		CriteriaBook criteriaBook = new CriteriaBook(51,60,1);
		System.out.println(bookDao.getList(criteriaBook,4));
	}
	@Test
	public void testGetPage() {
		CriteriaBook criteriaBook = new CriteriaBook(50,54,3);
		Page page = bookDao.getPage(criteriaBook);
		System.out.println(page);
		System.out.println(page.isHasNext());
		System.out.println(page.isHasPrev());
		System.out.println(page.getPrevNo());
		System.out.println(page.getNextNo());
		System.out.println(page.getPageCount());
	}

}
