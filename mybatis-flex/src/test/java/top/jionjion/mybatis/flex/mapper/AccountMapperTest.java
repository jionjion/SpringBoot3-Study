package top.jionjion.mybatis.flex.mapper;

import com.mybatisflex.core.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.jionjion.mybatis.flex.entity.Account;

import java.util.List;

/**
 * 测试账号
 *
 * @author Jion
 */
@SpringBootTest
class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;


    @Test
    void test() {
        QueryWrapper queryWrapper = QueryWrapper.create().select();
        List<Account> accountList = accountMapper.selectListByQuery(queryWrapper);
        System.out.println(accountList);
    }
}