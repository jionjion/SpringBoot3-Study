package top.jionjion.groovy.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * groovy 脚本
 *
 * @author Jion
 */
@Slf4j
@Service
public class GroovyLogicService {

    public String doSomething() {
        log.info("这是在 Java 中调用执行一些事情..");
        return "Some Things";
    }
}
