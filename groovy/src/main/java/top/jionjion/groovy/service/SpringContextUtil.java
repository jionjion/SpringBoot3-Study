package top.jionjion.groovy.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Spring 上下文
 *
 * @author Jion
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private static final SpringContextUtil INSTANCE = new SpringContextUtil();

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        INSTANCE.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return INSTANCE.applicationContext;
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz 类
     * @param <T>   类
     * @return 类的实例
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
}
