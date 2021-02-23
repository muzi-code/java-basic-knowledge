package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.thread;

/**
 * create by muzi  2019-06-28
 * Jdk 中Thread类中描述的两种启动线程的方式。
 * Runnable 是对线程执行任务的抽象
 * Thread 是对线程的抽象
 *
 * Thread是有声明周期的，创建，就绪，运行，阻塞，销毁。
 * 在以上各个生命周期涉及到很多的方法：
 *      start() join() yield() sleep() wait() notify() interrupt() stop() run()
 * 需要明确：
 *      start() 和 run()的区别
 *      join() 和 yield() 的作用及使用方式
 *      wait() 和 notify() 的作用及使用方式
 *      interrupt() 对 sleep() wait() 抛出的异常如何进行处理。
 *      为何不用suspend() stop() resume() destory()等抢占式的方法。
 */
public class UseThread {

    /**
     * 对任务的抽象
     */
    public static class RunnableImpl implements Runnable{

        @Override
        public void run() {
            System.out.println("implements runnable");
        }
    }

    /**
     * 对线程的抽象
     */
    public static class ThreadChild extends Thread{

        @Override
        public void run() {
            System.out.println("extends thread");
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new RunnableImpl();
        Thread thread01 = new Thread(runnable);
        Thread thread02 = new ThreadChild();

        thread01.start();
        thread02.start();
    }
}
