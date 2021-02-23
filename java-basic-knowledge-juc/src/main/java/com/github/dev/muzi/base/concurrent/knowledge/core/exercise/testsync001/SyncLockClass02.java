package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testsync001;

/**
 * 声明在代码块上
 * 使用synchronized 对类对象进行加锁
 */
public class SyncLockClass02 {

    private static int count = 0;

    /**
     * 执行method01需要先获得类对象的锁
     * SyncLockClass01.class
     */
    public  static void method01(){
        synchronized (SyncLockClass01.class){
            count --;
            System.out.println(Thread.currentThread().getName() + " : count=" + count);
        }
    }

}
