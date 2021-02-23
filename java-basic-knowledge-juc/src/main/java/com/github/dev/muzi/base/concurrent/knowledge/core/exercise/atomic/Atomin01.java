package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create by muzi  2019-06-26
 * 使用atomic进行累加，保证累加操作的原子性。
 * 底层是compareAndSwap 在UnSafe类中对其包装成compareAndSet等一系列方法。
 */
public class Atomin01 {

    private final static Integer CURRENT_NUMBER = 20;
    private AtomicInteger countAtomic = new AtomicInteger(0);

    //使用atomic
    private void add(){
        for (int i = 0; i < 10000 ; i++) {
            //if (count.get() < 1000)  //此处加上判断会有线程不安全的问题
                countAtomic.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        testAtomic();
    }

    private static void testAtomic(){
        Atomin01 task = new Atomin01();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < Atomin01.CURRENT_NUMBER ; i++) {
            threadList.add(new Thread(task::add,"Thread-"+i));
        }

        long time = process(threadList);
        System.out.println("Atomic耗时："+ time + "ms   累加结果：" +task.countAtomic.get());
    }


    static long process(List<Thread> threadList){
        long begin = System.currentTimeMillis();
        threadList.forEach(Thread::start);
        threadList.forEach((thread)->{
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        return  end - begin ;
    }

}
