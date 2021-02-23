package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create by muzi  2019-06-26
 * 对比atomic和synchronized的性能
 */
public class Atomic02 {
    //同一时间并行的线程数量
    private final static Integer CURRENT_NUMBER = 20;

    //门栓及初始累加器定义
    private AtomicInteger countAtomic = new AtomicInteger(0);
    private int countSync = 0;

    //使用atomic
    private void add(){
        for (int i = 0; i < 10000 ; i++) {
            countAtomic.incrementAndGet();
        }
    }

    //使用synchronized
    private synchronized void addSync(){
        for (int i = 0; i < 10000 ; i++) {
            countSync++;
        }
    }

    public static void main(String[] args) {
        Atomic02 task = new Atomic02();
        List<Thread> threadList = new ArrayList<>();


        for (int i = 0; i < Atomic02.CURRENT_NUMBER; i++) {
            threadList.add(new Thread(task::add,"Thread-"+i));
        }
        testAtomic(task,threadList);


        threadList.clear();
        for (int i = 0; i < Atomic02.CURRENT_NUMBER; i++) {
            threadList.add(new Thread(task::addSync,"Thread-"+i));
        }
        testSync(task,threadList);
    }


    private static void testAtomic( Atomic02 task, List<Thread> threadList){
        long time = Atomin01.process(threadList);
        System.out.println("Atomic耗时："+ time + "ms     累加结果：" +task.countAtomic.get());
    }


    private static void testSync( Atomic02 task, List<Thread> threadList){
        long time = Atomin01.process(threadList);
        System.out.println("Synchronized同步耗时："+ time + "ms     累加结果：" +task.countSync);
    }
}
