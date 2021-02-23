package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testsync001;

/**
 * 开发过程中尽量不要使用字符串作为锁对象，不然可能会产生死锁的问题
 */
public class SyncStringObj {

    String lock1 = "hello";
    String lock2 = "hello";

    void m1(){
        synchronized (lock1){

        }
    }

    void m2(){
        synchronized (lock2){

        }
    }

}
