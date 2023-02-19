package top.jionjion.logback.thread;

import org.slf4j.MDC;

import java.util.Map;

/**
 * 使用装饰器模式, 对线程工具类进行优化..可以在当前子线程中, 打印日志线程变量
 *
 * @author Jion
 */
public class MdcRunnable implements Runnable {

    private final Runnable runnable;

    private final Map<String, String> map;

    public MdcRunnable(Runnable runnable) {
        this.runnable = runnable;
        // 保存当前线程的 MDC 值
        this.map = MDC.getCopyOfContextMap();
    }

    @Override
    public void run() {
        // 传入已保存的MDC值
        for (Map.Entry<String, String> entry : map.entrySet()) {
            MDC.put(entry.getKey(), entry.getValue());
        }
        // 装饰器模式，执行run方法
        runnable.run();
        // 移除已保存的MDC值
        for (Map.Entry<String, String> entry : map.entrySet()) {
            MDC.remove(entry.getKey());
        }
    }
}
