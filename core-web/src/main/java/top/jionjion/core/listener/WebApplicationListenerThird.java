package top.jionjion.core.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;

/**
 * 自定义实现监听器, 在 CoreWebApplication 中注册
 * 泛型指定其感兴趣的事件
 *
 * @author Jion
 */
@Order(3)
@SuppressWarnings("unused")
public class WebApplicationListenerThird implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(@NonNull ApplicationStartedEvent event) {
        System.out.println("--- 方法三 ---");
        System.out.println("监听器: Spring 准备启动.." + event.hashCode());
        System.out.println("------");
    }
}
