package top.jionjion.core.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 每个线程单独一份实例
 *
 * @author Jion
 */
@Service
@Scope("threadLocalScope")
public class StudentThreadService {

    StudentThreadService() {
        System.out.println(Thread.currentThread().getName() + "初始化当前类 UserThreadService : " + this);
    }
}
