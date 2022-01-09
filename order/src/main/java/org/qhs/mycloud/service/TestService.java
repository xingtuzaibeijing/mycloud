package org.qhs.mycloud.service;

import org.springframework.transaction.annotation.Transactional;

public interface TestService {
	@Transactional()
	void doTest();
}
