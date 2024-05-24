package top.jionjion.mybatis.flex.mapper;

import com.mybatisflex.core.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import top.jionjion.mybatis.flex.entity.Account;
import top.jionjion.mybatis.flex.entity.table.AccountTableDef;

import java.util.*;

/**
 * 测试基础的删除语句
 *
 * @author Jion
 */
@Rollback
@Transactional
@SpringBootTest
class AccountMapperBaseDeleteTest {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 根据主键删除数据。如果是多个主键的情况下，需要传入数组，例如：new Integer[]{100,101}。
     */
    @Test
    void deleteById() {
        Long id = 100L;
        accountMapper.deleteById(id);
    }

    /**
     * 根据实体主键来删除数据。
     */
    @Test
    void delete() {
        Account account = new Account(100L, "张三");
        accountMapper.delete(account);
    }

    /**
     * 根据多个主键批量删除数据。
     */
    @Test
    void deleteBatchByIds() {
        Set<Long> ids = new HashSet<>();
        ids.add(1L);
        ids.add(2L);
        accountMapper.deleteBatchByIds(ids);
    }

    /**
     * 根据多个主键批量删除数据。
     */
    @Test
    void deleteBatchByIdsWithBatchSize() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        ids.add(4L);
        accountMapper.deleteBatchByIds(ids, 3);
    }

    /**
     * 根据 Map构建的条件来删除数据。
     */
    @Test
    void deleteByMap() {
        Map<String, Object> condition = new HashMap<>();
        condition.put("id", 1L);
        accountMapper.deleteByMap(condition);
    }

    /**
     * 根据查询条件来删除数据。
     */
    @Test
    void deleteByCondition() {
        accountMapper.deleteByCondition(AccountTableDef.ACCOUNT.ID.ge(100));
    }

    /**
     * 根据查询条件来删除数据。
     */
    @Test
    void deleteByQuery() {
        QueryWrapper queryWrapper = QueryWrapper.create().where(AccountTableDef.ACCOUNT.ID.ge(10));
        accountMapper.deleteByQuery(queryWrapper);
    }
}
