package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.threadpool;

import java.util.concurrent.*;

public class T05_ThreadPool {


    /*
     * fixed ThreadPool就是固定多少个线程，从线程池的创建到销毁固定就是这些线程
     */
    private static void testFixedThreadPool(){
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 6 ; i++) {
            service.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);


        service.shutdown();

        // 是不是任务都执行完了
        System.out.println(service.isTerminated());

        // 是不是关了，表示要关了，但是可以有正在执行的任务
        System.out.println(service.isShutdown());

        System.out.println(service);


        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);
    }

    private static void testCacheThreadPool(){
        ExecutorService service = new ThreadPoolExecutor(4, 6,
                60L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2));
        for (int i = 0; i < 4 ; i++) {
            service.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        // 核心线程都在执行任务
        System.out.println(service);

        for (int i = 0; i < 2 ; i++) {
            service.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        // 队列装两个
        System.out.println(service);

        for (int i = 0; i < 2 ; i++) {
            service.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        // 继续加入创建其他线程
        System.out.println(service);
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            e.printStackTrace();
        }

        // 其他线程执行完后超过生存时间销毁
        System.out.println(service);

        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    public static void main(String[] args) {
        //testFixedThreadPool();
        //testCacheThreadPool();
    }
}