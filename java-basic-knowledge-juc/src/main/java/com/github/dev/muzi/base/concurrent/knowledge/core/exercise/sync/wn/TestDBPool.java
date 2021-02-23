package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.sync.wn;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create by muzi 2019-06-29
 * 测试数据库连接池
 */
public class TestDBPool {

    static DBPool pool = new DBPool(10);

    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 50;

        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger(0);
        AtomicInteger notGot = new AtomicInteger(0);

        for (int i = 0; i < threadCount ; i++) {
            Thread thread = new Thread(new Worker(count,got,notGot),"worker_"+i);
            thread.start();
        }

        end.await();
        System.out.println("总共尝试了：" +(threadCount * count) + "次");
        System.out.println("获取链接成功："+got.get()+ "次");
        System.out.println("获取链接失败："+notGot.get() + "次");
    }


    public static class Worker implements Runnable{
        int count ;
        AtomicInteger got;
        AtomicInteger notGot;

        public Worker(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            while (count>0){
                try {
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null){
                        try {
                            //执行业务时间
                            Thread.sleep(50);
                        }finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else {
                        notGot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName() + " 等待超时！");
                    }
                }catch (InterruptedException e){

                }finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
