package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.jdkcontainer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class T08_SychronusQueue {

    public static void main(String[] args) {

        // 生产者和消费者直接对接，如果没有消费者就会阻塞，put并不会存起来，和transfer一样的实现。  相当于在线一对一。
        BlockingQueue<String> queue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "consumer").start();

        // 没有消费者就会阻塞住
        try {
            queue.put("aaa");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(queue.size());
    }
}
