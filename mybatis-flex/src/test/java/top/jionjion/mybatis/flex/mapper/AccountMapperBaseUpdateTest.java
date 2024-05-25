package top.jionjion.mybatis.flex.mapper;

import com.mybatisflex.core.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import top.jionjion.mybatis.flex.entity.Account;
import top.jionjion.mybatis.flex.entity.table.AccountTableDef;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * 测试基础的更新语句
 *
 * @author Jion
 */
@Rollback
@Transactional
@SpringBootTest
class AccountMapperBaseUpdateTest {

    @Autowired
    AccountMapper accountMapper;


    /**
     * 根据主键来更新数据, 若实体类属性数据为 null, 该属性不会更新到数据库.
     */
    @Test
    void update() {
        Account account = new Account();
        account.setId(1L);
        account.setUserName("张四");
        account.setAge(18);
        account.setBirthday(LocalDate.of(1999, 1, 1));
        accountMapper.update(account);
    }

    /**
     * 根据主键来更新数据到数据库.
     * 并指定空值处理
     */
    @Test
    void updateIgnoreNulls() {
        Account account = new Account();
        account.setId(1L);
        account.setUserName("张四");
        // 不忽略null, 这样为空的属性会更新到数据库中
        accountMapper.update(account, false);
    }

    /**
     * 根据 Map 构建的条件来更新数据.
     */
    @Test
    void updateByMap() {
        Account account = new Account();
        account.setUserName("张四");
        java.util.Map<String, Object> condition = new HashMap<>();
        condition.put("id", 1L);
        accountMapper.updateByMap(account, condition);
    }

    /**
     * 根据 Map 构建的条件来更新数据.
     * 并指定空值处理
     */
    @Test
    void updateByMapIgnoreNulls() {
        Account account = new Account();
        account.setUserName("张四");
        java.util.Map<String, Object> condition = new HashMap<>();
        condition.put("id", 1L);
        // 不忽略null, 这样为空的属性会更新到数据库中
        accountMapper.updateByMap(account, false, condition);
    }

    /**
     * 根据查询条件来更新数据
     */
    @Test
    void updateByCondition() {
        Account account = new Account();
        account.setUserName("张四");
        accountMapper.updateByCondition(account, AccountTableDef.ACCOUNT.ID.eq(1));
    }

    /**
     * 根据查询条件来更新数据
     * 并指定空值处理
     */
    @Test
    void updateByConditionIgnoreNulls() {
        Account account = new Account();
        account.setUserName("张四");
        // 不忽略null, 这样为空的属性会更新到数据库中
        accountMapper.updateByCondition(account, false, AccountTableDef.ACCOUNT.ID.eq(1));
    }

    /**
     * 根据查询条件来更新数据
     */
    @Test
    void updateByQuery() {
        Account account = new Account();
        account.setUserName("张四");
        QueryWrapper queryWrapper = QueryWrapper.create().where(AccountTableDef.ACCOUNT.ID.eq(1));
        accountMapper.updateByQuery(account, queryWrapper);
    }

    /**
     * 根据查询条件来更新数据
     * 并指定空值处理
     */
    @Test
    void updateByQueryIgnoreNulls() {
        Account account = new Account();
        account.setUserName("张四");
        QueryWrapper queryWrapper = QueryWrapper.create().where(AccountTableDef.ACCOUNT.ID.eq(1));
        accountMapper.updateByQuery(account, false, queryWrapper);
    }
}
