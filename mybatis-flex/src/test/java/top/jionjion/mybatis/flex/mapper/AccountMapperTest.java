package top.jionjion.mybatis.flex.mapper;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.jionjion.mybatis.flex.entity.Account;

import java.util.HashMap;
import java.util.Map;

import static top.jionjion.mybatis.flex.entity.table.AccountTableDef.ACCOUNT;

/**
 * 测试账号
 *
 * @author Jion
 */
@SpringBootTest
class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;


    /**
     * 测试框架
     */
    @Test
    void test() {
        QueryWrapper queryWrapper = QueryWrapper.create().select().where(ACCOUNT.ID.eq(2));
        Account account = accountMapper.selectOneByQuery(queryWrapper);
        System.out.println(account);
    }

    /**
     * 测试注解驱动的sql
     */
    @Test
    void selectById() {
        Account account = accountMapper.selectById(1L);
        System.out.println(account);
    }

    /**
     * 测试xml的sql
     */
    @Test
    void selectByUserName() {
        Account account = accountMapper.selectByUserName("张三");
        System.out.println(account);
    }

    /**
     * 测试xml的sql,并使用分页效果
     */
    @Test
    void selectByUserNameWithPage() {
        Account account = accountMapper.selectByUserName("张三");
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(Account::getAge).eq(18)
                .and(Account::getId).ge(0);

        // 可选其他参数
        Map<String, Object> otherParams = new HashMap<>();
        otherParams.put("userName", "张三");

        Page<Account> accountPage = accountMapper.xmlPaginate("top.jionjion.mybatis.flex.mapper.AccountMapper.selectByUserName",
                Page.of(1, 10), queryWrapper, otherParams);
        System.out.println(accountPage);
    }
}