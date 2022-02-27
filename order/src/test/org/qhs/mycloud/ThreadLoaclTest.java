package org.qhs.mycloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.qhs.mycloud.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author QianHuaSheng
 * @version V1.0
 * @description ThreadLocal测试类
 * @date 2022/1/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ThreadLoaclTest {

	@Autowired
	private ProxyService proxyService;

	@Test
	public void testThreadLocal() {
		proxyService.testProxy();
	}

	@Test
	public void testCglibProxy() {
		proxyService.testCglibProxy();
	}

}
