package top.jionjion.core.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * 线程级别的 Scope, 配置后每个线程单独获得一个 Bean 实例
 *
 * @author Jion
 */
public class ThreadLocalScope implements Scope {

    /**
     * 存入线程中...
     */
    private static final ThreadLocal<Object> THREAD_LOCAL_SCOPE = new ThreadLocal<>();

    /**
     * 每个线程一个, 如果没有, 从Bean工厂中获取一个
     *
     * @param name          要检索的对象的名称
     * @param objectFactory Spring 的对象工厂, 从其中获得对象
     * @return 获得的对象
     */
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object value = THREAD_LOCAL_SCOPE.get();
        if (value != null) {
            return value;
        }

        Object object = objectFactory.getObject();
        THREAD_LOCAL_SCOPE.set(object);
        return object;
    }

    /**
     * 线程结束删除对象
     *
     * @param name 对象
     * @return 删除后内容
     */
    @Override
    public Object remove(String name) {
        THREAD_LOCAL_SCOPE.remove();
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}