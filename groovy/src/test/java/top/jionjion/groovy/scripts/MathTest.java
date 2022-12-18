package top.jionjion.groovy.scripts;

import groovy.lang.GroovyShell;
import groovy.lang.Script;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试 脚本
 *
 * @author Jion
 */
@Slf4j
@SpringBootTest
class MathTest {

    @Test
    void test() throws IOException {
        // 类路径下的资源,在打包后读取jar包资源 classpath:...
        ClassPathResource classPathResource = new ClassPathResource("scripts/Math.groovy");

        // 创建GroovyShell
        GroovyShell groovyShell = new GroovyShell();

        //装载解析脚本代码
        Script script = groovyShell.parse(classPathResource.getFile());

        // 脚本 - 1
        Object result1 = script.invokeMethod("add", new Object[]{1, 2});
        log.info ("结果: " + result1);

        // 脚本 - 2
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("科目1", "语文");
        paramMap.put("科目2", "数学");
        Object[] params2 = new Object[]{paramMap};
        String result2 = (String) script.invokeMethod("mapToString", params2);
        log.info ("结果: " + result2);
    }
}
