package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.sync;


import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;

/**
 * create by muzi  2019-06-27
 * 相同的字符串字面量，其实是运行时常量池的同一个对象。
 * 避免用字符串常量作为被锁定的对象。
 */
public class Sync10 {
    String str1 = "123456";
    String str2 = "123456";

    void method01(){
       synchronized (str1){
           System.out.println(Thread.currentThread().getName() + " start");
           ThreadUtils.seconds(2L);
           System.out.println(Thread.currentThread().getName() + " end");
       }
    }


    void method02(){
        synchronized (str2){
            System.out.println(Thread.currentThread().getName() + " start");
            ThreadUtils.seconds(2L);
            System.out.println(Thread.currentThread().getName() + " end");
        }
    }

    public static void main(String[] args) {
        Sync10 task = new Sync10();
        new Thread(task::method01,"Thread-01").start();
        new Thread(task::method02,"Thread-02").start();
    }

}
