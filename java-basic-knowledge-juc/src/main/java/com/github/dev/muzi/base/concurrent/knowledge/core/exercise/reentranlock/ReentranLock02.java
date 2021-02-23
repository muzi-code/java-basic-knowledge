package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.reentranlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Reentranlock 可以进行尝试拿锁,并返回一个布尔变量。
 */
public class ReentranLock02 {

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
        boolean locked = false;
        try {
            locked = lock.tryLock(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            if (locked){
                System.out.println(Thread.currentThread().getName() + " m2 run start");
                synchronized (this){
                    System.out.println(Thread.currentThread().getName() + " m2 add sync ... ... ");
                    Thread.sleep(2000);
                }
                System.out.println(Thread.currentThread().getName() + " m2 run end");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked){
                lock.unlock();
            }
        }
        if (!locked){
            System.out.println("m2 is not lock");
        }
    }

    public static void main(String[] args) {
        ReentranLock02 method = new ReentranLock02();

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
