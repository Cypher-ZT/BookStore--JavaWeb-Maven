import com.cypher.bookstore.token.TokenProducer;
import org.junit.Test;

/**
 * @author Cypher-Z
 * @date 2018/8/16 - 3:25
 */
public class OtherTest {
	@Test
	public void tokenTest() {
		TokenProducer producer = TokenProducer.getInstance();
		System.out.println(producer.makeToken());
	}
}
