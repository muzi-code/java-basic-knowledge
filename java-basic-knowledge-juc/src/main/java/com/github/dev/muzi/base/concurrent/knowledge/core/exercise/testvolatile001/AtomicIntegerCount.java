package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testvolatile001;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomXXXX类型的类的方法都是原子操作
 */
public class AtomicIntegerCount {
    AtomicInteger count = new AtomicInteger(0);

    void m(){
        for (int i = 0; i <  100000 ; i++) {

            // 非原子,执行结果超过1000
//            if (count.get() < 1000){
//                // 方法原子不代表使用时也是原子的
//                count.incrementAndGet();//count++
//            }

            // 原子
            count.incrementAndGet();//count++
        }
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        AtomicIntegerCount calculator = new AtomicIntegerCount();

        List<Thread> threads  = new ArrayList<>();

        for (int i = 0; i < 1000 ; i++) {
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
