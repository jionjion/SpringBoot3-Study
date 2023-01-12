package top.jionjion.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.test.context.SpringBootTest;
import top.jionjion.core.service.StudentService;

/**
 * 测试线程级别的作用范围
 *
 * @author Jion
 */
@SpringBootTest
class StudentThreadServiceTest implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Test
    void test() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                StudentThreadService threadService = beanFactory.getBean(StudentThreadService.class);
                System.out.println("获得线程独有Bean " + threadService);
                StudentService service = beanFactory.getBean(StudentService.class);
                System.out.println("获得单实例Bean " + service);
            });
            thread.start();
            thread.join();
        }
    }
}