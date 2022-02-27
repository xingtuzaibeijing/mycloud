package org.qhs.mycloud.service.impl;

import org.qhs.mycloud.service.AdminService;

/**
 * @author QianHuaSheng
 * @version V1.0
 * @description
 * @date 2022/2/12
 */
public class AdminServiceImpl implements AdminService {


	@Override
	public void update() {
		System.out.println("修改管理系统数据");
	}


	@Override
	public Object find() {
		System.out.println("查看管理系统数据");
		return new Object();
	}
}
