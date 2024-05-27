package top.jionjion.mybatis.flex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis-flex 试用
 *
 * @author Jion
 */
@MapperScan("top.jionjion.mybatis.flex.mapper")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}