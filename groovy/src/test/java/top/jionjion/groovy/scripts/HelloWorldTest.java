package top.jionjion.groovy.scripts;

import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * 测试
 *
 * @author Jion
 */
@SpringBootTest
class HelloWorldTest {

    @Test
    void HelloWorld() throws IOException {
        // 类路径下的资源,在打包后读取jar包资源 classpath:...
        ClassPathResource classPathResource = new ClassPathResource("scripts/HelloWorld.groovy");

        // 创建GroovyShell
        GroovyShell groovyShell = new GroovyShell();

        //装载解析脚本代码
        Script script = groovyShell.parse(classPathResource.getFile());

        //执行
        script.invokeMethod("HelloWorld", null);
    }
}
