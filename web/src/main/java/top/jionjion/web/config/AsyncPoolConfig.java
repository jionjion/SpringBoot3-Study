package top.jionjion.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 异步线程配置
 *
 * @author Jion
 */
@EnableAsync
@Configuration
public class AsyncPoolConfig {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.initialize();
        // 设置最大线程数
        executor.setCorePoolSize(4);
        // 设置队列容量
        executor.setMaxPoolSize(32);
        // 设置线程活跃时间（秒）
        executor.setQueueCapacity(512);
        // 设置默认线程名称
        executor.setKeepAliveSeconds(60);
        // 设置拒绝策略
        executor.setThreadNamePrefix("Async-Thread-Pool-");
        // 等待所有任务结束后再关闭线程池
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }
}
