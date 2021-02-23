package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.reentranlock;


import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentranLock 的公平锁和非公平锁
 *
 * 因为底层的同步器有队列，可以保存线程顺序信息。
 */
public class ReentranLock04 {

    public static void main(String[] args) {

        // true为公平锁  false为非公平锁
        ReentrantLock lock = new ReentrantLock(true);


        Thread thread1 = new Thread(() -> {
            int count = 0 ;
            while (count<5) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " run");
                    ThreadUtils.seconds(1L);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                count++;
            }
        }, "Thread-01");

        thread1.start();

        Thread thread2 = new Thread(() -> {
            while (true){
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " run");
                    ThreadUtils.seconds(1L);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "Thread-02");

        thread2.start();

        Thread thread3 = new Thread(() -> {
            while (true){
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " run");
                    ThreadUtils.seconds(1L);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "Thread-03");

        thread3.start();

        ThreadUtils.seconds(20L);

    }
}
