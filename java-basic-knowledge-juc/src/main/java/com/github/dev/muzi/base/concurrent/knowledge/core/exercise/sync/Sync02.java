package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.sync;

/**
 * create by muzi  2019-06-26
 * 模拟一个大巴只有十张车票，5人抢这10个车票会不会出问题，会不会有人抢到重复的车票。
 */
public class Sync02 implements Runnable {

    private int ticket = 10;

    @Override
    public /*synchronized*/ void run() { //synchronized 保证了程序的原子性、可见性、有序性
        ticket--;
        System.out.println(Thread.currentThread().getName() + " : " + ticket);
    }

    public static void main(String[] args) {
        Sync02 task = new Sync02();

        for (int i = 0; i < 5 ; i++) {
            new Thread(task,"thread"+i).start();
        }
    }

}
