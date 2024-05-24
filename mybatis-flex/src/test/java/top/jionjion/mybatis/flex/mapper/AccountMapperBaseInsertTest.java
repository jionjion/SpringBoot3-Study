package top.jionjion.mybatis.flex.mapper;

import com.mybatisflex.core.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import top.jionjion.mybatis.flex.entity.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试基础的插入语句
 *
 * @author Jion
 */
@Rollback
@Transactional
@SpringBootTest
class AccountMapperBaseInsertTest {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 插入实体类数据, 不忽略 null 值
     */
    @Test
    void insert() {
        Account account = new Account();
        account.setUserName("张三");
        account.setAge(18);
        accountMapper.insert(account);
    }

    /**
     * 插入实体类数据, 但是忽略 null 的数据, 只对有值的内容进行插入.
     * 这样的好处是数据库已经配置了一些默认值, 这些默认值才会生效.
     */
    @Test
    void insertSelective() {
        Account account = new Account();
        account.setUserName("张三");
        account.setAge(18);
        accountMapper.insertSelective(account);
    }

    /**
     * 插入实体类数据
     */
    @Test
    void insertWithIgnoreNull() {
        Account account = new Account();
        account.setUserName("张三");
        account.setAge(18);
        // insert 的默认实现
        accountMapper.insert(account, false);
        // insertSelective 的默认实现
        account.setId(null);
        accountMapper.insert(account, true);
    }

    /**
     * 插入带有主键的实体类, 不忽略 null 值
     */
    @Test
    void insertWithPk() {
        Account account = new Account();
        account.setId(101L);
        account.setUserName("张三");
        account.setAge(18);
        accountMapper.insertWithPk(account);
    }

    /**
     * 插入带有主键的实体类, 忽略 null 值.
     */
    @Test
    void insertSelectiveWithPk() {
        Account account = new Account();
        account.setId(102L);
        account.setUserName("张三");
        account.setAge(18);
        accountMapper.insertSelectiveWithPk(account);
    }

    /**
     * 带有主键的插入, 此时实体类不会经过主键生成器生成主键
     */
    @Test
    void insertWithPkWithIgnoreNull() {
        Account account = new Account();
        account.setId(103L);
        account.setUserName("张三");
        account.setAge(18);
        // insertWithPk 的默认实现
        accountMapper.insertWithPk(account, false);
        // insertSelectiveWithPk 的默认实现
        account.setId(104L);
        accountMapper.insertWithPk(account, true);
    }

    /**
     * 批量插入实体类数据, 只会根据第一条数据来构建插入的字段内容
     */
    @Test
    void insertBatch() {
        List<Account> list = new ArrayList<>();
        list.add(new Account("张三"));
        list.add(new Account("李四"));
        list.add(new Account("王五"));
        accountMapper.insertBatch(list);
    }

    /**
     * 批量插入实体类数据, 按 size 切分, 一个批次插入N条
     */
    @Test
    void insertBatchWithBatchSize() {
        List<Account> list = new ArrayList<>();
        list.add(new Account("张三"));
        list.add(new Account("李四"));
        list.add(new Account("王五"));
        accountMapper.insertBatch(list, 2);
    }

    /**
     * 插入或者更新, 若主键有值, 则更新, 若没有主键值, 则插入, 插入或者更新都不会忽略 null 值
     */
    @Test
    void insertOrUpdate() {
        Account account = new Account(1L, "张三");
        accountMapper.insertOrUpdate(account);
    }

    /**
     * 插入或者更新, 若主键有值, 则更新, 若没有主键值, 则插入, 插入或者更新都会忽略 null 值.
     */
    @Test
    void insertOrUpdateSelective() {
        Account account = new Account(1L, "张三");
        accountMapper.insertOrUpdateSelective(account);
    }

    /**
     * 插入或者更新, 若主键有值, 则更新, 若没有主键值, 则插入.
     */
    @Test
    void insertOrUpdateWithIgnoreNull() {
        Account account = new Account();
        account.setUserName("张三");
        account.setAge(18);
        // insertOrUpdate 的默认实现
        accountMapper.insertOrUpdate(account, false);
        // insertOrUpdateSelective 的默认实现
        accountMapper.insertOrUpdate(account, true);
    }


    /**
     * 使用数据库原生方式
     */
    @Test
    void insertUpdateWrapper() {
        Account account = new Account();
        account.setUserName("张三");

        Account newAccount = UpdateWrapper.of(account)
                .setRaw("birthday", "now()")
//                .setRaw(ACCOUNT.BIRTHDAY, "now()")
//                .setRaw(Account::getBirthday, "now()")
                .toEntity();

        accountMapper.insert(newAccount);
    }

    /**
     *  测试使用子查询等数据库对象操作完成
     */
    @Test
    void testInsertWithRaw() {
        Account account = new Account();
        account.setUserName("张三");

        Account newAccount = UpdateWrapper.of(account)
                .setRaw(Account::getBirthday, "(select max(birthday) from account)")
                .toEntity();

        accountMapper.insert(newAccount);
    }
}
