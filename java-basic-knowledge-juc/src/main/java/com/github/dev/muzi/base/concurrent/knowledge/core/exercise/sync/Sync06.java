package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.sync;

/***
 * create by muzi  2019-06-26
 * 子类的同步方法调用父类的同步方法。
 */
public class Sync06 {

    public static class Child extends Father{
        @Override
        public synchronized void sing(){
            System.out.println("child -> singing start !");
            super.sing();
            System.out.println("child -> singing end !");
        }
    }

    public static class Father{
        public synchronized void sing(){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("father -> singing !");
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.sing();
    }

}
