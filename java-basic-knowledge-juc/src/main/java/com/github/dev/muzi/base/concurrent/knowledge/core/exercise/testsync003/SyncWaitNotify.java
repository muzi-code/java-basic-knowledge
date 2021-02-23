package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testsync003;


import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

/**ji'ra
 * wait 释放锁进行等待
 * notify 唤醒其他线程，但不释放锁
 */
public class SyncWaitNotify {

    volatile List<Object> list = new ArrayList<>();

    void add(Object o){
        list.add(o);
    }

    public static void main(String[] args) {
        SyncWaitNotify syncWaitNotify = new SyncWaitNotify();
        new Thread(()->{
            synchronized (syncWaitNotify){
                System.out.println("t2 启动");

                if (syncWaitNotify.list.size() != 5){
                    try{
                        syncWaitNotify.wait();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println("t2 结束");
                }
            }
        },"Thread-02").start();

        ThreadUtils.seconds(2L);

        new Thread(()->{
            System.out.println("t1 启动");
            synchronized (syncWaitNotify){
                for (int i = 0; i < 10 ; i++) {
                    syncWaitNotify.add(new Object());

                    System.out.println("add : " + i);

                    if (syncWaitNotify.list.size() == 5){
                        syncWaitNotify.notify();
                    }

                    ThreadUtils.seconds(1L);
                }
            }
            System.out.println("t1 结束");
        },"Thread-01").start();
    }
}
