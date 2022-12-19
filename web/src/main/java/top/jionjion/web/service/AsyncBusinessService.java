package top.jionjion.web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步线程处理业务逻辑
 *
 * @author Jion
 */
@Slf4j
@Service
public class AsyncBusinessService {

    @Async
    public void doSomeThing(String thing) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("异步处理发生异常: {}" + e.getMessage());
        }
        log.info("异步处理中, 当前线程: {}, 处理任务: {}", Thread.currentThread().getName(), thing);
    }
}
