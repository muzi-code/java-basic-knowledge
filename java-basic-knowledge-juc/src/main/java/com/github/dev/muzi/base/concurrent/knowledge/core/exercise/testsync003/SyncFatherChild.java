package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testsync003;

/**
 * 子类的同步方法可以调用父类的同步方法
 */
public class SyncFatherChild {

    public static void main(String[] args) {
        new Child().method();
    }
}


class Father{

    synchronized void method(){
        System.out.println(Thread.currentThread().getName() + " father method  run start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " father method  run end");
    }

}


class Child extends Father{

    @Override
    synchronized void method(){
        System.out.println(Thread.currentThread().getName() + " child method  run start");
        super.method();
        System.out.println(Thread.currentThread().getName() + " child method  run end");
    }
}