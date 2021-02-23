package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testsync002;

/**
 * 验证synchronized是可重入的锁。
 */
public class ExSyncmCallSyncm {

    /**
     * 方法1 需要加锁
     */
    private synchronized void m1(){
        try {
            System.out.println(Thread.currentThread().getName() + " m1 run start");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " m1 run end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // call m2
        m2();
    }

    /**
     * 方法2 需要加锁
     */
    private synchronized void m2(){
        try {
            System.out.println(Thread.currentThread().getName() + " m2 run start");
            synchronized (this){
                System.out.println(Thread.currentThread().getName() + " m2 add sync ... ... ");
                Thread.sleep(2000);
            }
            System.out.println(Thread.currentThread().getName() + " m2 run end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExSyncmCallSyncm method = new ExSyncmCallSyncm();

        //lambda
        new Thread(()-> {
            method.m1();
        },"Thread-01").start();
        new Thread(()-> method.m2(),"Thread-02").start();

        // easy code
//        new Thread(method::m1,"Thread-01").start();
//        new Thread(method::m2,"Thread-02").start();
//        new Thread(method::m3,"Thread-03").start();
    }
}
