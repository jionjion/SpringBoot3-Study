package top.jionjion.data.neo4j.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

/**
 * 英雄间关系
 *
 * @author Jion
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RelationshipProperties
public class Bond {

    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 关系类型, 对应 Cypher 中的 type
     */
    private String type;

    /**
     * 信息
     */
    private String info;

    /**
     * 对方
     */
    @TargetNode
    private Hero other;
}