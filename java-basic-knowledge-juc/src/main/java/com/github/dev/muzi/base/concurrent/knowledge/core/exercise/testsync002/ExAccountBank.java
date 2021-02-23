package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testsync002;

import java.util.concurrent.TimeUnit;

/**
 * 第一次读取是 0
 * 第二次读取是 100
 *
 * 由于业务执行的时间过长，导致的数据不一致性。
 *
 * 业务代码只对写进行加锁，未对读进行加锁，引起脏读的现象。
 */
public class ExAccountBank {
    private String name;
    private double balance;

    public synchronized void set(String name,double balance){
        this.name = name;

        // 表示业务执行时长
        try {
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public /*synchronized*/ double getBalance(){
        return this.balance;
    }


    public static void main(String[] args) {
        ExAccountBank account = new ExAccountBank();

        new Thread(()-> account.set("zhangsan",100) ).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(account.getBalance());

        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(account.getBalance());
    }

}
