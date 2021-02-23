package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.sync;

import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;

/**
 * create by muzi  2019-06-27
 * 在程序设计中，若使用锁自定义对象的方式，则自定义对象最好定义为final。
 * 确保锁对象不会被更改，导致线程不安全。
 */
public class Sync09 {

    /*final*/ Object lock =  new Object();
    void running(){
        synchronized(lock){
            for (;;){
                System.out.println(Thread.currentThread().getName() + " is running .");
                ThreadUtils.seconds(1);
            }
        }
    }

    public static void main(String[] args) {
        Sync09 task = new Sync09();
        new Thread(task::running,"Thread-01").start();

        ThreadUtils.seconds(3);

        Thread thread2 = new Thread(task::running,"Thread-02");
        //更换锁对象
        task.lock = new Object();
        thread2.start();
    }
}
