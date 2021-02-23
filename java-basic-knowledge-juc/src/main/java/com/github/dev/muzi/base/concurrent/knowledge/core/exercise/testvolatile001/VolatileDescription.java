package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testvolatile001;

/**
 *  无锁同步保证 线程间内存可见性 ， 禁止指令重排序
 *  但是它无法保证程序执行的原子性
 */
public class VolatileDescription {
    volatile boolean running = true;

    /**
     * 想让死循环停下，running置位false
     */
    void m(){
        System.out.println("m start");
        while (running){
            //线程有闲置的时候就可能会主线程刷新下数据
//            System.out.println("run");
        }
        System.out.println("m end");
    }

    public static void main(String[] args){
        VolatileDescription vola = new VolatileDescription();

        new Thread(vola::m,"t1").start();

        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        vola.running = false;
        System.out.println("set stop!!!");

    }
}
