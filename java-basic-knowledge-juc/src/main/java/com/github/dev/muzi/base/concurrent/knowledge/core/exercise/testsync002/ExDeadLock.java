package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testsync002;

/**
 * 死锁 ， A 先拿1 再拿 2 ， B 先拿 2 再拿 1
 */
public class ExDeadLock {

    private Object lock01 = new Object();

    private Object lock02 = new Object();


    public void get01(){
        synchronized (lock01){
            System.out.println(Thread.currentThread().getName() + " lock01 !");
            synchronized (lock02){
                System.out.println(Thread.currentThread().getName() + " lock02 !");
            }
        }
    }

    public void get02(){
        synchronized (lock02){
            System.out.println(Thread.currentThread().getName() + " lock02! ");
            synchronized (lock01){
                System.out.println(Thread.currentThread().getName() + " lock01 !");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExDeadLock deadLock = new ExDeadLock();
        new Thread(()->{
            while (true){
                deadLock.get01();
            }
        },"Thread-01").start();

        new Thread(()->{
            while (true){
                deadLock.get02();
            }
        },"Thread-02").start();


    }
}
