package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.container001;

import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于Reentranlock 实现生产者和消费者的程序。
 */
public class LockContainer<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private Integer MAX = 10;
    private Integer count = 0;

    private Lock lock = new ReentrantLock();

    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();


    public void put(T element){
        lock.lock();
        try {
            while (lists.size() == MAX){
                try {
                    producer.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            lists.add(element);
            consumer.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public T take(){
        T element = null;
        lock.lock();
        try {
            while (lists.size() == 0){
                try {
                    consumer.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            element = lists.removeFirst();
            producer.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return element;
    }
    public static void main(String[] args) {
        LockContainer<String> container = new LockContainer<>();

        for (int i = 0; i < 10 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 25 ; j++) {
                    ThreadUtils.sleep(TimeUnit.MILLISECONDS, 10L);
                    System.out.println(Thread.currentThread().getName() + " 消费了 " +container.take());
                }
            },"Consumer-"+i).start();
        }

        ThreadUtils.seconds(5L);

        for (int i = 0; i < 10 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 25 ; j++) {
                    container.put(Thread.currentThread().getName() + " 创造的第【 " + j + " 】个商品。");
                }
            },"Provider-"+i).start();
        }
    }
}
