package top.jionjion.mybatis.flex.mapper;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.core.update.UpdateWrapper;
import com.mybatisflex.core.util.UpdateEntity;
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


    /**
     * 只更新部分字段
     */
    @Test
    void updateWithPartColumn() {
        //Account account = UpdateEntity.of(Account.class);
        //account.setId(1L);
        // 等价写法
        Account account = UpdateEntity.of(Account.class, 1);

        // 更新名字为空, 年龄为10
        account.setUserName(null);
        account.setAge(10);

        accountMapper.update(account);
    }

    /**
     * 更新部分字段,并使用数据库函数
     */
    @Test
    void updateWithPartColumnAndFunction() {
        Account account = UpdateEntity.of(Account.class);
        account.setId(1L);

        // 姓名更新为空
        account.setUserName(null);

        // 通过 UpdateWrapper 操作 account 数据, 增强更新操作
        UpdateWrapper<Account> wrapper = UpdateWrapper.of(account);
        // 使用数据库函数 set age = age+1
        wrapper.setRaw("age", "age + 1");
        // set age = age + 1
        wrapper.setRaw(AccountTableDef.ACCOUNT.AGE, AccountTableDef.ACCOUNT.AGE.add(1));
        // set age = (select max(age) from account)
        wrapper.setRaw(AccountTableDef.ACCOUNT.AGE, "(select max(age) from account)");
        // 执行更新
        accountMapper.update(account);
    }

    /**
     * 使用更新链式操作
     */
    @Test
    void updateChain() {
        UpdateChain.of(Account.class)
                // set 指定字段
                .set(Account::getUserName, "张三")
                // setRaw 指定字段并参与 SQL 拼接, 有注入的风险
                .setRaw(Account::getAge, "age + 1")
                .setRaw(Account::getBirthday, "(select max(birthday) from account)")
                .where(Account::getId).eq(1)
                .update();
    }
}
