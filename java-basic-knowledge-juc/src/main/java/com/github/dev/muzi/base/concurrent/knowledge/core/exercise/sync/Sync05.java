package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.sync;

/**
 * create by muzi  2019-06-26
 * synchronized 是可重入锁。
 */
public class Sync05 {

    private synchronized void method01(){
        System.out.println("method01 start");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        method02();
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("method01 end");
    }

    private synchronized void method02(){
        System.out.println("method02 process");
    }

    public static void main(String[] args) {
        //synchronized 是可重入锁。
        Sync05 sync = new Sync05();
        sync.method01();
    }
}
