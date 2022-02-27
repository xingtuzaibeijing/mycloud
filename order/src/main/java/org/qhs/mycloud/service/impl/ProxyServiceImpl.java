package org.qhs.mycloud.service.impl;

import org.qhs.mycloud.service.AdminService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.stereotype.Service;

import java.lang.reflect.Proxy;

/**
 * @author QianHuaSheng
 * @version V1.0
 * @description
 * @date 2022/2/12
 */
@Service
public class ProxyServiceImpl implements org.qhs.mycloud.service.ProxyService {

	@Override
	public void testProxy() {
		final AdminServiceImpl testService = new AdminServiceImpl();
		AdminService o = (AdminService)Proxy.newProxyInstance(testService.getClass().getClassLoader()
				, testService.getClass().getInterfaces(), (proxy, method, args) -> {
					System.out.println("日志加强");
					Object obj = method.invoke(testService, args);
					System.out.println("事务加强");
					return obj;
				});
		o.find();
	}


	@Override
	public void testCglibProxy() {
		final AdminServiceImpl testService = new AdminServiceImpl();
		Enhancer en=new Enhancer();
		en.setSuperclass(testService.getClass());
		ProxyCglibServiceimpl proxyCglibServiceimpl = new ProxyCglibServiceimpl(testService);
		en.setCallback(proxyCglibServiceimpl);
		AdminService o = (AdminService)en.create();
		o.update();
	}
}
