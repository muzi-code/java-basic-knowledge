package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.jdkcontainer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class T02_CopyOnWriteList {

    public static void main(String[] args) {

        // 写时复制，创建一个新的数组复制原来的素组，然后把原来的数组替换掉
        List<String> lists = new CopyOnWriteArrayList<>();


        Random random = new Random();
        Thread[] ths = new Thread[100];

        CountDownLatch latch = new CountDownLatch(ths.length);

        long start = System.currentTimeMillis();

        for (int i = 0; i < ths.length ; i++) {
            ths[i] = new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    String name = Thread.currentThread().getName();
                    lists.add(name + ":" + random.nextInt(10000));
                }
                latch.countDown();
            },"thread-"+i);
        }

        Arrays.asList(ths).forEach(t->t.start());

        try {
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis() - start);

    }
}
