package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.threadlocal;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * create by muzi 2019-06-29
 *
 * ThreadLocal使用不得当会引发内存泄露的问题。
 */
public class ThreadLocalOOM {


    private static final int TASK_LOOP_SIZE = 500;
    static final ThreadPoolExecutor pool =
            new ThreadPoolExecutor(5,5,1, TimeUnit.MINUTES,new LinkedBlockingQueue<>());
    static class LocalVariable{
        private byte[] a = new byte[1024*1024*5];
    }
    static final ThreadLocal<LocalVariable> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException{
        for (int i = 0; i < TASK_LOOP_SIZE ; i++) {
            pool.execute(()->{
                threadLocal.set(new LocalVariable());
                System.out.println("use localVariable");
                //threadLocal.remove();  //使用完ThreadLocal如果不及时remove，内存占用率会不断增加，导致内存泄露。
            });
            Thread.sleep(100);
        }
        System.out.println("pool end");
    }


}
