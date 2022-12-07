package top.jionjion.elasticsearch.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

/**
 * 测试 ElasticsearchOperations 工具类
 *
 * @author Jion
 */
@SpringBootTest
class ElasticsearchOperationsTest {

    @Autowired
    ElasticsearchOperations operations;

    @Test
    void test() {
        Assertions.assertNotNull(operations);
    }
}
