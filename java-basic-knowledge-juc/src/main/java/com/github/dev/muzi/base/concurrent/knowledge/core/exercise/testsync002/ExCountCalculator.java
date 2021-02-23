package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.testsync002;

/**
 * 场景：计数器加锁和不加锁的输出内容的区别
 *
 * 分析程序的输出
 * 预期：9 8 7 6 5
 * 不加锁实际： x x x x x
 *
 * 不加锁代码块非原子，数值输出出来可能不符合预期。
 *
 * 验证：synchronized 声明的方法是原子的。
 */
public class ExCountCalculator implements Runnable {

    private int count = 10;

    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " : count=" + count);
    }

    public static void main(String[] args) {
        ExCountCalculator exCountCalculator = new ExCountCalculator();
        for (int i = 0; i < 5; i++) {
            new Thread(exCountCalculator, "Thread-" + i).start();
        }
    }
}
