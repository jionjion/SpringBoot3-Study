package top.jionjion.mybatis.flex.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.time.LocalDate;

/**
 * 账号类
 *
 * @author Jion
 */
@Table("tb_account")
public class Account {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private LocalDate birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}