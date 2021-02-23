package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testsync001;

/**
 * 锁关键字声明在代码块上
 * 使用synchronized对对象自身加锁
 */
public class SyncLockThis01 {

    private int count = 0;

    public void method01(){
        /**
         * 想执行计数减减操作需要先获得对象本身的锁
         */
        synchronized (this){
            count --;
            System.out.println(Thread.currentThread().getName() + " : count=" + count);
        }
    }
}
