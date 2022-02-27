package org.qhs.mycloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author QianHuaSheng
 * @version V1.0
 * @description
 * @date 2022/2/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LongaddTest {

	@Test
	public void longAdd(){
		LongAdder longAdder=new LongAdder();
		longAdder.add(1L);
		longAdder.add(2L);
	}
}
