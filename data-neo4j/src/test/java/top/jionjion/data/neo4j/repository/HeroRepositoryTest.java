package top.jionjion.data.neo4j.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.jionjion.data.neo4j.entity.Bond;
import top.jionjion.data.neo4j.entity.Hero;

import java.util.List;

/**
 * 测试
 *
 * @author Jion
 */
@SpringBootTest
class HeroRepositoryTest {

    @Autowired
    HeroRepository heroRepository;


    @Test
    void findByName() {
        heroRepository.findByName("张飞").ifPresent(System.out::println);
    }

    @Test
    void findByFaction() {
        List<Hero> heroList = heroRepository.findByFaction("蜀");
        heroList.forEach(System.out::println);
    }

    @Test
    void findOathBrothers() {
        List<Hero> heroList = heroRepository.findOathBrothers("刘备");
        heroList.forEach(System.out::println);
    }

    @Test
    void findBondBetween() {
        List<Bond> bondList = heroRepository.findBondBetween("刘备", "张飞");
        bondList.forEach(System.out::println);
    }
}