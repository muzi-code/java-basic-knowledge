package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testvolatile001;

/**
 * create by muzi  2019-06-26
 *
 * volatile 的作用（无锁同步）
 *      保证两个线程之间共享变量/对象的可见性
 *      每个线程会存一份自己的共享变量副本，volatile解决的就是线程间共享变量的不及时同步的问题。
 *      此处volatile还具备一个happens-before问题
 *      对volatile的写优先于对volatile 的读。
 */
public class Volatile01 {

    /*volatile*/ boolean running  = true;

    void method01(){
        System.out.println("method01 start !");
        for (;running;){
            /*try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }*/
        }
        System.out.println("method01 end !");
    }

    public static void main(String[] args) {
        Volatile01 task = new Volatile01();

        new Thread(task::method01,"Thread01").start();

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        task.running = false;
    }
}
