package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.jdkcontainer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class T01_ConcurrentMap {

    public static void main(String[] args) {
        // hashMap多线程操作容易造成环链表，程序锁死
//        Map<String,String> map = new HashMap<>();

        // synchronized实现并发安全
//        Map<String,String> map = new Hashtable<>();

        // 1.7分段锁+volatile等机制实现并发安全 ， 1.8 基于大量的cas实现并发安全
        Map<String,String> map = new ConcurrentHashMap<>();

        // 跳表实现有序的支持并发的Map
//        Map<String,String> map = new ConcurrentSkipListMap<>();

        // TreeMap 使用红黑树的解构实现有序的Map，非并发安全

        Random random = new Random();
        Thread[] ths = new Thread[100];

        CountDownLatch latch = new CountDownLatch(ths.length);

        long start = System.currentTimeMillis();

        for (int i = 0; i < ths.length ; i++) {
            ths[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    String name = Thread.currentThread().getName();
                    map.put(name + ":" + random.nextInt(10000),"a");
                }
                latch.countDown();
            },"thread-"+i);
        }
    }
}
