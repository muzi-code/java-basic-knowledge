package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testsync003;


import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DemoCountDownLatch {


    volatile List<Object> list = new ArrayList<>();

    void add(Object o) {
        list.add(o);
    }

    int size() {
        return list.size();
    }

    public static void main(String[] args) {
        DemoCountDownLatch container = new DemoCountDownLatch();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t2 启动");

            if (container.size() != 5) {
                try {
                    countDownLatch.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("t2 结束");
            }
        }, "Thread-02").start();

        ThreadUtils.seconds(2L);

        new Thread(() -> {
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {
                container.add(new Object());

                System.out.println("add : " + i);

                if (container.size() == 5) {
                    countDownLatch.countDown();
                }

                ThreadUtils.seconds(1L);
            }
            System.out.println("t1 结束");
        }, "Thread-01").start();
    }
}
