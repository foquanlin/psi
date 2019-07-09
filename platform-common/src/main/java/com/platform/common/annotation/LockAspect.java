package com.platform.common.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步锁 AOP
 *
 * @author 李鹏军
 */
@Component
@Scope
@Aspect
@Order(1)
public class LockAspect {
    /**
     * 不用synchronized service 默认是单例的，并发下lock只有一个实例
     * <p>
     * 互斥锁 参数默认false，不公平锁
     */
    private static Lock lock = new ReentrantLock(true);

    /**
     * Service层切点
     */
    @Pointcut("@annotation(com.platform.common.annotation.ServiceLock)")
    public void lockAspect() {

    }

    @Around("lockAspect()")
    public Object around(ProceedingJoinPoint joinPoint) {
        lock.lock();
        Object obj;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
        return obj;
    }
}
