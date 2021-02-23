package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.jdkcontainer;

import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class T05_BlockingQueue {

    static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
    static BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(10);

    static Random random = new Random();


    private static void testLinked(){
        new Thread(()->{
            for (int i = 0; i < 10000 ; i++) {
                try {
                    blockingQueue.put("a" + i);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"p1").start();


        for (int i = 0; i < 5 ; i++) {
            new Thread(()->{
                for (; ; ) {
                    try {
                        ThreadUtils.seconds(1);
                        System.out.println(Thread.currentThread().getName() + " : take -> " + blockingQueue.take());
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static void testArray(){
        for (int i = 0; i < 10 ; i++) {
            try {
                arrayBlockingQueue.put("a" + i);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        // 阻塞住  add会报异常  offer返回false   put阻塞
        try{
            System.out.println(arrayBlockingQueue.add("end" ));
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(arrayBlockingQueue.offer("end" ));

        try {
            arrayBlockingQueue.put("end" );
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(arrayBlockingQueue);
    }

    public static void main(String[] args) {
         // testLinked();

        testArray();
    }
}
