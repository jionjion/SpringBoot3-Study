package top.jionjion.redis.lock;

/**
 * 自定义实现锁.
 *
 * @author Jion
 */
public interface ILock {

    /**
     *  尝试获取锁,非阻塞获取.限定超时时间
     * @param timeoutSec 过期时间, 超过时间后自动释放, 单位秒
     * @return 是否获取锁, {true} 获取成功
     */
    boolean tryLock(long timeoutSec);

    /**
     * 释放锁
     */
    void unlock();
}
