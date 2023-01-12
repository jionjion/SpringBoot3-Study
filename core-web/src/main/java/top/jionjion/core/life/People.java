package top.jionjion.core.life;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Bean的生命周期
 *
 * @author Jion
 */
@Data
@Component
@SuppressWarnings("unused")
public class People implements InitializingBean, DisposableBean {

    private String name;

    private LocalDate birthday = LocalDate.now();

    @Value("${spring.datasource.url}")
    String datasourceUrl;

    /**
     * 优先执行,初始化方法
     * 已经 new 创建对象后执行, 且已经对 new 出的对象进行过属性赋值..
     *
     * @throws Exception 如果发生了异常
     */
    @PostConstruct
    public void init() throws Exception {
        System.out.println("People 初始化, 通过 @PostConstruct 注解" + this);
    }

    /**
     * 其次执行, 初始化方法
     * 此时, Bean 通过getBean() 方法已经创建过后, 且已经对 new 出的对象进行过属性赋值..
     *
     * @throws Exception 如果发生了异常
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("People 初始化, 通过 InitializingBean 接口: " + this);
    }

    /**
     * 销毁方法, 容器关闭时执行
     *
     * @throws Exception 如果发生了异常
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("People 已经, 通过 DisposableBean 接口: " + this);
    }
}
