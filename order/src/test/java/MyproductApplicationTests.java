
import org.junit.Test;
import org.junit.runner.RunWith;
import org.qhs.mycloud.OrderApplication;
import org.qhs.mycloud.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author QianHuaSheng
 * @version V1.0
 * @description
 * @date 2021/12/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyproductApplicationTests {

	@Autowired
	private TestService testService;

	@Test
//	@Transactional
	public void doTest(){
		doPrivateTest();
	}

	private void doPrivateTest(){
		testService.doTest();
	}
}
