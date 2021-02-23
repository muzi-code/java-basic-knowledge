package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testsync001;

/**
 * 声明在成员方法上
 * 使用synchronized对对象自身加锁
 */
public class SyncLockThis02 {

    private int count = 0;

    public synchronized void method01() {
        /**
         * 想执行计数减减操作需要先获得对象本身的锁
         */
        count--;
        System.out.println(Thread.currentThread().getName() + " : count=" + count);
    }
}
