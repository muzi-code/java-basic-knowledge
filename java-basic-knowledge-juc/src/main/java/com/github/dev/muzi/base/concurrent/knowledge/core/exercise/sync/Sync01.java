package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.sync;


/***
 * create by muzi  2019-06-26
 * synchronized 含义 ：
 *      对“对象”加锁，获取到锁的线程才能获得代码执行权。
 *      重点： synchronized锁的是对象而不是代码块或者方法。
 * synchronized 应用 ：
 *      根据加锁方式分为两种：
 *          1.代码块
 *          2.方法声明
 *      根据加锁对象分为三种：
 *          1.锁自定义对象
 *          2.锁this对象
 *          3.锁类对象（类.class，static method）
 */
public class Sync01 {

    private int count = 10;
    private static int CLASS_OBJECT_COUNT = 0;
    private final Object lock = new Object();

    //代码块:锁lock成员变量对象
    public void increment01(){
        synchronized (lock){
            count++;
            System.out.println(Thread.currentThread().getName() + " : " + count);
        }
    }

    //代码块:锁当前this对象
    public void increment03(){
        synchronized (this){
            count++;
            System.out.println(Thread.currentThread().getName() + " : " + count);
        }
    }

    //方法声明:锁当前this对象
    public synchronized void increment02(){
        count++;
        System.out.println(Thread.currentThread().getName() + " : " + count);
    }


    //方法声明:锁类对象
    public synchronized static void increment04(){  //这里相当于 synchronized(Sync01.class)
        CLASS_OBJECT_COUNT++;
        System.out.println(Thread.currentThread().getName() + " : " + CLASS_OBJECT_COUNT);
    }

    //代码块:锁类对象
    public static void increment05(){
        synchronized(Sync01.class){     //静态方法不能synchronized(this)
            CLASS_OBJECT_COUNT++;
            System.out.println(Thread.currentThread().getName() + " : " + CLASS_OBJECT_COUNT);
        }
    }

}
