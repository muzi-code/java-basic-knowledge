package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.threadlocal;

/**
 *  create by muzi  2019-06-28
 *  不用threadlocal
 */
public class NoThreadLocal {

    private static Integer count = new Integer(1);


    public static class Run implements  Runnable{
        int id ;

        public Run(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            //System.out.println(Thread.currentThread().getName() + " run");
            count = count + id;
            System.out.println(Thread.currentThread().getName() + " : " +count);
        }
    }


    public static void test(){
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5 ; i++) {
            threads[i] = new Thread(new Run(i),"Thread-"+i);
        }
        for (Thread t: threads) {
            t.start();
        }
    }

    public static void main(String[] args) {
        NoThreadLocal.test();
    }
}
