package top.jionjion.elasticsearch.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import top.jionjion.elasticsearch.pojo.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 测试
 *
 * @author Jion
 */
@Slf4j
@SpringBootTest
class BookRepositoryTest {

    /**
     * Elasticsearch 操作工具类
     */
    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    /**
     * Food 增删改查
     */
    @Autowired
    BookRepository foodRepository;


    /**
     * 插入与更新
     */
    @Test
    void testCreate() {
        Food food1 = new Food(1, "大米", "食品", 100D);
        Object save = this.foodRepository.save(food1);
        log.info("保存一个: {}", save);

        List<Food> list = new ArrayList<>();
        list.add(new Food(2, "小米", "食品", 200D));
        list.add(new Food(3, "黄豆", "食品", 300D));

        // 接收对象集合，实现批量新增
        Iterable<Food> foods = foodRepository.saveAll(list);
        log.info("保存全部: {}", foods);
    }

    /**
     * 删除
     */
    @Test
    void testDelete() {
        Food food = new Food(1, "大米", "食品", 100D);
        this.foodRepository.delete(food);

    }


    /**
     * 查询
     */
    @Test
    void testFind() {
        System.out.println("-----主键查询------");
        Optional<Food> byId = this.foodRepository.findById(1L);
        System.out.println(byId);

        System.out.println("-----查询全部------");
        Iterable<Food> all = this.foodRepository.findAll();
        all.forEach(System.out::println);

        System.out.println("-----排序查询(升序降序)------");
        Iterable<Food> price = this.foodRepository.findAll(Sort.by("price").descending());
        price.forEach(System.out::println);
    }

    /**
     * 调用自定义方法
     */
    @Test
    void testFindByU() {
        List<Food> phone = this.foodRepository.findByTitle("食品");
        phone.forEach(System.out::println);

        List<Food> byPriceBetween = this.foodRepository.findByPriceBetween(100.0, 300.0);
        byPriceBetween.forEach(System.out::println);
    }

    /**
     * 批量插入
     */
    @Test
    void indexList() {
        List<Food> list = new ArrayList<>();

        list.add(new Food(1, "大米", "食品", 100D));
        list.add(new Food(2, "小米", "食品", 200D));
        list.add(new Food(3, "黄豆", "食品", 300D));
        // 接收对象集合，实现批量新增
        foodRepository.saveAll(list);
    }

    /**
     * 高级查询
     */
    @Test
    void testSearch() {
        //通过查询构建器工具构建--重点：QueryBuilders：词条、模糊、范围
        NativeQuery nativeSearchQuery = NativeQuery.builder().withQuery(query -> query.match(match -> match.field("title").query("手机"))).build();

        //获取结果集
        SearchHits<Food> Foods = elasticsearchOperations.search(nativeSearchQuery, Food.class);

        Foods.forEach(System.out::println);

    }

    /**
     * 重点--自定义查询
     */
    @Test
    void testNative() {
        // 构建自定义查询构建器
        NativeQuery nativeSearchQuery = NativeQuery.builder().withQuery(query -> query.match(match -> match.field("title").query("手机"))).build();
        // 添加基本查询条件
        // 查询分页结果集
        SearchHits<Food> foodSearchHits = elasticsearchOperations.search(nativeSearchQuery, Food.class);
        System.out.println(foodSearchHits.getTotalHits());
        foodSearchHits.forEach(System.out::println);

    }

    /**
     * 重点--分页查询
     */
    @Test
    void testPage() {
        // 构建自定义查询构建器, 添加基本查询条件
        NativeQuery nativeSearchQuery = NativeQuery.builder().withQuery(query -> query.match(match -> match.field("title").query("手机")))
                .withPageable(PageRequest.of(1, 2))
                .build();
        // 查询分页结果集
        SearchHits<Food> foodSearchHits = this.elasticsearchOperations.search(nativeSearchQuery, Food.class);
        System.out.println(foodSearchHits.getTotalHits());
        foodSearchHits.forEach(System.out::println);

    }

    /**
     * 重点--排序
     */
    @Test
    void testSort() {
        // 构建自定义查询构建器
        NativeQueryBuilder queryBuilder = NativeQuery.builder();
        // 添加基本查询条件
        queryBuilder.withQuery(query -> query.match(match -> match.field("title").query("手机")));
        // 排序
        queryBuilder.withSort(Sort.by(Sort.Direction.DESC, "price"));
        // 查询分页结果集
        queryBuilder.withPageable(PageRequest.of(1, 2));
        SearchHits<Food> foodSearchHits = this.elasticsearchOperations.search(queryBuilder.build(), Food.class);
        System.out.println(foodSearchHits.getTotalHits());
        foodSearchHits.forEach(System.out::println);
    }
}