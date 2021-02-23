package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testsync002;

/**
 * 需要锁的方法被调用，并不影响其他无需加锁的方法调用。
 */
public class ExSyncOnlyOneMethod {

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
    }

    /**
     * 方法2 需要加锁
     */
    private void m2(){
        try {
            System.out.println(Thread.currentThread().getName() + " m2 run start");
            System.out.println(Thread.currentThread().getName() + " m2  sync range before ");
            synchronized (this){
                System.out.println(Thread.currentThread().getName() + " m2  sync ... ... ");
                Thread.sleep(2000);
            }
            System.out.println(Thread.currentThread().getName() + " m2  sync range after ");
            System.out.println(Thread.currentThread().getName() + " m2 run end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法3 不需要加锁
     */
    private void m3(){
        try {
            System.out.println(Thread.currentThread().getName() + " m3 run start");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " m3 run end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExSyncOnlyOneMethod method = new ExSyncOnlyOneMethod();

        //lambda
        new Thread(()-> method.m1(),"Thread-01").start();
        new Thread(()-> method.m2(),"Thread-02").start();
        new Thread(()-> method.m3(),"Thread-03").start();

        // easy code
//        new Thread(method::m1,"Thread-01").start();
//        new Thread(method::m2,"Thread-02").start();
//        new Thread(method::m3,"Thread-03").start();
    }
}
