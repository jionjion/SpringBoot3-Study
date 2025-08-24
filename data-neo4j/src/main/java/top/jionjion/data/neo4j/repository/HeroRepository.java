package top.jionjion.data.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import top.jionjion.data.neo4j.entity.Bond;
import top.jionjion.data.neo4j.entity.Hero;

import java.util.List;
import java.util.Optional;

/**
 * 存储 Hero 仓库
 *
 * @author Jion
 */
@Repository
public interface HeroRepository extends Neo4jRepository<Hero, Long> {

    /**
     * 根据名字查找英雄
     *
     * @param name 英雄名字
     * @return 英雄
     */
    Optional<Hero> findByName(String name);

    /**
     * 查找某阵营的所有英雄
     *
     * @param faction 阵营
     * @return 英雄
     */
    List<Hero> findByFaction(String faction);

    /**
     * 自定义 Cypher 查询：查找某人的所有“结拜兄弟”
     *
     * @param name 名字
     * @return 英雄
     */
    @Query("MATCH (h:Hero {name: $name})-[:HAS_BOND {info: '结拜兄弟'}]->(b) RETURN b")
    List<Hero> findOathBrothers(String name);

    // 查找两人之间的关系
    // @Query("MATCH (a:Hero {name: $name1})-[r:HAS_BOND]-(b:Hero {name: $name2}) RETURN r {.*, other: b}")
    @Query("MATCH (a:Hero {name: $name1})-[r:HAS_BOND]-(b:Hero {name: $name2}) RETURN r {  id: id(r),  info: r.info, other: b { id: id(b), name: b.name, faction: b.faction} }")
    List<Bond> findBondBetween(String name1, String name2);

}