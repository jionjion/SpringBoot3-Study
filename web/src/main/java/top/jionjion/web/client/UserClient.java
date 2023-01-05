package top.jionjion.web.client;

import jakarta.servlet.http.Cookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.jionjion.web.bean.User;
import top.jionjion.web.dto.ResultMessage;

import java.io.File;
import java.util.List;

/**
 * 使用 SpringBoot3 提供的方式执行网络请求, 获得返回结果
 *
 * @author Jion
 */
@HttpExchange(url = "/user", accept = "application/json", contentType = "application/json")
public interface UserClient {

    /**
     * 查询全部, 流查询
     *
     * @return 全部
     */
    @GetExchange("/users")
    Flux<User> userListWebflux();

    /**
     * 查询全部
     *
     * @return 全部
     */
    @GetExchange("/users")
    List<User> userList();

    /**
     * 新增一个, 流新增
     *
     * @param user 用户
     * @return 新增后
     */
    @PostExchange("/user")
    Mono<ResponseEntity<ResultMessage<User>>> userSaveWebflux(@RequestBody User user);

    /**
     * 新增一个
     *
     * @param user 用户
     * @return 新增后
     */
    @PostExchange("/user")
    ResponseEntity<ResultMessage<User>> userSave(@RequestBody User user);

    /**
     * 查询一个
     *
     * @param id 用户ID
     * @return 用户
     */
    @GetExchange("/users/{id}")
    User userGet(@PathVariable("id") Long id);

    /**
     * 更新一个
     *
     * @param id       用户ID
     * @param username 用户姓名
     * @param password 用户密码
     * @return 更新结果
     */
    @PutExchange("/users/{id}")
    ResponseEntity<User> userUpdate(@PathVariable Long id, @RequestParam String username, @RequestParam String password);

    /**
     * 删除一个
     *
     * @param id 用户ID
     * @return 删除结果
     */
    @DeleteExchange("/users/{id}")
    ResponseEntity<Void> userDelete(@PathVariable Long id);

    /**
     * 自定义查询,通过姓名查询用户
     *
     * @param username 用户姓名
     * @return 查询结果
     */
    @GetExchange("/users/username/{username}")
    List<User> userGetByUsername(@PathVariable("username") String username);

    /**
     * 表单请求
     *
     * @param user   用户信息
     * @param file   文件
     * @param cookie 可以为空, 接口无需提供
     * @return 表单提交结果
     */
    @PostExchange("/users/photo")
    ResponseEntity<Object> uploadPhoto(@RequestPart User user,
                                       @RequestPart File file,
                                       @CookieValue(required = false) Cookie cookie);

    /**
     * 错误内容
     *
     * @param code 异常Code
     * @return 异常内容
     */
    @GetExchange("/error/{code}")
    ResponseEntity<Object> errorCodeHandler(@PathVariable("code") String code);
}
