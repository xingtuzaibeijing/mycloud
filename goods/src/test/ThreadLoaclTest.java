package org.qhs.mycloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author QianHuaSheng
 * @version V1.0
 * @description ThreadLocal测试类
 * @date 2022/1/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoodsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ThreadLoaclTest {

	ThreadLocal<String> threadLocal = new ThreadLocal();

	ThreadLocal<String> threadLocal1 = new ThreadLocal();

	@Test
	public void testThreadLocal() {
		//thread 用于存储自己线程的不受其他线程的影响的变量 每一个thread有个 ThreadLocalMap 每当你去定一个一个 ThreadLocal 他会把threadLoacl作为key值  value就是具体数值
		//因为ThreadLocal的key用的是弱引用 强引用是说在垃圾回收的时候不会回收 弱引用是指垃圾回收时会回收掉 导致key成了null 而value还在
		//解决办法 使用完ThreadLocal调用下remove方法

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 2000, 3L, TimeUnit.MILLISECONDS
				, new LinkedBlockingQueue<>(2000));
		Set<Integer> integers=new HashSet<>();
		CountDownLatch countDownLatch=new CountDownLatch(200);
		for(int i=0,len=200;i<len;i++){
			int finalI = i;
			threadPoolExecutor.submit(() -> {
				threadLocal.set(String.valueOf(finalI));
				threadLocal1.set(String.valueOf(finalI));
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (integers){
					integers.add(Integer.parseInt(threadLocal.get()));
				}
				countDownLatch.countDown();
			});
		}
		try {
			countDownLatch.await();
			System.out.println("===================================="+integers.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
