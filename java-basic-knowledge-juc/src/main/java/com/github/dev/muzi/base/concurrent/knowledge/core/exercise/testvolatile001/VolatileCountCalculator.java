package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testvolatile001;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile 只改变了内存的可见性，对业务执行并没有影响，并未改变原子性。
 *
 * 三者没必要比谁快用途不一样
 * synchronized  atomic  synchronized
 *
 * volatile 一写多读场景
 */
public class VolatileCountCalculator {
    volatile int count = 0;

    /*synchronized*/ void m(){
        for (int i = 0; i <  10000000 ; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        VolatileCountCalculator calculator = new VolatileCountCalculator();

        List<Thread> threads  = new ArrayList<>();

        for (int i = 0; i < 10000 ; i++) {
            threads.add(new Thread(calculator::m,"Thread-" + i));
        }

        for (Thread thread: threads) {
            thread.start();
        }

        threads.forEach((thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));


        System.out.println(calculator.count + "  " + ( System.currentTimeMillis() - begin));
    }
}
