package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.thread;

import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;

/**
 * create by muzi  2019-06-28
 * Jdk 中的多线程编程都是协作式的，而不是抢占式的。
 * Jdk 协作式中断线程的方式
 * interrupt()      中断，修改线程的中断标识位
 * interrupted()    静态方法，判断线程是否中断，并将线程中断标识位置为false
 * isInterrupt()    判断线程是否中断
 *
 * 线程执行时若抛出线程中断异常（InterruptedException），则线程是不会理会中断的。
 * 线程执行过程中若遇到死锁的情况，则线程是不会中断的。
 */
public class UseInterrupt {

    public static class RunnableImpl implements Runnable{

        @Override
        public void run() {
            Thread now = Thread.currentThread();
            while (
                    /*!now.isInterrupted()*/
                    !Thread.interrupted()
            ){
                try {
                    System.out.println("implements runnable . interrupt status:" + now.isInterrupted());
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                    now.interrupt();
                }
            }
            System.out.println("\n\nthread stop . interrupt status:" + now.isInterrupted());
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableImpl());
        thread.start();
        ThreadUtils.seconds(2);
        thread.interrupt();
    }

}
