package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testsync001;

/**
 * 使用synchronized对某个对象加锁
 */
public class SyncLockObject {

    private int count = 0;

    private Object lock = new Object();

    public void method01(){

        /**
         * 想执行计数减减操作需要先获得锁
         */
        synchronized (lock){
            count --;
            System.out.println(Thread.currentThread().getName() + " : count=" + count);
        }
    }
}
