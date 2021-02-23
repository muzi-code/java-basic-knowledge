package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.container001;


import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class SyncContainer<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private Integer MAX = 10;
    private Integer count = 0;


    public synchronized void put(T element){
        while (lists.size() == MAX){
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        lists.add(element);
        this.notifyAll();
    }

    public synchronized T take(){
        T element = null;
        while (lists.size() == 0){
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        element = lists.removeFirst();
        this.notifyAll();
        return element;
    }

    public static void main(String[] args) {
        SyncContainer<String> container = new SyncContainer<>();

        for (int i = 0; i < 10 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 25 ; j++) {
                    ThreadUtils.sleep(TimeUnit.MILLISECONDS,10L);
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
            },"Provider-"+1).start();
        }
    }
}
