package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.thread;

import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;

/**
 *  create by muzi 2019-06-28
 * join()方法的使用
 * 一个羞涩男孩在排队打饭，女神来了，他让女神到他前面。
 * 女神又让女神的男朋友到女神的前面打饭
 * 很尴尬
 */
public class UseJoin {

    public static class People implements Runnable{
        private Thread love;

        public People(Thread thread) {
            this.love = thread;
        }

        public People(){
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() +" 开始排队打饭... ...");
            try {
                if (love != null){
                    love.join();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            ThreadUtils.seconds(2);
            System.out.println(Thread.currentThread().getName() + " 打饭完成");
        }
    }

    public static class BadPeople implements Runnable{
        @Override
        public void run() {
            ThreadUtils.seconds(2);
            System.out.println("GoddessBoyFriend开始排队打饭....");
            System.out.println(Thread.currentThread().getName() + "GoddessBoyFriend打饭完成");
        }
    }



    public static void main(String[] args) {
        Thread goddessBoyFriend = new Thread(new BadPeople(),"女神男朋友");
        Thread goddess = new Thread(new People(goddessBoyFriend),"女神");
        Thread shyBoy = new Thread(new People(goddess),"羞涩男孩");
        ThreadUtils.seconds(1);
        shyBoy.start();
        goddess.start();
        goddessBoyFriend.start();
    }
}
