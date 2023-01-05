package top.jionjion.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import top.jionjion.web.client.UserClient;

/**
 * 网络请求代理工厂配置
 *
 * @author Jion
 */
@Configuration
public class ClientProxyFactoryConfig {

    @Bean
    UserClient userClient() {
        // 请求地址
        WebClient client = WebClient.builder().baseUrl("http://127.0.0.1:8080/").build();
        // 代理工厂
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        // 绑定请求地址
        return factory.createClient(UserClient.class);
    }
}
