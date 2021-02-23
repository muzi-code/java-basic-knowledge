package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.sync;

/**
 * create by muzi  2019-06-26
 * 脏读问题
 * 对写加锁，对读未加锁，可能会出现脏读。
 */
public class Sync04 {

    public static class Account{
        String name;
        double balance;

        private synchronized void set(String name,double balance){      //写操作
            this.name = name;
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            this.balance = balance;
        }

        private /*synchronized*/ double getBalance(String name){      //读操作
            return this.balance;
        }
    }

    public static void main(String[] args) {

        Account account = new Account();
        new Thread(()-> account.set("张三",150)).start();
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(account.getBalance("张三"));
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(account.getBalance("张三"));
    }
}
