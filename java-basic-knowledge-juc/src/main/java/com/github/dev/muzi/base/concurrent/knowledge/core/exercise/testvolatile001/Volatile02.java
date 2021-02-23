package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testvolatile001;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * create by muzi  2019-06-26
 * volatile 只保证了内存可见性，保证不了程序的原子性。
 */
public class Volatile02 {

    public static CountDownLatch latch = new CountDownLatch(10);
    volatile int count = 0;
    /*synchronized*/ void add(){
        for (int i = 0; i < 10000 ; i++) {
            count++;
        }
        latch.countDown();
    }

    public static void main(String[] args) {
        Volatile02 task = new Volatile02();

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            threadList.add(new Thread(task::add,"Thread-"+i));
        }
        for (Thread thread : threadList) {
            thread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(task.count);
    }
}
