package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.sync;

/**
 * create by muzi  2019-06-26
 * “对象”的同步方法执行时，非同步方法也可以同时执行。
 */
public class Sync03 {

    private synchronized void method01(){
        System.out.println(Thread.currentThread().getName() + "method01 start");
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "method01 end");
    }

    private /*synchronized*/ void method02(){
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "method02 process");
    }

    public static void main(String[] args) {
        Sync03 obj = new Sync03();

        new Thread(obj::method01,"Thread-1").start();
        new Thread(obj::method02,"Thread-2").start();

    }
}
