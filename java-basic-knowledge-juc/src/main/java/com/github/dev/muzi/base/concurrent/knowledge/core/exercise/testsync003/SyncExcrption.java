package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testsync003;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 出现异常 锁会被释放
 */
public class SyncExcrption {
    int count = 0;

    synchronized void m (){
        System.out.println(Thread.currentThread().getName() + " start");
        while(true){
            count++;

            System.out.println(Thread.currentThread().getName() + " count=" + count);

            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            if (count == 5){
                int i = 1/0;
            }
        }
    }

    /**
     * thread1 不抛出异常 thread2 永远都不会执行
     */
    public static void main(String[] args) {
        SyncExcrption syncExcrption = new SyncExcrption();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                syncExcrption.m();
            }
        };

        new Thread(r,"thread1").start();


        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }

        new Thread(r,"thread2").start();

    }


}
