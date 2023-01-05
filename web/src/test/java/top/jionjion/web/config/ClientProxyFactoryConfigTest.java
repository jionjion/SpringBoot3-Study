package top.jionjion.web.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.jionjion.web.client.UserClient;

/**
 * 测试请求接口代理工厂
 *
 * @author Jion
 */
@SpringBootTest
class ClientProxyFactoryConfigTest {

    @Autowired
    UserClient userClient;

    @Test
    void userClient() {
        Assertions.assertNotNull(userClient);
    }
}