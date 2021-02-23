package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.jdkcontainer;

import lombok.Data;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class T06_DelayQueue {

    static BlockingQueue<MyTask> delayQueue = new DelayQueue<MyTask>();

    static Random random = new Random();

    @Data
    static class MyTask implements Delayed {
        long runningTime;

        public MyTask(long rt) {
            this.runningTime = rt;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        MyTask myTask01 = new MyTask(now + 1000);
        MyTask myTask02 = new MyTask(now + 2000);
        MyTask myTask03 = new MyTask(now + 3000);
        MyTask myTask04 = new MyTask(now + 4000);
        MyTask myTask05 = new MyTask(now + 5000);

        try {
            delayQueue.put(myTask01);
            delayQueue.put(myTask02);
            delayQueue.put(myTask03);
            delayQueue.put(myTask04);
            delayQueue.put(myTask05);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(delayQueue);

        for (int i = 0; i < 5 ; i++) {
            try {
                System.out.println(delayQueue.take());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
