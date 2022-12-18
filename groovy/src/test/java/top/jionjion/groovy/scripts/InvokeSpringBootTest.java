package top.jionjion.groovy.scripts;

import groovy.lang.GroovyShell;
import groovy.lang.Script;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * 测试调用 SpringBoot 的 groovy 脚本
 *
 * @author Jion
 */
@Slf4j
@SpringBootTest
class InvokeSpringBootTest {

    @Test
    void test() throws IOException {
        // 类路径下的资源,在打包后读取jar包资源 classpath:...
        ClassPathResource classPathResource = new ClassPathResource("scripts/InvokeSpringBoot.groovy");

        // 创建GroovyShell
        GroovyShell groovyShell = new GroovyShell();

        //装载解析脚本代码
        Script script = groovyShell.parse(classPathResource.getFile());

        //执行
        Object thing = script.invokeMethod("doSomething", null);
        log.info ("结果: " + thing);
    }
}
