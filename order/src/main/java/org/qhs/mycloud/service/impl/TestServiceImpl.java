package org.qhs.mycloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.qhs.mycloud.entity.Test;
import org.qhs.mycloud.mapper.TestMapper;
import org.qhs.mycloud.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author QianHuaSheng
 * @version V1.0
 * @description
 * @date 2021/12/30
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

	@Autowired
	private TestMapper testMapper;

	@Override
//	@Transactional()
	public void doTest(){
		List<Test> testList = Arrays.asList(Test.builder().no(1L).name("1").build(), Test.builder().no(2L).name("1").build(), Test.builder().no(2L).name("1").build());
		this.saveBatch(testList);
	}

}
