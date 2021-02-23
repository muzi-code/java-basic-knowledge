package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.reentranlock;


import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentranLock 的线程可以被中断
 */
public class ReentranLock03 {


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();


        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " start");
                ThreadUtils.seconds(10L);
                System.out.println(Thread.currentThread().getName() + " end");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "Thread-01");

        thread1.start();

        Thread thread2 = new Thread(() -> {
            boolean interrupt = false;
            try {
                // 使用Interruptibly可以被打断
                lock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + " start");
                ThreadUtils.seconds(10L);
                System.out.println(Thread.currentThread().getName() + " end");
            } catch (InterruptedException e) {
                System.out.println("thread2 interrupt!");
                e.printStackTrace();
                interrupt = true;
            } finally {
                if (!interrupt)
                    lock.unlock();
            }
        }, "Thread-02");

        thread2.start();

        ThreadUtils.seconds(5L);

        thread2.interrupt();
    }
}
