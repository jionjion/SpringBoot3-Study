package top.jionjion.core.conditional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Jion
 */
@SpringBootTest
class ActivePropertiesTest implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 测试, 是否条件注入
     */
    @Test
    void test() {
        ActiveProperties bean = applicationContext.getBean(ActiveProperties.class);
        System.out.println(bean);
        assertNotNull(bean);
    }

}