package top.jionjion.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Jion
 */
@Slf4j
@SpringBootTest
class SimpleRedisLockTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 模拟耗时的任务方法..
     */
    void businessMethod() {
        ILock lock = new SimpleRedisLock("Lock-A", stringRedisTemplate);

        // 加锁, 执行锁逻辑
        try {
            // 自旋, 尝试获取锁后执行..
            //noinspection StatementWithEmptyBody
            while (!lock.tryLock(10)) {}

            log.info("线程: {}, 获得锁:{}", Thread.currentThread().getName(), lock);


            // 一些业务逻辑, 3秒
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.error("分布式业务发生异常....", e);
        } finally {
            // 释放锁
            log.info("线程: {}, 释放锁:{}", Thread.currentThread().getName(), lock);
            lock.unlock();
        }
    }

    @Test
    void testBusinessMethod() throws InterruptedException {
        // 线程征用问题
        Thread threadA = new Thread(this::businessMethod, "Thread-A");
        Thread threadB = new Thread(this::businessMethod, "Thread-B");

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();
    }

    @Test
    void tryLock() throws InterruptedException {
        // 尝试获取锁, 释放锁
        ILock lock = new SimpleRedisLock("Lock-A", stringRedisTemplate);
        log.info("获得锁: {}", lock.tryLock(1));
        log.info("获得锁: {}", lock.tryLock(1));
        lock.unlock();
        log.info("获得锁: {}", lock.tryLock(1));
        Thread.sleep(1000);
        log.info("获得锁: {}", lock.tryLock(1));
        log.info("获得锁: {}", lock.tryLock(1));
    }
}