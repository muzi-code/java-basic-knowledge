package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.sync;

/**
 * create by muzi  2019-06-26
 * synchronized 在抛出异常的时候会释放锁，其他线程就会抢到锁，导致程序与预期结果不一致。
 */
public class Sync07 {

    public static class Count implements Runnable{
        private int count = 0;
        @Override
        public synchronized void run() {
            System.out.println(Thread.currentThread().getName() + " start!");
            while (true){
                count++;
                System.out.println(Thread.currentThread().getName() + " : count = " + count);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                if (count == 5){
                    int i = count / 0;
                }
            }
        }
    }


    public static void main(String[] args) {
        Count countTask = new Count();
        for (int i = 0; i < 2 ; i++) {
            new Thread(countTask,"Thread"+i).start();
        }
    }
}
