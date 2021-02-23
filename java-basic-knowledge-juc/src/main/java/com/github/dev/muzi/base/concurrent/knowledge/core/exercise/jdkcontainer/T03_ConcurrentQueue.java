package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.jdkcontainer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T03_ConcurrentQueue {

    public static void main(String[] args) {
        // 单向 先进先出 尾巴上加 头出
        Queue<String> queue = new ConcurrentLinkedQueue<>();

        // 双向队列  哪个方向都可以进哪个方向都可以出
//        Queue<String> queue = new ConcurrentLinkedDeque<>();

        for (int i = 0; i < 10 ; i++) {
            queue.offer("a" + i);
        }

        System.out.println(queue);
        System.out.println(queue.size());

        // 拿出来用删掉
        System.out.println(queue.poll());
        System.out.println(queue.size());

        // peek 拿出来用不删
        System.out.println(queue.peek());
        System.out.println(queue.size());
    }
}
