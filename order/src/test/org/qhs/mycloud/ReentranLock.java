package org.qhs.mycloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author QianHuaSheng
 * @version V1.0
 * @description
 * @date 2022/2/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReentranLock {

	ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

	ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

	ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

	private CountDownLatch countDownLatch = new CountDownLatch(20);

	ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 2000, 1000, TimeUnit.SECONDS
			, new LinkedBlockingDeque<>(10000));

	@Test
	public void testReentrantReadWrite() {
		Long startTime = System.currentTimeMillis();
		for (int i = 0, len = 18; i < len; i++) {
			threadPoolExecutor.submit(() -> testLock(readLock));
		}
		for (int i = 0, len = 2; i < len; i++) {
			threadPoolExecutor.submit(() -> testLock(writeLock));
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis() - startTime + "总耗时");
	}

	@Test
	public void testWrite() {

		CountDownLatch countDownLatch = new CountDownLatch(2);
		//写要10秒
		threadPoolExecutor.execute(() -> {
			try {
				Long startTime = System.currentTimeMillis();
				readLock.lock();
				Thread.sleep(5000);
				System.out.println(System.currentTimeMillis() - startTime + "读耗时");
				countDownLatch.countDown();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				readLock.unlock();
			}
		});

		//写要10秒
		threadPoolExecutor.execute(() -> {
			try {
				Thread.sleep(500);
				Long startTime = System.currentTimeMillis();
				readLock.lock();
				System.out.println(System.currentTimeMillis() - startTime + "写耗时");
				countDownLatch.countDown();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				readLock.unlock();
			}
		});
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void testLock(Lock lock) {
		try {
			Long startTime = System.currentTimeMillis();
			lock.lock();
			System.out.println("进来了");
			Thread.sleep(1000);
			countDownLatch.countDown();
			System.out.println(System.currentTimeMillis() - startTime + "lock的类型" + lock.getClass());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}


}
