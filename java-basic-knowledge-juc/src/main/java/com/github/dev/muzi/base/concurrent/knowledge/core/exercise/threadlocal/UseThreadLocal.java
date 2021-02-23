package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.threadlocal;

/**
 * create by muzi  2019-06-28
 * threadlocal 的使用方式
 */
public class UseThreadLocal {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };


    public static class Run implements  Runnable{
        int id ;

        public Run(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " run");
            Integer s = threadLocal.get();
            s = s + id;
            threadLocal.set(s);
            System.out.println(Thread.currentThread().getName() + " : " +threadLocal.get());
        }
    }


    public static void test(){
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3 ; i++) {
            threads[i] = new Thread(new Run(i),"Thread-"+i);
        }
        for (Thread t: threads) {
            t.start();
        }
    }

    public static void main(String[] args) {
        UseThreadLocal.test();
    }
}
