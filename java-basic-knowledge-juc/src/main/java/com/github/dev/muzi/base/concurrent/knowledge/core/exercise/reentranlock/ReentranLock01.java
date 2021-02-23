package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.reentranlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Reentranlock 可以替代 synchronized
 *
 * 手工锁需要释放，所以解锁的代码一般写在finally里面。
 *
 * ReentranLock也是可重入的。
 */
public class ReentranLock01 {

    ReentrantLock lock = new ReentrantLock();

    /**
     * 方法1 需要加锁
     */
    private void m1(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " m1 run start");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " m1 run end");

            m2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 方法2 需要加锁
     */
    private void m2(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " m2 run start");
            synchronized (this){
                System.out.println(Thread.currentThread().getName() + " m2 add sync ... ... ");
                Thread.sleep(2000);
            }
            System.out.println(Thread.currentThread().getName() + " m2 run end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentranLock01 method = new ReentranLock01();

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
