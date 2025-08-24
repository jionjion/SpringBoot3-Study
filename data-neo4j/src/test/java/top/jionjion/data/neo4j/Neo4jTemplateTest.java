package top.jionjion.data.neo4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jClient;

import java.util.Collection;
import java.util.Map;

/**
 * @author Jion
 */
@SpringBootTest
class Neo4jTemplateTest {

    @Autowired
    private Neo4jClient neo4jClient;

    @Test
    void test() {
        // 查询 关系 和 目标节点
        String cypher = "MATCH (a:Hero {name: '刘备'})-[r:HAS_BOND]-(b:Hero {name: '张飞'}) RETURN r AS bond, b AS other";
        Collection<Map> result = neo4jClient.query(cypher)
                .fetchAs(Map.class)
                .mappedBy((typeSystem, record) -> record.asMap()) // 转成 Map
                .all();

        System.out.println(result);
    }
}
