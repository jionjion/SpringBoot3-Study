package top.jionjion.logback.thread;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试子线程保存
 *
 * @author Jion
 */
class MdcRunnableTest {
    private static final String KEY = "requestId";

    @Test
    void testRun() {

        Logger logger = LoggerFactory.getLogger(MdcRunnableTest.class);

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 入口传入请求ID
        MDC.put(KEY, UUID.randomUUID().toString());

        // 主线程打印
        logger.debug("主线程日志 {}", MDC.get(KEY));

        // 异步线程打印, 用MDCRunnable装饰Runnable
        new Thread(new MdcRunnable(() -> logger.debug("使用异步线程 - 异步 {}", MDC.get(KEY)))).start();

        // 异步线程池打印日志，用MDCRunnable装饰Runnable
        executorService.execute(new MdcRunnable(() -> logger.debug("使用异步线程 - 异步线程池 {}", MDC.get(KEY))));
        executorService.shutdown();

        // 出口移除请求ID
        MDC.remove(KEY);
    }
}