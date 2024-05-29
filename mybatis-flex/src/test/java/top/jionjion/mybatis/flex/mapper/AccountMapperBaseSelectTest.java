package top.jionjion.mybatis.flex.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试基础的更新语句
 *
 * @author Jion
 */
@Rollback
@Transactional
@SpringBootTest
class AccountMapperBaseSelectTest {

    @Autowired
    AccountMapper accountMapper;


}
