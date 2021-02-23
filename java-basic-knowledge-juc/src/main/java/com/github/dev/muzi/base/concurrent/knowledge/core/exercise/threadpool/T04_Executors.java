package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.threadpool;


import java.util.concurrent.Executors;

/**
 * 操作API的一个工具类
 *
 * 有一些工厂相关的方法，可以实例化一些线程池
 *
 * 也有一些工具方法，生产callable（有返回值和返回null的） 拿到Thread工厂
 */
public class T04_Executors {

    public static void main(String[] args) {

        // 具体内容在T05去实践学习
        Executors.newCachedThreadPool();


    }
}
