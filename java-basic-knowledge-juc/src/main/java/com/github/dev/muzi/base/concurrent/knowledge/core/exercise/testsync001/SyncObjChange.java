package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testsync001;


import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;

/**
 * 在使用锁的时候不要随便更改/替换锁引用指向的对象
 */
public class SyncObjChange {


    Object lock = new Object();

    void method(){
        synchronized (lock){
            while(true){
                System.out.println(Thread.currentThread().getName() + " : run");
                ThreadUtils.seconds(1L);
            }
        }
    }

    public static void main(String[] args) {
        SyncObjChange syncObjChange = new SyncObjChange();

        new Thread(syncObjChange::method,"Thread-1").start();


        ThreadUtils.seconds(3L);
        System.out.println("锁对象切换");
        syncObjChange.lock = new Object();
        new Thread(syncObjChange::method,"Thread-2").start();

        ThreadUtils.seconds(3L);

    }
}
