package top.jionjion.core.ioc.annotation.factory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *  测试 FactoryBean<T> 注入Bean
 * @author Jion
 */
@SpringBootTest
class CatFactoryBeanTest {

    @Autowired
    @Qualifier("cat") // 指定具体Bean
    private AbstractAnimal cat;

    @Test
    void test(){
        System.out.println("获得Bean:" + cat);
    }
}