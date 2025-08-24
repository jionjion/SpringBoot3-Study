package top.jionjion.data.neo4j.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * 英雄
 *
 * @author Jion
 */
@Node("Hero")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hero {

    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 英雄名称
     */
    private String name;

    /**
     * 英雄别名
     */
    private String alias;

    /**
     * 所在区域
     */
    private String region;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 阵营
     */
    private String faction;

    /**
     * 角色
     */
    private String role;

    // 关系：HAS_BOND, 外向关系
    @Relationship(type = "HAS_BOND", direction = Relationship.Direction.OUTGOING)
    private Set<Bond> bonds = new HashSet<>();
}