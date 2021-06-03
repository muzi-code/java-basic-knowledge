package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.threadlocal;


/**
 * create by muzi 2019-06-29
 *
 * ThreadLocal使用不得当会引发线程安全的问题
 */
public class ThreadLocalUnsafe implements Runnable{
    public static ThreadLocal<Number> local = new ThreadLocal<Number>(){};
    public static Number number = new Number(0);
    public static class Number{
        private int count = 0;

        public Number(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    @Override
    public void run() {
        number.setCount(number.getCount() + 1);
        local.set(number); //
        System.out.println(Thread.currentThread().getName() + "  " + local.get()/*.getCount()*/);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5 ; i++) {
            new Thread(new ThreadLocalUnsafe()).start();
        }
    }

}
