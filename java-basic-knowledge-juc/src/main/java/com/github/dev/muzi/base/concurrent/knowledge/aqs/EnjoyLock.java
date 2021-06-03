package com.github.dev.muzi.base.concurrent.knowledge.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lifuyi8
 * @since 2021/5/17 2:37 下午
 */
public class EnjoyLock implements Lock {

    EnjoySync enjoySync = new EnjoySync();

    /**
     * 阻塞式加锁
     */
    @Override
    public void lock() {
        enjoySync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return enjoySync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        enjoySync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        EnjoyLock lock = new EnjoyLock();


        new Thread(() -> {
            lock.lock();
            System.out.println("213123213");
            lock.unlock();
        }, "t1").start();
        lock.lock();
        System.out.println("main sleep begin");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("main sleep end");
        lock.unlock();
    }
}
