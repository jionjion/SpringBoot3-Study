package top.jionjion.data.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用 Neo4j 存储数据
 *
 * @author Jion
 */
@SpringBootApplication
public class DataNeo4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataNeo4jApplication.class, args);
    }

}
