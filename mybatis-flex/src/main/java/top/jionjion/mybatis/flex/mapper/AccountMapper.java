package top.jionjion.mybatis.flex.mapper;

import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.jionjion.mybatis.flex.entity.Account;

/**
 * 账号处理
 *
 * @author Jion
 */
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 自定义查询, 注解驱动
     *
     * @param id 主键
     * @return 查询结果
     */
    @Select("select * from tb_account where id = #{id,jdbcType=BIGINT}")
    Account selectById(@Param("id") Long id);

    /**
     * 试用 xlm 查询
     *
     * @param userName 用户名
     * @return 查询结果
     */
    Account selectByUserName(@Param("userName") String userName);
}
