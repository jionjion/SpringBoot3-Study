package top.jionjion.core.starter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 自定义场景器测试
 *
 * @author Jion
 */
@SpringBootTest
class UseWeatherServiceTest {

    @Autowired
    UseWeatherService useWeatherService;

    @Test
    void getType() {
        String type = useWeatherService.getType();
        System.out.println("天气状态>> " + type);
    }
}