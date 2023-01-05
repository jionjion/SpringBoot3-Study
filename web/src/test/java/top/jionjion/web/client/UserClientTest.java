package top.jionjion.web.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.jionjion.web.bean.User;
import top.jionjion.web.dto.ResultMessage;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * 测试代理接口请求, 可以在主程序启动后,再次单独执行测试用例
 *
 * @author Jion
 */
@SpringBootTest
class UserClientTest {

    final Logger logger = Logger.getLogger(UserClientTest.class.getName());

    @Autowired
    UserClient userClient;

    @Test
    void userListWebflux() {
        Flux<User> list = userClient.userListWebflux();
        Stream<User> userStream = list.toStream();
        userStream.forEach(user -> logger.info(() -> "userList: " + user));
    }

    @Test
    void userList() {
        List<User> list = userClient.userList();
        list.forEach(user -> logger.info(() -> "userList: " + user));
    }

    @Test
    void userSaveWebflux() {
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        user.setPassword("password");
        user.setAddress("ShangHai");
        user.setBirthday(new Date());

        Mono<ResponseEntity<ResultMessage<User>>> responseEntityMono = userClient.userSaveWebflux(user);

        responseEntityMono.subscribe(responseEntity -> {
            // 响应状态
            HttpStatusCode statusCode = responseEntity.getStatusCode();
            logger.info(() -> "statusCode: " + statusCode);
            // 响应内容
            ResultMessage<User> body = responseEntity.getBody();
            logger.info(() -> "responseBody: " + body);
        });
    }

    @Test
    void userSaveWeb() {
        User user = new User();
        user.setId(4);
        user.setUsername("username");
        user.setPassword("password");
        user.setAddress("ShangHai");
        user.setBirthday(new Date());

        ResponseEntity<ResultMessage<User>> responseEntity = userClient.userSave(user);
        // 响应状态
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        logger.info(() -> "statusCode: " + statusCode);
        // 响应内容
        ResultMessage<User> body = responseEntity.getBody();
        logger.info(() -> "responseBody: " + body);
    }

    @Test
    void userGet() {
        User user = userClient.userGet(1L);
        logger.info(() -> "user: " + user);
    }

    @Test
    void userUpdate() {
        ResponseEntity<User> responseEntity = userClient.userUpdate(1L, "username", "password");
        // 响应状态
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        logger.info(() -> "statusCode: " + statusCode);
        // 响应内容
        User body = responseEntity.getBody();
        logger.info(() -> "responseBody: " + body);
    }

    @Test
    void userDelete() {
        ResponseEntity<Void> responseEntity = userClient.userDelete(1L);
        // 响应状态
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        logger.info(() -> "statusCode: " + statusCode);
    }

    @Test
    void userGetByUsername() {
        List<User> userList = userClient.userGetByUsername("jion");
        logger.info(() -> "userList: " + userList);
    }

    @Test
    void uploadPhoto() {
        Assertions.fail("暂时不支持....");

        User user = new User();
        user.setId(1);
        user.setUsername("username");
        user.setPassword("password");
        user.setAddress("ShangHai");
        user.setBirthday(new Date());
        File file = new File("W:\\SpringBoot3-Study\\web\\src\\test\\resources\\static\\jion.jpg");
        ResponseEntity<Object> responseEntity = userClient.uploadPhoto(user, file, null);
        logger.info(() -> "statusCode: " + responseEntity.getStatusCode());
        Object body = responseEntity.getBody();
        logger.info(() -> "responseBody: " + body);
    }

    @Test
    void errorCodeHandler() {
        ResponseEntity<Object> responseEntity = userClient.errorCodeHandler("1");
        logger.info(() -> "statusCode: " + responseEntity.getStatusCode());
        Object body = responseEntity.getBody();
        logger.info(() -> "responseBody: " + body);
    }
}