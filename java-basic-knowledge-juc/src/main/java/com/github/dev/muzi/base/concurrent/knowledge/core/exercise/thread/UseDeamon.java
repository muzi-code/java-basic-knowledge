package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.thread;

import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;


/**
 *  create by muzi 2019-06-28
 * 守护线程随着主线程的结束而结束
 * 守护线程中的finally代码块不一定会执行
 */
public class UseDeamon {



    public static class Deamon implements Runnable{

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10 ; i++) {
                    System.out.println("守护线程");
                    Thread.sleep(1000);
                }
            }catch (InterruptedException e){
            }finally {
                System.out.println("不一定会执行到的东西");
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Deamon());
        thread.setDaemon(true);
        thread.start();
        ThreadUtils.seconds(5);

    }
}
