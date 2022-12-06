package top.jionjion.core.ioc.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *  测试 @Component 注入Bean
 * @author Jion
 */
@SpringBootTest
class DogHouseTest {

    @Autowired
    DogHouse dogHouse;

    @Test
    void test(){
        System.out.println("获得Bean:" + dogHouse);
    }
}