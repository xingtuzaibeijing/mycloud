package org.qhs.mycloud.service.impl;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author QianHuaSheng
 * @version V1.0
 * @description
 * @date 2022/2/12
 */
public class ProxyCglibServiceimpl implements MethodInterceptor {

	private Object target;

	public ProxyCglibServiceimpl (Object target){
		this.target=target;
	}
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("判断用户是否有权限进行操作");
		Object obj = method.invoke(target);
		System.out.println("记录用户执行操作的用户信息、更改内容和时间等");
		return obj;
	}
}
